// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.CSharp_Statement.CSharp_StatementBlock;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.TokenSequence;

public class CSharp_SetProperty extends TokenSequence
{
	public CSharp_Keyword SET = new CSharp_Keyword("set");
	public CSharp_StatementBlock block;
}
