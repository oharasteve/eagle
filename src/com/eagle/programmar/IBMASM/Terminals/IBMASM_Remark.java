// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 10, 2011

package com.eagle.programmar.IBMASM.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalCommentToken;

public class IBMASM_Remark extends TerminalCommentToken
{
	// Need a default constructor for the parser
	public IBMASM_Remark()
	{
		this("");
	}
	
	public IBMASM_Remark(String remark)
	{
		super(remark);
	}

	@Override
	public boolean parse(EagleFileReader lines)
	{
		EagleLineReader rec = lines.get(_currentLine);
		_endChar = rec.length();
		if (_currentChar >= _endChar) return false;
		_comment = rec.substring(_currentChar, _endChar).trim();
		foundIt(_currentLine, _endChar);
		return true;
	}

	@Override
	public String showString()
	{
		return "Remark";
	}

	@Override
	public String description()
	{
		return "IBMASM remark";
	}
}
