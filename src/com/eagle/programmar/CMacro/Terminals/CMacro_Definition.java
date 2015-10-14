// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 11, 2015

package com.eagle.programmar.CMacro.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalLiteralToken;

public class CMacro_Definition extends TerminalLiteralToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		_currentLine = lines.getCurrentLine();
		_currentChar = lines.getCurrentChar();
		
		// Need to see if it is continued (with a backslash)
		StringBuffer macroValue = new StringBuffer();
		int lastLine = _currentLine;
		int firstChar = _currentChar;
		boolean continued = true;
		int maxLine = lines.size();
		
		// Skip the space after the macro name
		EagleLineReader rec = lines.get(lastLine);
		int recLen = rec.length();
		if (_currentChar + 1 < recLen) _currentChar++;
		
		while (continued && lastLine < maxLine)
		{
			rec = lines.get(lastLine);
			recLen = rec.length();
			int lastNonblank = recLen - 1;
			continued = false;

			while (lastNonblank >= 0)
			{
				char ch = rec.charAt(lastNonblank);
				if (ch == '\\')
				{
					continued = true;
					break;
				}
				
				if (ch != ' ' && ch != '\t')
				{
					break;	// We're good. Last non-blank character is not a backslash (\)
				}
				
				lastNonblank--;
			}
			macroValue.append(rec.substring(firstChar, lastNonblank+1));
			if (!continued) break;
			
			macroValue.append('\n');
			firstChar = 0;
			lastLine++;
		}
		
		foundIt(lastLine, recLen);
		_txt = macroValue.toString();
		return true;
	}

	@Override
	public String description()
	{
		return "Macro definition";
	}
}