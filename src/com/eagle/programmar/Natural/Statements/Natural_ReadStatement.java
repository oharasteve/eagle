// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 3, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Natural_Condition;
import com.eagle.programmar.Natural.Natural_Expression;
import com.eagle.programmar.Natural.Natural_Label;
import com.eagle.programmar.Natural.Natural_Statement;
import com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice;
import com.eagle.programmar.Natural.Terminals.Natural_Number;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Natural_ReadStatement extends TokenSequence
{
	public @OPT Natural_Label label;
	public @DOC("sm/read.htm") Natural_Keyword READ = new Natural_Keyword("READ");
	public @OPT Natural_Read_Number_Records numberRecords;
	public Natural_Identifier_Reference viewName;
	public @OPT Natural_PhysicalLogical physicalLogical;
	public @OPT TokenList<Natural_ReadClause> clauses;
	public @OPT TokenList<Natural_Statement> statements;
	public @OPT Natural_KeywordChoice endRead = new Natural_KeywordChoice(
			"END-READ", "END-ALL");
	
	public static class Natural_ReadClause extends TokenChooser
	{
		public @CHOICE Natural_Read_By readBy;
		public @CHOICE Natural_Read_Starting readStarting;
		public @CHOICE Natural_Read_Where readWhere;
		public @CHOICE Natural_Read_Ending readEnding;
		public @CHOICE Natural_Read_With readWith;
	}
	
	public static class Natural_PhysicalLogical extends TokenSequence
	{
		public @OPT Natural_Keyword IN = new Natural_Keyword("IN");
		public Natural_KeywordChoice device = new Natural_KeywordChoice(
				"PHYSICAL", "LOGICAL");
		public @OPT Natural_Keyword SEQUENCE = new Natural_Keyword("SEQUENCE");
	}
	
	public static class Natural_Read_Number_Records extends TokenSequence
	{
		public PunctuationLeftParen leftParen;
		public Natural_Number number;
		public PunctuationRightParen rightParen;
	}
	
	public static class Natural_Read_By extends TokenSequence
	{
		public Natural_Keyword BY = new Natural_Keyword("BY");
		public Natural_Condition cond;
	}
	
	public static class Natural_Read_Starting extends TokenSequence
	{
		public @OPT Natural_Keyword STARTING = new Natural_Keyword("STARTING");
		public Natural_Keyword FROM = new Natural_Keyword("FROM");
		public Natural_Expression expr;
	}
	
	public static class Natural_Read_Ending extends TokenSequence
	{
		public Natural_Keyword ENDING = new Natural_Keyword("ENDING");
		public Natural_Keyword AT = new Natural_Keyword("AT");
		public Natural_Expression expr;
	}
	
	public static class Natural_Read_Where extends TokenSequence
	{
		public Natural_Keyword WHERE = new Natural_Keyword("WHERE");
		public Natural_Condition cond;
	}
	
	public static class Natural_Read_With extends TokenSequence
	{
		public Natural_Keyword WITH = new Natural_Keyword("WITH");
		public Natural_Condition cond;
	}
}
