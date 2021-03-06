// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 7, 2011

package com.eagle.programmar.Natural;

import com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference;
import com.eagle.programmar.Natural.Terminals.Natural_EditMask;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice;
import com.eagle.programmar.Natural.Terminals.Natural_Literal;
import com.eagle.programmar.Natural.Terminals.Natural_Number;
import com.eagle.programmar.Natural.Terminals.Natural_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Natural_Option extends TokenSequence
{
	public PunctuationLeftParen leftParen;
	public TokenList<Natural_OptionChoice> options;
	public PunctuationRightParen rightParen;


	public static class Natural_OptionChoice extends TokenChooser
	{
		public @CHOICE static class Natural_OptionAD extends TokenSequence
		{
			public Natural_Keyword AD = new Natural_Keyword("AD");
			public PunctuationEquals equals;
			public Natural_Identifier_Reference attributeDefinition;
		}

		public @CHOICE static class Natural_OptionAL extends TokenSequence
		{
			public Natural_Keyword AL = new Natural_Keyword("AL");
			public PunctuationEquals equals;
			public Natural_Number alphanumericLength;
		}

		public @CHOICE static class Natural_OptionDF extends TokenSequence
		{
			public Natural_Keyword DF = new Natural_Keyword("DF");
			public PunctuationEquals equals;
			public Natural_KeywordChoice dateFormat = new Natural_KeywordChoice("L");
		}

		public @CHOICE static class Natural_OptionEM extends TokenSequence
		{
			public Natural_Keyword EM = new Natural_Keyword("EM");
			public PunctuationEquals equals;
			public Natural_EditMask editMask;
		}

		public @CHOICE static class Natural_OptionES extends TokenSequence
		{
			public Natural_Keyword ES = new Natural_Keyword("ES");
			public PunctuationEquals equals;
			public Natural_KeywordChoice emptyLineSuppression = new Natural_KeywordChoice("OFF");
		}

		public @CHOICE static class Natural_OptionFC extends TokenSequence
		{
			public Natural_Keyword FC = new Natural_Keyword("FC");
			public PunctuationEquals equals;
			public Natural_PunctuationChoice fc = new Natural_PunctuationChoice("*", "-");
		}

		public @CHOICE static class Natural_OptionGC extends TokenSequence
		{
			public Natural_Keyword GC = new Natural_Keyword("GC");
			public PunctuationEquals equals;
			public Natural_PunctuationChoice gc = new Natural_PunctuationChoice("+", "=");
		}

		public @CHOICE static class Natural_OptionHC extends TokenSequence
		{
			public Natural_Keyword HC = new Natural_Keyword("HC");
			public PunctuationEquals equals;
			public Natural_KeywordChoice hc = new Natural_KeywordChoice("L");
		}

		public @CHOICE static class Natural_OptionIC extends TokenSequence
		{
			public Natural_Keyword IC = new Natural_Keyword("IC");
			public PunctuationEquals equals;
			public Natural_Literal ip;
		}
		
		public @CHOICE static class Natural_OptionIP extends TokenSequence
		{
			public Natural_Keyword IP = new Natural_Keyword("IP");
			public PunctuationEquals equals;
			public Natural_KeywordChoice identicalSuppress = new Natural_KeywordChoice("OFF");
		}

		public @CHOICE static class Natural_OptionIS extends TokenSequence
		{
			public Natural_Keyword IS = new Natural_Keyword("IS");
			public PunctuationEquals equals;
			public Natural_KeywordChoice identicalSuppress = new Natural_KeywordChoice("ON", "OFF");
		}

		public @CHOICE static class Natural_OptionLC1 extends TokenSequence
		{
			public Natural_Keyword LC = new Natural_Keyword("LC");
			public PunctuationEquals equals;
			public Natural_Literal lc;
		}

		public @CHOICE static class Natural_OptionLC2 extends TokenSequence
		{
			public Natural_Keyword LC = new Natural_Keyword("LC");
			public PunctuationEquals equals;
			public Natural_PunctuationChoice lc = new Natural_PunctuationChoice("<");
		}
		
		public @CHOICE static class Natural_OptionLC3 extends TokenSequence
		{
			public Natural_Keyword LC = new Natural_Keyword("LC");
			public PunctuationEquals equals;
			public Natural_KeywordChoice lc = new Natural_KeywordChoice("USD");
		}
		
		public @CHOICE static class Natural_OptionNL extends TokenSequence
		{
			public Natural_Keyword NL = new Natural_Keyword("NL");
			public PunctuationEquals equals;
			public Natural_Number numericLength;
		}

		public @CHOICE static class Natural_OptionPS extends TokenSequence
		{
			public Natural_Keyword PS = new Natural_Keyword("PS");
			public PunctuationEquals equals;
			public Natural_Number pageSize;
		}

		public @CHOICE static class Natural_OptionSF extends TokenSequence
		{
			public Natural_Keyword SF = new Natural_Keyword("SF");
			public PunctuationEquals equals;
			public Natural_Number sf;
		}

		public @CHOICE static class Natural_OptionTC1 extends TokenSequence
		{
			public Natural_Keyword TC = new Natural_Keyword("TC");
			public PunctuationEquals equals;
			public Natural_Literal tc;
		}
		
		public @CHOICE static class Natural_OptionTC2 extends TokenSequence
		{
			public Natural_Keyword TC = new Natural_Keyword("TC");
			public PunctuationEquals equals;
			public Natural_PunctuationChoice tc = new Natural_PunctuationChoice(">");
		}
		
		public @CHOICE static class Natural_OptionTC3 extends TokenSequence
		{
			public Natural_Keyword TC = new Natural_Keyword("TC");
			public PunctuationEquals equals;
			public Natural_EditMask tc;
		}
		
		public @CHOICE static class Natural_OptionUC extends TokenSequence
		{
			public Natural_Keyword UC = new Natural_Keyword("UC");
			public PunctuationEquals equals;
			public Natural_PunctuationChoice underlineCharacter = new Natural_PunctuationChoice("=", "%", "-");
		}

		public @CHOICE static class Natural_OptionZP extends TokenSequence
		{
			public Natural_Keyword ZP = new Natural_Keyword("ZP");
			public PunctuationEquals equals;
			public Natural_KeywordChoice zeroPrinting = new Natural_KeywordChoice("ON");
		}
	}
}