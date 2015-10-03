// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 14, 2011

package com.eagle.programmar;

public class EagleSymbolException extends RuntimeException
{
	private static final long serialVersionUID = 1;
	
	public EagleSymbolException(String msg)
	{
		super(msg);
	}
	
	public EagleSymbolException(String msg, Exception ex)
	{
		super(msg, ex);
	}
}
