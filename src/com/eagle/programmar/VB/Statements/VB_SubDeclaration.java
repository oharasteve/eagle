// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 16, 2011

package com.eagle.programmar.VB.Statements;

import com.eagle.programmar.VB.VB_Parameters;
import com.eagle.programmar.VB.VB_Statement;
import com.eagle.programmar.VB.Symbols.VB_Sub_Definition;
import com.eagle.programmar.VB.Terminals.VB_EndOfLine;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class VB_SubDeclaration extends TokenSequence
{
	public @OPT VB_KeywordChoice modifier = new VB_KeywordChoice(
			"private", "public");
	public VB_Keyword SUB1 = new VB_Keyword("sub");
	public VB_Sub_Definition name;
	public VB_Parameters params;
	public VB_EndOfLine eoln;
	public @OPT TokenList<VB_Statement> stmts;
	public VB_Keyword END = new VB_Keyword("end");
	public VB_Keyword SUB2 = new VB_Keyword("sub");
}
