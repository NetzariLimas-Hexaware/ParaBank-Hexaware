package Hexaware.data;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataReader {
	DataFormatter formatter = new DataFormatter();
	public Object[][] DataRetrieveFromExcel(String path) throws IOException {		
		FileInputStream fis = new FileInputStream(path);
		@SuppressWarnings("resource")
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(0);
		
		int rowCount = sheet.getPhysicalNumberOfRows();
		XSSFRow row = sheet.getRow(0);
		int columnCount = row.getLastCellNum();
		
		Object[][] data = new Object[rowCount-1][columnCount];
		
		for(int i=0;i<rowCount-1;i++) {
//			System.out.println(rowCount-1);
			row = sheet.getRow(i+1);
			for(int j=0;j<columnCount;j++) {
				XSSFCell cell = row.getCell(j);	
				cell.setCellType(CellType.STRING);
//				data[i][j] = formatter.formatCellValue(cell); // This don't evaluate cells with formula
				data[i][j] = cell.getStringCellValue();
//				System.out.println(data[i][j]);
			}
		}
		
		return data;
	}
}
