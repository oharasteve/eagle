// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 15, 2013

package com.eagle.programmar.Lisp.Functions;

import com.eagle.programmar.Lisp.Lisp_SExpr;
import com.eagle.programmar.Lisp.Symbols.Lisp_Function_Definition;
import com.eagle.programmar.Lisp.Terminals.Lisp_Keyword;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Lisp_DefmacroFunction extends TokenSequence
{
	public PunctuationLeftParen leftParen;
	public @DOC("m_defmac.htm") Lisp_Keyword DEFMACRO = new Lisp_Keyword("defmacro");
	public Lisp_Function_Definition name;
	public Lisp_SExpr arguments;
	public TokenList<Lisp_SExpr> body;
	public PunctuationRightParen rightParen;
}
