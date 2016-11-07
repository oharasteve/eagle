// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 4, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Natural_FunctionCall;
import com.eagle.programmar.Natural.Natural_Option;
import com.eagle.programmar.Natural.Natural_SystemVariable;
import com.eagle.programmar.Natural.Natural_Variable;
import com.eagle.programmar.Natural.Terminals.Natural_Comment;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice;
import com.eagle.programmar.Natural.Terminals.Natural_Literal;
import com.eagle.programmar.Natural.Terminals.Natural_Number;
import com.eagle.programmar.Natural.Terminals.Natural_Tab;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSlash;
import com.eagle.tokens.punctuation.PunctuationStar;

public class Natural_DisplayStatement extends TokenSequence
{
	public @DOC("sm/display.htm") Natural_Keyword DISPLAY = new Natural_Keyword("DISPLAY");
	public @OPT Natural_Keyword NOTITLE = new Natural_Keyword("NOTITLE");
	public @OPT Natural_DisplayFunctions displayFns;
	public TokenList<Natural_DisplayElement> displayElement;
	
	public static class Natural_DisplayFunctions extends TokenSequence
	{
		public @OPT Natural_Keyword AND = new Natural_Keyword("AND");
		public @OPT Natural_Keyword GIVE = new Natural_Keyword("GIVE");
		public @OPT Natural_Keyword SYSTEM = new Natural_Keyword("SYSTEM");
		public Natural_Keyword FUNCTIONS = new Natural_Keyword("FUNCTIONS");
	}
	
	public static class Natural_DisplayElement extends TokenChooser
	{
		public @CHOICE Natural_Option displayOption;
		public @CHOICE PunctuationSlash slash;
		public @CHOICE Natural_Tab tab;
		public @CHOICE Natural_Variable var;
		public @CHOICE Natural_Comment comment;
		public @CHOICE Natural_SystemVariable sysVar;
		public @CHOICE Natural_KeywordChoice option = new Natural_KeywordChoice("HORIZ", "UNDERLINED", "TRAILER");
		public @CHOICE Natural_FunctionCall fnCall;
		public @CHOICE Natural_DisplayParameter parameter;
		
		public @CHOICE static class Natural_Justified extends TokenSequence
		{
			public Natural_Keyword LEFT = new Natural_Keyword("LEFT");
			public @OPT Natural_Keyword JUSTIFIED = new Natural_Keyword("JUSTIFIED");
		}

		public @CHOICE static class Natural_FieldPositioning extends TokenSequence
		{
			public Natural_Keyword T = new Natural_Keyword("T");
			public PunctuationStar star;
			public Natural_Variable var;
		}
		
		public @CHOICE static class Natural_FieldAndLinePositioning extends TokenSequence
		{
			public Natural_Keyword P = new Natural_Keyword("P");
			public PunctuationStar star;
			public Natural_Variable var;
		}
		
		public @CHOICE static class Natural_Vertical extends TokenSequence
		{
			public Natural_Keyword VERT = new Natural_Keyword("VERT");
			public @OPT Natural_Keyword AS = new Natural_Keyword("AS");
			public Natural_Literal literal;
		}
		
		public @CHOICE static class Natural_VerticalCaptioned extends TokenSequence
		{
			public Natural_Keyword VERT = new Natural_Keyword("VERT");
			public @OPT Natural_Keyword AS = new Natural_Keyword("AS");
			public Natural_Keyword CAPTIONED = new Natural_Keyword("CAPTIONED");
		}
		
		public @CHOICE static class Natural_Display_Literal extends TokenSequence
		{
			public Natural_Literal literal;
			public @OPT Natural_LiteralCount count;
			
			public static class Natural_LiteralCount extends TokenSequence
			{
				public PunctuationLeftParen leftParen;
				public Natural_Number count;
				public PunctuationRightParen rightParen;
			}
		}
		
		public @CHOICE static class Natural_Relative_Positioning extends TokenSequence
		{
			public Natural_Number lines;
			public PunctuationSlash slash;
			public Natural_Number column;
		}
	}
	
	public static class Natural_DisplayParameter extends TokenSequence
	{
		public PunctuationLeftParen leftParen;
		public Natural_DisplayParameterContents contents;
		public PunctuationRightParen rightParen;
	}
	
	public static class Natural_DisplayParameterContents extends TokenChooser
	{
		public @CHOICE Natural_DisplayParametersAD parameterAD;
		public @CHOICE Natural_DisplayParametersCD parameterCD;

		public @CHOICE static class NaturalDisplayParameterFieldRepresentation extends TokenSequence
		{
			public Natural_Keyword AD = new Natural_Keyword("AD");
			public PunctuationEquals equals;
			public Natural_DisplayParametersAD parameters;
		}

		public @CHOICE static class NaturalDisplayParameterColorDefinition extends TokenSequence
		{
			public Natural_Keyword CD = new Natural_Keyword("CD");
			public PunctuationEquals equals;
			public Natural_DisplayParametersCD parameters;
		}

		public @CHOICE static class NaturalDisplayParameterPrintMode extends TokenSequence
		{
			public Natural_Keyword PM = new Natural_Keyword("PM");
			public PunctuationEquals equals;
			public Natural_DisplayParametersPM parameters;
		}
	}
	
	public static class Natural_DisplayParametersAD extends TokenSequence
	{
		public Natural_KeywordChoice param = new Natural_KeywordChoice(
				"B", "C", "D", "I", "N", "U", "V");
	}
	
	public static class Natural_DisplayParametersCD extends TokenSequence
	{
		public Natural_KeywordChoice param = new Natural_KeywordChoice(
				"BL", "GR", "NE", "PI", "RE", "TU", "YE");
	}

	public static class Natural_DisplayParametersPM extends TokenSequence
	{
		public Natural_KeywordChoice param = new Natural_KeywordChoice(
				"C", "D", "I", "N");
	}
}
