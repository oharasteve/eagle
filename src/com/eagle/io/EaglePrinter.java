// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 26, 2010

package com.eagle.io;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import com.eagle.programmar.EagleLanguage;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TerminalToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class EaglePrinter
{
	private final String INDENT = "\t";
	private int _tabs = 0;
	private boolean _needSpace = false;
	private boolean _justDidNewline = false;
	
	private static final boolean DEBUG = false;
	
	// Keep track of mapping between old source and new
	private ArrayList<Integer> _sourceLineNumbers = null;

	public ArrayList<Integer> write(EagleLanguage program, String fileName) 
	{
		try
		{
			_sourceLineNumbers = new ArrayList<Integer>();
			PrintStream prt = new PrintStream(fileName);
			writeToken(prt, program);
			if (!_justDidNewline) prt.println();
			prt.close();
			System.out.println("Finished writing " + fileName);
			return _sourceLineNumbers;
		}
		catch (Exception ex)
		{
			throw new RuntimeException("Error writing to " + fileName, ex);
		}
	}
	
	// Print just a single token
	public void writeToken(PrintStream prt, AbstractToken token)
	{
		writeToken(prt, token, 1, false, null);
	}
	
	// Print a single token to a string
	public String writeToken(AbstractToken token)
	{
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		PrintStream prt = new PrintStream(stream);
		writeToken(prt, token);
		prt.close();
		return stream.toString();
	}
	
	// Returns true only for SKIP fields
	private boolean updateAnnotations(PrintStream prt, Field field, int currentLine)
	{
		// Decide on spacing in preparation for next token
		if (field == null)
		{
			// First (root) call only
			_needSpace = false;
			_justDidNewline = true;
			return false;
		}

		// Ignore all @SKIP fields
		if (field.isAnnotationPresent(AbstractToken.SKIP.class)) return true;
		
		if (field.isAnnotationPresent(AbstractToken.NEWLINE.class))
		{
			if (DEBUG) prt.print("@NL@"+field+"@");
			if (!_justDidNewline)
			{
				prt.println();
				if (_sourceLineNumbers != null) _sourceLineNumbers.add(currentLine);
				
				for (int i = 0; i < _tabs; i++) prt.print(INDENT);
				_needSpace = false;
				_justDidNewline = true;
			}
		}
		else if (field.isAnnotationPresent(AbstractToken.BLANKLINE.class))
		{
			if (DEBUG) prt.print("@BL@"+field+"@");
			if (!_justDidNewline)
			{
				prt.println();
				prt.println();
				if (_sourceLineNumbers != null) _sourceLineNumbers.add(currentLine);
				if (_sourceLineNumbers != null) _sourceLineNumbers.add(currentLine);

				for (int i = 0; i < _tabs; i++) prt.print(INDENT);
				_needSpace = false;
				_justDidNewline = true;
			}
		}
		return false;
	}
	
	private void writeToken(PrintStream prt, AbstractToken token, int currentLine, boolean optional, Field field)
	{
		boolean wasIndent = false;
		boolean wasOutdent = false;

		if (updateAnnotations(prt, field, currentLine)) return;	// Don't process if it is a SKIP field
		
		if (field != null)
		{
			if (field.isAnnotationPresent(AbstractToken.INDENT.class))
			{
				if (DEBUG) prt.print("@IN@");
				if (!_justDidNewline)
				{
					prt.println();
					if (_sourceLineNumbers != null) _sourceLineNumbers.add(currentLine);
					for (int i = 0; i < _tabs; i++) prt.print(INDENT);
				}
				_tabs++;
				_needSpace = false;
				wasIndent = true;
			}
			else if (field.isAnnotationPresent(AbstractToken.OUTDENT.class))
			{
				if (DEBUG) prt.print("@OUT@");
				prt.println();
				if (_sourceLineNumbers != null) _sourceLineNumbers.add(currentLine);
				_tabs--;
				for (int i = 0; i < _tabs; i++) prt.print(INDENT);
				_needSpace = false;
				wasOutdent = true;
			}
		}

		if (token != null)
		{
			// Reason this is here is to pick up any Formatting needed, before skipping any @OPT fields
			if (optional && !token._present) return;
			
			// Ignore @NOSPACE for elements that are Optional but not present
			if (field != null && field.isAnnotationPresent(AbstractToken.NOSPACE.class))
			{
				if (DEBUG) prt.print("@NOSP@");
				_needSpace = false;
			}
	
			if (token instanceof TerminalToken)
			{
				TerminalToken term = (TerminalToken) token;
				String display = term.toString();
				
				if (display.length() > 0)
				{
					if (_needSpace && !_justDidNewline) prt.print(' ');
					prt.print(display);
					
					if (display.endsWith("\n"))
					{
						for (int i = 0; i < _tabs; i++) prt.print(INDENT);
						_justDidNewline = true;
						_needSpace = false;
					}
					else
					{
						_justDidNewline = false;
						_needSpace = true;
					}
				}
			}
			else if (token instanceof TokenChooser)
			{
				TokenChooser chooser = (TokenChooser) token;
				AbstractToken which = chooser.getWhich();
				if (which == null)
				{
					throw new RuntimeException("TokenChooser has no value: " + chooser);
				}
				writeToken(prt, which, which._currentLine, false, field);
			}
			else if (token instanceof TokenSequence)
			{
				// Must be a regular container of sub-tokens
				Class<? extends AbstractToken> cls = token.getClass();
				Field[] fields = cls.getFields();
				
				for (Field fld : fields)
				{
					Class<?> fldType = fld.getType();
					String name = fld.getName();
		
					// Skip any private / protected fields
					if (name.startsWith("this$")) continue;		// Skip inner class junk
					if ((fld.getModifiers() & Modifier.PUBLIC) == 0) continue;	// Skip internal stuff
					
					if (TokenList.class.isAssignableFrom(fldType))
					{
						// Careful with @NEWLINE on a TokenList<>. Just do it once
						updateAnnotations(prt, fld, currentLine);
						
						TokenList<? extends AbstractToken> list = null;
						try
						{
							@SuppressWarnings("unchecked")
							TokenList<? extends AbstractToken> listTemp = (TokenList<? extends AbstractToken>) fld.get(token);
							list = listTemp;
						}
						catch (Exception ex)
						{
							throw new RuntimeException("Unable to get field " + fld + " from " + token);
						}
						if (list != null)
						{
							for (AbstractToken item : list._elements)
							{
								writeToken(prt, item, item._currentLine, false, fld);
							}
						}
					}
					else
					{
						Object obj = null;
						try
						{
							obj = fld.get(token);
						}
						catch (Exception ex)
						{
							throw new RuntimeException("Unable to get field " + fld + " from " + token);
						}
						boolean opt = fld.getAnnotation(TokenSequence.OPT.class) != null;
						if (obj == null)
						{
							writeToken(prt, null, 0, opt, fld);
						}
						else
						{
							// Ignore junk ...
							if (!(obj instanceof AbstractToken)) continue;
							AbstractToken tkn = (AbstractToken) obj;
							writeToken(prt, tkn, tkn._currentLine, opt, fld);
						}
					}
				}
			}
		}

		if (wasIndent)
		{
			prt.println();
			if (_sourceLineNumbers != null) _sourceLineNumbers.add(currentLine);
			for (int i = 0; i < _tabs; i++) prt.print(INDENT);
			_justDidNewline = true;
		}
		
		// Makes the side-by-side report much better. Combines the } with its block
		if (wasOutdent && token != null)
		{
			//prt.println(); Don't like this extra line after every block
			if (_sourceLineNumbers != null) _sourceLineNumbers.add(token._currentLine);
			//_needSpace = false;
			//_justDidNewline = true;
		}
	}
	
	public static void main(String[] args)
	{
		EaglePrinter ew = new EaglePrinter();
		if (args.length < 2)
		{
			System.out.println("Usage: EagleWriter <xmlName> <outputFile>");
			System.exit(0);
		}
		String xmlName = args[0];
		String outputName = args[1];
		
		EagleReadXML reader = new EagleReadXML();
		EagleLanguage program = reader.readFrom(xmlName);

		try
		{
			ew.write(program, outputName);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
