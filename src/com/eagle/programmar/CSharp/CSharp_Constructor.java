// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 24, 2015

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.CSharp_Expression.CSharp_ArgumentList;
import com.eagle.programmar.CSharp.CSharp_Method.CSharp_MethodBody;
import com.eagle.programmar.CSharp.CSharp_Method.CSharp_MethodModifiers;
import com.eagle.programmar.CSharp.CSharp_Method.CSharp_MethodParameter;
import com.eagle.programmar.CSharp.CSharp_Method.CSharp_MoreParameters;
import com.eagle.programmar.CSharp.Symbols.CSharp_Current_Class_Reference;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_Constructor extends TokenSequence
{
	public @OPT @NEWLINE TokenList<CSharp_Comment> comment;
	public @OPT TokenList<CSharp_Annotation> annotation;
	public @OPT TokenList<CSharp_MethodModifiers> modifiers;
	public CSharp_Current_Class_Reference constructorName;
	public PunctuationLeftParen leftParen;
	public @OPT CSharp_MethodParameter param;
	public @OPT TokenList<CSharp_MoreParameters> moreParams;
	public PunctuationRightParen rightParen;
	public @OPT CSharp_ExtendsBase extendsBase;
	public CSharp_MethodBody body;

	public static class CSharp_ExtendsBase extends TokenSequence
	{
		public PunctuationColon colon;
		public CSharp_KeywordChoice baseOrthis = new CSharp_KeywordChoice(
				"base", "this");
		public PunctuationLeftParen leftParen;
		public @OPT CSharp_ArgumentList argList;
		public PunctuationRightParen rightParen;
	}
}
