// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 22, 2015

package com.eagle.programmar.PPSM;

import com.eagle.programmar.EagleLanguage;
import com.eagle.programmar.PPSM.Terminals.PPSM_KeywordChoice;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class PPSM_Program extends EagleLanguage
{
	public static final String NAME = "PPSM";
	
	public PPSM_Program()
	{
		super(NAME, new PPSM_Syntax());
	}
	
	@Override
	public String getDocRoot()
	{
		return "Not Applicable";
	}
	
	public static class PPSM_Element extends TokenSequence
	{
		public PPSM_KeywordChoice x = new PPSM_KeywordChoice("a", "b");
		public @OPT PPSM_KeywordChoice y = new PPSM_KeywordChoice("c", "d", "e");
	}

	public TokenList<PPSM_Element> elements;
}
