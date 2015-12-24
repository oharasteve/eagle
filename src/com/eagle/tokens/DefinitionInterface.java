// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2010

package com.eagle.tokens;

import java.util.ArrayList;


public interface DefinitionInterface
{
	public void addReference(ReferenceInterface reference);
	public ArrayList<ReferenceInterface> listReferences();
	
	public String getFileName();
	public int getStartLine();
	public int getStartChar();
	
	public String typeName();
}
