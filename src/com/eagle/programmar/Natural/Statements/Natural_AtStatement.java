// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 4, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Natural_Statement;
import com.eagle.programmar.Natural.Natural_Variable;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Natural_AtStatement extends TokenSequence
{
	public Natural_Keyword AT = new Natural_Keyword("AT");
	public Natural_AtWhat atWhat;
	
	public static class Natural_AtWhat extends TokenChooser
	{
		public @CHOICE static class Natural_AtEndOfData extends TokenSequence
		{
			public @DOC("sm/atenddat.htm") Natural_Keyword END = new Natural_Keyword("END");
			public @OPT Natural_Keyword OF = new Natural_Keyword("OF");
			public Natural_Keyword DATA = new Natural_Keyword("DATA");
			public TokenList<Natural_Statement> statements;
			public @OPT Natural_Keyword ENDENDDATA = new Natural_Keyword("END-ENDDATA");
		}
		
		public @CHOICE static class Natural_AtEndOfPage extends TokenSequence
		{
			public @DOC("sm/atendpag.htm") Natural_Keyword END = new Natural_Keyword("END");
			public @OPT Natural_Keyword OF = new Natural_Keyword("OF");
			public Natural_Keyword PAGE = new Natural_Keyword("PAGE");
			public TokenList<Natural_Statement> statements;
			public Natural_Keyword ENDENDPAGE = new Natural_Keyword("END-ENDPAGE");
		}
		
		public @CHOICE static class Natural_AtBreakOfVariable extends TokenSequence
		{
			public @DOC("sm/atbreak.htm") Natural_Keyword BREAK = new Natural_Keyword("BREAK");
			public @OPT Natural_Keyword OF = new Natural_Keyword("OF");
			public Natural_Variable var;
			public TokenList<Natural_Statement> statements;
			public Natural_Keyword ENDBREAK = new Natural_Keyword("END-BREAK");
		}

		public @CHOICE static class Natural_AtStartOfData extends TokenSequence
		{
			public @DOC("sm/atstart.htm") Natural_Keyword START = new Natural_Keyword("START");
			public @OPT Natural_Keyword OF = new Natural_Keyword("OF");
			public Natural_Keyword DATA = new Natural_Keyword("DATA");
			public TokenList<Natural_Statement> statements;
			public Natural_Keyword ENDSTART = new Natural_Keyword("END-START");
		}
	}
}
