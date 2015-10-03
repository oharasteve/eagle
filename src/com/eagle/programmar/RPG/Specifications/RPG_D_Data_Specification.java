// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 15, 2011

package com.eagle.programmar.RPG.Specifications;

import com.eagle.programmar.RPG.Terminals.RPG_Blanks;
import com.eagle.programmar.RPG.Terminals.RPG_Keyword;
import com.eagle.programmar.RPG.Terminals.RPG_KeywordChoice;
import com.eagle.programmar.RPG.Terminals.RPG_Literal;
import com.eagle.programmar.RPG.Terminals.RPG_Number;
import com.eagle.tokens.TokenSequence;

public abstract class RPG_D_Data_Specification extends TokenSequence
{
	public RPG_Keyword D = new RPG_Keyword(6, 6, "D");
	public @OPT RPG_Literal name;
	public @OPT RPG_Keyword externalDescription;
	public @OPT RPG_KeywordChoice entryType;
	public @OPT RPG_KeywordChoice definitionType;
	public @OPT RPG_Number fromPosition;
	public @OPT RPG_Number toPositionOrLength;
	public @OPT RPG_KeywordChoice dataType;
	public @OPT RPG_Number decimalPosition;
	public RPG_Blanks blank;
	public @OPT RPG_Literal keywords;

	public static class RPG_D_Data_Specification_III extends RPG_D_Data_Specification
	{
		// Not available in RPG III
	}
	
	public static class RPG_D_Data_Specification_IV extends RPG_D_Data_Specification
	{
		public RPG_D_Data_Specification_IV()
		{
			name = new RPG_Literal(7, 21);
			externalDescription = new RPG_Keyword(22, 22, "E");
			entryType = new RPG_KeywordChoice(23, 23, "S", "U");
			definitionType = new RPG_KeywordChoice(24, 25,
					"C", "DS", "PI", "PR", "S");
			fromPosition = new RPG_Number(26, 32);
			toPositionOrLength = new RPG_Number(33, 39);
			dataType = new RPG_KeywordChoice(40, 40,
					"A", "B", "C", "D", "F", "G", "I", "N", "O", "P", "S", "T", "U", "Z");
			decimalPosition = new RPG_Number(41, 42);
			blank = new RPG_Blanks(43, 43);
			keywords = new RPG_Literal(44, 80);
		}
	}
}
