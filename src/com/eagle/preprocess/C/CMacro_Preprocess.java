// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2015

package com.eagle.preprocess.C;

import java.io.IOException;

import com.eagle.math.TokenValue;
import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.parsers.EagleTracer;
import com.eagle.parsers.ParserManager;
import com.eagle.preprocess.EagleInclude;
import com.eagle.preprocess.EagleSymbolTable;
import com.eagle.preprocess.FindIncludeFile;
import com.eagle.programmar.C.C_Program.C_StatementOrComment;
import com.eagle.programmar.C.C_Statement;
import com.eagle.programmar.CMacro.CMacro_Processable;
import com.eagle.programmar.CMacro.CMacro_Program;
import com.eagle.programmar.CMacro.CMacro_Program.CMacro_Element;
import com.eagle.programmar.CMacro.CMacro_StatementOrComment;
import com.eagle.programmar.CMacro.Statements.CMacro_Define_Statement;
import com.eagle.programmar.CMacro.Symbols.CMacro_Parameter_Definition;
import com.eagle.project.EagleProject;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TerminalEndOfLine;
import com.eagle.tokens.punctuation.PunctuationComma;

public class CMacro_Preprocess extends EagleInclude
{
	public FindIncludeFile _findInclude;
	
	public CMacro_Preprocess(EagleProject project, FindIncludeFile findInclude, EagleSymbolTable symbolTable, EagleTracer tracer)
	{
		super(project, symbolTable, tracer);
		_findInclude = findInclude;
	}
	
	public CMacro_Preprocess(CMacro_Preprocess preprocessor)
	{
		this(preprocessor._project, preprocessor._findInclude, preprocessor._symbolTable, preprocessor._tracer);
	}
	
	@Override
	public EagleFileReader preprocessFile(EagleFileReader lines)
	{
		System.out.println("===================================================");
		System.out.println("================ Pre-processing " + lines.getFileName() + " lines=" + lines.size());
		System.out.println();

		if (_depth > 0)
		{
			if (_project != null)
			{
				// The outermost #include file has already been repaired -- don't try to do it twice
				_project.performRepairs(lines.getFileName(), lines);
			}
		}
	
		// Save origin information for every file
		for (int i = 0; i < lines.size(); i++)
		{
			EagleLineReader line = lines.get(i);
			String fname = line.getOriginalFileName();
			if (fname == null)
			{
				line.setOriginalLocation(lines.getFileName(), i+1);
			}
		}

		_oldLines = lines;
		
		// Parse the include file
		ParserManager parser = new ParserManager();
		parser._parser.setTrace(_tracer);		// For debugging
		CMacro_Program pgm = new CMacro_Program();
		if (!parser.parseLines(lines, pgm, pgm))
		{
			String msg = parser._parser.getStoppingPoint(lines.getFileName());
			throw new RuntimeException("Unable to parse " + lines.getFileName() + '\n' + msg);
		}
		
//		DumpTree dump = new DumpTree();
//		dump.dump(System.out, pgm, DumpTree.Width.WIDE, 0, true);
		
		boolean changed = false;
		
		// Look for all the #if's and #include's etc.
		for (CMacro_Element element : pgm.elements._elements)
		{
			try
			{
				if (preprocessCMacroElement(element)) changed = true;
			}
			catch (Exception ex)
			{
				StringBuffer msg = new StringBuffer("Failed preprocessing ").append(element._whichToken).append('\n');
				msg.append("File ").append(lines.getFileName());
				msg.append(", line ").append(Integer.toString(element._currentLine+1)).append('\n');
				msg.append(lines.get(element._currentLine).toString()).append('\n');
				for (int i = 0; i < element._currentChar; i++) msg.append(' ');
				msg.append('^');
				System.err.println(msg.toString());
				ex.printStackTrace(System.err);
				return lines;	// Can't preprocess it -- leave it alone
			}
		}
		
		if (! changed) return lines;
		
		// Debugging: list the new file
		// SavePreprocessedFile.writeText(lines.getFileName(), _newLines);

		// Save the pre-processed file into the project artifact folder
		if (_depth == 0 && _project != null)
		{
			String prepName = lines.getFileName();
			try
			{
				_savePreprocessedFile.saveHtml(_project._artifactBase, prepName, _newLines);
			}
			catch (IOException ex)
			{
				throw new RuntimeException("Unable to write preprocessed version of " + prepName, ex);
			}
		}
		
//		// Save origin information
//		for (int i = 0; i < _newLines.size(); i++)
//		{
//			EagleLineReader line = _newLines.get(i);
//			String origFile = line.getOriginalFileName();
//			if (origFile == null)
//			{
//				line.setOriginalLocation(lines.getFileName(), i+1);
//			}
//			else
//			{
//				line.setOriginalLocation(origFile, line.getOriginalLineNumber());
//			}
//		}
		
		return _newLines;
	}

