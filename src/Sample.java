import com.eagle.io.EagleReadXML;
import com.eagle.programmar.PHP.PHP_Program;
import com.eagle.programmar.PHP.PHP_Program.PHP_Entry;
import com.eagle.tokens.AbstractToken;


public class Sample
{
	private void process(String filename)
	{
		EagleReadXML reader = new EagleReadXML();
		PHP_Program program = (PHP_Program) reader.readFrom(filename);
		int i = 0;
		for (PHP_Entry entry : program.entry._elements)
		{
			i++;
			AbstractToken which = entry.getWhich();
			System.out.println("** " + i + ": " + which.toString());
		}
	}
	
	public static void main(String[] args)
	{
		if (args.length < 1)
		{
			System.out.println("Usage: file_php.xml");
			System.exit(1);
		}
		String filename = args[0];
		if (! filename.endsWith("_php.xml"))
		{
			System.err.println("Please use a PHP file, not " + filename);
			System.exit(2);
		}
		
		Sample s = new Sample();
		s.process(filename);
		System.exit(0);
	}
}
