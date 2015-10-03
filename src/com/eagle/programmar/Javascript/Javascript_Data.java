// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 12, 2011

package com.eagle.programmar.Javascript;

import com.eagle.programmar.Javascript.Symbols.Javascript_Variable_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Javascript_Data extends TokenSequence
{
	public @NEWLINE Javascript_Type type;
	public Javascript_Variable_Definition var;
	public @OPT Javascript_InitData init;
	public @OPT TokenList<Javascript_More_Variables> moreVars;
	public @OPT @NOSPACE Javascript_Punctuation semicolon = new Javascript_Punctuation(';');
	
	public static class Javascript_InitData extends TokenSequence
	{
		public Javascript_Punctuation equals = new Javascript_Punctuation('=');
		public Javascript_Expression expr;
	}
	
	public static class Javascript_More_Variables extends TokenSequence
	{
		public @NOSPACE Javascript_Punctuation comma = new Javascript_Punctuation(',');
		public @OPT TokenList<Javascript_Comment> comments;
		public Javascript_Variable_Definition var;
		public @OPT Javascript_InitData init;
	}
}
