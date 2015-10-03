// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 11, 2011

package com.eagle.programmar.Gupta;

import com.eagle.programmar.Gupta.Statements.Gupta_Call_Statement;
import com.eagle.programmar.Gupta.Statements.Gupta_Comment_Statement;
import com.eagle.programmar.Gupta.Statements.Gupta_If_Statement;
import com.eagle.programmar.Gupta.Statements.Gupta_Return_Statement;
import com.eagle.programmar.Gupta.Statements.Gupta_Set_Statement;
import com.eagle.programmar.Gupta.Statements.Gupta_While_Statement;
import com.eagle.tokens.TokenChooser;

public class Gupta_Statement extends TokenChooser
{
	public Gupta_Call_Statement callStatement;
	public Gupta_Comment_Statement commentStatement;
	public Gupta_If_Statement ifStatement;
	public Gupta_Return_Statement returnStatement;
	public Gupta_Set_Statement setStatement;
	public Gupta_While_Statement whileStatement;
}
