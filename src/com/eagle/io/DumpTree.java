// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Steven A. O'Hara, Dec 5, 2010

package com.eagle.io;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;

import com.eagle.programmar.EagleLanguage;
import com.eagle.tokens.AbstractToken;
import com.eagle.tokens.TerminalToken;
import com.eagle.tokens.TokenChooser;
import com.eagle.tokens.TokenList;
import com.eagle.tokens.TokenSequence;
import com.eagle.utils.EagleUtilities;

public class DumpTree
{
	public enum Width { NARROW, WIDE }
	
	private int _seq;
	private ArrayList<AbstractToken> _saveTokens;
	
	private int _typeWidth;
	private int _textWidth;

	public void dump(PrintStream out, AbstractToken token, Width width, int maxLines, boolean showParent)
	{
		out.println();
		_typeWidth = 20;
		_textWidth = 22;
		if (width == Width.WIDE)
		{
			out.print("   Seq ");
			if (showParent) out.print("Parent ");
			out.println("Depth   SLn   SC   ELn   EC  Type                           Text");
			out.print(" ===== ");
			if (showParent) out.print("====== ");
			out.println("===== ===== ==== ===== ====  ============================== ==================================");
			_typeWidth += 10;
			_textWidth += 20;
		}
		else
		{
			out.print("   Seq ");
			if (showParent) out.print("Parent ");
			out.println("Depth   SLn   SC   ELn   EC  Type                 Text");
			out.print(" ===== ");
			if (showParent) out.print("====== ");
			out.println("===== ===== ==== ===== ====  ==================== ======================");
		}
		
		try
		{
			_seq = 0;
			_saveTokens = new ArrayList<AbstractToken>();
			int depth = dump(out, token, 1, false, maxLines, showParent);
			//EagleUtilities.printCrossReference(out, token);

			// Show final depth
			out.println(EagleUtilities.rj(depth, 6+1+6+1+4));
		}
		catch (Exception ex)
		{
			out.println("DumpTree failed.");
			ex.printStackTrace(out);
		}
		
		out.println();
	}
		
	// Recursive, be careful!
	// No limit if maxLines <= 0
	private int dump(PrintStream out, AbstractToken token0, int depth, boolean optional, int maxLines, boolean showParent) throws Exception
	{
		AbstractToken token = token0;
		if (token == null) return depth;			// Not quite sure how this happens.
		if (optional && !token._present) return depth;	// Skip optional items that are not present
		
		if (token instanceof TokenChooser)
		{
			// Was having a little trouble with the CastExpression piece ...
			// if (((GenericTokenChooser) token).whichToken == null)
			// {
			//	System.out.println(token.startLine + "/" + token.startChar + " WHAT THE? " + token.getClass().getName() + " has .whichToken = null.");
			//	if (!token.present) return;
			// }
			AbstractToken which = ((TokenChooser) token).getWhich();
			//if (which == null) throw new RuntimeException("Why is whichToken null (on " + token.getClass().getName() + ")?");
			if (which == null) return depth;	// Don't really know what to do, the tree is incomplete
			token = which;
		}
		
		// Is it like a Keyword or Punctuation?
		String txt = "";
		if (token instanceof TerminalToken)
		{
			TerminalToken term = (TerminalToken) token;
			txt = term.getValue();
		}
		
		Class<?> cls = token.getClass();
		String clsName;
		if (token instanceof TokenList)
		{
			clsName = "TokenList<>";
		}
		else
		{
			clsName = cls.getName();
			int pos = clsName.lastIndexOf('$');
			if (pos < 0) pos = clsName.lastIndexOf('.');
			if (pos > 0) clsName = clsName.substring(pos + 1);
		}
		
		// Print this token
		_saveTokens.add(token);
		_seq++;
		if (maxLines > 0 && _seq > maxLines) return depth;
		out.print(EagleUtilities.rj(_seq, 6) + " ");
		if (showParent) out.print(EagleUtilities.rj(findParent(token.getParent()), 6) + " ");
		out.println(EagleUtilities.rj(depth, 4) + "  " +
				EagleUtilities.rj(token._currentLine+1, 5) + " " +
				EagleUtilities.rj(token._currentChar+1, 4) + " " +
				EagleUtilities.rj(token._endLine+1, 5) + " " +
				EagleUtilities.rj(token._endChar+1, 4) + "  " +
				EagleUtilities.lj(clsName, _typeWidth) + " " +
				EagleUtilities.trunc(txt, _textWidth));

		if (token instanceof TerminalToken) return depth;
		
		if (token instanceof TokenChooser)
		{
			dump(out, token, depth+1, optional, maxLines, showParent);
			return depth;
		}
		
		// See what the Token is supposed to contain
		Field[] fields = cls.getFields();
		for (Field fld : fields)
		{
			String name = fld.getName();

			if (name.startsWith("this$")) continue;		// Skip inner class junk
			
			boolean skip = fld.isAnnotationPresent(AbstractToken.SKIP.class);
			if (skip) continue;

			boolean opt = fld.getAnnotation(TokenSequence.OPT.class) != null;
			Object obj = fld.get(token);

			if (obj instanceof TokenList<?>)
			{
				TokenList<?> values = (TokenList<?>) obj;
				if (values._elements.size() > 0)
				{
					dump(out, values, depth, opt, maxLines, showParent);
					for (Object val : values._elements)
					{
						if (fld.getAnnotation(AbstractToken.SKIP.class) == null)
						{
							dump(out, (AbstractToken) val, depth+1, opt, maxLines, showParent);
						}
					}
				}
				continue;
			}
			
			// Get the child instance
			AbstractToken child = null;
			if (!(obj instanceof AbstractToken)) continue;	// Ignore junk in class definition
			child = (AbstractToken) obj;
			
			// Recurse
			dump(out, child, depth+1, opt, maxLines, showParent);
		}
		
		return depth;
	}
	
	private int findParent(AbstractToken parent)
	{
		if (parent == null) return 0;
		int seq = 0;
		for (AbstractToken token : _saveTokens)
		{
			seq++;
			if (token.equals(parent)) return seq;
		}
		return findParent(parent.getParent());
	}
	
	public static void main(String args[])
	{
		if (args.length != 1)
		{
			System.out.println("Usage: DumpTree xmlFile");
			System.exit(0);
		}
		
		String xmlFile = args[0];
		if (! xmlFile.endsWith(".xml"))
		{
			System.err.println("xmlFile must end in .xml");
			System.exit(1);
		}
		
		EagleReadXML reader = new EagleReadXML();
		EagleLanguage program = reader.readFrom(xmlFile);
		if (program == null)
		{
			System.err.println("Unable to read " + xmlFile);
			System.exit(2);
		}
		
		DumpTree dt = new DumpTree();
		dt.dump(System.out, program, Width.WIDE, 0, true);
	}
}
