// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 22, 2014

package com.eagle.parsers;

import java.io.FileWriter;
import java.io.PrintWriter;

import com.eagle.programmar.EagleLanguage;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TerminalToken;
import com.eagle.utils.EagleUtilities;

public class EagleTracer
{
	private String _ignoreClassPrefix = "No way!";
	private static final int PIECE_LENGTH = 10;
	
	private String _htmlName;
	private boolean _toHtml;
	private PrintWriter _htmlFile;
	private int _htmlLines;
	
	private String _prevLine;
	private int _prevDepth;
	
	public EagleTracer()
	{
		_toHtml = false;
	}
	
	public EagleTracer(String fileName)
	{
		_htmlName = fileName;
		_toHtml = true;
		_htmlLines = 0;
		
		try
		{
			_htmlFile = new PrintWriter(new FileWriter(_htmlName));
		}
		catch (Exception ex)
		{
			throw new RuntimeException("Unable to write to " + _htmlName);
		}
	}

	public void header(ParserInterface parser, EagleLanguage lang)
	{
		String clsName = lang.getClass().getName();
		int pos = clsName.lastIndexOf('.');
		if (pos > 0) _ignoreClassPrefix = clsName.substring(0, pos+1);
		
		String hdr1 = "Depth    Seq  Syntax           Line  Char     Next       Pattern";
		String hdr2 = "=====  =====  ======           ====  ====  ==========  =================================================";

		if (_toHtml)
		{
			_htmlFile.println("<html>");
			_htmlFile.println("<head>");
			_htmlFile.println("<style type=\"text/css\">");
			_htmlFile.println("  pre { font-size: 10px; margin: 0;}");
			_htmlFile.println("</style>");
			_htmlFile.println("</head>");
			_htmlFile.println("<body>");
			_htmlFile.println("<h3>" + lang.getName() + " " + lang.getClass().getName() + "</h3>");
			_htmlFile.println("<h3>" + parser.getFileName() + "</h3>");
			
			_htmlFile.println("<pre>" + fix(hdr1) + "</pre>");
			_htmlFile.println("<pre>" + fix(hdr2) + "</pre>");
			
			_prevLine = null;
			_prevDepth = 0;
		}
		else
		{
			System.out.println("File: " + parser.getFileName());
			System.out.println("Lang: " + lang.getName() + " (" + lang.getClass().getName() + ")");
			System.out.println();
			System.out.println(hdr1);
			System.out.println(hdr2);
		}
	}
	
	public void detail(ParserInterface parser, int currDepth, String prefix, AbstractToken token, String msg, boolean matched)
	{
		String suffix = "";
		if (token instanceof TerminalToken)
		{
			String terminalString = ((TerminalToken)token).toString();
			if (terminalString != null)
			{
				suffix += " (" + terminalString + ")";
			}
		}
		
		int startLine = parser.getStartLine();
		int startChar = parser.getStartChar();
		String rec = parser.getLine(startLine);
		
		int nc = rec.length();
		String piece;
		if (startChar >= nc)
		{
			piece = EagleUtilities.lj("EOLN", PIECE_LENGTH);
		}
		else if (startChar + PIECE_LENGTH > nc)
		{
			int sc = (startChar < 0 ? 0 : startChar);
			piece = EagleUtilities.lj(rec.substring(sc), PIECE_LENGTH);
		}
		else
		{
			piece = rec.substring(startChar, startChar + PIECE_LENGTH);
		}
		
		String syntax = "";
		if (!matched)
		{
			syntax = token.getSyntax().syntaxId();
		}
		
		String clsName = token.getClass().getName();
		if (clsName.startsWith(_ignoreClassPrefix)) clsName = clsName.substring(_ignoreClassPrefix.length());
		int pos = clsName.lastIndexOf('$');
		String line = EagleUtilities.rj(currDepth+1, 5) + " " + EagleUtilities.rj(_htmlLines+1, 6) +
				"  " + EagleUtilities.lj(syntax, 15) + " " +
				EagleUtilities.rj(startLine+1,5) + " " + EagleUtilities.rj(startChar+1, 5) +
				"  " + piece + "  " + prefix + msg + clsName + suffix;
		if (pos > 0) clsName = clsName.substring(pos + 1);
		_htmlLines++;
		
		if (_toHtml)
		{
			String currLine = "<pre>" + fix(line) + "</pre>";

			if (currDepth > _prevDepth)
			{
				currLine = "<details><summary>" + currLine + "</summary>";
			}
			else if (currDepth < _prevDepth)
			{
				currLine = "</details>" + currLine;
			}
			
			if (_prevLine != null)
			{
				_htmlFile.println(_prevLine);
			}
			_prevLine = currLine;
			_prevDepth = currDepth;
		}
		else
		{
			System.out.println(line);
		}
	}
	
	private static String fix(String line)
	{
		return EagleUtilities.fixHtml(line).replaceAll("\n", "");
	}
	
	public void done()
	{
		if (_toHtml)
		{
			if (_prevLine != null)
			{
				_htmlFile.println("</detail>" + _prevLine);
				_htmlLines++;
			}

			_htmlFile.println("</body>");
			_htmlFile.println("</html>");
			_htmlFile.close();
			System.out.println("Finished writing debug info to " + _htmlName + " lines=" + _htmlLines);
		}
	}
}
