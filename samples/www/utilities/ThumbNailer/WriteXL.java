// Original author: Steven A. O'Hara, Sep 15, 2013

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteXL {
	private XSSFWorkbook xlWB;
	private FileOutputStream xlFile;
	private XSSFSheet[] sheets;
	private int[] numEntries;
	private int[] numColumns;

	private CellStyle fixed;
	private CellStyle headerStyle;

	public void openXL(String fileName, int nSheets) throws IOException {
		xlFile = new FileOutputStream(fileName);
		xlWB = new XSSFWorkbook();

		fixed = xlWB.createCellStyle();
		fixed.setAlignment(CellStyle.ALIGN_LEFT);
		Font fixedFont = xlWB.createFont();
		fixedFont.setFontName("courier");
		fixed.setFont(fixedFont);

		headerStyle = xlWB.createCellStyle();
		Font boldFont = xlWB.createFont();
		boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		headerStyle.setFont(boldFont);
		headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
		headerStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		headerStyle.setWrapText(true);
		
		sheets = new XSSFSheet[nSheets];
		numEntries = new int[nSheets];
		numColumns = new int[nSheets];
	}
	
	public int createSheet(int indx, String name, String... headers) {
		// Write the Responses header row
		sheets[indx] = xlWB.createSheet(name);
		sheets[indx].createFreezePane(1, 1);
		XSSFRow row = sheets[indx].createRow(0);	// 0 = header row
		row.setHeight((short) (2 * row.getHeight()));		// Double height
		
		int col = 0;
		for (String hdr : headers)
		{
			XSSFCell cell = row.createCell(col);
			cell.setCellValue(hdr);
			cell.setCellStyle(headerStyle);
			col++;
		}
		numColumns[indx] = col;
		
		return indx;
	}
	
	public void addRow(int indx, String... values) {
		int n = ++numEntries[indx];
		XSSFRow row = sheets[indx].createRow(n);
		
		int col = 0;
		for (String value : values) {
			XSSFCell cell = row.createCell(col);
			cell.setCellValue(value);
			cell.setCellStyle(fixed);
			col++;
		}
	}
	
	public void close() throws IOException
	{
		// Make sheets look nice
		for (int indx = 0; indx < sheets.length; indx++) {
			for (int col = 0; col < numColumns[indx]; col++) {
				sheets[indx].autoSizeColumn(col);
			}
		}
		
		xlWB.write(xlFile);
		xlFile.close();
	}
}
