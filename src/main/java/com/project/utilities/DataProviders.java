package com.project.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DataProviders {
	
	private static final String FILE_PATH = "src/test/resources/TestData.xlsx";
	
    @DataProvider(name = "LoginData")
    public static Object[][] getLoginDataFromExcel(Method method) {
    	List<Map<String, String>> data = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheet("Login"); 
            Iterator<Row> rows = sheet.iterator();
            
            // Read header row
            Row headerRow = rows.next();
            List<String> headers = new ArrayList<>();
            headerRow.forEach(cell -> headers.add(cell.getStringCellValue()));

            while (rows.hasNext()) {
                Row row = rows.next();
                Map<String, String> rowData = new HashMap<>();
                for (int i = 0; i < headers.size(); i++) {
                    Cell cell = row.getCell(i);
                    if (cell == null) {
                        rowData.put(headers.get(i), "");
                    } else {
                        switch (cell.getCellType()) {
                            case STRING:
                                rowData.put(headers.get(i), cell.getStringCellValue());
                                break;
                            case NUMERIC:
                                rowData.put(headers.get(i), String.valueOf(cell.getNumericCellValue()));
                                break;
                            case BOOLEAN:
                                rowData.put(headers.get(i), String.valueOf(cell.getBooleanCellValue()));
                                break;
                            case FORMULA:
                                rowData.put(headers.get(i), cell.getCellFormula());
                                break;
                            default:
                                rowData.put(headers.get(i), "");
                        }
                    }
                }
                data.add(rowData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert List<Map<String, String>> to Object[][] for DataProvider
        Object[][] dataArray = new Object[data.size()][1];
        for (int i = 0; i < data.size(); i++) {
            dataArray[i][0] = data.get(i);
        }

        return dataArray;
    }    
}
