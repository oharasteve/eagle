// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 28, 2013

package com.eagle.tokens;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.eagle.programmar.EagleSyntax;
import com.eagle.tests.EagleInterpreter;
import com.eagle.tests.EagleRunnable;

/**
 * What a generic token looks like
 */

public abstract class AbstractToken
{
	public boolean _present = false;		// Needed for @OPT
	
	private @SKIP AbstractToken _parent;		// Either another TokenSequence or a TokenList
	
	private EagleSyntax _syntax;
	
	public String _fileName;		// Track its original position
	public int _currentLine;
	public int _currentChar;
	public int _endLine = -1;
	public int _endChar = -1;
	
	public AbstractToken getParent()
	{
		return _parent;
	}
	
	public void setParent(AbstractToken parent)
	{
		_parent = parent;
		if (parent != null) _syntax = parent._syntax;
	}
	
	public EagleSyntax getSyntax()
	{
		return _syntax;
	}
	
	public void setSyntax(EagleSyntax syntax)
	{
		_syntax = syntax;
	}

	// Copy the syntax throughout the tree
	public abstract void forceSyntax(EagleSyntax syntax) throws IllegalAccessException;
	
	public void foundIt(int endLine, int endChar)
	{
		_present = true;
		_endLine = endLine;
		_endChar = endChar;
	}
	
//	protected EagleScope newEagleScope()
//	{
//		EagleScope scope = EagleScope.createEagleScope();
//		
//		// Need to find the top token (EagleLanguage)
//		AbstractToken parent = this;
//		while (parent != null)
//		{
//			if (parent instanceof EagleLanguage)
//			{
//				EagleLanguage lang = (EagleLanguage) parent;
//				lang.addScope(scope);
//				return scope;
//			}
//			parent = parent.getParent();
//		}
//		
//		// This little section is for debugging only. Creates the parentage tree for the offending token.
//		StringBuffer parents = new StringBuffer(this.toString());
//	    parent = this.getParent();
//		while (parent != null)
//		{
//			parents.append(" -> ");
//			parents.append(parent.toString());
//			parent = parent.getParent();
//		}
//		
//		//////// Can't always fail on this
//		// throw new RuntimeException("Unable to find the EagleLanguage parent of " + parents.toString());
//		return null;
//	}
	
	public void tryToInterpret(EagleInterpreter interpreter)
	{
		if (this instanceof EagleRunnable)
		{
			EagleRunnable runnable = (EagleRunnable) this;
			runnable.interpret(interpreter);
		}
		else if (TokenChooser.class.isAssignableFrom(this.getClass()))
		{
			((TokenChooser) this)._whichToken.tryToInterpret(interpreter);
		}
		else if (!TerminalCommentToken.class.isAssignableFrom(this.getClass()))
		{
			throw new RuntimeException("Please add EagleRunnable interface to " +
					this.getClass().getName() + " in " + this.getParent().getClass().getName());
		}
	}
	
	// Wow, this is really weird. Default is to discard all the Annotations at compile time
	@Retention(RetentionPolicy.RUNTIME)
	public @interface OPT
	{
		// @OPT means this token is optional
	}

	@Retention(RetentionPolicy.RUNTIME)
	public @interface SKIP
	{
		// @SKIP means this token cannot be parsed. Must come from post-editing.
		// An example is the COBOL picture clause, in order to get the 05's under the 01, etc.
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	public @interface CURIOUS
	{
		// @CURIOUS means this token ain't supposed to be here, but is (sort-of) ok.
		// An example is an extra semicolon in Java after a block { } statement.
		String value();
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	public @interface NEWLINE
	{
		// Do a newline BEFORE this token
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	public @interface NEWLINE2
	{
		// Do two newlines BEFORE this token
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	public @interface INDENT
	{
		// Do a newline before this token and indent afterwards. Like '{'
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	public @interface OUTDENT
	{
		// newline, un-indent, newline. Like '}'
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	public @interface NOSPACE
	{
		// No space at all. Like before a ';'
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	public @interface DOC
	{
		String value();
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	public @interface SYNTAX
	{
		Class<? extends EagleSyntax> value();
	}
	
	// Set the source for this token and all its children
	public void setSource(AbstractToken sourceToken) throws Exception
	{
		if (_fileName != null) return;	// Already set somehow
		
		_fileName = sourceToken._fileName;
		_currentLine = sourceToken._currentLine;
		_currentChar = sourceToken._currentChar;
		_endLine = sourceToken._endLine;
		_endChar = sourceToken._endChar;
		
		if (!_present) return;
		
		if (this instanceof TokenChooser)
		{
			TokenChooser chooser = (TokenChooser) this;
			chooser._whichToken.setSource(sourceToken);
		}
		else if (this instanceof TerminalToken)
		{
			// Do nothing ... has no children
		}
		else // Must be a list of fields
		{
			Class<? extends AbstractToken> cls = this.getClass();
			Field[] fields = cls.getDeclaredFields();
			for (Field fld : fields)
			{
				// Ignore all private fields
				if ((fld.getModifiers() & Modifier.PUBLIC) == 0) continue;	// Skip internal stuff
				
				Object obj = fld.get(this);
				if (obj instanceof TokenList<?>)
				{
					@SuppressWarnings("unchecked")
					TokenList<TokenSequence> items = (TokenList<TokenSequence>) obj;
					for (AbstractToken child : items._elements)
					{
						child.setSource(sourceToken);
					}
				}
				else if (obj instanceof TokenSequence)
				{
					TokenSequence child = (TokenSequence) obj;
					child.setSource(sourceToken);
				}
			}
		}
	}
}
