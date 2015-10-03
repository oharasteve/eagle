// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 2, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Natural_Label;
import com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.tokens.TokenSequence;

public class Natural_StoreStatement extends TokenSequence
{
	public @OPT Natural_Label label;
	public @DOC("sm/store.htm") Natural_Keyword STORE = new Natural_Keyword("STORE");
	public @OPT Natural_Keyword RECORD = new Natural_Keyword("RECORD");
	public @OPT Natural_Keyword IN = new Natural_Keyword("IN");
	public @OPT Natural_Keyword FILE = new Natural_Keyword("FILE");
	public Natural_Identifier_Reference viewName;
}
