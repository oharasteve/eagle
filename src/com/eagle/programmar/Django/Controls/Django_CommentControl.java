// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 5, 2015

package com.eagle.programmar.Django.Controls;

import com.eagle.programmar.Django.Terminals.Django_Comment;
import com.eagle.programmar.Django.Terminals.Django_Keyword;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationHyphen;

public class Django_CommentControl extends TokenSequence
{
	public HTML_Punctuation bracePercent1 = new HTML_Punctuation("{%");
	public @OPT PunctuationHyphen dash1;
	public Django_Keyword COMMENT = new Django_Keyword("comment");
	public @OPT PunctuationHyphen dash2;
	public HTML_Punctuation percentBrace1 = new HTML_Punctuation("%}");
	
	public Django_Comment comment;

	public HTML_Punctuation bracePercent2 = new HTML_Punctuation("{%");
	public @OPT PunctuationHyphen dash3;
	public Django_Keyword ENDCOMMENT = new Django_Keyword("endcomment");
	public @OPT PunctuationHyphen dash4;
	public HTML_Punctuation percentBrace2 = new HTML_Punctuation("%}");
}
