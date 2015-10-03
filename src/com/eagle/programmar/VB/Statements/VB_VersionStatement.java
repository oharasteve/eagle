// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2011

package com.eagle.programmar.VB.Statements;

import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.programmar.VB.Terminals.VB_Number;
import com.eagle.tokens.TokenSequence;

public class VB_VersionStatement extends TokenSequence
{
	public VB_Keyword VERSION = new VB_Keyword("version");
	public VB_Number version;
	public VB_Keyword CLASS = new VB_Keyword("class");
}
