// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 9, 2010

package com.eagle.programmar.COBOL;

import com.eagle.programmar.COBOL.Symbols.COBOL_Identifier_Reference;
import com.eagle.programmar.COBOL.Symbols.COBOL_ReportLine_Definition;
import com.eagle.programmar.COBOL.Symbols.COBOL_Report_Definition;
import com.eagle.programmar.COBOL.Terminals.COBOL_Keyword;
import com.eagle.programmar.COBOL.Terminals.COBOL_KeywordChoice;
import com.eagle.programmar.COBOL.Terminals.COBOL_Level;
import com.eagle.programmar.COBOL.Terminals.COBOL_Literal;
import com.eagle.programmar.COBOL.Terminals.COBOL_Number;
import com.eagle.programmar.COBOL.Terminals.COBOL_Picture;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationPeriod;

public class COBOL_ReportEntry extends TokenSequence
{
	public COBOL_ReportDescription rd;
	public TokenList<COBOL_ReportDataLine> dataLines;
	
	public static class COBOL_ReportDescription extends TokenSequence
	{
		public COBOL_Keyword RD = new COBOL_Keyword("RD");
		public COBOL_Report_Definition reportName;
		public TokenList<COBOL_ReportDescriptionEntry> descriptionEntries;
		public PunctuationPeriod dot;
		
		public static class COBOL_ReportDescriptionEntry extends TokenChooser
		{
			public static class COBOL_ReportDescriptionControls extends TokenSequence
			{
				public COBOL_Keyword CONTROLS = new COBOL_Keyword("CONTROLS");
				public COBOL_Keyword ARE = new COBOL_Keyword("ARE");
				public @OPT COBOL_Keyword FINAL = new COBOL_Keyword("FINAL");
				public TokenList<COBOL_Identifier_Reference> ids;
			}

			public static class COBOL_ReportDescriptionPageLimit extends TokenSequence
			{
				public COBOL_Keyword PAGE = new COBOL_Keyword("PAGE");
				public COBOL_Keyword LIMIT = new COBOL_Keyword("LIMIT");
				public COBOL_Keyword IS = new COBOL_Keyword("IS");
				public COBOL_Number count;
			}
			public static class COBOL_ReportDescriptionHeading extends TokenSequence
			{
				public COBOL_Keyword HEADING = new COBOL_Keyword("HEADING");
				public COBOL_Number count;
			}
			public static class COBOL_ReportDescriptionFirstDetail extends TokenSequence
			{
				public COBOL_Keyword FIRST = new COBOL_Keyword("FIRST");
				public COBOL_Keyword DETAIL = new COBOL_Keyword("DETAIL");
				public COBOL_Number count;
			}
			public static class COBOL_ReportDescriptionLastDetail extends TokenSequence
			{
				public COBOL_Keyword LAST = new COBOL_Keyword("LAST");
				public COBOL_Keyword DETAIL = new COBOL_Keyword("DETAIL");
				public COBOL_Number count;
			}
			public static class COBOL_ReportDescriptionFooting extends TokenSequence
			{
				public COBOL_Keyword FOOTING = new COBOL_Keyword("FOOTING");
				public COBOL_Number count;
			}
		}
	}
	
	public static class COBOL_ReportDataLine extends TokenSequence
	{
		public COBOL_Level O1;
		public @OPT COBOL_ReportLine_Definition id;
		public COBOL_Keyword TYPE = new COBOL_Keyword("TYPE");
		public COBOL_Keyword IS = new COBOL_Keyword("IS");
		public COBOL_ReportDataLineType lineType;
		public PunctuationPeriod dot;
		
		public TokenList<COBOL_ReportLine> reportLines;
		
 		public static class COBOL_ReportDataLineType extends TokenChooser
		{
			public static class COBOL_ReportDataLineTypeReportHeading extends TokenSequence
			{
				public COBOL_Keyword REPORT = new COBOL_Keyword("REPORT");
				public COBOL_Keyword HEADING = new COBOL_Keyword("HEADING");
			}
			
			public static class COBOL_ReportDataLineTypeReportFooting extends TokenSequence
			{
				public COBOL_Keyword REPORT = new COBOL_Keyword("REPORT");
				public COBOL_Keyword FOOTING = new COBOL_Keyword("FOOTING");
			}

