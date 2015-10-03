// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 13, 2010

package com.eagle.project;

import java.io.File;
import java.util.ArrayList;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.eagle.EagleReadXML;
import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleOverrideManager;
import com.eagle.parsers.EagleParseException.EagleSoftParseException;
import com.eagle.parsers.EagleParser;
import com.eagle.parsers.ParserManager;
import com.eagle.programmar.EagleLanguage;
import com.eagle.programmar.EagleLanguageLookup;
import com.eagle.project.ProjectEntry.ProgramEntry;
import com.eagle.project.ProjectEntry.TextEntry;
import com.eagle.utils.EagleFile;
import com.eagle.utils.EaglePath;

@RunWith(Parameterized.class)

public abstract class EagleProject
{
	public String sourceBase;		// Including the C: but no trailing backslash
	public String artifactBase;		// Including the C: but no trailing backslash

	public String programViewCss;
	public String symbolTableCss;
	
	protected EagleLanguageLookup _languageLookup = new EagleLanguageLookup();
	
	protected RepairFile _repair = new RepairFile();
	
	/**
	 * @param overrider  
	 */
	public void findClassOverrides(EagleOverrideManager overrider)
	{
		return; // Don't normally have any of these
	}

	public static enum ParseStatus
	{
		ALREADY_PARSED,
		PARSE_FAILURE,
		PARSED_SUCCESSFULLY
	}
	
	private ArrayList<ProjectEntry> _entries = new ArrayList<ProjectEntry>();
	private boolean _entriesLoaded = false;

	public int getTabSize()
	{
		return 0;	// Means don't bother converting tabs to spaces
	}
	
	public abstract String getName();
	
	public ArrayList<ProjectEntry> getEntries()
	{
		if (!_entriesLoaded)
		{
			_entriesLoaded = true;
			addEntries("");
		}
		return _entries;
	}
	
	/**
	 * @param parser 
	 */
	public void findClassOverrides(EagleParser parser)
	{
		return; // Don't normally have any of these
	}
	
	//
	// Manage the files in the project
	//

	protected void addEntry(ProjectEntry entry)
	{
		_entries.add(entry);
	}
	
	/**
	 * In case the project wants to exclude some files
	 * @param dir 
	 * @param fname  
	 */
	protected boolean rejectEntry(String dir, String fname)
	{
		return false;
	}
	
	// Careful, recursive
	private void addEntries(String sourceDir)
	{
		String dirName = EaglePath.combinePaths(sourceBase, sourceDir);
		File dir = new File(dirName);
		
		if (!dir.exists()) throw new RuntimeException(dirName + " does not exist.");
		if (!dir.isDirectory()) throw new RuntimeException(dirName + " exists, but is not a directory.");
		
		File[] files = dir.listFiles();
		
		// System.out.println("Processing " + sourceDir + "  files=" + files.length);
		
		programViewCss = EaglePath.combinePaths("VIEWER", "ProgramViewer.css");
		symbolTableCss = EaglePath.combinePaths("SYMBOLS", "SymbolTable.css");
		
		for (File file : files)
		{
			if (!file.isDirectory())
			{
				String fname = file.getName();
				String languageName = _languageLookup.lookupSuffix(fname);
				if (languageName != null && file.length() > 0)
				{
					// Might want to exclude some files
					if (rejectEntry(sourceDir, fname)) continue;
					
					if (languageName.equalsIgnoreCase("TXT"))
					{
						TextEntry entry = new TextEntry();
						entry.sourceFile = EaglePath.combinePaths(sourceDir, fname);
						_entries.add(entry);
					}
					else
					{
						ProgramEntry entry = new ProgramEntry();
						entry.languageName = languageName;
		
						entry.sourceFile = EaglePath.combinePaths(sourceDir, fname);
						EagleFile ef = new EagleFile(entry);
						
						String baseNameAndPath = EaglePath.combinePaths(ef.getPath(), ef.getBaseName() + "_" + ef.getExtension());
						
						entry.parsedFile = EaglePath.combinePaths("PARSED", baseNameAndPath + ".xml");
						entry.parseFailedFile = EaglePath.combinePaths("PARSED", baseNameAndPath + "_failed.html");
		
						entry.programView = EaglePath.combinePaths("VIEWER", baseNameAndPath + ".html"); 
						entry.symbolTable = EaglePath.combinePaths("SYMBOLS", baseNameAndPath + ".html");
	
						_entries.add(entry);
					}
				}
				else
				{
					// Oh well, just treat it as a generic file, not a program
					ProjectEntry entry = new ProjectEntry();
					entry.sourceFile = EaglePath.combinePaths(sourceDir, fname);
					_entries.add(entry);
				}
			}
		}
		
		// Loops could be combined, but I want the files first, then the sub-directories second
		for (File file : files)
		{
			// Now search the sub-directory
			if (file.isDirectory())
			{
				if (!file.getName().startsWith("."))
				{
					addEntries(EaglePath.combinePaths(sourceDir, file.getName()));
				}
			}
		}
	}
	
