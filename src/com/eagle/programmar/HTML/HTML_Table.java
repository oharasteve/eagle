// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 10, 2013

package com.eagle.programmar.HTML;

import com.eagle.programmar.Django.Django_Control;
import com.eagle.programmar.HTML.HTML_Program.HTML_Element;
import com.eagle.programmar.HTML.HTML_Table.HTML_TableRow.HTML_TableData;
import com.eagle.programmar.HTML.Terminals.HTML_Comment;
import com.eagle.programmar.HTML.Terminals.HTML_Keyword;
import com.eagle.programmar.HTML.Terminals.HTML_KeywordChoice;
import com.eagle.programmar.HTML.Terminals.HTML_Punctuation;
import com.eagle.programmar.HTML.Terminals.HTML_PunctuationChoice;
import com.eagle.programmar.PHP.PHP_Program.PHP_Section;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;

public class HTML_Table extends TokenSequence
{
	public @INDENT HTML_StartTable startTable;
	public TokenList<HTML_TableBodyElement> elements;
	public @OUTDENT HTML_EndTable endTable;
	
	public static class HTML_TableBodyElement extends TokenChooser
	{
		public @CHOICE PHP_Section php;
		public @CHOICE Django_Control control;
		public @CHOICE HTML_TablePiece piece;
	}

	public static class HTML_TablePiece extends TokenChooser
	{
		public @CHOICE HTML_Comment comment;
		public @CHOICE HTML_TableRow row;
		public @CHOICE HTML_Caption caption;
		public @CHOICE @CURIOUS("Missing table row") HTML_TableData data;
		
		public @CHOICE static class HTML_TableColGroup extends TokenSequence
		{
			public @INDENT HTML_Punctuation startTagColGroup = new HTML_Punctuation('<');
			public @NOSPACE HTML_Keyword COLGROUP1 = new HTML_Keyword("colgroup");
			public @OPT TokenList<HTML_Attribute> attributes; 
			public @NOSPACE HTML_Punctuation endTagColGroup = new HTML_Punctuation('>');
			
			public @OPT TokenList<HTML_TableCol> columns;

			public HTML_Punctuation startTag = new HTML_Punctuation("</");
			public @NOSPACE HTML_Keyword COLGROUP2 = new HTML_Keyword("colgroup");
			public @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
			
			public static class HTML_TableCol extends TokenSequence
			{
				public @INDENT HTML_Punctuation startTagSection = new HTML_Punctuation('<');
				public @NOSPACE HTML_Keyword COL = new HTML_Keyword("col");
				public @OPT TokenList<HTML_Attribute> attributes; 
				public @NOSPACE HTML_PunctuationChoice endTagCol = new HTML_PunctuationChoice(">", "/>");
			}
		}
		
		public @CHOICE static class HTML_TableSection extends TokenSequence
		{
			public @INDENT HTML_Punctuation startTagSection = new HTML_Punctuation('<');
			public @NOSPACE HTML_KeywordChoice tableType = new HTML_KeywordChoice("thead", "tbody", "tfoot");
			public @OPT TokenList<HTML_Attribute> attributes; 
			public @NOSPACE HTML_Punctuation endTagRow = new HTML_Punctuation('>');
			public @OPT HTML_TableSectionBody body;
			public @OPT @OUTDENT HTML_EndTableSection endSection;

			public static class HTML_TableSectionBody extends TokenChooser
			{
				public @CHOICE Django_Control control;
				
				public @CHOICE static class HTML_Table_NormalBody extends TokenSequence
				{
					public TokenList<HTML_TableRow> rows;
				}
			}
			
			public static class HTML_EndTableSection extends TokenSequence
			{
				public HTML_Punctuation startTag = new HTML_Punctuation("</");
				public @NOSPACE HTML_KeywordChoice tableType = new HTML_KeywordChoice("thead", "tbody", "tfoot");
				public @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
			}
		}
	}
	
	public static class HTML_TableRow extends TokenSequence
	{
		public @INDENT HTML_StartRow startRow;
		public @OPT TokenList<HTML_TableData> cells;
		public @OPT @OUTDENT HTML_EndRow endRow;
		
		public static class HTML_TableData extends TokenSequence
		{
			public @INDENT HTML_StartData startCell;
			public @OPT TokenList<HTML_Element> contents;
			public @OPT @OUTDENT HTML_EndData endData;
			
			public static class HTML_StartData extends TokenSequence
			{
				public HTML_Punctuation startTag = new HTML_Punctuation("<");
				public @NOSPACE HTML_KeywordChoice TD = new HTML_KeywordChoice("td", "th");
				public @OPT TokenList<HTML_Attribute> attributes; 
				public @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
			}
			
			public static class HTML_EndData extends TokenSequence
			{
				public HTML_Punctuation startTag = new HTML_Punctuation("</");
				public @NOSPACE HTML_KeywordChoice TD = new HTML_KeywordChoice("td", "th");
				public @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
			}
		}

		public static class HTML_StartRow extends TokenSequence
		{
			public HTML_Punctuation startTag = new HTML_Punctuation("<");
			public @NOSPACE HTML_Keyword TR = new HTML_Keyword("tr");
			public @OPT TokenList<HTML_Attribute> attributes; 
			public @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
		}

		public static class HTML_EndRow extends TokenSequence
		{
			public HTML_Punctuation startTag = new HTML_Punctuation("</");
			public @NOSPACE HTML_Keyword TR = new HTML_Keyword("tr");
			public @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
		}
	}
	
	public static class HTML_StartTable extends TokenSequence
	{
		public HTML_Punctuation startTag = new HTML_Punctuation("<");
		public @NOSPACE HTML_Keyword TABLE = new HTML_Keyword("table");
		public @OPT TokenList<HTML_Attribute> attributes; 
		public @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
	}
	
	public static class HTML_EndTable extends TokenSequence
	{
		public HTML_Punctuation startTag = new HTML_Punctuation("</");
		public @NOSPACE HTML_Keyword TABLE = new HTML_Keyword("table");
		public @NOSPACE HTML_Punctuation endTag = new HTML_Punctuation('>');
	}
}
