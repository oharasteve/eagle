// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 8, 2013

package com.eagle.programmar.Python.Statements;

import com.eagle.programmar.Python.Python_Expression;
import com.eagle.programmar.Python.Python_Syntax.Python_Multiline_Syntax;
import com.eagle.programmar.Python.Python_Variable;
import com.eagle.programmar.Python.Terminals.Python_Comment;
import com.eagle.programmar.Python.Terminals.Python_Keyword;
import com.eagle.programmar.Python.Terminals.Python_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Python_ImportStatement extends TokenSequence
{
	public Python_Keyword IMPORT = new Python_Keyword("import");
	public Python_ImportWhat what;
	public @OPT Python_FromImportAs fromAs;
	public @OPT TokenList<Python_MoreImports> moreImports;
	public @OPT Python_Comment comment;

	public static class Python_ImportWhat extends TokenChooser
	{
		public Python_Variable importName;
		public Python_Punctuation star = new Python_Punctuation('*');
		
		public static class Python_ImportList extends TokenSequence
		{
			public Python_Punctuation leftParen = new Python_Punctuation('(');
			public @SYNTAX(Python_Multiline_Syntax.class) Python_ImportListItem list;
			public @OPT Python_FromImportAs fromAs;
			public Python_Punctuation rightParen = new Python_Punctuation(')');
			
			public static class Python_ImportListItem extends TokenSequence
			{
				public @OPT Python_Expression expr;
				public @OPT Python_FromImportAs fromAs;
				public @OPT TokenList<Python_MoreImportListItem> nextItem;
				public @OPT Python_Punctuation comma = new Python_Punctuation(',');
				
				public static class Python_MoreImportListItem extends TokenSequence
				{
					public Python_Punctuation comma = new Python_Punctuation(',');
					public Python_Expression expr;
					public @OPT Python_FromImportAs fromAs;
				}
			}
		}
	}

	public static class Python_MoreImports extends TokenSequence
	{
		public Python_Punctuation comma = new Python_Punctuation(',');
		public Python_Variable importName;
		public @OPT Python_FromImportAs fromAs;
	}
	
	public static class Python_FromImportAs extends TokenSequence
	{
		public Python_Keyword AS = new Python_Keyword("as");
		public Python_Variable asName;
	}
}
