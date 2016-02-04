// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 1, 2010

package com.eagle.programmar;

import java.util.ArrayList;
import java.util.Collection;

import com.eagle.parsers.EagleOverrideManager;
import com.eagle.programmar.Python.Python_Program;
import com.eagle.programmar.Python.Python_Terminals;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.EagleScope;
import com.eagle.tokens.EagleScope.EagleScopeInterface;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;


/**
 * Generic Grammar
 */

public abstract class EagleLanguage extends TokenSequence implements EagleScopeInterface
{
 	public static final String COM_EAGLE_PROGRAMMAR = "com.eagle.programmar.";
	
	private String _name;
	private EagleScope _scope;
	private ArrayList<EagleScope> _scopes = new ArrayList<EagleScope>();
	
	public EagleLanguage(String name, EagleSyntax syntax)
	{
		_name = name;
		setSyntax(syntax);
		
		// Timing is a little strange here.
		_scope = new EagleScope(syntax._isCaseSensitive);
		_scopes.add(_scope);
	}
	
	public final String getName()
	{
		return _name;
	}
	
	// For online documentation
	public String getDocRoot()
	{
		return null;
	}
	
	
	@Override
	public EagleScope getScope()
	{
		return _scope;
	}
	
//	@Override
//	public void setScope(EagleScope scope)
//	{
//		_scope = scope;
//	}
	
	public void addScope(EagleScope scope)
	{
		_scopes.add(scope);
	}
	
	public Collection<EagleScope> getScopes()
	{
		return _scopes;
	}
	
	// In case we want to remove all the comments from the program and re-parse
	public TokenList<? extends AbstractToken> getTerminals()
	{
		return null;
	}
	
	/**
	 * @param overrideManager
	 */
	public void findClassOverrides(EagleOverrideManager overrideManager)
	{
		return; // Don't normally have any of these
	}
		
	public static EagleLanguage findTerminals(String langName)
	{
		if (langName.equalsIgnoreCase(Python_Program.NAME)) return new Python_Terminals();
		throw new RuntimeException("Unknown language: " + langName);
	}
}
