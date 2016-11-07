// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 12, 2014

package com.eagle.programmar.Python;

import com.eagle.programmar.EagleLanguage;
import com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_HexNumber;
import com.eagle.programmar.Python.Terminals.Python_Identifier;
import com.eagle.programmar.Python.Terminals.Python_KeywordChoice;
import com.eagle.programmar.Python.Terminals.Python_Literal;
import com.eagle.programmar.Python.Terminals.Python_Number;
import com.eagle.programmar.Python.Terminals.Python_PunctuationChoice;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class Python_Terminals extends EagleLanguage
{
	public Python_Terminals()
	{
		super("Python Terminals", new Python_Terminal_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return null;
	}
	
	@Override
	public TokenList<? extends AbstractToken> getTerminals()
	{
		return _terminals;
	}

	// Just collect a list of all the terminals
	public TokenList<Python_Terminal> _terminals;
	
	// Need to use this, so there are no punctuation exceptions like "+="
	public static class Python_Terminal_Syntax extends Python_Multiline_Syntax
	{
		public Python_Terminal_Syntax()
		{
			_punctuationExceptions = null;
		}
	}

	public static class Python_Terminal extends TokenChooser
	{
		public @CHOICE Python_Comment comment;
		public @CHOICE Python_HexNumber hex;
		public @CHOICE Python_Number number;
		public @CHOICE Python_Literal literal;
		public @CHOICE Python_KeywordChoice keywords = new Python_KeywordChoice(getSyntax().allReservedWords());
		public @CHOICE Python_Identifier id;
		public @CHOICE Python_PunctuationChoice puncts = new Python_PunctuationChoice(
				"+", "-", "*", "/", "%", ",", ".", "=", "<", ">", ":", ";", "_", "!", "@", "(", ")", "[", "]", "{", "}");
	}
}
