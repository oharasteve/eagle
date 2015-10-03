// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

package com.eagle.programmar.CMacro;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.parsers.EagleOverrideManager;
import com.eagle.programmar.EagleLanguage;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.CMacro.Statements.CMacro_IfDef_Statement.CMacro_IfDefElement;
import com.eagle.programmar.CMacro.Statements.CMacro_If_Statement.CMacro_IfElement;
import com.eagle.programmar.CMacro.Terminals.CMacro_EndOfLine;
import com.eagle.tokens.TerminalLiteralToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CMacro_Program extends EagleLanguage
{
	public static final String NAME = "CMacro";
	
	public CMacro_Program()
	{
		super(NAME, new CMacro_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "http://gcc.gnu.org/onlinedocs/cpp/";
	}
	
	@Override
	public void findClassOverrides(EagleOverrideManager overrideManager)
	{
		// Instead of creating a bunch of real C statements inside a #if, just use this simple class
		overrideManager.override(CMacro_IfElement.class, CMacro_Element.class);
		overrideManager.override(CMacro_IfDefElement.class, CMacro_Element.class);
	}
	
	public TokenList<CMacro_Element> elements;
	
	public static class CMacro_Element extends TokenChooser
	{
		public CMacro_CommentLine comment;
		public CMacro_Statement stmt;
		public CMacro_TextLine textLine;
		public @LAST CMacro_EndOfLine endOfLine;
	}
	
	public static class CMacro_CommentLine extends TokenSequence
	{
		public C_Comment comment;
		public CMacro_EndOfLine endOfLine;
	}
	
	public static class CMacro_TextLine extends TerminalLiteralToken
	{
		@Override
		public boolean parse(EagleFileReader lines)
		{
			// Don't allow C lines to start with a #
			if (findStart(lines) == FOUND.EOF) return false;
			EagleLineReader rec = lines.get(_currentLine);
			int recLen = rec.length();
			if (recLen < _currentChar) return false;
			if (_currentChar < recLen && rec.charAt(_currentChar) == '#')
			{
				// Check to make sure we are at the start of a line. This check may be superfluous.
				// Normally, _currentChar = 0 for a macro line that starts with #
				// In that case, the loop doesn't even execute once so it fails as a text line.
				boolean atStart = true;
				for (int i = 0; i < _currentChar; i++)
				{
					char ch = rec.charAt(i);
					if (ch != ' ' && ch != '\t')
					{
						atStart = false;
						break;
					}
				}
				if (atStart) return false;
			}
			
			foundIt(_currentLine, recLen);
			_txt = rec.substring(_currentChar, recLen);
			return true;
		}
	}
}