// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 6, 2011

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.CSharp_Expression;
import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.CSharp_Type;
import com.eagle.programmar.CSharp.CSharp_Variable;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_ForEachStatement extends TokenSequence
{
	public @NEWLINE CSharp_Keyword FOREACH = new CSharp_Keyword("foreach");
	public PunctuationLeftParen leftParen;
	public CSharp_Type varType;
	public CSharp_Variable forVar;
	public CSharp_Keyword IN = new CSharp_Keyword("in");
	public CSharp_Expression collection;
	public PunctuationRightParen rightParen;
	public @OPT CSharp_Comment comment;
	public CSharp_Statement action;
}

