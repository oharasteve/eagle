// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 5, 2014

package com.eagle.programmar.CSharp.Statements;

import com.eagle.programmar.CSharp.CSharp_Data.CSharp_DataBeforeSemicolon;
import com.eagle.programmar.CSharp.CSharp_Statement;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.TokenSequence;

public class CSharp_UsingStatement extends TokenSequence
{
	public @NEWLINE CSharp_Keyword USING = new CSharp_Keyword("using");
	public CSharp_Punctuation leftParen = new CSharp_Punctuation('(');
	public @NOSPACE CSharp_DataBeforeSemicolon declaration;
	public @NOSPACE CSharp_Punctuation rightParen = new CSharp_Punctuation(')');
	public CSharp_Statement whileStatement;
}
