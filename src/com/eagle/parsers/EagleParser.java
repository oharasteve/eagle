package com.eagle.parsers;

import com.eagle.programmar.EagleLanguage;
import com.eagle.project.EagleProject;
import com.eagle.tokens.AbstractToken;

//
// Dummy file
//

public class EagleParser
{
	public boolean parse(EagleProject project, EagleLanguage lang, String canonicalName, EagleFileReader lines)
	{
		return false;
	}
	
	public boolean quickParse(String fileName, EagleFileReader lines, AbstractToken token)
	{
		return false;
	}
	
	public String getHighestPosition(String fileName)
	{
		return null;
	}
	
	public void saveXML(EagleLanguage program, String parsedFileName)
	{
	}
}
