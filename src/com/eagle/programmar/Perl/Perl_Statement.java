// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 25, 2011

package com.eagle.programmar.Perl;

import com.eagle.programmar.Perl.Perl_FunctionDefinition.Perl_Function_Parameters;
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

public class Perl_Statement extends TokenChooser
{
	public Perl_Include include;
	public Perl_FunctionDefinition function;
	public @LAST Perl_Label label;
	public @CURIOUS("Empty statement") Perl_Punctuation semicolon = new Perl_Punctuation(';');
	
	public @LAST static class Perl_ExpressionCallStatement extends TokenSequence
	{
		public Perl_Expression expr;
		public @OPT Perl_StatementModifier modifier;
		public Perl_Punctuation semicolon = new Perl_Punctuation(';');
	}

	public static class Perl_StatementBlock extends TokenSequence
	{
		public @INDENT Perl_Punctuation leftBrace = new Perl_Punctuation('{');
		public @OPT TokenList<Perl_StatementOrComment> statements;
		public @OUTDENT Perl_Punctuation rightBrace = new Perl_Punctuation('}');
	}
	
	public static class Perl_SubDeclaration extends TokenSequence
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

	public static class Perl_StatementModifier extends TokenSequence
	{
		public Perl_KeywordChoice IfUnless = new Perl_KeywordChoice("if", "unless", "while");
		public @OPT Perl_MinusF minusF;
		public Perl_Expression condition;
		
		public static class Perl_MinusF extends TokenSequence
		{
			public Perl_Punctuation minus = new Perl_Punctuation('-');
			public Perl_KeywordChoice DF = new Perl_KeywordChoice("d", "f");
		}
	}

	public static class Perl_SimpleStatement extends TokenSequence
	{
		public Perl_StatementList statement;
		public @OPT Perl_StatementModifier modifier;
		public @OPT Perl_Punctuation semicolon = new Perl_Punctuation(';');

		//
		// Start actual statement list here
		//
		public static class Perl_StatementList extends TokenChooser
		{
			public Perl_BreakStatement breakStatement;
			public Perl_ChdirStatement chdirStatement;
			public Perl_ChmodStatement chmodStatement;
			public Perl_ClassStatement classStatement;
			public Perl_CloseStatement closeStatement;
			public Perl_ContinueStatement continueStatement;
			public Perl_DieStatement dieStatement;
			public Perl_DoStatement doStatement;
			public Perl_EchoStatement echoStatement;
			public Perl_ExitStatement exitStatement;
			public Perl_GlobalStatement globalStatement;
			public Perl_IncludeStatement includeStatement;
			public Perl_MyStatement myStatement;
			public Perl_NamespaceStatement namespaceStatement;
			public Perl_NextStatement nextStatement;
			public Perl_PackageStatement packageStatement;
			public Perl_PrintStatement printStatement;
			public Perl_RequireStatement requireStatement;
			public Perl_ReturnStatement returnStatement;
			public Perl_SleepStatement sleepStatement;
			public Perl_ThrowStatement throwStatement;
			public Perl_UnlinkStatement unlinkStatement;
			public Perl_UseStatement useStatement;
			public Perl_VarStatement varStatement;
		}
		
		public static class Perl_StatementOrComment extends TokenChooser
		{
			public Perl_Statement statement;
			public Perl_Comment comment;
		}
	}
	
	public static class Perl_CompundStatement extends TokenChooser
	{
		public Perl_ForStatement forStatement;
		public Perl_ForEachStatement forEachStatement;
		public Perl_IfStatement ifStatement;
		public Perl_SwitchStatement switchStatement;
		public Perl_TraitStatement traitStatement;
		public Perl_TryStatement tryStatement;
		public Perl_WhileStatement whileStatement;
	}
}
