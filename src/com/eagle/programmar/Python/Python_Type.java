// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 28, 2013

package com.eagle.programmar.Python;

import com.eagle.programmar.Python.Symbols.Python_Identifier_Reference;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Python_Type extends TokenChooser
{
	public Python_Keyword OBJECT = new Python_Keyword("object");
	
	public static class Python_Regular_Class extends TokenSequence
	{
		public Python_Identifier_Reference superClass;
		public @OPT TokenList<Python_MoreClasses> moreClasses;
		
		public static class Python_MoreClasses extends TokenSequence
		{
			public Python_Punctuation dot = new Python_Punctuation('.');
			public Python_Identifier_Reference nextClass;
		}
	}
}
