// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 12, 2015

package com.eagle.programmar.Template;

import com.eagle.programmar.Template.Statements.Template_AssignmentStatement;
import com.eagle.programmar.Template.Statements.Template_DataStatement;
import com.eagle.programmar.Template.Statements.Template_PrintStatement;
import com.eagle.tokens.TokenChooser;

public class Template_Statement extends TokenChooser
{
	public @CHOICE Template_AssignmentStatement assignmentStatement;
	public @CHOICE Template_DataStatement dataStatement;
	public @CHOICE Template_PrintStatement printStatement;
}
