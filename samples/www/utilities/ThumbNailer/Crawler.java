import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;

// Original author: Steven A. O'Hara, Mar 26, 2011

public class Crawler
{
	private boolean VERBOSE = false;
	private String root;	// Typically C:\www2
	
	private class Photo
	{
		public String directory;
		public String photo;
		public String suffix;
	}
	
	private class PersonLink
	{
		public String description;
		public String key;
	}

	private int linkCount = 0;
	private PrintWriter prt;
	
	private HashSet<String> doneLinks = new HashSet<String>();
	private Hashtable<String,Photo> photos = new Hashtable<String,Photo>();
	
	private HashSet<String> people = new HashSet<String>();
	private LinkedList<PersonLink> peopleToCheck = new LinkedList<PersonLink>();
	
	private void checkLink(String fname) throws Exception
	{
		checkLink(null, fname, null, 0);
	}
	
	private void checkLink(LinkedList<String> todo, String fname, String source, int line) throws Exception
	{
		linkCount++;
		if (fname.startsWith("/")) fname = fname.substring(1);

		if (doneLinks.contains(fname)) return;	// Don't re-check anything
		doneLinks.add(fname);
		if (VERBOSE) System.out.println("************* Putting " + fname);
		
		String lowerName = fname.toLowerCase();
		
		// Make sure the file exists
		File f = new File(fname);
		if (!f.exists())
		{
			if (fname.indexOf("/.git/") >= 0) return;
			
			if (source != null)
			{
				// These are in github / appengine.
				if (source.startsWith("Dox/Pool/Lucky_Shot/aspastats/aspa/html/")) return;
				
				// UTSA stuff
				if (source.startsWith("Dox/Schools/Steve_UTSA/SVN2/MultiAnswer/")) return;
			}
			
			if (fname.contains("' + fn + '")) return;	// Ignore javascript junk ...
			if (fname.contains("{{")) return;			// Ignore Jinja / Django junk ...
			if (fname.startsWith("/static/")) return;	// From webpages ...
			if (fname.startsWith("static/")) return;	// From webpages ...
			
			prt.println("*** Missing file " + fname);
			if (source != null) prt.println("    " + source + " (line " + line + ")");
			return;
		}
		
		String localName = f.getCanonicalPath();
		if (!localName.endsWith(fname.replaceAll("\\/", "\\\\")))
		{
			prt.println("Case wrong for file " + fname + " (" + localName + ")");
			if (source != null) prt.println("    " + source + " (line " + line + ")");
			return;
		}
			
		// See if it links to other pages
		if (lowerName.endsWith(".html") ||
			lowerName.endsWith(".htm") ||
			lowerName.endsWith(".php"))
		{
			todo.add(fname);
		}
	}
	
	private void foundLink(LinkedList<String> todo, String currDir, String link, String source, int line) throws Exception
	{
		String[] RF = new String[] { "rf.php?fname=", "readfile.php?fname=" };
		for (String rf : RF)
		{
			if (link.startsWith(rf))
			{
				link = link.substring(rf.length());		// Toss this part, use the rest
			}
		}
		
		int question = link.indexOf('?');
		if (question > 0) link = link.substring(0, question);
		
		if (VERBOSE) System.out.println("     -> " + link);
		
		if (link.startsWith("mailto:")) return;
		if (link.startsWith("javascript:")) return;
		
		if (link.indexOf("www.oharasteve") >= 0)
		{
			if (!link.equals("http://www.oharasteve.com/ssl/index.html"))
			{
				throw new RuntimeException("Need to remove www.oharasteve");
			}
		}

		if (link.startsWith("http:")) return;
		if (link.startsWith("https:")) return;

		if (link.startsWith("/"))
		{
			checkLink(todo, link.substring(1), source, line);
		}
		else
		{
			String curr = currDir;
			
			// Trim down links
			while (link.startsWith("../"))
			{
				curr = curr.substring(0, curr.length()-1);	// Toss the trailing /
				int pos = curr.lastIndexOf('/');
				curr = curr.substring(0, pos + 1);
				link = link.substring(3);	// Toss the ../
			}
			
			checkLink(todo, curr + link, source, line);
		}
	}
	
