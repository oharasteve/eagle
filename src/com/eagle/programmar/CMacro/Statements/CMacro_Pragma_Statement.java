// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

package com.eagle.programmar.CMacro.Statements;

import com.eagle.preprocess.FindIncludeFile;
import com.eagle.preprocess.C.CMacro_Preprocess;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_Number;
import com.eagle.programmar.CMacro.CMacro_Statement;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CMacro_Pragma_Statement extends CMacro_Statement
{
	public @DOC("Pragmas.html") C_Keyword PRAGMA = new C_Keyword("pragma");
	public CMacro_Pragma_Type what;
	
	public static class CMacro_Pragma_Type extends TokenChooser
	{
		public C_Keyword ONCE = new C_Keyword("once");
		
		public static class CMacro_Pragma_Warning extends TokenSequence
		{
			public C_Keyword WARNING = new C_Keyword("warning");
			public PunctuationLeftParen leftParen;
			public C_Keyword DISABLE = new C_Keyword("disable");
			public PunctuationColon colon;
			public C_Number code;
			public PunctuationRightParen rightParen;
		}
	}
	
	@Override
	public boolean processMacro(CMacro_Preprocess preprocessor, FindIncludeFile findInclude)
	{
		// Nothing to do
		return false;	// false means we didn't change anything
	}
}
