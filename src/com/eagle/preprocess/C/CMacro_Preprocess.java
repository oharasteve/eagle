// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2015

package com.eagle.preprocess.C;

import java.util.HashMap;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.parsers.ParserManager;
import com.eagle.preprocess.EagleInclude;
import com.eagle.preprocess.FindIncludeFile;
import com.eagle.programmar.CMacro.CMacro_Program;
import com.eagle.programmar.CMacro.CMacro_Program.CMacro_Element;
import com.eagle.programmar.CMacro.CMacro_Statement;
import com.eagle.programmar.CMacro.CMacro_StatementOrComment;
import com.eagle.programmar.CMacro.Statements.CMacro_Define_Statement;
import com.eagle.programmar.CMacro.Statements.CMacro_Define_Statement.CMacro_DefineWhich.CMacro_DefineSimple;
import com.eagle.programmar.CMacro.Terminals.CMacro_Definition;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TerminalEndOfLine;

public class CMacro_Preprocess extends EagleInclude
{
	public CMacro_Preprocess(HashMap<String, AbstractToken> symbolTable)
	{
		super(symbolTable);
	}
	
	@Override
	public EagleFileReader preprocessFile(EagleFileReader lines, FindIncludeFile findInclude)
	{
		_oldLines = lines;
		
		// Parse the include file
		ParserManager parser = new ParserManager();
		CMacro_Program pgm = new CMacro_Program();
		if (!parser.parseLines(lines, pgm, pgm))
		{
			return null;
		}
		
//		DumpTree dump = new DumpTree();
//		dump.dump(System.out, pgm, DumpTree.Width.WIDE, 0, true);
		
		boolean changed = false;
		
		// Look for all the #if's and #include's etc.
		for (CMacro_Element element : pgm.elements._elements)
		{
			if (preprocessElement(element, findInclude)) changed = true;
		}
		
		if (changed) return _newLines;
		return lines;
	}
	
	// Returns true iff something was changed in the file (not including the symbol table)
	public boolean preprocessElement(AbstractToken token, FindIncludeFile findInclude)
	{
		if (! (token instanceof CMacro_Element))
		{
			throw new RuntimeException("Element should be a CMacro_Element, not " + token);
		}
		
		// Ignore all the rest of the stuff
		CMacro_Element element = (CMacro_Element) token;
		AbstractToken which = element._whichToken;
		if (! (which instanceof CMacro_StatementOrComment))
		{
			copyElement(which);
			return false;
		}
		CMacro_StatementOrComment statementContainer = (CMacro_StatementOrComment) which;

		AbstractToken statement = statementContainer.stmt._whichToken;
		if (! (statement instanceof CMacro_Statement))
		{
			throw new RuntimeException("C macro statement is not handled: " + statement);
		}
		CMacro_Statement macro = ((CMacro_Statement) statement);

		// Route it to its own controller
		if (! macro.processMacro(this, findInclude))
		{
			// The macro didn't write anything on its own
			copyElement(macro);
			return false;
		}
		return true;
	}
	
	@Override
	public void copyElement(AbstractToken token)
	{
		if (token instanceof TerminalEndOfLine) return;
		
		//System.out.println("******************* token = " + token.getClass().getName());
		for (int line = token._currentLine; line <= token._endLine; line++)
		{
			EagleLineReader oldLine = _oldLines.get(line);
			//System.out.println("***** Copying " + oldLine.toString());
			
			// Returns null if nothing has changed
			String newLine = replaceWords(oldLine.toString());
			
			if (newLine == null)
			{
				_newLines.add(oldLine);
			}
			else
			{
				_newLines.add(new EagleLineReader(newLine));
			}
		}
	}

	// Returns null if nothing changed
	// QUESTION: should we ignore comments and strings? Currently: we don't check for them.
	// Careful, this is recursive
	private String replaceWords(String oldLine)
	{
		String newLine = null;
		
		int sc = 0;
		int len = oldLine.length();
		while (sc < len)
		{
			// Find start of the next word
			char ch = oldLine.charAt(sc);
			if (Character.isLetter(ch) || ch == '_')
			{
				// Found a word!
				int ec = sc;
				while (ec <= len)
				{
					ch = ' ';	// Pretend there is a space at the end of the line
					if (ec < len) ch = oldLine.charAt(ec);
					if (!Character.isLetterOrDigit(ch) && ch != '_')
					{
						String word = oldLine.substring(sc, ec);
						System.out.println("Checking " + word + " to see if it is a macro");
						if (_symbolTable.containsKey(word))
						{
							// Yes, found a macro!
							AbstractToken macro = _symbolTable.get(word);
							if (macro instanceof CMacro_Define_Statement)
							{
								CMacro_Define_Statement defineStatement = (CMacro_Define_Statement) macro;
								CMacro_DefineSimple defineSimple = (CMacro_DefineSimple) defineStatement.which._whichToken;
								CMacro_Definition macroDefinition = defineSimple.definition;
								String macroValue = macroDefinition.getValue();
								String changedLine = oldLine.substring(0, sc) + macroValue + oldLine.substring(ec, len);
								System.out.println("Replaced " + word + " with " + macroValue);
								String moreChanges = replaceWords(changedLine);		// Recursive
								if (moreChanges != null) return moreChanges;
								return changedLine;
							}
							throw new RuntimeException("Expected CMacro_Define_Statement, not " + macro);
						}
						break;
					}
					ec++;
				}
				sc = ec;	// Keep looking across the line for another word
			}

			sc++;
		}
		
		return newLine;
	}
	
	public void addLine(EagleLineReader line)
	{
		_newLines.add(line);
	}
}
