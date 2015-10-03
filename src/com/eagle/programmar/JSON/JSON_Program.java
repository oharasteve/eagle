// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2014

package com.eagle.programmar.JSON;

import com.eagle.programmar.EagleLanguage;
import com.eagle.programmar.JSON.Terminals.JSON_KeywordChoice;
import com.eagle.programmar.JSON.Terminals.JSON_Literal;
import com.eagle.programmar.JSON.Terminals.JSON_Number;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class JSON_Program extends EagleLanguage
{
	public static final String NAME = "JSON";
	
	public JSON_Program()
	{
		super(NAME, new JSON_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "http://www.w3schools.com/json/";
	}
	
	public TokenList<JSON_Element> elements;
	
	public static class JSON_Element extends TokenChooser
	{
		public JSON_Literal literal;
		public JSON_Number number;
		public JSON_Object object;
		public JSON_Dictionary dictionary;
		public JSON_KeywordChoice builtIn = new JSON_KeywordChoice("null", "true", "false");
	}
}