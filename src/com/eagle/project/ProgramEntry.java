// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Oct 3, 2015

package com.eagle.project;

import com.eagle.project.EagleProject.ParseStatus;

public class ProgramEntry extends ProjectEntry
{
	public String languageName;
	public ParseStatus status;
	
	public String parsedFile;		// XML file, relative to project.artifactBase
	public String parseFailedFile;	// HTML file, relative to project.artifactBase
	
	public String programView;		// Html file with source code, linked to symbolTable
	public String symbolTable;		// Html file with symbol table, linked to programView

	public String javaFile;			// Generated java file
	public String javaClassDir;		// Directory for compiled java class file
	public String javaHtmlReport;	// Side-by-side report from transformation

	public String csFile;			// Generated C# file
	public String csExeFile;		// Compiled C# executable
	public String csHtmlReport;		// Side-by-side report from transformation
	
	public String inputFile;		// For testing
	public String outputFile;		// Expected results, for testing
	public String actualOutput;		// Actual output, in C:\Temp
}
