// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 9, 2010

package com.eagle.tokens;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;

import com.eagle.programmar.EagleSyntax;


public class TokenChooser extends AbstractToken
{
	public static final String WHICH_TOKEN = "_whichToken";
	public @SKIP AbstractToken _whichToken = null;

	@Retention(RetentionPolicy.RUNTIME)
	public @interface FIRST
	{
		// @FIRST means look for this before the others
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	public @interface LAST
	{
		// @LAST means look for this after the others
	}
	
	@Override
	public void forceSyntax(EagleSyntax syntax) throws IllegalAccessException
	{
		setSyntax(syntax);
		if (_whichToken != null)
		{
			_whichToken.forceSyntax(syntax);
		}
	}
	
	// Duplicate classes in a TokenChooser means we cannot tell them apart in the "_whichToken" field.
	// Warning, this is pretty slow and should be used with discretion
	public void validateUniqueClasses()
	{
		Field[] fields = this.getClass().getFields();
		Class<?>[] classes = this.getClass().getClasses();
		
		int numFields = fields.length;
		for (int i = 0; i < numFields; i++)
		{
			Field fld = fields[i];
			Class<?> fldType = fld.getType();
			if (! AbstractToken.class.isAssignableFrom(fldType)) continue;
			String fldName = fld.getName();
			if (fldName.equals(WHICH_TOKEN)) continue;	// Skip _whichToken
			
			for (int j = i + 1; j < numFields; j++)
			{
				Class<?> fldType2 = fields[j].getType();
				if (! AbstractToken.class.isAssignableFrom(fldType2)) continue;
				if (fldType.equals(fldType2))
				{
					throw new RuntimeException("Cannot have two fields with the same class in a TokenChooser.\n" +
							"Class=" + this.getClass().getCanonicalName() + " field1=" + fldName + " field2=" + fields[j].getName());
				}
			}
			
			for (Class<?> cls : classes)
			{
				if (!AbstractToken.class.isAssignableFrom(cls)) continue;
				if (fldType.equals(cls))
				{
					throw new RuntimeException("Cannot have a field with a duplicate class in a TokenChooser.\n" +
							"Class=" + this.getClass().getCanonicalName() + " field1=" + fldName + " class2=" + cls.getCanonicalName());
				}
			}
		}
	}
}
