/**
 * This software is free. Use at your own risk.
 * Copyright 2005 by Harald Vogt <vogt@inf.ethz.ch>
 */

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;

import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.poifs.filesystem.DocumentNode;
import org.apache.poi.poifs.filesystem.Entry;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.poifs.property.DocumentProperty;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGDecodeParam;
import com.sun.image.codec.jpeg.JPEGImageDecoder;

public class ThumbsReader {
	POIFSFileSystem fs;

	int counter = 0;

	String outputDir = ".";

	boolean DEBUG = false;

	public ThumbsReader(InputStream stream, String outDir) throws IOException {
		fs = new POIFSFileSystem(stream);
		outputDir = outDir;
	}

	public String space(int n) {
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < n; i++)
			s.append(' ');
		return s.toString();
	}

	public void handleProperty(DocumentProperty prop) {
		System.out.println("name=" + prop.getName());
		System.out.println("desc=" + prop.getShortDescription());
		System.out.println("startblock=" + prop.getStartBlock());
		System.out.println("storageclass=" + prop.getStorageClsid());
	}

	public void showDir(int indent, DirectoryNode d) {
		if (DEBUG)
			System.out.println(d.getName());
		Iterator<Entry> it = d.getEntries();
		while (it.hasNext()) {
			Object element = it.next();
			if (element instanceof DirectoryNode) {
				if (DEBUG) {
					System.out.println(space(indent) + "[DIR] "
							+ ((DirectoryNode) element).getName());
				}
				showDir(indent + 4, (DirectoryNode) element);
			} else if (element instanceof DocumentNode) {

				DocumentNode n = (DocumentNode) element;

				if (DEBUG) {
					System.out.println(space(indent) + "[DOC]\nName: "
							+ n.getName() + "\nDesc: "
							+ n.getShortDescription() + "\nSize: "
							+ n.getSize() + " (0x"
							+ Integer.toHexString(n.getSize()) + ")");
				}

				if (DEBUG) {
					if (n.preferArray()) {
						for (Object obj : n.getViewableArray()) {
							System.out.println("> " + obj);
							if (obj instanceof DocumentProperty)
								handleProperty((DocumentProperty) obj);
						}
					} else {
						@SuppressWarnings("unchecked")
						Iterator<Object> itx = n.getViewableIterator();
						while (itx.hasNext()) {
							Object obj = itx.next();
							System.out.println("> " + obj);
							if (obj instanceof DocumentProperty)
								handleProperty((DocumentProperty) obj);
						}
					}
				}

				// try to decode it...
				try {
					DocumentInputStream is = fs.createDocumentInputStream(n
							.getName());

					// how to get information from the Catalog entry?
					/*
					 * if (n.getName().equals("5") ||
					 * n.getName().equals("Catalog")) { int m = is.available();
					 * for (int i = 0; i < m; i++) {
					 * System.out.print(Integer.toHexString(is.read()) + " "); }
					 * System.out.println(); continue; }
					 */
					int header_len = is.read();
					for (int i = 1; i < header_len; i++) {
						is.read();
					}
					// what's in the header?

					/*
					 * Code from http://forum.java.sun.com/thread.jspa?threadID=427883&tstart=30
					 */
					JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(is);
					JPEGDecodeParam param = JPEGCodec
							.getDefaultJPEGEncodeParam(4,
									JPEGDecodeParam.COLOR_ID_RGBA);
					decoder.setJPEGDecodeParam(param);
					BufferedImage originalBufferedImage = decoder
							.decodeAsBufferedImage();

					ImageIO.write(originalBufferedImage, "jpg", new File(
							outputDir + File.separator + "thumb-" + counter
									+ ".jpg"));
					counter++;

				} catch (Exception e) {
					// System.out.println(e.getMessage());
					// throw new RuntimeException(e.toString());
					// System.out.println("Couldn't decode file");
					// ignore exceptions
				}
			} else {
				if (DEBUG)
					System.out.println("+++++++++++++++ " + element.getClass());
			}
		}
	}

	public void showFS(POIFSFileSystem fs) {
		DirectoryNode d = (DirectoryNode) fs.getRoot(); // TODO: get rid of cast
		showDir(0, d);
	}

	public void makeJPG() throws IOException {
		showFS(fs);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String thumbsFile = "Thumbs.db";
		String outDir = ".";

		if (args.length > 0) {
			thumbsFile = args[0];
		}
		if (args.length > 1) {
			outDir = args[1];
		}
		try {
			InputStream stream = new FileInputStream(thumbsFile);
			ThumbsReader r = new ThumbsReader(stream, outDir);
			r.makeJPG();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
