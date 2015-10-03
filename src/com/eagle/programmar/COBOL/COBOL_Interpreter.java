// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 23, 2012

package com.eagle.programmar.COBOL;

import com.eagle.programmar.DefinitionInterface;
import com.eagle.programmar.ReferenceInterface;
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
	
	@Override
	public int getValue(ReferenceInterface variable)
	{
		/*
		COBOL_Data_Definition definition = (COBOL_Data_Definition) variable.getDefinition();
		COBOL_DataDeclaration declaration = definition.parentDef;
		for (COBOL_DataClause clause : declaration.clauses.elements)
		{
			AbstractToken which = clause.whichToken;
			if (which instanceof COBOL_ValueClause)
			{
				COBOL_ValueClause values = (COBOL_ValueClause) which;
				for (COBOL_Picture_Value value : values.values.elements)
				{
					AbstractToken which2 = value.whichToken;
					if (which2 instanceof COBOL_Picture_Value_Number)
					{
						COBOL_Picture_Value_Number number = (COBOL_Picture_Value_Number) which2;
						return Integer.parseInt(number.number.getValue());
					}
				}
			}
		}
		*/
		throw new RuntimeException("Unable to find a value for " + variable);
	}
	
	@Override
	public void setValue(DefinitionInterface variable, int value)
	{
		/*
		COBOL_Data_Definition definition = (COBOL_Data_Definition) variable.getDefinition();
		COBOL_DataDeclaration declaration = definition.parentDef;
		for (COBOL_DataClause clause : declaration.clauses.elements)
		{
			AbstractToken which = clause.whichToken;
			if (which instanceof COBOL_ValueClause)
			{
				COBOL_ValueClause values = (COBOL_ValueClause) which;
				for (COBOL_Picture_Value pic : values.values.elements)
				{
					AbstractToken which2 = pic.whichToken;
					if (which2 instanceof COBOL_Picture_Value_Number)
					{
						COBOL_Picture_Value_Number number = (COBOL_Picture_Value_Number) which2;
						number.number.setValue(Integer.toString(value));
						return;
					}
				}
			}
		}
		*/
		throw new RuntimeException("Unable to set value of " + variable + " to " + value);
	}
}
