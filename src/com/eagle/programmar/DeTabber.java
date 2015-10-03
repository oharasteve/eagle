// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 4, 2010

package com.eagle.programmar;


/**
 * Convert tabs to spaces
 */

public class DeTabber
{
	/**
	 * Remove all the tabs from a string
	 */
	public static String deTab(String rec, int tabSize)
	{
		if (rec == null) return null;
		
		StringBuffer sb = new StringBuffer();
		int posTab;
		int alreadyProcessed = 0;
		
		while ((posTab = rec.indexOf('\t', alreadyProcessed)) >= 0)
		{
			// Here are the rules. Suppose tabSize is 4
			// If current length = 7, need to add 1 space
			//                     8              4 spaces
			//                     9              3
			//                    10              2
			//                    11              1
			// etc.
			sb.append(rec.substring(alreadyProcessed, posTab));
			int currLen = sb.length();
			int spacesToAdd = tabSize - (currLen % tabSize);
			for (int i = 0; i < spacesToAdd; i++) sb.append(' ');
			alreadyProcessed = posTab + 1;
		}

		sb.append(rec.substring(alreadyProcessed, rec.length()));
		return sb.toString();
	}
	
	public static String deTab(String rec)
	{
		return deTab(rec, 4);	// Default tabSize to 4
	}
}
