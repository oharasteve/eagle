// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 15, 2015

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.CSharp_Method.CSharp_MethodBody.CSharp_MethodImplementation;
import com.eagle.programmar.CSharp.CSharp_Method.CSharp_MethodModifiers;
import com.eagle.programmar.CSharp.CSharp_Method.CSharp_MethodParameter;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CSharp_SubscriptOperator extends TokenSequence
{
	public @OPT @NEWLINE TokenList<CSharp_Comment> comment;
	public @OPT TokenList<CSharp_Annotation> annotation;
	public @OPT @NEWLINE TokenList<CSharp_MethodModifiers> modifiers;
	public CSharp_Type returnType;
	public CSharp_Keyword THIS = new CSharp_Keyword("this");
	public CSharp_Punctuation leftBracket = new CSharp_Punctuation('[');
	public CSharp_MethodParameter parameter;
	public CSharp_Punctuation rightBracket = new CSharp_Punctuation(']');
	public CSharp_MethodImplementation implementation;
}
