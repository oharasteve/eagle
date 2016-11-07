// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 9, 2011

package com.eagle.programmar.IBMASM;

import com.eagle.programmar.IBMASM.Formats.IBMASM_Format_I;
import com.eagle.programmar.IBMASM.Formats.IBMASM_Format_R;
import com.eagle.programmar.IBMASM.Formats.IBMASM_Format_RI;
import com.eagle.programmar.IBMASM.Formats.IBMASM_Format_RR;
import com.eagle.programmar.IBMASM.Formats.IBMASM_Format_RRS;
import com.eagle.programmar.IBMASM.Formats.IBMASM_Format_RSS;
import com.eagle.programmar.IBMASM.Formats.IBMASM_Format_RX;
import com.eagle.programmar.IBMASM.Formats.IBMASM_Format_S;
import com.eagle.programmar.IBMASM.Formats.IBMASM_Format_SI;
import com.eagle.programmar.IBMASM.Formats.IBMASM_Format_SII;
import com.eagle.programmar.IBMASM.Formats.IBMASM_Format_SS;
import com.eagle.programmar.IBMASM.Symbols.IBMASM_Label_Definition;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Remark;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Spaces;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class IBMASM_Instruction extends TokenSequence
{
	public @OPT IBMASM_Label_Definition label;
	public IBMASM_Spaces spaces1;
	public IBMASM_Instr instr;
	public @OPT IBMASM_Spaces spaces2;
	public @OPT IBMASM_Remark remark;
	
	public static class IBMASM_Instr extends TokenChooser
	{
		public @CHOICE IBMASM_Format_I immediate;
		public @CHOICE IBMASM_Format_R register;
		public @CHOICE IBMASM_Format_RI registerImmediate;
		public @CHOICE IBMASM_Format_RR registerRegister;
		public @CHOICE IBMASM_Format_RRS registerRegisterAddress;
		public @CHOICE IBMASM_Format_RSS registerAddressAddress;
		public @CHOICE IBMASM_Format_RX rx;
		public @CHOICE IBMASM_Format_S address;
		public @CHOICE IBMASM_Format_SI addressImmediate;
		public @CHOICE IBMASM_Format_SII addressImmediateImmediate;
		public @CHOICE IBMASM_Format_SS addressAddress;
	}
}
