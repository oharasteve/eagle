// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

package com.eagle.programmar.CMacro.Statements;

import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_Number;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class CMacro_Pragma_Statement extends TokenSequence
{
	public @DOC("Pragmas.html") C_Keyword PRAGMA = new C_Keyword("pragma");
	public CMacro_Pragma_Type what;
	
	public static class CMacro_Pragma_Type extends TokenChooser
	{
		public C_Keyword ONCE = new C_Keyword("once");
		
		public static class CMacro_Pragma_Warning extends TokenSequence
		{
			public C_Keyword WARNING = new C_Keyword("warning");
			public C_Punctuation leftParen = new C_Punctuation('(');
			public C_Keyword DISABLE = new C_Keyword("disable");
			public C_Punctuation colon = new C_Punctuation(':');
			public C_Number code;
			public C_Punctuation rightParen = new C_Punctuation(')');
		}
	}
}
