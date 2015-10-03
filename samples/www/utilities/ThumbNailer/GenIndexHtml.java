import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

// Original author: Steven A. O'Hara, Aug 16, 2012

public class GenIndexHtml {
	private int checkedFiles = 0;
	private int updatedFiles = 0;
	
	private static final String JPGS = "/utilities";
	private final SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss.SSS");

	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("Usage: Dir");
			System.exit(1);
		}
		
		try {
			GenIndexHtml gen = new GenIndexHtml();
			String top = args[0];
			String justName = top;
			int pos = top.lastIndexOf("\\");
			if (pos >= 0) justName = top.substring(pos + 1);
			int pdfPages = gen.doDir(" ", top, justName);
			System.out.println("Files checked: " + commas(gen.checkedFiles));
			System.out.println("      updated: " + commas(gen.updatedFiles));
			System.out.println("    PDF pages: " + commas(pdfPages));
			System.exit(0);
		} catch (Exception ex) {
			System.err.println("GetIndexHtml failed: " + ex.getMessage());
			ex.printStackTrace();
			System.exit(1);
		}
	}

	// Return # PDF pages in a directory (including sub-directories)
	private int doDir(String indent, String dirName, String shortDirName) throws IOException
	{
		int pages = 0;
		String outName = dirName + "\\index.html";
		
		checkedFiles++;
		
		// If the file exists, read it all into one String
		StringBuffer oldFileContents = new StringBuffer("");
		File oldf = new File(outName);
		if (oldf.exists())
		{
			// See if all files and directories are *older* than index.html
			// If yes, skip this folder
			// long oldDate = oldf.lastModified();
			
			BufferedReader br = new BufferedReader(new FileReader(outName));
			String rec;
			while ((rec = br.readLine()) != null)
			{
				oldFileContents.append(rec).append("\r\n");
			}
			br.close();
		}
		
		// Create the new file, in memory only for now
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		PrintWriter pw = new PrintWriter(stream);
		pw.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
        pw.println("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
		pw.println("<head>");
		pw.println("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"/>");
		pw.println("  <link rel=\"stylesheet\" type=\"text/css\" href=\"/Dox/dox.css\"/>");
		pw.println("  <title>Directory " + shortDirName + "</title>");
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<center>");
		pw.println("<h1>Directory " + shortDirName + "</h1>");
		pw.println();

		File dir = new File(dirName);
		int seq = 0;
		boolean showedHeader = false;
		for (File f : dir.listFiles())
		{
			if (f.isDirectory())
			{
				if (f.getName().equals(".git")) continue;
				
				if (!showedHeader)
				{
					showedHeader = true;
					if (indent.length() < 2)
					{
						Date now = new Date();
						System.out.println(df.format(now) + indent + shortDirName);
					}
				}
				pages += dirSub(indent, pw, f.getCanonicalPath(), f.getName());
			}
		}
		// Did directories, now do files
		for (File f : dir.listFiles())
		{
			if (f.isFile())
			{
				pages += fileSub(pw, f.getCanonicalPath(), f.getName(), ++seq);
			}
		}
		
		pw.println("</center>");
		if (pages > 0) pw.println("<p>Total PDF page count = " + commas(pages) + "</p>");
		// pw.println("<br/>Last Updated: " + (new Date()).toString() + "</p>"); 	// This messes up the 'diff' below
		pw.println("</body>");
		pw.println("</html>");
		pw.close();
		
		if (indent.length() < 5)
		{
			Date now = new Date();
			System.out.print(df.format(now) + indent + shortDirName);
			if (pages > 0) System.out.print(" (pdf pages = " + commas(pages) + ")");
			System.out.println();
		}

		String newFileContents = new String(stream.toByteArray());
		if (! oldFileContents.toString().equals(newFileContents))
		{
			updatedFiles++;
			
			// Have to write to the actual file
			FileWriter fw = new FileWriter(outName);
			fw.write(newFileContents);
			fw.close();

			if (oldFileContents.length() == 0)
			{
				System.out.println("             Created " + outName);
			}
			else
			{
				System.out.println("             Updated " + outName);
			}
		}
		
		return pages;
	}
	
	private int dirSub(String indent, PrintWriter pw, String fullName, String shortName) throws IOException
	{
		// Skip thumbnail folders
		if (shortName.equals("thumbs")) return 0;

		// Skip .git folders
		if (shortName.equals(".git")) return 0;
		
		// Recurse
		int pages = doDir(indent + ". ", fullName, shortName);
		
		pw.println("  <table><tr><td><center><a href=\"" + shortName + "/index.html\">");
		pw.print("    <img src=\"" + JPGS + "/folder.jpg\" height=\"100\"/><br/>" + shortName);
		if (pages > 0) pw.print("<br/><i>(pdf pages=" + commas(pages) + ")</i>");
		pw.println();
		pw.println("  </a></center></td></tr></table>");
		pw.println();

		return pages;
	}
	
	private int fileSub(PrintWriter pw, String fullName, String name, int seq) throws IOException
	{
		if (name.equals("index.html")) return 0;
		if (name.equals(".htaccess")) return 0;
		if (name.endsWith("~")) return 0;

		int pages = 0;
		
		pw.println("  <table><tr><td><center><a href=\"" + name + "\">");
		if (name.endsWith(".jpg") || name.endsWith(".JPG")
				 || name.endsWith(".png") || name.endsWith(".PNG")
				 || name.endsWith(".gif") || name.endsWith(".GIF"))
		{
			int nc = name.length();
			String noSuffix = name.substring(0, nc - 4);
			String suffix = name.substring(nc - 4);
			pw.println("    <img src=\"thumbs/" + noSuffix + "_thumb" + suffix + "\" height=\"100\"" +
					" onmouseover=\"p" + seq + ".height=400; p" + seq + ".border=1;\"" +
					" onmouseout=\"p" + seq + ".height=0; p" + seq + ".border=0;\"/>");
			pw.print("    <img name=\"p" + seq + "\" src=\"thumbs/" + noSuffix + "_thumb" + suffix + "\"" +
					" style=\"position:absolute;\" height=\"0\" border=\"0\"/>");
		}
		else if (name.endsWith(".jpeg") || name.endsWith(".JPEG") || name.endsWith(".tiff"))
		{
			int nc = name.length();
			String noSuffix = name.substring(0, nc - 5);
			String suffix = name.substring(nc - 5);
			pw.println("    <img src=\"thumbs/" + noSuffix + "_thumb" + suffix + "\" height=\"100\"" +
					" onmouseover=\"p" + seq + ".height=400; p" + seq + ".border=1;\"" +
					" onmouseout=\"p" + seq + ".height=0; p" + seq + ".border=0;\"/>");
			pw.print("    <img name=\"p" + seq + "\" src=\"thumbs/" + noSuffix + "_thumb" + suffix + "\"" +
					" style=\"position:absolute;\" height=\"0\" border=\"0\"/>");
		}
		else if (name.endsWith(".pdf"))
		{
			int nc = name.length();
			String noSuffix = name.substring(0, nc - 4);
			pw.println("    <img src=\"thumbs/" + noSuffix + "_pdf_thumb.jpg\" height=\"100\"" +
					" onmouseover=\"p" + seq + ".height=400; p" + seq + ".border=1;\"" +
					" onmouseout=\"p" + seq + ".height=0; p" + seq + ".border=0;\"/>");
			pw.print("    <img name=\"p" + seq + "\" src=\"thumbs/" + noSuffix + "_pdf_thumb.jpg\"" +
					" style=\"position:absolute;\" height=\"0\" border=\"0\"/>");
			pages = count_pdf_pages(fullName);
		}
		else if (name.endsWith(".xls") || name.endsWith(".xlsx"))
		{
			pw.print("    <img src=\"" + JPGS + "/excel.jpg\" height=\"100\"/>");
		}
		else if (name.endsWith(".doc") || name.endsWith(".docx"))
		{
			pw.print("    <img src=\"" + JPGS + "/word.jpg\" height=\"100\"/>");
		}
		else
		{
			pw.print("    <img src=\"" + JPGS + "/question.jpg\" height=\"100\"/>");
		}

		pw.print("<br/>" + name);
		if (pages > 0) pw.print("<br/><i>pages=" + commas(pages) + "</i>");
		pw.println();
		pw.println("  </a></center></td></tr></table>");
		pw.println();
		return pages;
	}
	
	private int count_pdf_pages(String fileName) throws IOException
	{
		int pages = 0;
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		String rec;
		while ((rec = br.readLine()) != null)
		{
			int type = rec.indexOf("Type");
			if (type >= 0)
			{
				int page = rec.indexOf("Page", type);
				if (page > 0)
				{
					if (page + 4 >= rec.length() || rec.charAt(page + 4) != 's')
					{
						pages++;
					}
				}
			}
		}
		br.close();
		return pages;
	}
	
	private static String commas (int num)
	{
		return String.format("%,d", num);
	}
}
