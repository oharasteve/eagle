// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 4, 2014

package com.eagle.tests;

import com.eagle.math.EagleStack;
import com.eagle.math.EagleValue;
import com.eagle.preprocess.EagleSymbolTable;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TerminalCommentToken;
import com.eagle.tokens.TokenChooser;

public abstract class EagleInterpreter
{
	public EagleSymbolTable _symbolTable;
	private EagleStack _stack = new EagleStack();
	
	public EagleInterpreter(EagleSymbolTable symbolTable)
	{
		_symbolTable = symbolTable;
	}
	
	public void tryToInterpret(AbstractToken token)
	{
		if (token instanceof EagleRunnable)
		{
			EagleRunnable runnable = (EagleRunnable) token;
			runnable.interpret(this);
		}
		else if (TokenChooser.class.isAssignableFrom(token.getClass()))
		{
			tryToInterpret(((TokenChooser) token)._whichToken);
		}
		else if (!TerminalCommentToken.class.isAssignableFrom(token.getClass()))
		{
			throw new RuntimeException("Please add EagleRunnable interface to " +
					token.getClass().getName() + " in " + token.getParent().getClass().getName());
		}
	}
	
	//
	// Pushers
	//
	
	public void pushBool(boolean val)
	{
		_stack.pushBool(val);
	}
	
	public void pushInt(int val)
	{
		_stack.pushInt(val);
	}
	
	public void pushStr(String val)
	{
		_stack.pushStr(val);
	}
	
	public void pushEagleValue(EagleValue val)
	{
		_stack.pushEagleValue(val);
	}
	
	//
	// Getters
	//
	
	public boolean getBoolValue(AbstractToken expr)
	{
		tryToInterpret(expr);
		return _stack.popBoolValue();
	}
	
	public int getIntValue(AbstractToken expr)
	{
		tryToInterpret(expr);
		return _stack.popIntValue();
	}
	
	public String getStrValue(AbstractToken expr)
	{
		tryToInterpret(expr);
		return _stack.popStrValue();
	}
	
	public EagleValue getEagleValue(AbstractToken expr)
	{
		tryToInterpret(expr);
		return _stack.popEagleValue();
	}
}
