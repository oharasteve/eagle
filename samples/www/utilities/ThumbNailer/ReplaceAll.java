
public class ReplaceAll
{
	public static void main(String[] args)
	{
		String s = "c:\\temp";
		String t = s.replaceAll("\\\\", "\\\\\\\\");
		System.out.println("**" + s + "***" + t + "**");
	}
}
