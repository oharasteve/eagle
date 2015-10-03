// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi.Statements;

import com.eagle.programmar.Delphi.Symbols.Delphi_Identifier_Reference;
import com.eagle.programmar.Delphi.Terminals.Delphi_Keyword;
import com.eagle.programmar.Delphi.Terminals.Delphi_Literal;
import com.eagle.programmar.Delphi.Terminals.Delphi_Punctuation;
import com.eagle.tokens.TokenSequence;

public class Delphi_Rewrite_Statement extends TokenSequence
{
	public Delphi_Keyword REWRITE = new Delphi_Keyword("ReWrite");
	public Delphi_Punctuation leftParen = new Delphi_Punctuation('(');
	public Delphi_Identifier_Reference file;
	public Delphi_Punctuation comma = new Delphi_Punctuation(',');
	public Delphi_Literal fileName;
	public Delphi_Punctuation rightParen = new Delphi_Punctuation(')');
}
