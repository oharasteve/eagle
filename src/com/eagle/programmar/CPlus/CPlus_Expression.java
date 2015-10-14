// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jul 11, 2015

package com.eagle.programmar.CPlus;

import com.eagle.programmar.C.C_Expression;
import com.eagle.programmar.C.C_Subscript;
import com.eagle.programmar.C.C_Type;
import com.eagle.programmar.C.Terminals.C_Keyword;
import com.eagle.tokens.TokenChooser;

public class CPlus_Expression extends C_Expression
{
	@Override
	protected void establishChoices()
	{
		super.establishChoices();
	}

	public static class CPlus_NewExpression extends ExpressionTerm
	{
		public C_Keyword NEW = new C_Keyword("new");
		public C_Type type;
		public CPlus_NewWhat what;
		
		public static class CPlus_NewWhat extends TokenChooser
		{
			public C_ParenthesizedExpression expr;
			public C_Subscript size;
		}
	}
}
