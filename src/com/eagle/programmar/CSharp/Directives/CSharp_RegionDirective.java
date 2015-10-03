// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 5, 2014

package com.eagle.programmar.CSharp.Directives;

import com.eagle.programmar.CSharp.CSharp_Class.CSharp_ClassElement;
import com.eagle.programmar.CSharp.Terminals.CSharp_CommentToEndOfLine;
import com.eagle.programmar.CSharp.Terminals.CSharp_Keyword;
import com.eagle.programmar.CSharp.Terminals.CSharp_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CSharp_RegionDirective extends TokenSequence
{
	public CSharp_Punctuation pound1 = new CSharp_Punctuation('#');
	public CSharp_Keyword REGION = new CSharp_Keyword("region");
	public CSharp_CommentToEndOfLine regionName;
	
	public TokenList<CSharp_ClassElement> elements;

	public CSharp_Punctuation pound2 = new CSharp_Punctuation('#');
	public CSharp_Keyword ENDREGION = new CSharp_Keyword("endregion");
}
