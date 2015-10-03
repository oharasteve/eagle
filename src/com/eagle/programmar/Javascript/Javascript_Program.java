// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript;

import com.eagle.programmar.EagleLanguage;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class Javascript_Program extends EagleLanguage
{
	public static final String NAME = "Javascript";
	
	public Javascript_Program()
	{
		super(NAME, new Javascript_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "http://www.w3schools.com/js/";
	}
	
	public @OPT TokenList<Javascript_Element> elements;
	
	public static class Javascript_Element extends TokenChooser
	{
		public Javascript_Comment comment;
		public Javascript_Function function;
		public Javascript_Statement statement;
	}
}
