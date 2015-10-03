// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Sep 24, 2015

package com.eagle.parsers;

import java.util.Hashtable;

import com.eagle.tokens.AbstractToken;

public class EagleOverrideManager
{
	// See if we need to create a specialized instance of a particular token
	private Hashtable<String, Class<? extends AbstractToken>> _classOverrides = new Hashtable<String, Class<? extends AbstractToken>>();
	
	// Override the creation of a class in the parser ...
	public void override(Class<? extends AbstractToken> oldClass, Class<? extends AbstractToken> newClass)
	{
		_classOverrides.put(oldClass.getName(), newClass);
		//System.err.println("******* Added " + oldClass.getName() + " -> " + newClass.getName());
	}
	
	public Class<? extends AbstractToken> lookup(Class<? extends AbstractToken> oldClass)
	{
		String name = oldClass.getName();
		//System.err.println("***** Searching for " + name);
		if (! _classOverrides.containsKey(name)) return null;
		//System.err.println("****** Found " + name + " -> " + _classOverrides.get(name)._newClass.getName());
		return _classOverrides.get(name);
	}
}
