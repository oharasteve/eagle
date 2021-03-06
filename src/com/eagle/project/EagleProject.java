// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 13, 2010

package com.eagle.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.eagle.io.EagleReadXML;
import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleOverrideManager;
import com.eagle.parsers.EagleParseException.EagleSoftParseException;
import com.eagle.parsers.EagleParser;
import com.eagle.parsers.ParserManager;
import com.eagle.programmar.EagleLanguage;
import com.eagle.programmar.EagleLanguageLookup;
import com.eagle.softwarehouse.FileInventory;
import com.eagle.utils.EaglePath;

@RunWith(Parameterized.class)

public abstract class EagleProject
{
	public static final String SAMPLES = "Samples";
	public static final String VIEWER = "Viewer";
	public static final String SYMBOLS = "Symbols";
	public static final String PARSED = "Parsed";
	public static final String TOKENS = "Tokens";

	private static final String HDR = "Top Dir,Directory,File Name,Suffix,Bytes,Date,Type,Lines,Parsed,Tokens,";
	private static final int HDR_DIR = 1;
	private static final int HDR_FILE = 2;
	private static final int HDR_PARSED = 8;
	private static final String HDR_NO = "No";
	private HashSet<String> _previousFailures = null;
	
	public String _sourceBase;		    // Including the C: but no trailing backslash
	public String _artifactBase;		// Including the C: but no trailing backslash

	protected EagleLanguageLookup _languageLookup = new EagleLanguageLookup();
	protected ParserManager _parserManager = new ParserManager();
	protected RepairFile _repair = new RepairFile();

	/**
	 * Does this language, for this project, have macro processing?
	 * @param lang  
	 */
	public boolean hasMacros(EagleLanguage lang)
	{
		return false;	// Assume no.
	}
	
	/**
	 * Expand all macros unless told otherwise
	 * @param macroName  
	 */
	public boolean expandMacro(String macroName)
	{
		return true;
	}

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
	
	protected void findSourceFiles()
	{
		addEntries("");
	}
	
