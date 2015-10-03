// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 4, 2014

package com.eagle.tests;

import java.util.Stack;

import com.eagle.programmar.DefinitionInterface;
import com.eagle.programmar.ReferenceInterface;
import com.eagle.tokens.AbstractToken;

public abstract class EagleInterpreter
{
	public abstract static class EagleValue
	{
	}
	
	public static class BooleanValue extends EagleValue
	{
		boolean _boolValue;
		
		public BooleanValue(boolean b)
		{
			_boolValue = b;
		}
		
		@Override
		public String toString()
		{
			return Boolean.toString(_boolValue);
		}
	}
	
	public static class IntegerValue extends EagleValue
	{
		int _intValue;
		
		public IntegerValue(int i)
		{
			_intValue = i;
		}

		@Override
		public String toString()
		{
			return Integer.toString(_intValue);
		}
	}
	
	private Stack<EagleValue> stack = new Stack<EagleValue>();

	public void pushBool(boolean val)
	{
		stack.push(new BooleanValue(val));
	}
	
	public void pushInt(int val)
	{
		stack.push(new IntegerValue(val));
	}
	
	public EagleValue popValue()
	{
		return stack.pop();
	}
	
	private boolean popBoolValue()
	{
		EagleValue value = stack.pop();
		if (value instanceof BooleanValue)
		{
			return ((BooleanValue) value)._boolValue;
		}
		throw new RuntimeException("Expected a boolean value on the stack, not " + value.toString());
	}
	
	private int popIntValue()
	{
		EagleValue value = stack.pop();
		if (value instanceof IntegerValue)
		{
			return ((IntegerValue) value)._intValue;
		}
		throw new RuntimeException("Expected an integer value on the stack, not " + value.toString());
	}
	
	public boolean getBoolValue(AbstractToken expr)
	{
		expr.tryToInterpret(this);
		return popBoolValue();
	}
	
	public int getIntValue(AbstractToken expr)
	{
		expr.tryToInterpret(this);
		return popIntValue();
	}
	
	public abstract int getValue(ReferenceInterface variable);

	public abstract void setValue(DefinitionInterface definition, int value);
}
