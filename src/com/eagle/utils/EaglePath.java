// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Shaddock Heath, Aug 20, 2011

package com.eagle.utils;

import java.io.File;

public class EaglePath
{
	/**
	 * Will return a full path containing all the passed in elements
	 * @param paths
	 * @return
	 */
	public static String combinePaths(String... paths)
	{
		//go through and add slashes to all path elements except the last one
		String result = normalizeSlashes(paths[0]);
		if (result == null) return null;
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
				if (!p.endsWith("/") && i < paths.length - 1) p = p.concat("/");
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
		if (filenameAndPath == null) return null;
		return filenameAndPath.replaceAll("\\\\", "/");
	}


	/**
	 * Will create a directory if it doesn't exist
	 * Returns true if the directory was created successfully
	 */
	public static boolean createDir(String path)
	{
		if (path == null) return false;
		File outFile = new File(path);
		if (outFile.exists()) return false;
		return outFile.mkdirs();
	}
	
	
	/**
	 * Remove a whole directory, one file at a time :(
	 * Returns true if the directory was deleted successfully
	 */
	public static boolean deleteDir(File path)
	{
		if (! path.exists()) return true;
		
		for (File f : path.listFiles())
		{
			if (f.isDirectory())
			{
				if (! deleteDir(f)) return false;
			}
			else
			{
				if (! f.delete()) return false;
			}
		}
		if (! path.delete()) return false;
		return true;
	}
}
