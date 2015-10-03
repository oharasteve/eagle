/*
 * Steve O'Hara
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ThumbNailer
{
    private static int size = 100;
    private static String thumb = "_thumb";
    private static String newdir = "thumbs";
    private static String thumbmsg = "Thumbing";
    private static String caption = null;
    
    private static double fraction = 0;	// If zero, do normal scaling
    
    private boolean do1(String inname)
    {
        File inp = new File(inname);
        if (!inp.exists())
        {
            System.err.println("File " + inname + " does not exist");
            return false;
        }

        // Curse again for directories
        if (inp.isDirectory())
        {
            boolean ok = true;
            File[] jpgfiles = inp.listFiles();

            for (int i = 0; i < jpgfiles.length; i++)
            {
                File f = jpgfiles[i];
                if (f.isDirectory())
                {
                    if (f.getName().equalsIgnoreCase(newdir))
                    {
                        continue;
                    }
                    if (!do1(f.getAbsolutePath()))
                    {
                        ok = false;
                    }
                }

                String low = f.getName().toLowerCase();
                // Don't redo thumbnails!
                if (low.indexOf("_thumb") >= 0 || low.indexOf("thumb_") >= 0)
                {
                    continue;
                }

                if (!low.endsWith(".jpg") && !low.endsWith(".jpeg") && !low.endsWith(".png") &&
                		!low.endsWith(".gif")) // FAILS if low.endsWith(".tiff"). Need Java Advanced Imaging (JAI)
                {
                    continue;
                }

                // Don't do monster files
                if (f.length() > 25000000)
                {
                    System.err.println("Skipping " + f.length() + " byte file: " + f.getAbsolutePath());
                    continue;
                }

                if (!do1(f.getAbsolutePath()))
                {
                    ok = false;
                }
            }

            return ok;
        }

        int dot = inname.lastIndexOf('.');
        if (dot < 0)
        {
            dot = inname.length();
        }
        int slash = inname.lastIndexOf('\\');
        if (slash < 0)
        {
            slash = -1;
        }

        try
        {
			String outname = inname.substring(0, slash+1) + newdir + "\\" +
			inname.substring(slash+1, dot) + thumb + inname.substring(dot);
			File outp = new File(outname);
			if (outp.exists())
			{
				return true;	// Already exists, don't overwrite it
			}
			
			BufferedImage inimg = ImageIO.read(inp);
			if (inimg == null) throw new Exception("Unable to read " + inname);
			int w = inimg.getWidth();
			int h = inimg.getHeight();

			if (fraction > 0.0)
			{
				size = (int) Math.round(fraction * Math.min(w, h));
			}
	
		    int neww = size;
		    int newh = size;
		    if (w < h)
            {
    	    	newh = (int) (1.0 * size * h / w);
		    }
		    else
            {
    	    	neww = (int) (1.0 * size * w / h);
		    }

			if (w <= size || h <= size)
			{
			    if (thumb.equals(""))	// -sameName
				{
					return true;	// Don't bother, already small enough
				}
			    neww = w;
			    newh = h;
			}

			String dirname = inname.substring(0, slash+1) + newdir;
			File dirp = new File(dirname);
			if (!dirp.exists())
			{
				System.out.println("Creating directory " + dirname);
				dirp.mkdir();	// Create the directory
			}

            Image img = inimg.getScaledInstance(neww, newh, Image.SCALE_DEFAULT);

            System.out.println(thumbmsg + " " + inname + " (" + w + "x" + h + ")");
            System.out.println("      to " + outname + " (" + neww + "x" + newh + ")");

            // Pretty goofy!
            int imageType = inimg.getType();
            if (outname.endsWith(".png")) imageType = BufferedImage.TYPE_BYTE_INDEXED;
            BufferedImage outimg = new BufferedImage(neww, newh, imageType);
            Graphics g = outimg.createGraphics();
            g.drawImage(img, 0, 0, null);
            
            // Caption?
            if (caption != null)
            {
            	int fontsize = 24;
            	Rectangle2D rect = null;
            	while (fontsize >= 8)		// Make sure it is not too wide
            	{
                	Font font = new Font("fixed", Font.BOLD, fontsize);
                	g.setFont(font);
                	FontMetrics fm = g.getFontMetrics(font);
                	rect = fm.getStringBounds(caption, g);
                	if (rect.getWidth() < neww) break;
                	
            		fontsize = (3 * fontsize) / 4;	// Still too wide :(
            	}
            	g.setXORMode(Color.BLACK);
            	g.drawString(caption, (int)(neww - rect.getWidth()) / 2, newh - 5);
            }
            
            if (!ImageIO.write(outimg, "jpeg", outp))
            {
                System.err.println("Error writing to " + outp.getAbsolutePath());
                return false;
            }
        }
        catch (Exception ex)
        {
        	System.err.println("Processing " + inname);
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args)
    {
        ThumbNailer tn = new ThumbNailer();

        if (args.length == 0)
        {
            System.out.println("Usage: ThumbNailer -size 100 -caption 'Hi' -sameName img1.jpg img2.jpg ...");
            System.out.println("       creates files img1_thumb.jpg img2_thumb.jpg ...");
            System.exit(0);
        }

        for (int i = 0; i < args.length; i++)
        {
			if (args[i].equalsIgnoreCase("-size") && i < args.length-1)
			{
				i++;
				size = Integer.parseInt(args[i]);
			}
			else if (args[i].equalsIgnoreCase("-fraction") && i < args.length-1)
			{
				i++;
				fraction = Double.parseDouble(args[i]);
			}
			else if (args[i].equalsIgnoreCase("-caption") && i < args.length-1)
			{
				i++;
				caption = args[i];
			}
			else if (args[i].equalsIgnoreCase("-sameName"))
			{
				thumb = "";
				newdir = "reduced";
                thumbmsg = "Reducing";
			}
			else tn.do1(args[i]);
        }
    }
}