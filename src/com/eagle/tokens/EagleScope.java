// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 19, 2013

package com.eagle.tokens;

import java.util.Collection;
import java.util.TreeMap;

import com.eagle.programmar.DefinitionInterface;

public class EagleScope
{
	private boolean _caseSensitive;
	
	@SuppressWarnings("unused")
	private EagleScope()
	{
		// Don't want anybody to create one without specifying case sensitive or not
	}
	
	public EagleScope(boolean caseSensitive)
	{
		_caseSensitive = caseSensitive;
	}
	
	private TreeMap<String, DefinitionInterface> allSymbols = new TreeMap<String, DefinitionInterface>();

	public interface EagleScopeInterface
	{
		public EagleScope getScope();
//		public void setScope(EagleScope scope);
	}

	// Add a new symbol to this scope level.
	public void addSymbol(DefinitionInterface token)
	{
		if (_caseSensitive)
		{
			allSymbols.put(token.toString(), token);
		}
		else
		{
			allSymbols.put(token.toString().toLowerCase(), token);
		}
	}
	
	// Search for a symbol name (only at this scope level)
	public DefinitionInterface findSymbol(String name)
	{
		if (_caseSensitive)
		{
			if (allSymbols.containsKey(name)) return allSymbols.get(name);
		}
		else
		{
			String lower = name.toLowerCase();
			if (allSymbols.containsKey(lower)) return allSymbols.get(lower);
		}
		return null;
	}
	
	// Return all symbols at this scope level.
	public Collection<DefinitionInterface> allSymbols()
	{
		return allSymbols.values();
	}
}
