// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 20, 2012

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.COBOL_DataDivision.COBOL_Copy_or_FileDescriptor.COBOL_FileDescriptor;
import com.eagle.programmar.COBOL.COBOL_FileControl.COBOL_Copy_or_FileSelect.COBOL_FileSelect;
import com.eagle.programmar.COBOL.COBOL_ProcedureDivision.COBOL_Paragraph;
import com.eagle.programmar.COBOL.COBOL_ProcedureDivision.COBOL_Section;
import com.eagle.programmar.COBOL.COBOL_ScreenSection.COBOL_ScreenDeclaration;
import com.eagle.programmar.COBOL.COBOL_Syntax.COBOL_Fixed_Format_Syntax;
import com.eagle.programmar.COBOL.Terminals.COBOL_Comment;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;

public class COBOL_Partial_Fixed_Format extends COBOL_Program
{
	public static final String NAME = "COBOL_Partial_Fixed_Format";

	public COBOL_Partial_Fixed_Format()
	{
		super(NAME, new COBOL_Fixed_Format_Syntax());
	}
	
	public TokenList<COBOL_PartialWhat> pieces;
	
	public static class COBOL_PartialWhat extends TokenChooser
	{
		public @CHOICE COBOL_Directive directive;
		public @CHOICE COBOL_Comment comment;
		public @CHOICE COBOL_Paragraph paragraph;
		public @CHOICE COBOL_Section section;
		public @CHOICE COBOL_ScreenDeclaration screenDeclaration;
		public @CHOICE COBOL_DataDeclaration declarations;
		public @CHOICE COBOL_FileDescriptor fileDescriptor;
		public @CHOICE COBOL_FileSelect fileSelect;
	}
}
