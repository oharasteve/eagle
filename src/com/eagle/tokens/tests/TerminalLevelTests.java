// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 26, 2015

package com.eagle.tokens.tests;

import org.junit.Test;

import com.eagle.io.DumpTree;
import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.ParserManager;
import com.eagle.programmar.COBOL.COBOL_DataDeclaration;
import com.eagle.programmar.COBOL.COBOL_DataDivision.COBOL_CopyOrDataDeclaration;
import com.eagle.programmar.COBOL.COBOL_DataDivision.COBOL_WorkingStorageSection;
import com.eagle.programmar.COBOL.COBOL_Program_Fixed_Format;
import com.eagle.programmar.COBOL.COBOL_Syntax.COBOL_Free_Format_Syntax;
import com.eagle.programmar.COBOL.Symbols.COBOL_Data_Definition;

import junit.framework.TestCase;

public class TerminalLevelTests extends TestCase
{
	private String[] data = new String[] {
			"WORKING-STORAGE SECTION.",
			"01 StudentDetails.",
			"   02  StudentId        PIC 9(7).",
			"   02  StudentName.",
			"       03 Surname      PIC X(8).",
			"       03 Initials     PIC XX.",
			"   02  CourseCode      PIC X(4).",
			"   02  Gender          PIC X.",
			"01 CurrentDate.",
			"   02  CurrentYear     PIC 9(4).",
			"   02  CurrentMonth    PIC 99.",
			"   02  CurrentDay      PIC 99."
	};
	
	@Test
	public void testPictureHierarchy()
	{
		//TerminalLevelToken.DEBUG = true;
		
		EagleFileReader reader = new EagleFileReader();
		for (String line : data)
		{
			reader.add(line);
		}
		
		COBOL_WorkingStorageSection ws = new COBOL_WorkingStorageSection();
		COBOL_Free_Format_Syntax syntax = new COBOL_Free_Format_Syntax();
		ParserManager parser = new ParserManager();
		ws.setSyntax(syntax);
		parser.parseLines(reader, new COBOL_Program_Fixed_Format(), ws);
		
		DumpTree dt = new DumpTree();
		dt.dump(System.out, ws, DumpTree.Width.WIDE, 0, true);

		COBOL_CopyOrDataDeclaration copyOrData = ws.dataDeclarations._elements.get(0);
		COBOL_DataDeclaration decl1 = (COBOL_DataDeclaration) copyOrData.getWhich();
		COBOL_Data_Definition def = (COBOL_Data_Definition) decl1.fieldName.getWhich();
		assertEquals("StudentDetails", def.getValue());
		assertEquals(1, decl1.level._level);

		copyOrData = decl1.children._elements.get(0);
		COBOL_DataDeclaration decl2 = (COBOL_DataDeclaration) copyOrData.getWhich();
		def = (COBOL_Data_Definition) decl2.fieldName.getWhich();
		assertEquals("StudentId", def.getValue());
		assertEquals(2, decl2.level._level);

		copyOrData = decl1.children._elements.get(1);
		decl2 = (COBOL_DataDeclaration) copyOrData.getWhich();
		def = (COBOL_Data_Definition) decl2.fieldName.getWhich();
		assertEquals("StudentName", def.getValue());
		assertEquals(2, decl2.level._level);

		copyOrData = decl2.children._elements.get(0);
		COBOL_DataDeclaration decl3 = (COBOL_DataDeclaration) copyOrData.getWhich();
		def = (COBOL_Data_Definition) decl3.fieldName.getWhich();
		assertEquals("Surname", def.getValue());
		assertEquals(3, decl3.level._level);

		copyOrData = decl2.children._elements.get(1);
		decl3 = (COBOL_DataDeclaration) copyOrData.getWhich();
		def = (COBOL_Data_Definition) decl3.fieldName.getWhich();
		assertEquals("Initials", def.getValue());
		assertEquals(3, decl3.level._level);

		copyOrData = decl1.children._elements.get(2);
		decl2 = (COBOL_DataDeclaration) copyOrData.getWhich();
		def = (COBOL_Data_Definition) decl2.fieldName.getWhich();
		assertEquals("CourseCode", def.getValue());
		assertEquals(2, decl2.level._level);

		copyOrData = decl1.children._elements.get(3);
		decl2 = (COBOL_DataDeclaration) copyOrData.getWhich();
		def = (COBOL_Data_Definition) decl2.fieldName.getWhich();
		assertEquals("Gender", def.getValue());
		assertEquals(2, decl2.level._level);

		// Current Date
		
		copyOrData = ws.dataDeclarations._elements.get(1);
		decl1 = (COBOL_DataDeclaration) copyOrData.getWhich();
		def = (COBOL_Data_Definition) decl1.fieldName.getWhich();
		assertEquals("CurrentDate", def.getValue());
		assertEquals(1, decl1.level._level);

		copyOrData = decl1.children._elements.get(0);
		decl2 = (COBOL_DataDeclaration) copyOrData.getWhich();
		def = (COBOL_Data_Definition) decl2.fieldName.getWhich();
		assertEquals("CurrentYear", def.getValue());
		assertEquals(2, decl2.level._level);

		copyOrData = decl1.children._elements.get(1);
		decl2 = (COBOL_DataDeclaration) copyOrData.getWhich();
		def = (COBOL_Data_Definition) decl2.fieldName.getWhich();
		assertEquals("CurrentMonth", def.getValue());
		assertEquals(2, decl2.level._level);

		copyOrData = decl1.children._elements.get(2);
		decl2 = (COBOL_DataDeclaration) copyOrData.getWhich();
		def = (COBOL_Data_Definition) decl2.fieldName.getWhich();
		assertEquals("CurrentDay", def.getValue());
		assertEquals(2, decl2.level._level);
	}
}
