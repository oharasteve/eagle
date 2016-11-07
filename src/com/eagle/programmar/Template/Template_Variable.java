// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 13, 2015

package com.eagle.programmar.Template;

import com.eagle.programmar.Template.Symbols.Template_Identifier_Reference;
import com.eagle.tokens.TokenChooser;

public class Template_Variable extends TokenChooser
{
	public @CHOICE Template_Identifier_Reference id;
}
