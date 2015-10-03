// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 10, 2011

package com.eagle.programmar.PHP;

import com.eagle.programmar.EagleLanguage;
import com.eagle.programmar.HTML.HTML_DocType;
import com.eagle.programmar.HTML.HTML_Program;
import com.eagle.programmar.HTML.HTML_Syntax;
import com.eagle.programmar.HTML.Terminals.HTML_Keyword;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.programmar.Perl.Perl_Statement.Perl_SimpleStatement.Perl_StatementOrComment;
import com.eagle.programmar.Perl.Perl_Syntax;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class PHP_Program extends EagleLanguage
{
	public static final String NAME = "PHP";
	
	public PHP_Program()
	{
		super(NAME, new PHP_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "http://www.php.net/manual/en/";
	}
	
	public @OPT @SYNTAX(HTML_Syntax.class) HTML_DocType docType;
	public TokenList<PHP_Entry> entry;
	
	public static class PHP_Entry extends TokenChooser
	{
		public @SYNTAX(HTML_Syntax.class) HTML_Program html;
		public PHP_Section php;
		public @LAST PHP_IfBlock ifBlock;
	}
	
	public static class PHP_StartTag extends TokenSequence
	{
		public HTML_Punctuation startTag = new HTML_Punctuation("<?");
		public HTML_Keyword php = new HTML_Keyword("php");
	}
	
	public static class PHP_EndTag extends TokenSequence
	{
		public HTML_Punctuation endTag = new HTML_Punctuation("?>");
	}
	
	public static class PHP_Section extends TokenSequence
	{
		public PHP_StartTag startTag;
		public PHP_Body body;
		
		public static class PHP_Body extends TokenChooser
		{
			// Really wasteful ... frequently parses twice
			public static class PHP_NoEnd extends TokenSequence
			{
				public TokenList<PHP_Element> elements;
				public PHP_EndOfFile eof;	// Can't be inside another class ...
			}
			
			public static class PHP_NormalEnd extends TokenSequence
			{
				public TokenList<PHP_Element> elements;
				public PHP_EndTag endTag;
			}
		}

		public static class PHP_Element extends TokenChooser
		{
			public @SYNTAX(Perl_Syntax.class) Perl_StatementOrComment statement;
		}
	}
}
