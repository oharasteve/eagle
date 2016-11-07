// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 8, 2011

package com.eagle.tokens;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.programmar.EagleSyntax;

public abstract class TerminalLiteralToken extends TerminalToken
{
	protected String _txt = "";
	
	@Override
	public String getDisplayStyleName()
	{
		return "literal";
	}
	
	protected boolean genericLiteral(EagleFileReader lines, String quotes, boolean hasEscape, char escape,
			boolean allowDoubled, boolean allowMultiline)
	{
		if (findStart(lines) == FOUND.EOF) return false;

		EagleLineReader rec = lines.get(_currentLine);
		int lastLine = _currentLine;
		int recLen = rec.length();
		if (_currentChar >= recLen) return false;
		
		EagleSyntax syntax = this.getSyntax();
		char ch = rec.charAt(_currentChar);
		if (quotes.indexOf(ch) >= 0)
		{
			char quote = ch;
			int endChar = _currentChar;
			char prevCh = ' ';
			while (true)
			{
				endChar++;
				boolean keepGoing = true;
				while (endChar >= recLen)
				{
					boolean endedWithNewline = false;
					if (hasEscape && prevCh == escape)
					{
						// Oh brother, they escaped the end-of-line marker
						// Need to keep going on the next line
						// Do this, even if allowMultiline is not set
						endedWithNewline = true;
					}

					if (!endedWithNewline && !allowMultiline && syntax._fixedStartColumn == 0)
					{
						endChar--;
						keepGoing = false;
						break;
					}
					
					_txt += rec.substring(_currentChar, endChar) + '\n';
					
					lastLine++;
					rec = lines.get(lastLine);
					recLen = rec.length();
					if (findStart(lines, lastLine, 0) == FOUND.EOF) return false;
					
					_currentChar = 0;
					if (syntax != null && syntax._fixedStartColumn > 0 && syntax._fixedStartColumn < recLen && rec.charAt(syntax._fixedStartColumn) == '-')
					{
						// Fixed-format COBOL is odd, with a "-" in column 7
						_currentChar = rec.indexOf('"') + 1;
					}
					
					endChar = _currentChar;
				}
				if (!keepGoing) break;
	
				if (endChar >= recLen) break;
				ch = rec.charAt(endChar);
				
				// Make sure not doubled up quotes
				if (ch == quote && allowDoubled)
				{
					// Careful with SQL which seems to allow both '' and \' ...
					if (!hasEscape || prevCh != escape)
					{
						if (endChar+1 >= rec.length()) break;
						if (rec.charAt(endChar + 1) != quote) break;
						endChar++;
					}
				}
				else if (hasEscape)
				{
					if (ch == quote && prevCh != escape) break;
					if (prevCh == escape && ch == escape) ch = ' ';
				}
				else
				{
					if (ch == quote) break;
				}
				
				prevCh = ch;
			}
			
			foundIt(lastLine, endChar);
			endChar++;
			if (endChar > recLen) endChar = recLen;
			_txt += rec.substring(_currentChar, endChar);
			return true;
		}
		return false;
	}

	// Python example: """ ... """)
	protected boolean genericLiteral3(EagleFileReader lines, EagleLineReader rec0, String firstMarker, String lastMarker)
	{
		EagleLineReader rec = rec0;
		if (firstMarker.length() != 3) throw new RuntimeException("Literal first marker must have length three.");
		if (lastMarker.length() != 3) throw new RuntimeException("Literal last marker must have length three.");
		if (rec.charAt(_currentChar) != firstMarker.charAt(0)) return false;	// Redundant check
		int nc = rec.length();
		if (_currentChar + 2 >= nc) return false;
		if (rec.charAt(_currentChar + 1) != firstMarker.charAt(1)) return false;
		if (rec.charAt(_currentChar + 2) != firstMarker.charAt(2)) return false;

		// Is the end on the same line?
		int ec = rec.indexOf(lastMarker, _currentChar + 2);
		if (ec >= 0)
		{
			if (! isEscaped(rec, ec))
			{
				// Yes! Whew! Same line.
				foundIt(_currentLine, ec+2);
				_txt = rec.substring(_currentChar, ec+3).trim();
				return true;
			}
		}
		
		// Oh dang ... multi-line literal
		_txt = rec.substring(_currentChar) + "\n";
		int lastLine = _currentLine + 1;
		int numberLines = lines.size();
		while (lastLine < numberLines)
		{
			rec = lines.get(lastLine);
			ec = rec.indexOf(lastMarker);
			if (ec >= 0)
			{
				if (! isEscaped(rec, ec))
				{
					// Finished multiline literal
					foundIt(lastLine, ec+2);
					_txt += rec.substring(0, ec+3);
					return true;
				}
			}
			_txt += rec + "\n";
			lastLine++;
		}
		throw new RuntimeException("End of literal missing " + firstMarker + " ... " + lastMarker);
	}
	
	private static boolean isEscaped(EagleLineReader rec, int ec)
	{
		// Don't stop on \""" ...
		if (ec == 0) return false;
		if (rec.charAt(ec-1) != '\\') return false;
		if (ec > 1 && rec.charAt(ec-2) == '\\') return false;
		return true;
	}
	
	// <<<STOPPER lines STOPPER
	protected boolean multilineStopper(EagleFileReader lines, EagleLineReader rec0, String pattern)
	{
		EagleLineReader rec = rec0;
		if (rec.charAt(_currentChar) != pattern.charAt(0)) return false;
		String remainder = rec.substring(_currentChar);
		if (!remainder.startsWith(pattern)) return false;
		
		String stopper = rec.substring(_currentChar+pattern.length()).trim().
				replace(";", "").
				replaceAll("'", "").
				replaceAll("\"", "");	// Rest of this line is the stopper
		_txt = rec.substring(_currentChar);
		int lastLine = _currentLine;
		while (++lastLine < lines.size())
		{
			String next = lines.get(lastLine).toString();
			if (findStart(lines, lastLine, 0) == FOUND.EOF) return false;
			_txt += '\n' + next.trim();
			if (next.startsWith(stopper))
			{
				foundIt(lastLine, stopper.length() - 1);
				return true;
			}
		}
		
		return false;
	}
	
	// Canonical example is [[#include <mem.h>]] in C
	protected boolean quotePair(EagleFileReader lines, char startQuote, char endQuote)
	{
		if (findStart(lines) == FOUND.EOF) return false;

		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		if (_currentChar >= recLen) return false;
		
		char ch = rec.charAt(_currentChar);
		if (ch != startQuote) return false;
		
		int endChar = _currentChar;
		while (++endChar < recLen)
		{
			ch = rec.charAt(endChar);
			if (ch == endQuote)
			{
				foundIt(_currentLine, endChar);
				endChar++;
				_txt += rec.substring(_currentChar, endChar);
				return true;
			}
		}
		
		return false;	// Found < but not >
	}
	
	@Override
	public String toString()
	{
		return _txt;
	}
	
	@Override
	public void setValue(String val)
	{
		_txt = val;
		_present = (val != null);
	}
	
	@Override
	public String showString()
	{
		return "Literal";
	}

	@Override
	public String description()
	{
		return "A string enclosed in quotes";
	}
}
