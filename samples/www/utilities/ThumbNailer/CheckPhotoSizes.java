import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.util.Date;

import javax.imageio.ImageIO;


public class CheckPhotoSizes
{
	private static final String SENTINEL = "INSERT INTO `Photos` VALUES ('";
	private int _errs = 0;
	private int _checked = 0;
	
	private final DateFormat sdf = DateFormat.getTimeInstance();

	private void doIt(String sqlFilename, String wwwDirName) throws Exception
	{
		BufferedReader br = new BufferedReader(new FileReader(sqlFilename));
		String rec;
		while ((rec = br.readLine()) != null)
		{
			if (rec.startsWith(SENTINEL))
			{
				String[] pieces = rec.substring(SENTINEL.length()).replaceAll("'","").split(",");
				String key = pieces[1];
				String photoName = pieces[0] + '/' + key + pieces[2];
				int expectedWidth = Integer.parseInt(pieces[7]);
				int expectedHeight = Integer.parseInt(pieces[8]);
				
				_checked++;
				if ((_checked % 1000) == 0)
				{
					Date now = new Date();
					System.err.println(sdf.format(now) + " " + _checked + " " + photoName);
				}

				BufferedImage img = ImageIO.read(new File(wwwDirName + photoName));
	            int actualWidth = img.getWidth();
	            int actualHeight = img.getHeight();
	            
	            if (actualWidth != expectedWidth || actualHeight != expectedHeight)
	            {
	            	System.out.println("UPDATE Photos SET Width=" + actualWidth + ", Height=" + actualHeight +
	            			" WHERE Photo='" + key + "';");
	            	_errs++;
	            }
	            
	            // Now check the thumbnail size
	            photoName = pieces[0] + "/thumbs/" + key + "_thumb" + pieces[2];
				expectedWidth = Integer.parseInt(pieces[9]);
				expectedHeight = Integer.parseInt(pieces[10]);
				
				img = ImageIO.read(new File(wwwDirName + photoName));
	            actualWidth = img.getWidth();
	            actualHeight = img.getHeight();
	            
	            if (actualWidth != expectedWidth || actualHeight != expectedHeight)
	            {
	            	System.out.println("UPDATE Photos SET ThumbWidth=" + actualWidth + ", ThumbHeight=" + actualHeight +
	            			" WHERE Photo='" + key + "';");
	            	_errs++;
	            }
			}
		}
		br.close();
	}
	
	public static void main(String[] args)
	{
		if (args.length != 2)
		{
			System.out.println("Usage: c:\\www\\family.sql c:/www");
			System.exit(1);
		}
		
		CheckPhotoSizes cps = new CheckPhotoSizes();
		try
		{
			String sql = args[0];
			String root = args[1].replaceAll("\\\\", "/");
			cps.doIt(sql, root);
			System.err.println("Checked=" + cps._checked + " Errors=" + cps._errs);
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}
