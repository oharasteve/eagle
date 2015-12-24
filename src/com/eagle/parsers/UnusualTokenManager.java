// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 2, 2015

package com.eagle.parsers;

import com.eagle.tokens.AbstractToken;
import com.eagle.utils.EagleUtilities;

public class UnusualTokenManager
{
	private static final int MAX_CURIOSITIES = 500;
	private int _curiosities = 0;
	private AbstractToken[] _curiousToken = new AbstractToken[MAX_CURIOSITIES];
	private String[] _curiousReason = new String[MAX_CURIOSITIES];

	private static final int REASON_WIDTH = 20;
	private static final int LINE_WIDTH = 5;
	
	public void setCuriosities(int count)
	{
		_curiosities = count;
	}
	
	public int getCuriousities()
	{
		return _curiosities;
	}
	
	public void addCuriosity(AbstractToken token, String reason)
	{
		if (_curiosities < MAX_CURIOSITIES)
		{
			_curiousToken[_curiosities] = token;
			_curiousReason[_curiosities] = reason;
		}
		_curiosities++;
	}
	
	// Print out the first few @CURIOUS elements
	public String allCuriousElements(EagleFileReader lines)
	{
		if (_curiosities == 0) return null;
		
		StringBuffer result = new StringBuffer();
		result.append(" Line  Reason                Curious Element\n");
		result.append("=====  ====================  ==================================================\n");
		for (int i = 0; i < _curiosities; i++)
		{
			if (i >= MAX_CURIOSITIES)
			{
				result.append("    (remaining curious elements skipped)\n");
				break;
			}
			
			AbstractToken token = _curiousToken[i];
			String reason = EagleUtilities.lj(_curiousReason[i], REASON_WIDTH);
			
			result.append(EagleUtilities.rj(token._currentLine+1, LINE_WIDTH));
			result.append("  ");
			result.append(reason);
			result.append("  ");
			result.append(lines.get(token._currentLine));
			result.append('\n');
			
			for (int j = 0; j < token._currentChar + LINE_WIDTH + 2 + REASON_WIDTH + 2; j++) result.append(' ');
			result.append("^\n");
		}

		return result.toString();
	}
}
