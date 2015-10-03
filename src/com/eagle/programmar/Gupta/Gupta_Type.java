// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 11, 2011

package com.eagle.programmar.Gupta;

import com.eagle.programmar.Gupta.Terminals.Gupta_KeywordChoice;
import com.eagle.tokens.TokenSequence;

public class Gupta_Type extends TokenSequence
{
	public Gupta_KeywordChoice typeName = new Gupta_KeywordChoice("String", "Number");
}
