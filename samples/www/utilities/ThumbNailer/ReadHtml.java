import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.text.NumberFormat;

// Original author: Steven A. O'Hara, Mar 25, 2011

public class ReadHtml
{
	private static final int MAXMINUTES = 5;
	private static final int SLOWMINUTES = 1;
	
	public static String readUrl(String url) throws Exception
	{
		URL website = new URL(url);
		StringBuffer sb = new StringBuffer();
		
		URLConnection conn =  website.openConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while ((line = br.readLine()) != null)
		{
			sb.append(line);
            sb.append("\n");
		}
		br.close();
		return sb.toString();
	}
	
	private void readIt(String url, PrintStream out) throws Exception
	{
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(2);

		URL website = new URL(url);
		
		URLConnection conn =  website.openConnection();
		conn.setReadTimeout(MAXMINUTES * 60 * 1000);
		InputStream is = conn.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		while (true)
		{
			long start = System.currentTimeMillis();
			try
			{
				String line = br.readLine();
				long end = System.currentTimeMillis();
				if (end - start > SLOWMINUTES * 60 * 1000)
				{
					double delay = (end - start) / 60.0 / 1000;
					System.err.println("Really slow read ... minutes = " + nf.format(delay));
				}
				if (line == null) break;
				out.println(line);
			}
			catch (SocketTimeoutException ex)
			{
				throw new RuntimeException("Hit read timeout limit of " + MAXMINUTES + " minutes.", ex);
			}
		}
		br.close();
	}
	
	public static void main(String[] args)
    {
		if (args.length != 1)
		{
			System.err.println("Usage: ReadHtml url");
			System.exit(1);
		}
		ReadHtml rh = new ReadHtml();
    	try
    	{
    		rh.readIt(args[0], System.out);
    	}
    	catch (Exception ex)
    	{
    		System.err.println("Processing error");
    		ex.printStackTrace();
    		System.exit(2);
    	}
    	System.exit(0);
    }
}
