// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

package com.eagle.programmar.JavaP;

import com.eagle.programmar.JavaP.Symbols.JavaP_Symbol_Reference;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.programmar.JavaP.Terminals.JavaP_LClassName;
import com.eagle.programmar.JavaP.Terminals.JavaP_Literal;
import com.eagle.programmar.JavaP.Terminals.JavaP_Punctuation;
import com.eagle.programmar.JavaP.Terminals.JavaP_QualifiedName;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class JavaP_Value extends TokenChooser
{
	public @FIRST JavaP_Type type;
	public JavaP_Symbol_Reference symbol;
	public @LAST JavaP_QualifiedName name;
	public JavaP_Literal literal;
	
	public static class JavaP_ValueLClass extends TokenSequence
	{
		public JavaP_LClassName className;
		public PunctuationSemicolon semicolon;
	}
	
	public static class JavaP_ValueInit extends TokenSequence
	{
		public JavaP_Punctuation lessThan = new JavaP_Punctuation('<');
		public JavaP_Keyword INIT = new JavaP_Keyword("init");
		public JavaP_Punctuation greaterThan = new JavaP_Punctuation('>');
	}
	
	public static class JavaP_ValueArray extends TokenSequence
	{
		public PunctuationLeftParen leftParen;
		public @OPT TokenList<JavaP_Type> parameters;
		public PunctuationRightParen rightParen;
		public JavaP_Type returns;
	}
}
