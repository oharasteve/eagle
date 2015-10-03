// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2011

package com.eagle.programmar.VB.Statements;

import com.eagle.programmar.VB.VB_Expression;
import com.eagle.programmar.VB.VB_Statement;
import com.eagle.programmar.VB.Symbols.VB_Identifier_Reference;
import com.eagle.programmar.VB.Terminals.VB_EndOfLine;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.programmar.VB.Terminals.VB_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class VB_ForStatement extends TokenSequence
{
	public @DOC("5z06z1kb.aspx") VB_Keyword FOR = new VB_Keyword("for");
	public VB_Identifier_Reference var;
	public VB_Punctuation equals = new VB_Punctuation('=');
	public VB_Expression from;
	public VB_Keyword TO = new VB_Keyword("to");
	public VB_Expression to;
	public TokenList<VB_EndOfLine> eoln;
	public TokenList<VB_Statement> action;
	public VB_Keyword NEXT = new VB_Keyword("next");
	public @OPT VB_Identifier_Reference var2;
}
