// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 3, 2015

package com.eagle.programmar;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import com.eagle.programmar.AWK.AWK_Program;
import com.eagle.programmar.BNF.BNF_Program;
import com.eagle.programmar.C.C_Program;
import com.eagle.programmar.CMD.CMD_Program;
import com.eagle.programmar.CMacro.CMacro_Program;
import com.eagle.programmar.COBOL.COBOL_Partial_Fixed_Format;
import com.eagle.programmar.COBOL.COBOL_Program_Fixed_Format;
import com.eagle.programmar.COBOL.COBOL_Program_Free_Format;
import com.eagle.programmar.CPlus.CPlus_Program;
import com.eagle.programmar.CSS.CSS_Program;
import com.eagle.programmar.CSharp.CSharp_Program;
import com.eagle.programmar.Delphi.Delphi_Configuration;
import com.eagle.programmar.Delphi.Delphi_Program;
import com.eagle.programmar.Django.Django_Program;
import com.eagle.programmar.Gupta.Gupta_Program;
import com.eagle.programmar.HTML.HTML_Program;
import com.eagle.programmar.IBMASM.IBMASM_Program;
import com.eagle.programmar.JSON.JSON_Program;
import com.eagle.programmar.Java.Java_Program;
import com.eagle.programmar.Javascript.Javascript_Program;
import com.eagle.programmar.Lisp.Lisp_Program;
import com.eagle.programmar.Natural.Natural_Program;
import com.eagle.programmar.PHP.PHP_Program;
import com.eagle.programmar.PLI.PLI_Program;
import com.eagle.programmar.Property.Property_Program;
import com.eagle.programmar.Python.Python_Program;
import com.eagle.programmar.RPG.RPG_Program.RPG_III_Program;
import com.eagle.programmar.RPG.RPG_Program.RPG_IV_Program;
import com.eagle.programmar.SQL.SQL_Program;
import com.eagle.programmar.VB.VB_Program;
import com.eagle.programmar.XML.XML_Program;

public class EagleLanguageLookup
{
	public EagleLanguageLookup()
	{
		setLanguageDefaults();
	}
	
	public static class LanguageDefinition
	{
		String _name;
		Class<? extends EagleLanguage> _langClass;
		
		public LanguageDefinition(String name, Class<? extends EagleLanguage> langClass)
		{
			_name = name;
			_langClass = langClass;
		}
	}

	private LanguageDefinition[] _allLanguages = new LanguageDefinition[] {
		new LanguageDefinition(AWK_Program.NAME, AWK_Program.class),
		new LanguageDefinition(BNF_Program.NAME, BNF_Program.class),
		new LanguageDefinition(C_Program.NAME, C_Program.class),
		new LanguageDefinition(CMacro_Program.NAME, CMacro_Program.class),
		new LanguageDefinition(CMD_Program.NAME, CMD_Program.class),
		new LanguageDefinition(COBOL_Program_Free_Format.NAME, COBOL_Program_Free_Format.class),
		new LanguageDefinition(COBOL_Program_Fixed_Format.NAME, COBOL_Program_Fixed_Format.class),
		new LanguageDefinition(COBOL_Partial_Fixed_Format.NAME, COBOL_Partial_Fixed_Format.class),
		new LanguageDefinition(CPlus_Program.CPPNAME, CPlus_Program.class),
		new LanguageDefinition(CSharp_Program.NAME, CSharp_Program.class),
		new LanguageDefinition(CSS_Program.NAME, CSS_Program.class),
		new LanguageDefinition(Delphi_Program.NAME, Delphi_Program.class),
		new LanguageDefinition(Delphi_Configuration.NAME, Delphi_Configuration.class),
		new LanguageDefinition(Django_Program.NAME, Django_Program.class),
		new LanguageDefinition(Gupta_Program.NAME, Gupta_Program.class),
		new LanguageDefinition(HTML_Program.NAME, HTML_Program.class),
		new LanguageDefinition(IBMASM_Program.NAME, IBMASM_Program.class),
		//new LanguageDefinition(IntelASM_Program.NAME, IntelASM_Program.class),
		new LanguageDefinition(Java_Program.NAME, Java_Program.class),
		new LanguageDefinition(Javascript_Program.NAME, Javascript_Program.class),
		new LanguageDefinition(JSON_Program.NAME, JSON_Program.class),
		new LanguageDefinition(Lisp_Program.NAME, Lisp_Program.class),
		new LanguageDefinition(Natural_Program.NAME, Natural_Program.class),
		//new LanguageDefinition(Perl_Program.NAME, Perl_Program.class),
		new LanguageDefinition(PHP_Program.NAME, PHP_Program.class),
		new LanguageDefinition(PLI_Program.NAME, PLI_Program.class),
		new LanguageDefinition(Property_Program.NAME, Property_Program.class),
		new LanguageDefinition(Python_Program.NAME, Python_Program.class),
		new LanguageDefinition(RPG_III_Program.NAME, RPG_III_Program.class),
		new LanguageDefinition(RPG_IV_Program.NAME, RPG_IV_Program.class),
		new LanguageDefinition(SQL_Program.NAME, SQL_Program.class),
		//new LanguageDefinition(TCL_Program.NAME, TCL_Program.class),
		new LanguageDefinition(VB_Program.NAME, VB_Program.class),
		new LanguageDefinition(XML_Program.NAME, XML_Program.class),
	};
	
