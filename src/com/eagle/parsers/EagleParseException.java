// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 14, 2011

package com.eagle.parsers;

public class EagleParseException extends RuntimeException
{
	private static final long serialVersionUID = 1;
	  
	public EagleParseException(String msg)
	{
		super(msg);
	}
	
	public EagleParseException(String msg, Exception ex)
	{
		super(msg, ex);
	}
	
	public static class EagleSoftParseException extends EagleParseException
	{
		private static final long serialVersionUID = 1;
		  
		public EagleSoftParseException(String msg)
		{
			super(msg);
		}
	}
}
