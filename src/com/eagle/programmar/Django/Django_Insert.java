// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 15, 2014

package com.eagle.programmar.Django;

import com.eagle.programmar.Django.Terminals.Django_Keyword;
import com.eagle.programmar.Django.Terminals.Django_KeywordChoice;
import com.eagle.programmar.Django.Terminals.Django_Literal;
import com.eagle.programmar.Django.Terminals.Django_Number;
import com.eagle.programmar.Django.Terminals.Django_Punctuation;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Django_Insert extends TokenSequence
{
	public HTML_Punctuation startBraceBrace = new HTML_Punctuation("{{");
	public Django_InsertWhat what;
	public HTML_Punctuation endBraceBrace = new HTML_Punctuation("}}");
	
	public static class Django_InsertWhat extends TokenChooser
	{
		public @CHOICE static class Django_InsertSuper extends TokenSequence
		{
			public Django_Keyword SUPER = new Django_Keyword("super");
			public @OPT Django_InsertSuperArgs args;

			public static class Django_InsertSuperArgs extends TokenSequence
			{
				public PunctuationLeftParen leftParen;
				public PunctuationRightParen rightParen;
			}
		}

		public @CHOICE static class Django_InsertVariable extends TokenSequence
		{
			public Django_Variable variable;
			public @OPT Django_InsertDot insertDot;
			public @OPT Django_OrWhat what;
		}
	}
	
	public static class Django_InsertDot extends TokenSequence
	{
		public PunctuationPeriod dot;
		public Django_Number number;
	}

	public static class Django_OrWhat extends TokenSequence
	{
		public Django_Punctuation verticalBar = new Django_Punctuation('|');
		public Django_KeywordChoice DATE = new Django_KeywordChoice(
				"capfirst",
				"date",
				"escape",
				"safe"
		);
		public @OPT PunctuationColon colon;
		public @OPT Django_Literal literal;
	}
}
