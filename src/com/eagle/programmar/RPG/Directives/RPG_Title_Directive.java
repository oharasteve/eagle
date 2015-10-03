// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.RPG.Directives;

import com.eagle.programmar.RPG.Terminals.RPG_Keyword;
import com.eagle.programmar.RPG.Terminals.RPG_Literal;
import com.eagle.tokens.TokenSequence;

public class RPG_Title_Directive extends TokenSequence
{
	public RPG_Keyword TITLE = new RPG_Keyword(7, 12, "/TITLE");
	public RPG_Literal ttl = new RPG_Literal(14, 74);
}
