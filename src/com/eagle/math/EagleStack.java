// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 1, 2015

package com.eagle.math;

import java.util.Stack;

import com.eagle.tokens.AbstractToken;

public class EagleStack
{
	private Stack<EagleValue> _stack = new Stack<EagleValue>();

	public void pushBool(boolean val)
	{
		_stack.push(new BooleanValue(val));
	}
	
	public void pushInt(int val)
	{
		_stack.push(new IntegerValue(val));
	}
	
	public void pushStr(String val)
	{
		_stack.push(new StringValue(val));
	}
	
	public void pushTokenValue(TokenValue val)
	{
		_stack.push(val);
	}
	
	public void pushEagleValue(EagleValue val)
	{
		_stack.push(val);
	}
	
	public EagleValue popEagleValue()
	{
		return _stack.pop();
	}
	
	public boolean popBoolValue()
	{
		EagleValue value = _stack.pop();
		return value.forceBooleanValue();
	}
	
	public int popIntValue()
	{
		EagleValue value = _stack.pop();
		return value.forceIntegerValue();
	}
	
	public String popStrValue()
	{
		EagleValue value = _stack.pop();
		return value.forceStringValue();
	}
	
	public AbstractToken popTokenValue()
	{
		EagleValue value = _stack.pop();
		return ((TokenValue) value).getTokenValue();
	}
}
