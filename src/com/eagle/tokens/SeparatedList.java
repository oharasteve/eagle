// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 21, 2013

// This does NOT work at all. Gets some kind of nasty java.lang.InstantiationException

package com.eagle.tokens;

// Think of this as "T { P T }*", such as "1, 211, 3, 18" where T=number P=comma

public class SeparatedList<T extends AbstractToken, P extends AbstractToken> extends TokenList<T>
{
}