	public ArrayList<ProjectEntry> getEntries()
	{
		if (!_entriesLoaded)
		{
			_entriesLoaded = true;
			findSourceFiles();
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
	protected boolean acceptEntry(String dir, String fname)
	{
		return true;
	}
	
	// Careful, recursive
	protected void addEntries(String sourceDir)
	{
		String dirName = EaglePath.combinePaths(_sourceBase, sourceDir);
		if (dirName == null) return;
		File dir = new File(dirName);
		
		if (!dir.exists()) throw new RuntimeException(dirName + " does not exist.");
		if (!dir.isDirectory()) throw new RuntimeException(dirName + " exists, but is not a directory.");
		
		File[] files = dir.listFiles();
		
		// System.out.println("Processing " + sourceDir + "  files=" + files.length);
		
		for (File file : files)
		{
			if (! file.isDirectory())
			{
				String fname = file.getName();
				String languageName = _languageLookup.lookupSuffix(fname);
				if (languageName != null && file.length() > 0)
				{
					// Might want to exclude some files
					if (! acceptEntry(sourceDir, fname)) continue;
					addSourceEntry(sourceDir, fname, languageName);
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
		
		_entriesLoaded = true;
	}
	
	// Called for javap file when the .class file exists, but the .javap file does not exist
	protected void addSourceEntry(String sourceDir, String fname, String languageName)
	{
		ProgramEntry entry = new ProgramEntry();
		entry.languageName = languageName;

		String tempName = EaglePath.combinePaths(sourceDir, fname);
		entry.sourceFile = tempName;
		int dot = tempName.lastIndexOf('.');
		if (dot > 0)
		{
			tempName = tempName.substring(0, dot) + '_' + tempName.substring(dot + 1);
		}
		
		entry.parsedFile = EaglePath.combinePaths(PARSED, tempName + ".xml");
		entry.parseFailedFile = EaglePath.combinePaths(PARSED, tempName + "_failed.html");

		entry.programView = EaglePath.combinePaths(VIEWER, tempName + ".html"); 
		entry.symbolTable = EaglePath.combinePaths(SYMBOLS, tempName + ".html");

		_entries.add(entry);
	}

	protected void parseFiles(boolean force)
	{
		for (ProjectEntry file : getEntries())
		{
			if (!(file instanceof ProgramEntry)) continue;
			ProgramEntry entry = (ProgramEntry) file;
				
			boolean needsParsing = true;
			
			String parsedFileName = EaglePath.combinePaths(_artifactBase, entry.parsedFile);
			String sourceFileName = EaglePath.combinePaths(_sourceBase, entry.sourceFile);
			
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
					EaglePath.createDirForFile(parsedFileName);
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
					int tabs = getTabSize();
					if (tabs > 0)
					{
						_parserManager._convertTabs = true;
						_parserManager._tabSize = tabs;
					}
					
					try
					{
						ParseStatus status = ParseStatus.PARSED_SUCCESSFULLY;
						try
						{
							_parserManager.parseFile(this, entry.sourceFile, entry.sourceFile, program);
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
							_parserManager._parser.saveXML(program, parsedFileName);
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
			String parsedFileName = EaglePath.combinePaths(_artifactBase, entry.parsedFile);
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
		// In case the full Name is given ....
		String shortName = name;
		if (name.startsWith(_sourceBase))
		{
			shortName = name.substring(_sourceBase.length());
			if (shortName.startsWith("/")) shortName = shortName.substring(1);
		}
		
		for (ProjectEntry entry : getEntries())
		{
			if (entry.sourceFile.equals(shortName)) return entry;
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
	
		parseFiles(force);
		showParseResults();
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
	
	// For handling FindIncludeFile interface
	protected EagleFileReader findFileFixedLocation(String dir, String fname) throws IOException
	{
		String includeFile = EaglePath.combinePaths(_sourceBase, dir, fname);
		BufferedReader br = new BufferedReader(new FileReader(includeFile));
		ArrayList<String> contents = new ArrayList<String>();
		String rec;
		
		while ((rec = br.readLine()) != null)
		{
			contents.add(rec);
		}
		br.close();

		String[] body = contents.toArray(new String[contents.size()]);
		EagleFileReader lines = new EagleFileReader(body);
		lines.setFileName(EaglePath.combinePaths(dir, fname));
		return lines;
	}

	// Called from RemoveTemporaryFiles.java, at start of TestDriver.java
	public void removeFiles()
	{
		if (_artifactBase == null) return;
		
		String parsedDir = EaglePath.combinePaths(_artifactBase, EagleProject.PARSED);
		File pdir = new File(parsedDir);
		if (pdir.exists() && EaglePath.deleteDir(pdir))
		{
			System.out.println("Deleted directory " + parsedDir);
		}
		
		String symbolsDir = EaglePath.combinePaths(_artifactBase, EagleProject.SYMBOLS);
		File sdir = new File(symbolsDir);
		if (sdir.exists() && EaglePath.deleteDir(sdir))
		{
			System.out.println("Deleted directory " + symbolsDir);
		}
		
		String viewerDir = EaglePath.combinePaths(_artifactBase, EagleProject.VIEWER);
		File vdir = new File(viewerDir);
		if (vdir.exists() && EaglePath.deleteDir(vdir))
		{
			System.out.println("Deleted directory " + viewerDir);
		}
		
		String tokenDir = EaglePath.combinePaths(_artifactBase, EagleProject.TOKENS);
		File tdir = new File(tokenDir);
		if (tdir.exists() && EaglePath.deleteDir(tdir))
		{
			System.out.println("Deleted directory " + tokenDir);
		}
	}
	
//	public EagleSymbolTable loadSymbolTable(ProgramEntry pgm)
//	{
//		EagleSymbolTable symbolTable = new EagleSymbolTable();
//		return symbolTable;
//	}
	
	protected boolean doTransformation(@SuppressWarnings("unused") ProgramEntry entry)
	{
		return true;
	}

	protected void setupTransformation()
	{
		// Don't wait to add the entries into the Project
		addEntries("");
		
		// Set up Transformation files
		for (ProjectEntry item : getEntries())
		{
			if (item instanceof ProgramEntry)
			{
				ProgramEntry entry = (ProgramEntry) item;
				if (doTransformation(entry))
				{
					setupTransformation(entry);
				}
			}
		}
	}

	private void setupTransformation(ProgramEntry entry)
	{
		String fname = entry.sourceFile;
		int slash = fname.lastIndexOf('/');
		if (slash > 0) fname = fname.substring(slash+1);
		int lastDot = fname.lastIndexOf('.');
		String piece = fname.substring(0, lastDot);
	
		
		entry.javaFile = EaglePath.combinePaths("Transformed", piece + ".java");
		entry.javaClassDir = EaglePath.combinePaths("Transformed", "classes");
		entry.javaHtmlReport = EaglePath.combinePaths("Reports", piece + "_java.html");
		
		entry.csFile = EaglePath.combinePaths("Transformed", piece + ".cs");
		entry.csExeFile = EaglePath.combinePaths("Transformed", "bin", piece + ".exe");
		entry.csHtmlReport = EaglePath.combinePaths("Reports", piece + "_cs.html");
	
		entry.inputFile = EaglePath.combinePaths("Output", piece + ".in");
		entry.outputFile = EaglePath.combinePaths("Output", piece + ".out");
	
		checkDir(EaglePath.combinePaths("Transformed", "classes"));
		checkDir(EaglePath.combinePaths("Transformed", "bin"));
		checkDir("Reports");
		checkDir("Output");
		
		String os = System.getenv("OS");
		boolean isDos = (os != null && os.equals("Windows_NT"));
		entry.actualOutput = (isDos ? "C:\\temp\\" : "/private/tmp/") + piece + ".out";
	}
	
	private void checkDir(String dirName)
	{
		String path = EaglePath.combinePaths(_artifactBase, dirName);
		if (EaglePath.createDir(path))
		{
			System.out.println("Created directory " + path);
		}
	}
	
	// Did it fail parsing the last time we did a full test run?
	// Can't just look at the existing of the html parse fail file because
	// it gets deleted at the start of the nightly run. Have to look at
	// the FileInventory.csv file instead.
	private void collectPreviousFailures()
	{
		_previousFailures = new HashSet<String>();
				
		String csv = EaglePath.combinePaths(_artifactBase, FileInventory.FILEINVENTORY_CSV);
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(csv));
			String rec = br.readLine();
			if (! rec.startsWith(HDR))
			{
				br.close();
				throw new RuntimeException("Expected " + HDR + " not " + rec);
			}
			
			while ((rec = br.readLine()) != null)
			{
				String[] pieces = rec.split(",");
				if (pieces.length >= HDR_PARSED && pieces[HDR_PARSED].equalsIgnoreCase(HDR_NO))
				{
					String dir = pieces[HDR_DIR];
					String file = pieces[HDR_FILE];
					String fullFilename;
					if (dir.length() > 0)
					{
						fullFilename = dir + '/' + file;
					}
					else
					{
						fullFilename = file;
					}
					_previousFailures.add(fullFilename);
					//System.out.println("*** Previous Parse failure: " + fullFilename);
				}
			}
			br.close();
		}
		catch (Exception ex)
		{
			System.err.println("Problem reading " + csv);
			ex.printStackTrace(System.err);
		}
	}
	
	public boolean previouslyFailedParsing(ProgramEntry entry)
	{
		if (_previousFailures == null) collectPreviousFailures();
		return _previousFailures.contains(entry.sourceFile);
	}
}
