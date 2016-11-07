// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Oct 2, 2015

package com.eagle.softwarehouse;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PercentComplete
{
	private int _total;
	private int _tenthsComplete;
	private PrintStream _out;
	
	public PercentComplete(PrintStream out, String msg, int total)
	{
		_total = total;
		_tenthsComplete = 0;
		_out = out;
		
		Date now = new Date();
		DateFormat df = new SimpleDateFormat("HH:mm:ss");
		out.print(df.format(now) + "  " + msg + " (% of " + total + "): 0");
		out.flush();
	}
	
	public void update(int count)
	{
		int tenths = (10 * count) / _total;
		if (tenths > _tenthsComplete)
		{
			_out.print(" " + tenths + "0");
			_out.flush();
			_tenthsComplete = tenths;
		}
	}
}