	protected void parseFiles(boolean force)
	{
		for (ProjectEntry file : getEntries())
		{
			if (!(file instanceof ProgramEntry)) continue;
			ProgramEntry entry = (ProgramEntry) file;
				
			boolean needsParsing = true;
			
			String parsedFileName = EaglePath.combinePaths(artifactBase, entry.parsedFile);
			String sourceFileName = EaglePath.combinePaths(sourceBase, entry.sourceFile);
			
			// Check date/time stamps to see if it needs to be re-parsed
			if (!force)
			{
				File parsedFile = new File(parsedFileName);
				if (parsedFile.exists())
				{
					File sourceFile = new File(sourceFileName);
					if (!sourceFile.exists()) throw new RuntimeException("Unable to read " + sourceFile);
					
					if (sourceFile.lastModified() < parsedFile.lastModified()) needsParsing = false;
				}
				else
				{
					// Make sure the directory exists
					EagleFile ef = new EagleFile(parsedFileName);
					EaglePath.createDir(ef.getPath());
				}
			}
			
			if (!needsParsing)
			{
				entry.status = ParseStatus.ALREADY_PARSED;
			}
			else
			{
				EagleLanguage program = _languageLookup.findLanguage(entry.languageName);
				
				if (program != null)
				{
					ParserManager p = new ParserManager();
					int tabs = getTabSize();
					if (tabs > 0)
					{
						p._convertTabs = true;
						p._tabSize = tabs;
					}
					
					try
					{
						ParseStatus status = ParseStatus.PARSED_SUCCESSFULLY;
						try
						{
							p.parseFile(this, sourceFileName, program);
						}
						catch (EagleSoftParseException ex)
						{
							ex.printStackTrace();
							status = ParseStatus.PARSE_FAILURE;	// But, keep going!
						}

						// Print the symbol table / cross reference
						// EagleUtilities.printCrossReference(System.out, program);
							
						// Now save it
						try
						{
							p._parser.saveXML(program, parsedFileName);
							System.out.println("Created " + parsedFileName);
							entry.status = status;
						}
						catch (Exception ex)
						{
							System.err.println("Failed trying to create XML file " + parsedFileName);
							ex.printStackTrace();
							entry.status = ParseStatus.PARSE_FAILURE;
						}
					}
					catch (Exception ex)
					{
						System.out.println();
						ex.printStackTrace();
						entry.status = ParseStatus.PARSE_FAILURE;
					}
				}
			}
		}
	}

	protected void showParseResults()
	{
		int alreadyParsed = 0;
		int parsedSuccessfully = 0;
		int parseFailures = 0;
		
		for (ProjectEntry file : getEntries())
		{
			if (!(file instanceof ProgramEntry)) continue;
			ProgramEntry entry = (ProgramEntry) file;

			switch (entry.status)
			{
			case ALREADY_PARSED :
				alreadyParsed++;
				break;
			
			case PARSED_SUCCESSFULLY :
				parsedSuccessfully++;
				break;

			case PARSE_FAILURE :
				parseFailures++;
				break;
			}
		}
		
		System.out.println();
		System.out.println("Already Parsed:      " + alreadyParsed);
		System.out.println("Parsed Successfully: " + parsedSuccessfully);
		System.out.println("Parse Failures:      " + parseFailures);

		int sum = alreadyParsed + parsedSuccessfully + parseFailures;
		if (sum > 0)
		{
			int pct = 100 * (alreadyParsed + parsedSuccessfully) / sum;
			System.out.println("Success Rate:        " + pct + "%");
		}
	}
	
	// Returns null if there is a problem, like it didn't parse
	public EagleLanguage loadProgramFromXML(ProgramEntry entry)
	{
		try
		{
			// Read whole file at once
			EagleReadXML reader = new EagleReadXML();
			String parsedFileName = EaglePath.combinePaths(artifactBase, entry.parsedFile);
			EagleLanguage pgm = reader.readFrom(parsedFileName);
			return pgm;
		}
		catch (Exception ex)
		{
			return null;
		}
	}

	public ProjectEntry findEntry(String name)
	{
		for (ProjectEntry entry : getEntries())
		{
			if (entry.sourceFile.equals(name)) return entry;
		}
		return null;
	}

	protected void parseAll(String[] args)
	{
		boolean force = false;
		int offset = 0;
		
		if (args.length > 0 && args[0].equals("-force"))
		{
			force = true;
			offset++;
		}
		
		if (args.length <= offset)
		{
			System.out.println("Usage: " + this.getName() + " [-force] baseDir");
		}
		
		String baseDir = args[offset + 0];
		if (!baseDir.endsWith("\\")) baseDir += '\\';
	
		this.parseFiles(force);
		this.showParseResults();
	}
	
	public void performRepairs(String fileName, EagleFileReader lines)
	{
		_repair.performRepairs(fileName, lines);
	}
	
	// Just a layer on RepairFile
	public void repair(String fileName, Integer lineNumber, String pattern, String replacement, String explanation)
	{
		_repair.repair(fileName, lineNumber, pattern, replacement, explanation);
	}
}
