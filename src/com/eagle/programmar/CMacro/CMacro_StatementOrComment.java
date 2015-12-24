// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

package com.eagle.programmar.CMacro;

import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.CMacro.Statements.CMacro_Define_Statement;
import com.eagle.programmar.CMacro.Statements.CMacro_Error_Statement;
import com.eagle.programmar.CMacro.Statements.CMacro_IfDef_Statement;
import com.eagle.programmar.CMacro.Statements.CMacro_IfDef_Statement.CMacro_IfDefCPlusPlus;
import com.eagle.programmar.CMacro.Statements.CMacro_If_Statement;
import com.eagle.programmar.CMacro.Statements.CMacro_Include_Statement;
import com.eagle.programmar.CMacro.Statements.CMacro_Pragma_Statement;
import com.eagle.programmar.CMacro.Statements.CMacro_Undef_Statement;
import com.eagle.programmar.CMacro.Terminals.CMacro_EndOfLine;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class CMacro_StatementOrComment extends TokenSequence
{
	public @OPT CMacro_EndOfLine eoln1;
	public CMacro_StmtBody stmt;
	public @OPT C_Comment comment;
	public CMacro_EndOfLine eoln2;

	public static class CMacro_StmtBody extends TokenChooser
	{
		public @FIRST CMacro_IfDefCPlusPlus ifdefCPlusPlus;
		
		public CMacro_Define_Statement defineStatement;
		public CMacro_Error_Statement errorStatement;
		public CMacro_If_Statement ifStatement;
		public CMacro_IfDef_Statement ifdefStatement;
		public CMacro_Include_Statement includeStatement;
		public CMacro_Pragma_Statement pragmaStatement;
		public CMacro_Undef_Statement undefStatement;
	}
}
