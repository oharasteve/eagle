// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 17, 2011

package com.eagle.programmar.VB.Statements;

import com.eagle.programmar.VB.Symbols.VB_Identifier_Reference;
import com.eagle.programmar.VB.Terminals.VB_Keyword;
import com.eagle.programmar.VB.Terminals.VB_Number;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class VB_OnStatement extends TokenSequence
{
	public VB_Keyword ON = new VB_Keyword("on");
	public VB_Keyword ERROR = new VB_Keyword("error");
	public VB_OnWhat onWhat;
	
	public static class VB_OnWhat extends TokenChooser
	{
		public static class VB_OnResume extends TokenSequence
		{
			public VB_Keyword RESUME = new VB_Keyword("resume");
			public VB_Keyword NEXT = new VB_Keyword("next");
		}
		
		public static class VB_OnGotoZero extends TokenSequence
		{
			public VB_Keyword GOTO = new VB_Keyword("goto");
			public VB_Number zero;
		}
		
		public static class VB_OnGotoLabel extends TokenSequence
		{
			public VB_Keyword GOTO = new VB_Keyword("goto");
			public VB_Identifier_Reference lbl;
		}
	}
}
