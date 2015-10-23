// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 1, 2010

package com.eagle.tokens;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.programmar.EagleSyntax;



/**
 * Different kinds of tokens
 */

public abstract class TerminalToken extends AbstractToken
{
	public abstract boolean parse(EagleFileReader lines);

	public final boolean parse(EagleFileReader lines, int startLine, int startChar)
	{
		lines.setCurrentLine(startLine);
		lines.setCurrentChar(startChar);
		return parse(lines);
	}

	protected enum FOUND { GOOD, EOLN, EOF }
	public boolean _matchesConstant = false;
	
	// Used by ViewProgram
	public abstract String getDisplayStyleName();
	
	// Used by TokenXML
	public abstract void setValue(String val);
	
	public String getValue()
	{
		return toString();
	}
	
	// These two are needed by ShowGrammar.
	public abstract String showString();
	public abstract String description();

	// For most languages, line breaks don't matter so continuationChar will be null
	// For some, like CMD, it will be a String = "\\" (length 1)
	protected FOUND findStart(EagleFileReader lines)
	{
		return findStart(lines, lines.getCurrentLine(), lines.getCurrentChar());
	}
	
	protected FOUND findStart(EagleFileReader lines, int startLine0, int startChar0)
	{
		//this.setSyntax(getParent().getSyntax());
		int startLine = startLine0;
		int startChar = startChar0;
		
		// For each line
		EagleSyntax syntax = this.getSyntax();
		while (true)
		{
			if (startLine >= lines.size()) break;	// End of file
			EagleLineReader rec = lines.get(startLine);
			int recLen = rec.length();
			
			// For each char
			while (true)
			{
				// At end of line?
				if (startChar >= recLen)
				{
					if (syntax != null && syntax._autoAdvance) break;	    // Get to the next line
					return FOUND.EOLN;						// No free motion to next line; must have a continuation char
				}

				char ch = rec.charAt(startChar);
				if (ch != ' ' && ch != '\t' && ch != '\f')
				{
					// First see if this is just a continuation marker
					if (syntax != null && syntax._continuationChar != null)
					{
						// Yes, fake end-of-line so it rolls over to the next line
						if (ch == syntax._continuationChar.charAt(0))
						{
							if (startChar + 1 >= recLen) break;	// At end-of-line
							char next = rec.charAt(startChar + 1);
							if (next == ' ' || next == '\t' || next == '\f') break;	// Followed by a space
						}
					}
					
					_currentLine = startLine;
					_currentChar = startChar;
					return FOUND.GOOD;
				}
				
				// Try next char
				startChar++;
			}
			
			// Try next line
			startLine++;
			startChar = 0;
		}

		// Must be at end of file ...
		return FOUND.EOF;
	}
	
	protected boolean matchWord(EagleFileReader lines, String word)
	{
		if (findStart(lines) == FOUND.EOF) return false;
		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		int endChar = _currentChar + word.length();
		if (endChar > recLen) return false;	// Not enough chars left on the line
		String piece = rec.substring(_currentChar, endChar);
		
		EagleSyntax syntax = this.getSyntax();
		if (syntax == null)
		{
			throw new RuntimeException("Syntax should not be null: " + this.toString());
		}
		if (syntax._isCaseSensitive)
		{
			if (!piece.equals(word)) return false;				// Java, for example
		}
		else
		{
			if (!piece.equalsIgnoreCase(word)) return false;	// COBOL, for example
		}

		// Make sure there isn't an alphabetic char just after it ...
		if (endChar < recLen)
		{
			char ch = rec.charAt(endChar);
			if (syntax._allowDigitsInKeywords)
			{
				if (Character.isLetterOrDigit(ch)) return false;
			}
			else
			{
				if (Character.isLetter(ch)) return false;
			}
			if (syntax._extraCharacters != null)
			{
				if (syntax._extraCharacters.indexOf(ch) >= 0) return false;			// - for COBOL, _ for Java, etc
			}
		}
		
		foundIt(_currentLine, _currentChar + word.length() - 1);
		return true;
	}
	
	@Override
	public void forceSyntax(EagleSyntax syntax)
	{
		setSyntax(syntax);
	}
}
