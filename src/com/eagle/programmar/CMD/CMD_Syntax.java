// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 30, 2012

package com.eagle.programmar.CMD;

import com.eagle.programmar.EagleSyntax;
import com.eagle.programmar.CMD.CMD_Command.CMD_Statement;

public class CMD_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "CMD";
	}
	
	public CMD_Syntax()
	{
		_isCaseSensitive = false;
		_continuationChar = "\\";
		_extraCharacters = "";
		_autoAdvance = false;
		_punctuationExceptions = new String[] { "::", "==", "&&", ">>", ">&" };
		
		findFirstWords(CMD_Statement.class);
	}
}
