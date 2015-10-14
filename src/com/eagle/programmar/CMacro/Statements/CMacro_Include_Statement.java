// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2011

package com.eagle.programmar.CMacro.Statements;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.preprocess.FindIncludeFile;
import com.eagle.preprocess.C.CMacro_Preprocess;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.programmar.C.Terminals.C_Literal;
import com.eagle.programmar.C.Terminals.C_Punctuation;
import com.eagle.programmar.CMacro.CMacro_Statement;
import com.eagle.programmar.CMacro.Terminals.CMacro_Identifier;
import com.eagle.tokens.SeparatedList;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;
import com.eagle.tokens.punctuation.PunctuationSlash;

public class CMacro_Include_Statement extends CMacro_Statement
{
	public @DOC("Include-Syntax.html") C_Keyword INCLUDE = new C_Keyword("include");
	public CMacro_IncludeWhat what;
	
	public static class CMacro_IncludeWhat extends TokenChooser
	{
		public C_Literal filename;
		
		public static class CMacro_IncludeBuiltin extends TokenSequence
		{
			public C_Punctuation lessThen = new C_Punctuation('<');
			public @OPT CMacro_Include_Sys sys;
			public SeparatedList<CMacro_Identifier,PunctuationPeriod> file;
			public C_Punctuation greaterThan = new C_Punctuation('>');
			
			public static class CMacro_Include_Sys extends TokenSequence
			{
				public CMacro_Identifier library;
				public PunctuationSlash slash;
			}
		}
	}
	
	@Override
	public boolean processMacro(CMacro_Preprocess preprocessor, FindIncludeFile findInclude)
	{
		if (! (what._whichToken instanceof C_Literal)) return false;
		String macroName = ((C_Literal) what._whichToken).getValue();
		System.out.println("#include " + macroName);
		String[] macroText = findInclude.findFile("", macroName);
		if (macroText == null) return false;

		CMacro_Preprocess innerPreprocessor = new CMacro_Preprocess(preprocessor._symbolTable);
		EagleFileReader macro = new EagleFileReader(macroText);
		EagleFileReader macroLines = innerPreprocessor.preprocessFile(macro, findInclude);
		for (EagleLineReader line : macroLines.lines())
		{
			preprocessor.addLine(line);
		}
		
		return true;
	}
}