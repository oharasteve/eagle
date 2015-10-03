// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 22, 2010

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CSharp_Subscript extends TokenSequence
{
	public @NOSPACE CSharp_Punctuation leftBracket = new CSharp_Punctuation('[');
	public @NOSPACE CSharp_Expression expr;
	public @OPT @NOSPACE TokenList<CSharp_MoreSubscripts> more;
	public @NOSPACE CSharp_Punctuation rightBracket = new CSharp_Punctuation(']');
	
	public static class CSharp_MoreSubscripts extends TokenSequence
	{
		public CSharp_Punctuation comma = new CSharp_Punctuation(',');
		public CSharp_Expression expr;
	}
}
