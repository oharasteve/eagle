// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

package com.eagle.programmar.IntelASM;

import com.eagle.programmar.IntelASM.Symbols.IntelASM_Label_Definition;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_EndOfLine;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class IntelASM_Instruction extends TokenSequence
{
	public @OPT IntelASM_Label_Definition label;
	public IntelASM_RegularInstruction instruction;
	public IntelASM_EndOfLine eoln;
	
	public static class IntelASM_RegularInstruction extends TokenChooser
	{
		
	}
}
