// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Sep 25, 2011

package com.eagle.programmar.RPG.Specifications;

import com.eagle.programmar.RPG.Specifications.RPG_O_Output_Specification.RPG_O_Output_External_Field_Descr.RPG_O_Output_External_Field_Descr_III;
import com.eagle.programmar.RPG.Specifications.RPG_O_Output_Specification.RPG_O_Output_External_Field_Descr.RPG_O_Output_External_Field_Descr_IV;
import com.eagle.programmar.RPG.Specifications.RPG_O_Output_Specification.RPG_O_Output_External_Record_Id.RPG_O_Output_External_Record_Id_III;
import com.eagle.programmar.RPG.Specifications.RPG_O_Output_Specification.RPG_O_Output_External_Record_Id.RPG_O_Output_External_Record_Id_IV;
import com.eagle.programmar.RPG.Specifications.RPG_O_Output_Specification.RPG_O_Output_External_Record_Id.RPG_O_Output_External_Record_Id_Piece1.RPG_O_Output_External_Record_Id_Piece1_III;
import com.eagle.programmar.RPG.Specifications.RPG_O_Output_Specification.RPG_O_Output_External_Record_Id.RPG_O_Output_External_Record_Id_Piece1.RPG_O_Output_External_Record_Id_Piece1_IV;
import com.eagle.programmar.RPG.Specifications.RPG_O_Output_Specification.RPG_O_Output_External_Record_Id.RPG_O_Output_External_Record_Id_Piece2.RPG_O_Output_External_Record_Id_Piece2_III;
import com.eagle.programmar.RPG.Specifications.RPG_O_Output_Specification.RPG_O_Output_External_Record_Id.RPG_O_Output_External_Record_Id_Piece2.RPG_O_Output_External_Record_Id_Piece2_IV;
import com.eagle.programmar.RPG.Specifications.RPG_O_Output_Specification.RPG_O_Output_External_Record_Id.RPG_O_Output_External_Record_Id_Piece3.RPG_O_Output_External_Record_Id_Piece3_III;
import com.eagle.programmar.RPG.Specifications.RPG_O_Output_Specification.RPG_O_Output_External_Record_Id.RPG_O_Output_External_Record_Id_Piece3.RPG_O_Output_External_Record_Id_Piece3_IV;
import com.eagle.programmar.RPG.Specifications.RPG_O_Output_Specification.RPG_O_Output_Program_Field_Descr.RPG_O_Output_Program_Field_Descr_III;
import com.eagle.programmar.RPG.Specifications.RPG_O_Output_Specification.RPG_O_Output_Program_Field_Descr.RPG_O_Output_Program_Field_Descr_IV;
import com.eagle.programmar.RPG.Specifications.RPG_O_Output_Specification.RPG_O_Output_Program_Record_Id.RPG_O_Output_Program_Record_Id_III;
import com.eagle.programmar.RPG.Specifications.RPG_O_Output_Specification.RPG_O_Output_Program_Record_Id.RPG_O_Output_Program_Record_Id_IV;
import com.eagle.programmar.RPG.Specifications.RPG_O_Output_Specification.RPG_O_Output_Program_Record_Id.RPG_O_Output_Program_Record_Id_Piece1.RPG_O_Output_Program_Record_Id_Piece1_III;
import com.eagle.programmar.RPG.Specifications.RPG_O_Output_Specification.RPG_O_Output_Program_Record_Id.RPG_O_Output_Program_Record_Id_Piece1.RPG_O_Output_Program_Record_Id_Piece1_IV;
import com.eagle.programmar.RPG.Specifications.RPG_O_Output_Specification.RPG_O_Output_Program_Record_Id.RPG_O_Output_Program_Record_Id_Piece2.RPG_O_Output_Program_Record_Id_Piece2_III;
import com.eagle.programmar.RPG.Specifications.RPG_O_Output_Specification.RPG_O_Output_Program_Record_Id.RPG_O_Output_Program_Record_Id_Piece2.RPG_O_Output_Program_Record_Id_Piece2_IV;
import com.eagle.programmar.RPG.Specifications.RPG_O_Output_Specification.RPG_O_Output_Program_Record_Id.RPG_O_Output_Program_Record_Id_Piece3.RPG_O_Output_Program_Record_Id_Piece3_III;
import com.eagle.programmar.RPG.Specifications.RPG_O_Output_Specification.RPG_O_Output_Program_Record_Id.RPG_O_Output_Program_Record_Id_Piece3.RPG_O_Output_Program_Record_Id_Piece3_IV;
import com.eagle.programmar.RPG.Terminals.RPG_Blanks;
import com.eagle.programmar.RPG.Terminals.RPG_Keyword;
import com.eagle.programmar.RPG.Terminals.RPG_KeywordChoice;
import com.eagle.programmar.RPG.Terminals.RPG_Literal;
import com.eagle.programmar.RPG.Terminals.RPG_Number;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public abstract class RPG_O_Output_Specification extends TokenSequence
{
	// Extra layer to keep everything in one file ....
	public RPG_O_Output_Spec oSpec;
	
	public static class RPG_O_Output_Spec extends TokenChooser
	{
		public RPG_O_Output_Program_Record_Id recordId;
		public RPG_O_Output_Program_Field_Descr fieldDescr;
		public RPG_O_Output_External_Record_Id externalRecordId;
		public RPG_O_Output_External_Field_Descr externalFieldDescr;
	}
		
	public static class RPG_O_Output_Specification_III extends RPG_O_Output_Spec
	{
		public RPG_O_Output_Specification_III()
		{
			recordId = new RPG_O_Output_Program_Record_Id_III();
			fieldDescr = new RPG_O_Output_Program_Field_Descr_III();
			externalRecordId = new RPG_O_Output_External_Record_Id_III();
			externalFieldDescr = new RPG_O_Output_External_Field_Descr_III();
		}
	}
	
	public static class RPG_O_Output_Specification_IV extends RPG_O_Output_Spec
	{
		public RPG_O_Output_Specification_IV()
		{
			recordId = new RPG_O_Output_Program_Record_Id_IV();
			fieldDescr = new RPG_O_Output_Program_Field_Descr_IV();
			externalRecordId = new RPG_O_Output_External_Record_Id_IV();
			externalFieldDescr = new RPG_O_Output_External_Field_Descr_IV();
		}
	}
	
	public abstract static class RPG_O_Output_Program_Record_Id extends TokenSequence
	{
		public RPG_Keyword O = new RPG_Keyword(6, 6, "O");
		public RPG_O_Output_Program_Record_Id_Piece piece;
		public @OPT RPG_Literal skipBefore;
		public @OPT RPG_Literal skipAfter;
		public @OPT RPG_Literal indicators;
		public @OPT RPG_Literal exceptName;
		public RPG_Blanks blank1;
		
		public static class RPG_O_Output_Program_Record_Id_III extends RPG_O_Output_Program_Record_Id
		{
			public RPG_O_Output_Program_Record_Id_III()
			{
				piece = new RPG_O_Output_Program_Record_Id_Piece_III();
				
				skipBefore = new RPG_Literal(19, 20);
				skipAfter = new RPG_Literal(21, 22);
				indicators = new RPG_Literal(23, 31);
				exceptName = new RPG_Literal(32, 37);
				blank1 = new RPG_Blanks(38, 74);
			}
		}
		
		public static class RPG_O_Output_Program_Record_Id_IV extends RPG_O_Output_Program_Record_Id
		{
			public RPG_O_Output_Program_Record_Id_IV()
			{
				piece = new RPG_O_Output_Program_Record_Id_Piece_IV();
				
				skipBefore = new RPG_Literal(46, 48);
				skipAfter = new RPG_Literal(49, 51);
				indicators = new RPG_Literal(21, 29);
				exceptName = new RPG_Literal(30, 39);
				blank1 = new RPG_Blanks(52, 80);
			}
		}

		public abstract static class RPG_O_Output_Program_Record_Id_Piece extends TokenChooser
		{
			public RPG_O_Output_Program_Record_Id_Piece1 piece1;
			public RPG_O_Output_Program_Record_Id_Piece2 piece2;
			public RPG_O_Output_Program_Record_Id_Piece3 piece3;
		}
		
		public static class RPG_O_Output_Program_Record_Id_Piece_III extends RPG_O_Output_Program_Record_Id_Piece
		{
			public RPG_O_Output_Program_Record_Id_Piece_III()
			{
				piece1 = new RPG_O_Output_Program_Record_Id_Piece1_III();
				piece2 = new RPG_O_Output_Program_Record_Id_Piece2_III();
				piece3 = new RPG_O_Output_Program_Record_Id_Piece3_III();
			}
		}
		
		public static class RPG_O_Output_Program_Record_Id_Piece_IV extends RPG_O_Output_Program_Record_Id_Piece
		{
			public RPG_O_Output_Program_Record_Id_Piece_IV()
			{
				piece1 = new RPG_O_Output_Program_Record_Id_Piece1_IV();
				piece2 = new RPG_O_Output_Program_Record_Id_Piece2_IV();
				piece3 = new RPG_O_Output_Program_Record_Id_Piece3_IV();
			}
		}

		public abstract static class RPG_O_Output_Program_Record_Id_Piece1 extends TokenSequence
		{
			public RPG_Literal recordName;
			public RPG_KeywordChoice type;
			public @OPT RPG_KeywordChoice overflowRelease;
			public @OPT RPG_Number spaceBefore;
			public @OPT RPG_Number spaceAfter;
			public @OPT RPG_Literal skipBefore;
			public @OPT RPG_Literal skipAfter;
			
			public static class RPG_O_Output_Program_Record_Id_Piece1_III extends RPG_O_Output_Program_Record_Id_Piece1
			{
				public RPG_O_Output_Program_Record_Id_Piece1_III()
				{
					recordName = new RPG_Literal(7, 14);
					type = new RPG_KeywordChoice(15, 15, "H", "D", "T", "E");
					overflowRelease = new RPG_KeywordChoice(16, 16, "F", "R");
					spaceBefore = new RPG_Number(17, 17);
					spaceAfter = new RPG_Number(18, 18);
					skipBefore = new RPG_Literal(19, 20);
					skipAfter = new RPG_Literal(21, 22);
				}
			}

			public static class RPG_O_Output_Program_Record_Id_Piece1_IV extends RPG_O_Output_Program_Record_Id_Piece1
			{
				public RPG_O_Output_Program_Record_Id_Piece1_IV()
				{
					recordName = new RPG_Literal(7, 16);
					type = new RPG_KeywordChoice(17, 17, "H", "D", "T", "E");
					overflowRelease = new RPG_KeywordChoice(18, 18, "F", "R");
					spaceBefore = new RPG_Number(40, 42);
					spaceAfter = new RPG_Number(43, 45);
					skipBefore = new RPG_Literal(46, 48);
					skipAfter = new RPG_Literal(49, 51);
				}
			}
		}
		
		public abstract static class RPG_O_Output_Program_Record_Id_Piece2 extends TokenSequence
		{
			public RPG_Blanks blank1;
			public RPG_KeywordChoice logicalRelation;
			public RPG_Blanks blank2;
			
			public static class RPG_O_Output_Program_Record_Id_Piece2_III extends RPG_O_Output_Program_Record_Id_Piece2
			{
				public RPG_O_Output_Program_Record_Id_Piece2_III()
				{
					blank1 = new RPG_Blanks(7, 13);
					logicalRelation = new RPG_KeywordChoice(14, 16, "AND", "OR");
					blank2 = new RPG_Blanks(17, 22);
				}
			}
			
			public static class RPG_O_Output_Program_Record_Id_Piece2_IV extends RPG_O_Output_Program_Record_Id_Piece2
			{
				public RPG_O_Output_Program_Record_Id_Piece2_IV()
				{
					blank1 = new RPG_Blanks(7, 15);
					logicalRelation = new RPG_KeywordChoice(16, 18, "AND", "OR");
					blank2 = new RPG_Blanks(19, 22);
				}
			}
		}
		
		public abstract static class RPG_O_Output_Program_Record_Id_Piece3 extends TokenSequence
		{
			public RPG_Blanks blank1;
			public RPG_KeywordChoice logicalRelation;
			public RPG_Blanks blank2;
			
			public static class RPG_O_Output_Program_Record_Id_Piece3_III extends RPG_O_Output_Program_Record_Id_Piece3
			{
				public RPG_O_Output_Program_Record_Id_Piece3_III()
				{
					blank1 = new RPG_Blanks(7, 15);
					logicalRelation = new RPG_KeywordChoice(16, 18, "ADD", "DEL");
					blank2 = new RPG_Blanks(19, 22);
				}
			}
			
			public static class RPG_O_Output_Program_Record_Id_Piece3_IV extends RPG_O_Output_Program_Record_Id_Piece3
			{
				public RPG_O_Output_Program_Record_Id_Piece3_IV()
				{
					blank1 = new RPG_Blanks(7, 17);
					logicalRelation = new RPG_KeywordChoice(18, 20, "ADD", "DEL");
					blank2 = new RPG_Blanks(21, 22);
				}
			}
		}
	}
	
	public abstract static class RPG_O_Output_Program_Field_Descr extends TokenSequence
	{
		public RPG_Keyword O = new RPG_Keyword(6, 6, "O");
		public RPG_Blanks blank1;
		public @OPT RPG_Literal indicators;
		public RPG_Literal fieldName;
		public @OPT RPG_Literal editCodes;
		public @OPT RPG_Keyword blankAfter;
		public @OPT RPG_Literal endPosition;
		public @OPT RPG_KeywordChoice dataFormat;
		public @OPT RPG_Literal constantOrEditWord;
		public RPG_Blanks blank2;

		public static class RPG_O_Output_Program_Field_Descr_III extends RPG_O_Output_Program_Field_Descr
		{
			public RPG_O_Output_Program_Field_Descr_III()
			{
				blank1 = new RPG_Blanks(7, 22);
				indicators = new RPG_Literal(23, 31);
				fieldName = new RPG_Literal(32, 37);
				editCodes = new RPG_Literal(38, 38);
				blankAfter = new RPG_Keyword(39, 39, "B");
				endPosition = new RPG_Literal(40, 43);
				dataFormat = new RPG_KeywordChoice(44, 44, "P", "B", "L", "R");
				constantOrEditWord = new RPG_Literal(45, 70);
				blank2 = new RPG_Blanks(71, 74);
			}
		}
		
		public static class RPG_O_Output_Program_Field_Descr_IV extends RPG_O_Output_Program_Field_Descr
		{
			public RPG_O_Output_Program_Field_Descr_IV()
			{
				blank1 = new RPG_Blanks(7, 21);
				indicators = new RPG_Literal(21, 29);
				fieldName = new RPG_Literal(30, 43);
				editCodes = new RPG_Literal(44, 44);
				blankAfter = new RPG_Keyword(45, 45, "B");
				endPosition = new RPG_Literal(47, 51);
				dataFormat = new RPG_KeywordChoice(52, 52, "P", "B", "L", "R");
				constantOrEditWord = new RPG_Literal(53, 80);
				blank2 = new RPG_Blanks(0, 0);		// Not Applicable
			}
		}
	}
	
	public abstract static class RPG_O_Output_External_Record_Id extends TokenSequence
	{
		public RPG_Keyword O = new RPG_Keyword(6, 6, "O");
		public RPG_Literal recordName;
		public RPG_O_Output_External_Record_Id_Piece piece;
		public @OPT RPG_Literal indicators;
		public @OPT RPG_Literal exceptName;
		public RPG_Blanks blank1;
		
		public static class RPG_O_Output_External_Record_Id_III extends RPG_O_Output_External_Record_Id
		{
			public RPG_O_Output_External_Record_Id_III()
			{
				piece = new RPG_O_Output_External_Record_Id_Piece_III();

				recordName = new RPG_Literal(7, 14);
				indicators = new RPG_Literal(23, 31);
				exceptName = new RPG_Literal(32, 37);
				blank1 = new RPG_Blanks(38, 74);
			}
		}
		
		public static class RPG_O_Output_External_Record_Id_IV extends RPG_O_Output_External_Record_Id
		{
			public RPG_O_Output_External_Record_Id_IV()
			{
				piece = new RPG_O_Output_External_Record_Id_Piece_IV();

				recordName = new RPG_Literal(7, 16);
				indicators = new RPG_Literal(21, 29);
				exceptName = new RPG_Literal(30, 39);
				blank1 = new RPG_Blanks(40, 80);
			}
		}
		
		public abstract static class RPG_O_Output_External_Record_Id_Piece extends TokenChooser
		{
			public RPG_O_Output_External_Record_Id_Piece1 piece1;
			public RPG_O_Output_External_Record_Id_Piece2 piece2;
			public RPG_O_Output_External_Record_Id_Piece3 piece3;
		}
		
		public static class RPG_O_Output_External_Record_Id_Piece_III extends RPG_O_Output_External_Record_Id_Piece
		{
			public RPG_O_Output_External_Record_Id_Piece_III()
			{
				piece1 = new RPG_O_Output_External_Record_Id_Piece1_III();
				piece2 = new RPG_O_Output_External_Record_Id_Piece2_III();
				piece3 = new RPG_O_Output_External_Record_Id_Piece3_III();
			}
		}
		
		public static class RPG_O_Output_External_Record_Id_Piece_IV extends RPG_O_Output_External_Record_Id_Piece
		{
			public RPG_O_Output_External_Record_Id_Piece_IV()
			{
				piece1 = new RPG_O_Output_External_Record_Id_Piece1_IV();
				piece2 = new RPG_O_Output_External_Record_Id_Piece2_IV();
				piece3 = new RPG_O_Output_External_Record_Id_Piece3_IV();
			}
		}
		
		public abstract static class RPG_O_Output_External_Record_Id_Piece1 extends TokenSequence
		{
			public RPG_Literal filename;
			public RPG_KeywordChoice type;
			public RPG_Keyword release;
			public RPG_Blanks blank2;
			
			public static class RPG_O_Output_External_Record_Id_Piece1_III extends RPG_O_Output_External_Record_Id_Piece1
			{
				public RPG_O_Output_External_Record_Id_Piece1_III()
				{
					filename = new RPG_Literal(7, 14);
					type = new RPG_KeywordChoice(15, 15, "H", "D", "T", "E");
					release = new RPG_Keyword(16, 16, "R");
					blank2 = new RPG_Blanks(17, 22);
				}
			}
			
			public static class RPG_O_Output_External_Record_Id_Piece1_IV extends RPG_O_Output_External_Record_Id_Piece1
			{
				public RPG_O_Output_External_Record_Id_Piece1_IV()
				{
					filename = new RPG_Literal(7, 16);
					type = new RPG_KeywordChoice(17, 17, "H", "D", "T", "E");
					release = new RPG_Keyword(18, 18, "R");
					blank2 = new RPG_Blanks(19, 22);
				}
			}
		}
		
		public abstract static class RPG_O_Output_External_Record_Id_Piece2 extends TokenSequence
		{
			public RPG_Blanks blank1;
			public RPG_KeywordChoice logicalRelation;
			public RPG_Blanks blank2;
			
			public static class RPG_O_Output_External_Record_Id_Piece2_III extends RPG_O_Output_External_Record_Id_Piece2
			{
				public RPG_O_Output_External_Record_Id_Piece2_III()
				{
					blank1 = new RPG_Blanks(7, 13);
					logicalRelation = new RPG_KeywordChoice(14, 16, "AND", "OR");
					blank2 = new RPG_Blanks(17, 22);
				}
			}
			
			public static class RPG_O_Output_External_Record_Id_Piece2_IV extends RPG_O_Output_External_Record_Id_Piece2
			{
				public RPG_O_Output_External_Record_Id_Piece2_IV()
				{
					blank1 = new RPG_Blanks(7, 15);
					logicalRelation = new RPG_KeywordChoice(16, 18, "AND", "OR");
					blank2 = new RPG_Blanks(19, 22);
				}
			}
		}
		
		public abstract static class RPG_O_Output_External_Record_Id_Piece3 extends TokenSequence
		{
			public RPG_Blanks blank1;
			public RPG_KeywordChoice logicalRelation;
			public RPG_Blanks blank2;
			
			public static class RPG_O_Output_External_Record_Id_Piece3_III extends RPG_O_Output_External_Record_Id_Piece3
			{
				public RPG_O_Output_External_Record_Id_Piece3_III()
				{
					blank1 = new RPG_Blanks(7, 15);
					logicalRelation = new RPG_KeywordChoice(16, 18, "ADD", "DEL");
					blank2 = new RPG_Blanks(19, 22);
				}
			}
			
			public static class RPG_O_Output_External_Record_Id_Piece3_IV extends RPG_O_Output_External_Record_Id_Piece3
			{
				public RPG_O_Output_External_Record_Id_Piece3_IV()
				{
					blank1 = new RPG_Blanks(7, 17);
					logicalRelation = new RPG_KeywordChoice(18, 20, "ADD", "DEL");
					blank2 = new RPG_Blanks(21, 22);
				}
			}
		}
	}
	
	public abstract static class RPG_O_Output_External_Field_Descr extends TokenSequence
	{
		public RPG_Keyword O = new RPG_Keyword(6, 6, "O");
		public RPG_Blanks blank1;
		public @OPT RPG_Literal indicators;
		public RPG_Literal fieldName;
		public RPG_Blanks blank2;
		public @OPT RPG_Keyword blankAfter;
		public RPG_Blanks blank3;

		public static class RPG_O_Output_External_Field_Descr_III extends RPG_O_Output_External_Field_Descr
		{
			public RPG_O_Output_External_Field_Descr_III()
			{
				blank1 = new RPG_Blanks(7, 22);
				indicators = new RPG_Literal(23, 31);
				fieldName = new RPG_Literal(32, 37);
				blank2 = new RPG_Blanks(38, 38);
				blankAfter = new RPG_Keyword(39, 39, "B");
				blank3 = new RPG_Blanks(40, 74);
			}
		}
		
		public static class RPG_O_Output_External_Field_Descr_IV extends RPG_O_Output_External_Field_Descr
		{
			public RPG_O_Output_External_Field_Descr_IV()
			{
				blank1 = new RPG_Blanks(7, 20);
				indicators = new RPG_Literal(21, 29);
				fieldName = new RPG_Literal(30, 43);
				blank2 = new RPG_Blanks(44, 44);
				blankAfter = new RPG_Keyword(45, 45, "B");
				blank3 = new RPG_Blanks(46, 80);
			}
		}
	}
}
