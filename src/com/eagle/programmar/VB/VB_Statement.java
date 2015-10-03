// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2011

package com.eagle.programmar.VB;

import com.eagle.programmar.VB.Statements.VB_AssignmentStatement;
import com.eagle.programmar.VB.Statements.VB_AttributeStatement;
import com.eagle.programmar.VB.Statements.VB_BeginStatement;
import com.eagle.programmar.VB.Statements.VB_CallStatement;
import com.eagle.programmar.VB.Statements.VB_CloseStatement;
import com.eagle.programmar.VB.Statements.VB_DataDeclaration;
import com.eagle.programmar.VB.Statements.VB_ExitStatement;
import com.eagle.programmar.VB.Statements.VB_ForStatement;
import com.eagle.programmar.VB.Statements.VB_FunctionDeclaration;
import com.eagle.programmar.VB.Statements.VB_GotoStatement;
import com.eagle.programmar.VB.Statements.VB_IfStatement;
import com.eagle.programmar.VB.Statements.VB_MessageBoxStatment;
import com.eagle.programmar.VB.Statements.VB_OnStatement;
import com.eagle.programmar.VB.Statements.VB_OpenStatement;
import com.eagle.programmar.VB.Statements.VB_OptionStatement;
import com.eagle.programmar.VB.Statements.VB_PrintStatement;
import com.eagle.programmar.VB.Statements.VB_SetStatement;
import com.eagle.programmar.VB.Statements.VB_SubDeclaration;
import com.eagle.programmar.VB.Statements.VB_VersionStatement;
import com.eagle.programmar.VB.Symbols.VB_Label_Definition;
import com.eagle.programmar.VB.Terminals.VB_Comment;
import com.eagle.programmar.VB.Terminals.VB_EndOfLine;
import com.eagle.programmar.VB.Terminals.VB_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class VB_Statement extends TokenSequence
{
	public VB_BaseStatement baseStatement;
	public @OPT VB_Comment comment;
	public TokenList<VB_EndOfLine> eoln;
	
	public static class VB_BaseStatement extends TokenChooser
	{
		public VB_Comment comment;
		
		public VB_AssignmentStatement assignmentStatement;
		public VB_AttributeStatement attributeStatement;
		public VB_BeginStatement beginStatement;
		public VB_CallStatement callStatement;
		public VB_CloseStatement closeStatement;
		public VB_DataDeclaration dataDeclaration;
		public VB_ExitStatement exitStatement;
		public VB_ForStatement forStatement;
		public VB_FunctionDeclaration functionDefinition;
		public VB_GotoStatement gotoStatement;
		public VB_IfStatement ifStatement;
		public VB_MessageBoxStatment messageBoxStatment;
		public VB_OnStatement onStatement;
		public VB_OpenStatement openStatement;
		public VB_OptionStatement optionStatement;
		public VB_PrintStatement printStatement;
		public VB_SetStatement setStatement;
		public VB_SubDeclaration subDefinition;
		public VB_VersionStatement versionStatement;
		
		public VB_Variable functionCall;	// Not really right ...
		
		public static class VB_Label extends TokenSequence
		{
			public VB_Label_Definition lbl;
			public VB_Punctuation colon = new VB_Punctuation(':');
		}
	}
}
