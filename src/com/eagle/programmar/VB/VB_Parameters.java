// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 16, 2011

package com.eagle.programmar.VB;

import com.eagle.programmar.VB.Symbols.VB_Variable_Definition;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.programmar.VB.Terminals.VB_KeywordChoice;
import com.eagle.programmar.VB.Terminals.VB_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class VB_Parameters extends TokenSequence
{
	public VB_Punctuation leftParen = new VB_Punctuation('(');
	public @OPT VB_Parameter param;
	public @OPT TokenList<VB_MoreParameters> moreParams;
	public VB_Punctuation rightParen = new VB_Punctuation(')');
	
	public static class VB_Parameter extends TokenSequence
	{
		public @OPT VB_KeywordChoice valRef = new VB_KeywordChoice(
				"byval", "byref");
		public VB_Variable_Definition var;
		public VB_Keyword AS = new VB_Keyword("as");
		public VB_Type type;
	}
	
	public static class VB_MoreParameters extends TokenSequence
	{
		public VB_Punctuation comma = new VB_Punctuation(',');
		public VB_Parameter param;
	}
}
