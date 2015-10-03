// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 15, 2014

package com.eagle.programmar.Django;

import com.eagle.programmar.HTML.HTML_Program.HTML_Element;
import com.eagle.programmar.HTML.HTML_Syntax;
import com.eagle.programmar.HTML.HTML_Table.HTML_TableRow;
import com.eagle.tokens.TokenChooser;

public class Django_Element extends TokenChooser
{
	public @SYNTAX(HTML_Syntax.class) HTML_TableRow tableRow;
	public @SYNTAX(HTML_Syntax.class) HTML_Element element;
}
