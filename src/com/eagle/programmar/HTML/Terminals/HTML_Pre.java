// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 7, 2011

package com.eagle.programmar.HTML.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.tokens.TerminalCommentToken;

public class HTML_Pre extends TerminalCommentToken
{
	// Need a default constructor for the parser
	public HTML_Pre()
	{
		this("");
	}
	
	public HTML_Pre(String text)
	{
		super(text);
	}
	
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		
		EagleLineReader rec = lines.get(_currentLine);
		int nc = rec.length();
		if (_currentChar + 3 >= nc) return false;	// Need room for a fourth character

		if (rec.charAt(_currentChar) != '<') return false;
		if (rec.charAt(_currentChar + 1) != 'p') return false;
		if (rec.charAt(_currentChar + 2) != 'r') return false;
		if (rec.charAt(_currentChar + 3) != 'e') return false;

		// Is the end on the same line?
		int ec = rec.indexOf("</pre>", _currentChar + 4);
		if (ec >= 0)
		{
			// Yes! Whew!
			foundIt(_currentLine, ec + 2);
			_comment = rec.substring(_currentChar + 3, ec).trim();
			return true;
		}
		
		// Oh dang ... multi-line comment
		_comment = rec.substring(_currentChar + 4).trim() + "\n";
		int lastLine = _currentLine + 1;
		int numberLines = lines.size();
		while (lastLine < numberLines)
		{
			String next = lines.get(lastLine).toString();
			ec = next.indexOf("</pre>");
			if (ec >= 0)
			{
				foundIt(lastLine, ec + 5);
				_comment += next.substring(0, ec).trim();
				return true;
			}
			_comment += next.trim() + "\n";
			lastLine++;
		}
		throw new RuntimeException("End of HTML <pre>: missing </pre>");
	}
	
	@Override
	public String showString()
	{
		return "pre";
	}

	@Override
	public String description()
	{
		return "Preformmated string";
	}
}