			public static class COBOL_ReportDataLineTypePageHeading extends TokenSequence
			{
				public COBOL_Keyword PAGE = new COBOL_Keyword("PAGE");
				public COBOL_Keyword HEADING = new COBOL_Keyword("HEADING");
			}
			
			public static class COBOL_ReportDataLineTypeDetail extends TokenSequence
			{
				public COBOL_Keyword DETAIL = new COBOL_Keyword("DETAIL");
			}

			public static class COBOL_ReportDataLineTypePageFooting extends TokenSequence
			{
				public COBOL_Keyword PAGE = new COBOL_Keyword("PAGE");
				public COBOL_Keyword FOOTING = new COBOL_Keyword("FOOTING");
			}

			public static class COBOL_ReportDataLineTypeControlFooting extends TokenSequence
			{
				public COBOL_Keyword CONTROL = new COBOL_Keyword("CONTROL");
				public COBOL_Keyword FOOTING = new COBOL_Keyword("FOOTING");
				public @OPT COBOL_Keyword FINAL = new COBOL_Keyword("FINAL");
				public @OPT COBOL_Identifier_Reference id;
				public @OPT COBOL_Keyword NEXT = new COBOL_Keyword("NEXT");
				public @OPT COBOL_Keyword GROUP = new COBOL_Keyword("GROUP");
				public @OPT COBOL_Keyword PLUS = new COBOL_Keyword("PLUS");
				public @OPT COBOL_Number count;
			}
		}
 		
 		public static class COBOL_ReportLine extends TokenSequence
 		{
 			public COBOL_Level O2;
 			public COBOL_Keyword LINE = new COBOL_Keyword("LINE");
 			public @OPT COBOL_Keyword IS = new COBOL_Keyword("IS");
 			public @OPT COBOL_Keyword PLUS = new COBOL_Keyword("PLUS");
			public @OPT COBOL_Number line;
 			public PunctuationPeriod dot;
 			
 			public TokenList<COBOL_ColumnLine> columnLines;
 			
 	 		public static class COBOL_ColumnLine extends TokenSequence
 	 		{
 	 			public COBOL_Level O3;
				public @OPT COBOL_ReportLine_Definition id;
 	 			public COBOL_Keyword COLUMN = new COBOL_Keyword("COLUMN");
 				public @OPT COBOL_Number column;

 				public COBOL_KeywordChoice pic = new COBOL_KeywordChoice("PIC", "PICTURE");
 				public COBOL_Picture picture;
 				public TokenList<COBOL_ColumnLineClause> columnClauses;
 				public PunctuationPeriod dot;
 	 			
 	 			public static class COBOL_ColumnLineClause extends TokenChooser
 	 			{
 	 				public static class COBOL_ColumnLineSource extends TokenSequence
 	 				{
	 	 				public COBOL_Keyword SOURCE = new COBOL_Keyword("SOURCE");
	 					public COBOL_Identifier_Reference id;
	 					public @OPT COBOL_Subscript subscript;
 	 				}

 	 				public static class COBOL_SourceClause extends TokenSequence
 	 				{
 	 					public COBOL_Keyword SOURCE = new COBOL_Keyword("SOURCE");
 	 					public COBOL_Keyword PAGECOUNTER = new COBOL_Keyword("PAGE-COUNTER");
 	 				}

 	 				public static class COBOL_ColumnLineGroup extends TokenSequence
 	 				{
	 	 				public COBOL_Keyword GROUP = new COBOL_Keyword("GROUP");
	 	 				public COBOL_Keyword INDICATE = new COBOL_Keyword("INDICATE");
 	 				}
 	 				
 	 				public static class COBOL_ColumnLineValue extends TokenSequence
 	 				{
	 	 				public COBOL_Keyword VALUE = new COBOL_Keyword("VALUE");
	 	 				public @OPT COBOL_Keyword ALL = new COBOL_Keyword("ALL");
	 	 				public COBOL_Literal val;
 	 				}
 	 				
 	 				public static class COBOL_ColumnLineSum extends TokenSequence
 	 				{
	 	 				public COBOL_Keyword SUM = new COBOL_Keyword("SUM");
	 					public COBOL_Identifier_Reference id;
 	 				}
 	 			}
 	 		}
 		}
	}
}
