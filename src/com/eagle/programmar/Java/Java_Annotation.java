// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2010

package com.eagle.programmar.Java;

import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Identifier;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Java_Annotation extends TokenChooser
{
	public static class Java_AnnotationCall extends TokenSequence
	{
		public Java_Punctuation atSign = new Java_Punctuation('@');
		public Java_Variable var;
		public Java_Punctuation leftParen = new Java_Punctuation('(');
		public @OPT Java_Expression expr;
		public @OPT TokenList<Java_MoreAnnotationExpressions> moreExpressions;
		public Java_Punctuation rightParen = new Java_Punctuation(')');
	}
	
	public static class Java_MoreAnnotationExpressions extends TokenSequence
	{
		public Java_Punctuation comma = new Java_Punctuation(',');
		public Java_Expression expr;
	}
	
	public static class Java_AnnotationCall2 extends TokenSequence
	{
		public Java_Punctuation atSign = new Java_Punctuation('@');
		public Java_Identifier id;
		public @OPT TokenList<Java_Comment> comments;
	}
}
