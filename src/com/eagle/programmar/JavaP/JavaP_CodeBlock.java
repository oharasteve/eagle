// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 3, 2015

package com.eagle.programmar.JavaP;

import com.eagle.programmar.JavaP.Blocks.JavaP_CodeExceptions;
import com.eagle.programmar.JavaP.Blocks.JavaP_CodeLineNumbers;
import com.eagle.programmar.JavaP.Blocks.JavaP_CodeLocalValues;
import com.eagle.programmar.JavaP.Blocks.JavaP_CodeStackInfo;
import com.eagle.programmar.JavaP.Blocks.JavaP_StackMapTable;
import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class JavaP_CodeBlock extends TokenSequence
{
	public JavaP_Keyword CODE = new JavaP_Keyword("Code");
	public PunctuationColon colon;
	public JavaP_EndOfLine eoln;
	
	public @OPT TokenList<JavaP_CodeDetails> details;

	public static class JavaP_CodeDetails extends TokenChooser
	{
		public JavaP_CodeLocalValues localValues; 
		public JavaP_CodeStackInfo stackInfo; 
		public JavaP_CodeLineNumbers lineNumbers; 
		public JavaP_StackMapTable stackMap;
		public JavaP_CodeExceptions codeExceptions;
	}
}