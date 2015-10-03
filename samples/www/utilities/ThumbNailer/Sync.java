// Original author: Steven A. O'Hara, Mar 23, 2011

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.TimeZone;
import java.util.TreeMap;

public class Sync
{
	private Hashtable<String, Remote> remotes = new Hashtable<String, Remote>();
	private String url = "ftp.oharasteven.com";
	private String id = "oharasteve";
	private int timeZoneDifference;
	private String root;	// Typically "C:\www2"
	
	private class Remote
	{
		String dir;
		String file;
		Date modified;
		long size;

		boolean error = false;
		boolean referenced = false;
	}
	
	private void readRemote(String remoteList) throws Exception
	{
		String rec = "";
		//int seq = 0;
		
		BufferedReader remoteReader = new BufferedReader(new FileReader(remoteList));
		SimpleDateFormat sdf1 = new SimpleDateFormat("y-M-d H:m:s");
		Calendar cal = Calendar.getInstance();
		while ((rec = remoteReader.readLine()) != null)
		{
			//seq++;
			
			if (rec.startsWith(".."))
			{
				String[] pieces = rec.split(",");
				if (pieces.length < 4)
				{
					// ../Dox/Pool/Lucky_Shot,2013-05-01_Mark_Davidson_match.MTS,1969-12-31 17:00:00,
					// Has no file size !?! ignore it!
					continue;	// Must be too large
				}
				Remote rem = new Remote();
				rem.dir = pieces[0].replaceAll("/", "\\\\");
				if (pieces.length > 0) rem.file = pieces[1];
				String key = rem.dir + "\\" + rem.file;
				try
				{
					Date mod = sdf1.parse(pieces[2]);
					rem.size = Long.parseLong(pieces[3]);
			
					cal.setTime(mod);
					cal.add(Calendar.MILLISECOND, -timeZoneDifference);
					mod = cal.getTime();
					
					rem.modified = mod;
				}
				catch (Exception ex)
				{
					if (! key.equals("..\\logs\\.access.log") &&
						! key.equals("..\\stats\\logs"))
					{
						remoteReader.close();
						throw new RuntimeException("Error on: " + key, ex);
					}
					rem.error = true;
				}

				remotes.put(key, rem);
			}
		}
		remoteReader.close();
	}
	
	private TreeMap<Long, ArrayList<String>> _items = new TreeMap<Long, ArrayList<String>>();

	private void readLocal(String localList) throws Exception
	{
		String rec = "";
		int seq = 0;
		
		BufferedReader localReader = new BufferedReader(new FileReader(localList));
		SimpleDateFormat sdf2 = new SimpleDateFormat("M/d/y h:m a");
		DecimalFormat df = new DecimalFormat("#0.0");
		String currDir = "";
		while ((rec = localReader.readLine()) != null)
		{
			seq++;
			
			try
			{
				if ((rec.indexOf(" AM ") > 0 || rec.indexOf(" PM ") > 0) && rec.indexOf("<DIR>") < 0)
				{
					if (currDir.endsWith("\\.svn") || currDir.indexOf("\\.svn\\") > 0) continue;
					if (currDir.endsWith("\\.git") || currDir.indexOf("\\.git\\") > 0) continue;

					String packed = rec.replaceAll("  *", " ");
					String[] pieces = packed.split(" ");
					long size = Long.parseLong(pieces[3].replaceAll(",", ""));
					Date modified = sdf2.parse(pieces[0] + ' ' + pieces[1] + ' ' + pieces[2]);
					String fname = pieces[4];
					String key = currDir + "\\" + fname;
					if (fname.endsWith("~")) continue;
					
					Remote rem = remotes.get(key);

					// Toss leading ..\
					String localName = key;
					if (localName.startsWith("..\\")) localName = localName.substring(3);
					String remoteName = localName.replaceAll("\\\\", "/");
					
					if (rem == null)
					{
						if (! remoteName.equals("Dox/Pool/Lucky_Shot/2013-05-01_Mark_Davidson_match.MTS"))
						{
							itemsPut(size, "@call :do1 " + size + " " + "missing" + " " + localName + " " + remoteName);
						}
					}
					else
					{
						rem.referenced = true;
						
						if (!key.startsWith("..\\stats\\") && !rem.error)
						{
							if (rem.size != size)
							{
								itemsPut(size, "@call :do1 " + size + " " + rem.size + " " + localName + " " + remoteName);
							}
							else if (modified.after(rem.modified))
							{
								long ms = modified.getTime() - rem.modified.getTime();
								double hours = ms / 1000.0 / 60 / 60;
								String older = "older_" + df.format(hours) + "_hrs";
								itemsPut(size, "@call :do1 " + size + " " + older + " " + localName + " " + remoteName);
							}
						}
					}
				}
				else if (rec.indexOf("Directory of ") == 1)
				{
					currDir = rec.substring(14).replace(root, "..");
				}
			}
			catch (Exception ex)
			{
				System.err.println("Error in local at line " + seq + ": " + rec);
				ex.printStackTrace();
				System.exit(1);
			}
		}
		
		// Pull them out, in sorted order
		for (ArrayList<String> itemList : _items.values())
		{
			for (String item : itemList)
			{
				System.out.println(item);
			}
		}
		
		localReader.close();
	}
	
