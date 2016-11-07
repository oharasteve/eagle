// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

package com.eagle.programmar.CMacro.Statements;

import com.eagle.preprocess.C.CMacro_Preprocess;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_Number;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.programmar.CMacro.CMacro_Processable;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CMacro_Pragma_Statement extends TokenSequence implements CMacro_Processable
{
	public C_Punctuation pound = new C_Punctuation('#'); 
	public @DOC("Pragmas.html") C_Keyword PRAGMA = new C_Keyword("pragma");
	public CMacro_Pragma_Type what;
	
	public static class CMacro_Pragma_Type extends TokenChooser
	{
		public @CHOICE C_Keyword ONCE = new C_Keyword("once");
		
		public @CHOICE static class CMacro_Pragma_Warning extends TokenSequence
		{
			public C_Keyword WARNING = new C_Keyword("warning");
			public PunctuationLeftParen leftParen;
			public C_Keyword DISABLE = new C_Keyword("disable");
			public PunctuationColon colon;
			public TokenList<C_Number> codes;
			public PunctuationRightParen rightParen;
		}
		
		public @CHOICE static class CMacro_Pragma_Pack extends TokenSequence
		{
			public C_Keyword PACK = new C_Keyword("pack");
			public PunctuationLeftParen leftParen;
			public @OPT C_Keyword PUSH = new C_Keyword("push");
			public PunctuationRightParen rightParen;
		}
	}
	
	@Override
	public boolean processMacro(CMacro_Preprocess preprocessor)
	{
		// Nothing to do
		return false;	// false means we didn't change anything
	}
}
