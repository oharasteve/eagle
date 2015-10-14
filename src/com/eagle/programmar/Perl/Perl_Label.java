// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 18, 2014

package com.eagle.programmar.Perl;

import com.eagle.programmar.Perl.Symbols.Perl_Label_Definition;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class Perl_Label extends TokenSequence
{
	public Perl_Label_Definition label;
	public PunctuationColon colon;
}
