// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

package com.eagle.programmar.JavaP;

import com.eagle.programmar.EagleSyntax;

public class JavaP_Syntax extends EagleSyntax
{
	@Override
	public String syntaxId()
	{
		return "Java";
	}
	
	public JavaP_Syntax()
	{
		_isCaseSensitive = true;
		_continuationChar = null;
		_autoAdvance = false;
		_extraCharacters = "_";
		_punctuationExceptions = new String[] { "//", ";;" };
		
		addReservedWords(keywords);
	}
	
	private String[] keywords = new String[] {
			"class",
			"const",
			"public",
	};
	
	public static final String[] ACC_CODES = new String[] {
			"ACC_ABSTRACT",
			"ACC_ANNOTATION",
			"ACC_ENUM",
			"ACC_FINAL",
			"ACC_INTERFACE",
			"ACC_PRIVATE",
			"ACC_PROTECTED",
			"ACC_PUBLIC",
			"ACC_STATIC",
			"ACC_SUPER",
			"ACC_SYNTHETIC",
			"ACC_VARARGS",
	};
	
	public static final String[] COMMANDS = new String[] {
			"aaload",
			"aastore",
			"aconst_null",
			"aload", "aload_0", "aload_1", "aload_2", "aload_3",
			"anewarray",
			"areturn",
			"arraylength",
			"astore", "astore_0", "astore_1", "astore_2", "astore_3",
			"athrow",
			"bipush",
			"caload",
			"checkcast",
			"dload_0",
			"dup", "dup_x1",
			"getfield",
			"getstatic",
			"goto",
			"i2l",
			"iadd",
			"iaload",
			"iand",
			"iastore",
			"iconst_0", "iconst_1", "iconst_2", "iconst_3", "iconst_4", "iconst_5", "iconst_m1",
			"idiv",
			"if_acmpeq", "if_acmpne",
			"ifeq", "ifne", "iflt", "ifle", "ifge", "ifgt",
			"if_icmpeq", "if_icmpne", "if_icmplt", "if_icmple", "if_icmpge", "if_icmpgt",
			"ifnull", "ifnonnull",
			"iinc",
			"iload", "iload_0", "iload_1", "iload_2", "iload_3",
			"imul",
			"instanceof",
			"invokeinterface", "invokespecial", "invokestatic", "invokevirtual",
			"irem",
			"ireturn",
			"istore", "istore_0", "istore_1", "istore_2", "istore_3",
			"isub",
			"ixor",
			"ladd",
			"lcmp",
			"lconst_0", "lconst_1",
			"ldc", "ldc_w", "ldc2_w",
			"lload", "lload_0", "lload_1", "lload_2", "lload_3",
			"lookupswitch",
			"lreturn",
			"lstore",
			"l2i",
			"monitorenter",
			"monitorexit",
			"new",
			"newarray",
			"pop",
			"putfield",
			"putstatic",
			"return",
			"sipush",
			"swap",
			"tableswitch",
		};
}
