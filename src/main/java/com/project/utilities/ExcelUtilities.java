package com.project.utilities;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.project.base.BaseClass;

public class ExcelUtilities extends BaseClass {
	
//	Excel Utility
	String filePath = "src/test/resources/TestData.xlsx";
	public XSSFWorkbook wb;
	XSSFSheet sheet;
	HashMap<String, Integer> colNums = null;
	FileInputStream fis;

	public ExcelUtilities() {
		try {

			fis = new FileInputStream(filePath);
			wb = new XSSFWorkbook(fis);
			sheet = wb.getSheet("stateside_Data");
			populateColNums();
		} catch (Exception e) {

		}
	}

	public void readSheetData() {
		Iterator<Row> rows = sheet.iterator();
		String value = "";
		while (rows.hasNext()) {
			Row curRow = rows.next();
			Iterator<Cell> cells = curRow.cellIterator();
			while (cells.hasNext()) {
				Cell curCell = cells.next();
				CellType cType = curCell.getCellType();
				if (cType == CellType.STRING) {
					value = curCell.getStringCellValue();
				}
			}

		}
	}

	public void populateColNums() {
		colNums = new HashMap<String, Integer>();
		int colmIndex = 0;
		Row row = sheet.getRow(0);
		Iterator<Cell> cells = row.cellIterator();
		while (cells.hasNext()) {
			Cell cell = cells.next();
			String colName = cell.getStringCellValue();
			colNums.put(colName, colmIndex);
			colmIndex++;
		}
	}

	public int getColNumber(String colName) {
		return colNums.get(colName);
	}

	public String getCellValue(int rowNum, String colName) {
		String ret = "";
		int colNum = getColNumber(colName);
		ret = getCellData(rowNum, colNum);
		return ret;

	}

	public String getCellData(int rowNum, int colNum) {
		String ret = "";
		try {
			Row row = sheet.getRow(rowNum);
			Cell cell = row.getCell(colNum);
			if (cell.getCellType() == CellType.STRING) {
				ret = cell.getStringCellValue();
			}

		} catch (Exception e) {

		}
		return ret;
	}

	public void close() {
		try {
			if (fis != null) {
				fis.close();
				wb.close();
			}
		} catch (Exception e) {

		}
	}
}