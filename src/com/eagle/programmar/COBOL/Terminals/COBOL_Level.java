// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 26, 2011

package com.eagle.programmar.COBOL.Terminals;

import com.eagle.programmar.COBOL.COBOL_DataDeclaration;
import com.eagle.programmar.COBOL.COBOL_DataDivision.COBOL_CopyOrDataDeclaration;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TerminalLevelToken;
import com.eagle.tokens.TokenList;

public class COBOL_Level extends TerminalLevelToken
{
	@Override
	protected boolean validateLevel()
	{
		// All of this used to be post-processed. No more.
		AbstractToken parent = this.getParent();
		
		if (parent instanceof COBOL_DataDeclaration)
		{
			COBOL_DataDeclaration decl = (COBOL_DataDeclaration) this.getParent();
			if (! (decl.getParent() instanceof COBOL_CopyOrDataDeclaration))
			{
				throw new RuntimeException("Expected COBOL_CopyOrDataDeclaration, not " + decl.getParent());
			}
			COBOL_CopyOrDataDeclaration copyData = (COBOL_CopyOrDataDeclaration) decl.getParent();
			@SuppressWarnings("unchecked")
			TokenList<? extends AbstractToken> siblings = (TokenList<? extends AbstractToken>) copyData.getParent();
			if (DEBUG) System.out.println((_currentLine+1) + " siblings.size() = " + siblings.size());
			if (DEBUG) System.out.println((_currentLine+1) + " parent of TokenList is " + siblings.getParent());

			// Don't bother checking top-level elements. Doesn't matter about siblings with no parents.
			if (DEBUG) System.out.println((_currentLine+1) + " parent of copyData is " + copyData.getParent());
			if (copyData.getParent() instanceof COBOL_DataDeclaration)
			{
				if (siblings.size() > 0)
				{
					if (! validateLevelSiblings(siblings)) return false;
				}
			}
			else
			{
				if (! validateLevelParents(copyData)) return false;
			}
		}
		// Otherwise probably a Screen or Report entry. Ignore the test for now.
		
		// Passed all the tests!
		return true;
	}
	
	private boolean validateLevelSiblings(TokenList<? extends AbstractToken> siblings)
	{
		for (AbstractToken sibling : siblings._elements)
		{
			// Context-sensitive check. Level must be = to a sister level
			COBOL_CopyOrDataDeclaration otherDeclParent = (COBOL_CopyOrDataDeclaration) sibling;
			if (otherDeclParent._whichToken instanceof COBOL_DataDeclaration)
			{
				if (DEBUG) System.out.println((_currentLine+1) + " found a sibling");
				COBOL_DataDeclaration otherDecl = (COBOL_DataDeclaration) otherDeclParent._whichToken;
				if (DEBUG) System.out.println((_currentLine+1) + " same: if " + _level + " != " + otherDecl.level._level + " -> fail");
				if (_level != otherDecl.level._level) return false;
				break;	// Only need to check one -- all siblings have the same level
			}
		}
		return true;
	}
	
	private boolean validateLevelParents(COBOL_CopyOrDataDeclaration copyData)
	{
		AbstractToken parent = copyData.getParent();
		while (parent != null)
		{
			if (parent instanceof COBOL_CopyOrDataDeclaration)
			{
				if (DEBUG) System.out.println((_currentLine+1) + " found a parent COBOL_CopyOrDataDeclaration");
				if (_level == 77 || _level == 78) return false;		// 77 is always at the top
				if (_level == 88) return true;		// 88 can be anyplace
				
				// Context-sensitive check. Level cannot be <= a parent level
				COBOL_CopyOrDataDeclaration otherCopyDecl = (COBOL_CopyOrDataDeclaration) parent;
				if (otherCopyDecl._whichToken instanceof COBOL_DataDeclaration)
				{
					COBOL_DataDeclaration otherDecl = (COBOL_DataDeclaration) otherCopyDecl._whichToken;
					if (otherDecl.level._level != 77 && otherDecl.level._level != 78)	// 77 can be followed by an 01
					{
						if (otherDecl.level._level == 88) return true;	// Always match an 88
						if (DEBUG) System.out.println((_currentLine+1) + " under: if " + _level + " <= " + otherDecl.level._level + " -> fail");
						if (_level <= otherDecl.level._level) return false;
					}
				}
				else throw new RuntimeException("Expected COBOL_DataDeclaration, not " + otherCopyDecl._whichToken);
				break;
			}
			parent = parent.getParent();
		}
		return true;
	}

	@Override
	public String toString()
	{
		if (_level >= 100 || _level <= 0) return "Err";
		if (_level >= 10) return Integer.toString(_level);
		return '0' + Integer.toString(_level);
	}
	
	@Override
	public String description()
	{
		return "COBOL level number, such as 01, 05 or 77.";
	}
}
