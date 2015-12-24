// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 16, 2013

package com.eagle.programmar.Python;

import com.eagle.programmar.EagleSyntax;
import com.eagle.programmar.Python.Terminals.Python_Comment;

public class Python_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "Python";
	}
	
	public Python_Syntax()
	{
		_isCaseSensitive = true;
		_continuationChar = "\\";
		_extraCharacters = "_";
		_autoAdvance = false;
		_punctuationExceptions = new String[] { ">>", "|=", "+=", "-=", "__", "_$", "**" };
		
		_commentInstance = new Python_Comment();
		
		addReservedWords(keywords);
	}

	private String[] keywords = new String[] {
		"and",
		"as",
		"assert",
		"break",
		"class",
		"continue",
		"def",
		"del",
		"elif",
		"else",
		"except",
		"exec",
		"False",
		"finally",
		"for",
		"from",
		"global",
		"if",
		"import",
		"in",
		"is",
		"lambda",
		"not",
		"or",
		"pass",
		"raise",
		"return",
		"self",
		"True",
		"try",
		"while",
		"with",
		"yield"
	};
	
	public static class Python_Multiline_Syntax extends Python_Syntax
	{
		@Override
		public String syntaxId()
		{
			return "Python Multi";
		}
		
		public Python_Multiline_Syntax()
		{
			_autoAdvance = true;
		}
	}
}
