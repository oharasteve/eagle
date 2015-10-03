// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

package com.eagle.programmar.IntelASM;

import com.eagle.programmar.EagleLanguage;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_Comment;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_EndOfLine;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class IntelASM_Program extends EagleLanguage
{
	public static final String NAME = "IntelASM";
	
	public IntelASM_Program()
	{
		super(NAME, new IntelASM_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "TBD";
	}
	
	public TokenList<IntelASM_Line> lines;
	
	public static class IntelASM_Line extends TokenChooser
	{
		public IntelASM_EndOfLine blankLine;
		public IntelASM_Comment comment;
		public IntelASM_Directive directive;
		public IntelASM_Instruction instruction;
	}
}