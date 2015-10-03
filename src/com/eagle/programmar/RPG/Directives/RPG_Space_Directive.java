// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.RPG.Directives;

import com.eagle.programmar.RPG.Terminals.RPG_Keyword;
import com.eagle.programmar.RPG.Terminals.RPG_Number;
import com.eagle.tokens.TokenSequence;

public class RPG_Space_Directive extends TokenSequence
{
	public RPG_Keyword SPACE = new RPG_Keyword(7, 12, "/SPACE");
	public RPG_Number lines = new RPG_Number(14, 16);
}
