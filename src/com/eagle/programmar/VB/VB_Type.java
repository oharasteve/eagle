// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 16, 2011

package com.eagle.programmar.VB;

import com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
import com.eagle.tokens.TokenChooser;

public class VB_Type extends TokenChooser
{
	public VB_KeywordChoice base = new VB_KeywordChoice(
			"boolean", "integer", "long", "range", "string", "worksheet");
}
