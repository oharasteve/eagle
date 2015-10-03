// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Shaddock Heath, Aug 20, 2011

package com.eagle.utils;

import java.io.File;

public class EaglePath {
	/**
	 * Will return a full path containing all the passed in elements
	 * @param paths
	 * @return
	 */
	public static String combinePaths(String... paths) {
		//go through and add slashes to all path elements except the last one
		String result = normalizeSlashes(paths[0]);
		if (!result.isEmpty()) result = result.concat("/");
		// allow the first element to have a prefix slash
		
		for (int i=1; i < paths.length; i++ ) // remaining elements 
		{
			String p = EaglePath.normalizeSlashes(paths[i]);
			
			if (!p.isEmpty())
			{
				while (p.startsWith("/"))
				{
					p = p.substring(1);
				}
				if (!p.endsWith("/") 
						&& i < paths.length - 1) p = p.concat("/");
				result = result.concat(p);
			}
		}		
		return result;
	}
	
	
	/**
	 * Will return a path and filename with any \ changed to / in the path
	 * @param filenameAndPath
	 */
	public static String normalizeSlashes(String filenameAndPath)
	{
		return filenameAndPath.replace("\\","/");
	}


	/**
	 * Will create a directory if it doesn't exist
	 */
	public static void createDir(String path) {
		File outFile = new File(path);
		if (! outFile.exists()) outFile.mkdirs();
	}

}
