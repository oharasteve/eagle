import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Original author: Steven A. O'Hara, Oct 1, 2012

public class DuplicateString
{
	private boolean matchesMoreThanOnce(String s)
	{
	    int len = s.length();
	    if (len == 0) return true;

	    for (int m = 1; m <= len/2; m++)
	    {
	        if (len % m != 0) continue;
	        String t = s.substring(0, m);
	        int n;
	        boolean ok = true;
	        for (n = 1; n < len/m; n++)
	        {
	            // Make sure the rest of the string is a repeat of “t”
	            if (! t.equals(s.substring(m*n, m*(n+1))))
	            {
	                ok = false;
	                break;
	            }
	        }
	        if (ok) return true;
	    }
	   return false;
	}
	
	private boolean matchesRegex(String s)
	{
		Pattern p = Pattern.compile("^(.*)\\1{1,}$");
		Matcher m = p.matcher(s);
		return m.matches();
	}

	private void test(String s)
	{
		boolean ok1 = matchesMoreThanOnce(s);
		System.out.println("Result of " + s + " = " + (ok1 ? "dups" : "no dups"));
		boolean ok2 = matchesRegex(s);
		System.out.println("      and " + s + " = " + (ok2 ? "dups" : "no dups"));
		if (ok1 != ok2) System.err.println("Oh no!");
	}
	
	public static void main(String args[])
	{
		DuplicateString ds = new DuplicateString();
		ds.test("abcde");
		ds.test("aaa");
		ds.test("abcabc");
		ds.test("abcabcv");
		ds.test("aaaaaaaaaaaaax");
	}
}
