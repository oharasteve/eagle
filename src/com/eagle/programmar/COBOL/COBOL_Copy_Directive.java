// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 5, 2012

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Statements.COBOL_CopyStatement;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.programmar.COBOL.Terminals.COBOL_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_Copy_Directive extends TokenSequence
{
	public @OPT TokenList<COBOL_Comment> comments;
	public COBOL_CopyStatement copyStatement;
	public COBOL_Punctuation dot = new COBOL_Punctuation('.');
}
