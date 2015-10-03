/*
 * Steve O'Hara
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class ImageStitcher
{
    private boolean do2(String[] inname, String outname)
    {
    	try
    	{
	    	File inp[] = new File[2];
	    	BufferedImage inimg[] = new BufferedImage[2];
	    	int w[] = new int[2];
	    	int h[] = new int[2];
	
	    	for (int i = 0; i < 2; i++)
	    	{
		    	inp[i] = new File(inname[i]);
		        if (!inp[i].exists())
		        {
		            System.err.println("File " + inname[i] + " does not exist");
		            return false;
		        }
		        
				inimg[i] = ImageIO.read(inp[i]);
				if (inimg[i] == null) throw new Exception("Unable to read " + inname[i]);
				w[i] = inimg[i].getWidth();
				h[i] = inimg[i].getHeight();
	    	}
	    	
			File outp = new File(outname);
			
			int neww, newh;
			boolean wider = w[0] > h[0];
			if (wider)
			{
			    neww = Math.max(w[0], w[1]);
			    newh = h[0] + 1 + h[1];
			}
			else
			{
			    neww = w[0] + 1 + w[1];
			    newh = Math.max(h[0], h[1]);
			}
//            Image img1 = inimg[0].getScaledInstance(neww, newh, Image.SCALE_DEFAULT);
//            Image img2 = inimg[1].getScaledInstance(neww, newh, Image.SCALE_DEFAULT);

            int imageType = inimg[0].getType();
            BufferedImage outimg = new BufferedImage(neww, newh, imageType);
            Graphics g = outimg.createGraphics();
            
            // Clear background to be all white
            g.setColor(Color.white);
            g.fillRect(0, 0, neww, newh);

            // Draw separator line between the two images
            // And draw the images, center the smaller image
            g.setColor(Color.black);
            if (wider)
            {
                g.drawLine(0, h[0], neww, h[0]);
                g.drawImage(inimg[0], (neww-w[0])/2, 0, null);
                g.drawImage(inimg[1], (neww-w[1])/2, h[0]+1, null);
            }
            else
            {
                g.drawLine(w[0], 0, w[0], newh);
                g.drawImage(inimg[0], 0, (newh-h[0])/2, null);
                g.drawImage(inimg[1], w[0]+1, (newh-h[1])/2, null);
            }
            
            if (!ImageIO.write(outimg, "jpeg", outp))
            {
                System.err.println("Error writing to " + outp.getAbsolutePath());
                return false;
            }
            System.out.println("Wrote " + outname);
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
    	if (args.length != 3)
    	{
    		System.out.println("Usage in1 in2 out");
    		System.exit(0);
    	}
    	
        ImageStitcher is = new ImageStitcher();
        String[] innames = new String[] { args[0], args[1] };
        is.do2(innames, args[2]);
    }
}