	private void extractLink(LinkedList<String> todo, String currDir,
			String source, int line, String rec, int pos, String suffix) throws Exception
	{
		if (rec.charAt(pos) == '#') return;		// Ignore local links
		
		// Skip all the PHP generated lines.
		if (rec.indexOf(" . $", pos) > 0) return;
		
		if (source != null)
		{
			if (source.startsWith("Dox/Schools/Steve_UTSA/SVN2/MultiAnswer")) return;
		}

		if (rec.charAt(pos) == '"')
		{
			if (rec.charAt(pos+1) == '#') return;		// Ignore local links
			int ec = rec.indexOf('"', pos+1);
			String link = rec.substring(pos+1, ec);
			int poundSign = link.indexOf('#');
			if (poundSign > 0) link = link.substring(0, poundSign);
			foundLink(todo, currDir, link + suffix, source, line);
		}
		else if (rec.charAt(pos) == '\\' && rec.charAt(pos+1) == '\"')
		{
			int ec = rec.indexOf("\\\"", pos+1);
			foundLink(todo, currDir, rec.substring(pos+2, ec) + suffix, source, line);
		}
		else if (rec.charAt(pos) != '&' && rec.charAt(pos) != '<')
		{
			throw new Exception("missing quotes at column " + (pos+1));
		}
	}
	
	private void processPage(String indexFname) throws Exception
	{
		String needToDelete = null;
		
		// Special case: PHP files that don't need to read from a database
		if (indexFname.equalsIgnoreCase("CoolPrograms/FreeCell/Cards/index.php"))
		{
			needToDelete = "CoolPrograms\\FreeCell\\Cards\\index.html";
			indexFname = "CoolPrograms/FreeCell/Cards/index.html";
			// System.out.println("curr dir = " + System.getProperty("user.dir"));  prints c:\\www2
			Process p = Runtime.getRuntime().exec("cmd /c c:\\PHP\\php.exe CoolPrograms\\FreeCell\\Cards\\index.php > " + indexFname);
			p.waitFor();
		}
		
		LinkedList<String> todo = new LinkedList<String>();
		if (VERBOSE) System.out.println("Processing " + indexFname);
		
		// Find current directory
		String currDir = "";
		int slash = indexFname.lastIndexOf('/');
		if (slash > 0) currDir = indexFname.substring(0, slash+1);
		
		BufferedReader br = new BufferedReader(new FileReader(indexFname));
		String rec;
		int seq = 0;
		String root = "";
		
		while ((rec = br.readLine()) != null)
		{
			seq++;
			
			try
			{
				int pos = -1;
				while ((pos = rec.indexOf(" href=", pos+1)) > 0)
				{
					extractLink(todo, currDir, indexFname, seq, rec, pos+6, "");
				}
				
				pos = -1;
				while ((pos = rec.indexOf(" src=", pos+1)) > 0)
				{
					extractLink(todo, currDir, indexFname, seq, rec, pos+5, "");
				}
				
				if ((pos = rec.indexOf("  subdir(")) >= 0)
				{
					extractLink(todo, currDir, indexFname, seq, rec, pos+9, "/index.php");
				}
				
				if ((pos = rec.indexOf("  pic(")) >= 0)
				{
					extractLink(todo, currDir, indexFname, seq, rec, pos+6, "/index.php");
				}
				
				if ((pos = rec.indexOf("  vid(")) >= 0)
				{
					String[] pieces = rec.split(",");
					String fname = pieces[1].replaceAll("\"","").trim();
					String suffix = pieces[2].replaceAll("\"","").trim();
					foundLink(todo, currDir, fname + suffix, indexFname, seq);
					foundLink(todo, currDir + "thumbs/", fname + ".jpg", indexFname, seq);
				}

				// Try to get stuff out of the JavaScript (in collages folder)
				
				if (rec.indexOf("root = \"") > 0)
				{
					pos = rec.indexOf('\"');
					root = rec.substring(pos + 1, rec.length()-2);
				}
				
				String temp = rec.trim();
				String[] directions = new String[] { "nw(", "n(", "ne(", "w(", "c(", "e(", "sw(", "s(", "se(" };
				
				for (String s : directions)
				{
					if (temp.startsWith(s))
					{
						int nc = s.length();
						if (temp.substring(nc, nc + 7).equals("root + "))
						{
							String temp2 = "\"" + root + temp.substring(nc + 8);
							extractLink(todo, currDir, indexFname, seq, temp2, 0, "");
						}
						else
						{
							extractLink(todo, currDir, indexFname, seq, temp, nc, "");
						}
						break;
					}
				}
			}
			catch (Exception ex)
			{
				System.out.flush();
				prt.println("Problem at line " + seq  + " of " + indexFname);
				prt.println("     " + ex.getMessage());
				//ex.printStackTrace(System.err);
			}
		}
		br.close();
		
		// Now process all of its links
		for (String t : todo)
		{
			processPage(t);
		}
		
		if (needToDelete != null)
		{
			File f = new File(needToDelete);
			f.delete();
		}
	}
	
