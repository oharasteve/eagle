/*
 */

        // Curse again for directories
            for (int i = 0; i < jpgfiles.length; i++)
                String low = f.getName().toLowerCase();
                if (!low.endsWith(".jpg") && !low.endsWith(".jpeg") && !low.endsWith(".png") &&
                if (!do1(f.getAbsolutePath()))
            return ok;
        try
            // Read the thumbnail size as well
            //int dpi = 0;
            // Seeking DPI. No dice.
            // No luck here eithe
            // Third try is not a charm
            String canon = inp.getCanonicalPath();
            int photodir = canon.indexOf(root) + root.length();
            String caption = "";
            String dir = canon.substring(photodir,slash);
			String origPath = Integer.toString(yr);
			if (mon <= 4) origPath += 'a';
			else if (mon <= 8) origPath += 'b';
			else origPath += 'c';
            String original = "/originals/Year_" + yr + "/" + origPath + "/" + canon.substring(slash+1,dot) + canon.substring(dot);

            if (doDate)
            {
				System.out.println(left + original.replaceAll("_pdf.jpg", ".pdf") + ";" + mon + ";" + day + ";" + yr + ";" + right + ";" + caption);
			}
			else
			{
				System.out.println(left + right);
			}
        }
    public static void main(String[] args)
        if (args.length <= 1)
        for (int i = 1; i < args.length; i++)
			{
				pi.doDate = false;
			}
			else
			{
            	pi.do1(args[i]);
        }
}