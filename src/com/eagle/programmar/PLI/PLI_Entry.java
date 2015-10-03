// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 23, 2015

package com.eagle.programmar.PLI;

import com.eagle.programmar.PLI.PLI_Procedure.PLI_ProcedureOptions;
import com.eagle.programmar.PLI.PLI_Procedure.PLI_ProcedureReturns;
import com.eagle.programmar.PLI.PLI_Procedure.PLI_Procedure_Parameters;
import com.eagle.programmar.PLI.PLI_Procedure.PLI_StatementOrComment;
import com.eagle.programmar.PLI.Symbols.PLI_Procedure_Definition;
import com.eagle.programmar.PLI.Terminals.PLI_Keyword;
import com.eagle.programmar.PLI.Terminals.PLI_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class PLI_Entry extends TokenSequence
{
	public @OPT PLI_Punctuation percent1 = new PLI_Punctuation('%');
	public PLI_Procedure_Definition id1;
	public PLI_Punctuation colon = new PLI_Punctuation(':');
	
	public PLI_Keyword ENTRY = new PLI_Keyword("ENTRY");
	public @OPT PLI_Procedure_Parameters params;
	
	public @OPT PLI_ProcedureOptions options1;
	public @OPT PLI_ProcedureReturns returns;
	public @OPT PLI_ProcedureOptions options2;
	public PLI_Punctuation semicolon = new PLI_Punctuation(';');

	public TokenList<PLI_StatementOrComment> statements;
}
