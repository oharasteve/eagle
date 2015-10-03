// Original author: Steven A. O'Hara, Sep 5, 2014

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;

public class PicCaptions
{
	private static final String NAME = "name";
	private static final String CAPTION = "caption";
	
	private static final String[] SKIPS = new String[] {
		"2008-Bhupal",
		"2008-galapagos",
		"2008-Nice",
		"2008-prague",
		"2009_chris_erin",
		"2010_Cinque_Terre",
		"2010-shang",
		"running_ribbon",
	};

	private int _count = 0;
	private int _updated = 0;
	
	private static final int TOP_SIZE = 16;
	private static final int BOTTOM_SIZE = 24;
	
	private static final double MAX_HEIGHT = 600;
	
	private static final Font TOP_FONT = new Font(Font.DIALOG, Font.BOLD, 16);
	private static final Font[] FONT_LIST= new Font[] {
		new Font(Font.DIALOG, Font.BOLD, 24),
		new Font(Font.DIALOG, Font.BOLD, 18),
		new Font(Font.DIALOG, Font.BOLD, 14),
		new Font(Font.DIALOG, Font.BOLD, 12),
		new Font(Font.DIALOG, Font.BOLD, 10),
		new Font(Font.DIALOG, Font.BOLD, 8),
		new Font(Font.DIALOG, Font.BOLD, 7)
	};
	
	private TreeMap<String, String> _listPix = new TreeMap<String, String>();
	
	private void doIt(String inName, String outDirName, String picDirName) throws Exception
	{
		String prefix = picDirName + "\\";
		
		File outDir = new File(outDirName);
		if (!outDir.exists())
		{
			System.out.println("Creating directory " + outDirName);
			outDir.mkdirs();
			if (!outDir.exists())
			{
				throw new RuntimeException("Unable to create " + outDirName);
			}
		}
		
		String startName = "<" + NAME + ">";
		int startNameLen = startName.length();
		String endName = "</" + NAME + ">";
		int endNameLen = endName.length();
		
		String startCaption = "<" + CAPTION + ">";
		int startCaptionLen = startCaption.length();
		String endCaption = "</" + CAPTION + ">";
		int endCaptionLen = endCaption.length();
		
		BufferedReader br = new BufferedReader(new FileReader(inName));
		try
		{
			String rec = br.readLine();
			rec = br.readLine();
			if (!rec.equals("<coolcaptions>"))
			{
				throw new RuntimeException("Wrong file, expected <coolcaptions>, not " + rec);
			}
			
			String file = null;
			int cnt = 0;
			while ((rec = br.readLine()) != null)
			{
				String line = rec.trim(); 
				if (line.startsWith(startName + prefix))
				{
					file = line.substring(startNameLen, line.length()-endNameLen);
					
					// Skip some directories
					for (String skip : SKIPS)
					{
						if (line.indexOf(skip) >= 0)
						{
							file = null;
							break;
						}
					}
				}
				else if (line.startsWith(startCaption))
				{
					String caption = line.substring(startCaptionLen, line.length()-endCaptionLen);
					if (file != null)
					{
						cnt++;
						if (cnt % 1000 == 0)
						{
							System.out.print('.');
							System.out.flush();
						}
						
						String imageName = picDirName + "\\" + file.substring(prefix.length()).replaceAll("\\\\", "/");
						File inImage = new File(imageName);
						if (!inImage.exists()) throw new RuntimeException("Unable to find image " + file);
						_listPix.put(imageName, caption.replaceAll("&quot;", "\"").replaceAll("&amp;", "&"));
					}
					file = null;
				}
			}
			System.out.println(" pictures = " + cnt);
		}
		finally
		{
			br.close();
		}
		
		// Now they should be all sorted by folder
		for (Map.Entry<String, String> pic : _listPix.entrySet())
		{
			doOne(pic.getKey(), outDirName, picDirName, pic.getValue());
		}
		
		System.out.println("Total updated = " + _updated);
	}
	
	private final DateFormat sdf = DateFormat.getTimeInstance();
	
