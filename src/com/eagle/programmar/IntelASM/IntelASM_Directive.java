// Copyright Eagle Legacy Modernization, LLC, 2010-date
// Original author: Steven A. O'Hara, Nov 29, 2014

package com.eagle.programmar.IntelASM;

import com.eagle.programmar.IntelASM.Directives.IntelASM_CpuDirective;
import com.eagle.programmar.IntelASM.Directives.IntelASM_DefineDirective;
import com.eagle.programmar.IntelASM.Directives.IntelASM_IncludeDirective;
import com.eagle.programmar.IntelASM.Directives.IntelASM_ListDirective;
import com.eagle.programmar.IntelASM.Directives.IntelASM_MacroDirective;
import com.eagle.programmar.IntelASM.Directives.IntelASM_SectionDirective;
import com.eagle.programmar.IntelASM.Directives.IntelASM_TitleDirective;
import com.eagle.programmar.IntelASM.Terminals.IntelASM_EndOfLine;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenSequence;

public class IntelASM_Directive extends TokenSequence
{
	public IntelASM_DirectiveList directive;
	public IntelASM_EndOfLine eoln;
	
	public static class IntelASM_DirectiveList extends TokenChooser
	{
		public @CHOICE IntelASM_CpuDirective cpuDirective;
		public @CHOICE IntelASM_DefineDirective defineDirective;
		public @CHOICE IntelASM_IncludeDirective includeDirective;
		public @CHOICE IntelASM_ListDirective listDirective;
		public @CHOICE IntelASM_MacroDirective macroDirective;
		public @CHOICE IntelASM_SectionDirective sectionDirective;
		public @CHOICE IntelASM_TitleDirective titleInstruction;
	}
}
