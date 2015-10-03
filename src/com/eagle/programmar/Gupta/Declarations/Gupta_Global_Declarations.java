// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 11, 2011

package com.eagle.programmar.Gupta.Declarations;

import com.eagle.programmar.Gupta.Gupta_Declaration;
import com.eagle.programmar.Gupta.Terminals.Gupta_Keyword;

public class Gupta_Global_Declarations extends Gupta_Declaration
{
	public Gupta_Keyword Global = new Gupta_Keyword("Global");
	public Gupta_Keyword Declarations = new Gupta_Keyword("Declarations");

	public @INDENT Gupta_Window_Defaults windowDefaults;
	public Gupta_Formats formats;
	public Gupta_External_Functions externalFunctions;
	public Gupta_Constants constants;
	public Gupta_Resources resources;
	public Gupta_Variables variables;
	public Gupta_Internal_Functions internalFunctions;
	public Gupta_Named_Menus namedMenus;
	public Gupta_Class_Definitions classDefinitions;
	public Gupta_Application_Actions applicationActions;
}