	private void findOrphans(String localFname) throws Exception
	{
		BufferedReader br = new BufferedReader(new FileReader(localFname));
		String rec;
		String dirName = "";
		while ((rec = br.readLine()) != null)
		{
			if (rec.indexOf("Directory of ") == 1)
			{
				dirName = rec.substring(14).replace(root, "..");
				dirName = dirName.replaceAll("\\\\", "/");
				if (dirName.startsWith("../")) dirName = dirName.substring(3);
			}
			
			if ((rec.indexOf(" AM ") > 0 || rec.indexOf(" PM ") > 0) && rec.indexOf("<DIR>") < 0)
			{
				String packed = rec.replaceAll("  *", " ");
				String[] pieces = packed.split(" ");
				String fname = pieces[4];

				String file = dirName + '/' + fname;
				if (!doneLinks.contains(file))
				{
					// Tons of exceptions ... don't bother reporting these as Orphans
					if (file.startsWith("grok/backup/")) continue;
					if (file.startsWith("family/") && file.endsWith(".php")) continue;
					if (file.startsWith("pool/") && file.endsWith(".php")) continue;
					if (file.startsWith("pool/") && file.endsWith(".jpg")) continue;
					if (file.startsWith("pool/") && file.endsWith(".css")) continue;
					if (file.startsWith("pool/") && file.endsWith(".sql")) continue;
					if (file.startsWith("iloychavez/")) continue;
					if (file.startsWith("logs/")) continue;
					if (file.startsWith("gbarj/")) continue;
					if (file.startsWith("map/")) continue;
					if (file.startsWith("news/")) continue;
					if (file.startsWith("originals/2006-mpegs/")) continue;
					if (file.startsWith("originals/2007-mpegs/")) continue;
					if (file.startsWith("originals/2008-mpegs/")) continue;
					if (file.startsWith("originals/2010-mpegs/")) continue;
					if (file.startsWith("rrcc/")) continue;
					if (file.indexOf("arrick") > 0) continue;
					// if (file.indexOf("_rachel") > 0) continue;
					if (file.startsWith("stats/")) continue;
					if (file.startsWith("utilities/")) continue;
					if (file.startsWith("Books/")) continue;
					if (file.equals("photos/Books/all1.bat")) continue;
 					if (file.startsWith("utsa/")) continue;
 					if (file.startsWith("Dox/Schools/Steve_UTSA/SVN2/MultiAnswer")) continue;
					if (file.startsWith("resume/sr")) continue;
					if (file.equals("resume/maxima.jpg")) continue;
					if (file.startsWith("resume/Old_Resumes/")) continue;
					if (file.startsWith("upload/")) continue;
					if (file.startsWith("cgi/")) continue;
					if (file.startsWith("_db_backups/")) continue;
					// if (file.startsWith("CoolPrograms/")) continue;
					if (file.startsWith("pool/Utilities/")) continue;
					if (file.endsWith("/rf.php")) continue;
					if (file.endsWith("/readfile.php")) continue;
					if (file.endsWith("/.htaccess")) continue;
					if (file.startsWith(".hcc.thumbs/")) continue;
					if (file.startsWith(".errordocs/")) continue;
					if (file.startsWith("Pool/")) continue;
					if (file.startsWith("Games/")) continue;
					if (file.startsWith("Eagle/")) continue;
					if (file.startsWith("tree/Graphs/junk/")) continue;
					if (file.equals("../family.sql")) continue;
					if (file.equals("../favicon.gif")) continue;
					if (file.equals("../favicon.ico")) continue;
					if (file.equals("../gdform.php")) continue;
					if (file.equals("../google877701acb74862e3.html")) continue;
					if (file.equals("../Dissertation.pdf")) continue;
					if (file.equals("../index.html")) continue;
					if (file.equals("../index0.html")) continue;
					if (file.equals("../missing.html")) continue;
					if (file.equals("../php.ini")) continue;
					if (file.equals("../robots.txt")) continue;
					if (file.equals("../sitemap.xml")) continue;
					if (file.equals("../webformmailer.php")) continue;
					if (file.equals("../welcome.html")) continue;
					if (file.equals("collages/template.html")) continue;
					if (file.equals("collages/pdf/chk.bat")) continue;
					if (file.equals("family/Tree.xlsx")) continue;
					if (file.equals("gbarj/google1e86c83e58087298.html")) continue;
					if (file.equals("Music/Music.cls")) continue;
					if (file.equals("Music/RunFirst.bat")) continue;
					if (file.equals("Music/RunLast.bat")) continue;
					if (file.equals("originals/index0.html")) continue;
					if (file.equals("photos/add.php")) continue;
					if (file.equals("photos/count.php")) continue;
					if (file.equals("photos/db.php")) continue;
					if (file.equals("photos/header.php")) continue;
					if (file.equals("photos/photos.php")) continue;
					if (file.equals("photos/screensaver.php")) continue;
					if (file.equals("photos/search.php")) continue;
					if (file.equals("photos/pdf/chk.bat")) continue;
					if (file.equals("photos/pdf/chk.out")) continue;
					if (file.equals("photos/pdf/chk.xlsx")) continue;
					if (file.equals("photos/pdf/counts.xls")) continue;
					if (file.equals("photos/pdf/pages.xls")) continue;
					if (file.equals("random/1998-04-30_Furrow_fails_math.pdf")) continue;
					if (file.equals("random/TheLastQuestion.txt")) continue;
					if (file.equals("resume/index.html")) continue;
					if (file.startsWith("statistics/")) continue;
					if (file.equals("tree/ohara2.jpg")) continue;
					if (file.equals("tree/ohara2short.jpg")) continue;
					if (file.equals("tree/quick.psf")) continue;
					if (file.equals("tree/tree1.pptx")) continue;
					if (file.equals("tree/tree2.pptx")) continue;
					if (file.equals("tree/tree3.pptx")) continue;
					if (file.equals("tree/Graphs/hblankup.gif")) continue;
					if (file.equals("tree/Lavillebeuvre/Jean_La_Villebeuvre_info.pdf")) continue;
					if (file.startsWith("tree/") && file.endsWith(".php")) continue;
					if (file.startsWith("tree/") && file.endsWith("Google_contacts.csv")) continue;
					if (file.startsWith("tree/WikiTree/")) continue;
					if (file.equals("videos/count.bat")) continue;
					if (file.equals("videos/videos.xlsx")) continue;
					if (file.equals("../thumb_Ghostbusters4.jpg")) continue;
					if (file.equals("Dox/pdf_count.txt")) continue;
					if (file.equals("Dox/sizes.bat")) continue;
					if (file.equals("Dox/sizes.txt")) continue;
					if (file.indexOf("Bryan/Dear_Valued_Customer.pdf") > 0) continue;
					if (file.startsWith("MultiAns/")) continue;
					if (file.indexOf("CoolPrograms") >= 0)
					{
						if (file.endsWith(".class")) continue;
						if (file.endsWith(".jar")) continue;
					}
					if (file.indexOf("/.git/") >= 0) continue;
					if (file.startsWith(".svn/")) continue;
					if (file.startsWith("grok/") && file.endsWith(".php")) continue;
					
					if (file.endsWith("/.classpath")) continue;
					if (file.endsWith("/.project")) continue;
					if (file.contains("/pdfwords/")) continue;
					if (file.endsWith("/.picasa.ini")) continue;

					// These are rejected by David:
					if (file.indexOf("92_david_and_rachel") > 0) continue;
					if (file.indexOf("28_pat_and_rachel") > 0) continue;
					if (file.indexOf("9091_pg016_david_rachel_steve_harry") > 0) continue;
					
					// These are rejected by Jeanne:
					if (file.indexOf("A10-0982") > 0) continue;
					if (file.indexOf("dec09_scan0065C") > 0) continue;
					if (file.indexOf("dec09_scan0065D") > 0) continue;
					if (file.indexOf("dec09_scan0065E") > 0) continue;
					if (file.indexOf("dec09_scan0066A") > 0) continue;
					if (file.indexOf("dec09_scan0076C") > 0) continue;
					
					// These are rejected by Chris:
					if (file.indexOf("Greer-76") > 0) continue;
					
					// These are rejected by Daisy:
					if (file.indexOf("hungry_lance") > 0) continue;
					if (file.indexOf("60_daisy_and_lance") > 0) continue;
					
					// Rejected by Bill Berberich:
					if (file.indexOf("reg06_PICT0186") > 0) continue;
					
					prt.println("*** Orphan: " + file);
				}
			}
		}
		br.close();
	}
	
