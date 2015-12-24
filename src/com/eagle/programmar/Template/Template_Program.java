// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Template;

import com.eagle.programmar.EagleLanguage;
import com.eagle.tests.EagleInterpreter;
import com.eagle.tests.EagleRunnable;
import com.eagle.tokens.TokenList;

public class Template_Program extends EagleLanguage implements EagleRunnable
{
	public static final String NAME = "Template";
	
	public Template_Program()
	{
		super(NAME, new Template_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "TBD";
	}
	
	public @OPT TokenList<Template_Statement> statements;

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		if (statements._present)
		{
			for (Template_Statement stmt : statements._elements)
			{
				interpreter.tryToInterpret(stmt);
			}
		}
	}
}