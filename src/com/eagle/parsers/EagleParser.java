package com.eagle.parsers;

import com.eagle.programmar.EagleLanguage;
import com.eagle.project.EagleProject;
import com.eagle.tokens.AbstractToken;

//
// Dummy file
//

public class EagleParser
{
	EagleTracer _tracer = null;
	
	public void setTrace(EagleTracer tracer)
	{
		_tracer = tracer;
	}
	
	public boolean parse(EagleProject project, EagleLanguage lang, String canonicalName, EagleFileReader lines)
	{
		return false;
	}
	
	public boolean quickParse(EagleFileReader lines, EagleLanguage lang, AbstractToken token)
	{
		return false;
	}
	
	public String getStoppingPoint(String fileName)
	{
		return null;
	}
	
	public void saveXML(EagleLanguage program, String parsedFileName)
	{
	}
}
