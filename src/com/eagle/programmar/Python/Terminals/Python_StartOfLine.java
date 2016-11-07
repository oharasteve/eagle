// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 28, 2013

package com.eagle.programmar.Python.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.programmar.Python.Python_Statement;
import com.eagle.programmar.Python.Python_Statement.Python_Statement_List;
import com.eagle.programmar.Python.Statements.Python_IfStatement.Python_IfElif;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
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
			if (parent instanceof TokenList && !(parent instanceof SeparatedList))
			{
				@SuppressWarnings("unchecked")
				TokenList<? extends AbstractToken> tokenList = (TokenList<? extends AbstractToken>) parent;
				if (tokenList.size() == 0) break; // First entry always matches

				// The 'elif' clause is an irrelevant TokenList on an 'if' statement
				if (! (tokenList.first() instanceof Python_IfElif))
				{
					for (AbstractToken token : tokenList._elements)
					{
						if (token instanceof Python_Comment)
						{
							continue;	// Doesn't matter what columns comments are in
						}
	
						if (token instanceof Python_Statement)
						{
							Python_Statement firstStmt = (Python_Statement) token;
							AbstractToken child = firstStmt.statement.getWhich();
							if (child instanceof Python_Statement_List)
							{
								Python_Statement_List stmtList = (Python_Statement_List) child;
								if (_currentChar != stmtList.statements.getPrimaryElement(0)._currentChar) return false;
								break;
							}
						}
						else
						{
							throw new RuntimeException("Expected a Python_Statement, not " + token);
						}
					}
					break;
				}
			}
			parent = parent.getParent();
		}
		
		// This is an error -- the python statement was not inside a TokenList
		if (parent == null)
		{
			throw new RuntimeException("Never found the parent TokenList, at line " + _currentLine);
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