	private void findLinks(String linkFname) throws Exception
	{
		LinkedList<String> todo = new LinkedList<String>();
		BufferedReader br = new BufferedReader(new FileReader(linkFname));
		String rec;

		int seq = 0;
		while ((rec = br.readLine()) != null)
		{
			seq++;
			String[] tokens = rec.split(",");
			if (tokens.length < 2)
			{
				br.close();
				throw new Exception("Error at line " + seq + " in " +  linkFname + "\n" + rec);
			}
			
			if (tokens[0].equals("Photo"))
			{
				if (tokens.length < 3)
				{
					br.close();
					throw new Exception("Error at Photo line " + seq + " in " +  linkFname + "\n" + rec);
				}
				String orig = "";
				if (tokens.length > 4) orig = tokens[4];
				addPhoto(todo, tokens[1], tokens[2], tokens[3], orig, linkFname, seq);
			}
			else if (tokens[0].equals("PeoplePhoto"))
			{
				addPeoplePhoto(tokens[1], tokens[2]);
			}
			else if (tokens[0].equals("Video"))
			{
				addVideo(tokens[1], tokens[2], tokens[3]);
			}
			else if (tokens[0].equals("Person"))
			{
				String mom = "";
				String dad = "";
				if (tokens.length > 2) mom = tokens[2];
				if (tokens.length > 3) mom = tokens[3];
				addPerson(tokens[1], mom, dad);
			}
			else if (tokens[0].equals("Detail"))
			{
				addDetail(tokens[1]);
			}
			else if (tokens[0].equals("Marriage"))
			{
				addMarriage(tokens[1], tokens[2]);
			}
			else
			{
				br.close();
				throw new Exception("Unexpected keyword at line " + seq + " of " + linkFname + " : " + tokens[0]);
			}
		}
		br.close();
		
		for (PersonLink pl : peopleToCheck)
		{
			if (!people.contains(pl.key))
			{
				prt.println("*** Missing key " + pl.key + " " + pl.description);
			}
		}

		// Now process all of its links
		for (String t : todo)
		{
			processPage(t);
		}
	}
	
