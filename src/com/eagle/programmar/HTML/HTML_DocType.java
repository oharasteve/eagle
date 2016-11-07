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
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

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
		public @CHOICE HTML_Value value;
		public @CHOICE HTML_DocType docType;
		public @CHOICE HTML_Comment comment;
		
		public @CHOICE static class HTML_DocBrackets extends TokenSequence
		{
			public PunctuationLeftBracket leftBracket;
			public TokenList<HTML_DocValue> values; 
			public PunctuationRightBracket rightBracket;
		}
		
		public @CHOICE static class HTML_DocParens extends TokenSequence
		{
			public PunctuationLeftParen leftParen;
			public HTML_DocValue value;
			public @OPT HTML_PunctuationChoice suffix = new HTML_PunctuationChoice(SUFFIXES);
			public @OPT TokenList<HTML_DocMoreValues> more;
			public PunctuationRightParen rightParen;
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