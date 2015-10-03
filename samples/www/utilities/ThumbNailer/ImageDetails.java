// Original author: Steven A. O'Hara, May 4, 2013

import java.io.File;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;

public class ImageDetails
{
	private void doit(String imageName)
	{
		try
		{
			File imageFile = new File(imageName);
			Metadata metadata = ImageMetadataReader.readMetadata(imageFile);
			for (Directory dir : metadata.getDirectories())
			{
				System.out.println("-->" + dir.getName());
				for (Tag tag : dir.getTags())
				{
					System.out.println("      " + tag.toString());
				}
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		ImageDetails x = new ImageDetails();
		for (String arg : args)
		{
			x.doit(arg);
		}
	}
}
