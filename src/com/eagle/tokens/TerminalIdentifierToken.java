// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 8, 2011

package com.eagle.tokens;

import java.util.ArrayList;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.programmar.DefinitionInterface;
import com.eagle.programmar.EagleSyntax;
import com.eagle.programmar.ReferenceInterface;
import com.eagle.tokens.EagleScope.EagleScopeInterface;


public abstract class TerminalIdentifierToken extends TerminalToken
{
	protected final String UPPERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	protected final String LOWERS = "abcdefghijklmnopqrstuvwxyz";
	protected final String ALPHAS = UPPERS + LOWERS;
	protected final String DIGITS = "0123456789";
	
	protected String _id;

	///////////////////////////////////////////////////////////////
	// Only used for DefinitionInterface
	//
	private ArrayList<ReferenceInterface> _allReferences = null;
	
	public void addReference(ReferenceInterface reference)
	{
		if (_allReferences == null) _allReferences = new ArrayList<ReferenceInterface>();
		if (! _allReferences.contains(reference)) _allReferences.add(reference);
	}

	public ArrayList<ReferenceInterface> listReferences()
	{
		return _allReferences;
	}
	
	public void addDefinitionToScope(DefinitionInterface def)
	{
		AbstractToken parent = this.getParent();
		while (parent != null)
		{
			if (parent instanceof EagleScopeInterface)
			{
				EagleScopeInterface scopeInterface = (EagleScopeInterface) parent;
				EagleScope scope = scopeInterface.getScope();
				scope.addSymbol(def);
				return;
			}
			parent = parent.getParent();
		}

		////////// Can't always fail if not found .... might be a program snippet from transformation generator
        // throw new RuntimeException("No surrounding scope found for " + def.typeName() +
        // " on line " + (def.getStartLine()+1));
	}

	///////////////////////////////////////////////////////////////
	// Only used for ReferenceInterface
	//
	private DefinitionInterface _definition = null;

	public void setDefinition(DefinitionInterface def)
	{
		_definition = def;
	}
	
	public DefinitionInterface getDefinition()
	{
		// Try searching for it, on the fly!
		if (_definition == null)
		{
			AbstractToken parent = this.getParent();
			while (parent != null)
			{
				if (parent instanceof EagleScopeInterface)
				{
					EagleScopeInterface scopeInterface = (EagleScopeInterface) parent;
					EagleScope scope = scopeInterface.getScope();
					_definition = scope.findSymbol(_id);
					if (_definition != null)
					{
						setDefinition(_definition);
						_definition.addReference((ReferenceInterface) this);
						//System.out.println("**Found DEF=" + _definition + " **For REF=" + this);
						break;
					}
				}
				parent = parent.getParent();
			}
		}
		return _definition;
	}

	///////////////////////////////////////////////////////////////
	// Used by all Definitions and References
	//
	protected boolean genericIdentifier(EagleFileReader lines, String firstChars, String moreChars,
			boolean requireLetters)
	{
		if (findStart(lines) == FOUND.EOF) return false;

		EagleLineReader rec = lines.get(_currentLine);
		int recLen = rec.length();
		if (_currentChar >= recLen) return false;
		char ch = rec.charAt(_currentChar);
		boolean validYet = false;
		if (firstChars.indexOf(ch) >= 0)
		{
			int endChar = _currentChar;
			while (true)
			{
				if (!validYet)
				{
					if (requireLetters) validYet = Character.isLetter(ch);
					else validYet = ! Character.isDigit(ch);
				}
				
				endChar++;
				if (endChar >= recLen) break;
				ch = rec.charAt(endChar);
				if (moreChars.indexOf(ch) < 0) break;
			}
			
			// All numbers? can't be an identifier
			if (! validYet) return false;	

			_id = rec.substring(_currentChar, endChar);
			
			// Make sure it is not a pre-defined keyword
			EagleSyntax syntax = this.getSyntax();
			if (syntax.isReserved(_id)) return false;
			
			foundIt(_currentLine, endChar - 1);
			return true;
		}
		return false;
	}
	
	@Override
	public String getDisplayStyleName()
	{
		return "identifier";
	}
	
	@Override
	public String toString()
	{
		return _id;
	}
	
	@Override
	public void setValue(String val)
	{
		_id = val;
	}
	
	@Override
	public String showString()
	{
		return "Identifier";
	}

	@Override
	public String description()
	{
		return "An identifier";
	}

	public String getFileName()
	{
		return _fileName;
	}
	
	public int getStartLine()
	{
		return _currentLine;
	}
	
	public int getStartChar()
	{
		return _currentChar;
	}
}
