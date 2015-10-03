// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

package com.eagle.programmar.Lisp;

import com.eagle.programmar.Lisp.Symbols.Lisp_Identifier_Reference;
import com.eagle.programmar.Lisp.Terminals.Lisp_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class Lisp_Variable extends TokenChooser
{
	public Lisp_Identifier_Reference var;
	
	public static class Lisp_VariableWithDot extends TokenSequence
	{
		public Lisp_Punctuation leftParen = new Lisp_Punctuation('(');
		public Lisp_Identifier_Reference var1;
		public Lisp_Punctuation dot = new Lisp_Punctuation('.');
		public Lisp_Identifier_Reference var2;
		public Lisp_Punctuation rightParen = new Lisp_Punctuation(')');
	}
}
