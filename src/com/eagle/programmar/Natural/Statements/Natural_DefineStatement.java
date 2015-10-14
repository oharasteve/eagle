// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 3, 2011

package com.eagle.programmar.Natural.Statements;

import com.eagle.programmar.Natural.Natural_Subscript;
import com.eagle.programmar.Natural.Symbols.Natural_Data_Definition;
import com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference;
import com.eagle.programmar.Natural.Terminals.Natural_Comment;
import com.eagle.programmar.Natural.Terminals.Natural_DataType;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.programmar.Natural.Terminals.Natural_Level;
import com.eagle.programmar.Natural.Terminals.Natural_Literal;
import com.eagle.programmar.Natural.Terminals.Natural_Punctuation;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class Natural_DefineStatement extends TokenSequence
{
	public @DOC("sm/defineda.htm") Natural_Keyword DEFINE = new Natural_Keyword("DEFINE");
	public Natural_Keyword DATA = new Natural_Keyword("DATA");
	public Natural_Keyword LOCAL = new Natural_Keyword("LOCAL");
	public TokenList<Natural_DataLine> dataLines;
	public Natural_Keyword ENDDEFINE = new Natural_Keyword("END-DEFINE");
	
	public static class Natural_DataLine extends TokenSequence
	{
		public @OPT Natural_Comment comment;
		public Natural_Level level;
		public @OPT Natural_Keyword REDEFINE = new Natural_Keyword("REDEFINE");
		public Natural_Data_Definition name;
		public @OPT Natural_DataView dataView;
		public @OPT Natural_DataDeclaration dataDeclaration;
		public @OPT Natural_Subscript subscript;
		public @OPT Natural_DataInitialization init;
		
		public static class Natural_DataView extends TokenSequence
		{
			public Natural_Keyword VIEW = new Natural_Keyword("VIEW");
			public Natural_Keyword OF = new Natural_Keyword("OF");
			public Natural_Identifier_Reference dbTable;
		}
		
		public static class Natural_DataDeclaration extends TokenSequence
		{
			public PunctuationLeftParen leftParen;
			public Natural_DataType dataType;
			public PunctuationRightParen rightParen;
		}
		
		public static class Natural_DataInitialization extends TokenSequence
		{
			public Natural_Keyword INIT = new Natural_Keyword("INIT");
			public Natural_Punctuation lessThan = new Natural_Punctuation('<');
			public Natural_Literal literal;
			public Natural_Punctuation greaterThan = new Natural_Punctuation('>');
		}
		
		// These are special -- they are not parsed. A post-edit step fills them in.
		public @SKIP TokenList<Natural_DataLine> children = new TokenList<Natural_DataLine>();
		public Natural_DataParent parentDeclContainer = new Natural_DataParent();
		
		public static class Natural_DataParent	// Does NOT extend GenericToken -- don't want this parsed or looked at
		{
			public Natural_DataLine parentDecl;
		}
	}
}
