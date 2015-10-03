// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 9, 2011

package com.eagle.programmar.IBMASM;

import com.eagle.programmar.EagleLanguage;
import com.eagle.programmar.IBMASM.Data.IBMASM_DC_Instruction;
import com.eagle.programmar.IBMASM.Data.IBMASM_DS_Instruction;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_Comment;
import com.eagle.programmar.IBMASM.Terminals.IBMASM_EndOfLine;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class IBMASM_Program extends EagleLanguage
{
	public static final String NAME = "IBMASM";
	
	public IBMASM_Program()
	{
		super(NAME, new IBMASM_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "http://publibz.boulder.ibm.com/bookmgr_OS390/libraryserver/zosv1r7/";
	}
	
	public TokenList<IBMASM_Line> lines;
	
	public static class IBMASM_Line extends TokenSequence
	{
		public IBMASM_LineContents contents;
		public IBMASM_EndOfLine eoln;
	}

	public static class IBMASM_LineContents extends TokenChooser
	{
		public IBMASM_Comment comment;
		public IBMASM_Instruction instruction;
		public IBMASM_Directive directive;
		public IBMASM_Macro macro;
		public IBMASM_DC_Instruction defineConstant;
		public IBMASM_DS_Instruction declareStorage;
	}
}
