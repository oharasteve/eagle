/*
 * Steve O'Hara
 */

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class FixSize
{
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
                    if (f.getName().equalsIgnoreCase("archive"))
                    {
                        continue;
                    }
                    if (!do1(f.getAbsolutePath()))
                    {
                        ok = false;
                    }
                }

                String low = f.getName().toLowerCase();
                // Don't mess with thumbnails!
                if (low.indexOf("_thumb") >= 0 || low.indexOf("thumb_") >= 0)
                {
                    continue;
                }

                if (!low.endsWith(".jpg") && !low.endsWith(".jpeg"))
                {
                    continue;
                }

                if (!do1(f.getAbsolutePath()))
                {
                    ok = false;
                }
            }

            return ok;
        }

        try
        {
            BufferedImage inimg = ImageIO.read(inp);
            int w = inimg.getWidth();
            int h = inimg.getHeight();

            String canon = inp.getCanonicalPath();
            int dot = canon.lastIndexOf('.');
            int slash = canon.lastIndexOf('\\');

            // Read the thumbnail size as well
            int pos1 = inname.lastIndexOf('.');
            int pos2 = inname.lastIndexOf('\\');
            String tinname = inname.substring(0, pos2) + "\\thumbs\\" + inname.substring(pos2+1, pos1) +
                "_thumb" + inname.substring(pos1);
            //System.out.println("thumbnail = " + tinname);
            File tinp = new File(tinname);
            BufferedImage tinimg = ImageIO.read(tinp);
            int tw = tinimg.getWidth();
            int th = tinimg.getHeight();

            System.out.println("update Photos set Width=" + w + ", Height=" + h +
                ", ThumbWidth=" + tw + ", ThumbHeight=" + th + " where Photo='" +
            	canon.substring(slash+1,dot) + "';");
        }
        catch (Exception ex)
        {
            System.err.println("Error on " + inname);
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args)
    {
        FixSize fs = new FixSize();

        if (args.length == 0)
        {
            System.out.println("Usage: FixSize img1.jpg img2.jpg ...");
            System.exit(0);
        }

        for (int i = 0; i < args.length; i++)
        {
            fs.do1(args[i]);
        }
    }
}