	private void itemsPut(long size, String name)
	{
		if (_items.containsKey(size))
		{
			ArrayList<String> list = _items.get(size);
			list.add(name);
		}
		else
		{
			ArrayList<String> list = new ArrayList<String>();
			list.add(name);
			_items.put(size, list);
		}
	}
	
	private void findOrphans()
	{
		// See if any remote files exist, but don't exist locally
		for (Remote rem : remotes.values())
		{
			if (!rem.referenced)
			{
				if (!rem.dir.startsWith("..\\stats") &&
						!rem.dir.startsWith("..\\.hcc.thumbs") &&
						!rem.dir.startsWith("..\\php") &&
						!rem.dir.startsWith("..\\logs") &&
						!rem.dir.startsWith("..\\.cache") &&
						!rem.dir.contains("\\2012-01-07_Sonogram\\VIDEO"))
				{
					String key = rem.dir + "\\" + rem.file;
					System.err.println("Missing from local: " + key);
				}
			}
		}
	}

	private void showHeader()
	{
		System.out.println("@setlocal");
		System.out.println();
		System.out.println("@if not defined PWD2  echo Please set PWD2 && goto :eof");
		System.out.println();
		System.out.println("@set CNT=0");
		System.out.println();
		System.out.println("@pushd \"" + root + "\"");
		System.out.println();
	}
	
	private void showFooter()
	{
		System.out.println();
		System.out.println("@popd");
		System.out.println("@goto :eof");
		System.out.println();
		System.out.println(":do1");
		System.out.println("@set /a CNT=CNT+1");
		System.out.println();
		System.out.println("@rem %1=size %2=comment %3=local %4=remote");
		System.out.println("@echo %CNT%. Putting %~nx3 - now %1 - was %2");
		System.out.println("@ncftpput -u " + id + " -p %PWD2% -m -C " + url + " %3 %4");
		System.out.println("@goto :eof");
	}
	
	public static void main(String[] args)
    {
		if (args.length != 3)
		{
			System.err.println("Usage: Sync root remoteList localList");
			System.exit(1);
		}

		try
    	{
			Sync s = new Sync();

			// Adjust for 2 hour time zone difference between "here" and California
			// TimeZone texas = TimeZone.getTimeZone("CST");
			TimeZone here = TimeZone.getDefault();
			//System.out.println("TZ Here = " + here.toString());
			//String pt = (here.inDaylightTime(new Date()) ? "PDT" : "PST");
			TimeZone california = TimeZone.getTimeZone("PST");
			//System.out.println("TZ Cal = " + california.toString());
			long today = new Date().getTime();
			s.timeZoneDifference = california.getOffset(today) - here.getOffset(today);

			s.root = args[0];
			
    		s.showHeader();
    		s.readRemote(args[1]);
    		s.readLocal(args[2]);
    		s.findOrphans();
    		s.showFooter();
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
