// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 25, 2011

package com.eagle.programmar.CMD;

import com.eagle.programmar.EagleLanguage;
import com.eagle.programmar.CMD.Statements.CMD_Unparsed_Statement;
import com.eagle.programmar.CMD.Symbols.CMD_Label_Definition;
import com.eagle.programmar.CMD.Terminals.CMD_EndOfLine;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class CMD_Program extends EagleLanguage
{
	public static final String NAME = "CMD";
	
	public CMD_Program()
	{
		super(NAME, new CMD_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "http://www.microsoft.com/resources/documentation/windows/xp/all/proddocs/en-us/";
	}
	
	public @OPT TokenList<CMD_CommandOrLabelOrUnparsed> commands;
	
	public @SKIP static class CMD_CommandOrLabelOrUnparsed extends TokenChooser
	{
		public CMD_Command command;
		public CMD_Label label;
		public @LAST CMD_Unparsed_Statement unparsed;
	}
	
	public @SKIP static class CMD_Label extends TokenSequence
	{
		public PunctuationColon colon;
		public CMD_Label_Definition label;
		public CMD_EndOfLine eoln;
	}
}
