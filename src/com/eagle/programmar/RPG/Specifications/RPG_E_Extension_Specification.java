// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.RPG.Specifications;

import com.eagle.programmar.RPG.Terminals.RPG_Blanks;
import com.eagle.programmar.RPG.Terminals.RPG_Keyword;
import com.eagle.programmar.RPG.Terminals.RPG_KeywordChoice;
import com.eagle.programmar.RPG.Terminals.RPG_Literal;
import com.eagle.programmar.RPG.Terminals.RPG_Number;
import com.eagle.tokens.TokenSequence;

public class RPG_E_Extension_Specification extends TokenSequence
{
	public RPG_Keyword E = new RPG_Keyword(6, 6, "E");
	public RPG_Blanks blank1 = new RPG_Blanks(7, 10);
	public @OPT RPG_Literal fromFileName = new RPG_Literal(11, 18);
	public @OPT RPG_Literal toFileName = new RPG_Literal(19, 26);

	public RPG_Literal arrayTable1 = new RPG_Literal(27, 32);
	public RPG_Number entries = new RPG_Number(36, 39);
	public RPG_Number length1 = new RPG_Number(40, 42);
	public @OPT RPG_KeywordChoice dataFormat1 = new RPG_KeywordChoice(43, 43, "P", "B", "L", "R");
	public @OPT RPG_Number positions1 = new RPG_Number(44, 44);
	public @OPT RPG_KeywordChoice sequence1 = new RPG_KeywordChoice(45, 45, "A", "D");

	public RPG_Literal arrayTable2 = new RPG_Literal(46, 51);
	public RPG_Number length2 = new RPG_Number(52, 54);
	public @OPT RPG_KeywordChoice dataFormat2 = new RPG_KeywordChoice(55, 55, "P", "B", "L", "R");
	public @OPT RPG_Number positions2 = new RPG_Number(56, 56);
	public @OPT RPG_KeywordChoice sequence2 = new RPG_KeywordChoice(57, 57, "A", "D");
	
	public @OPT RPG_Literal comments = new RPG_Literal(58, 74);
}
