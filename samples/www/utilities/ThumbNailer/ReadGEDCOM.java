// Original author: Steven A. O'Hara, Sep 15, 2013

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class ReadGEDCOM {
	private enum SHEETS { GPERSON, GCHILD, GMARRIAGE, SPERSON, SMARRIAGE };
	private WriteXL writer;
	
	private String[] MONTHS = new String[] {
			"Jan", "Feb", "Mar", "Apr", "May", "Jun",
			"Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
			};

	private HashMap<String, GEDPerson> individualCodes = new HashMap<String, GEDPerson>();

	private ArrayList<String> queue = new ArrayList<String>();
	
	private int _idCount = 0;
	private int _families = 0;
	
	private class GEDPerson {
		public String _id;
		public String _name;
		public String _famc;
		public String _www;
		public String _sex;
		public String _givn;
		public String _pgvn;
		public String _midn;
		public String _surn;
		public String _marn;
		public String _birtDate;
		public String _birtPlac;
		public String _deatDate;
		public String _deatPlac;
		
		public GEDPerson(String... values) {
			int col = 0;
			_id = values[col++];
			_name = values[col++];
			_famc = values[col++];
			_www = values[col++];
			_sex = values[col++];
			_givn = values[col++];
			_pgvn = values[col++];
			_midn = values[col++];
			_surn = values[col++];
			_marn = values[col++];
			_birtDate = values[col++];
			_birtPlac = values[col++];
			_deatDate = values[col++];
			_deatPlac = values[col++];
			
			if (_surn.equals("Unknown")) {
				_surn = _marn;
				_marn = "";
			}
		}
		
		public GEDPerson(SQLPerson sp) {
			_idCount++;
			_id = "@I" + _idCount + "@";
			_sex = sp._sex;
			//sp._wikiTree = _id;
			
			_givn = sp._first;
			_midn = sp._middle;
			_pgvn = sp._nickName;
			if (sp._maiden.equals("")) {
				_surn = sp._last;
				_marn = "";
				_name = sp._first + " " + sp._middle + " /" + sp._last + "/";
			} else {
				_surn = sp._maiden;
				_marn = sp._last;
				_name = sp._first + " " + sp._middle + " /" + sp._maiden + "/";
			}
			
			if (sp._monBorn == 0) {
				_birtDate = "ABT " + sp._yrBorn;
			} else if (sp._dayBorn == 0) {
				_birtDate = "ABT " + MONTHS[sp._monBorn-1] + " " + sp._yrBorn;
			} else {
				_birtDate = sp._dayBorn + " " + MONTHS[sp._monBorn-1] + " " + sp._yrBorn;
			}
			_birtPlac = sp._whereBorn;
			
			if (sp._yrDied > 0) {
				if (sp._monDied == 0) {
					_deatDate = "ABT " + sp._yrDied;
				} else if (sp._dayDied == 0) {
					_deatDate = "ABT " + MONTHS[sp._monDied-1] + " " + sp._yrDied;
				} else {
					_deatDate = sp._dayDied + " " + MONTHS[sp._monDied-1] + " " + sp._yrDied;
				}
			}
			_deatPlac = sp._whereBuried;
		}
		
		public String genKey() {
			String name = _givn + (_surn.equals("") ? _marn : _surn);
			return name.replaceAll(" ", "").replaceAll("'", "");
		}
		
		public void printGED(PrintWriter pw, boolean doFamily) {
			pw.println("0 " + _id + " INDI");
			
			_families++;
			_famc = "@F" + _families + "@";
			pw.println("1 FAMS " + _famc );
			
			pw.println("1 NAME " + _name);
			pw.println("2 GIVN " + _givn);
			if (!_midn.equals("")) pw.println("2 _MIDN " + _midn);
			if (!_marn.equals("")) pw.println("2 _MARN " + _marn);
			pw.println("2 SURN " + _surn);

			pw.println("1 SEX " + _sex);

			pw.println("1 BIRT");
			pw.println("2 DATE " + _birtDate);
			if (!_birtPlac.equals("")) pw.println("2 PLAC " + _birtPlac);
			
			if (_deatDate != null && (!_deatDate.equals("") || !_deatPlac.equals(""))) {
				pw.println("1 DEAT");
				if (!_deatDate.equals("")) pw.println("2 DATE " + _deatDate);
				if (!_deatPlac.equals("")) pw.println("2 PLAC " + _deatPlac);
			}
		}
	}
	private ArrayList<GEDPerson> GEDPeople = new ArrayList<GEDPerson>();
	
	private class GEDChild {
		//public String _id;
		public String _husb;
		public String _wife;
		public String _chil;
		
		public GEDChild(String id, String husb, String wife, String chil) {
			//_id = id;
			_husb = husb;
			_wife = wife;
			_chil = chil;
		}
	}
	private ArrayList<GEDChild> GEDChildren = new ArrayList<GEDChild>();
	
	private class GEDMarriage {
		//public String _id;
		public String _husb;
		public String _wife;
		public String _marrDate;
		public String _marrPlac;
		
		public GEDMarriage(String id, String husb, String wife, String marrDate, String marrPlac) {
			//_id = id;
			_husb = husb;
			_wife = wife;
			_marrDate = marrDate;
			_marrPlac = marrPlac;
		}
	}
	private ArrayList<GEDMarriage> GEDMarriages = new ArrayList<GEDMarriage>();
	
	private void doGED(String nameGEDCOM) throws Exception {
		int gperson = writer.createSheet(SHEETS.GPERSON.ordinal(), "GED People", "Id", "NAME", "FAMC", "WWW", "SEX",
				"GIVN", "PGVN", "MIDN", "SURN", "MARN",
				"BIRT DATE", "BIRT PLAC", "DEAT DATE", "DEAT PLAC");
		int gchild = writer.createSheet(SHEETS.GCHILD.ordinal(), "GED Children", "Id",
				"HUSB", "HUSB NAME", "WIFE", "WIFE NAME", "CHIL", "CHIL NAME");
		int gmarriage = writer.createSheet(SHEETS.GMARRIAGE.ordinal(), "GED Marriages", "Id",
				"HUSB", "HUSB NAME", "WIFE", "WIFE NAME", "MARR DATE", "MARR PLAC");

		// Start reading the GEDCOM file
		BufferedReader br = new BufferedReader(new FileReader(nameGEDCOM));
		String rec = "";
		String indi = "";
		String sex = "";
		String www = "";
		String famc = "";
		
		String name = "";
		String givnName = "";
		String surnName = "";
		String pgvnName = "";
		String midnName = "";
		String marnName = "";
		
		boolean inBirt = false;
		boolean inDeat = false;
		String birtDate = "";
		String birtPlac = "";
		String deatDate = "";
		String deatPlac = "";
		
		boolean inFamily = false;
		boolean inIndividual = false;
		
		String fam = "";
		String husb = "";
		String wife = "";
		boolean inMarr = false;
		String marrDate = "";
		String marrPlac = "";
		boolean done = true;
		
		while ((rec = br.readLine()) != null) {
			if (rec.startsWith("0 ")) {
				if (inFamily && inMarr) {
					writer.addRow(gmarriage, fam, husb, findName(husb),
							wife, findName(wife), marrDate, marrPlac);
					GEDMarriages.add(new GEDMarriage(fam, husb, wife, marrDate, marrPlac));
				}
				
				inFamily = false;
				inIndividual = false;

				if (rec.endsWith(" INDI")) {
					done = false;
					indi = rec.split(" ")[1];
					inIndividual = true;
					sex = "";
					www = "";
					famc = "";
					
					givnName = "";
					surnName = "";
					pgvnName = "";
					midnName = "";
					marnName = "";
					
					inBirt = false;
					inDeat = false;
					birtDate = "";
					birtPlac = "";
					deatDate = "";
					deatPlac = "";
				} else if (rec.endsWith(" FAM")) {
					fam = rec.split(" ")[1];
					inFamily = true;
					
					husb = "";
					wife = "";
					inMarr = false;
					marrDate = "";
					marrPlac = "";
				}
			} else if (inFamily) {
				if (rec.startsWith("1 MARR")) {
					inMarr = true;
				} else if (rec.startsWith("1 HUSB ")) {
					husb = rec.substring("1 HUSB ".length()).trim();
				} else if (rec.startsWith("1 WIFE ")) {
					wife = rec.substring("1 WIFE ".length()).trim();
				} else if (rec.startsWith("2 DATE ")) {
					if (inMarr) marrDate = rec.substring("2 DATE ".length()).trim();
				} else if (rec.startsWith("2 PLAC ")) {
					if (inMarr) marrPlac = rec.substring("2 PLAC ".length()).trim();		
				} else if (rec.startsWith("1 CHIL ")) {
					String chil = rec.substring("1 CHIL ".length()).trim();
					writer.addRow(gchild, fam, husb, findName(husb),
							wife, findName(wife), chil, findName(chil));
					GEDChildren.add(new GEDChild(fam, husb, wife, chil));
				}
			} else if (inIndividual) {
				// Regular processing
				if (rec.startsWith("1 NAME ")) {
					name = rec.substring("1 NAME ".length()).trim();
				} else if (rec.startsWith("1 SEX ")) {
					sex = rec.substring("1 SEX ".length()).trim();
				} else if (rec.startsWith("1 WWW ")) {
					www = rec.substring(rec.lastIndexOf('/')+1).trim();
				} else if (rec.startsWith("1 FAMC ")) {
					famc = rec.substring("1 FAMC ".length()).trim();
					
				} else if (rec.startsWith("2 GIVN ")) {
					givnName = rec.substring("2 GIVN ".length()).trim();
				} else if (rec.startsWith("2 _PGVN ")) {
					pgvnName = rec.substring("2 _PGVN ".length()).trim();
				} else if (rec.startsWith("2 _MIDN ")) {
					midnName = rec.substring("2 _MIDN ".length()).trim();
				} else if (rec.startsWith("2 _MARN ")) {
					marnName = rec.substring("2 _MARN ".length()).trim();
				} else if (rec.startsWith("2 SURN ")) {
					surnName = rec.substring("2 SURN ".length()).trim();
					
				} else if (rec.startsWith("1 BIRT")) {
					inBirt = true;
					inDeat = false;
				} else if (rec.startsWith("1 DEAT")) {
					inBirt = false;
					inDeat = true;
				} else if (rec.startsWith("2 DATE ")) {
					if (inBirt) birtDate = rec.substring("2 DATE ".length()).trim();
					if (inDeat) deatDate = rec.substring("2 DATE ".length()).trim();
				} else if (rec.startsWith("2 PLAC ")) {
					if (inBirt) birtPlac = rec.substring("2 PLAC ".length()).trim();
					if (inDeat) deatPlac = rec.substring("2 PLAC ".length()).trim();

				} else if (rec.startsWith("2 TYPE wikitree.") && !done) {
					writer.addRow(gperson, indi, name, famc, www, sex,
							givnName, pgvnName, midnName, surnName, marnName,
							birtDate, birtPlac, deatDate, deatPlac);
					GEDPerson p = new GEDPerson(indi, name, famc, www, sex,
							givnName, pgvnName, midnName, surnName, marnName,
							birtDate, birtPlac, deatDate, deatPlac);
					GEDPeople.add(p);
					individualCodes.put(indi, p);
					done = true;
				}
			}
		}
		
		br.close();
	}

	private class SQLPerson {
		public String _GED_families;
		
		public String _key;
		public String _first;
		public String _middle;
		public String _maiden;
		public String _last;
		//public String _jr;
		//public String _title;
		public String _nickName;
		public String _sex;
		public int _monBorn;
		public int _dayBorn;
		public int _yrBorn;
		public String _whereBorn;
		public boolean _alive;
		public int _monDied;
		public int _dayDied;
		public int _yrDied;
		public String _whereBuried;
		public String _motherKey;
		public String _fatherKey;
		public String _wikiTree;
		
		public SQLPerson(String[] values) {
			int col = 0;
			_key = values[col++];
			_first = values[col++];
			_middle = values[col++];
			_maiden = values[col++];
			_last = values[col++];
			col++; //_jr = values[col++];
			col++; //_title = values[col++];
			_nickName = values[col++];
			_sex = values[col++];
			_monBorn = Integer.parseInt(values[col++]);
			_dayBorn = Integer.parseInt(values[col++]);
			_yrBorn = Integer.parseInt(values[col++]);
			_whereBorn = values[col++];
			_alive = values[col++].equals("Y");
			_monDied = Integer.parseInt(values[col++]);
			_dayDied = Integer.parseInt(values[col++]);
			_yrDied = Integer.parseInt(values[col++]);
			_whereBuried = values[col++];
			_motherKey = values[col++];
			_fatherKey = values[col++];
			_wikiTree = values[col++];
		}
		
		public SQLPerson(GEDPerson gp) {
			_key = gp.genKey();
			_sex = gp._sex;
			_wikiTree = gp._www;
			
			_first = gp._givn;
			_middle = gp._midn;
			if (gp._marn.equals("")) {
				_maiden = "";
				_last = gp._surn;
			} else {
				_last = gp._marn;
				_maiden = gp._surn;
			}
			_nickName = gp._pgvn;
			
			_monBorn = getMonth(gp._birtDate);
			_dayBorn = getDay(gp._birtDate);
			_yrBorn = getYear(gp._birtDate);
			_whereBorn = gp._birtPlac;
			
			_monDied = getMonth(gp._deatDate);
			_dayDied = getDay(gp._deatDate);
			_yrDied = getYear(gp._deatDate);
			_whereBuried = gp._deatPlac;
		}
		
		public void printSQL(PrintWriter pw) {
			StringBuffer sb = new StringBuffer();
			sb.append("INSERT People SET");
			sb.append(" KeyName = '" + _key + "'");
			sb.append(", First = '" + _first + "'");
			sb.append(", Middle = '" + _middle + "'");
			sb.append(", Maiden = '" + _maiden + "'");
			sb.append(", Last = '" + _last + "'");
			sb.append(", Nickname = '" + _nickName + "'");
			sb.append(", Sex = '" + _sex + "'");
			sb.append(", MonBorn = " + _monBorn);
			sb.append(", DayBorn = " + _dayBorn);
			sb.append(", YrBorn = " + _yrBorn);
			sb.append(", WhereBorn = '" + _whereBorn + "'");
			sb.append(", Alive = '" + (_alive ? "Y" : "N") + "'");
			sb.append(", MonDied = " + _monDied);
			sb.append(", DayDied = " + _dayDied);
			sb.append(", YrDied = " + _yrDied);
			sb.append(", WhereBuried = '" + _whereBuried + "'");
			sb.append(", MotherKey = ''");
			sb.append(", FatherKey = ''");
			sb.append(", WikiTree = '" + _wikiTree + "'");
			
			sb.append(";");
			pw.println(sb.toString());
		}

		public boolean hasSpouse() {
			for (SQLMarriage sm : SQLMarriages) {
				if (_key.equals(sm._wifeKey) || _key.equals(sm._husbandKey)) return true;
			}
			return false;
		}
		
		public boolean hasChildren() {
			for (SQLPerson sp : SQLPeople) {
				if (_key.equals(sp._motherKey) || _key.equals(sp._fatherKey)) return true;
			}
			return false;
		}
	}
	
	private int getMonth(String dateGED) {
		String[] pieces = dateGED.split(" ");
		if (pieces.length < 2) return 0;
		String mon = pieces[pieces.length-2];
		for (int m = 0; m < 12; m++) {
			if (mon.equals(MONTHS[m])) return m+1;
		}
		if (mon.equals("ABT")) return 0;
		if (mon.equals("AFT")) return 0;
		throw new RuntimeException("Invalid month: " + dateGED);
	}
	
	private int getDay(String dateGED) {
		if (dateGED.length() == 0) return 0;
		String[] pieces = dateGED.split(" ");
		if (pieces.length < 3) return 0;
		String day = pieces[pieces.length-3];
		if (day.equals("ABT")) return 0;
		return Integer.parseInt(day);
	}
	
	private int getYear(String dateGED) {
		if (dateGED.length() == 0) return 0;
		String[] pieces = dateGED.split(" ");
		if (pieces.length == 0) return 0;
		return Integer.parseInt(pieces[pieces.length-1]);
	}
	
	private String findName(String id) {
		if (id.equals("")) return "";
		GEDPerson p = individualCodes.get(id);
		if (p == null) return "";
		return p._name;
	}
	
	private ArrayList<SQLPerson> SQLPeople = new ArrayList<SQLPerson>();
	
	private class SQLMarriage {
		public String _husbandKey;
		public String _wifeKey;
		public int _monWed;
		public int _dayWed;
		public int _yrWed;
		public String _where;
		//public String _divorced;
		
		public SQLMarriage(String[] values) {
			int col = 0;
			_husbandKey = values[col++];
			_wifeKey = values[col++];
			_monWed = Integer.parseInt(values[col++]);
			_dayWed = Integer.parseInt(values[col++]);
			_yrWed = Integer.parseInt(values[col++]);
			_where = values[col++];
			col++; //_divorced = values[col++];
		}
	}
	private ArrayList<SQLMarriage> SQLMarriages = new ArrayList<SQLMarriage>();
	
	private void doSQL(String nameSQL) throws Exception {
		String[] peopleFields = new String[] { "Key", "First", "Middle", "Maiden", "Last", "Jr",
				"Title", "Nickname", "Sex", "MonBorn", "DayBorn", "YrBorn", "WhereBorn", "Alive", "MonDied",
				"DayDied", "YrDied", "WhereBuried", "MotherKey", "FatherKey", "WikiTree" };
		int sperson = writer.createSheet(SHEETS.SPERSON.ordinal(), "SQL People", peopleFields);
		String[] marriageFields = new String[] { "HusbandKey", "WifeKey", "MonWed", "DayWed", "YrWed", "Where", "Divorced" };
		int smarriage = writer.createSheet(SHEETS.SMARRIAGE.ordinal(), "SQL Marriages", marriageFields);
		
		// Start reading the SQL file
		BufferedReader br = new BufferedReader(new FileReader(nameSQL));
		String rec;
		while ((rec = br.readLine()) != null) {
			if (rec.startsWith("INSERT INTO `Marriages` VALUES (")) {
				String[] mrg = getRow(rec, marriageFields.length);
				writer.addRow(smarriage, mrg);
				SQLMarriages.add(new SQLMarriage(mrg));
			} else if (rec.startsWith("INSERT INTO `People` VALUES (")) {
				//System.out.println("*********************" + rec);
				String[] ppl = getRow(rec, peopleFields.length);
				writer.addRow(sperson, ppl);
				SQLPeople.add(new SQLPerson(ppl));
			}
		}
		
		br.close();
	}

	private String[] getRow(String rec, int numCols) {
		String[] values = new String[numCols];
		StringBuffer sb = new StringBuffer();

		int pos = rec.indexOf('(');
		int nc = rec.length();
		for (int i = 0; i < numCols; i++) {
			sb.setLength(0);
			pos++;

			if (rec.charAt(pos) == '\'') {
				pos++;
				while (pos < nc-1) {
					char ch = rec.charAt(pos);
					if (ch == '\\' && rec.charAt(pos+1) == '\'') {
						pos++;
						ch = '\'';
					} else if (ch == '\'') break;
					sb.append(ch);
					pos++;
				}
				pos++;
			} else {
				while (pos < nc) {
					char ch = rec.charAt(pos);
					if (ch == ',' || ch == ')') break;
					sb.append(ch);
					pos++;
				}
			}
			values[i] = sb.toString();
		}
		return values;
	}
	
	private void validateMine(PrintWriter pw2, String outName) throws Exception {
		PrintWriter pw = new PrintWriter(new FileWriter(outName));
		int lines = 0;
		
		// Find WikiTree entries that are not in my tree
		for (GEDPerson gp : GEDPeople) {
			boolean found = false;
			for (SQLPerson sp : SQLPeople) {
				if (gp._www.equals(sp._wikiTree)) {
					found = true;
					break;
				}
			}
			if (!found) {
				if (gp._birtDate.equals("")) {
					pw2.println("<br>Person: " + gp._name + " (" + gp._www + ") is not in my tree, but the birth date is blank.");
				} else {
					pw2.println("<br>Person: " + gp._name + " (" + gp._www + ") is not in my tree, birth = " + gp._birtDate);
					SQLPerson sp = new SQLPerson(gp);
					sp.printSQL(pw);
					lines++;
				}
			}
		}
		
		// Look for missing parent(s)
		for (GEDChild gc : GEDChildren) {
			validateParent(pw, gc._chil, gc._husb, "FatherKey");
			validateParent(pw, gc._chil, gc._wife, "MotherKey");
		}
		
		// Look for missing marriages
		for (GEDMarriage gm : GEDMarriages) {
			GEDPerson ghusband = individualCodes.get(gm._husb);
			GEDPerson gwife = individualCodes.get(gm._wife);
			// System.out.println("    Marriage: " + individualCodes.get(gm._husb)._name + " and " +
			// 		individualCodes.get(gm._wife)._name);;
			SQLPerson shusband = findWiki(ghusband._www);
			SQLPerson swife = findWiki(gwife._www);
			
			if (shusband != null && swife != null) {
				// See if already in the system
				boolean found = false;
				for (SQLMarriage sm : SQLMarriages) {
					if (sm._husbandKey.equals(shusband._key) && sm._wifeKey.equals(swife._key)) {
						found = true;
						break;
					}
				}
				if (!found) {
					int monWed = getMonth(gm._marrDate);
					int dayWed = getDay(gm._marrDate);
					int yrWed = getYear(gm._marrDate);
					pw.println("INSERT Marriages SET HusbandKey = '" + shusband._key +
							"', WifeKey = '" + swife._key +
							"', MonWed = " + monWed +
							", DayWed = " + dayWed +
							", YrWed = " + yrWed +
							", `Where` = '" + gm._marrPlac + "';");
					lines++;
				}
			}
		}
		
		pw.close();
		System.out.println("Wrote " + outName + " lines=" + lines);
	}
	
	private void validateParent(PrintWriter pw, String childId, String parentId, String whichKey) {
		if (parentId.equals("")) return;
		GEDPerson gparent = individualCodes.get(parentId);
		GEDPerson gchild = individualCodes.get(childId);
		// System.out.println("   Child: " + whichKey + " of " + gchild._name + " is " + gparent._name);

		SQLPerson schild = findWiki(gchild._www);
		SQLPerson sparent = findWiki(gparent._www);
		
		if (schild != null && sparent != null) {
			String key;
			if (whichKey.equals("MotherKey")) key = schild._motherKey;
			else key = schild._fatherKey;
			if (key.length() == 0) {
				pw.println("UPDATE People SET " + whichKey + " = '" + sparent._key + "' WHERE KeyName = '" + schild._key + "';");
			}
		}
	}
	
	private SQLPerson findWiki(String www) {
		if (www.equals("")) return null;
		for (SQLPerson p : SQLPeople) {
			if (p._wikiTree.equals(www)) return p;
		}
		return null;
	}
	
	private GEDPerson findGEDPerson(String key) {
		for (SQLPerson sp : SQLPeople) {
			if (sp._key.equals(key)) {
				for (GEDPerson gp : GEDPeople) {
					if (gp._www.equals(sp._wikiTree)) {
						return gp;
					}
				}
			}
		}
		return null;
	}
	
	private SQLPerson findSQLPerson(String key) {
		for (GEDPerson gp : GEDPeople) {
			if (gp._id.equals(key)) {
				for (SQLPerson sp : SQLPeople) {
					if (!sp._wikiTree.equals("") && sp._wikiTree.equals(gp._www)) {
						return sp;
					}
				}
			}
		}
		return null;
	}

	private void validateWikiTree(String outName) throws Exception {
		PrintWriter pw = new PrintWriter(new FileWriter(outName));
		
		for (SQLPerson sp : SQLPeople) {
			// WikiTree has some limitations
			if (! sp._wikiTree.equals("")) continue;
			if (sp._last.equals("")) continue;
			
			GEDPerson gp = new GEDPerson(sp);
			boolean hasFamily = (sp.hasSpouse() || sp.hasChildren());
			gp.printGED(pw, hasFamily);
			if (hasFamily) sp._GED_families = gp._famc;
		}
		
		// Look for families
		for (SQLPerson sp : SQLPeople) {
			if (sp._GED_families != null) {
				
				// Look for spouse
				if (sp._sex.equals("M")) {
					pw.println("0 " + sp._GED_families + " FAM");
					pw.println("1 HUSB " + sp._wikiTree);
					for (SQLMarriage sm : SQLMarriages) {
						if (sm._husbandKey.equals(sp._key)) {
							for (SQLPerson w : SQLPeople) {
								if (w._key.equals(sm._wifeKey)) {
									pw.println("1 WIFE " + w._wikiTree);
								}
							}
						}
					}
				} else {
					if (sp.hasSpouse()) continue;
					pw.println("0 " + sp._GED_families + " FAM");
					pw.println("1 WIFE " + sp._wikiTree);
				}
				
				// Look for children
				for (SQLPerson c : SQLPeople) {
					if (c._fatherKey.equals(sp._key) || c._motherKey.equals(sp._key)) {
						pw.println("1 CHIL " + c._wikiTree);
					}
				}
			}
		}

		pw.close();
		System.out.println("Wrote " + outName + " people=" + _idCount + " families=" + _families);
	}
	
	private void compareTrees(PrintWriter pw) throws Exception {
		// See who is missing
		boolean didHdr = false;
		for (SQLPerson sp : SQLPeople) {
			if (!sp._wikiTree.equals("")) continue;
			
			if (!didHdr) {
				didHdr = true;
				pw.println("<p>Missing WikiTree identifier:");
				pw.println("<ul>");
			}
			pw.println("  <li>" + sp._key);
		}
		if (didHdr) pw.println("</ul>");
		
		// See who is different
		pw.println("<br/><table border=1>");
		pw.println("<tr><td>Wiki Key<td>My Key<td>Field<td>Mine<td>WikiTree");
		for (SQLPerson sp : SQLPeople) {
			if (sp._wikiTree.equals("")) continue;
			
			for (GEDPerson gp : GEDPeople) {
				if (gp._www.equals(sp._wikiTree)) {
					comparePerson(pw, sp, gp);
					break;
				}
			}
		}
		pw.println("</table><br/>");
	}
	
	private void comparePerson(PrintWriter pw, SQLPerson sp, GEDPerson gp) {
		String www = sp._wikiTree;
		compareField(pw, www, sp._key, "Sex", sp._sex, gp._sex);

		compareField(pw, www, sp._key, "First name", sp._first, gp._givn);
		compareField(pw, www, sp._key, "Middle name", sp._middle, gp._midn);
		if (sp._maiden.equals("")) {
			compareField(pw, www, sp._key, "Maiden name", sp._maiden, gp._marn);
			compareField(pw, www, sp._key, "Last name", sp._last, gp._surn);
		} else {
			compareField(pw, www, sp._key, "Maiden name", sp._maiden, gp._surn);
			compareField(pw, www, sp._key, "Last name", sp._last, gp._marn);
		}

		compareField(pw, www, sp._key, "Birth Date",
				sp._monBorn + "-" + sp._dayBorn + "-" + sp._yrBorn,
				getMonth(gp._birtDate) + "-" + getDay(gp._birtDate) + "-" + getYear(gp._birtDate));
		compareField(pw, www, sp._key, "Birth Place", sp._whereBorn, gp._birtPlac);
		
		compareField(pw, www, sp._key, "Death Date",
				sp._monDied + "-" + sp._dayDied + "-" + sp._yrDied,
				getMonth(gp._deatDate) + "-" + getDay(gp._deatDate) + "-" + getYear(gp._deatDate));
		compareField(pw, www, sp._key, "Death Place", sp._whereBuried, gp._deatPlac);
	}
	
	private void compareField(PrintWriter pw, String www, String key, String field, String sp, String gp) {
		if (sp.equals(gp)) return;
		pw.println("  <tr><td>" + www + "<td>" + key + "<td>" + field + "<td>" + sp + "<td>" + gp);
	}
	
	private void compareMarriages(PrintWriter pw) {
		pw.println("<table border=1>");
		pw.println("<tr><td>Wiki Keys<td>My Keys<td>Field<td>Mine<td>Wiki");
		for (SQLMarriage sm : SQLMarriages) {
			GEDPerson husband = findGEDPerson(sm._husbandKey);
			GEDPerson wife = findGEDPerson(sm._wifeKey);
			if (husband == null) {
				//queue.add("Unable to find SQL husband " + sm._husbandKey);
				continue;
			}
			if (wife == null) {
				//queue.add("Unable to find SQL wife " + sm._wifeKey);
				continue;
			}
			
			boolean foundIt = false;
			for (GEDMarriage gm : GEDMarriages) {
				if (gm._husb == null || gm._wife == null) continue;
				if (gm._husb.equals(husband._id) && gm._wife.equals(wife._id)) {
					if (sm._dayWed != getDay(gm._marrDate) ||
							sm._monWed != getMonth(gm._marrDate) ||
							sm._yrWed != getYear(gm._marrDate)) {
						pw.println("<tr><td>" + husband._www + " " +
								findName(gm._husb) + " - " + findName(gm._wife) +
								"<td>" + sm._husbandKey + " - " + sm._wifeKey +
								"<td>Date<td>" + sm._monWed + "-" + sm._dayWed + "-" + sm._yrWed +
								"<td>" + getMonth(gm._marrDate) + "-" + getDay(gm._marrDate) + "-" +
								getYear(gm._marrDate) + " (" + gm._marrDate + ")");
					}
					if (!sm._where.equals(gm._marrPlac)) {
						pw.println("<tr><td>" + husband._www + " " +
								findName(gm._husb) + " - " + findName(gm._wife) +
								"<td>" + sm._husbandKey + " - " + sm._wifeKey +
								"<td>Where<td>" + sm._where +
								"<td>" + gm._marrPlac);
					}
					foundIt = true;
					break;
				}
			}
			if (!foundIt) {
				pw.println("<tr><td>" + "<i>Missing " + husband._www + " - " + wife._www + "</i>" +
						"<td>" + sm._husbandKey + " - " + sm._wifeKey);
			}
		}

		for (GEDMarriage gm : GEDMarriages) {
			SQLPerson husband = findSQLPerson(gm._husb);
			SQLPerson wife = findSQLPerson(gm._wife);
			if (husband == null) {
				//queue.add("Unable to find GED husband " + findName(gm._husb));
				continue;
			}
			if (wife == null) {
				//queue.add("Unable to find GED wife " + findName(gm._wife));
				continue;
			}
			
			if (husband._wikiTree.equals("")) continue;
			if (wife._wikiTree.equals("")) continue;

			boolean foundIt = false;
			for (SQLMarriage sm : SQLMarriages) {
				if (sm._husbandKey.equals(husband._key) && sm._wifeKey.equals(wife._key)) {
					foundIt = true;
					break;
				}
			}
			if (!foundIt) {
				pw.println("<tr><td>" + husband._wikiTree + " " + findName(gm._husb) + " - " +
						wife._wikiTree + " " + findName(gm._wife) + "<td>" + "<i>Missing</i>");
			}
		}
		pw.println("</table><br/>");
	}
	
	private void compareChildren(PrintWriter pw) {
		pw.println("<table border=1>");
		pw.println("<tr><td>Child<td>My Parent<td>Wiki Parent");
		
		for (GEDChild gc : GEDChildren) {
			SQLPerson dad = findSQLPerson(gc._husb);
			SQLPerson mom = findSQLPerson(gc._wife);
			SQLPerson kid = findSQLPerson(gc._chil);
			if (kid == null) {
				String parents = "";
				if (dad != null && mom != null) parents = " (dad=" + dad._key + ", mom=" + mom._key + ")";
				else if (dad != null) parents = " (dad=" + dad._key + ")";
				else if (mom != null) parents = " (mom=" + mom._key + ")";
				queue.add("Unable to find child " + findName(gc._chil) + " in mine" + parents);
				continue;
			}
			
			if (dad != null) {
				if (kid._fatherKey.equals("")) {
					pw.println("<tr><td>" + kid._key + "<td><i>missing dad</i><td>" + dad._key);
				}
				else if (!kid._fatherKey.equals(dad._key)) {
					pw.println("<tr><td>" + kid._key + "<td>" + kid._fatherKey + "<td>" + dad._key);
				}
			}
			if (mom != null) {
				if (kid._motherKey.equals("")) {
					pw.println("<tr><td>" + kid._key + "<td><i>missing mom</i><td>" + mom._key);
				}
				else if (!kid._motherKey.equals(mom._key)) {
					pw.println("<tr><td>" + kid._key + "<td>" + kid._motherKey + "<td>" +  mom._key);
				}
			}
		}
		
		for (SQLPerson sp : SQLPeople) {
			GEDPerson dad = findGEDPerson(sp._fatherKey);
			GEDPerson mom = findGEDPerson(sp._motherKey);
			GEDPerson kid = findGEDPerson(sp._key);
			if (kid == null) {
				String parents = "";
				if (dad != null && mom != null) parents = " (dad=" + findName(dad._id) + ", mom=" + findName(mom._id) + ")";
				else if (dad != null) parents = " (dad=" + findName(dad._id) + ")";
				else if (mom != null) parents = " (mom=" + findName(mom._id) + ")";
				queue.add("Unable to find child " + sp._key + " in WikiTree" + parents);
				continue;
			}
			
			for (GEDChild gc : GEDChildren) {
				if (gc._chil.equals(kid._id)) {
					if (dad != null) {
						if (gc._husb.equals("")) {
							pw.println("<tr><td>" + findName(kid._id) + "<td>" + sp._fatherKey + "<td><i>missing dad");
						}
						else if (!gc._husb.equals(dad._id)) {
							pw.println("<tr><td>" + findName(kid._id) + "<td>" + sp._fatherKey + "<td>" + findName(dad._id));
						}
					}
					if (mom != null) {
						if (gc._wife.equals("")) {
							pw.println("<tr><td>" + findName(kid._id) + "<td>" + sp._motherKey + "<td><i>missing mom");
						}
						else if (!gc._wife.equals(mom._id)) {
							pw.println("<tr><td>" + findName(kid._id) + "<td>" + sp._motherKey + "<td>" + findName(mom._id));
						}
					}
					break;
				}
			}
		}
		
		pw.println("</table><br/>");
	}
	
	private void printQueue(PrintWriter pw) {
		pw.println("<p>Missing people");
		pw.println("<ul>");
		for (String line : queue) {
			pw.println("<li>" + line);
		}
	}
	
	public static void main(String[] args) {
		if (args.length != 6) {
			System.out.println("Usage: in.GED in.SQL out.XL out.SQL out.GED out.HTML");
			System.exit(1);
		}
		String nameGED = args[0];
		String nameSQL = args[1];
		String nameXL = args[2];
		String outSQL = args[3];
		String outGED = args[4];
		String outHTML = args[5];
		
		try {
			ReadGEDCOM m = new ReadGEDCOM();

			// Create the XL file
			m.writer = new WriteXL();
			m.writer.openXL(nameXL, SHEETS.values().length);
			m.doGED(nameGED);
			m.doSQL(nameSQL);
			
			m.writer.close();
			System.out.println("Created Excel " + nameXL);
			
			PrintWriter pw = new PrintWriter(new FileWriter(outHTML));
			pw.println("<html>");
			pw.println("<body>");
			
			m.validateMine(pw, outSQL);
			pw.println("<br/>");
			m.validateWikiTree(outGED);

			m.compareTrees(pw);
			m.compareMarriages(pw);
			m.compareChildren(pw);
			m.printQueue(pw);

			pw.println("</body>");
			pw.println("</html>");
			pw.close();
			System.out.println("Finished writing to " + outHTML);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(2);
		}
		System.exit(0);
	}
}
