// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 6, 2011

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.CSharp_Type;
import com.eagle.programmar.CSharp.CSharp_Variable;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.TokenSequence;

public class CSharp_ForEachStatement extends TokenSequence
{
	public @NEWLINE CSharp_Keyword FOREACH = new CSharp_Keyword("foreach");
	public CSharp_Punctuation leftParen = new CSharp_Punctuation('(');
	public CSharp_Type varType;
	public CSharp_Variable forVar;
	public CSharp_Keyword IN = new CSharp_Keyword("in");
	public CSharp_Expression collection;
	public CSharp_Punctuation rightParen = new CSharp_Punctuation(')');
	public @OPT CSharp_Comment comment;
	public CSharp_Statement action;
}