	private boolean letMacroHandleIt(AbstractToken token)
	{
		if (token instanceof CMacro_Processable)
		{
			CMacro_Processable macro = ((CMacro_Processable) token);
	
			// Route it to its own controller
			if (macro.processMacro(this))
			{
				return true;
			}
		}
		return false;
	}
	
	// Returns true iff something was changed in the file (not including the symbol table)
	public boolean preprocessCMacroElement(CMacro_Element element)
	{
		// Ignore all the rest of the stuff
		AbstractToken whichStatement = element._whichToken;
		if (whichStatement instanceof CMacro_StatementOrComment)
		{
			CMacro_StatementOrComment statementContainer = (CMacro_StatementOrComment) whichStatement;
			if (letMacroHandleIt(statementContainer.stmt._whichToken))
			{
				return true;
			}
		}

		// The macro didn't write anything on its own
		copyElement(whichStatement);
		return true;
	}
	
	// Returns true iff something was changed in the file (not including the symbol table)
	public boolean preprocessCStatement(C_StatementOrComment element)
	{
		AbstractToken whichStatement = element._whichToken;
		if (whichStatement instanceof C_Statement)
		{
			C_Statement cStatement = (C_Statement) whichStatement;
			if (letMacroHandleIt(cStatement._whichToken))
			{
				return true;
			}
		}

		// The macro didn't write anything on its own
		copyElement(element);
		return true;
	}
	
