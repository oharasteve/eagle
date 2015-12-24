// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

package com.eagle.programmar.JavaP;

import com.eagle.programmar.JavaP.Statements.JavaP_Classes;
import com.eagle.programmar.JavaP.Statements.JavaP_Classfile;
import com.eagle.programmar.JavaP.Statements.JavaP_CompiledFrom;
import com.eagle.programmar.JavaP.Statements.JavaP_ConstantPool;
import com.eagle.programmar.JavaP.Statements.JavaP_InnerClasses;
import com.eagle.programmar.JavaP.Statements.JavaP_PublicClass;
import com.eagle.programmar.JavaP.Statements.JavaP_RuntimeVisibleAnnotations;
import com.eagle.programmar.JavaP.Statements.JavaP_Signature;
import com.eagle.programmar.JavaP.Statements.JavaP_SourceFile;
import com.eagle.tokens.TokenChooser;

public class JavaP_Statement extends TokenChooser
{
	public JavaP_Classes classes;
	public JavaP_Classfile classfile;
	public JavaP_CompiledFrom compiledFrom;
	public JavaP_ConstantPool constantPool;
	public JavaP_InnerClasses innerClasses;
	public JavaP_PublicClass publicClass;
	public JavaP_RuntimeVisibleAnnotations runtimeVisibleAnnotations;
	public JavaP_Signature signature;
	public JavaP_SourceFile sourceFile;
}
