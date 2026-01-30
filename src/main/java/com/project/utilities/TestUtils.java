package com.project.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.project.base.BaseClass;

public class TestUtils extends BaseClass {
	
	public static HashMap<String, Integer> colNums;
	
	public static String getTimeStamp() {
		Date currentDate = new Date();
		String timestamp = currentDate.toString().replace(" ", "-").replace(":", "-");
		return timestamp;
	}
	
	/**
	 * Utility = Screenshot
	 * */

	public static void getScreenshot() throws IOException {
		Date currentDate = new Date();
		String screenshotFilename = currentDate.toString().replace(" ", "-").replace(":", "-");
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File(".//screenshot//"+ screenshotFilename +".png")); 
	}
	
	public static String getScreenshotExtentReport(String dirName) throws IOException {
		String absolutePathScreen = "";
		screenshotCount++;
		try {
	        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	        String screenshotName = dirName + "/" + screenshotCount + ".png";
	        FileUtils.copyFile(screenshot, new File(screenshotName));
	        
	        File destFile =  new File(screenshotName);
	        absolutePathScreen = destFile.getAbsolutePath();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		return absolutePathScreen;
		
//		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); 
//		File destFile =  new File("src/../reportScreenshot/images"+ System.currentTimeMillis() +".png");
//		String absolutePathScreen = destFile.getAbsolutePath();
//		FileUtils.copyFile(srcFile,destFile);
		
	}
	
	/**
	 * Utility = Delete existing file
	 * */
	public static void deleteExistingFile(String expectedFilename) {
		String dirName = System.getProperty("user.home") + File.separator + "Downloads";
		String actualFileName;
		File dirDownloads = new File(dirName);
		File[] dirFiles = dirDownloads.listFiles();
		for (File file : dirFiles) {
			actualFileName = file.getName();
			if (actualFileName.contains(expectedFilename)) {
				file.delete();
			}
		}
	}
	
	/**
	 * Utility = Excel
	 * */
	
	public static Object[][] getExcelData(String filePath, String sheetName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(filePath));
        Workbook workbook = WorkbookFactory.create(fileInputStream);
        Sheet sheet = workbook.getSheet(sheetName);

        List<Object[]> data = new ArrayList<>();
        Iterator<Row> rowIterator = sheet.rowIterator();

        // Skip the header row
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            int cellCount = row.getPhysicalNumberOfCells();
            Object[] rowData = new Object[cellCount];

            for (int i = 0; i < cellCount; i++) {
                rowData[i] = row.getCell(i).toString();
            }

            data.add(rowData);
        }

        workbook.close();
        fileInputStream.close();

        return data.toArray(new Object[0][]);
    }
	
	public static String getStringDataFromExcel(String filePath, String sheetName, int rowNum, int cellNum) throws Exception {
		Sheet sheet = WorkbookFactory.create(new FileInputStream(filePath)).getSheet(sheetName);
		Cell cell = sheet.getRow(rowNum).getCell(cellNum);
		String strCellValue = "";
		try {
			strCellValue = cell.getStringCellValue();			
		}catch(NullPointerException e) {
			strCellValue = "";
		}
		return strCellValue;
	}
	
	public static int getColNumber(String filePath, String sheetName, String colName) throws Exception {
		Sheet sheet = WorkbookFactory.create(new FileInputStream(filePath)).getSheet(sheetName);
		colNums = new HashMap<String, Integer>();
		int colIndex = 0;
		Row row = sheet.getRow(0);
		Iterator<Cell> cells = row.cellIterator();
		
		while(cells.hasNext()) {
			Cell cell = cells.next();
			String currColName = cell.getStringCellValue();
			colNums.put(currColName, colIndex);
			if(currColName.equals(colName)) {
				break;
			}
			colIndex++;
		}
		return colNums.get(colName);
	}
	
	public static int getRowNumber(String filePath, String sheetName, String cellName, int colNum) throws Exception {
		Sheet sheet = WorkbookFactory.create(new FileInputStream(filePath)).getSheet(sheetName);
		int rowIndex = 0;
		for (Row row: sheet) {
			Cell cell = row.getCell(colNum);
			if(cell!=null && cell.getCellType() == CellType.STRING) {
				if((cell.getStringCellValue().trim()).equalsIgnoreCase(cellName)){
					break;
				}
			}
			rowIndex++;			
		}
		return rowIndex;
	}
	
	public static String getStringDataFromExcelByColName(String filePath, String sheetName, int rowNum, String colName) throws Exception {
		Sheet sheet = WorkbookFactory.create(new FileInputStream(filePath)).getSheet(sheetName);
		int colNum = getColNumber(filePath,sheetName,colName);
		Cell cell = sheet.getRow(rowNum).getCell(colNum);
		return cell.getStringCellValue();
	}
	
	public static double getNumericDataFromExcel(String filePath, String sheetName, int rowNum, int cellNum) throws Exception {
		Sheet sheet = WorkbookFactory.create(new FileInputStream(filePath)).getSheet(sheetName);
		Cell cell = sheet.getRow(rowNum).getCell(cellNum);
		return cell.getNumericCellValue();
	}
	
	public static boolean getBooleanDataFromExcel(String filePath, String sheetName, int rowNum, int cellNum) throws Exception {
		Sheet sheet = WorkbookFactory.create(new FileInputStream(filePath)).getSheet(sheetName);
		Cell cell = sheet.getRow(rowNum).getCell(cellNum);
		return cell.getBooleanCellValue();
	}
	
	public static String writeDataInExcel(String filePath, String sheetName, int rowNum, int cellNum, String data) throws Exception {
		FileInputStream fin = new FileInputStream(filePath);
		Workbook workbook = WorkbookFactory.create(fin);
		workbook.getSheet(sheetName).getRow(rowNum).createCell(cellNum).setCellValue(data);
		workbook.write(new FileOutputStream(filePath));
		workbook.close();
		return "Data written successfully to the Excel file";
	}
	
	public static boolean isExcelFileContainsData(String filePath, String sheetName) throws Exception {
		Sheet sheet = WorkbookFactory.create(new FileInputStream(filePath)).getSheet(sheetName);
		if (sheet.getPhysicalNumberOfRows() != 0) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Utility = CSV
	 * */

}