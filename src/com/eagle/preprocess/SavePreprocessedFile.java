// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 3, 2015

package com.eagle.preprocess;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleLineReader;
import com.eagle.utils.EaglePath;
import com.eagle.utils.EagleUtilities;

public class SavePreprocessedFile
{
	public static void writeText(String fileName, EagleFileReader lines)
	{
		System.out.println("===================================================");
		System.out.println("================ Pre-processed version of " + fileName);
		String prevFile = null;
		for (int i = 0; i < lines.size(); i++)
		{
			EagleLineReader line = lines.get(i);
			String seq = EagleUtilities.rj(i+1, 6);
			String fName = line.getOriginalFileName();
			int lineNum = line.getOriginalLineNumber();
			String origFile;
			String origLine;
			if (fName == null)
			{
				origFile = prevFile;
				origLine = "&nbsp;&nbsp;&quot;&nbsp;&nbsp;&nbsp;";
			}
			else
			{
				origFile = fName;
				origLine = EagleUtilities.rj(lineNum, 6);
			}
			System.out.println(seq + " " + EagleUtilities.lj(origFile,30) + " " + origLine + " : " + line);
			
			prevFile = origFile;
		}
		System.out.println();
	}

	public void saveHtml(String prefix, String fileName, EagleFileReader lines) throws IOException
	{
		int lastDot = fileName.lastIndexOf('.');
		String newName = fileName;
		if (lastDot > 0)
		{
			newName = fileName.substring(0, lastDot) + "_" + fileName.substring(lastDot + 1) + ".html";
		}
		
		String outName = EaglePath.combinePaths(prefix, "preprocessed", newName);
		
		// Make sure the directory exists
		EaglePath.createDirForFile(outName);
		
		PrintWriter prt = new PrintWriter(new FileWriter(outName));
		prt.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
		prt.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
		prt.println("<head>");
		prt.println("<title>Preprocessed version of " + fileName + "</title>");
		prt.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"/>");
		prt.println("<style type=\"text/css\">");
		prt.println(" td.lf { text-align: left; font-family: courier; font-size: 12px; }");
		prt.println(" td.cn { text-align: center; font-family: courier; font-size: 12px; }");
		prt.println(" td.txt { text-align: left; font-size: 12px; }");
		prt.println(" td.dim { color: lightgray; }");
		prt.println(" pre { margin: 0; }");
		prt.println("</style>");
		prt.println("</head>");
		prt.println("<body>");
		prt.println("<center><h1>Preprocessed version of " + fileName + "</h1></center>");
		
		prt.println("<center><table border>");
		prt.println("  <thead><td class=cn>Seq" +
				"<td class=lf>File Name" +
				"<td class=cn>Line" +
				"<td class=txt><pre>Text</pre>" +
				"</thead>");

		String prevName = "?";
		int prevLine = 0;
		for (int i = 0; i < lines.size(); i++)
		{
			EagleLineReader line = lines.get(i);
			String origName = line.getOriginalFileName();
			int origLine = line.getOriginalLineNumber();
			
			String lineStyle = "cn";
			if (origName == null)
			{
				// Normally means macro spanned multiple lines
				origName = prevName;
				origLine = prevLine;
				lineStyle = "cn dim";
			}

			String nameStyle = (prevName.equals(origName) ? "lf dim" : "lf");
			prt.println("  <tr><td class=cn>" + (i+1) +
					"<td class=\"" + nameStyle + "\">" + origName +
					"<td class=\"" + lineStyle + "\">" + origLine +
					"<td class=\"txt\"><pre>" + line + "</pre>" +
					"</tr>");
			
			prevName = origName;
			prevLine = origLine;
		}

		prt.println("</table></center>");
		prt.println("</body>");
		prt.println("</html>");
		prt.close();
		System.out.println("Finished writing to " + newName);
	}
}
