// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Oct 31, 2015

package com.eagle.programmar.Python;

import java.util.ArrayList;

import org.junit.Test;

import com.eagle.parsers.EagleFileReader;
import com.eagle.parsers.EagleParser;
import com.eagle.programmar.Python.Python_Statement.Python_Simple_Statement;
import com.eagle.programmar.Python.Python_Statement.Python_SingleOrMultiLineStatement.Python_MultlineStatement;
import com.eagle.programmar.Python.Python_Statement.Python_Statement_List;
import com.eagle.programmar.Python.Statements.Python_WhileStatement;
import com.eagle.tokens.AbstractToken;

import junit.framework.TestCase;

public class Python_Tests extends TestCase
{
	private String[] guess = new String[] {
			// Please be careful adding or removing lines												// Seq, Line
			"# They said:  \"Guess the Number\" Game (edited) from http://inventwithpython.com",		//  0     0
			"",																							//        1
			"import random",																			//  1     2
			"guesses_made = 0",																			//  2     3
			"name = raw_input('Hello! What is your name?\n')",											//  3     4
			"number = random.randint(1, 20)",															//  4     5
			"print 'Well, {0}, I am thinking of a number between 1 and 20.'.format(name)",				//  5     6
			"",																							//        7
			"while guesses_made < 6:",																	//  6     8
			"    guess = int(raw_input('Take a guess: '))",												//        9
			"    guesses_made += 1",																	//       10
			"    if guess < number:",																	//       11
			"        print 'Your guess is too low.'",													//       12
			"    if guess > number:",																	//       13
			"        print 'Your guess is too high.'",													//       14
			"",																							//       15
			"    if guess == number:",																	//       16
			"        break",																			//       17
			"",																							//       18
			"if guess == number:",																		//  7    19
			"    print 'Good job, {0}! You guessed my number in {1} guesses!'.format(name, guesses_made)",
			"else:",
			"    print 'Nope. The number I was thinking of was {0}'.format(number)",
	};
	
	@Test
	public void testIndentation()
	{
		EagleParser parser = new EagleParser();
		Python_Program pgm = new Python_Program();
		boolean ok = parser.parse(null, pgm, "guess.py", new EagleFileReader(guess));
		assertTrue("Parse failed", ok);
		
		// Verify depth of the indented statements
		ArrayList<Python_Statement> stmt1 = pgm.entries._elements;
		
		assertEquals(0, getLine(stmt1.get(0), 0));
		assertEquals(2, getLine(stmt1.get(1), 0));
		assertEquals(3, getLine(stmt1.get(2), 0));
		assertEquals(4, getLine(stmt1.get(3), 0));
		assertEquals(5, getLine(stmt1.get(4), 0));
		assertEquals(6, getLine(stmt1.get(5), 0));
		assertEquals(8, getLine(stmt1.get(6), 0));
		assertEquals(19, getLine(stmt1.get(7), 0));
		
		Python_Statement_List stmt2 = (Python_Statement_List) stmt1.get(6).statement._whichToken;
		Python_WhileStatement whileStmt = (Python_WhileStatement) stmt2.statements.getPrimaryElement(0)._whichToken;

		assertEquals(9, getWhileLine(whileStmt, 0, 0));
		assertEquals(10, getWhileLine(whileStmt, 1, 0));
		assertEquals(11, getWhileLine(whileStmt, 2, 0));
		assertEquals(13, getWhileLine(whileStmt, 3, 0));
		assertEquals(16, getWhileLine(whileStmt, 4, 0));
	}
	
	private String[] multi = new String[] {
										// Seq   Sub    Line
			"# Multiline test",			//  0             0
			"",							//                1
			"i = 0; j = 0; k = 0",		//  1,1,1         2
			"while k < 10:",			//  2             3
			"  i+=1;j+=1;k+=1",			//       0,0,0    4
			"  j-=2;j+=2",				//       1,1      5
			"",							//                6
			"print i"					//  3             7
	};
	
	@Test
	public void testMultiLine()
	{
		EagleParser parser = new EagleParser();
		Python_Program pgm = new Python_Program();
		boolean ok = parser.parse(null, pgm, "multi.py", new EagleFileReader(multi));
		assertTrue("Parse failed", ok);
		
		// Verify depth of the indented statements
		ArrayList<Python_Statement> stmt1 = pgm.entries._elements;

		assertEquals(0, getLine(stmt1.get(0), 0));
		assertEquals(2, getLine(stmt1.get(1), 0));
		assertEquals(2, getLine(stmt1.get(1), 1));
		assertEquals(2, getLine(stmt1.get(1), 2));
		assertEquals(3, getLine(stmt1.get(2), 0));
		assertEquals(7, getLine(stmt1.get(3), 0));
		
		Python_Statement_List stmt2 = (Python_Statement_List) stmt1.get(2).statement._whichToken;
		Python_WhileStatement whileStmt = (Python_WhileStatement) stmt2.statements.getPrimaryElement(0)._whichToken;

		assertEquals(4, getWhileLine(whileStmt, 0, 0));
		assertEquals(4, getWhileLine(whileStmt, 0, 1));
		assertEquals(4, getWhileLine(whileStmt, 0, 2));
		assertEquals(5, getWhileLine(whileStmt, 1, 0));
		assertEquals(5, getWhileLine(whileStmt, 1, 1));
	}
	
	private static int getLine(Python_Statement stmt, int indexOnLine)
	{
		AbstractToken which = stmt.statement._whichToken;
		if (which instanceof Python_Statement_List)
		{
			Python_Statement_List list = (Python_Statement_List) which;
			Python_Simple_Statement statement = list.statements.getPrimaryElement(indexOnLine);
			return statement._currentLine;
		}
		return 0;
	}
	
	private static int getWhileLine(Python_WhileStatement stmt, int seq, int indexOnLine)
	{
		Python_MultlineStatement block = (Python_MultlineStatement) stmt.statements._whichToken;
		Python_Statement child = block.statements._elements.get(seq);
		return getLine(child, indexOnLine);
	}
}
