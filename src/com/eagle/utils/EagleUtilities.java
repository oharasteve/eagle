// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 14, 2010

package com.eagle.utils;


public class EagleUtilities
{
	public static final String SPACES = "                                                      ";

	public static String rj(long i, int nc)
	{
		String s = Long.toString(i);
		int nspaces = nc - s.length();
		if (nspaces < 0) return s;
		return SPACES.substring(0, nspaces) + s;
	}
	
	public static String lj(String str, int nc)
	{
		String s = str;
		if (s == null) s = "NULL";	// Shouldn't need this ....
		int sLen = s.length();
		if (sLen > nc) return "*" + s.substring(sLen-nc+1, sLen);	// Too long? Keep rightmost part
		return s + SPACES.substring(0, nc - sLen);
	}

	public static String lj(long i, int nc)
	{
		return lj(Long.toString(i), nc);
	}
	
	public static String fixHtml(String s)
	{
		StringBuffer sb = new StringBuffer();
		for (char c : s.toCharArray())
		{
			switch (c)
			{
			case '&':
				sb.append("&amp;");
				break;
			case '"':
				sb.append("&quot;");
				break;
			case '<':
				sb.append("&lt;");
				break;
			case '>':
				sb.append("&gt;");
				break;
			default:
				sb.append(c);
				break;
			}
		}
		return sb.toString();
		
//		return s.replaceAll("&", "&amp;")
//				.replaceAll("\"", "&quot;")
//				.replaceAll("<", "&lt;")
//				.replaceAll(">", "&gt;");
	}
	
	public static String undoHtml(String s)
	{
		if (s.indexOf('&') < 0) return s;
		return s.replaceAll("&amp;", "&")
				.replaceAll("&lt;", "<")
				.replaceAll("&gt;", ">")
				.replaceAll("&quot;", "\"");
	}
	
	public static String fixAscii(String s)
	{
		StringBuffer sb = new StringBuffer();
		for (char c : s.toCharArray())
		{
			if (c < ' ' || c > '~')
			{
				sb.append('?');
			}
			else
			{
				sb.append(c);
			}
		}
		return sb.toString();
	}
}
