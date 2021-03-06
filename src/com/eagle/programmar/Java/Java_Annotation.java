// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 20, 2010

package com.eagle.programmar.Java;

import com.eagle.programmar.Java.Terminals.Java_Comment;
import com.eagle.programmar.Java.Terminals.Java_Identifier;
import com.eagle.programmar.Java.Terminals.Java_Punctuation;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Java_Annotation extends TokenChooser
{
	public @CHOICE static class Java_AnnotationCall extends TokenSequence
	{
		public Java_Punctuation atSign = new Java_Punctuation('@');
		public @NOSPACE Java_Variable var;
		public @NOSPACE PunctuationLeftParen leftParen;
		public @OPT @NOSPACE SeparatedList<Java_Expression, PunctuationComma> expressions;
		public @NOSPACE PunctuationRightParen rightParen;
	}
	
	public @CHOICE static class Java_AnnotationCall2 extends TokenSequence
	{
		public Java_Punctuation atSign = new Java_Punctuation('@');
		public @NOSPACE Java_Identifier id;
		public @OPT TokenList<Java_Comment> comments;
	}
}
