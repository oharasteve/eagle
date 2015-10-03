import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;


// Original author: Steven A. O'Hara, Sep 26, 2013

public class CrawlWikiTree {
	private TreeMap<String, Info> currIds;
	private HashMap<String, Info> sqlIds = new HashMap<String, Info>();

	private static class Info {
		public String wiki;
		public String key;
		public String code;
		public boolean male;
		
		public Info father;
		public String fatherKey;
		public Info mother;
		public String motherKey;
		
		public ArrayList<Info> children = new ArrayList<Info>();

		public String name;
		public String born;
		public String died;
	}

	private int _count = 0;
	private int _seq = 0;
	
	// Note: recursive
	private void doit(Info[] wikiPeople, Info person, String code) throws Exception {
		String id = person.wiki;
		System.out.println("***** Reading " + id);
		
		HashSet<Info> todo = new HashSet<Info>();
		
		String url = "http://www.wikitree.com/treewidget/" + id.replaceAll(" ", "%20") + "/9";
		
		String page = ReadHtml.readUrl(url);
		if (page.indexOf("Sorry, this profile is <a href=\"/wiki/Privacy\">Private") >= 0) {
			System.err.println("Sorry " + id + " is too recent.");
			return;
		}

		for (int i = 0; i < 64; i++) {
			wikiPeople[i] = new Info();
		}
		
		for (int n = 2; n <= 64; n++) {
			int namePos = page.indexOf("<a name=\"" + n + "\">");
			if (namePos < 0) {
				System.err.println("Unable to find name " + n + " under " + id);
				System.err.println(page.substring(0,3000));
				System.err.println();
				return;
			}
			
			// Get wiki id
			String treeWidget = "<a href=\"http://www.wikitree.com/treewidget/";
			int startPos = page.indexOf(treeWidget, namePos);
			if (startPos < 0) {
				continue;
			}

			// Go too far?
			int nextPos = page.indexOf("<a name=\"" + (n+1) + "\">");
			if (startPos > nextPos) {
				continue;	// Yes
			}
			
			startPos += treeWidget.length();
			int slash = page.indexOf("/", startPos);
			String wiki = page.substring(startPos, slash);
			if (currIds.containsKey(wiki)) {
				wikiPeople[n-1] = currIds.get(wiki);
				System.err.println("*** Duplicate person: " + wiki);
				continue;
			}
			
			Info info = new Info();
			info.code = code + "." + n;
			info.wiki = wiki;
			wikiPeople[n-1] = info;
			currIds.put(wiki, info);
			if (n > 32) {
				todo.add(info);
			}
			
			int gtPos = page.indexOf(">", slash);
			int ltPos = page.indexOf("<", gtPos);
			info.name = page.substring(gtPos+1, ltPos);
			
			int bornPos = page.indexOf("Born ", startPos);
			int dotPos = page.indexOf(".\n", bornPos);
			info.born = page.substring(bornPos + "Born ".length(), dotPos);
			
			if (n == 2) System.out.println("     " + info.born.replaceAll("\n\t", " "));
			_count++;
			System.out.println("     " + _count + ". n=" + n + " " + wiki);

			int diedPos = page.indexOf("Died ", startPos);
			dotPos = page.indexOf(".\n", diedPos);
			info.died = page.substring(diedPos + "Died ".length(), dotPos);
			
			int fatherPos = page.indexOf("Father of ", diedPos);
			int motherPos = page.indexOf("Mother of ", diedPos);
			int sonPos = page.indexOf("Son of ", diedPos);
			int daughterPos = page.indexOf("Daughter of ", diedPos);
			int malePos = (sonPos > 0 && sonPos < fatherPos ? sonPos : fatherPos);
			int femalePos = (daughterPos > 0 && daughterPos < motherPos? daughterPos : motherPos);
			info.male = malePos < femalePos;
			
			int endParaPos = page.indexOf("</p>", startPos);
			boolean eitherParent = false;
			if (fatherPos > 0 && fatherPos < endParaPos) {
				gtPos = page.indexOf(">", fatherPos);
				ltPos = page.indexOf("<", gtPos);
				int child = Integer.parseInt(page.substring(gtPos+1, ltPos));
				Info p = (child == 1 ? person : wikiPeople[child-1]);
				if (p.name == null) {
					throw new RuntimeException("No name for " + p.wiki);
				}
				p.father = info;
				info.children.add(p);
				eitherParent = true;
			}
			if (motherPos > 0 && motherPos < endParaPos) {
				gtPos = page.indexOf(">", motherPos);
				ltPos = page.indexOf("<", gtPos);
				int child = Integer.parseInt(page.substring(gtPos+1, ltPos));
				Info p = (child == 1 ? person : wikiPeople[child-1]);
				if (p.name == null) {
					throw new RuntimeException("No name for " + p.wiki);
				}
				p.mother = info;
				info.children.add(p);
				eitherParent = true;
			}
			if (!eitherParent) {
				System.err.println("Need a parent for " + wiki);
				System.err.println(page.substring(startPos, startPos+1000));
				System.exit(1);
			}
		}
		
		// Do their ancestors
		for (Info nxt : todo) {
			doit(new Info[64], nxt, nxt.code);		// Recurse
		}
	}
	
