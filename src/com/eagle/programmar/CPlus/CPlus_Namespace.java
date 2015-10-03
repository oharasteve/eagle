// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Jan 25, 2015

package com.eagle.programmar.CPlus;

import com.eagle.programmar.C.C_Program.C_StatementOrComment;
import com.eagle.programmar.C.Symbols.C_Namespace_Definition;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CPlus_Namespace extends TokenSequence
{
	public C_Keyword NAMESPACE = new C_Keyword("namespace");
	public @OPT C_Namespace_Definition namespace;
	public C_Punctuation leftBrace = new C_Punctuation('{');
	public TokenList<CPlus_NamespaceElement> statements;
	public C_Punctuation rightBrace = new C_Punctuation('}');
	
	public static class CPlus_NamespaceElement extends TokenChooser
	{
		public CPlus_Namespace cpp_namespace;
		public CPlus_Class cpp_class;
		public @LAST C_StatementOrComment c_stmt;
	}
}