	private void addPhoto(LinkedList<String> todo, String directory, String photo,
			String suffix, String original, String source, int line) throws Exception
	{
		Photo p = new Photo();
		p.directory = directory;
		p.photo = photo;
		p.suffix = suffix;
		
		photos.put(photo, p);
		
		String name = directory + "/thumbs/" + photo + "_thumb" + suffix;
		checkLink(name);
		
		String thumb = directory + "/" + photo + suffix;
		checkLink(thumb);

		checkLink(todo, directory + "/index.php", source, line);
		
		if (!original.isEmpty())
		{
			int pdfPos = original.indexOf(".pdf");
			if (pdfPos > 0)
			{
				int lastSlash = original.lastIndexOf('/');
				String oDir = original.substring(0, lastSlash);
				String oName = original.substring(lastSlash+1, pdfPos);
				String oThumb = oDir + "/thumbs/" + oName + "_pdf_thumb.jpg";
				checkLink(oThumb);
			}
			checkLink(original);
		}
	}

	private void addPeoplePhoto(String photo, String key) throws Exception
	{
		Photo p = photos.get(photo);
		if (p == null)
		{
			prt.println("*** Missing photo " + photo + " for " + key);
			return;
		}
		
		String name = p.directory + "/" + p.photo + p.suffix;
		checkLink(name);
		
		checkKey("photo", key);
	}

	private void addVideo(String directory, String video, String photo) throws Exception
	{
		checkLink(photo);
		checkLink(directory + "/" + video);
	}

	private void addPerson(String key, String mother, String father) throws Exception
	{
		people.add(key);
		
		if (!mother.isEmpty()) checkKey("mother", mother);
		if (!father.isEmpty()) checkKey("father", father);
	}

	private void addDetail(String key) throws Exception
	{
		checkKey("detail", key);
	}

	private void addMarriage(String husband, String wife) throws Exception
	{
		checkKey("husband", husband);
		checkKey("wife", wife);
	}

	private void checkKey(String description, String key)
	{
		PersonLink pl = new PersonLink();
		pl.description = description;
		pl.key = key;
		peopleToCheck.add(pl);
	}

	private void doIt(String indexHtml, String localFiles, String linksCsv, String outName) throws Exception
	{
		prt = new PrintWriter(new FileWriter(outName));
		findLinks(linksCsv);
		processPage(indexHtml);
		findOrphans(localFiles);
		prt.close();
	}
	
	public static void main(String[] args)
	{
		if (args.length != 5)
		{
			System.out.println("Usage: Crawler root <index.html> <local.files> <links.csv> <crawl.out>");
			System.exit(0);
		}
		
		Crawler c = new Crawler();
		c.root = args[0];
		try
		{
			c.doIt(args[1], args[2], args[3], args[4]);
			System.out.println("Links checked = " + c.linkCount);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			System.exit(1);
		}
		System.exit(0);
	}
}
