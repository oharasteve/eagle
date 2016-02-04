// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.Delphi;

import com.eagle.programmar.Delphi.Statements.Delphi_Assignment;
import com.eagle.programmar.Delphi.Statements.Delphi_BeginEnd;
import com.eagle.programmar.Delphi.Statements.Delphi_Case_Statement;
import com.eagle.programmar.Delphi.Statements.Delphi_Close_Statement;
import com.eagle.programmar.Delphi.Statements.Delphi_For_Statement;
import com.eagle.programmar.Delphi.Statements.Delphi_Halt_Statement;
import com.eagle.programmar.Delphi.Statements.Delphi_If_Statement;
import com.eagle.programmar.Delphi.Statements.Delphi_Inherited_Statement;
import com.eagle.programmar.Delphi.Statements.Delphi_Procedure_Call;
import com.eagle.programmar.Delphi.Statements.Delphi_Raise_Statement;
import com.eagle.programmar.Delphi.Statements.Delphi_Readln_Statement;
import com.eagle.programmar.Delphi.Statements.Delphi_Repeat_Statement;
import com.eagle.programmar.Delphi.Statements.Delphi_Rewrite_Statement;
import com.eagle.programmar.Delphi.Statements.Delphi_Try_Statement;
import com.eagle.programmar.Delphi.Statements.Delphi_While_Statement;
import com.eagle.programmar.Delphi.Statements.Delphi_With_Statement;
import com.eagle.programmar.Delphi.Statements.Delphi_Writeln_Statement;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Delphi_Statement extends TokenChooser
{
	public PunctuationSemicolon semicolon;
	
	public Delphi_Assignment assignment;
	public Delphi_BeginEnd beginEnd;
	public Delphi_Case_Statement caseStatement;
	public Delphi_Close_Statement closeStatement;
	public Delphi_For_Statement forStatement;
	public Delphi_Halt_Statement haltStatement;
	public Delphi_If_Statement ifStatement;
	public Delphi_Inherited_Statement inheritedStatement;
	public Delphi_Raise_Statement raiseStatement;
	public Delphi_Readln_Statement readlnStatement;
	public Delphi_Repeat_Statement repeat_Statement;
	public Delphi_Rewrite_Statement rewriteStatement;
	public Delphi_Try_Statement tryStatement;
	public Delphi_While_Statement while_Statement;
	public Delphi_With_Statement with_Statement;
	public Delphi_Writeln_Statement writelnStatement;

	// This guy has to be last
	public @LAST Delphi_Procedure_Call procedureCall;
}
