// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Apr 27, 2014

package com.eagle.programmar.Perl;

import com.eagle.programmar.Perl.Symbols.Perl_Variable_Definition;
import com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Perl_Type extends TokenChooser
{
	public @CHOICE Perl_KeywordChoice base = new Perl_KeywordChoice("array", "string");
	public @CHOICE Perl_Variable_Definition type;

	public @CHOICE static class Perl_CompoundType extends TokenSequence
	{
		public Perl_Punctuation backSlash = new Perl_Punctuation('\\');
		public Perl_Variable_Definition type;
		public @OPT TokenList<Perl_MoreTypeName> more;

		public static class Perl_MoreTypeName extends TokenSequence
		{
			public Perl_Punctuation backSlash = new Perl_Punctuation('\\');
			public Perl_Variable_Definition type;
		}
	}
}
