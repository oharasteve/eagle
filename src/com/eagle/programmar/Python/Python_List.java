// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 19, 2016

package com.eagle.programmar.Python;

import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;

public class Python_List extends TokenSequence
{
	public @OPT TokenList<Python_Comment> comment1;
	public Python_Expression expr;
	public @OPT TokenList<Python_MoreListItem> nextItem;
	public @OPT PunctuationComma comma;
	public @OPT TokenList<Python_Comment> comment2;
	
	public static class Python_MoreListItem extends TokenSequence
	{
		public PunctuationComma comma;
		public @OPT TokenList<Python_Comment> comment;
		public Python_Expression expr;
	}
}