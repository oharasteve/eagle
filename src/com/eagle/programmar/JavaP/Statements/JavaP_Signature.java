// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 5, 2015

package com.eagle.programmar.JavaP.Statements;

import com.eagle.programmar.JavaP.Symbols.JavaP_Symbol_Reference;
import com.eagle.programmar.JavaP.Terminals.JavaP_Comment;
import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class JavaP_Signature extends TokenSequence
{
	public JavaP_Keyword SIGNATURE = new JavaP_Keyword("Signature");
	public PunctuationColon colon;
	public JavaP_Symbol_Reference ref;
	public @OPT JavaP_Comment comment;
	public JavaP_EndOfLine eoln;
}
