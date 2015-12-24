// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 8, 2015

package com.eagle.preprocess;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleTracer;
import com.eagle.project.EagleProject;
import com.eagle.tokens.AbstractToken;

public abstract class EagleInclude
{
	protected EagleFileReader _oldLines;
	
	// Symbol table (macro names)
	public EagleSymbolTable _symbolTable;
	
	public EagleProject _project;
	
	// Macros read from one EagleFileReader and collects lines into another
	public EagleFileReader _newLines = new EagleFileReader();
	
	public int _depth;
	
	public EagleTracer _tracer;
	
	protected SavePreprocessedFile _savePreprocessedFile = new SavePreprocessedFile();
	
	public EagleInclude(EagleProject project, EagleSymbolTable symbolTable, EagleTracer tracer)
	{
		_project = project;
		_symbolTable = symbolTable;
		_tracer = tracer;
	}
	
	// Each language is a little different
	public abstract EagleFileReader preprocessFile(EagleFileReader lines);

	public EagleFileReader preprocessFile(EagleFileReader lines, int depth)
	{
		_depth = depth;
		return preprocessFile(lines);
	}
	
	// Copy an element that is not a macro
	// Currently assumes that each element is a complete line
	public abstract void copyElement(AbstractToken token);
}
