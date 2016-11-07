// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 7, 2012

package com.eagle.programmar.COBOL.Statements;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.programmar.COBOL.COBOL_AbstractStatement;
import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.tokens.TerminalToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class COBOL_CopyStatement extends COBOL_AbstractStatement
{
	public COBOL_Keyword COPY = new COBOL_Keyword("COPY");
	public COBOL_FileNameOrLiteral fileName;
	public @OPT COBOL_CopyReplacing replacing;
	
	public static class COBOL_CopyReplacing extends TokenSequence
	{
		public COBOL_Keyword REPLACING = new COBOL_Keyword("REPLACING");
		public TokenList<COBOL_CopyReplace> replacements;
		
		public static class COBOL_CopyReplace extends TokenSequence
		{
			public COBOL_Identifier_Reference from;
			public COBOL_Keyword BY = new COBOL_Keyword("BY");
			public COBOL_Identifier_Reference to;
		}
	}

	public static class COBOL_FileNameOrLiteral extends TokenChooser
	{
		public @CHOICE COBOL_FileName fileNmae;
		public @CHOICE COBOL_Literal literal;
	}
	
	public static class COBOL_FileName extends TerminalToken
	{
		private String id;
		
		@Override
		public boolean parse(EagleFileReader lines)
		{
			if (findStart(lines) == FOUND.EOF) return false;
			EagleLineReader rec = lines.get(_currentLine);
			int recLen = rec.length();
			char ch = rec.charAt(_currentChar);
			if (Character.isLetter(ch))
			{
				int endChar = _currentChar;
				while (true)
				{
					endChar++;
					if (endChar >= recLen) break;
					ch = rec.charAt(endChar);
					if (!Character.isLetterOrDigit(ch) && ch != '-' && ch != '.') break;
					if (ch == '.' && (endChar+1 == recLen || !Character.isLetterOrDigit(rec.charAt(endChar+1)))) break;
				}
				
				id = rec.substring(_currentChar, endChar);
				
				foundIt(_currentLine, endChar - 1);
				return true;
			}
			return false;
		}
		
		@Override
		public String toString()
		{
			return id;
		}
		
		@Override
		public void setValue(String val)
		{
			id = val;
		}
		
		@Override
		public String showString()
		{
			return "Filename";
		}

		@Override
		public String description()
		{
			return "A filename.";
		}

		@Override
		public String getDisplayStyleName()
		{
			return "identifier";
		}
	}
}
