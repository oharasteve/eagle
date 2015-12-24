// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 28, 2014

package com.eagle.parsers;

import com.eagle.tokens.AbstractToken;


//
// Only implemented by EagleParser
//

public interface ParserInterface
{
	// Main interface to parse, hoping to find the specified token
	boolean match(AbstractToken token, int depth, String prefix);

	// Current positioning in the file
	String getFileName();
	int getStartLine();
	int getStartChar();
	void setStartLine(int line);
	void setStartChar(int sc);
	String getLine(int line);
	
	// Search depth
	int getCurrDepth();
	String getIndent();

	// Get past this token and get ready for the next one
	void getToEndOfThisToken(AbstractToken matchSoFar);
	
	// Substitute for 'new' in case they want to override a class
	AbstractToken createAnInstance(Class<? extends AbstractToken> subClass);
	
	// Unparsed tokens are collected into a list for presentation later
	UnparsedTokenManager getUnparsedTokenManager();

	// Curious items are collected into a list
	UnusualTokenManager getUnusualTokenManager();
	
	// Cache fields to improve speed. We assume reflection is slow.
	ParserCache getParserCache();
}
