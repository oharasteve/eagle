// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 29, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Natural_Statement;
import com.eagle.programmar.Natural.Natural_Subscript;
import com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Natural_SortStatement extends TokenSequence
{
	public @DOC("sm/sort.htm") Natural_Keyword SORT = new Natural_Keyword("SORT");
	public @OPT Natural_KeywordChoice THEM_RECORDS = new Natural_KeywordChoice(
			"THEM", "RECORDS");
	public @OPT Natural_Keyword BY = new Natural_Keyword("BY");
	public TokenList<Natural_SortBy> sortBy;
	public Natural_SortUsing using;
	public TokenList<Natural_Statement> statements;
	public @OPT Natural_Keyword ENDSORT = new Natural_Keyword("END-SORT");
	
	public static class Natural_SortBy extends TokenSequence
	{
		public Natural_Identifier_Reference id;
		public @OPT Natural_KeywordChoice ASCDESC = new Natural_KeywordChoice(
				"ASC", "DESC", "ASCENDING", "DESCENDING");
	}
	
	public static class Natural_SortUsing extends TokenSequence
	{
		public Natural_Keyword USING = new Natural_Keyword("USING");
		public @OPT Natural_Keyword KEYS = new Natural_Keyword("KEYS");
		public @OPT TokenList<Natural_SortUsingVar> using;
		
		public static class Natural_SortUsingVar extends TokenSequence
		{
			public Natural_Identifier_Reference id;
			public @OPT Natural_Subscript subscript;
		}
	}
}
