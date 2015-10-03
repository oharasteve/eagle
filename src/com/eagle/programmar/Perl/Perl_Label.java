// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 18, 2014

package com.eagle.programmar.Perl;

import com.eagle.programmar.Perl.Symbols.Perl_Label_Definition;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.TokenSequence;

public class Perl_Label extends TokenSequence
{
	public Perl_Label_Definition label;
	public Perl_Punctuation colon = new Perl_Punctuation(':');
}
