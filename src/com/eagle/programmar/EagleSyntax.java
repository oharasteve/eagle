// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 30, 2011

package com.eagle.programmar;

import java.lang.reflect.Field;
import java.util.HashSet;

import com.eagle.parsers.EagleFileReader;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TerminalCommentToken;
import com.eagle.tokens.TerminalKeywordToken;

public abstract class EagleSyntax
{
	public TerminalCommentToken _commentInstance = null;	// Catch-all for comments to ignore
	
	public boolean _isCaseSensitive = false;
	public String _continuationChar;			// Like "_" for VB
	public String _extraCharacters;
	public boolean _autoAdvance = true;			// Set to false for picking up EndOfLine
	
	public boolean _allowDigitsInKeywords = true;	// Only false for IBM Assembler (so far)
	
	public String[] _punctuationExceptions = null;		// To prevent punctuation like "==" from matching "="
	
	public int _commentColumn = 0;		// Meaningful only for COBOL and few others
	public int _earliestComment = 0;	// Means none, 11 for some COBOL's
	public int _fixedStartColumn = 0;	// Means none, 7 for some COBOL's
	public int _fixedEndColumn = 0;		// Means none, 73 for some COBOL's
	
	private HashSet<String> _words = new HashSet<String>();

	// Languages like COBOL need these when there are comments past column 72
	public int recLen(EagleFileReader lines, int line)
	{
		int nc = lines.get(line).length();
		if (_fixedEndColumn == 0) return nc;
		if (nc < _fixedEndColumn) return nc;
		return _fixedEndColumn;
	}
	
	public abstract String syntaxId();
	
	protected void addReservedWord(String word)
	{
		if (_isCaseSensitive)
		{
			_words.add(word);
		}
		else
		{
			_words.add(word.toLowerCase());
		}
	}

	protected void addReservedWords(String[] words)
	{
		for (String w : words) addReservedWord(w);
	}

	protected void findFirstWords(Class<? extends AbstractToken> cls)
	{
		int count = 0;
		
		// Pick up all the Statement keywords.
		// Note: the names must match! E.g., PERFORM = new COBOL_Keyword("PERFORM");
		for (Field fld : cls.getDeclaredFields())
		{
			// Skip Comments - they don't normally start with a regular keyword
			Class<?> statementClass = fld.getType();
			if (! TerminalCommentToken.class.isAssignableFrom(statementClass))
			{
				Field[] fields = statementClass.getDeclaredFields();
				if (fields.length > 0)
				{
					Field firstField = fields[0];
					if (TerminalKeywordToken.class.isAssignableFrom(firstField.getType()))
					{
						String name = firstField.getName();
						//System.out.println("**** Adding reserved word: " + name);
						addReservedWord(name);
						count++;
					}
				}
			}
		}
		
		if (count == 0)
		{
			throw new RuntimeException("Expected to add some keywords from " + cls.getName());
		}
	}
	
	public String[] allReservedWords()
	{
		String[] keywords = new String[_words.size()];
		return _words.toArray(keywords);
	}
	
	public boolean isReserved(String word)
	{
		if (_isCaseSensitive)
		{
			return _words.contains(word);
		}
		return _words.contains(word.toLowerCase());
	}
}
