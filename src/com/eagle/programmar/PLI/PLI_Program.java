// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, May 6, 2011

package com.eagle.programmar.PLI;

import com.eagle.programmar.EagleLanguage;
import com.eagle.programmar.PLI.Statements.PLI_PercentStatement;
import com.eagle.programmar.PLI.Terminals.PLI_Comment;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class PLI_Program extends EagleLanguage
{
	public static final String NAME = "PL/I";
	
	public PLI_Program()
	{
		super(NAME, new PLI_Syntax());
	}

	@Override
	public String getDocRoot()
	{
		return "http://publibfp.boulder.ibm.com/cgi-bin/bookmgr/BOOKS/IBM3L101/";
	}
	
	// Components of a PL/I Program
	public @OPT TokenList<PLI_Element> elements;
	
	public static class PLI_Element extends TokenChooser
	{
		public PLI_Comment comment;
		public PLI_DeclareGeneric declareGeneric;
		public PLI_Procedure procedure;
		public PLI_Declaration declaration;
		public PLI_PercentStatement percentStmt;
	}
}
