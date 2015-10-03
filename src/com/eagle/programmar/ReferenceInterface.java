// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2010

package com.eagle.programmar;


public interface ReferenceInterface
{
	public void setDefinition(DefinitionInterface definition);
	public DefinitionInterface getDefinition();

	public String getFileName();
	public int getStartLine();
	public int getStartChar();
}