	@Override
	public void copyElement(AbstractToken token)
	{
		if (token instanceof TerminalEndOfLine) return;
		
		//System.out.println("******************* token = " + token.getClass().getName());
		for (int seq = token._currentLine; seq <= token._endLine; seq++)
		{
			if (seq == token._endLine && token._endChar < 0) break;	// Went a little too far with EOLN
			
			EagleLineReader oldLine = _oldLines.get(seq);
			//System.out.println("***** Copying " + oldLine.toString());
			
			// Returns null if nothing has changed
			String newLine = replaceWords(oldLine.toString());
			
			if (newLine == null)
			{
				_newLines.add(oldLine);
			}
			else
			{
				if (newLine.indexOf('\n') < 0)
				{
					EagleLineReader line = new EagleLineReader(newLine);
					line.setOriginalLocation(oldLine.getOriginalFileName(), oldLine.getOriginalLineNumber());
					_newLines.add(line);
				}
				else
				{
					// Must have been a multi-line macro in there
					boolean first = true;
					for (String piece : newLine.split("\\n"))
					{
						EagleLineReader line = new EagleLineReader(piece);
						if (first)
						{
							line.setOriginalLocation(oldLine.getOriginalFileName(), oldLine.getOriginalLineNumber());
							first = false;
						}
						_newLines.add(line);
					}
				}
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
						//System.out.println("Checking " + word + " to see if it is a macro");
						if (_symbolTable.isDefined(word))
						{
							// Yes, found a macro!
							TokenValue macroValue = (TokenValue) _symbolTable.findSymbol(word);
							AbstractToken macro = macroValue.getTokenValue();
							if (macro instanceof CMacro_Define_Statement)
							{
								CMacro_Define_Statement defineStatement = (CMacro_Define_Statement) macro;
								if (defineStatement.value != null)
								{
									String newPiece = defineStatement.value.getValue();
									String changedLine;
									if (defineStatement.params != null && defineStatement.params._present)
									{
										// Macro function, ugh
										if (oldLine.charAt(ec) == ' ') ec++;	// Trim leading space
										changedLine = processDefineFunction(sc, ec, word, oldLine, newPiece, defineStatement);
									}
									else
									{
										//System.out.println("Replacing " + word + " with " + newPiece);
										
										// Apply the change
										changedLine = oldLine.substring(0, sc) + newPiece + oldLine.substring(ec);
									}
									
									if (changedLine != null)
									{
										String moreChanges = replaceWords(changedLine);		// Recursive
										if (moreChanges != null) return moreChanges;
										return changedLine;
									}
								}
							}
							else
							{
								throw new RuntimeException("Expected CMacro_Define_Statement, not " + macro);
							}
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
	
	// Handle macro functions.
	private static String processDefineFunction(int sc, int ec, String word, String oldLine, String newPiece, CMacro_Define_Statement defineStatement)
	{
		if (oldLine.charAt(ec) != '(')
		{
			return null;	// Don't expand the macro -- it was supposed to be a function, but no params were passed
			//throw new RuntimeException("Expected a left paren, not " + oldLine.substring(ec));
		}
		int rparen = oldLine.indexOf(')', ec);
		if (rparen < 0)
		{
			throw new RuntimeException("Missing right paren in " + oldLine.substring(ec));
		}
		
		String[] actualParams;
		// Handle nasty things like CM_ARGS((int a, int b))
		if (rparen+1 < oldLine.length() && oldLine.charAt(ec+1) == '(' && oldLine.charAt(rparen+1) == ')')
		{
			rparen++;
			actualParams = new String[1];
			actualParams[0] = oldLine.substring(ec+1, rparen);
		}
		else	// Normal macro function
		{
			String actualParamString = oldLine.substring(ec+1, rparen);
			actualParams = actualParamString.split(",");
		}
		
		
		SeparatedList<CMacro_Parameter_Definition,PunctuationComma> formalParams = defineStatement.params.params;
		int paramCount = formalParams.getPrimaryCount();
		if (actualParams.length != paramCount)
		{
			throw new RuntimeException("Number of parameters for " + word + " does not match, actual=" + actualParams.length + ", expected=" + paramCount);
		}

		int index = 0;
		String changedPiece = newPiece;
		for (String actualParam : actualParams)
		{
			CMacro_Parameter_Definition formalParamElement = formalParams.getPrimaryElement(index);
			String formalParam = formalParamElement.toString();
			if (! formalParam.equals(actualParam.trim()))		// Don't get stuck in a loop!
			{
				//System.out.println("Replacing " + formalParam + " with " + actualParam.trim() + " in " + changedPiece);
	
				int start = -1;
				while (true)
				{
					start = changedPiece.indexOf(formalParam, start+1);
					if (start < 0) break;
	
					if (start > 0)
					{
						char ch = changedPiece.charAt(start-1);
						if (ch == '_' || Character.isLetterOrDigit(ch)) continue;
					}
					int end = start + formalParam.length();
					if (end < changedPiece.length())
					{
						char ch = changedPiece.charAt(end);
						if (ch == '_' || Character.isLetterOrDigit(ch)) continue;
					}
	
					// Ok, make the change!
					String trimmedParam = actualParam.trim();
					changedPiece = changedPiece.substring(0, start) + trimmedParam + changedPiece.substring(end);
					start += trimmedParam.length();	// Don't look at it again
				}
			}
			
			index++;
		}

		return oldLine.substring(0, sc) + changedPiece + oldLine.substring(rparen+1);
	}
	
	public void addLine(EagleLineReader line)
	{
		_newLines.add(line);
	}
}
