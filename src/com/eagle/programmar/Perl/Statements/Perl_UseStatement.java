// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 17, 2011

package com.eagle.programmar.Perl.Statements;

import com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Perl_UseStatement extends TokenSequence
{
	public Perl_Keyword USE = new Perl_Keyword("use");
	public Perl_UseWhat what;
	
	public static class Perl_UseWhat extends TokenChooser
	{
		public @FIRST static class Perl_UseRemote extends TokenSequence
		{
			public Perl_KeywordChoice NET = new Perl_KeywordChoice(
					"Getopt", "IPC", "Net", "Win32");
			public @OPT Perl_Punctuation colonColon = new Perl_Punctuation("::");
			public @OPT Perl_Identifier_Reference id;
		}

		public @CHOICE static class Perl_UseLocal extends TokenSequence
		{
			public @OPT Perl_Punctuation backSlash = new Perl_Punctuation('\\');
			public Perl_Identifier_Reference id;
			public @OPT TokenList<Perl_MoreUse> more;

			public static class Perl_MoreUse extends TokenSequence
			{
				public Perl_Punctuation backSlash = new Perl_Punctuation('\\');
				public Perl_Identifier_Reference id;
			}
		}
	}
}
