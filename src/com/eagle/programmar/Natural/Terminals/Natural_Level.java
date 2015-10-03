// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 26, 2011

package com.eagle.programmar.Natural.Terminals;

import com.eagle.programmar.Natural.Natural_DDM.Natural_DDM_Line;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TerminalLevelToken;
import com.eagle.tokens.TokenList;

public class Natural_Level extends TerminalLevelToken
{
	@Override
	protected boolean validateLevel()
	{
		if (this.getParent() instanceof Natural_DDM_Line)
		{
			Natural_DDM_Line decl = (Natural_DDM_Line) this.getParent();
			@SuppressWarnings("unchecked")
			TokenList<AbstractToken> previous = (TokenList<AbstractToken>) decl.getParent();
			if (previous.size() > 0)
			{
				// Context-sensitive check. Level must be <= a sister level
				Natural_DDM_Line otherDecl = (Natural_DDM_Line) previous.first();
				if (_level < otherDecl.Level._level) return false;
			}
			else
			{
				AbstractToken grandParent = previous.getParent();
				if (grandParent != null && grandParent instanceof Natural_DDM_Line)
				{
					// Context-sensitive check. Level cannot be <= a parent level
					Natural_DDM_Line otherDecl = (Natural_DDM_Line) grandParent;
					if (_level <= otherDecl.Level._level) return false;
				}
			}
		}

		// Passed all the tests!
		return true;
	}
	
	@Override
	public String description()
	{
		return "Natural DDM level number.";
	}
}
