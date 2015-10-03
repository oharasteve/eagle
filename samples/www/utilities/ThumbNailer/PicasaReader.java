import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map.Entry;


// Original author: Steven A. O'Hara, Mar 17, 2012

public class PicasaReader
{
	private final String HEX = "0123456789abcdef";
	private PrintWriter faceWriter;
	private int _faces = 0;
	private int _errs = 0;

	private int _adds = 0;
	private int _noTrees = 0;
	
	private enum findEnum { FIND_CONTACTS, FIND_FACES };
	
	private Hashtable<NameNumber,Contact> contacts = new Hashtable<NameNumber,Contact>();
	private Hashtable<String,Contact> decrypt = new Hashtable<String,Contact>();

	private HashSet<String> missingPhotos = new HashSet<String>();
	
	private Hashtable<String,String> picasaContacts = new Hashtable<String,String>();
	
	private class NameNumber
	{
		public String name;
		public int number;
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + number;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			NameNumber other = (NameNumber) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (number != other.number)
				return false;
			return true;
		}

		private PicasaReader getOuterType() {
			return PicasaReader.this;
		}
	}
	
	private class FaceInfo implements Comparable<FaceInfo>
	{
		int left;
		int top;
		int right;
		int bottom;
		String code;
		int lowYear;
		int highYear;
		
		public int compareTo(FaceInfo other)
		{
			return left - other.left;
		}
	}

	private class YearRange
	{
		String prefix;
		int lowYear;
		int highYear;
	}
	
	private ArrayList<YearRange> yearRanges = new ArrayList<YearRange>();
	
	private class Contact
	{
		String shortName;
		String treeName;		// Can be blank
		boolean found = false;
		boolean skip = false;
	}
	
	private Contact add(String shortName, String treeName, String photoName)
	{
		return add(shortName, treeName, photoName, 1);
	}
	
	private Contact noTree(String shortName, String photoName)
	{
		return noTree(shortName, photoName, 1);
	}
	
	private Contact noTree(String shortName, String photoName, int faceNumber)
	{
		_noTrees++;
		Contact contact = add(shortName, "", photoName, faceNumber);
		// if (contact != null) contact.skip = true; -- Don't skip anybody!
		return contact;
	}
	
	private Contact add(String shortName, String treeName, String photoName, int faceNumber)
	{
		if (photoName.length() == 0) return null;
	
		Contact contact = new Contact();
		contact.shortName = shortName.replaceAll("'", "");
		if (treeName != null)
		{
			contact.treeName = treeName.replaceAll(" ", "");
		}
		
		NameNumber nn = new NameNumber();
		nn.name = photoName;
		nn.number = faceNumber;
		
		if (! contacts.containsKey(nn)) _adds++;		// Don't count duplicates as 2+ people
		
//		if (contacts.containsKey(nn))
//		{
//			Contact oldContact = contacts.get(nn);
//			System.err.println("*** Duplicate key for " + shortName + ": " + photoName + " with " + oldContact.shortName);
//			errs++;
//		}
		
		contacts.put(nn, contact);
		return contact;
	}
		
	private int getHex(String hex) throws Exception
	{
		int value = 0;
		for (int i = 0; i < 4; i++)
		{
			char ch = hex.charAt(i);
			int index = HEX.indexOf(ch);
			if (index < 0) throw new Exception("Bad hex number: " + hex);
			value = 16 * value + index;
		}
		return value;
	}

	private FaceInfo[] collectFaces(String rec, int lowYear, int highYear, String photoName, File dirName) throws Exception
	{
		// Need to sort by "Left" position within the picture
		String[] pieces = rec.substring(6).split(";");
		ArrayList<FaceInfo> unsortedFaces = new ArrayList<FaceInfo>();

		for (String piece : pieces)
		{
			if (piece.startsWith("rect64("))
			{
				int comma = piece.indexOf("),");
				
				String rect = piece.substring(7, comma);
				while (rect.length() < 16)
				{
					rect = "0" + rect;
				}
				
				String code = piece.substring(comma + 2);
				if (code.equals("ffffffffffffffff")) continue;
				
				FaceInfo faceInfo = new FaceInfo();
				faceInfo.left = getHex(rect.substring(0, 4));
				faceInfo.top = getHex(rect.substring(4, 8));
				faceInfo.right = getHex(rect.substring(8, 12));
				faceInfo.bottom = getHex(rect.substring(12, 16));
				faceInfo.code = code;
				faceInfo.lowYear = lowYear;
				faceInfo.highYear = highYear;
				
				// See if it is a duplicate!
				for (FaceInfo soFar : unsortedFaces)
				{
					if (faceInfo.code.equals(soFar.code))
					{
						String hint = "<unknown>";
						if (picasaContacts.containsKey(faceInfo.code))
						{
							hint = picasaContacts.get(faceInfo.code);
						}
						System.err.println("Duplicate face " + hint + " in " +
								photoName.replaceAll("-", " ") + " " + dirName.getCanonicalPath());
						_errs++;
					}
				}

				unsortedFaces.add(faceInfo);
			}
		}
		
		FaceInfo[] sortedFaces = new FaceInfo[unsortedFaces.size()];
		sortedFaces = unsortedFaces.toArray(sortedFaces);
		Arrays.sort(sortedFaces);
		return sortedFaces;
	}
	
	private boolean findContacts(File picasa, int lowYear, int highYear) throws Exception
	{
		BufferedReader br = new BufferedReader(new FileReader(picasa));
		
		String rec;
		String photoName = "????";
		while ((rec = br.readLine()) != null)
		{
			if (rec.startsWith("["))
			{
				if (rec.startsWith("[Contacts")) continue;
				if (rec.equals("[Picasa]")) continue;
				if (rec.equals("[photoid]")) continue;
				int dot = rec.lastIndexOf('.');
				if (dot < 0)
				{
					System.err.println("Missing the dot in " + rec);
					System.exit(1);
				}
				photoName = rec.substring(1, dot);
			}
			else if (rec.startsWith("faces="))
			{
				FaceInfo[] sortedFaces = collectFaces(rec, lowYear, highYear, photoName, picasa);
				
				int faceNumber = 0;
				for (FaceInfo faceInfo : sortedFaces)
				{
					faceNumber++;

					NameNumber nn = new NameNumber();
					nn.name = photoName;
					nn.number = faceNumber;
					
					if (contacts.containsKey(nn))
					{
						Contact contact = contacts.get(nn);
						String name = contact.shortName;
						if (contact.found)
						{
							System.err.println("**** Duplicate photo key for " + name + " (in " + photoName + ")");
							_errs++;
						}
						contact.found = true;

//						System.out.print("Found " + faceInfo.code + ": " + contact.shortName + " in " + photoName);
//						if (faceNumber > 1) System.out.print(" (#" + faceNumber + ")");
//						int left = (100 * faceInfo.left) / 65535;
//						System.out.println(" left=" + left + "%");
						
						if (decrypt.containsKey(faceInfo.code))
						{
							String oldName = decrypt.get(faceInfo.code).shortName;
							System.err.println("**** Duplicate photo key for " + name + " (" + oldName + " in " + photoName + ")");
							_errs++;
						}
						
						decrypt.put(faceInfo.code, contact);
					}
				}
			}
		}
		
		br.close();
		return true;
	}
	
	private boolean findFaces(File picasa, int lowYear, int highYear) throws Exception
	{
		BufferedReader br = new BufferedReader(new FileReader(picasa));
		
		String rec;
		String photoName = "????";
		while ((rec = br.readLine()) != null)
		{
			if (rec.startsWith("["))
			{
				if (rec.startsWith("[Contacts")) continue;
				if (rec.equals("[Picasa]")) continue;
				if (rec.equals("[photoid]"))
				{
					rec = br.readLine();
					int pos = rec.indexOf('=');
					String photoid = rec.substring(pos+1);
					System.err.println("Missing photoid: " + photoid + " in " + picasa.getCanonicalPath());
					_errs++;
					continue;
				}
				
				int dot = rec.lastIndexOf('.');
				if (dot < 0)
				{
					System.err.println("Missing dot in " + rec);
					System.exit(1);
				}
				photoName = rec.substring(1, dot);
			}
			else if (rec.startsWith("faces="))
			{
				FaceInfo[] sortedFaces = collectFaces(rec, lowYear, highYear, photoName, picasa);

				if (missingPhotos.contains(photoName)) continue;	// Don't bother ... picture is not on the website
				
				int faceNumber = 0;
				for (FaceInfo faceInfo : sortedFaces)
				{
					faceNumber++;
					
					if (!decrypt.containsKey(faceInfo.code))
					{
						//if (!faceInfo.code.equals("dc05f218e9b374b9") &&	// This is a second Robin
						//	!faceInfo.code.equals("657db71f9ee6d659") &&	// This is a second Lance
						//	!faceInfo.code.equals("71dd8bb3282d0ed"))		// This is a second Shane
						
						// Maybe it is in picasa's contact list?
						String hint = "";
						if (picasaContacts.containsKey(faceInfo.code))
						{
							hint = "  (" + picasaContacts.get(faceInfo.code) + ")";
						}
						System.err.println("Need a name for " + photoName + ", face #" + faceNumber +
								" code: " + faceInfo.code + hint + " " + picasa.getCanonicalPath());
						_errs++;
						continue;
					}

					Contact contact = decrypt.get(faceInfo.code);
					
					// Try to get the year from the photo name
					int year1 = faceInfo.lowYear;
					int year2 = faceInfo.highYear;
					if (photoName.length() >= 4)
					{
						char c1 = photoName.charAt(0);
						char c2 = photoName.charAt(1);
						char c3 = photoName.charAt(2);
						char c4 = photoName.charAt(3);
						if (Character.isDigit(c1) && Character.isDigit(c2) && Character.isDigit(c3) && Character.isDigit(c4))
						{
							int yr = Integer.parseInt(photoName.substring(0, 4));
							if (yr >= 1800 && yr <= 2100)
							{
								year1 = yr;
								year2 = yr;
							}
						}
						
					}
					
					if (!contact.skip)
					{
						// Skip strange animated gif's 2013-Florida\sep13a_017.jpg is odd
						if (!photoName.endsWith("-MIX") && !photoName.endsWith("-MOTION"))
						{
							faceWriter.println(photoName + "," + faceInfo.left + "," + faceInfo.right + "," +
									faceInfo.top + "," + faceInfo.bottom + "," + contact.shortName + "," + contact.treeName + "," +
									year1 + "," + year2);
							_faces++;
						}
					}
				}
			}
		}
		
		br.close();
		return true;
	}

	private boolean doDir(File dir, findEnum whichPass, int depth, int lowYear, int highYear) throws Exception
	{
		if (depth == 1 && whichPass == findEnum.FIND_FACES)
		{
			System.out.println("Processing " + dir.getName() + " ...");
		}
		
		if (dir.isDirectory())
		{
			File items[] = dir.listFiles();
			for (File item : items)
			{
				if (item.isDirectory())
				{
					String dirName = item.getName();
					if (dirName.equals("thumbs")) continue;
					if (dirName.equals("pdf")) continue;
					
					int year1 = lowYear;
					int year2 = highYear;

					for (YearRange range : yearRanges)
					{
						if (dirName.startsWith(range.prefix))
						{
							// Try to narrow down the range of years
							year1 = range.lowYear;
							year2 = range.highYear;
						}
					}
					
					doDir(item, whichPass, depth+1, year1, year2);	// Recurse
				}
				else if (item.getName().equals(".picasa.ini"))
				{
					switch(whichPass)
					{
					case FIND_CONTACTS :
						findContacts(item, lowYear, highYear);
						break;
					case FIND_FACES :
						findFaces(item, lowYear, highYear);
						break;
					}
				}
			}
		}
		
		return true;
	}
	
	private boolean doFaces(String dirName, String faceName) throws Exception
	{
		faceWriter = new PrintWriter(new FileWriter(faceName));
		faceWriter.println("Photo,Left,Right,Top,Bottom,Name,Person,LowYear,HighYear");
		
		File dir = new File(dirName);
		
		initContacts();
		initYearRanges();
		initMissing();
		
		System.out.println("Family=" + (_adds-_noTrees) + " Friends=" + _noTrees);
		doDir(dir, findEnum.FIND_CONTACTS, 0, 0, 0);
		for (Entry<NameNumber,Contact> entry : contacts.entrySet())
		{
			if (entry.getValue().found == false)
			{
				System.err.println("**** Never found " + entry.getValue().shortName +
						" (looking for " + entry.getKey().name + " #" + entry.getKey().number + ")");
				_errs++;
			}
		}
		System.out.println();
		
		doDir(dir, findEnum.FIND_FACES, 0, 0, 0);
		System.out.println();
		
		faceWriter.close();
		
		System.out.println("Wrote " + _faces + " faces to " + faceName);
		
		return true;
	}
	
	private void readContacts(String contactsFilename) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(contactsFilename));
		String rec;
		while ((rec = br.readLine()) != null)
		{
			int idPos = rec.indexOf("id=");
			int namePos = rec.indexOf("name=");
			if (idPos > 0 && namePos > 0)
			{
				int secondQuote = rec.indexOf('"', idPos+4);
				String id = rec.substring(idPos+4, secondQuote);
				secondQuote = rec.indexOf('"', namePos+6);
				String name = rec.substring(namePos+6, secondQuote);
				if (! picasaContacts.containsKey(id))
				{
					// System.out.println("**** " + id + "  '" + name + "'");
					picasaContacts.put(id, name);
				}
			}
		}
		br.close();
	}
	
	public static void main(String[] args)
	{
		if (args.length != 3)
		{
			System.err.println("Usage: directory face.csv contacts.xml");
			System.exit(1);
		}
		
		PicasaReader pr = new PicasaReader();
		try
		{
			pr.readContacts(args[2]);
			pr.doFaces(args[0], args[1]);
		}
		catch (Exception ex)
		{
			System.err.println("Dang. PicasaReader bombed out.");
			ex.printStackTrace(System.err);
			System.exit(2);
		}

		System.out.println();
		System.err.println("Errors = " + pr._errs);
		System.exit(0);
	}
	
	private void addYearRange(String prefix, int lowYear, int highYear)
	{
		YearRange range = new YearRange();
		range.prefix = prefix;
		range.lowYear = lowYear;
		range.highYear = highYear;
		
		yearRanges.add(range);
	}
	
	private void initMissing()
	{
		missingPhotos.add("Greer-7656");
		missingPhotos.add("Greer-7650");
		missingPhotos.add("Portrait2_Shane_img068-001");
		missingPhotos.add("82_hungry_lance");
		missingPhotos.add("60_daisy_and_lance");
		missingPhotos.add("92_david_and_rachel");
		missingPhotos.add("28_pat_and_rachel");
	}

	private void initYearRanges()
	{
		addYearRange("pre-1980", 0, 1980);
		addYearRange("years-1980-1990", 1980, 1990);
		addYearRange("years-1990-1996", 1990, 1996);
		addYearRange("years-1996-2001", 1996, 2001);
		addYearRange("years-2001-2003", 2001, 2003);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());	// Now
		for (int year = 2004; year <= cal.get(Calendar.YEAR); year++)
		{
			addYearRange("year-" + year, year, year);
		}
		
		addYearRange("shane_jamie", 2005, 2007);
		
		addYearRange("1980", 1980, 1980);
		addYearRange("1980-81", 1980, 1981);
		addYearRange("1980-88", 1980, 1988);
		addYearRange("1982", 1982, 1982);
		addYearRange("1983", 1983, 1983);
		addYearRange("1984-1985", 1984, 1985);
		addYearRange("1985-1986", 1985, 1986);
		addYearRange("1986-1987", 1986, 1987);
		addYearRange("1988", 1988, 1988);
		addYearRange("1988-89", 1988, 1989);
		addYearRange("1989", 1989, 1989);
		addYearRange("1989-90", 1989, 1990);
		addYearRange("1990", 1990, 1990);
		
		addYearRange("1990-91", 1990, 1991);
		addYearRange("1991-92", 1991, 1992);
		addYearRange("1992-93", 1992, 1993);
		addYearRange("1994-95", 1994, 1995);
		addYearRange("1995", 1995, 1995);
		addYearRange("1995-96", 1995, 1996);
		
		addYearRange("1996-97", 1996, 1997);
		addYearRange("1997-98", 1997, 1998);
		addYearRange("1998", 1998, 1998);
		addYearRange("1998-99", 1998, 1999);
		addYearRange("1999-2000", 1999, 2000);
		addYearRange("2000", 2000, 2000);
		addYearRange("2001", 2001, 2001);

		for (int year = 2002; year <= cal.get(Calendar.YEAR); year++)
		{
			addYearRange("" + year, year, year);
		}

		addYearRange("1998-2002", 1998, 2002);
		addYearRange("2001-2002", 2001, 2002);
	}

	private void initContacts()
	{
		add("Abigael O'Hara", "Abigael OHara", "mar11_141");
		add("Adaline O'Hara", "Adaline OHara", "aug11_332");
		add("Aimee Byrnes", "Aimee Sikes", "AimeeGraduationNinon");
		add("Aimee Day", "Aimee Day", "Mother1936");
		add("Aimee Sikes", "Aimee Sikes", "IMG_20150830_142336", 1);
		add("Alex McNeill", "Alexander McNeill", "Daniel_McNeill_and_family", 7);
		add("Allie Pungello", "Allison Pungello", "img142_Allie_Pungello_9th_grade_2006");
		add("Ally O'Hara", "Ally OHara", "Gala037");
		add("Andre Roman", "Andre Roman", "AndreRoman");
		add("Andrea Day", "Andrea Day", "img239_Andrea");
		add("Andrew Smyth", "Andrew Smyth", "dec11_img031", 1);
		add("Ann Lynch", "Ann Lynch", "10_lady3");
		add("Ann Marcott", "Ann Marcott", "j2_ann");
		add("Anna Antoni", "Anna Schneider", "omi_pic08", 5);
		add("Anna Hyman", "Anna Hyman", "AnnaHyman_litho");
		add("Anna Wiggins", "Anna Wiggins", "mar12d_061");
		add("Annaliese", "Annaliese OHara", "IMG_20150730_101306");
		add("Anne West", "Anne Rhea", "anne_rhea");
		add("Annika O'Hara", "Annika OHara", "B10-0997");
		add("Art O'Hara", "Arthur OHara", "Slides_01_001");
		add("Arthur Vincent O'Hara", "Arthur Vincent OHara", "jul07_005");
		add("August Antoni", "August Antoni", "omi_pic08", 3);
		add("Ava Burkat", "Ava Burkat", "apr12_DSC00849", 1);
		add("Avangeline O'Hara", "Avangeline OHara", "IMG_20150813_164750", 1);
		add("Barrie Byrnes", "Barrie Byrnes", "May2008_mom_008", 1);
		add("Bea Day", "Bea Day", "nov10_Grandpa_and_Bea", 2);
		add("Ben Kohler", "Benjamin Kohler", "A27-2403");
		add("Bennett Jansen", "Bennett Jansen", "Regatta2015-057");
		add("Bentley Byrnes", "Bentley Byrnes", "BentleyObit");
		add("Bernhard Antoni", "Bernhard Antoni", "rosa_bernhard", 1);
		add("Beverly Johnson", "Beverly Johnson", "D03-0144", 1);
		add("Bill Berberich", "William Berberich", "img139_Bill");
		add("Bill Torrence", "William Torrence", "dec09_scan0116A");
		add("Billy Butts", "James Butts", "j4_Billy_Butts");
		add("Bob Berberich", "Robert Berberich", "carol_0348");
		add("Bob Lennen", "Robert Lennen", "200701070036", 1);
		add("Bob Rhea", "RobertRhea", "From_Aimee_060");
		add("Bob Robinson", "Robert Robinson", "jul10d_img026");
		add("Brian Coller", "Brian Coller", "apr10_Sidney059", 1);
		add("Brooke Woolston", "Brooke Woolston", "img277_Brooke_Sara_Nov_1977");
		add("Bryan Singleton", "Bryan Singleton", "img262_Vivi_and_Family", 4);
		add("Carl Dean Johnson", "Carl Johnson", "dec09a_pic0018B", 2);
		add("Carl Greer", "Carl Greer", "Carl");
		add("Carl Prieser", "Carl Prieser", "may06_100_0161");
		add("Carol Pungello", "Carol Pungello", "img245_Carol");
		add("Carolyn O'Hara", "Carolyn OHara", "dec10_022");
		add("Casey Dertzbaugh", "Casey Torrence", "dc14_twins", 2);
		add("Catherine McNeill ONeil", "Katie McNeill", "Daniel_McNeill_and_family", 4);
		add("Charles Day", "Charles Day", "Slides_01_065");
		add("Charles McNeill", "Charles McNeill", "Daniel_McNeill_and_family", 5);
		add("Charles O'Hara", "Charles OHara", "j1_chuck");
		add("Charlie Hamilton", "Charles Hamilton", "DSCF2663", 2);
		add("Charlotte McNeill Graham", "Charlotte McNeill", "Daniel_McNeill_and_family", 13);
		add("Chip Day", "Chip Day", "A18-1762", 1);
		add("Chris Greer", "Christopher Greer", "Greer-7602");
		add("Chris Ibert", "Chris Ibert", "dec11_img024", 2);
		add("Chuckie Day", "Chuck Day", "img259_Chuckie");
		add("Claudia Day", "Claudia Day", "nov10a_img026");
		add("Colin Lynch", "Colin Lynch", "sep11ny_042", 1);
		add("Colleen Torrence", "Colleen Torrence", "apr10_Brian1", 2);
		add("Corey Astorian", "James Astorian", "2013_St_Louis_summer_104");
		add("Corey Torrence", "Corey Torrence", "A19-1860");
		add("Craig Torrence", "Craig Torrence", "A19-1825", 2);
		add("Daisy O'Hara", "Daisy OHara", "jul02_img071");
		add("Dan Ibert", "Daniel Ibert", "dec11_img024", 4);
		add("Dan Lynch", "Daniel Lynch", "img226_Dan");
		add("Dan Pungello", "Daniel Pungello", "scan08_carol_mike_2007", 5);
		add("Daniel McNeill", "Dan McNeill", "Daniel_McNeill_and_family", 3);
		add("David O'Hara", "David OHara", "A14-1323");
		add("Dot Becnel", "Dot Becnel", "C01-0012");
		add("Doug Robinson", "Douglas Robinson", "dec11_img023", 1);
		add("Douglas Johnson", "Douglas Johnson", "dec09a_pic0032C", 2);
		add("Dude Byrnes", "WilliamByrnesIII", "nov10c_img046");
		add("Dwight Singleton", "Dwight Singleton", "img262_Vivi_and_Family");
		add("Dylan Johnson Thomas", "Dylan Thomas", "dylan3_027");
		add("Dylan Lynch", "Dylan Lynch", "C25-2014", 1);
		add("Ed Woolston", "Edward Woolston", "C15-1066", 2);
		add("Elie LaVillebeuvre", "Elie de laVillebeuvre", "EliFChasFLaVillebeuvre_1");
		add("Elisa Lynch", "Elysa Lynch", "B19-1906", 2);
		add("Elizabeth Antoni", "Elizabeth Antoni 2", "g4_lady5");
		add("Elizabeth Gaudencia Antoni", "Elizabeth Antoni 1", "omi_pic08", 6);
		add("Ellen F. McNeill", "Ellen McNeill", "sep12_scan0002", 3);
		add("Ellen Smyth", "Ellen Smyth", "img127_Ellen");
		add("Emma Antoni Becker", "Emma Becker", "omi_pic08", 13);
		add("Emma O'Hara", "Emma OHara", "Park025");
		add("Erich Becker", "Erich Becker", "1990_pg074_ingrid_markus_und_Erich");
		add("Erin Greer", "Erin Greer", "Greer-7695");
		add("Erin Lynch", "Erin Lynch", "C15-1072");
		add("Ernst Antoni", "Ernst Antoni", "1990_pg072_ernst");
		add("Eugen Antoni", "Eugen Antoni", "omi_pic08", 12);
		add("Florian Becker", "Florian Becker", "1990_pg077_florian", 1);
		add("Fritz Antoni", "Friedrich Antoni", "img316_Fritz");
		add("Fronie Torrence", "Fronie Torrence", "Fronie_Mike", 1);
		add("Gabi Deschner", "Gabriella Deschner", "1990_pg069_gabi");
		add("Genevieve Day", "Genevieve Day", "may06_100_0189");
		add("Gerald Deschner", "Gerald Deschner", "germ08_147", 5);
		add("Gertrude Greer", "Gertrude Greer", "9192_pg044_gertrude_easter", 1);
		add("Gisela Johnson", "Gisela Johnson", "g4_omi1");
		add("Graham Smyth", "Graham Smyth", "Boys", 1);
		add("Hannah Greer", "Hannah Greer", "RRW422");
		add("Harold Blakeley", "Harold Blakely", "nov10c_img037");
		add("Harry McCaffrey", "Harry McCaffrey", "hmcrit", 2);
		add("Harry O'Hara I", "Harry OHara I", "Harry_OHara_I_again");
		add("Harry O'Hara II", "Harry OHara II", "Harry_II_young");
		add("Harry O'Hara III", "Harry OHara III", "A19-1818");
		add("Harry Robinson", "Harry Robinson", "dec09_scan0087C");
		add("Heather Greer", "Heather Greer", "40_heather");
		add("Henry McNeill", "Henry P McNeill", "Daniel_McNeill_and_family", 12);
		add("Hermine Antoni Kiefer", "Hermine Kiefer", "omi_pic08", 8);
		add("Hildegard Schneider", "Hildegard Schneider", "omi_pic08", 9);
		add("Holli Lynch", "Holli Lynch", "mem07_IMG_1653");
		add("Howard Lynch", "Edward Lynch", "img147_Howard");
		add("Ian Koelsch", "Ian Koelsch", "dec11_img031", 7);
		add("Ian Lynch", "Ian Lynch", "B19-1909", 4);
		add("Ingrid Becker", "Ingrid Becker", "germ08_161");
		add("Jacob Wiggins", "Jacob Wiggins", "07212013_0");
		add("James McNeill", "James McNeill", "Daniel_McNeill_and_family", 9);
		add("James Taylor", "James Taylor", "2014_Minerva_James_Taylor", 2);
		add("Jamie O'Hara", "Jamie OHara", "2008sja_IMG_0275");
		add("Jan Dertzbaugh", "Jan Dertzbaugh", "img293_Jan");
		add("Jasmin Greer", "Jasmin Greer", "g3_jasmin3");
		add("Jean Koelsch", "Jean Koelsch", "Prague08_DSC_0056");
		add("Jean Smyth", "Jean Smyth", "dec09_scan0070B");
		add("Jeanne Hyman", "Jeanne Hyman", "aunt_jeanne_blakely");
		add("Jeanne O'Hara", "Jeanne OHara", "A04-0401");
		add("Jeanne Roman de la Villebeuvre", "Jeanne de la Villebeuvr", "jeanne_roman_deLa_Villebeuvre");
		add("Jeff Robinson", "Jeffery Robinson", "dec11_img023", 2);
		add("Jim Sikes", "Jim Sikes", "IMG_20150830_151032", 2);
		add("Joe Lynch", "Joseph Lynch", "img122_Joe");
		add("Joelle Woolston", "Joelle Woolston", "img219_Joelle");
		add("Johan Antoni", "Johannes Antoni", "omi_pic08", 1);
		add("John Dertzbaugh", "John Dertzbaugh", "B08-0782", 2);
		add("John Leal", "John Leal", "mar12a_010", 2);
		add("John McNeill", "John McNeill", "Daniel_McNeill_and_family", 10);
		add("Jon Greer", "Jonathan Greer", "42_jon");
		add("Joyce McClure", "Joyce McClure", "Truman_and_Paul_and_Jimmy_and_Joyce1", 2);
		add("Judy Petrosillo", "Judy Petrosillo", "sep11ny_058", 1);
		add("Kate Lynch", "Kate Lynch", "jul07_002_kate_lynch");
		add("Kathy Torrence", "Kathleen Torrence", "mom-jun07_075", 2);
		add("Katie Lynch", "Catherine Lynch", "sep11ny_031", 2);
		add("Keeley West", "Keeley West", "2015-05-25_Keeley_and_Ann_West", 1);
		add("Keith Marcott", "Keith Marcott", "Marcott_Thanksgiving_10", 3);
		add("Kelly Robinson", "Kelly Robinson", "B06-0547");
		add("Kenneth Wayne Johnson", "Kenneth Johnson", "dec09a_pic0034B");
		add("Kevin Koelsch", "Kevin Koelsch", "mem12_pic021");
		add("Kevin Lynch", "Kevin Lynch", "sep06_100_0299", 5);
		add("Kitty Ibert", "Catherine Ibert", "dec09_scan0077A");
		add("Kristin Berberich", "Kristin Berberich", "200701070034");
		add("Lance O'Hara", "Lance OHara", "Lance_img649");
		add("Landys Peterson", "LandysPeterson", "img272_Landys_Fall_1965_2_and_half_yrs");
		add("Laura Berberich", "Laura Berberich", "pik_044");
		add("Leah Robinson", "Leah Robinson", "A15-1452");
		add("Lena O'Hara", "Lena OHara", "B23-2206");
		add("Leo Weiss", "Leo Weiss", "g4_uncle_leo_weiss");
		add("Lezin Becnel", "Lezin Becnel", "Lezin_img821");
		add("Liam Lynch", "William Lynch", "sep11ny_042", 3);
		add("Lily Greer", "Lily Greer", "jan12_lily_1");
		add("Little Pat Lynch", "Patrick Lynch", "sep11ny_050", 2);
		add("Liza Robinson", "Liz Robinson", "Tom_Mel_Bob_Liza", 4);
		add("Lori Johnson", "Lori Johnson", "g3_lori1");
		add("Louise Blakely", "Louise Hyman", "nov10c_img053", 4);
		add("Lula McClure", "Lula Taylor", "2014_Lula_Taylor-McClure");
		add("Maddy Koelsch", "Madeline Koelsch", "pik_033");
		add("Mamie O'Hara", "Mamie OHara", "OHara_311_Clinton_St_1897", 5);
		add("Margaret McNeill McClarity", "Margaret McNeill", "Daniel_McNeill_and_family", 1);
		add("Margaret Mooney McNeill", "Margaret McNeill", "Daniel_McNeill_and_family", 6);
		add("Maria Antoni Mauerer", "Maria Mauerer", "omi_pic08", 2);
		add("Marian Johnson", "Marian Johnson", "g1_marian_and_wayne_johnson", 1);
		add("Marie Hyman", "MarieHyman", "C01-0029", 2);
		add("Marie Louise Peterson", "MarieLouise", "dec09_scan0049B", 2);
		add("Marilyn Woolston", "Marilyn Woolston", "dec09_scan0086F");
		add("Markus Becker", "Markus Becker", "1990_pg074_ingrid_markus_und_Erich", 3);
		add("Marla Lynch", "Marla Lynch", "lilac055", 1);
		add("Mary Anne Robinson", "Mary Anne Robinson", "apr12_DSC00801", 1);
		add("Mary Coxey Taylor", "Mary Coxey", "2014_Mary_Coxey_Taylor");
		add("Mary E. O'Hara", "Mary OHara", "matt_gramma");
		add("Mary Kate", "Mary Kate Lennen", "200701070035", 1);
		add("Mary McNeill", "Mary McNeill", "Daniel_McNeill_and_family", 11);
		add("Mary Torrence", "Mary Cunningham Torren", "Mary_and_who_and_who");
		add("Mary Torrence", "Mary OHara Torrence", "B13-1315");
		add("Matt Lynch", "Matthew Lynch", "dec09_scan0089A");
		add("Matt O'Hara", "Matthew OHara", "img225_Matt");
		add("Matt Robinson", "Matthew Robinson", "img165_kid");
		add("Mattie Johnson", "Mattie Johnson", "dec09a_pic0032C", 1);
		add("Max Lynch", "Maxwell Lynch", "scan08_joe_lynchs_2007", 1);
		add("McKenzie Lynch", "McKenzie Lynch", "C25-2014", 2);
		add("ME Koelsch", "Mary Koelsch", "mem07_100_0733", 4);
		add("Megan Coller", "Megan Coller", "mem07_100_0922", 1);
		add("Melanie Robinson", "Melonie Robinson", "1972-07-02_Melanie_Mulroy_Robinson_2");
		add("Meta Johnson", "Meta Johnson", "g1_grandma_meta_johnson");
		add("Mike Lynch", "Michael Lynch", "img172_Mike");
		add("Mike O'Hara", "Michael OHara Jr", "OHara_311_Clinton_St_1897", 2);
		add("Mike Pungello", "Michael Pungello", "mem07_100_0935", 1);
		add("Mike Torrence", "Mike Torrence", "img078_Torrence");
		add("Mimi Byrnes", "Aimee Butts", "j4_Mimi");
		add("Minerva Lillard", "Minerva Lillard", "2014_Minerva_James_Taylor", 1);
		add("Mitch Day", "Mitchell Day", "C25-2015", 4);
		add("Ninon Balch", "Grace Ninon Balch", "nov10c_img444");
		add("Nora O'Hara McCaffrey", "Nora OHara", "oct11_img532", 2);
		add("Oliver Lynch", "Oliver Lynch", "Little_Pat_and_kids", 1);
		add("Olya O'Hara", "Olga Lebret", "2012-01-03_Texas_Longhorn_02");
		add("Pat Lynch", "Patrick Anton Lynch", "img113_Pat_Lynch");
		add("Patrick McNeill Sr", "Patrick McNeill", "Daniel_McNeill_and_family", 8);
		add("Patrick McNeill", "Patrick McNeill", "Patrick_McNeill", 3);
		add("Patty O'Hara", "Patricia OHara", "B14-1429");
		add("Paul Ibert", "Paul Ibert", "dec11_img024", 1);
		add("Paul McClure", "Paul McClure", "Truman_and_Paul_and_Jimmy_and_Joyce1", 1);
		add("Paula Antoni", "Paula Antoni", "g4_lady3");
		add("Peggy Berberich", "Margaret Berberich", "B16-1702");
		add("Pete Day", "Peter Day", "C25-2015", 1);
		add("Pete Lynch", "Peter Lynch", "img121_Pete");
		add("Pete Peterson", "PetePeterson", "dec09_scan0049B", 4);
		add("Peter Marcott", "Peter Marcott", "pik_040", 1);
		add("Phyl Becnel", "Phyllis", "nov10c_img047");
		add("Posy Lynch", "Posy Lynch", "apr10_Brian4", 1);
		add("Powell Rhea", "Powell Rhea", "family1925", 2);
		add("Quentin Scobel", "Quentin Scobel", "MattQuentinMike", 2);
		add("Rachel Lynch", "RachaelLynch", "apr10_Brian1", 1);
		add("Ralph West", "Ralph West", "1969-10-02_Captain_West");
		add("Rebecca Marcott", "Rebecca Marcott", "dec09_scan0071F");
		add("Rhea Peterson", "RheaPeterson", "Andy_Rhea_Steve_Hayes", 1);
		add("Richard Johnson", "Richard Johnson", "g4_richard_korea");
		add("Richie Rhea", "Rich Rhea", "IMG_20150228_184357", 1);
		add("Rita Berberich", "Rita Berberich", "img107_Rita");
		add("Rob Pungello", "Robert Pungello", "dec09_scan0084D");
		add("Robbie Robinson", "Robbie Robinson", "A17-1587");
		add("Robert Wiggins", "Robert Wiggins", "RRW033", 1);
		add("Robin Wiggins", "Robin OHara", "mar12a_002");
		add("Romeo Casso", "Romeo Casso", "B10-1021");
		add("Rory Lynch", "Rory Lynch", "sep11ny_038", 1);
		add("Rosa Antoni", "Rosa Antoni", "rosa_bernhard", 2);
		add("Rose Casso-O'Hara", "Rosie Casso OHara", "Rose_Fall_08");
		add("Ruby Babb", "Ruby Babb", "McClure_Sisters", 1);
		add("Ruth Dyer", "Ruth Dyer", "SkipAGeneration", 9);
		add("Sabina Deschner", "Sabina Deschner", "1990_pg068_gerald_gabi_stefan_sabina_sonya", 4);
		add("Sal Robinson", "Sarah Robinson", "A02-0178");
		add("Sally Conner White", "Sarah Conner", "hmcrit", 1);
		add("Sam Day", "Samuel Day", "C25-2015", 2);
		add("Samuel Taylor", "Samuel Taylor", "2014_Samuel_H_Taylor");
		add("Sarah Burkat", "Sarah Burkat", "apr12_DSC00850", 1);
		add("Sarah Conner", "Sarah Conner", "oct11_img532", 3);
		add("Sarah Lynch", "Sarah Lynch", "apr10_Brian3", 2);
		add("Sarah Quinn O'Hara", "Sarah OHara", "oct11_img533");
		add("Sarah Torrence", "Sarah Torrence", "Sarah_Mary", 1);
		add("Sean Berberich", "Sean Berberich", "mem07_100_0733", 8);
		add("Sean O'Hara", "Sean OHara", "jan11_001");
		add("Sean Singleton", "Sean Singleton", "img262_Vivi_and_Family", 3);
		add("Shane O'Hara", "Shane OHara", "A14-1349");
		add("Shane O'Hara", "Shane OHara", "IMG_20140816_132652", 2);	// Duplicate for Shane
		add("Sonya Deschner", "Sonya Deschner", "1990_pg071_sonya");
		add("Stefan Deschner", "Stefan Deschner", "1990_pg068_gerald_gabi_stefan_sabina_sonya", 3);
		add("Stephanie Berberich", "Stephanie Berberich", "200701070033", 3);
		add("Stephen Berberich", "Stephen Berberich", "aug11_279", 1);
		add("Steve O'Hara", "Steven OHara", "jul02_img111");
		add("Susan Lynch", "Susan Lynch", "apr10_Brian2");
		add("Susie Hamilton", "Susan Hamilton", "mem07_100_0863", 2);
		add("Tante Luise", "Luise Antoni", "g1_hildegard_and_louise");
		add("Taylor Gibson McClure", "Taylor McClure", "McClure_Boys");
		add("Thomas Hyman", "Thomas Hyman", "ThomasMcCabeHymanetal", 1);
		add("Tim Dertzbaugh", "Timothy Dertzbaugh", "dc15_tim", 2);
		add("Tim Lynch", "Tim Lynch", "img149_Tim");
		add("Tim Lynch", "Timothy Lynch", "scan08_joe_lynchs_2007", 3);
		add("TJ Lynch", "TJ Lynch", "sep11ny_042", 2);
		add("Tom Burkat", "Tom Burkat", "apr12_DSC00803", 1);
		add("Tom Koelsch", "Thomas Koelsch", "9192_pg010_tom_bean_2", 1);
		add("Tom Petrosillo", "Thomas Petrosillo", "sep11ny_040", 3);
		add("Tom Rhea", "ThomasRhea", "Rhea_Tom_and_Marie_Louise", 1);
		add("Tom Robinson", "Thomas Robinson", "img175_Tom");
		add("Tommy Day", "Thomas Day", "Tommy1947");
		add("Tony Day", "Tony Day", "C25-2015", 3);
		add("Tracy Lynch", "Tracy Lynch", "10_lady4");
		add("Trish Burkat", "Patricia Robinson", "C15-1073", 1);
		add("Vera Johnson", "Vera Johnson", "g3_vera_dumas_texas");
		add("Vinny Lynch", "Vincent Lynch", "j3_Vinnie");
		add("Vivi Singleton", "Mary Singleton", "img157_Vivi_1957");
		add("Wayne Johnson", "Wayne Johnson", "g1_marian_and_wayne_johnson", 2);
		add("Will Torrence", "Will Torrence", "Regatta2015-194");
		add("William Byrnes IV", "William Byrnes IV", "B03-0264");
		add("Xavi Casso-O'Hara", "Xavier Casso OHara", "C14-1030");
		
		noTree("Al Munro", "Al", 1);
		noTree("Aleftina Nam", "May2008_mom_039");
		noTree("Alex Ramirez", "ma_ca_P5070007", 1);
		noTree("Alex Tagge", "99-00_pg019_john_and_alex");
		noTree("Alexandra Robinson", "apr12_DSC00839", 2);
		noTree("Alyce Finder", "96-97_pg05_unknown_family1", 3);
		noTree("Amanda", "Amanda_14A");
		noTree("Amanda", "aug11_294", 1);
		noTree("Anatoly Nam", "2006-06-04_Dinner_33");
		noTree("Andrew Jackson", "j4_Jackson_maybe");
		noTree("Andrew McAllister", "dec08_002");
		noTree("Andrew Staley", "Portrait7_Andrew_Staley_img130");
		noTree("Arliss Holland", "RRW327");
		noTree("Aunt Mary Cummings", "dec09_scan0056B");
		noTree("Betti Bunce", "9192_pg005_bunce_maysonave_simchuk_riedesel", 1);
		noTree("Bill Semler", "bill_semler");
		noTree("Boyd Hutton", "nyc10_Empire_239");
		noTree("Brian Wiggins", "RRW489", 2);
		noTree("Brother Scott", "RobnRob_0192", 1);
		noTree("Bruce Ferguson", "p9802_img001");
		noTree("Bruce McVay", "2000-07_Winter_Park_004", 4);
		noTree("Candy Datko", "13_candy_datko");
		noTree("Carolyn Rivera", "9495_pg083_coach_cici_rivera");
		noTree("Cecil Cannon", "mom_dad_and_cheryl_batchelor_and_cecil_cannon", 2);
		noTree("Chang Kyu Kim", "9091_pg053_chang_kyu_japan");
		noTree("Charles La Villebeuvre", "Charles_H_La_Villebeuvre_1");
		noTree("Charlie Sanchez", "00-01_pg7_xc1");
		noTree("Cheryl Batchelor", "mom_dad_and_cheryl_batchelor_and_cecil_cannon", 1);
		noTree("Cheryl McVay", "2000-07_Winter_Park_004", 3);
		noTree("Chris Ebaugh", "chris_ebaugh");
		noTree("Chris Kniffin", "Chris_-_Valentine_Dance");
		noTree("CJ Wiggins", "RRW335");
		noTree("Coach Jackson", "aug08_142", 2);
		noTree("Colonel Terry", "dad_award_col_terry_roger_camp", 1);
		noTree("Craig Trupo", "aug09_135", 2);
		noTree("Crystal Straseske", "RRW124", 2);
		noTree("Cyndi Klein", "RRW100", 1);
		noTree("Dan Diamond", "Mike_Mary_Dan_Diamond", 3);
		noTree("Dan Simchuk", "9192_pg005_bunce_maysonave_simchuk_riedesel", 4);
		noTree("Danny Provost", "Portrait3_Robin_missing_img043", 1);
		noTree("Dave Aronowsky", "30_shane_robin_and_people", 3);
		noTree("Deb Robertson", "trip_photo_1_reduced");
		noTree("Denise Staley", "g4_mom_and_denise", 2);
		noTree("Dennis Nam", "98-99_pg099_dennis_and_pat", 2);
		noTree("Diane Rabb", "PC080053", 1);
		noTree("Dick Rogers", "aug11_213", 2);
		noTree("Don Oxkey", "dad_and_tatung", 2);
		noTree("Donna Lengefeld", "sep09_010", 1);
		noTree("Doug McCombs", "04_Dick_and_Team", 6);
		noTree("Dub Wells", "04_Dick_and_Team", 2);
		noTree("Dustin Borden", "mar12b_img013", 2);
		noTree("Dusty", "9596_pg034_wendell_gary_unknown_provost", 2);
		noTree("Ed Heugel", "daisy_talking_to_a_guy", 2);
		noTree("Eli Chavez", "nyc10_Empire_101");
		noTree("Eliana Munkeby", "08172013623", 3);
		noTree("Ellen Moss", "2001_Fran_and_Ellen_Moss", 1);
		noTree("Eric Gomez", "Ice_Bats_-_Hungry_Kids", 1);
		noTree("Erin Hardy", "Erin_Hardy");
		noTree("Ethan Munkeby", "2008SJ_IMG_2140");
		noTree("Father O'Keefe", "D16-1218", 1);
		noTree("Federico Aguinaga", "2011v__021", 4);
		noTree("Fran Moss", "Fran_Tom_Moss", 1);
		noTree("Frau Bruner", "95ger_p113a", 2);
		noTree("Fred Soland", "lady_and_guy", 1);
		noTree("Ganesh Iyer", "200611140047", 2);
		noTree("Gary Fox", "gary_fox", 3);
		noTree("Gary Hartman", "RRW556", 3);
		noTree("Gene Landry", "scan_2010_10_img028");
		noTree("Gerard Neele", "sep09_123");
		noTree("Ginger Kelly", "Ginger_Jasmin_who", 1);
		noTree("Gino Cedillo", "04262011039", 2);
		noTree("Greg Vondrak", "Greer-8241");
		noTree("Ian McVay", "mar12b_img013", 1);
		noTree("Ingrid Linden", "B10-1031", 2);
		noTree("Isaac Colley", "sj_wedding_247");
		noTree("Jacob Menardus", "p9802_img044", 2);
		noTree("Jan Jackson", "2000-07_Winter_Park_004", 6);
		noTree("Jane Aronowski", "9192_pg063_jane_aronowsky_2");
		noTree("Janice Buchhorn", "g4_unknown-1", 1);
		noTree("Jason", "IMG_20141227_202501", 1);
		noTree("Jeff Butler", "04262011039", 3);
		noTree("Jeff Thomas", "sep05_jeff_dylan", 1);
		noTree("Jennifer Wiggins", "RRW068", 2);
		noTree("Jerry", "jun08_102", 1);
		noTree("Jessica Ho", "2008msia_093");
		noTree("Jim Williams", "dad_jim_williams", 2);
		noTree("Joachim Linden", "Kerstin_Joachim_Annika", 2);
		noTree("Joan Aiken", "img098_Lady");
		noTree("Joe Brown", "9091_pg076_joe_brown_japan");
		noTree("Joe Moss", "nov10c_img050", 2);
		noTree("Joey Caruso", "2011v__038");
		noTree("Joey Koepke", "1999-10-16_img027", 3);
		noTree("John Hu", "P5070238");
		noTree("Johnny Beserra", "RRW289");
		noTree("Johnny Weissmuller", "04_Dick_and_Team", 3);
		noTree("jon fisher", "p0102_img068", 2);
		noTree("Josh Luke", "2011v__173", 3);
		noTree("Josh Munkeby", "SJ2008_mom_127");
		noTree("Juan Longoria", "2011v__119", 1);
		noTree("Justin Pittinger", "sj_wedding_209", 1);
		noTree("Kara Rubeo", "RRW522", 1);
		noTree("Kathy Garcia", "jul05_019");
		noTree("Ken Holland", "RRW355", 3);
		noTree("Kerstin Linden", "Kerstin_Joachim_Annika", 1);
		noTree("Kevin DeViney", "2011v__187", 1);
		noTree("Kevin Gerkins", "99-00_pg019_kevin");
		noTree("Kevin Rood", "100_3641");
		noTree("Kim Kui", "P5080246", 5);
		noTree("Kristina Beserra", "RRW287", 2);
		noTree("Kurt Lutz", "nov10f_PB290093", 2);
		noTree("Laura Bush", "p83_daisy_laura_bush_pat_stan_smtih", 2);
		noTree("Laurel Betz", "04_Dick_and_Team", 1);
		noTree("Laurie Wells", "sep09_117");
		noTree("Lee Meyers", "daisy_and_guy", 2);
		noTree("Leesa Ferrero", "Leesa_Ferrero");
		noTree("Lori Provost", "9596_pg035_kevin_rood_lori_provost_donna_lengefeld", 1);
		noTree("Lou Datko", "candy_lou_datko", 1);
		noTree("Maggie", "ChristmasMere021", 2);
		noTree("Marge", "ChristmasMere021", 1);
		noTree("Marilyn Dobson", "sj_wedding_180", 2);
		noTree("Mark Hoernis", "9596_pg034_pam_mark_hoernis_sidney_steve_hiser", 2);
		noTree("Mary Anne Harding", "p9802_img017");
		noTree("Mary Diamond", "Mary_Mike_Diamond", 1);
		noTree("Mary McNeill", "Daniel_McNeill_and_family", 2);
		noTree("Matt Gieringer", "1999-10-06_img033", 1);
		noTree("Matt Poggemiller", "p64_shane_and_matt_poggemiller", 2);
		noTree("Megan Staley", "Portrait7_Megan_Staley_img135");
		noTree("Michael Belton", "2010-08-08_Seans_Baptism_10", 2);
		noTree("Michael Quinn", "waterskiing_440", 2);
		noTree("Michael Thew", "04_Dick_and_Team", 4);
		noTree("Michel Munkeby", "sj_wedding_364");
		noTree("Mike Diamond", "B14-1394");
		noTree("Mike Staley", "Portrait7_Staleys_img128", 3);
		noTree("Monica Harmon", "IMG_0290");
		noTree("Monica Lynch", "apr10_Sidney070", 3);
		noTree("Nancy Pennypacker", "Gang_1", 4);
		noTree("Nastia Nam", "dec09d_PC310049");
		noTree("Nathan Ehlinger", "scan_2010_10_img004", 3);
		noTree("Nicole DiDaniele", "may06_005");
		noTree("Nina", "jun12a_015", 2);
		noTree("Pam Hoernis", "9596_pg034_pam_mark_hoernis_sidney_steve_hiser", 1);
		noTree("Patsy Morgan", "dec10_017", 1);
		noTree("Patty Ann Boocher", "RRW033", 2);
		noTree("Peter Wynyard", "brown_018");
		noTree("Phil Finder Jr", "9192_pg016_phil_finder");
		noTree("Phil Finder", "96-97_pg05_unknown_family1", 4);
		noTree("Rachel Thurner", "28_pat_and_rachel", 2);
		noTree("Rebecca Cabrera", "RRW094", 1);
		noTree("Resa", "dec09b_pic0009D");
		noTree("Rich Mendoza", "steve_and_two_guys", 3);
		noTree("Roberta Holleway Swischuk", "Nov05_t006", 2);
		noTree("Robin Curle", "dad_and_friend");
		noTree("Roger Camp", "dad_award_col_terry_roger_camp", 3);
		noTree("Roger Senn", "05302012372", 2);
		noTree("Ron Paul", "Ron_Paul_4");
		noTree("Ron Riedesel", "9192_pg005_bunce_maysonave_simchuk_riedesel", 5);
		noTree("Ryan Callaway", "1999-10-09_img037");
		noTree("Sam Goodrich", "6pocket_sam_goodrich");
		noTree("Sam Uva", "A17-1620");
		noTree("Sarah Pittinger", "SJ2008_P5300040", 2);
		noTree("Scott Wiggins", "RRW357");
		noTree("Shad Heath", "may10b_080", 2);
		noTree("Sharrah Gibbs", "dec09b_pic0000I", 1);
		noTree("Shelby", "oct13a_013", 1);
		noTree("Shyam Aramkuni", "HYD-Thanks1", 2);
		noTree("Shylesh Ramanjini", "200611190236");
		noTree("Sidney Hiser", "9596_pg034_pam_mark_hoernis_sidney_steve_hiser", 3);
		noTree("Siew Meng Lai", "P5080246", 6);
		noTree("Stan Smith", "p83_daisy_laura_bush_pat_stan_smtih", 3);
		noTree("Steve Hiser", "9596_pg034_pam_mark_hoernis_sidney_steve_hiser", 4);
		noTree("Steve Maysonave", "japan15", 1);
		noTree("Steven Munkeby", "sja_DCP_2003", 1);
		noTree("Tammye Custer", "tammy_at_wedding");
		noTree("Tim Williams", "A21-2155");
		noTree("Tom Bylander", "tom-with-droids");
		noTree("Tom Moss", "tom_moss");
		noTree("Tom Provost", "RRW115", 5);
		noTree("Trey Fischer", "D26-2144", 1);
		noTree("Vasanth Coorg", "9596_pg019_vasanth");
		noTree("Vedika", "HYD-Thanks1", 1);
		noTree("Wendell Lengefeld", "RRW115", 3);
		noTree("Yteve Reinhard", "B04-0353", 2);	}
}

/*
From running PicasaReader.bat:

From tree/check.php:
	Checking Faces column Photo 
	Checking Faces column Person 
*/