	static class LanguageSuffixName
	{
		public String _suffix;
		public String _languageName;
	}
	
	private ArrayList<LanguageSuffixName> _suffixes = new ArrayList<LanguageSuffixName>();

	public void setLanguageSuffix(String suffix, String languageName)
	{
		for (LanguageSuffixName entry : _suffixes)
		{
			if (entry._suffix.equalsIgnoreCase(suffix))
			{
				// Override an existing entry
				entry._languageName = languageName;
				return;
			}
		}
		
		// Have to create a new entry
		LanguageSuffixName entry = new LanguageSuffixName();
		entry._suffix = suffix;
		entry._languageName = languageName;
		_suffixes.add(entry);
	}
	
	private void setLanguageDefaults()
	{
		//setLanguageSuffix(".bash", Shell_Program.NAME);
		setLanguageSuffix(".awk", AWK_Program.NAME);
		setLanguageSuffix(".bat", CMD_Program.NAME);
		setLanguageSuffix(".bnf", BNF_Program.NAME);
		setLanguageSuffix(".c", C_Program.NAME);
		setLanguageSuffix(".cbl", COBOL_Program_Free_Format.NAME);
		setLanguageSuffix(".cc", CPlus_Program.CPPNAME);
		setLanguageSuffix(".cls", VB_Program.NAME);
		setLanguageSuffix(".cpp", CPlus_Program.CPPNAME);
		setLanguageSuffix(".cs", CSharp_Program.NAME);
		setLanguageSuffix(".css", CSS_Program.NAME);
		setLanguageSuffix(".h", C_Program.NAME);
		setLanguageSuffix(".hh", CPlus_Program.CPPNAME);
		setLanguageSuffix(".htm", HTML_Program.NAME);
		setLanguageSuffix(".html", HTML_Program.NAME);
		setLanguageSuffix(".java", Java_Program.NAME);
		setLanguageSuffix(".js", Javascript_Program.NAME);
		setLanguageSuffix(".json", JSON_Program.NAME);
		setLanguageSuffix(".lisp", Lisp_Program.NAME);
		//setLanguageSuffix(".m4", Macro_Program.NAME);
		setLanguageSuffix(".ntf", Natural_Program.NAME);
		setLanguageSuffix(".p", Delphi_Program.NAME);
		setLanguageSuffix(".pas", Delphi_Program.NAME);
		setLanguageSuffix(".php", PHP_Program.NAME);
		//setLanguageSuffix(".pl", Perl_Program.NAME);
		setLanguageSuffix(".pli", PLI_Program.NAME);
		//setLanguageSuffix(".pm", Perl_Program.NAME);
		setLanguageSuffix(".properties", Property_Program.NAME);
		setLanguageSuffix(".py", Python_Program.NAME);
		setLanguageSuffix(".rpg", RPG_III_Program.NAME);
		//setLanguageSuffix(".sh", Shell_Program.NAME);
		setLanguageSuffix(".sql", SQL_Program.NAME);
		//setLanguageSuffix(".tcl", TCL_Program.NAME);
		setLanguageSuffix(".xml", XML_Program.NAME);
		setLanguageSuffix(".xsd", XML_Program.NAME);
	}
	
	// Decide which language it is, from the name
	public EagleLanguage findLanguage(String langName)
	{
		try
		{
			for (LanguageDefinition lang : _allLanguages)
			{
				if (langName.equalsIgnoreCase(lang._name))
				{
					Constructor<? extends EagleLanguage> cons = lang._langClass.getConstructor();
					return cons.newInstance();
				}
			}
		}
		catch (Exception ex)
		{
			throw new RuntimeException("Unable create instance of " + langName + " program", ex);
		}
		throw new RuntimeException("Unknown language: " + langName);
	}
	
	public ArrayList<String> allLanguageNames()
	{
		ArrayList<String> languages = new ArrayList<String>();
		for (LanguageDefinition lang : _allLanguages)
		{
			languages.add(lang._name);
		}
		return languages;
	}
	
	public ArrayList<String> allLanguageClassNames()
	{
		ArrayList<String> classes = new ArrayList<String>();
		for (LanguageDefinition lang : _allLanguages)
		{
			classes.add(lang._langClass.getName());
		}
		return classes;
	}
	
	public String lookupSuffix(String fileName)
	{
		String lowerName = fileName.toLowerCase();
		for (LanguageSuffixName entry : _suffixes)
		{
			if (lowerName.endsWith(entry._suffix)) return entry._languageName;
		}
		return null;	// Didn't find it
	}
}
