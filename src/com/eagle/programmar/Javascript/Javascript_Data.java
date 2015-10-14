// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 12, 2011

package com.eagle.programmar.Javascript;

import com.eagle.programmar.Javascript.Symbols.Javascript_Variable_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Javascript_Data extends TokenSequence
{
	public @NEWLINE Javascript_Type type;
	public Javascript_Variable_Definition var;
	public @OPT Javascript_InitData init;
	public @OPT TokenList<Javascript_More_Variables> moreVars;
	public @OPT @NOSPACE PunctuationSemicolon semicolon;
	
	public static class Javascript_InitData extends TokenSequence
	{
		public PunctuationEquals equals;
		public Javascript_Expression expr;
	}
	
	public static class Javascript_More_Variables extends TokenSequence
	{
		public @NOSPACE PunctuationComma comma;
		public @OPT TokenList<Javascript_Comment> comments;
		public Javascript_Variable_Definition var;
		public @OPT Javascript_InitData init;
	}
}
