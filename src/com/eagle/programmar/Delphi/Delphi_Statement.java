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
	public @CHOICE PunctuationSemicolon semicolon;
	
	public @CHOICE Delphi_Assignment assignment;
	public @CHOICE Delphi_BeginEnd beginEnd;
	public @CHOICE Delphi_Case_Statement caseStatement;
	public @CHOICE Delphi_Close_Statement closeStatement;
	public @CHOICE Delphi_For_Statement forStatement;
	public @CHOICE Delphi_Halt_Statement haltStatement;
	public @CHOICE Delphi_If_Statement ifStatement;
	public @CHOICE Delphi_Inherited_Statement inheritedStatement;
	public @CHOICE Delphi_Raise_Statement raiseStatement;
	public @CHOICE Delphi_Readln_Statement readlnStatement;
	public @CHOICE Delphi_Repeat_Statement repeat_Statement;
	public @CHOICE Delphi_Rewrite_Statement rewriteStatement;
	public @CHOICE Delphi_Try_Statement tryStatement;
	public @CHOICE Delphi_While_Statement while_Statement;
	public @CHOICE Delphi_With_Statement with_Statement;
	public @CHOICE Delphi_Writeln_Statement writelnStatement;

	// This guy has to be last
	public @LAST Delphi_Procedure_Call procedureCall;
}
