// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 2, 2015

package com.eagle.parsers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.eagle.programmar.DeTabber;
import com.eagle.programmar.EagleLanguage;
import com.eagle.programmar.EagleSyntax;
import com.eagle.project.EagleProject;
import com.eagle.tokens.AbstractToken;

public class ParserManager
{
	public EagleParser _parser = new EagleParser();

	private static final char UTF8_BOM = '\uFEFF';
	
	public boolean _convertTabs = false;
	public int _tabSize = 0;
	
	/**
	 * Just read the whole file into the EagleFileReader structure
	 */
	public EagleFileReader readWholeFile(String fullName)
	{
		// Read the whole file into an array
		BufferedReader br = null;
		EagleFileReader lines = new EagleFileReader();
		try
		{
			br = new BufferedReader(new InputStreamReader(new FileInputStream(fullName), "UTF-8"));
			String rec;
			while ((rec = br.readLine()) != null)
			{
				if (lines.size() == 0)
				{
					//System.out.println("*****" + (0+rec.charAt(0)) + "****" + rec.charAt(1) + rec.charAt(2));
					if (rec.length() > 0 && rec.charAt(0) == UTF8_BOM)
					{
						rec = rec.substring(1);	// Toss the BOM away
					}
				}
				
				EagleLineReader line = new EagleLineReader(rec);
				lines.add(line);

				if (_convertTabs && rec.indexOf('\t') >= 0)
				{
					rec = DeTabber.deTab(rec, _tabSize);
					line.change(rec, "Replace all tabs with spaces (" + _tabSize + ").");
				}
				
				if (rec.indexOf(0x1a) >= 0)	// ASCII SUB is a 26 decimal
				{
					rec = rec.replaceAll("\\x1a", "");
					line.change(rec, "Remove ASCII SUB (0x1a) codes.");
				}
			}
			br.close();
		}
		catch (IOException ex)
		{
			throw new EagleParseException("Unable to read file '" + fullName + "'", ex);
		}
		return lines;
	}

	/**
	 * Parse the file and create a 'Language' object
	 */
	public boolean parseFile(EagleProject project, String fullName, EagleLanguage lang)
	{
		File file = new File(fullName);
		EagleFileReader lines = readWholeFile(fullName);
		String canonicalName = fullName;
		try
		{
			canonicalName = file.getCanonicalPath();
		}
		catch (IOException ex)
		{
			throw new RuntimeException("Unable to get canonical name for " + fullName, ex);
		}

		// Manual changes to the file (in memory, not really in the file)
		if (project != null)
		{
			project.performRepairs(fullName, lines);
		}

		return _parser.parse(project, lang, canonicalName, lines);
	}
	
	/**
	 * Parse a single line
	 */

	public boolean parseLine(String line, EagleLanguage lang, AbstractToken token, EagleSyntax syntax)
	{
		token.setSyntax(syntax);
		EagleFileReader lines = new EagleFileReader();
		lines.add(line);
		try
		{
			return _parser.quickParse(null, lines, lang, token);
		}
		catch (Exception ex)
		{
			String msg = _parser.getHighestPosition(null);
			throw new EagleParseException(msg, ex);
		}
	}
	
	/**
	 * Parse an array of Strings
	 */
	public boolean parseLines(EagleFileReader lines, EagleLanguage lang, AbstractToken token)
	{
		try
		{
			return _parser.quickParse(null, lines, lang, token);
		}
		catch (Exception ex)
		{
			String msg = _parser.getHighestPosition(null);
			throw new EagleParseException(msg, ex);
		}
	}
}
