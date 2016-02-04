// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Feb 1, 2016

package com.eagle.io;

public class AbbreviateClassName
{
	public static final String CLASS_PREFIX1 = "com.eagle.programmar";
	public static final String CLASS_PREFIX2 = "com.eagle.tokens.punctuation.Punctuation";
	
	public static final String CLASS_TOKENLIST = "com.eagle.tokens.TokenList";
	public static final String LIST = "List";

	public static String abbrev(String fullName)
	{
		if (fullName.startsWith(CLASS_PREFIX1))
		{
			// Toss the leading "com.eagle.programmar"
			return fullName.substring(CLASS_PREFIX1.length());
		}
		
		if (fullName.startsWith(CLASS_PREFIX2))
		{
			// Replace the leading "com.eagle.tokens.punctuation." with ","
			return "," + fullName.substring(CLASS_PREFIX2.length());
		}
		
		if (fullName.equals(CLASS_TOKENLIST))
		{
			return LIST;
		}
		
		// No change
		return fullName;
	}
	
	public static String restore(String shortName)
	{
		if (shortName.startsWith("."))
		{
			return CLASS_PREFIX1 + shortName;
		}
		
		if (shortName.startsWith(","))
		{
			return CLASS_PREFIX2 + shortName.substring(1);
		}
		
		if (shortName.equals(LIST))
		{
			return CLASS_TOKENLIST;
		}
		
		// No change
		return shortName;
	}
}
