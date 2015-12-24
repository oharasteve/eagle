// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 19, 2012

package com.eagle.programmar.Delphi;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.programmar.EagleLanguage;
import com.eagle.programmar.EagleSyntax;
import com.eagle.tokens.TerminalEndOfLine;
import com.eagle.tokens.TerminalLiteralToken;
import com.eagle.tokens.TerminalPunctuationToken;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Delphi_Configuration extends EagleLanguage
{
	public static final String NAME = "Delphi_Configuration";
	
	public static class Delphi_Config_Syntax extends EagleSyntax
	{
		@Override
		public String syntaxId()
		{
			return "Delphi Config";
		}
		
		public Delphi_Config_Syntax()
		{
			_isCaseSensitive = false;
			_continuationChar = "\\";
			_extraCharacters = "";
			_autoAdvance = false;
		}
	}
	
	public static class Delphi_Config_EndOfLine extends TerminalEndOfLine
	{
	}

	public Delphi_Configuration()
	{
		super(NAME, new Delphi_Config_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return null;
	}
	
	public @OPT Delphi_Config_EndOfLine eoln;
	public TokenList<Delphi_Config_Block> blocks;
	
	public static class Delphi_Config_Block extends TokenSequence
	{
		public Delphi_Config_Punctuation leftBracket = new Delphi_Config_Punctuation('[');
		public @OPT Delphi_Config_Value blockName = new Delphi_Config_Value("]");
		public Delphi_Config_Punctuation rightBracket = new Delphi_Config_Punctuation(']');
		public Delphi_Config_EndOfLine eoln;
		public TokenList<Delphi_Config_Line> lines;
		
		public static class Delphi_Config_Line extends TokenSequence
		{
			public @OPT Delphi_Config_Value id = new Delphi_Config_Value("=]");
			public Delphi_Config_Punctuation equals = new Delphi_Config_Punctuation('=');
			public @OPT Delphi_Config_Value value = new Delphi_Config_Value("]");
			public Delphi_Config_EndOfLine eoln;
		}
	}
	
	public static class Delphi_Config_Value extends TerminalLiteralToken
	{
		String _stoppers;
		
		public Delphi_Config_Value()
		{
			this("");
		}
		
		public Delphi_Config_Value(String stoppers)
		{
			_stoppers = stoppers;
		}
		
		@Override
		public boolean parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF) return false;
			EagleLineReader rec = lines.get(_currentLine);
			int recLen = rec.length();
			if (_currentChar >= recLen) return false;
			char ch = rec.charAt(_currentChar);
			if (_stoppers.indexOf(ch) >= 0) return false;
			
			_endChar = _currentChar;
			while (true)
			{
				_endChar++;
				if (_endChar >= recLen) break;
				ch = rec.charAt(_endChar);
				if (_stoppers.indexOf(ch) >= 0) break;
			}
			_txt = rec.substring(_currentChar, _endChar-1);

			foundIt(_currentLine, _endChar-1);
			return true;
		}
	}
	
	public static class Delphi_Config_Punctuation extends TerminalPunctuationToken
	{
		// Need default constructor for reading from the XML file
		public Delphi_Config_Punctuation()
		{
			this('\0');
		}

		public Delphi_Config_Punctuation(char punct)
		{
			super(punct);
		}
	}
}
