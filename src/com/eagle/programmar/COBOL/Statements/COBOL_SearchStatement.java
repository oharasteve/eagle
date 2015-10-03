// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 12, 2010

package com.eagle.programmar.COBOL.Statements;

import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.COBOL_Expression;
import com.eagle.programmar.COBOL.COBOL_Statement;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_SearchStatement extends COBOL_AbstractStatement
{
	public @DOC("rlpssear.htm") COBOL_Keyword SEARCH = new COBOL_Keyword("SEARCH");
	public @OPT COBOL_Keyword ALL = new COBOL_Keyword("ALL");
	public COBOL_Identifier_Reference var;
	public @OPT COBOL_SearchAtEndAction atEnd;
	public TokenList<COBOL_SearchWhenClause> whens;
	public @OPT COBOL_Keyword ENDSEARCH = new COBOL_Keyword("END-SEARCH");
	
	public static class COBOL_SearchAtEndAction extends TokenSequence
	{
		public COBOL_Keyword AT = new COBOL_Keyword("AT");
		public COBOL_Keyword END = new COBOL_Keyword("END");
		public TokenList<COBOL_Statement> endAction;
	}

	public static class COBOL_SearchWhenClause extends TokenSequence
	{
		public COBOL_Keyword WHEN = new COBOL_Keyword("WHEN");
		public COBOL_Expression condition;
		public TokenList<COBOL_Statement> statements; 
	}
}
