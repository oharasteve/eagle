// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 14, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Natural_Variable;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.tokens.TokenSequence;

public class Natural_DivideStatement extends TokenSequence
{
	public @DOC("sm/divide.htm") Natural_Keyword DIVIDE = new Natural_Keyword("DIVIDE");
	public Natural_Variable denominator;
	public Natural_Keyword INTO = new Natural_Keyword("INTO");
	public Natural_Variable numerator;
	public Natural_Keyword GIVING = new Natural_Keyword("GIVING");
	public Natural_Variable quotient;
}
