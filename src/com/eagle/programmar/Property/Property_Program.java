// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 29, 2014

package com.eagle.programmar.Property;

import com.eagle.programmar.EagleLanguage;
import com.eagle.programmar.Property.Terminals.Property_Comment;
import com.eagle.programmar.Property.Terminals.Property_EndOfLine;
import com.eagle.programmar.Property.Terminals.Property_Identifier;
import com.eagle.programmar.Property.Terminals.Property_Punctuation;
import com.eagle.programmar.Property.Terminals.Property_RestOfLine;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Property_Program extends EagleLanguage
{
	public static final String NAME = "Property";
	
	public Property_Program()
	{
		super(NAME, new Property_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "http://www.w3schools.com/json/";
	}
	
	public TokenList<Property_Element> elements;
	
	public static class Property_Element extends TokenChooser
	{
		public Property_Comment comment;
		public Property_Value pair;
		public Property_EndOfLine eoln;
	}
	
	public static class Property_Value extends TokenSequence
	{
		public @OPT Property_Identifier id;
		public @OPT TokenList<Property_MoreIds> more;
		public Property_Punctuation equals = new Property_Punctuation('=');
		public Property_RestOfLine value;
		
		public static class Property_MoreIds extends TokenSequence
		{
			public Property_Punctuation dot = new Property_Punctuation('.');
			public Property_Identifier id;
		}
	}
}