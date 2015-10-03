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

public class HTML_Attribute extends TokenSequence
{
	public HTML_IdentifierOrKeyword attribute;
	public @OPT HTML_AttributeValue val;
	
	public static class HTML_AttributeValue extends TokenSequence
	{
		public @NOSPACE HTML_Punctuation equals = new HTML_Punctuation('=');
		public @NOSPACE HTML_Value value;
	}
	
	public static class HTML_IdentifierOrKeyword extends TokenChooser
	{
		public HTML_Identifier attribute;
		public HTML_Keyword style = new HTML_Keyword("style");
		
		public static class HTML_Namespace extends TokenSequence
		{
			public HTML_Identifier id1;
			public HTML_Punctuation colon = new HTML_Punctuation(':');
			public HTML_Identifier id2;
		}
	}
	
	public static class HTML_Value extends TokenChooser
	{
		public HTML_HexNumber hex;
		public HTML_Number number;
		public HTML_Literal literal;
		
		public static class HTML_Id_Value extends TokenSequence
		{
			public HTML_Identifier id;
			public @OPT HTML_Id_DotValue dotValue;
			
			public static class HTML_Id_DotValue extends TokenSequence
			{
				public HTML_PunctuationChoice dotOrColon = new HTML_PunctuationChoice(".", ":");
				public HTML_Identifier id;
			}
		}
		
		public static class HTML_Label extends TokenSequence
		{
			public HTML_Punctuation poundSign = new HTML_Punctuation('#');
			public HTML_Identifier label;
		}
		
		public static class HTML_Strange_Number extends TokenSequence
		{
			public HTML_Punctuation plus = new HTML_Punctuation('+');
			public HTML_Punctuation point = new HTML_Punctuation('.');
			public HTML_Number number;
		}
	}
}
