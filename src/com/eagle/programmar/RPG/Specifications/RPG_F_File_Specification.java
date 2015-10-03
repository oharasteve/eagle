// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.RPG.Specifications;

import com.eagle.programmar.RPG.Terminals.RPG_Blanks;
import com.eagle.programmar.RPG.Terminals.RPG_Keyword;
import com.eagle.programmar.RPG.Terminals.RPG_KeywordChoice;
import com.eagle.programmar.RPG.Terminals.RPG_Literal;
import com.eagle.programmar.RPG.Terminals.RPG_Number;
import com.eagle.tokens.TokenSequence;

public abstract class RPG_F_File_Specification extends TokenSequence
{
	public RPG_Keyword F = new RPG_Keyword(6, 6, "F");
	public @OPT RPG_Literal fileName;
	public @OPT RPG_KeywordChoice fileType;
	public @OPT RPG_KeywordChoice fileDesignation;
	public @OPT RPG_Keyword endOfFile;
	public @OPT RPG_KeywordChoice sequence;
	public @OPT RPG_KeywordChoice fileFormat;
	public RPG_Blanks blank1;
	public @OPT RPG_Number recordLength;
	public @OPT RPG_Keyword limitsProcessing;
	public @OPT RPG_Number fieldLength;
	public @OPT RPG_KeywordChoice recordAddressType;
	public @OPT RPG_KeywordChoice fileOrganization;
	public @OPT RPG_Literal overflowIndicator;
	public @OPT RPG_Number keyStart;
	public @OPT RPG_KeywordChoice extensionCode;
	public @OPT RPG_KeywordChoice device;
	public RPG_Blanks blank2;
	public @OPT RPG_Keyword continuationLines;
	public @OPT RPG_Literal routine;
	public RPG_Blanks blank3;
	public @OPT RPG_Keyword fileAddition;
	public RPG_Blanks blank4;
	public @OPT RPG_Literal fileCondition;
	public RPG_Blanks blank5;
	
	public static class RPG_F_File_Specification_III extends RPG_F_File_Specification
	{
		public RPG_F_File_Specification_III()
		{
			fileName = new RPG_Literal(7, 14);
			fileType = new RPG_KeywordChoice(15, 15, "I", "O", "U", "C");
			fileDesignation = new RPG_KeywordChoice(16, 16, "P", "S", "R", "T", "F");
			endOfFile = new RPG_Keyword(17, 17, "E");
			sequence = new RPG_KeywordChoice(18, 18, "A", "D");
			fileFormat = new RPG_KeywordChoice(19, 19, "F", "E");
			blank1 = new RPG_Blanks(20, 23);
			recordLength = new RPG_Number(24, 27);
			limitsProcessing = new RPG_Keyword(28, 28, "L");
			fieldLength = new RPG_Number(29, 30);
			recordAddressType = new RPG_KeywordChoice(31, 31, "A", "P", "K");
			fileOrganization = new RPG_KeywordChoice(32, 32, "I", "T");
			overflowIndicator = new RPG_Literal(33, 34);
			keyStart = new RPG_Number(35, 38);
			extensionCode = new RPG_KeywordChoice(39, 39, "E", "L");
			device = new RPG_KeywordChoice(40, 46,
					"PRINTER", "DISK", "WORKSTN", "SPECIAL", "SEQ");
			blank2 = new RPG_Blanks(47, 52);
			continuationLines = new RPG_Keyword(53, 53, "K");
			routine = new RPG_Literal(54, 59);
			blank3 = new RPG_Blanks(60, 65);
			fileAddition = new RPG_Keyword(66, 66, "A");
			blank4 = new RPG_Blanks(67, 70);
			fileCondition = new RPG_Literal(71, 72);
			blank5 = new RPG_Blanks(73, 74);
		}
	}
	
	public static class RPG_F_File_Specification_IV extends RPG_F_File_Specification
	{
		public RPG_F_File_Specification_IV()
		{
			fileName = new RPG_Literal(7, 16);
			fileType = new RPG_KeywordChoice(17, 17, "I", "O", "U", "C");
			fileDesignation = new RPG_KeywordChoice(18, 18, "P", "S", "R", "T", "F");
			endOfFile = new RPG_Keyword(19, 19, "E");
			sequence = new RPG_KeywordChoice(21, 21, "A", "D");
			fileFormat = new RPG_KeywordChoice(22, 22, "F", "E");
			blank1 = new RPG_Blanks(0, 0);		// Not Applicable
			recordLength = new RPG_Number(23, 27);
			limitsProcessing = new RPG_Keyword(28, 28, "L");
			fieldLength = new RPG_Number(29, 33);
			recordAddressType = new RPG_KeywordChoice(34, 34, "A", "P", "K");
			fileOrganization = new RPG_KeywordChoice(35, 35, "I", "T");
			overflowIndicator = new RPG_Literal(0, 0);	// ??
			keyStart = new RPG_Number(0, 0);			// ??
			extensionCode = new RPG_KeywordChoice(0, 0, "E", "L");
			device = new RPG_KeywordChoice(36, 42,
					"PRINTER", "DISK", "WORKSTN", "SPECIAL", "SEQ");
			blank2 = new RPG_Blanks(43, 43);
			continuationLines = new RPG_Keyword(0, 0, "K");	// Not Applicable
			routine = new RPG_Literal(0, 0);		// ??
			blank3 = new RPG_Blanks(0, 0);		// Not Applicable
			fileAddition = new RPG_Keyword(20, 20, "A");
			blank4 = new RPG_Blanks(0, 0);	// ??
			fileCondition = new RPG_Literal(0, 0);	// ??
			blank5 = new RPG_Blanks(0, 0);	// ??
		}
	}
}
