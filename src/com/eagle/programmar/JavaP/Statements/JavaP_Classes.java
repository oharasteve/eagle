// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 3, 2015

package com.eagle.programmar.JavaP.Statements;

import com.eagle.programmar.JavaP.JavaP_CodeBlock;
import com.eagle.programmar.JavaP.JavaP_Syntax;
import com.eagle.programmar.JavaP.JavaP_Value;
import com.eagle.programmar.JavaP.Blocks.JavaP_CodeLineNumbers;
import com.eagle.programmar.JavaP.Blocks.JavaP_CodeLocalValues;
import com.eagle.programmar.JavaP.Statements.JavaP_Classes.JavaP_OneClass.JavaP_MethodArgument.JavaP_MethodArg.JavaP_EmptySubscript;
import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_Identifier;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.programmar.JavaP.Terminals.JavaP_KeywordChoice;
import com.eagle.programmar.JavaP.Terminals.JavaP_Punctuation;
import com.eagle.programmar.JavaP.Terminals.JavaP_QualifiedName;
import com.eagle.programmar.JavaP.Terminals.JavaP_RestOfLine;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;
import com.eagle.tokens.punctuation.PunctuationComma;
import com.eagle.tokens.punctuation.PunctuationLeftBrace;
import com.eagle.tokens.punctuation.PunctuationLeftBracket;
import com.eagle.tokens.punctuation.PunctuationLeftParen;
import com.eagle.tokens.punctuation.PunctuationRightBrace;
import com.eagle.tokens.punctuation.PunctuationRightBracket;
import com.eagle.tokens.punctuation.PunctuationRightParen;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class JavaP_Classes extends TokenSequence
{
	public PunctuationLeftBrace leftBrace;
	public JavaP_EndOfLine eoln1;
	public @OPT TokenList<JavaP_OneClass> oneClass;
	public PunctuationRightBrace rightBrace;
	public JavaP_EndOfLine eoln2;
	
	public static class JavaP_OneClass extends TokenSequence
	{
		public @OPT TokenList<JavaP_Modifier> modifier;
		public JavaP_OneClassHeader header;
		public PunctuationSemicolon semicolon;
		public JavaP_EndOfLine eoln1;
		public TokenList<JavaP_OneClassParameter> parameters;
		public @OPT JavaP_EndOfLine eoln2;

		public static class JavaP_OneClassHeader extends TokenChooser
		{
			public @CHOICE static class JavaP_OneClassRegularHeader extends TokenSequence
			{
				public JavaP_QualifiedName type;
				public @OPT JavaP_OneClassGeneric generic;
				public @OPT JavaP_EmptySubscript subscript;
				public JavaP_OneClassWhat what;
			}

			public @CHOICE static class JavaP_OneClassStaticHeader extends TokenSequence
			{
				public PunctuationLeftBrace leftBrace;
				public PunctuationRightBrace rightBrace;
			}
		}

		public static class JavaP_Modifier extends TokenSequence
		{
			public JavaP_KeywordChoice PUBLIC = new JavaP_KeywordChoice(
					"abstract",
					"final",
					"private",
					"protected",
					"public",
					"static",
					"synchronized"
			);
		}
		
		public static class JavaP_OneClassWhat extends TokenChooser
		{
			public @LAST JavaP_QualifiedName data;
			
			public @CHOICE static class JavaP_OneClassMethod extends TokenSequence
			{
				public @OPT JavaP_QualifiedName name;
				public PunctuationLeftParen leftParen;
				public @OPT SeparatedList<JavaP_MethodArgument, PunctuationComma> params;
				public PunctuationRightParen rightParen;
				public @OPT JavaP_OneClassThrows classThrows;
				
				public static class JavaP_OneClassThrows extends TokenSequence
				{
					public JavaP_Keyword THROWS = new JavaP_Keyword("throws");
					public SeparatedList<JavaP_QualifiedName,PunctuationComma> name;
				}
				
			}
		}
		
		public static class JavaP_MethodArgument extends TokenChooser
		{
			public @CHOICE JavaP_Punctuation question = new JavaP_Punctuation('?');
			
			public @CHOICE static class JavaP_MethodArg extends TokenSequence
			{
				public @OPT JavaP_QuestionExtends question;
				public @OPT JavaP_TypeExtends type;
				public JavaP_QualifiedName name;
				public @OPT JavaP_OneClassGeneric generic;
				public @OPT JavaP_EmptySubscript subscript;
				
				public static class JavaP_QuestionExtends extends TokenSequence
				{
					public JavaP_Punctuation question = new JavaP_Punctuation('?');
					public JavaP_Keyword EXTENDS = new JavaP_Keyword("extends");
				}
	
				public static class JavaP_TypeExtends extends TokenSequence
				{
					public JavaP_Identifier typeName;
					public JavaP_Keyword EXTENDS = new JavaP_Keyword("extends");
				}
	
				public static class JavaP_EmptySubscript extends TokenSequence
				{
					public PunctuationLeftBracket leftBracket;
					public PunctuationRightBracket rightBracket;
				}
			}
		}
		
		public static class JavaP_OneClassGeneric extends TokenSequence
		{
			public JavaP_Punctuation lessThan = new JavaP_Punctuation('<');
			public SeparatedList<JavaP_MethodArgument,PunctuationComma> names;
			public JavaP_Punctuation greaterThan = new JavaP_Punctuation('>');
		}
		
		public static class JavaP_OneClassParameter extends TokenChooser
		{
			public @CHOICE JavaP_CodeBlock code;
			public @CHOICE JavaP_Signature signature;
			public @CHOICE JavaP_RuntimeVisibleAnnotations runtimeAnnotation;
			public @CHOICE JavaP_CodeLineNumbers lineNumbers;
			public @CHOICE JavaP_CodeLocalValues localValues;

			public @CHOICE static class JavaP_OneClassDescriptor extends TokenSequence
			{
				public JavaP_Keyword DESCRIPTOR = new JavaP_Keyword("descriptor");
				public PunctuationColon colon;
				public JavaP_Value value;
				public JavaP_EndOfLine eoln;
			}
			
			public @CHOICE static class JavaP_OneClassFlags extends TokenSequence
			{
				public JavaP_Keyword FLAGS = new JavaP_Keyword("flags");
				public PunctuationColon colon;
				public @OPT SeparatedList<JavaP_OneClassFlag, PunctuationComma> flags;
				public JavaP_EndOfLine eoln;
				
				public static class JavaP_OneClassFlag extends TokenChooser
				{
					public @CHOICE JavaP_KeywordChoice ACC = new JavaP_KeywordChoice(JavaP_Syntax.ACC_CODES);
				}
			}
			
			public @CHOICE static class JavaP_OneClassConstantValue extends TokenSequence
			{
				public JavaP_KeywordChoice CONSTANTVALUE = new JavaP_KeywordChoice("Constant", "ConstantValue");
				public @OPT JavaP_Keyword VALUE = new JavaP_Keyword("value");
				public PunctuationColon colon;
				public JavaP_KeywordChoice type = new JavaP_KeywordChoice("int", "long", "String");
				public JavaP_RestOfLine value;
				public JavaP_EndOfLine eoln;
			}
			
			public @CHOICE static class JavaP_OneClassExceptions extends TokenSequence
			{
				public JavaP_Keyword EXCEPTIONS = new JavaP_Keyword("Exceptions");
				public PunctuationColon colon1;
				public JavaP_EndOfLine eoln1;
				
				public JavaP_Keyword THROWS = new JavaP_Keyword("throws");
				public SeparatedList<JavaP_QualifiedName,PunctuationComma> name;
				public @OPT JavaP_EndOfLine eoln2;
			}
			
			public @CHOICE static class JavaP_OneClassMethodParameters extends TokenSequence
			{
				public JavaP_Keyword METHODPARAMETERS = new JavaP_Keyword("MethodParameters");
				public PunctuationColon colon;
				public JavaP_EndOfLine eoln1;
				
				public JavaP_Keyword NAME = new JavaP_Keyword("Name");
				public JavaP_Keyword FLAGS = new JavaP_Keyword("Flags");
				public JavaP_EndOfLine eoln2;
				
				public @OPT TokenList<JavaP_OneClassMethodParameter> params;
				
				public static class JavaP_OneClassMethodParameter extends TokenSequence
				{
					public JavaP_QualifiedName name;
					public @OPT TokenList<JavaP_Value> values;
					public JavaP_EndOfLine eoln;
				}
			}
		}
	}
}
