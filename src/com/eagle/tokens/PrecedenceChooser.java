// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Jeff Wilkinson, Nov 1, 2011

package com.eagle.tokens;

import java.util.ArrayList;


public abstract class PrecedenceChooser extends TokenChooser
{
	private ArrayList<Class<? extends AbstractToken>> _unaryChoices =
			new ArrayList<Class<? extends AbstractToken>>();
	private ArrayList<Class<? extends AbstractToken>> _binaryChoices =
			new ArrayList<Class<? extends AbstractToken>>();

	private BinaryOperator.AllowedPrecedence _allowed = BinaryOperator.AllowedPrecedence.ANY;
	private Class<? extends BinaryOperator> _lastChoice = null;
	
	protected abstract void establishChoices();		

	public static class UnaryOperator extends TokenSequence
	{
		// Nothing to add -- just a layer
	}

	public static class BinaryOperator extends TokenSequence
	{
		public enum AllowedPrecedence 
		{
			ANY,
			HIGHER,
			ATLEAST
		}
	}
	
	public PrecedenceChooser() 
	{
		establishChoices();
	}
	
	public PrecedenceChooser(BinaryOperator.AllowedPrecedence allowed,
			Class<? extends BinaryOperator> lastChoice)
	{ 
		_allowed = allowed;
		_lastChoice = lastChoice;
		establishChoices();
	}
	
	protected void addUnaryOperator(Class<? extends AbstractToken> cls)
	{
		_unaryChoices.add(cls);
	}
		
	protected void addBinaryOperator(Class<? extends BinaryOperator> cls)
	{
		_binaryChoices.add(cls);
	}
		
	public ArrayList<Class<? extends AbstractToken>> getPrimaries()
	{
		return _unaryChoices;
	}
	
	public ArrayList<Class<? extends AbstractToken>> getBinaries()
	{
		return _binaryChoices;
	}
	
	public Class<? extends BinaryOperator> getLastChoice()
	{
		return _lastChoice;
	}
	
	public BinaryOperator.AllowedPrecedence getAllowed()
	{
		return _allowed;
	}
}	// end of PrecedenceChooser class
