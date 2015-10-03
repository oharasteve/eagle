// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 14, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Natural_Statement;
import com.eagle.programmar.Natural.Natural_Variable;
import com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.programmar.Natural.Terminals.Natural_Literal;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Natural_HistogramStatement extends TokenSequence
{
	public @DOC("sm/histogra.htm") Natural_Keyword HISTOGRAM = new Natural_Keyword("HISTOGRAM");
	public Natural_Identifier_Reference viewName;
	public Natural_Variable var;
	public Natural_Keyword STARTING = new Natural_Keyword("STARTING");
	public Natural_Keyword FROM = new Natural_Keyword("FROM");
	public Natural_Literal literal;
	public TokenList<Natural_Statement> statements;
	public Natural_Keyword ENDHISTOGRAM = new Natural_Keyword("END-HISTOGRAM");
}
