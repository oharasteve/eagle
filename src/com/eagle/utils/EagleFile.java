// Copyright Eagle Legacy Modernization, 2010-date
// Original author: Shaddock Heath, Aug 20, 2011

package com.eagle.utils;

import java.io.File;

import com.eagle.project.EagleProject;
import com.eagle.project.ProjectEntry;

public class EagleFile {
	private String filename = null;
	private String path = null;
	
	/**
	 * Create an EagleFile object from a literal path + filename
	 * @param filenameandpath
	 */
	public EagleFile(String filenameandpath)
	{
		setFilenameAndPath(filenameandpath);
	}
	
	/**
	 * Create an eagle file object from a ProjectEntry.sourceFile
	 * @param entry
	 */
	public EagleFile(ProjectEntry entry) {
		setFilenameAndPath(entry.sourceFile);
	}
	
	private void setFilenameAndPath(String filenameAndPath0)
	{
		String filenameAndPath = EaglePath.normalizeSlashes(filenameAndPath0);
		int pos = filenameAndPath.lastIndexOf("/");
		if (pos >= 0)
		{
			path = filenameAndPath.substring(0, pos);
			filename = filenameAndPath.substring(pos + 1);
		}
		else
		{
			path = "";
			filename = filenameAndPath;
		}
	}
	
	/**
	 * Returns the file extension, or "" if there is no extension
	 * @return
	 */
	public String getExtension()
	{
		String ext = "";
		int pos = filename.lastIndexOf('.');
		if (pos > 0) ext = filename.substring(pos + 1);
		return ext;
	}
	
	/**
	 * Returns the filename w/o an extension
	 * @return
	 */
	public String getBaseName()
	{
		int pos = filename.lastIndexOf('.');
		if (pos >= 0) 
		{ 
			return filename.substring(0, pos);
		}
		return filename;
	}
	
	/**
	 * returns the filename + ext part of the file
	 * @return
	 */
	public String getFilename()
	{
		return filename;
	}
	
	/**
	 * returns the path part of the file
	 * @return
	 */
	public String getPath()
	{
		return path;
	}

	/**
	 * Returns a File object based upon the EagleFile path and filename and the Project Root
	 * @param proj
	 * @return
	 */
	public File getFileObject(EagleProject proj) {
		return new File(EaglePath.combinePaths(proj._sourceBase, path, filename));
	}
}
