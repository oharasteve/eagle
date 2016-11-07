// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2010

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.Terminals.CSharp_Identifier;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CSharp_Annotation extends TokenSequence
{
	public PunctuationLeftBracket leftBracket;
	public CSharp_AnnotationItem item;
	public @OPT TokenList<CSharp_MoreAnnotations> more;
	public PunctuationRightBracket rightBracket;

	public static class CSharp_AnnotationItem extends TokenSequence
	{
		public @OPT CSharp_AnnotionGlobal global;
		public @OPT CSharp_AnnotionAssembly assembly;
		public SeparatedList<CSharp_Identifier,PunctuationPeriod> ids;
		public @OPT CSharp_AnnotationParams params;
	}

	public static class CSharp_AnnotionGlobal extends TokenSequence
	{
		public CSharp_Keyword GLOBAL = new CSharp_Keyword("global");
		public CSharp_Punctuation colon2 = new CSharp_Punctuation("::");
	}
	
	public static class CSharp_AnnotionAssembly extends TokenSequence
	{
		public CSharp_KeywordChoice ASSEMBLY = new CSharp_KeywordChoice("assembly", "return");
		public PunctuationColon colon;
	}
	
	public static class CSharp_AnnotationParams extends TokenSequence
	{
		public PunctuationLeftParen leftParen;
		public @OPT CSharp_ArgumentList argList;
		public PunctuationRightParen rightParen;
	}
	
	public static class CSharp_MoreAnnotations extends TokenSequence
	{
		public PunctuationComma comma;
		public CSharp_AnnotationItem item;
	}
}
