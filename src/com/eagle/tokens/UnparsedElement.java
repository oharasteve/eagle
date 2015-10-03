// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 6, 2012

package com.eagle.tokens;


public abstract class UnparsedElement extends TokenSequence
{
	public abstract TokenList<? extends AbstractToken> unparsedPieces();
}
