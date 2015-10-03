// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

package com.eagle.programmar.CMacro.Statements;

import com.eagle.programmar.C.Terminals.C_Character_Literal;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.programmar.C.Terminals.C_HexNumber;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_Literal;
import com.eagle.programmar.C.Terminals.C_Number;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.programmar.C.Terminals.C_PunctuationChoice;
import com.eagle.programmar.CMacro.Symbols.CMacro_Define_Definition;
import com.eagle.programmar.CMacro.Symbols.CMacro_Identifier_Reference;
import com.eagle.programmar.CMacro.Symbols.CMacro_Parameter_Definition;
import com.eagle.programmar.CMacro.Terminals.CMacro_EndOfLine;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class CMacro_Define_Statement extends TokenSequence
{
	public @DOC("Macros.html") C_Keyword DEFINE = new C_Keyword("define");
	public CMacro_Define_Definition var;
	public @OPT CMacro_Define_Parameters params;
	public @OPT TokenList<CMacro_DefineItem> item;
	
	public static class CMacro_Define_Parameters extends TokenSequence
	{
		public C_Punctuation leftParen = new C_Punctuation('(');
		public CMacro_Parameter_Definition var;
		public @OPT TokenList<CMacro_More_Parameter> more;
		public C_Punctuation rightParen = new C_Punctuation(')');
		
		public static class CMacro_More_Parameter extends TokenSequence
		{
			public C_Punctuation comma = new C_Punctuation(',');
			public CMacro_Parameter_Definition var;
		}
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
			public C_Punctuation leftParen = new C_Punctuation('(');
			public CMacro_Parameter_Definition param;
			public @OPT TokenList<CMacro_MoreParameters> more;
			public C_Punctuation rightParen = new C_Punctuation(')');
			
			public static class CMacro_MoreParameters extends TokenSequence
			{
				public C_Punctuation comma = new C_Punctuation(',');
				public CMacro_Parameter_Definition param;
			}
		}
	}
}
