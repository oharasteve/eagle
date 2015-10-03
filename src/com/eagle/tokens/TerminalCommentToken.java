// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 8, 2011

package com.eagle.tokens;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;


public abstract class TerminalCommentToken extends TerminalToken
{
	protected String _comment;
	protected boolean _hasEOLN = false;
	
	public TerminalCommentToken(String comment, boolean hasEOLN)
	{
		this(comment);
		_hasEOLN = hasEOLN;
	}

	public TerminalCommentToken(String comment)
	{
		_comment = comment;
	}

	@Override
	public String toString()
	{
		if (_hasEOLN) return _comment + '\n';
		return _comment;
	}
	
	@Override
	public String getValue()
	{
		return _comment;
	}
	
	@Override
	public void setValue(String val)
	{
		_comment = val;
	}
	
	@Override
	public String showString()
	{
		return "Comment";
	}

	@Override
	public String description()
	{
		return "Comment";
	}

	@Override
	public String getDisplayStyleName()
	{
		return "comment";
	}
	
	// Delphi example { ... }
	protected boolean possibleCommentPair1(EagleFileReader lines, EagleLineReader rec0, char firstMarker, char lastMarker)
	{
		EagleLineReader rec = rec0;
		if (rec.charAt(_currentChar) != firstMarker) return false;	// Redundant check
		
		// Is the end on the same line?
		int ec = rec.indexOf(lastMarker, _currentChar + 1);
		if (ec >= 0)
		{
			// Yes! Whew!
			foundIt(_currentLine, ec);
			_comment = rec.substring(_currentChar, ec).trim();
			return true;
		}
		
		// Oh dang ... multi-line comment
		_comment = rec.substring(_currentChar) + "\n";
		int lastLine = _currentLine + 1;
		int numberLines = lines.size();
		while (lastLine < numberLines)
		{
			rec = lines.get(lastLine);
			ec = rec.indexOf(lastMarker);
			if (ec >= 0)
			{
				foundIt(lastLine, ec);
				_comment += rec.substring(0, ec).trim();
				return true;
			}
			_comment += rec + "\n";
			lastLine++;
		}
		throw new RuntimeException("End of comment missing " + firstMarker + " ... " + lastMarker);
	}
	
	// Delphi example: //
	protected boolean possibleCommentToEndOfLine(EagleLineReader rec, String marker)
	{
		int markerLen = marker.length();
		if (markerLen < 1 || markerLen > 2) throw new RuntimeException("Comment marker must have length one or two.");
		if (rec.charAt(_currentChar) != marker.charAt(0)) return false;	// Redundant check

		int nc = rec.length();
		if (_currentChar + markerLen - 1 >= nc) return false;
		if (markerLen == 1 || rec.charAt(_currentChar + 1) == marker.charAt(1)) {
			foundIt(_currentLine, nc-1);
			_comment = rec.substring(_currentChar, nc);
			_hasEOLN = true;
			return true;
		}
		return false;
	}
	
	// Delphi example: (* ... *)
	protected boolean possibleCommentPair2(EagleFileReader lines, EagleLineReader rec0, String firstMarker, String lastMarker)
	{
		EagleLineReader rec = rec0;
		int nc = rec.length();
		if (_currentChar + 1 >= nc) return false;

		if (firstMarker.length() != 2) throw new RuntimeException("Comment first marker must have length two.");
		if (lastMarker.length() != 2) throw new RuntimeException("Comment last marker must have length two.");
		if (rec.charAt(_currentChar) != firstMarker.charAt(0)) return false;	// Redundant check
		if (rec.charAt(_currentChar + 1) != firstMarker.charAt(1)) return false;

		// Is the end on the same line?
		int ec = rec.indexOf(lastMarker, _currentChar + 2);
		if (ec >= 0)
		{
			// Yes! Whew!
			foundIt(_currentLine, ec+1);
			_comment = rec.substring(_currentChar, ec+2).trim();
			return true;
		}
		
		// Oh dang ... multi-line comment
		_comment = rec.substring(_currentChar) + "\n";
		int lastLine = _currentLine + 1;
		int numberLines = lines.size();
		while (lastLine < numberLines)
		{
			rec = lines.get(lastLine);
			ec = rec.indexOf(lastMarker);
			if (ec >= 0)
			{
				foundIt(lastLine, ec+1);
				_comment += rec.substring(0, ec+2);
				return true;
			}
			_comment += rec + "\n";
			lastLine++;
		}
		throw new RuntimeException("End of comment missing " + firstMarker + " ... " + lastMarker);
	}
	
