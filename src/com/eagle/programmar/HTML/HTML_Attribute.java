// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2011

package com.eagle.programmar.HTML;

import com.eagle.programmar.HTML.Terminals.HTML_HexNumber;
import com.eagle.programmar.HTML.Terminals.HTML_Identifier;
import com.eagle.programmar.HTML.Terminals.HTML_Keyword;
import com.eagle.programmar.HTML.Terminals.HTML_Literal;
import com.eagle.programmar.HTML.Terminals.HTML_Number;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.programmar.HTML.Terminals.HTML_PunctuationChoice;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class HTML_Attribute extends TokenSequence
{
	public HTML_IdentifierOrKeyword attribute;
	public @OPT HTML_AttributeValue val;
	
	public static class HTML_AttributeValue extends TokenSequence
	{
		public @NOSPACE PunctuationEquals equals;
		public @NOSPACE HTML_Value value;
	}
	
	public static class HTML_IdentifierOrKeyword extends TokenChooser
	{
		public @CHOICE HTML_Identifier attribute;
		public @CHOICE HTML_Keyword style = new HTML_Keyword("style");
		
		public @CHOICE static class HTML_Namespace extends TokenSequence
		{
			public HTML_Identifier id1;
			public PunctuationColon colon;
			public HTML_Identifier id2;
		}
	}
	
	public static class HTML_Value extends TokenChooser
	{
		public @CHOICE HTML_HexNumber hex;
		public @CHOICE HTML_Number number;
		public @CHOICE HTML_Literal literal;
		
		public @CHOICE static class HTML_Id_Value extends TokenSequence
		{
			public HTML_Identifier id;
			public @OPT HTML_Id_DotValue dotValue;
			
			public static class HTML_Id_DotValue extends TokenSequence
			{
				public HTML_PunctuationChoice dotOrColon = new HTML_PunctuationChoice(".", ":");
				public HTML_Identifier id;
			}
		}
		
		public @CHOICE static class HTML_Label extends TokenSequence
		{
			public HTML_Punctuation poundSign = new HTML_Punctuation('#');
			public HTML_Identifier label;
		}
		
		public @CHOICE static class HTML_Strange_Number extends TokenSequence
		{
			public HTML_Punctuation plus = new HTML_Punctuation('+');
			public PunctuationPeriod point;
			public HTML_Number number;
		}
	}
}
