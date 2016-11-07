// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2011

package com.eagle.softwarehouse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.eagle.io.EagleReadXML;
import com.eagle.io.EagleReadXML.XMLToken;
import com.eagle.programmar.EagleLanguage;
import com.eagle.project.EagleProject;
import com.eagle.project.ProgramEntry;
import com.eagle.project.ProjectEntry;
import com.eagle.utils.EaglePath;
import com.eagle.utils.EagleUtilities;

public class FileInventory
{
	public static final String SOURCELOCATION_TXT = "SourceLocation.txt";
	public static final String FILEINVENTORY_CSV = "FileInventory.csv";
	
	private TreeMap<String, PrintWriter> _foundTypes = new TreeMap<String, PrintWriter>();
	
	private String _preFail;
	private String _postFail;
	
	public void createCsv(EagleProject proj) throws IOException
	{
		// Create output directories
		String outDirName = proj._artifactBase;
		if (outDirName == null) return;
		EaglePath.createDir(outDirName);
		String outTokenDirName = EaglePath.combinePaths(outDirName, EagleProject.TOKENS);
		EaglePath.createDir(outTokenDirName);

		String sourceLocation = EaglePath.combinePaths(outDirName, SOURCELOCATION_TXT);
		PrintWriter prtSource = new PrintWriter(new FileWriter(sourceLocation));
		prtSource.println(proj._sourceBase);
		prtSource.close();

		String outFileCsv = EaglePath.combinePaths(outDirName, FILEINVENTORY_CSV);
		PrintWriter outFile = new PrintWriter(new FileWriter(outFileCsv));
		outFile.println("Top Dir,Directory,File Name,Suffix,Bytes,Date,Type,Lines,Parsed,Tokens,Elapsed,Steps,PreFail,PostFail");
		int fileLines = 0;
		
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		
		// Make a nice little format string
		int count = proj.getEntries().size();
		System.out.println();
		PercentComplete percent = new PercentComplete(System.out, "File Inventory progress", count);
		
		for (ProjectEntry entry : proj.getEntries())
		{
			String top = "";
			String dir = "";
			String fname = entry.sourceFile;
			int pos = fname.lastIndexOf('/');
			if (pos >= 0)
			{
				int pos2 = fname.indexOf('/');
				top = fname.substring(0, pos2);

				dir = fname.substring(0, pos);
				fname = fname.substring(pos + 1);
			}
			
			String suffix = "";
			pos = fname.lastIndexOf('.');
			if (pos > 0) suffix = fname.substring(pos + 1);
			outFile.print(top + "," +
					dir + "," +
					fname + "," +
					suffix + ",");
			
			File file = new File(EaglePath.combinePaths(proj._sourceBase, entry.sourceFile));
			if (file.exists())
			{
				long size = file.length();
				Date modified = new Date(file.lastModified());
				String dateTime = df.format(modified);
				outFile.print(size + "," + dateTime + ",");
			}
			else
			{
				outFile.println(",,");
			}
			
			if (entry instanceof ProgramEntry)
			{
				ProgramEntry program = (ProgramEntry) entry;
				outFile.print(program.languageName + ",");

				int lines = 0;
				try
				{
					BufferedReader br = new BufferedReader(new FileReader(file));
					while (br.readLine() != null) lines++;
					br.close();
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
				outFile.print(lines + ",");

				String xmlName = EaglePath.combinePaths(proj._artifactBase, program.parsedFile);
				EagleReadXML xml = new EagleReadXML();
				ArrayList<EagleReadXML.XMLToken> xmlTokens = new ArrayList<EagleReadXML.XMLToken>();
				EagleLanguage prog = null;
				try
				{
					prog = xml.collectStats(xmlName, xmlTokens);
				}
				catch (Exception ex)
				{
					prog = null;
				}
				if (prog != null)
				{
					outFile.print("Yes" + "," + xml._tokens + "," + xml._elapsed + "," + xml._steps + ",,");
					updateTokens(outTokenDirName, program.sourceFile, xmlTokens);
				}
				else
				{
					// Parse must have failed
					String htmlName = EaglePath.combinePaths(proj._artifactBase, program.parseFailedFile);
					findFailureFromHtml(htmlName);
					// Replace all single \ with double \\
					outFile.print("No" + ",,,," + _preFail.replaceAll("\\\\", "\\\\\\\\") + "," +
														_postFail.replaceAll("\\\\", "\\\\\\\\"));
				}
			}
			else
			{
				outFile.print(",,,,,,,,");
			}
			
			outFile.println();
			fileLines++;
			
			// More pretty printing of status line
			percent.update(fileLines);
		}
		
		for (Entry<String, PrintWriter> entry : _foundTypes.entrySet())
		{
			PrintWriter pw = entry.getValue();
			pw.close();
		}
		
		System.out.println();
		System.out.println("Finished writing to " + outFileCsv + " (lines=" + fileLines + ")");
		System.out.println("                and " + sourceLocation + " (one line)");
		outFile.close();
	}
	
	private void findFailureFromHtml(String htmlFilename) throws IOException
	{
		_preFail = "";
		_postFail = "";
		BufferedReader br = null;
		try
		{
			br = new BufferedReader(new FileReader(htmlFilename));
		}
		catch (IOException ex)
		{
			//System.err.println("Unable to read " + htmlFilename);
			return;
		}

		// Ignore errors reading the html parse failure file
		try
		{
			String rec, prevRec;
			prevRec = "";
			while ((rec = br.readLine()) != null)
			{
				if (rec.trim().equals("^"))
				{
					// Found the marker, it points to prevRec;
					int hatPos = rec.indexOf('^');
					
					// Change &amp; to & etc.
					String fixed = EagleUtilities.undoHtml(prevRec);
					
					int sc = hatPos - 10;
					int colonPos = fixed.indexOf(':');
					if (sc <= colonPos) sc = colonPos + 1;
					_preFail = EagleUtilities.fixAscii(fixed.substring(sc, hatPos));
					
					int ec = hatPos + 10;
					int nc = fixed.length();
					if (ec > nc) ec = nc;
					_postFail = EagleUtilities.fixAscii(fixed.substring(hatPos, ec));
					
					break;
				}
				prevRec = rec;
			}
			
			br.close();
		}
		catch (Exception ex)
		{
			return;
		}
	}
	
	private void updateTokens(String outTokenDirName, String fname, ArrayList<XMLToken> xmlTokens) throws IOException
	{
		for (XMLToken token : xmlTokens)
		{
			String type = token._type;
			PrintWriter pw;
			if (_foundTypes.containsKey(type))
			{
				pw = _foundTypes.get(type);
			}
			else
			{
				String fixedName = token._type.replaceAll("\\.", "/").replaceAll("\\$", "/");
				String outName = EaglePath.combinePaths(outTokenDirName, fixedName + ".csv");
				EaglePath.createDirForFile(outName);

				pw = new PrintWriter(new FileWriter(outName));
				pw.println("File,Name,SL,SC,EL,EC,Value");
				_foundTypes.put(type, pw);
			}
			
			pw.println(fname + ',' + token._name + ',' +
					token._sl + ',' + token._sc + ',' +
					token._el + ',' + token._ec + ',' +
					token._value);
		}
	}

//	public static void main(String[] args)
//	{
//		if (args.length < 1)
//		{
//			System.out.println("Usage: FileInventory <projName>");
//			System.exit(0);
//		}
//		String projName = args[0];
//		
//		try
//		{
//			EagleProject proj = EagleProjectLookup.lookupProject(projName);
//			FileInventory fi = new FileInventory();
//			fi.createCsv(proj);
//		}
//		catch (Exception ex)
//		{
//			ex.printStackTrace();
//			System.exit(1);
//		}
//		
//		System.exit(0);
//	}
}
