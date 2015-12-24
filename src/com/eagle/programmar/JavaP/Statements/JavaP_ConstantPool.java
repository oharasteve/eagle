// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

package com.eagle.programmar.JavaP.Statements;

import com.eagle.programmar.JavaP.Constants.JavaP_ConstantClass;
import com.eagle.programmar.JavaP.Constants.JavaP_ConstantLong;
import com.eagle.programmar.JavaP.Constants.JavaP_ConstantMethodRef;
import com.eagle.programmar.JavaP.Constants.JavaP_ConstantNameAndType;
import com.eagle.programmar.JavaP.Constants.JavaP_ConstantString;
import com.eagle.programmar.JavaP.Constants.JavaP_ConstantUtf8;
import com.eagle.programmar.JavaP.Symbols.JavaP_Symbol_Definition;
import com.eagle.programmar.JavaP.Terminals.JavaP_Comment;
import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class JavaP_ConstantPool extends TokenSequence
{
	public JavaP_Keyword CONSTANT = new JavaP_Keyword("Constant");
	public JavaP_Keyword POOL = new JavaP_Keyword("pool");
	public PunctuationColon colon;
	public JavaP_EndOfLine eoln;
	
	public TokenList<JavaP_Constant> constants;
	
	public static class JavaP_Constant extends TokenSequence
	{
		public @OPT JavaP_Keyword CONST = new JavaP_Keyword("const");
		public JavaP_Symbol_Definition symbol;
		public PunctuationEquals equals;
		public JavaP_ConstantType type;
		public @OPT PunctuationSemicolon semicolon;
		public @OPT JavaP_Comment comment;
		public JavaP_EndOfLine eoln;
		
		public static class JavaP_ConstantType extends TokenChooser
		{
			// All of these need to implement the JavaP_ConstantShowable interface
			public JavaP_ConstantClass constantClass;
			public JavaP_ConstantLong constantLong;
			public JavaP_ConstantMethodRef methodRef;
			public JavaP_ConstantNameAndType nameAndType;
			public JavaP_ConstantString constantString;
			public JavaP_ConstantUtf8 constantUtf8;
		}
	}
	
	public interface JavaP_ConstantShowable
	{
		public String showConstant();
	}
}
