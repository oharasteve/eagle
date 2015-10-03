// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 12, 2011

package com.eagle.programmar.IBMASM.Data;

import com.eagle.programmar.IBMASM.Symbols.IBMASM_Label_Definition;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Keyword;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_KeywordChoice;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Number;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Remark;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class IBMASM_DS_Instruction extends TokenSequence
{
	public @OPT IBMASM_Label_Definition label;
	public IBMASM_Spaces spaces1;
	public IBMASM_Keyword DS = new IBMASM_Keyword("DS");
	public IBMASM_Spaces spaces2;
	public IBMASM_DS_Value value;
	public IBMASM_Spaces spaces3;
	public @OPT IBMASM_Remark remark;

	public static class IBMASM_DS_Value extends TokenChooser
	{
		public static class IBMASM_DS_storage extends TokenSequence
		{
			public @OPT IBMASM_Number replication;
			public IBMASM_KeywordChoice code = new IBMASM_KeywordChoice("F");
		}
	}
}
