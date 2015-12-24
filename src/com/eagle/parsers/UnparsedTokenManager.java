// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Oct 1, 2015

package com.eagle.parsers;

import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.UnparsedElement;
import com.eagle.utils.EagleUtilities;

public class UnparsedTokenManager
{
	public static final int MAX_UNPARSED_ELEMENTS = 10;
	private int _unparsedElements = 0;
	private int[] _unparsedElementStartLine = new int[MAX_UNPARSED_ELEMENTS];
	private int[] _unparsedElementStartChar = new int[MAX_UNPARSED_ELEMENTS];
	private String[] _unparsedElementText = new String[MAX_UNPARSED_ELEMENTS];

	// Unparsed tokens are collected into a list for presentation later
	public boolean addUnparsedToken(UnparsedElement token, int saveStartLine, int saveStartChar)
	{
		if (_unparsedElements >= MAX_UNPARSED_ELEMENTS) return false;
		
		// Ignore duplicates
		boolean duplicate = false;
		StringBuffer sb = new StringBuffer();
		TokenList<? extends AbstractToken> pieces = token.unparsedPieces();
		if (pieces != null)
		{
			for (AbstractToken piece : pieces._elements)
			{
				if (piece instanceof TokenChooser)
				{
					TokenChooser chooser = (TokenChooser) piece;
					sb.append(chooser._whichToken.toString());
					sb.append(' ');
				}
			}
		}
		String txt = sb.toString();
		
		if (_unparsedElements > 0)
		{
			int prev = _unparsedElements - 1;
			if (_unparsedElementStartLine[prev] == saveStartLine &&
					_unparsedElementStartChar[prev] == saveStartChar &&
					_unparsedElementText[prev].equals(txt))
			{
				duplicate = true;
			}
		}

		if (! duplicate)
		{
			// Add to the list
			if (_unparsedElements < MAX_UNPARSED_ELEMENTS)
			{
				_unparsedElementStartLine[_unparsedElements] = saveStartLine;
				_unparsedElementStartChar[_unparsedElements] = saveStartChar;
				_unparsedElementText[_unparsedElements] = txt;
			}
			_unparsedElements++;
		}
		return true;
	}

	public void setUnparsedElements(int elements)
	{
		_unparsedElements = elements;
	}
	
	public int getUnparsedElements()
	{
		return _unparsedElements;
	}

	public String allUnparsedElements(String filename)
	{
		String unparsed = null;
		if (_unparsedElements > 0)
		{
			unparsed = _unparsedElements + " Unparsed Element" + (_unparsedElements==1 ? "" : "s") + "\n" +
					filename + "\n" +
					"  Line   SC  ===== Text =====\n";
			for (int i = 0; i < _unparsedElements; i++)
			{
				if (i >= MAX_UNPARSED_ELEMENTS)
				{
					unparsed += "    (remaining elements skipped)";
					break;
				}
				
				unparsed += EagleUtilities.rj(_unparsedElementStartLine[i]+1, 6) + " " +
						EagleUtilities.rj(_unparsedElementStartChar[i]+1, 4) + "  " +
						_unparsedElementText[i] + "\n";
			}
		}
		return unparsed;
	}
}
