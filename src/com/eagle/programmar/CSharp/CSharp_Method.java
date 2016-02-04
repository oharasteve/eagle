// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 18, 2010

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.CSharp_Statement.CSharp_StatementBlock.CSharp_StatementOrComment;
import com.eagle.programmar.CSharp.Symbols.CSharp_Method_Definition;
import com.eagle.programmar.CSharp.Symbols.CSharp_Variable_Definition;
import com.eagle.programmar.CSharp.Terminals.CSharp_Comment;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class CSharp_Method extends TokenSequence
{
	public @OPT @NEWLINE TokenList<CSharp_Comment> comment;
	public @OPT TokenList<CSharp_Annotation> annotation;
	public @OPT @NEWLINE TokenList<CSharp_MethodModifiers> modifiers;
	public @OPT TokenList<CSharp_Comment> comment2;
	public CSharp_Type returnType;
	public @OPT CSharp_Keyword GLOBAL = new CSharp_Keyword("global");
	public @OPT CSharp_Punctuation colon2 = new CSharp_Punctuation("::");
	public CSharp_Method_Definition methodName;
	public CSharp_MethodParameters parameters;
	public @NEWLINE CSharp_MethodBody body;
	public @OPT @CURIOUS("Extra semicolon") PunctuationSemicolon semicolon;

	public static class CSharp_MethodParameters extends TokenSequence
	{
		public PunctuationLeftParen leftParen;
		public @OPT CSharp_MethodParameter param;
		public @OPT TokenList<CSharp_MoreParameters> moreParams;
		public PunctuationRightParen rightParen;
		public @OPT CSharp_Comment comment3;
	}
	
	public static class CSharp_MethodModifiers extends TokenSequence
	{
		public CSharp_KeywordChoice modifier = new CSharp_KeywordChoice(CSharp_Program.MODIFIERS);
	}
	
	public static class CSharp_MethodParameter extends TokenSequence
	{
		public @OPT CSharp_Annotation annotation;
		public @OPT CSharp_KeywordChoice passBy = new CSharp_KeywordChoice("ref", "out", "this");
		public CSharp_Type cstype;
		public CSharp_Variable_Definition id;
		public @OPT CSharp_Punctuation emptySubscript = new CSharp_Punctuation("[]");
	}
	
	public static class CSharp_MoreParameters extends TokenSequence
	{
		public PunctuationComma comma;
		public CSharp_MethodParameter param;
	}
	
	public static class CSharp_MethodBody extends TokenChooser
	{
		public PunctuationSemicolon semicolon;
		
		public static class CSharp_MethodImplementation extends TokenSequence
		{
			public @INDENT PunctuationLeftBrace leftBrace;
			public @OPT TokenList<CSharp_StatementOrComment> elements;
			public @OUTDENT PunctuationRightBrace rightBrace;
		}
	}
}
