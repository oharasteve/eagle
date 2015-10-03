// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 25, 2014

package com.eagle.parsers;

import java.util.ArrayList;
import java.util.Collection;

// Represents all the lines in a file
public class EagleFileReader
{
	private ArrayList<EagleLineReader> _lines = new ArrayList<EagleLineReader>();
	private int _currentLine = 0;
	private int _currentChar = 0;
	
	public EagleFileReader()
	{
	}
	
	public EagleFileReader(String[] initialStrings)
	{
		for (String s : initialStrings)
		{
			add(s);
		}
	}
	
	public int getCurrentLine()
	{
		return _currentLine;
	}
	
	public void setCurrentLine(int currentLine)
	{
		_currentLine = currentLine;
	}
	
	public int getCurrentChar()
	{
		return _currentChar;
	}
	
	public void setCurrentChar(int currentChar)
	{
		_currentChar = currentChar;
	}
	
	public EagleLineReader get(int line)
	{
		return _lines.get(line);
	}
	
	public int size()
	{
		return _lines.size();
	}
	
	public Collection<EagleLineReader> lines()
	{
		return _lines;
	}
	
	public void add(EagleLineReader line)
	{
		_lines.add(line);
	}
	
	public void add(String rec)
	{
		_lines.add(new EagleLineReader(rec));
	}
	
	// Replace given line with the whole file
	public void expandFile(int line, EagleFileReader includeFile, String explanation)
	{
		EagleLineReader oldLine = _lines.get(line);

		// If replacing with an empty file, keep an empty line in its place
		if (includeFile.size() == 0)
		{
			oldLine.change("", explanation);
			return;
		}
		
		_lines.remove(line);
		int seq = line;
		for (EagleLineReader includeLine : includeFile.lines())
		{
			EagleLineReader newLine = new EagleLineReader(oldLine.toString());
			newLine.change(includeLine.toString(), explanation);
			_lines.add(seq, newLine);
			seq++;
		}
	}
}
