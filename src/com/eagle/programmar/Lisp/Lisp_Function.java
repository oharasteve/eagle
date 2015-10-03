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
	public Lisp_DefmacroFunction defMacro;
	public Lisp_DefparameterFunction defParameter;
	public Lisp_DefunFunction defFunction;
	public Lisp_IfFunction ifFunction;
	public Lisp_LoopFunction loopFunction;
}
