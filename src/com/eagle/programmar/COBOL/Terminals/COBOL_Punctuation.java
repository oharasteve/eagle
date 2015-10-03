// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 6, 2010

package com.eagle.programmar.COBOL.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.programmar.EagleSyntax;
import com.eagle.tokens.TerminalPunctuationToken;

public class COBOL_Punctuation extends TerminalPunctuationToken
{
	// Need default constructor for reading from the XML file
	public COBOL_Punctuation()
	{
		this('\0');
	}

	public COBOL_Punctuation(char punct)
	{
		super(punct);
	}

	public COBOL_Punctuation(String punct)
	{
		super(punct);
	}

	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		if (_punct1 == '*')
		{
			// Make sure a '*' is not a comment
			EagleLineReader rec = lines.get(_currentLine);
			if (rec.charAt(_currentChar) == '*')
			{
				EagleSyntax syntax = this.getSyntax();
				if (_currentChar == syntax._commentColumn) return false;
			}
		}
		return super.parse(lines);
	}
}