	// Python example: """ ... """)
//	protected boolean possibleCommentPair3(EagleFileReader lines, String rec, String firstMarker, String lastMarker)
//	{
//		if (firstMarker.length() != 3) throw new RuntimeException("Comment first marker must have length three.");
//		if (lastMarker.length() != 3) throw new RuntimeException("Comment last marker must have length three.");
//		if (rec.charAt(_currentChar) != firstMarker.charAt(0)) return false;	// Redundant check
//		int nc = rec.length();
//		if (_currentChar + 2 >= nc) return false;
//		if (rec.charAt(_currentChar + 1) != firstMarker.charAt(1)) return false;
//		if (rec.charAt(_currentChar + 2) != firstMarker.charAt(2)) return false;
//
//		// Is the end on the same line?
//		int ec = rec.indexOf(lastMarker, _currentChar + 2);
//		if (ec >= 0)
//		{
//			// Yes! Whew!
//			foundIt(_fileName, _currentLine, ec+3);
//			_comment = rec.substring(_currentChar, ec+3).trim();
//			return true;
//		}
//		
//		// Oh dang ... multi-line comment
//		_comment = rec.substring(_currentChar) + "\n";
//		int lastLine = _currentLine + 1;
//		int numberLines = lines.size();
//		while (lastLine < numberLines)
//		{
//			rec = lines.get(lastLine);
//			ec = rec.indexOf(lastMarker);
//			if (ec >= 0)
//			{
//				foundIt(_fileName, lastLine, ec+3);
//				_comment += rec.substring(0, ec+3);
//				return true;
//			}
//			_comment += rec + "\n";
//			lastLine++;
//		}
//		throw new RuntimeException("End of comment missing " + firstMarker + " ... " + lastMarker);
//	}

	protected boolean possibleHtmlComment(EagleFileReader lines, EagleLineReader rec0, String firstMarker, String lastMarker)
	{
		EagleLineReader rec = rec0;
		if (firstMarker.length() != 4) throw new RuntimeException("Comment first marker must have length four.");
		if (lastMarker.length() != 3) throw new RuntimeException("Comment last marker must have length three.");
		int nc = rec.length();
		if (_currentChar + 3 >= nc) return false;	// Need room for four characters
		
		if (rec.charAt(_currentChar) == firstMarker.charAt(0) &&
				rec.charAt(_currentChar + 1) == firstMarker.charAt(1) &&
				rec.charAt(_currentChar + 2) == firstMarker.charAt(2) &&
				rec.charAt(_currentChar + 3) == firstMarker.charAt(3))
		{
			// Is the end on the same line?
			int ec = rec.indexOf(lastMarker, _currentChar);
			if (ec >= 0)
			{
				// Yes! Whew!
				foundIt(_currentLine, ec + 2);
				_comment = rec.substring(_currentChar, ec + 3);
				return true;
			}
			
			// Oh dang ... multi-line comment
			_comment = rec.substring(_currentChar) + "\n";
			int lastLine = _currentLine + 1;
			int numberLines = lines.size();
			while (lastLine < numberLines)
			{
				rec = lines.get(lastLine);
				ec = rec.indexOf(lastMarker);
				if (ec >= 0)
				{
					foundIt(lastLine, ec + 2);
					_comment += rec.substring(0, ec + 3);
					return true;
				}
				_comment += rec + "\n";
				lastLine++;
			}
			throw new RuntimeException("End of comment missing " + firstMarker + " ... " + lastMarker);
		}
		return false;
	}
}
