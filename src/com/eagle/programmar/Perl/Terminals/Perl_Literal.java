// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jul 12, 2011

package com.eagle.programmar.Perl.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalLiteralToken;

public class Perl_Literal extends TerminalLiteralToken
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		
		// Check for <<<STOPPER lines STOPPER
		EagleLineReader rec = lines.get(_currentLine);
		if (multilineStopper(lines, rec, "<<<")) return true;
		if (multilineStopper(lines, rec, "<<")) return true;

		return genericLiteral(lines, "\"'`", true, '\\', false, true);
	}
}
