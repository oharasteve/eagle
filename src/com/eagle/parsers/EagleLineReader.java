// Copyright Eagle Legacy Modernization LLC, 2010-date
// Original author: Steven A. O'Hara, Jun 25, 2014

package com.eagle.parsers;

import java.util.ArrayList;

// Represents a single line in a file

// IMPORTANT: Do not add any methods that CHANGE the current _rec!

public class EagleLineReader
{
	public static class EagleLineChange
	{
		public String _oldrec;
		public String _explanation; // This is for the change that *will* be made to this _oldrec.
	}
	
	private ArrayList<EagleLineChange> _changes = null;
	private String _rec;
	
	public EagleLineReader(String rec)
	{
		_rec = rec;
	}
	
	public char charAt(int pos)
	{
		return _rec.charAt(pos);
	}
	
	public String substring(int sc, int ec)
	{
		return _rec.substring(sc, ec);
	}
	
	public String substring(int sc)
	{
		return _rec.substring(sc);
	}
	
	public int indexOf(char ch)
	{
		return _rec.indexOf(ch);
	}
	
	public int indexOf(char ch, int sc)
	{
		return _rec.indexOf(ch, sc);
	}
	
	public int indexOf(String str)
	{
		return _rec.indexOf(str);
	}
	
	public int indexOf(String str, int sc)
	{
		return _rec.indexOf(str, sc);
	}
	
	public boolean contains(String str)
	{
		return _rec.contains(str);
	}
	
	public boolean startsWith(String str)
	{
		return _rec.startsWith(str);
	}
	
	public boolean startsWith(String str, int sc)
	{
		return _rec.startsWith(str, sc);
	}
	
	public int length()
	{
		return _rec.length();
	}
	
	// This is the ONLY way to modify a source line!
	public void change(String rec, String explanation)
	{
		// The _explanation is for the change compared to the next entry, or _rec itself.
		EagleLineChange lineChange = new EagleLineChange();
		lineChange._oldrec = _rec;
		lineChange._explanation = explanation;
		
		if (_changes == null) _changes = new ArrayList<EagleLineChange>();
		_changes.add(lineChange);
		
		_rec = rec;
	}
	
	@Override
	public String toString()
	{
		return _rec;
	}
}
