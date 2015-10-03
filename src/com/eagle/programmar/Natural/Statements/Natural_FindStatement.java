// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 8, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Natural_Condition;
import com.eagle.programmar.Natural.Natural_Label;
import com.eagle.programmar.Natural.Natural_Statement;
import com.eagle.programmar.Natural.Natural_Variable;
import com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice;
import com.eagle.programmar.Natural.Terminals.Natural_Literal;
import com.eagle.programmar.Natural.Terminals.Natural_Number;
import com.eagle.programmar.Natural.Terminals.Natural_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Natural_FindStatement extends TokenSequence
{
	public @OPT Natural_Label label;
	public @DOC("sm/find.htm") Natural_Keyword FIND = new Natural_Keyword("FIND");
	public Natural_FindType findType;
	
	public static class Natural_FindType extends TokenChooser
	{
		public static class Natural_FindNoBlock extends TokenSequence
		{
			public Natural_KeywordChoice howMany = new Natural_KeywordChoice(
					"FIRST", "NUMBER", "UNIQUE");
			public @OPT Natural_Find_Number_Records numberRecords;
			public Natural_Identifier_Reference viewName;
			public @OPT Natural_KeywordChoice device = new Natural_KeywordChoice(
					"PHYSICAL", "LOGICAL");
			public TokenList<Natural_Find_Clause> clauses;
		}

		public static class Natural_FindWithBlock extends TokenSequence
		{
			public @OPT Natural_Find_Number_Records numberRecords;
			public Natural_Identifier_Reference viewName;
			public @OPT Natural_KeywordChoice device = new Natural_KeywordChoice(
					"PHYSICAL", "LOGICAL");
			public TokenList<Natural_Find_Clause> clauses;
			public TokenList<Natural_Statement> statements;
			public Natural_Keyword ENDFIND = new Natural_Keyword("END-FIND");
		}
	}
	
	public static class Natural_Find_Clause extends TokenChooser
	{
		public Natural_Find_By_Condition findByCond;
		public Natural_Find_By_ISN findByISN;
		public Natural_Find_From findFrom;
		public Natural_Find_Coupled coupled;
		public Natural_Find_Sorted_By sortedBy;
		public Natural_Find_Where findWhere;
	}
	
	public static class Natural_Find_Number_Records extends TokenSequence
	{
		public Natural_Punctuation leftParen = new Natural_Punctuation('(');
		public Natural_Number number;
		public Natural_Punctuation rightParen = new Natural_Punctuation(')');
	}
	
	public static class Natural_Find_By_ISN extends TokenSequence
	{
		public Natural_Keyword BY = new Natural_Keyword("BY");
		public Natural_Identifier_Reference isn;
	}
	
	public static class Natural_Find_Coupled extends TokenSequence
	{
		public Natural_KeywordChoice andOr = new Natural_KeywordChoice(
				"AND", "OR");
		public Natural_Keyword COUPLED = new Natural_Keyword("COUPLED");
		public @OPT Natural_Keyword TO = new Natural_Keyword("TO");
		public @OPT Natural_Keyword FILE = new Natural_Keyword("FILE");
		public Natural_Identifier_Reference viewName;
		public @OPT Natural_Find_Coupled_Via via;
		
		public static class Natural_Find_Coupled_Via extends TokenSequence
		{
			public Natural_Keyword VIA = new Natural_Keyword("VIA");
			public Natural_Identifier_Reference var1;
			public Natural_Find_Via_Equals equals;
			public @OPT Natural_Keyword TO = new Natural_Keyword("TO");
			public Natural_Identifier_Reference var2;
			
			public static class Natural_Find_Via_Equals extends TokenChooser
			{
				public Natural_Punctuation equals = new Natural_Punctuation('=');
				public Natural_KeywordChoice EQUALS = new Natural_KeywordChoice(
						"EQ", "EQUAL");
			}
		}
	}
	
	public static class Natural_Find_Sorted_By extends TokenSequence
	{
		public @OPT Natural_Keyword SORTED = new Natural_Keyword("SORTED");
		public Natural_Keyword BY = new Natural_Keyword("BY");
		public TokenList<Natural_Variable> vars;
	}

	public static class Natural_Find_By_Condition extends TokenSequence
	{
		public Natural_KeywordChoice byWith = new Natural_KeywordChoice(
				"BY", "WITH");
		public @OPT Natural_Find_With_Limit findWithLimit;
		public Natural_Condition cond;
		public @OPT Natural_Find_Retain retain;
		
		public static class Natural_Find_With_Limit extends TokenSequence
		{
			public Natural_Keyword LIMIT = new Natural_Keyword("LIMIT");
			public Natural_Find_Number_Records limit;
		}
		
		public static class Natural_Find_Retain extends TokenSequence
		{
			public Natural_Keyword RETAIN = new Natural_Keyword("RETAIN");
			public Natural_Keyword AS = new Natural_Keyword("AS");
			public Natural_Literal literal;
		}
	}
	
	public static class Natural_Find_From extends TokenSequence
	{
		public @OPT Natural_Keyword STARTING = new Natural_Keyword("STARTING");
		public Natural_Keyword FROM = new Natural_Keyword("FROM");
		public Natural_Literal literal;
	}
	
	public static class Natural_Find_Where extends TokenSequence
	{
		public Natural_Keyword WHERE = new Natural_Keyword("WHERE");
		public Natural_Condition cond;
	}
}
