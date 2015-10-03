// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2011

package com.eagle.programmar.VB.Statements;

import com.eagle.programmar.VB.VB_Statement;
import com.eagle.programmar.VB.Terminals.VB_EndOfLine;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.TokenList;

public class VB_BeginStatement extends TokenSequence
{
	public VB_Keyword BEGIN = new VB_Keyword("begin");
	public TokenList<VB_EndOfLine> eoln1;
	public TokenList<VB_Statement> stmts;
	public VB_Keyword END = new VB_Keyword("end");

}
