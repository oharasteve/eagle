// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Dec 15, 2013

package com.eagle.programmar.Lisp;

import com.eagle.programmar.Lisp.Functions.Lisp_DefmacroFunction;
import com.eagle.programmar.Lisp.Functions.Lisp_DefparameterFunction;
import com.eagle.programmar.Lisp.Functions.Lisp_DefunFunction;
import com.eagle.programmar.Lisp.Functions.Lisp_IfFunction;
import com.eagle.programmar.Lisp.Functions.Lisp_LoopFunction;
import com.eagle.tokens.TokenChooser;

public class Lisp_Function extends TokenChooser
{
	public @CHOICE Lisp_DefmacroFunction defMacro;
	public @CHOICE Lisp_DefparameterFunction defParameter;
	public @CHOICE Lisp_DefunFunction defFunction;
	public @CHOICE Lisp_IfFunction ifFunction;
	public @CHOICE Lisp_LoopFunction loopFunction;
}
