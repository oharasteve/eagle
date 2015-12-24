// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 2, 2015

package com.eagle.parsers;

import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TerminalCommentToken;
import com.eagle.tokens.TokenList;
import com.eagle.utils.EagleUtilities;

public class UnexpectedCommentManager
{
	private static final int MAX_UNEXPECTED_COMMENTS = 25;
	private int _unexpectedComments = 0;
	private int[] _unexpectedCommentLine = new int[MAX_UNEXPECTED_COMMENTS];
	private int[] _unexpectedCommentChar = new int[MAX_UNEXPECTED_COMMENTS];
	private String[] _unexpectedComment = new String[MAX_UNEXPECTED_COMMENTS];

	public void setUnexpectedComments(int count)
	{
		_unexpectedComments = count;
	}
	
	public int getUnexpectedComments()
	{
		return _unexpectedComments;
	}
	
	public void addUnexpectedComment(AbstractToken token, TerminalCommentToken comment)
	{
		// Ok, keep track of it (may get removed later)
		// But don't keep duplicates
		if (_unexpectedComments == 0 ||
				_unexpectedComments > MAX_UNEXPECTED_COMMENTS ||
				_unexpectedCommentLine[_unexpectedComments-1] != comment._currentLine ||
				_unexpectedCommentChar[_unexpectedComments-1] != comment._currentChar)
		{
			if (_unexpectedComments < MAX_UNEXPECTED_COMMENTS)
			{
				AbstractToken parent = token.getParent();
				if (parent instanceof TokenList<?>)
				{
					parent = parent.getParent();
				}
				String clsParent = parent.getClass().getSimpleName();
				String clsToken = token.getClass().getSimpleName();
				_unexpectedComment[_unexpectedComments] = clsParent + "/" + clsToken + " " + comment.getValue();
				_unexpectedCommentLine[_unexpectedComments] = comment._currentLine;
				_unexpectedCommentChar[_unexpectedComments] = comment._currentChar;
			}
			_unexpectedComments++;
		}
	}
	
	public String allUnexpectedComments()
	{
		if (_unexpectedComments == 0) return null;
		
		StringBuffer result = new StringBuffer();
		result.append(_unexpectedComments);
		result.append(" Discarded Comment");
		if (_unexpectedComments != 1) result.append('s');
		result.append('\n');
		
		result.append("  Line   SC  ===== Parent/Token Comment =====\n");
		
		for (int i = 0; i < _unexpectedComments; i++)
		{
			if (i >= MAX_UNEXPECTED_COMMENTS)
			{
				result.append("    (remaining unexpected comments skipped)\n");
				break;
			}
			
			result.append(EagleUtilities.rj(_unexpectedCommentLine[i]+1, 6));
			result.append(' ');
			result.append(EagleUtilities.rj(_unexpectedCommentChar[i]+1, 4));
			result.append("  ");
			result.append(_unexpectedComment[i]);
			result.append('\n');
		}
		
		return result.toString();
	}
	
}
