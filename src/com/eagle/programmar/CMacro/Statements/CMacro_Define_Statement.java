// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

package com.eagle.programmar.CMacro.Statements;

import com.eagle.preprocess.FindIncludeFile;
import com.eagle.preprocess.C.CMacro_Preprocess;
import com.eagle.programmar.C.Terminals.C_Character_Literal;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_HexNumber;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_Literal;
import com.eagle.programmar.C.Terminals.C_Number;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.programmar.C.Terminals.C_PunctuationChoice;
import com.eagle.programmar.CMacro.CMacro_Statement;
import com.eagle.programmar.CMacro.Symbols.CMacro_Define_Definition;
import com.eagle.programmar.CMacro.Symbols.CMacro_Identifier_Reference;
import com.eagle.programmar.CMacro.Symbols.CMacro_Parameter_Definition;
import com.eagle.programmar.CMacro.Terminals.CMacro_Definition;
import com.eagle.programmar.CMacro.Terminals.CMacro_EndOfLine;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightParen;

public class CMacro_Define_Statement extends CMacro_Statement
{
	public @DOC("Macros.html") C_Keyword DEFINE = new C_Keyword("define");
	public CMacro_Define_Definition var;
	public CMacro_DefineWhich which;
	
	public static class CMacro_DefineWhich extends TokenChooser
	{
		public static class CMacro_DefineSimple extends TokenSequence
		{
			public CMacro_Definition definition;
		}
		
		public @FIRST static class CMacro_DefineFunction extends TokenSequence
		{
			public CMacro_Define_Parameters params;
			public TokenList<CMacro_DefineItem> item;
		}
	}
	
	public static class CMacro_Define_Parameters extends TokenSequence
	{
		public PunctuationLeftParen leftParen;
		public SeparatedList<CMacro_Parameter_Definition, PunctuationComma> vars;
		public PunctuationRightParen rightParen;
	}
	
	public static class CMacro_DefineItem extends TokenChooser
	{
		public C_Comment comment;
		public CMacro_Identifier_Reference var;
		public C_Number number;
		public C_HexNumber hex;
		public C_Literal literal;
		public C_Character_Literal charLiteral;
		public C_PunctuationChoice dotDotDot = new C_PunctuationChoice(
				"...", "==", "<=", ">=", "!=", "++", "--", "->", "||", "&&", "##",
				"(", ")", "/", "|", "<", ">", "+", "&", "%", "-", "*", "[", "]",
				"!", "=", ";", ".", ",", "~", "?", ":", "{", "}", "#");
		
		public static class CMacro_Continued extends TokenSequence
		{
			public C_Punctuation continuationMarker = new C_Punctuation('\\');
			public CMacro_EndOfLine endOfLine;
		}
		
		public static class CMacro_Parameters extends TokenSequence
		{
			public PunctuationLeftParen leftParen;
			public SeparatedList<CMacro_Parameter_Definition,PunctuationComma> params;
			public PunctuationRightParen rightParen;
		}
	}
	
	@Override
	public boolean processMacro(CMacro_Preprocess preprocessor, FindIncludeFile findInclude)
	{
		String macroName = var.getValue();
		System.out.println("#define " + macroName + " ...");
		preprocessor.setSymbol(macroName, this);
		return true;	// No need to add these to the file
	}
}
