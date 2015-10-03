// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 17, 2011

package com.eagle.programmar.SQL.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalIdentifierToken;

public class SQL_Identifier extends TerminalIdentifierToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		
		// Have to see if it starts with a quote first!
		EagleLineReader rec = lines.get(_currentLine);
		char ch = rec.charAt(_currentChar);
		if (ch == '\'' || ch == '`' || ch == '"')
		{
			int recLen = rec.length();
			char quote = ch;
			int endChar = _currentChar;
			char prevCh = ' ';
			while (true)
			{
				endChar++;
				if (endChar >= recLen) break;
				ch = rec.charAt(endChar);
				
				if (ch == quote && prevCh != '\\') break;
				if (prevCh == '\\' && ch == '\\') ch = ' ';
				
				prevCh = ch;
			}
			foundIt(_currentLine, endChar);
			_id = rec.substring(_currentChar, endChar+1);
			return true;
		}
		
		return genericIdentifier(lines, ALPHAS, ALPHAS+DIGITS+"_", true);
	}
}
