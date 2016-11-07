// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 23, 2015

package com.eagle.programmar.JavaP;

import com.eagle.programmar.JavaP.Statements.JavaP_Classes;
import com.eagle.programmar.JavaP.Statements.JavaP_Classfile;
import com.eagle.programmar.JavaP.Statements.JavaP_CompiledFrom;
import com.eagle.programmar.JavaP.Statements.JavaP_ConstantPool;
import com.eagle.programmar.JavaP.Statements.JavaP_InnerClasses;
import com.eagle.programmar.JavaP.Statements.JavaP_MajorVersion;
import com.eagle.programmar.JavaP.Statements.JavaP_MinorVersion;
import com.eagle.programmar.JavaP.Statements.JavaP_PublicClass;
import com.eagle.programmar.JavaP.Statements.JavaP_RuntimeVisibleAnnotations;
import com.eagle.programmar.JavaP.Statements.JavaP_Signature;
import com.eagle.programmar.JavaP.Statements.JavaP_SourceFile;
import com.eagle.tokens.TokenChooser;

public class JavaP_Statement extends TokenChooser
{
	public @CHOICE JavaP_Classes classes;
	public @CHOICE JavaP_Classfile classfile;
	public @CHOICE JavaP_CompiledFrom compiledFrom;
	public @CHOICE JavaP_ConstantPool constantPool;
	public @CHOICE JavaP_InnerClasses innerClasses;
	public @CHOICE JavaP_MajorVersion majorVersion;
	public @CHOICE JavaP_MinorVersion minorVersion;
	public @CHOICE JavaP_PublicClass publicClass;
	public @CHOICE JavaP_RuntimeVisibleAnnotations runtimeVisibleAnnotations;
	public @CHOICE JavaP_Signature signature;
	public @CHOICE JavaP_SourceFile sourceFile;
}
