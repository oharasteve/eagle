// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 2, 2015

package com.eagle.project;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;


public class RepairFile
{
	static class RepairItem
	{
		public String _fileName;
		public int _lineNumber;
		public String _pattern;
		public String _replacement;
		public String _explanation;
	}
	
	private ArrayList<RepairItem> repairs = null;

	//
	// Manual edit changes
	//
	public void repair(String fileName, Integer lineNumber, String pattern, String replacement, String explanation)
	{
		// First repair for this project?
		if (repairs == null)
		{
			repairs = new ArrayList<RepairItem>();
		}
		
		// Add to the list of repairs
		RepairItem item = new RepairItem();
		item._fileName = fileName;
		item._lineNumber = lineNumber;
		item._pattern = pattern;
		item._replacement = replacement;
		item._explanation = explanation;
		repairs.add(item);
	}
	
	private static final int NEIGHBORHOOD = 30;
	
	public void performRepairs(String fileName, EagleFileReader lines)
	{
		if (repairs == null) return;
		String normalized = fileName.replaceAll("\\\\", "/");
		for (RepairItem item : repairs)
		{
			if (normalized.endsWith(item._fileName))
			{
				int seq = item._lineNumber - 1;
				if (seq >= lines.size())
				{
					throw new RuntimeException("Expected change has line number " + item._lineNumber + " which is too big (max = " + lines.size() + ")");
				}
				
				EagleLineReader line = lines.get(seq);
				String oldRec = line.toString();
				Matcher matcher = Pattern.compile(item._pattern).matcher(oldRec);
				if (!matcher.find())
				{
					throw new RuntimeException("Expected change on line " + item._lineNumber + " of " + normalized + " (" + item._pattern + ") failed.\n" +
							"    ^" + oldRec.toString() + "$");
				}
				
				// Perform the repair!
				String newRec = matcher.replaceFirst(item._replacement);
				line.change(newRec, item._explanation);
				System.out.println("**** Changed line " + item._lineNumber + " of " + normalized + " due to: " + item._explanation);

				int pos = matcher.start();
				int oldLen = oldRec.length();
				int newLen = newRec.length();
				if ((oldLen > 2*NEIGHBORHOOD || newLen > 2*NEIGHBORHOOD) && pos >= 0)
				{
					int sc = pos < NEIGHBORHOOD ? 0 : pos - NEIGHBORHOOD;
					int oldEc = sc + 2*NEIGHBORHOOD + item._pattern.length();
					if (oldEc > oldLen) oldEc = oldLen;
					System.out.println("     from ..." + oldRec.substring(sc, oldEc) + "...");
					int newEc = sc + 2*NEIGHBORHOOD + item._replacement.length();
					if (newEc > newLen) newEc = newLen;
					System.out.println("      to  ..." + newRec.substring(sc, newEc) + "...");
					System.out.println("sc=" + sc + " oldEc=" + oldEc + " newEc=" + newEc);
				}
				else
				{
					System.out.println("     from " + oldRec);
					System.out.println("      to  " + newRec);
				}
			}
		}
	}
}
