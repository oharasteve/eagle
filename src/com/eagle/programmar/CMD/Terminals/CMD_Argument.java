// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 5, 2011

package com.eagle.programmar.CMD.Terminals;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.programmar.EagleSyntax;
import com.eagle.programmar.CMD.Symbols.CMD_Identifier_Reference;
import com.eagle.programmar.CMD.Terminals.CMD_Argument.CMD_ArgumentWrapper.CMD_RawArgument;
import com.eagle.tokens.TerminalLiteralToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

//
// This class is a little odd. It always creates a CMD_RawArgument while parsing
// But on Post Processing, it splits it apart into an array of CMD_ArgumentPiece's
//

public class CMD_Argument extends TokenSequence
{
	public CMD_ArgumentWrapper wrapper;
	
	// Only need the wrapper to keep everything together in one file
	public static class CMD_ArgumentWrapper extends TokenChooser
	{
		public static class CMD_RawArgument extends TerminalLiteralToken
		{
			@Override
			public boolean parse(EagleFileReader lines)
			{
				if (findStart(lines) == FOUND.EOF) return false;
				EagleLineReader rec = lines.get(_currentLine);
				int recLen = rec.length();
				if (_currentChar >= recLen) return false;
				char ch = rec.charAt(_currentChar);
				if (ch == ':' || ch == '@' || ch == '-' || ch == '<' || ch == '>' || ch == '|' || ch == '&' || ch == ')') return false;
				
				int offset = 0;
				char quote;
				if (ch == '"' || ch == '\'')
				{
					quote = ch;

					_endChar = _currentChar;
					while (true)
					{
						_endChar++;
						if (_endChar >= recLen) break;
						ch = rec.charAt(_endChar);
						if (ch == quote) break;
					}
					_txt = rec.substring(_currentChar + 1, _endChar);
				}
				else if (ch == '/')
				{
					// Allow /%%x
					if (_currentChar + 3 >= recLen) return false;
					if (rec.charAt(_currentChar+1) != '%') return false;
					if (rec.charAt(_currentChar+2) != '%') return false;
					ch = rec.charAt(_currentChar + 3);
					if (! Character.isLetter(ch)) return false;
					_endChar = _currentChar + 3;
				}
				else
				{
					// Plain argument, no quotes
					_endChar = _currentChar;
					while (true)
					{
						_endChar++;
						if (_endChar >= recLen) break;
						ch = rec.charAt(_endChar);
						if (ch == ' ' || ch == '<' || ch == '>' || ch == '|' || ch == '&' || ch == '(' || ch == ')') break;
						
						// Don't allow == in the middle of an argument
						if (ch == '=')
						{
							if (_endChar + 1 <= recLen)
							{
								if (rec.charAt(_endChar+1) == '=') break;
							}
						}
					}
					_txt = rec.substring(_currentChar, _endChar);

					// Make sure a generic argument is not a program name like SET or REM or whatever
					String word = _txt.toUpperCase();
					int dot = word.indexOf('.');
					if (dot > 0)
					{
						word = word.substring(0, dot);
					}
					EagleSyntax syntax = getSyntax();
					if (syntax.isReserved(word)) return false;
					offset = 1;
				}

				foundIt(_currentLine, _endChar - offset);
				return true;
			}
		}
	}
	
	public static class CMD_ArgumentPiece extends TokenChooser
	{
		public CMD_Punctuation quote = new CMD_Punctuation('"');
		public CMD_RawArgument literal;
		
		public static class CMD_ArgumentVariable extends TokenSequence
		{
			public CMD_Punctuation percent1 = new CMD_Punctuation('%');
			public CMD_Identifier_Reference var;
			public CMD_Punctuation percent2 = new CMD_Punctuation('%');
		}
		
		public static class CMD_ArgumentParament extends TokenSequence
		{
			public CMD_Punctuation percent = new CMD_Punctuation('%');
			public CMD_Number num;
		}
	}
}
