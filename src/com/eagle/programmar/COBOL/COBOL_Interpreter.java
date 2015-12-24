// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 23, 2012

package com.eagle.programmar.COBOL;

import com.eagle.preprocess.EagleSymbolTable;
import com.eagle.programmar.COBOL.COBOL_ProcedureDivision.COBOL_Paragraph;
import com.eagle.programmar.COBOL.COBOL_ProcedureDivision.COBOL_Paragraph.COBOL_SentenceOrComment;
import com.eagle.programmar.COBOL.COBOL_ProcedureDivision.COBOL_Section;
import com.eagle.programmar.COBOL.COBOL_ProcedureDivision.COBOL_Sentence;
import com.eagle.programmar.COBOL.COBOL_ProcedureDivision.COBOL_Sentence.COBOL_StatementOrComment;
import com.eagle.tests.EagleInterpreter;
import com.eagle.tests.EagleRunnable;
import com.eagle.tokens.AbstractToken;

public class COBOL_Interpreter extends EagleInterpreter
{
	public COBOL_Interpreter(EagleSymbolTable symbolTable)
	{
		super(symbolTable);
	}

	public void execute(COBOL_Program_Complete pgm)
	{
		//COBOL_Resolve_References.resolveReferences(pgm);
		
		COBOL_ProcedureDivision procDiv = pgm.procedureDiv;
		COBOL_Section firstSection = procDiv.sections.first();
		COBOL_Paragraph firstParagraph = firstSection.paragraphs.first();
		for (COBOL_SentenceOrComment sentenceItem : firstParagraph.sentences._elements)
		{
			if (sentenceItem._whichToken instanceof COBOL_Sentence)
			{
				COBOL_Sentence sentence = (COBOL_Sentence) sentenceItem._whichToken;
				for (COBOL_StatementOrComment statementItem : sentence.statements._elements)
				{
					if (statementItem._whichToken instanceof COBOL_Statement)
					{
						COBOL_Statement stmt = (COBOL_Statement) statementItem._whichToken;
						AbstractToken which = stmt._whichToken;
						if (which instanceof EagleRunnable)
						{
							EagleRunnable runnable = (EagleRunnable) which;
							runnable.interpret(this);
						}
						else throw new RuntimeException("Don't know how to interpret statement: " + which);
					}
				}
			}
		}
	}
}
