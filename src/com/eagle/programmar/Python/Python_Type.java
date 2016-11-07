// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 28, 2013

package com.eagle.programmar.Python;

import com.eagle.programmar.Python.Symbols.Python_Identifier_Reference;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class Python_Type extends TokenChooser
{
	public @CHOICE Python_Keyword OBJECT = new Python_Keyword("object");
	
	public @CHOICE static class Python_Regular_Class extends TokenSequence
	{
		public SeparatedList<Python_TypeName,PunctuationPeriod> superClass;
		
		public static class Python_TypeName extends TokenChooser
		{
			public @CHOICE Python_Keyword SELF = new Python_Keyword("self");
			public @CHOICE Python_Identifier_Reference id;
		}
	}
}
