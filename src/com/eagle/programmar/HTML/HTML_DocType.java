// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Nov 23, 2014

package com.eagle.programmar.HTML;

import com.eagle.programmar.HTML.HTML_Attribute.HTML_Value;
import com.eagle.programmar.HTML.Terminals.HTML_Comment;
import com.eagle.programmar.HTML.Terminals.HTML_KeywordChoice;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.programmar.HTML.Terminals.HTML_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class HTML_DocType extends TokenSequence
{
	static String[] SUFFIXES = new String[] { "+", "?", "*" };
	
	public HTML_Punctuation startTag = new HTML_Punctuation("<!");
	public @NOSPACE HTML_KeywordChoice DOCTYPE = new HTML_KeywordChoice(
			"ATTLIST",
			"DOCTYPE",
			"ELEMENT",
			"ENTITY",
			"NOTATION");
	public TokenList<HTML_DocValue> values; 
	public @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
	
	public static class HTML_DocValue extends TokenChooser
	{
		public HTML_Value value;
		public HTML_DocType docType;
		public HTML_Comment comment;
		
		public static class HTML_DocBrackets extends TokenSequence
		{
			public HTML_Punctuation leftBracket = new HTML_Punctuation('[');
			public TokenList<HTML_DocValue> values; 
			public HTML_Punctuation rightBracket = new HTML_Punctuation(']');
		}
		
		public static class HTML_DocParens extends TokenSequence
		{
			public HTML_Punctuation leftParen = new HTML_Punctuation('(');
			public HTML_DocValue value;
			public @OPT HTML_PunctuationChoice suffix = new HTML_PunctuationChoice(SUFFIXES);
			public @OPT TokenList<HTML_DocMoreValues> more;
			public HTML_Punctuation rightParen = new HTML_Punctuation(')');
			public @OPT HTML_Punctuation plus = new HTML_Punctuation('+');
			
			public static class HTML_DocMoreValues extends TokenSequence
			{
				public HTML_PunctuationChoice commaOrBar = new HTML_PunctuationChoice(",", "|");
				public HTML_DocValue value;
				public @OPT HTML_PunctuationChoice suffix = new HTML_PunctuationChoice(SUFFIXES);
			}
		}
	}
}