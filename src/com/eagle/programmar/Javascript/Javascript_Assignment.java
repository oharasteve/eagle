// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 12, 2011

package com.eagle.programmar.Javascript;

import com.eagle.programmar.Javascript.Terminals.Javascript_Punctuation;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;
import com.eagle.tokens.punctuation.PunctuationEquals;
import com.eagle.tokens.punctuation.PunctuationSemicolon;

public class Javascript_Assignment extends TokenSequence
{
	public @NEWLINE Javascript_Variable var;
	public Javascript_AssignmentOperator assignmentOperator;
	public Javascript_Expression exp;
	public @NOSPACE PunctuationSemicolon semicolon;

	public static class Javascript_AssignmentOperator extends TokenChooser
	{
		public @CHOICE PunctuationEquals equals;
		public @CHOICE Javascript_Punctuation timesEquals = new Javascript_Punctuation("*=");
		public @CHOICE Javascript_Punctuation divideEquals = new Javascript_Punctuation("/=");
		public @CHOICE Javascript_Punctuation modulusEquals = new Javascript_Punctuation("%=");
		public @CHOICE Javascript_Punctuation plusEquals = new Javascript_Punctuation("+=");
		public @CHOICE Javascript_Punctuation minusEquals = new Javascript_Punctuation("-=");
		public @CHOICE Javascript_Punctuation shiftLeftEquals = new Javascript_Punctuation("<<=");
		public @CHOICE Javascript_Punctuation shiftRightEquals = new Javascript_Punctuation(">>=");
		public @CHOICE Javascript_Punctuation logicalShiftRightEquals = new Javascript_Punctuation(">>>=");
		public @CHOICE Javascript_Punctuation bitwiseAndEquals = new Javascript_Punctuation("&=");
		public @CHOICE Javascript_Punctuation bitwiseXOrEquals = new Javascript_Punctuation("^=");
		public @CHOICE Javascript_Punctuation bitwiseOrEquals = new Javascript_Punctuation("|=");
	}
}
