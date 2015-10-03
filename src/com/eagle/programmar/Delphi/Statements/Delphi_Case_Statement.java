// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 7, 2014

package com.eagle.programmar.Delphi.Statements;

import com.eagle.programmar.Delphi.Delphi_Expression;
import com.eagle.programmar.Delphi.Delphi_Statement;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Delphi_Case_Statement extends TokenSequence
{
	public Delphi_Keyword CASE = new Delphi_Keyword("Case");
	public Delphi_Expression expr;
	public Delphi_Keyword OF = new Delphi_Keyword("Of");
	public TokenList<Delphi_CaseClause> clauses;
	public Delphi_Keyword END = new Delphi_Keyword("End");
	
	public static class Delphi_CaseClause extends TokenSequence
	{
		public Delphi_Expression expr;
		public Delphi_Punctuation colon = new Delphi_Punctuation(':');
		public Delphi_Statement stmt;
		public Delphi_Punctuation semicolon = new Delphi_Punctuation(';');
	}
}
