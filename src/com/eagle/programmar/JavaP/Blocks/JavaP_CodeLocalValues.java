// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 4, 2015

package com.eagle.programmar.JavaP.Blocks;

import com.eagle.programmar.JavaP.JavaP_Value;
import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_HexNumber;
import com.eagle.programmar.JavaP.Terminals.JavaP_HexNumber.JavaP_HexNoPrefix;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice;
import com.eagle.programmar.JavaP.Terminals.JavaP_Number;
import com.eagle.programmar.JavaP.Terminals.JavaP_QualifiedName;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationEquals;

public class JavaP_CodeLocalValues extends TokenSequence
{
	public JavaP_KeywordChoice LOCALVARIABLES = new JavaP_KeywordChoice("LocalVariableTable", "LocalVariableTypeTable");
	public PunctuationColon colon;
	public JavaP_CodeLocalHeaderChoice header;
	
	public static class JavaP_CodeLocalHeaderChoice extends TokenChooser
	{
		public @CHOICE static class JavaP_CodeLocalHeader extends TokenSequence
		{
			public JavaP_EndOfLine eoln1;
			public JavaP_Keyword START = new JavaP_Keyword("Start");
			public JavaP_Keyword LENGTH = new JavaP_Keyword("Length");
			public JavaP_Keyword SLOT = new JavaP_Keyword("Slot");
			public JavaP_Keyword NAME = new JavaP_Keyword("Name");
			public JavaP_Keyword SIGNATURE = new JavaP_Keyword("Signature");
			public JavaP_EndOfLine eoln2;
			
			public @OPT TokenList<JavaP_CodeLocalEntry> entries;
			
			public static class JavaP_CodeLocalEntry extends TokenSequence
			{
				public JavaP_Number start;
				public JavaP_Number length;
				public JavaP_Number slot;
				public JavaP_QualifiedName name;
				public JavaP_Value value;
				public JavaP_EndOfLine eoln;
			}
		}
		
		public @CHOICE static class JavaP_CodeLocalLength extends TokenSequence
		{
			public JavaP_Keyword LENGTH = new JavaP_Keyword("length");
			public PunctuationEquals equals;
			public JavaP_HexNumber len;
			public TokenList<JavaP_CodeLocalHexes> hexes;
			
			public static class JavaP_CodeLocalHexes extends TokenChooser
			{
				public @CHOICE JavaP_HexNoPrefix hex;
				public @CHOICE JavaP_EndOfLine eoln;
			}
		}
	}
}