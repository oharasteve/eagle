// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Aug 22, 2016

package com.eagle.tokens.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses
(
	{
		TerminalLiteralTests.class,
		TerminalLevelTests.class,
		TerminalPunctuationTests.class,
		TerminalHexNumberTests.class,
		TerminalOctalNumberTests.class
	}
)

public class TerminalTokenTests
{
	// Empty class. All the real action is in the @Suite.SuiteClasses annotation
}
