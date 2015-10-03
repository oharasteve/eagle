import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class PicasaContacts2
{
	private void process(File dir) throws IOException
	{
		for (File file : dir.listFiles())
		{
			if (file.isDirectory())
			{
				process(file);
			}
			else if (file.getName().equalsIgnoreCase(".picasa.ini"))
			{
				//System.out.println(file.getCanonicalPath());
				processOne(file);
			}
		}
	}

	private void processOne(File file) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(file));
		String rec;
		ArrayList<String> lines = new ArrayList<String>();
		while ((rec = br.readLine()) != null)
		{
			if (rec.startsWith("[Contacts2]"))
			{
				// Finish reading the file, but skip the [Contacts2] entry
				while ((rec = br.readLine()) != null)
				{
					if (rec.startsWith("["))
					{
						lines.add(rec);
						break;
					}
				}
				while ((rec = br.readLine()) != null)
				{
					lines.add(rec);
				}
				br.close();

				changeFile(file, lines);
				return;
			}
			lines.add(rec);
		}
		
		br.close();
	}
	
	private void changeFile(File file, ArrayList<String> lines) throws IOException
	{
		// Rewrite it, done reading it
		PrintWriter pw = new PrintWriter(new FileWriter(file));
		for (String line : lines)
		{
			pw.println(line);
		}
		pw.close();

		String fullname = file.getCanonicalPath();
		System.out.println("Updated " + fullname);
	}
	
	public static void main(String args[])
	{
		if (args.length != 1)
		{
			System.err.println("Usage: PicasaContacts2 C:\\www2\\photos");
			System.exit(0);
		}
		
		PicasaContacts2 pc = new PicasaContacts2();
		try
		{
			pc.process(new File(args[0]));
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			System.exit(1);
		}
		System.exit(0);
	}
}