	private void readSql(String sqlName) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader(sqlName));
		String rec;
		int comma[] = new int[100];
		final String[] MONTHS = new String[] {
				"Jan", "Feb", "Mar", "Apr", "May", "Jun",
				"Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
		final String PREFIX = "INSERT INTO `People` VALUES (";
		while ((rec = br.readLine()) != null) {
			if (rec.startsWith(PREFIX)) {
				// Find all the fields
				int len = rec.length();
				boolean inQuotes = false;
				int commas = 0;
				char prevCh = ' ';
				for (int i = 0; i < len; i++) {
					char ch = rec.charAt(i);
					if (ch == '\'' && prevCh != '\\') {
						inQuotes = ! inQuotes;
					}
					if (ch == ',' && !inQuotes) {
						comma[commas] = i;
						commas++;
					}
					prevCh = ch;
				}
				
				int i = 0;
				String key = rec.substring(PREFIX.length()+1, comma[i]-1);
				String first = rec.substring(comma[i++]+2, comma[i]-1);
				String middle = rec.substring(comma[i++]+2, comma[i]-1);
				String maiden = rec.substring(comma[i++]+2, comma[i]-1);
				String last = rec.substring(comma[i++]+2, comma[i]-1);
				String jr = rec.substring(comma[i++]+2, comma[i]-1);
				String title = rec.substring(comma[i++]+2, comma[i]-1);
				@SuppressWarnings("unused")
				String nick = rec.substring(comma[i++]+2, comma[i]-1);
				String sex = rec.substring(comma[i++]+2, comma[i]-1);
				int monBorn = Integer.parseInt(rec.substring(comma[i++]+1, comma[i]));
				int dayBorn = Integer.parseInt(rec.substring(comma[i++]+1, comma[i]));
				int yrBorn = Integer.parseInt(rec.substring(comma[i++]+1, comma[i]));
				String whereBorn = rec.substring(comma[i++]+2, comma[i]-1);
				@SuppressWarnings("unused")
				String alive = rec.substring(comma[i++]+2, comma[i]-1);
				int monDied = Integer.parseInt(rec.substring(comma[i++]+1, comma[i]));
				int dayDied = Integer.parseInt(rec.substring(comma[i++]+1, comma[i]));
				int yrDied = Integer.parseInt(rec.substring(comma[i++]+1, comma[i]));
				String whereDied = rec.substring(comma[i++]+2, comma[i]-1);
				String motherKey = rec.substring(comma[i++]+2, comma[i]-1);
				String fatherKey = rec.substring(comma[i++]+2, comma[i]-1);
				String wiki = rec.substring(comma[i++]+2, comma[i]-1).replaceAll("\\\\'", "'");
				@SuppressWarnings("unused")
				String note = rec.substring(comma[i++]+2, len-2);
				
				if (wiki.equals("")) continue;	// No wiki code -> skip it
				
				Info info = new Info();
				info.wiki = wiki;
				info.key = key;
				String name = title + " " + first + " " + middle + " " + maiden + " " + last + " " + jr;
				info.name = name.trim().replaceAll("\\\\'", "'").replaceAll("  ", " ").replaceAll("  ", " ");
				info.male = sex.equals("M");
				
				String born = whereBorn + " ";
				if (dayBorn > 0) born += dayBorn + " ";
				if (monBorn > 0) born += MONTHS[monBorn-1] + " ";
				born += yrBorn;
				info.born = born;
				
				String died = whereDied + " ";
				if (dayDied > 0) died += dayDied + " ";
				if (monDied > 0) died += MONTHS[monDied-1] + " ";
				if (yrDied > 0) died += yrDied;
				info.died = died;
				
				info.motherKey = motherKey;
				info.fatherKey = fatherKey;
				
				sqlIds.put(info.wiki, info);
			}
		}
		
		// Connect up parents
		for (Info info : sqlIds.values()) {
			for (Info info2 : sqlIds.values()) {
				if (info2.fatherKey.equals(info.key)) {
					info2.father = info;
				}
				if (info2.motherKey.equals(info.key)) {
					info2.mother = info;
				}
			}
		}
		
		br.close();
	}
	
	private void printEm(PrintWriter pw) {
		for (Info info : currIds.values()) {
			_seq++;
			pw.println("<p>" + _seq + ".");
			pw.println("<table border=1>");
			pw.print("    <tr><td width=300><a name=\"" + info.wiki + "\">Wiki</a>: ");
			pw.println("<b><a href=\"http://www.wikitree.com/wiki/" + info.wiki + "\">" + info.wiki + "</a></b></td>");

			Info sql = null;
			if (sqlIds.containsKey(info.wiki)) {
				sql = sqlIds.get(info.wiki);
			}
			if (sql != null) {
				pw.print("    <td width=300><a name=\"" + sql.key + "\">Mine</a>: ");
				pw.println("<b><a href=\"/tree/view.php?Key=" + sql.key + "\">" + sql.key + "</a></b></td></tr>");
			}
			
			if (info.male) {
				pw.print("  <tr><td>Male: ");
			} else {
				pw.print("  <tr><td>Female: ");
			}
			pw.println("<b>" + info.name + "</b></td>");

			if (sql != null) {
				if (sql.male) {
					pw.print("    <td>Male: ");
				} else {
					pw.print("    <td>Female: ");
				}
				pw.println(sql.name + "</b></td>");
			} else {
				pw.println("  </tr>");
			}

			pw.println("  <tr><td>Born: <b>" + info.born.replace("abt ", "").replace("\n\t", " ").trim() + "</b>");
			if (sql != null) pw.println("    <td>Born: <b>" + sql.born.trim() + "</b>");
			pw.println("  </tr>");
			pw.println("  <tr><td>Died: <b>" + info.died.replace("abt ", "").replace("\n\t", " ").trim() + "</b>");
			if (sql != null) pw.println("    <td>Died:   <b>" + sql.died.trim() + "</b>");
			pw.println("  </tr>");

			if (info.father != null || (sql != null && sql.father != null)) {
				pw.println("  <tr>");
				if (info.father != null) {
					pw.println("  <tr><td>Father: <a href=\"#" + info.father.wiki + "\"><b>" + info.father.name + "</b></a>");
				} else {
					pw.println("    <td>&nbsp;");
				}
				if (sql != null) {
					if (sql.father != null) {
						pw.println("    <td>Father: <a href=\"#" + sql.father.key + "\"><b>" + sql.father.name + "</b></a>");
					} else {
						pw.println("    <td>&nbsp;");
					}
				}
				pw.println("  </tr>");
			}
			
			if (info.mother != null || (sql != null && sql.mother != null)) {
				pw.println("  <tr>");
				if (info.mother != null) {
					pw.println("    <td>Mother: <a href=\"#" + info.mother.wiki + "\"><b>" + info.mother.name + "</b></a>");
				} else {
					pw.println("    <td>&nbsp;");
				}
				if (sql != null) {
					if (sql.mother != null) {
						pw.println("    <td>Mother: <a href=\"#" + sql.mother.key + "\"><b>" + sql.mother.name + "</b></a>");
					} else {
						pw.println("    <td>&nbsp;");
					}
				}
				pw.println("  </tr>");
			}

			if (info.children.size() > 0) {
				if (info.children.size() == 1) {
					pw.println("  <tr><td>Child:");
				} else {
					pw.println("  <tr><td>Children:");
				}
				for (Info child : info.children) {
					pw.println(" <a href=\"#" + child.wiki + "\"><b>" + child.name + "</b></a>");
				}
				pw.println("  </tr>");
			}
			
			pw.println("</table>");
		}
	}
	
	public static void main(String[] args) {
		CrawlWikiTree m = new CrawlWikiTree();
		String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

		if (args.length < 3) {
			System.out.println("Usage: family.sql wiki.html wiki-ids");
			System.exit(0);
		}
		
		try {
			String outHtml = args[1];
			PrintWriter pw = new PrintWriter(new FileWriter(outHtml));
			pw.println("<html>");
			pw.println("<head>");
			pw.println("<title>More O'Hara Ancestors</title>");
			pw.println("</head>");
			pw.println("<body>");

			pw.println("<p>Ancient but unreliable family tree. The starting points are my grandparents.");
			for (int i = 2; i < args.length; i+=2) {
				m._seq++;
				pw.println("<br/>&nbsp;&nbsp;" + m._seq +". <a href=\"#" + args[i] + "\">" + args[i+1] + "</a>");
			}
			
			pw.println("<p>Here are some interesting people:<ul>");
			pw.println("<li><a href=\"#Caesar-30\">Julius Caesar (say what?)</a>");
			pw.println("<li><a href=\"#Plantagenet-143\">King John (lots of Kings and Queens)</a>");
			pw.println("<li><a href=\"#Of Han-2\">Chinese (couple of them)</a>");
			pw.println("<li><a href=\"#Kiev-27\">Russian (quite a few of them)</a>");
			pw.println("<li><a href=\"#King Of Scythia-5\">Phoeniusa Farsaidh King of Scythia (2600 BC)</a>");
			pw.println("</ul>");
			pw.println("Pick one of these people and follow the Child link all the way up.");
			
			m.readSql(args[0]);
			for (int i = 2; i < args.length; i+=2) {
				Info info = new Info();
				info.wiki = args[i];
				info.name = args[i+1];

				String letter =  abc.substring(i-2, i-1);
				pw.println("<hr/>");
				pw.println("<h2><a name=\"" + info.wiki + "\">" + info.name + " Ancestors</a></h2>");

				m.currIds = new TreeMap<String, Info>();
				m.doit(new Info[64], info, letter);
				m.printEm(pw);
			}

			pw.println("<hr/>");
			Date today = new Date();
			pw.println("<p>Last updated: " + today.toString());
			pw.println("</body>");
			pw.println("</html>");
			pw.close();
			System.out.println("Finished writing " + outHtml);
			System.exit(0);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(1);
		}
	}
}
