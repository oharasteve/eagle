// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.Javascript;

import com.eagle.programmar.Javascript.Javascript_Statement.Javascript_StatementOrComment;
import com.eagle.programmar.Javascript.Symbols.Javascript_Function_Definition;
import com.eagle.programmar.Javascript.Symbols.Javascript_Variable_Definition;
import com.eagle.programmar.Javascript.Terminals.Javascript_Comment;
import com.eagle.programmar.Javascript.Terminals.Javascript_Keyword;
import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Javascript_Function extends TokenSequence
{
	public Javascript_Keyword FUNCTION = new Javascript_Keyword("function");
	public @OPT Javascript_Function_Definition functionName;
	public @NOSPACE Javascript_Punctuation leftParen = new Javascript_Punctuation('(');
	public @OPT @NOSPACE Javascript_FunctionParameter param;
	public @OPT @NOSPACE TokenList<Javascript_MoreParameters> moreParams;
	public @OPT TokenList<Javascript_Comment> comments1;
	public @NOSPACE Javascript_Punctuation rightParen = new Javascript_Punctuation(')');
	public @OPT TokenList<Javascript_Comment> comments2;
	public Javascript_FunctionBody body;
	
	public static class Javascript_FunctionParameter extends TokenChooser
	{
		public Javascript_Variable_Definition id;
		public Javascript_Punctuation dollar = new Javascript_Punctuation('$');
	}
		
	public static class Javascript_MoreParameters extends TokenSequence
	{
		public @NOSPACE Javascript_Punctuation comma = new Javascript_Punctuation(',');
		public @OPT Javascript_Comment comment;
		public Javascript_FunctionParameter param;
	}
	
	public static class Javascript_FunctionBody extends TokenSequence
	{
		public @INDENT Javascript_Punctuation leftBrace = new Javascript_Punctuation('{');
		public @OPT TokenList<Javascript_StatementOrComment> statements;
		public @OUTDENT Javascript_Punctuation rightBrace = new Javascript_Punctuation('}');
	}
}
