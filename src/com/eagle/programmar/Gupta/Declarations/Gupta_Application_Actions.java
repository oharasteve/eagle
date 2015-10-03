// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 11, 2011

package com.eagle.programmar.Gupta.Declarations;

import com.eagle.programmar.Gupta.Gupta_Declaration;
import com.eagle.programmar.Gupta.Gupta_Statement;
import com.eagle.programmar.Gupta.Terminals.Gupta_Keyword;
import com.eagle.programmar.Gupta.Terminals.Gupta_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Gupta_Application_Actions extends Gupta_Declaration
{
	public Gupta_Keyword Application = new Gupta_Keyword("Application");
	public Gupta_Keyword Actions = new Gupta_Keyword("Actions");
	
	public @INDENT TokenList<Gupta_OnEvent> onEvents;
	
	public static class Gupta_OnEvent extends TokenSequence
	{
		public Gupta_Keyword On = new Gupta_Keyword("On");
		public Gupta_KeywordChoice eventName = new Gupta_KeywordChoice("SAM_AppStartup");
		public @INDENT TokenList<Gupta_Statement> actions;
	}
}