	private void doOne(String imageName, String topDirName, String picDirName, String caption) throws Exception
	{
		String prefix = picDirName + "\\";

		_count++;
		if ((_count % 1000) == 0)
		{
			Date now = new Date();
			String shortName = imageName.substring(prefix.length());
			System.out.println(sdf.format(now) + " (" + _count + ") " + shortName.replaceAll("/", "\\\\"));
		}
		
		String imageDir = imageName.substring(picDirName.length());
		int firstSlash = imageDir.indexOf('/');
		String outDirName = topDirName + imageDir.substring(0, firstSlash+1);
		int lastSlash = imageName.lastIndexOf('/');
    	String outName = outDirName + imageName.substring(lastSlash+1);
    	File outDir = new File(outDirName);
    	if (!outDir.exists())
    	{
    		Date now = new Date();
			System.out.println(now.toString() + " Creating directory " + outDirName.replaceAll("/", "\\\\"));
    		outDir.mkdirs();
    	}
    	
    	BufferedImage inimg = ImageIO.read(new File(imageName));
    	int w = inimg.getWidth();
    	int h = inimg.getHeight();
    	
    	int topMargin = TOP_SIZE + 1;
    	int bottomMargin = BOTTOM_SIZE + 1;
    	
		File outFile = new File(outName);
		int neww = w;
		int newh = h;
		
		if (h > MAX_HEIGHT)
		{
			double ratio = MAX_HEIGHT / h;
			neww = (int) (w * ratio);
			newh = (int) (h * ratio);
		}

    	Image img = inimg.getScaledInstance(neww, newh, Image.SCALE_DEFAULT);
        int imageType = inimg.getType();
        if (outName.endsWith(".png")) imageType = BufferedImage.TYPE_BYTE_INDEXED;
        BufferedImage outimg = new BufferedImage(neww, newh + topMargin + bottomMargin, imageType);
        Graphics2D g = outimg.createGraphics();
        
        // Clear background to be all white
        g.setColor(Color.white);
        g.fillRect(0, 0, neww, newh + topMargin + bottomMargin);

        // Write the imageName at the top
        {
            g.setColor(Color.black);
            g.setFont(TOP_FONT);
	        TextLayout topLayout = new TextLayout(imageName, TOP_FONT, g.getFontRenderContext());
	        double topWidth = topLayout.getBounds().getWidth();
	        int topLeft = neww - (int) topWidth - 3;	// Right justify
	        if (topWidth < neww)
	        {
	        	topLeft = (int) (neww - topWidth) / 2;		// Fits, center it
	        }
	        g.drawString(imageName.replaceAll("/", "\\\\"), topLeft, topMargin - topLayout.getDescent());
        }

        // Copy the image
        g.drawImage(img, 0, topMargin, null);
        
        // Write the caption at the bottom
        g.setColor(Color.red);
        int fonts = FONT_LIST.length;
        Font font = null;
        int bottomLeft = 0;		// Left justify
        int bottomPos = 0;
        for (int i = 0; i < FONT_LIST.length; i++)
        {
	        TextLayout bottomLayout = new TextLayout(caption, FONT_LIST[i], g.getFontRenderContext());
	        double bottomWidth = bottomLayout.getBounds().getWidth();
	        if (bottomWidth < neww || i == fonts-1)
	        {
	        	font = FONT_LIST[i];
		        if (bottomWidth < neww)
		        {
		        	bottomLeft = (int) (neww - bottomWidth) / 2;	// Fits, center it
		        }
		        bottomPos = newh + topMargin + bottomMargin - (int) bottomLayout.getDescent();
	        	break;
	        }
        }
        g.setFont(font);
        g.drawString(caption, bottomLeft, bottomPos);
        
        // Read the old image file, if it is there
        // If it exists, and it is identical, don't bother writing again
        boolean writeIt = true;
        if (outFile.exists())
        {
	        try
	        {
	        	// Waste a write! But 99% of the time, it will be the same so it won't write it twice.
	        	File tempFile = new File("C:\\temp\\delimg.jpg");
	        	ImageIO.write(outimg, "jpeg", tempFile);
	        	BufferedImage tempNewImg = ImageIO.read(tempFile);
	        	BufferedImage tempOldImg = ImageIO.read(outFile);
		        if (same(tempOldImg, tempNewImg)) writeIt = false;
	        }
	        catch (Exception ex)
	        {
	        	//ex.printStackTrace();
	        	writeIt = true;
	        }
        }
        
        // Write the new image, with caption
        if (writeIt)
        {
	        if (!ImageIO.write(outimg, "jpeg", outFile))
	        {
	            throw new RuntimeException("Error writing to " + outFile.getAbsolutePath());
	        }
	        System.out.println("Updating " + outName.replaceAll("/", "\\\\") + " (" + neww + "x" + newh + ")");
	        _updated++;
        }
	}

	private boolean same(BufferedImage imgA, BufferedImage imgB)
	{
	    // The images must be the same size.
	    int width = imgA.getWidth();
        int height = imgA.getHeight();
	    if (width != imgB.getWidth() || height != imgB.getHeight())
	    {
	    	return false;
	    }

        // Loop over every pixel.
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                // Compare the pixels for equality.
            	int rgbA = imgA.getRGB(x, y);
            	int rgbB = imgB.getRGB(x, y);
                if (rgbA != rgbB)
                {
                	// It is lossy -- so compare to see if the colors are "close"
                	if (!similar((rgbA >> 16) & 0xFF, (rgbB >> 16) & 0xFF) ||	// Red
                		!similar((rgbA >> 8) & 0xFF, (rgbB >> 8) & 0xFF) ||		// Green
                		!similar((rgbA >> 0) & 0xFF, (rgbB >> 0) & 0xFF))		// Blue
            		{
//                    	System.out.println("Pixel " + x + " of " + width + ", " + y + " of " + height + " " +
//                    			hex(imgA.getRGB(x, y)) + " != " + hex(imgB.getRGB(x, y)));
                        return false;
            		}
                }
            }
        }
        
	    return true;
	}
	
	// Account for images being "lossy" when persisted
	private static final int THRESHOLD = 10;	// of 256
	
	private boolean similar(int a, int b)
	{
		return a - b < THRESHOLD && b - a < THRESHOLD;
	}
	
//	private static String hex(int num)
//	{
//		return String.format("%08X", num);
//	}
	
    public static void main(String[] args)
    {
    	if (args.length < 3)
    	{
    		System.out.println("Usage in.ini outDir picDir");
    		System.exit(0);
    	}
    	
    	PicCaptions pc = new PicCaptions();
    	try
    	{
            pc.doIt(args[0], args[1], args[2]);
    	}
    	catch (Exception ex)
    	{
    		ex.printStackTrace();
    	}
    }
}
