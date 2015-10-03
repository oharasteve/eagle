// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, May 4, 2014

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.TokenSequence;

public class CSharp_CheckedStatement extends TokenSequence
{
	public CSharp_Keyword CHECKED = new CSharp_Keyword("checked");
	public CSharp_Statement statement;
}
