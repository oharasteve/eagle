// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 29, 2015

package com.eagle.programmar.CMacro.Terminals;

import com.eagle.math.EagleValue;
import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleParser;
import com.eagle.programmar.CMacro.CMacro_Expression;
import com.eagle.programmar.CMacro.CMacro_Program;
import com.eagle.programmar.CMacro.CMacro_Syntax;
import com.eagle.tests.EagleInterpreter;
import com.eagle.tests.EagleRunnable;
import com.eagle.tokens.TokenRestOfLine;

public class CMacro_RestOfLine extends TokenRestOfLine implements EagleRunnable
{
	@Override
	public boolean parse(EagleFileReader lines)
	{
		if (findStart(lines) == FOUND.EOF) return false;

		String rec = lines.get(_currentLine).toString();
		int linesSize = lines.size();
		int lastLine = _currentLine;
		int recLen = rec.length();
		if (_currentChar >= recLen) return false;

		int sc = _currentChar;
		int ec;
		_txt = null;
		while (true)
		{
			// Look for comments
			ec = recLen - 1;
			int commentPos = rec.indexOf("/*", sc);
			if (commentPos >= 0)
			{
				int endCommentPos = rec.indexOf("*/", commentPos);
				if (endCommentPos > 0)
				{
					// Have to remove the comment and keep going
					StringBuffer sb = new StringBuffer(rec.substring(0, commentPos));
					for (int i = commentPos; i < endCommentPos + 2; i++) sb.append(' ');
					sb.append(rec.substring(endCommentPos+2));
					rec = sb.toString();
				}
				else
				{
					// Macro ends in a multi-line comment! Oh no.
					_txt = rec.substring(sc, commentPos);
					
					// Must be continued on the next line
					while (lastLine < lines.size())
					{
						lastLine++;
						rec = lines.get(lastLine).toString();
						ec = rec.indexOf("*/");
						if (ec >= 0) break;
					}
					foundIt(lastLine, ec+1);
					return true;
				}
			}
			else
			{
				commentPos = rec.indexOf("//", _currentChar);
				if (commentPos >= 0) ec = commentPos - 1;
			}
			
			// Build the new result, one line at a time
			String piece = rec.substring(sc, ec + 1);
			if (_txt == null)
			{
				_txt = piece;
			}
			else
			{
				// Chop off the trailing \
				_txt = _txt.substring(0, _txt.length()-1) + "\n" + piece;
			}
			
			// Not continued (any more)
			if (! rec.endsWith("\\") || lastLine+1 >= linesSize) break;
			
			// Must be continued on the next line
			lastLine++;
			rec = lines.get(lastLine).toString();
			recLen = rec.length();
			sc = 0;
		}
		
		foundIt(lastLine, ec);
		return true;
	}

	@Override
	public void interpret(EagleInterpreter interpreter)
	{
		// Blank _txt means return ""
		if (_txt.trim().length() == 0)
		{
			interpreter.pushStr("");
			return;
		}
		
		// Have to parse it, in order to evaluate it
		EagleParser parser = new EagleParser();
		CMacro_Program lang = new CMacro_Program();
		CMacro_Syntax syntax = new CMacro_Syntax();
		EagleFileReader lines = new EagleFileReader();
		lines.add(_txt);
		
		CMacro_Expression expr = new CMacro_Expression();
		expr.setSyntax(syntax);
		if (! parser.quickParse(lines, lang, expr))
		{
			throw new RuntimeException("Unable to parse expression " + _txt);
		}
	
		// Evaluate the newly parsed expression
		EagleValue val = interpreter.getEagleValue(expr);
		interpreter.pushEagleValue(val);
	}
}
