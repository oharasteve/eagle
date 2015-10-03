// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 30, 2013

package com.eagle.programmar.C.Statements;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TerminalCommentToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.UnparsedElement;

public class C_UnparsedStatement extends UnparsedElement
{
	@Override
	public TokenList<? extends AbstractToken> unparsedPieces()
	{
		TokenList<C_SkipToSemicolon> t = new TokenList<C_SkipToSemicolon>();
		t.addToken(unparsedStatement);
		return t;
	}

	public C_SkipToSemicolon unparsedStatement;
	
	public String getUnparsedElement()
	{
		return unparsedStatement.toString();
	}
	
	public static class C_SkipToSemicolon extends TerminalCommentToken
	{
		public C_SkipToSemicolon()
		{
			this("");
		}

		public C_SkipToSemicolon(String comment)
		{
			super(comment);
		}

		@Override
		public boolean parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF) return false;
			
			boolean inQuotes1 = false;
			boolean inQuotes2 = false;
			_comment = "";
			while (_currentLine < lines.size())
			{
				EagleLineReader rec = lines.get(_currentLine);
				if (rec == null) return false;
				_endChar = rec.length();
				if (_currentChar >= _endChar) break;	// What happened? Already too far?
				_comment += rec.substring(_currentChar, _endChar).trim();
				
				while (_currentChar < _endChar)
				{
					char ch = rec.charAt(_currentChar);
					if (ch == '\'' && ! inQuotes2)
					{
						inQuotes1 = ! inQuotes1;
					}
					if (ch == '"' && ! inQuotes1)
					{
						inQuotes2 = ! inQuotes2;
					}
					else if (ch == ';' && ! inQuotes1 && ! inQuotes2)
					{
						if (_comment.length() == 0) return false;
						foundIt(_currentLine, _endChar-2);		// Can't consume the semicolon!
						return true;
					}
					
					_currentChar++;
				}
				
				_comment += " ";
			}
			return false;
		}

		@Override
		public String description()
		{
			return "Unparsed C statement.";
		}
	}
}
