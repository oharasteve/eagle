// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 25, 2011

package com.eagle.programmar.Perl;

import com.eagle.programmar.Perl.Perl_FunctionDefinition.Perl_Function_Parameters;
import com.eagle.programmar.Perl.Perl_Statement.Perl_ExpressionStatement.Perl_StatementSuffixModifier;
import com.eagle.programmar.Perl.Perl_Statement.Perl_SimpleStatement.Perl_StatementOrComment;
import com.eagle.programmar.Perl.Statements.Perl_BreakStatement;
import com.eagle.programmar.Perl.Statements.Perl_ChdirStatement;
import com.eagle.programmar.Perl.Statements.Perl_ChmodStatement;
import com.eagle.programmar.Perl.Statements.Perl_ClassStatement;
import com.eagle.programmar.Perl.Statements.Perl_CloseStatement;
import com.eagle.programmar.Perl.Statements.Perl_ContinueStatement;
import com.eagle.programmar.Perl.Statements.Perl_DieStatement;
import com.eagle.programmar.Perl.Statements.Perl_DoStatement;
import com.eagle.programmar.Perl.Statements.Perl_EchoStatement;
import com.eagle.programmar.Perl.Statements.Perl_ExitStatement;
import com.eagle.programmar.Perl.Statements.Perl_ForEachStatement;
import com.eagle.programmar.Perl.Statements.Perl_ForStatement;
import com.eagle.programmar.Perl.Statements.Perl_GlobalStatement;
import com.eagle.programmar.Perl.Statements.Perl_IfStatement;
import com.eagle.programmar.Perl.Statements.Perl_IncludeStatement;
import com.eagle.programmar.Perl.Statements.Perl_MyStatement;
import com.eagle.programmar.Perl.Statements.Perl_NamespaceStatement;
import com.eagle.programmar.Perl.Statements.Perl_NextStatement;
import com.eagle.programmar.Perl.Statements.Perl_PackageStatement;
import com.eagle.programmar.Perl.Statements.Perl_PrintStatement;
import com.eagle.programmar.Perl.Statements.Perl_RequireStatement;
import com.eagle.programmar.Perl.Statements.Perl_ReturnStatement;
import com.eagle.programmar.Perl.Statements.Perl_SleepStatement;
import com.eagle.programmar.Perl.Statements.Perl_SwitchStatement;
import com.eagle.programmar.Perl.Statements.Perl_ThrowStatement;
import com.eagle.programmar.Perl.Statements.Perl_TraitStatement;
import com.eagle.programmar.Perl.Statements.Perl_TryStatement;
import com.eagle.programmar.Perl.Statements.Perl_UnlinkStatement;
import com.eagle.programmar.Perl.Statements.Perl_UseStatement;
import com.eagle.programmar.Perl.Statements.Perl_VarStatement;
import com.eagle.programmar.Perl.Statements.Perl_WhileStatement;
import com.eagle.programmar.Perl.Symbols.Perl_Function_Definition;
import com.eagle.programmar.Perl.Symbols.Perl_Identifier_Reference;
import com.eagle.programmar.Perl.Terminals.Perl_Comment;
import com.eagle.programmar.Perl.Terminals.Perl_Keyword;
import com.eagle.programmar.Perl.Terminals.Perl_KeywordChoice;
import com.eagle.programmar.Perl.Terminals.Perl_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationHyphen;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Perl_Statement extends TokenChooser
{
	public @CHOICE Perl_Include include;
	public @CHOICE Perl_FunctionDefinition function;
	public @CHOICE Perl_NamespaceStatement namespaceStatement;
	public @CHOICE Perl_ClassStatement classStatement;
	public @LAST Perl_Label label;
	public @CHOICE @CURIOUS("Empty statement") PunctuationSemicolon semicolon;
	
	public @LAST static class Perl_ExpressionStatement extends TokenSequence
	{
		public Perl_Expression expr;
		public @OPT Perl_StatementSuffixModifier modifier;
		public PunctuationSemicolon semicolon;

		public static class Perl_StatementSuffixModifier extends TokenSequence
		{
			public Perl_KeywordChoice IfUnless = new Perl_KeywordChoice("if", "unless", "while");
			public @OPT Perl_MinusF minusF;
			public Perl_Expression condition;
			
			public static class Perl_MinusF extends TokenSequence
			{
				public PunctuationHyphen minus;
				public Perl_KeywordChoice DF = new Perl_KeywordChoice("d", "f");
			}
		}
	}

	public @CHOICE static class Perl_StatementBlock extends TokenSequence
	{
		public @INDENT PunctuationLeftBrace leftBrace;
		public @OPT TokenList<Perl_StatementOrComment> statements;
		public @OUTDENT PunctuationRightBrace rightBrace;
	}
	
	public @CHOICE static class Perl_SubDeclaration extends TokenSequence
	{
		public Perl_Keyword SUB = new Perl_Keyword("sub");
		public @OPT Perl_SubMain main;
		public Perl_Function_Definition fnName;
		public @OPT Perl_Function_Parameters params;
		public Perl_StatementBlock block;
		
		public static class Perl_SubMain extends TokenSequence
		{
			public Perl_Identifier_Reference id;
			public Perl_Punctuation quote = new Perl_Punctuation('\'');
		}
	}

	public @CHOICE static class Perl_SimpleStatement extends TokenSequence
	{
		public Perl_StatementList statement;
		public @OPT Perl_StatementSuffixModifier modifier;
		public PunctuationSemicolon semicolon;

		//
		// Start actual statement list here
		//
		public static class Perl_StatementList extends TokenChooser
		{
			public @CHOICE Perl_BreakStatement breakStatement;
			public @CHOICE Perl_ChdirStatement chdirStatement;
			public @CHOICE Perl_ChmodStatement chmodStatement;
			public @CHOICE Perl_CloseStatement closeStatement;
			public @CHOICE Perl_ContinueStatement continueStatement;
			public @CHOICE Perl_DieStatement dieStatement;
			public @CHOICE Perl_DoStatement doStatement;
			public @CHOICE Perl_EchoStatement echoStatement;
			public @CHOICE Perl_ExitStatement exitStatement;
			public @CHOICE Perl_GlobalStatement globalStatement;
			public @CHOICE Perl_IncludeStatement includeStatement;
			public @CHOICE Perl_MyStatement myStatement;
			public @CHOICE Perl_NextStatement nextStatement;
			public @CHOICE Perl_PackageStatement packageStatement;
			public @CHOICE Perl_PrintStatement printStatement;
			public @CHOICE Perl_RequireStatement requireStatement;
			public @CHOICE Perl_ReturnStatement returnStatement;
			public @CHOICE Perl_SleepStatement sleepStatement;
			public @CHOICE Perl_ThrowStatement throwStatement;
			public @CHOICE Perl_UnlinkStatement unlinkStatement;
			public @CHOICE Perl_UseStatement useStatement;
			public @CHOICE Perl_VarStatement varStatement;
		}
		
		public static class Perl_StatementOrComment extends TokenChooser
		{
			public @CHOICE Perl_Statement statement;
			public @CHOICE Perl_Comment comment;
		}
	}
	
	public @CHOICE static class Perl_CompundStatement extends TokenChooser
	{
		public @CHOICE Perl_ForStatement forStatement;
		public @CHOICE Perl_ForEachStatement forEachStatement;
		public @CHOICE Perl_IfStatement ifStatement;
		public @CHOICE Perl_SwitchStatement switchStatement;
		public @CHOICE Perl_TraitStatement traitStatement;
		public @CHOICE Perl_TryStatement tryStatement;
		public @CHOICE Perl_WhileStatement whileStatement;
	}
}
