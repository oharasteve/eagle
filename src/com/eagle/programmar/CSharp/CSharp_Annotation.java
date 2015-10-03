// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2010

package com.eagle.programmar.CSharp;

import com.eagle.programmar.CSharp.CSharp_Expression.CSharp_ArgumentList;
import com.eagle.programmar.CSharp.CSharp_Variable.CSharp_DotIdentifier;
import com.eagle.programmar.CSharp.Terminals.CSharp_Identifier;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_KeywordChoice;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CSharp_Annotation extends TokenSequence
{
	public CSharp_Punctuation leftBracket = new CSharp_Punctuation('[');
	public CSharp_AnnotationItem item;
	public @OPT TokenList<CSharp_MoreAnnotations> more;
	public CSharp_Punctuation rightBracket = new CSharp_Punctuation(']');

	public static class CSharp_AnnotationItem extends TokenSequence
	{
		public @OPT CSharp_AnnotionGlobal global;
		public @OPT CSharp_AnnotionAssembly assembly;
		public CSharp_Identifier id;
		public @OPT TokenList<CSharp_DotIdentifier> moreIds;
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
		public CSharp_Punctuation colon = new CSharp_Punctuation(':');
	}
	
	public static class CSharp_AnnotationParams extends TokenSequence
	{
		public CSharp_Punctuation leftParen = new CSharp_Punctuation('(');
		public @OPT CSharp_ArgumentList argList;
		public CSharp_Punctuation rightParen = new CSharp_Punctuation(')');
	}
	
	public static class CSharp_MoreAnnotations extends TokenSequence
	{
		public CSharp_Punctuation comma = new CSharp_Punctuation(',');
		public CSharp_AnnotationItem item;
	}
}
