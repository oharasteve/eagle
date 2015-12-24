// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 1, 2010

package com.eagle.tokens;

import java.lang.reflect.Field;

import com.eagle.programmar.EagleSyntax;


/**
 * Most common Token, a sequence of other tokens
 */

public class TokenSequence extends AbstractToken
{
	@Override
	public void forceSyntax(EagleSyntax syntax)
	{
		try
		{
			setSyntax(syntax);
	
			Class<? extends AbstractToken> cls = this.getClass();
			Field[] fields = cls.getFields();
			for (Field fld : fields)
			{
				Class<?> fldType = fld.getType();
				if (! AbstractToken.class.isAssignableFrom(fldType)) continue;
				AbstractToken child = (AbstractToken) fld.get(this);
				if (child != null) child.forceSyntax(syntax);
			}
		}
		catch (IllegalAccessException ex)
		{
			throw new RuntimeException("Unable to set syntax on " + this.toString(), ex);
		}
	}
}
