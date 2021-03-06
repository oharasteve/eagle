// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 17, 2013

package com.eagle.programmar.Python.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalLiteralToken;

public class Python_Literal extends TerminalLiteralToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;

		EagleLineReader rec = lines.get(_currentLine);
		int nc = rec.length();
		
		// Pick up the prefix(es), if they are present
		char pre1 = ' ';
		char pre2 = ' ';
		if (_currentChar < nc) pre1 = rec.charAt(_currentChar);
		if (_currentChar + 1 < nc) pre2 = rec.charAt(_currentChar + 1);
		
		int prefixLen;
		if (pre1 == 'u' && pre2 == 'r') prefixLen = 2;
		else if (pre1 == 'r' && pre2 == 'u') prefixLen = 2;
		else if (pre1 == 'b' && pre2 == 'r') prefixLen = 2;
		else if (pre1 == 'r') prefixLen = 1;
		else if (pre1 == 'u') prefixLen = 1;
		else if (pre1 == 'b') prefixLen = 1;
		else prefixLen = 0;
		_currentChar += prefixLen;
		
		// Pick up the next three characters, if they are present
		char ch1 = ' ';
		char ch2 = ' ';
		char ch3 = ' ';
		if (_currentChar < nc) ch1 = rec.charAt(_currentChar);
		if (_currentChar + 1 < nc) ch2 = rec.charAt(_currentChar + 1);
		if (_currentChar + 2 < nc) ch3 = rec.charAt(_currentChar + 2);

		boolean ok;
		if (ch1 == '\'' || ch1 == '"')
		{
			lines.setCurrentChar(_currentChar);
			lines.setCurrentLine(_currentLine);
			if (ch2 == ch1 && ch3 == ch1)
			{
				String triple = (ch1 == '"' ? "\"\"\"" : "'''");	// Either ''' or """
				ok = genericLiteral3(lines, rec, triple, triple);
			}
			else
			{
				ok = genericLiteral(lines, "\"'", true, '\\', false, false);
			}
		}
		else ok = false;
		
		if (ok)
		{
			if (prefixLen == 1) _txt = pre1 + _txt;
			else if (prefixLen == 2) _txt = pre1 + pre2 + _txt;
		}
		_currentChar -= prefixLen;
		return ok;
	}
}
