// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 28, 2013

package com.eagle.programmar.Python.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.programmar.Python.Python_Statement;
import com.eagle.programmar.Python.Python_Statement.Python_Simple_Statement.Python_Statement_Not_Comment;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TerminalLiteralToken;
import com.eagle.tokens.TokenList;

public class Python_StartOfLine extends TerminalLiteralToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		AbstractToken parent = this.getParent();
		while (parent != null)
		{
			// Find the enclosing TokenList of statements
			if (parent instanceof TokenList)
			{
				@SuppressWarnings("unchecked")
				TokenList<? extends AbstractToken> tokenList = (TokenList<? extends AbstractToken>) parent;
				if (tokenList.size() == 0) break; // First entry always matches
				for (AbstractToken token : tokenList._elements)
				{
					if (token instanceof Python_Comment) continue;	// Doesn't matter what columns comments are in
					if (! (token instanceof Python_Statement)) break;
					Python_Statement firstStmt = (Python_Statement) token;
					AbstractToken child = firstStmt.statement.statements.first()._whichToken;
					if (child instanceof Python_Statement_Not_Comment)
					{
						Python_Statement_Not_Comment childStmt = (Python_Statement_Not_Comment) child;
						if (_currentChar != childStmt.statement._currentChar) return false;
						break;
					}
				}
				break;
			}
			parent = parent.getParent();
		}
		foundIt(_currentLine, _currentChar-1);
		return true;
	}
	
	@Override
	public String showString()
	{
		return "SOLN";
	}

	@Override
	public String description()
	{
		return "Start of line";
	}
}
