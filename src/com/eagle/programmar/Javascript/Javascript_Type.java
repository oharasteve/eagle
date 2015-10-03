// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.Javascript;

import com.eagle.programmar.Javascript.Terminals.Javascript_KeywordChoice;
import com.eagle.tokens.TokenChooser;

public class Javascript_Type extends TokenChooser
{
	public Javascript_KeywordChoice buitinType = new Javascript_KeywordChoice(
			"var",
			"Array",
			"Date",
			"Image",
			"String");
	public Javascript_Variable userType;
}
