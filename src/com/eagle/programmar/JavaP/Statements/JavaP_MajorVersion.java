// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jan 13, 2016

package com.eagle.programmar.JavaP.Statements;

import com.eagle.programmar.JavaP.Terminals.JavaP_EndOfLine;
import com.eagle.programmar.JavaP.Terminals.JavaP_Keyword;
import com.eagle.programmar.JavaP.Terminals.JavaP_Number;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationColon;

public class JavaP_MajorVersion extends TokenSequence
{
	public JavaP_Keyword MAJOR = new JavaP_Keyword("major");
	public JavaP_Keyword VERSION = new JavaP_Keyword("version");
	public PunctuationColon colon;
	public JavaP_Number version;
	public JavaP_EndOfLine eoln;
}
