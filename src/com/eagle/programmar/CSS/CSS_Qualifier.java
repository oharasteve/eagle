// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 28, 2014

package com.eagle.programmar.CSS;

import com.eagle.programmar.CSS.Terminals.CSS_Keyword;
import com.eagle.programmar.CSS.Terminals.CSS_KeywordChoice;
import com.eagle.programmar.CSS.Terminals.CSS_Literal;
import com.eagle.programmar.CSS.Terminals.CSS_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class CSS_Qualifier extends TokenSequence
{
	public CSS_Punctuation leftBracket = new CSS_Punctuation('[');
	public CSS_QualifierChoice qual;
	public CSS_Punctuation rightBracket = new CSS_Punctuation(']');
	public @OPT CSS_Punctuation greaterThan = new CSS_Punctuation('>');

	public static class CSS_QualifierChoice extends TokenChooser
	{
		public CSS_KeywordChoice choice = new CSS_KeywordChoice(
				"has-element-focus",
				"multiple",
				"readonly",
				"selected",
				"size",
				"subframe");
		
		public static class CSS_QualifierClass extends TokenSequence
		{
			public CSS_Keyword CLASS = new CSS_Keyword("class");
			public CSS_Punctuation star = new CSS_Punctuation('*');
			public CSS_Punctuation equals = new CSS_Punctuation('=');
			public CSS_Value value;
		}
		
		public static class CSS_QualiferCode extends TokenSequence
		{
			public CSS_KeywordChoice CODE = new CSS_KeywordChoice(
					"controls",
					"data-original-title",
					"disabled",
					"hidden",
					"href",
					"title");
			public @OPT CSS_BracketsHatEquals hatEquals;
			
			public static class CSS_BracketsHatEquals extends TokenSequence
			{
				public CSS_Punctuation hat = new CSS_Punctuation('^');
				public CSS_Punctuation equals = new CSS_Punctuation('=');
				public CSS_Literal literal;
			}
		}
		
		public static class CSS_QualifierDataToggle extends TokenSequence
		{
			public CSS_Keyword DATA_TOGGLE = new CSS_Keyword("data-toggle");
			public CSS_Punctuation equals = new CSS_Punctuation('=');
			public CSS_Value value;
		}
		
		public static class CSS_QualifierDir extends TokenSequence
		{
			public CSS_Keyword DIR = new CSS_Keyword("dir");
			public CSS_Punctuation equals = new CSS_Punctuation('=');
			public CSS_QualifierWhichDir which;
			
			public static class CSS_QualifierWhichDir extends TokenChooser
			{
				public CSS_KeywordChoice RTL = new CSS_KeywordChoice(
						"rtl");
				public CSS_Literal literal;
			}
		}

		public static class CSS_QualifierFrame extends TokenSequence
		{
			public CSS_KeywordChoice FRAME = new CSS_KeywordChoice(
					"aria-valuenow",
					"frame",
					"page");
			public CSS_Punctuation equals = new CSS_Punctuation('=');
			public CSS_Literal literal;
		}

		public static class CSS_QualifierHighlight extends TokenSequence
		{
			public CSS_Keyword HIGHLIGHT = new CSS_Keyword("highlight");
			public CSS_Punctuation equals = new CSS_Punctuation('=');
			public CSS_QualifierWhichHighlight which;
			
			public static class CSS_QualifierWhichHighlight extends TokenChooser
			{
				public CSS_KeywordChoice STRONG = new CSS_KeywordChoice(
						"strong");
				public CSS_Literal literal;
			}
		}

		public static class CSS_QualifierRole extends TokenSequence
		{
			public CSS_Keyword ROLE = new CSS_Keyword("role");
			public CSS_Punctuation equals = new CSS_Punctuation('=');
			public CSS_QualifierWhichRole which;
			
			public static class CSS_QualifierWhichRole extends TokenChooser
			{
				public CSS_KeywordChoice value = new CSS_KeywordChoice(
						"button",
						"number",
						"text");
				public CSS_Literal literal;
			}
		}
		
		public static class CSS_QualifierRow extends TokenSequence
		{
			public CSS_Keyword ROW = new CSS_Keyword("row$");
			public CSS_Punctuation equals = new CSS_Punctuation('=');
			public CSS_Literal literal;
		}

		public static class CSS_QualifierType extends TokenSequence
		{
			public CSS_Keyword TYPE = new CSS_Keyword("type");
			public CSS_Punctuation equals = new CSS_Punctuation('=');
			public CSS_QualifierWhichType which;
			
			public static class CSS_QualifierWhichType extends TokenChooser
			{
				public CSS_KeywordChoice value = new CSS_KeywordChoice(
						"button",
						"checkbox",
						"date",
						"datetime-local",
						"email",
						"file",
						"month",
						"number",
						"password",
						"radio",
						"range",
						"reset",
						"search",
						"submit",
						"time",
						"text");
				public CSS_Literal literal;
			}
		}
	}
}
