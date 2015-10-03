// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Jan 4, 2011

package com.eagle.programmar.Natural;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.eagle.programmar.Natural.Symbols.Natural_Identifier_Reference;
import com.eagle.programmar.Natural.Terminals.Natural_Float;
import com.eagle.programmar.Natural.Terminals.Natural_Keyword;
import com.eagle.programmar.Natural.Terminals.Natural_KeywordChoice;
import com.eagle.programmar.Natural.Terminals.Natural_Level;
import com.eagle.programmar.Natural.Terminals.Natural_Number;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class Natural_DDM extends TokenSequence
{
	public Natural_Identifier_Reference name;
	public TokenList<Natural_DDM_Line> lines;
	
	public static class Natural_DDM_Line extends TokenSequence
	{
		public @OPT Natural_KeywordChoice Ty = new Natural_KeywordChoice(
				"GR", "MU", "PE", "SB", "SP");
		public Natural_Level Level;
		public Natural_Identifier_Reference FieldName;
		public @OPT Natural_KeywordChoice F = new Natural_KeywordChoice(
				"A", "B", "P", "U");
		public @OPT Natural_Float Length;
		public @OPT Natural_Number Occurs;
		public @OPT Natural_Keyword D = new Natural_Keyword("D");
		public @OPT Natural_Keyword U = new Natural_Keyword("U");
		public Natural_Identifier_Reference DB;
		public @OPT Natural_KeywordChoice S = new Natural_KeywordChoice(
				"F", "N");
		
		
		// These are special -- context-sensitive, must have smaller Level numbers
		public @OPT TokenList<Natural_DDM_Line> children;
	}
	
	/*                          123456789.123456789.123456789.123456789.123456789.123456789.1234 */
	private final String HDR = "Ty L Field name                       F  Length   Occ   D U DB S";

	public void customReader(String fileName)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String rec = br.readLine();
			if (!rec.startsWith(HDR))
			{
				br.close();
				throw new RuntimeException("Invalid DDM file: " + fileName);
			}
			while ((rec = br.readLine()) != null)
			{
				if (rec.trim().length() == 0) continue;
				String level = rec.substring(3, 4);
				if (level.equals("-")) continue;	// Skip header and footer markers

				Natural_DDM_Line line = new Natural_DDM_Line();		// @SKIP -- this instance is ok
				
				String ty = rec.substring(0, 2).trim().toUpperCase();
				if (ty.length() > 0) line.Ty.setValue(ty);
				
				line.Level.setValue(level);
				
				line.FieldName.setValue(rec.substring(6, 38).trim());
				
				String f = rec.substring(39, 40).trim().toUpperCase();
				if (f.length() > 0) line.F.setValue(f);
				
				String len = rec.substring(42, 50).trim();
				if (len.length() > 0) line.Length.setValue(len);
				
				String occ = rec.substring(51, 56).trim();
				if (occ.length() > 0) line.Occurs.setValue(occ);
				
				String d = rec.substring(57, 58).trim().toUpperCase();
				if (d.length() > 0) line.D.setValue(d);
				
				String u = rec.substring(59, 60).trim().toUpperCase();
				if (u.length() > 0) line.U.setValue(u);
				
				String db = rec.substring(61, 63).trim().toUpperCase();
				line.DB.setValue(db);
				
				String s = rec.substring(64, 65).trim().toUpperCase();
				if (s.length() > 0) line.D.setValue(s);
				
				lines.addToken(line);
			}
			br.close();
		}
		catch (IOException ex)
		{
			throw new RuntimeException("Error reading " + fileName, ex);
		}
	}
}
