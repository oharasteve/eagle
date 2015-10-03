// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Feb 11, 2011

package com.eagle.programmar.Gupta.Declarations;

import com.eagle.programmar.Gupta.Gupta_Declaration;
import com.eagle.programmar.Gupta.Terminals.Gupta_CommentToEndOfLine;
import com.eagle.programmar.Gupta.Terminals.Gupta_Keyword;

public class Gupta_Application extends Gupta_Declaration
{
	public Gupta_Keyword Application = new Gupta_Keyword("Application");
	public Gupta_Keyword description = new Gupta_Keyword("description");
	public Gupta_CommentToEndOfLine comment;
	
	public @INDENT Gupta_Libraries libraries;
	public Gupta_Global_Declarations globalDeclarations;
}
