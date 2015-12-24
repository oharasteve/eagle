// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Aug 8, 2011

package com.eagle.programmar.C;

import com.eagle.programmar.EagleLanguage;
import com.eagle.programmar.EagleSyntax;
import com.eagle.programmar.C.C_Type.C_TypeBase.C_TypeEnum;
import com.eagle.programmar.C.Terminals.C_Comment;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class C_Program extends EagleLanguage
{
	public static final String NAME = "C";
	
	public C_Program()
	{
		super(NAME, new C_Syntax());
	}
	
	// Called from C++ constructor
	public C_Program(String name, EagleSyntax syntax)
	{
		super(name, syntax);
	}
	
	@Override
	public String getDocRoot()
	{
		return "http://www.gnu.org/s/gnu-c-manual/gnu-c-manual.html";
	}
	
	private static String[] primitives = new String[] {
			"char",
			"double",
			"float",
			"int",
			"long",
			"short",
			"void"
	};
	
	// Careful, this gets added to in some projects
	private static String[] modifiers = new String[] {
			"const",
			"extern",
			"inline",
			"register",
			"static",
			"virtual",
			"volatile"
	};

	public static void addPrimitive(String primitive)
	{
		// Append to an array
		int n = primitives.length;
		String[] temp = new String[n+1];
		for (int i = 0; i < n; i++)
		{
			if (primitives[i].equals(primitives)) return;
			temp[i] = primitives[i];
		}
		temp[n] = primitive;
		primitives = temp;
	}
	
	public static void addModifier(String modifier)
	{
		// Append to an array
		int n = modifiers.length;
		String[] temp = new String[n+1];
		for (int i = 0; i < n; i++)
		{
			if (modifiers[i].equals(modifier)) return;
			temp[i] = modifiers[i];
		}
		temp[n] = modifier;
		modifiers = temp;
	}
	
	public static String[] getPrimitives()
	{
		return primitives;
	}
	
	public static String[] getModifiers()
	{
		return modifiers;
	}
	
	public @OPT TokenList<C_StatementOrComment> elements;

	public static class C_StatementOrComment extends TokenChooser
	{
		public C_Comment comment;
		public C_TypeDef typeDef;
		public @LAST C_Data data;
		public C_Function function;
		public C_Statement statement;
		public C_Type type;
		
		public static class C_Enum extends TokenSequence
		{
			public C_TypeEnum cenum;
			public PunctuationSemicolon semicolon;
		}
	}
}
