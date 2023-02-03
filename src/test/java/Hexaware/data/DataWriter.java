package Hexaware.data;

import java.io.FileOutputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataWriter {
	public boolean DataWriterToExcel(String username, String accountId) {		
		//Blank workbook
		@SuppressWarnings("resource")
		XSSFWorkbook workbook = new XSSFWorkbook(); 
		
		//Create a blank sheet
		XSSFSheet sheet = workbook.createSheet(username);
		
		//This data needs to be written (Object[])
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
        data.put("1", new Object[] {"Account Number"});
        data.put("2", new Object[] { accountId });
        
        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset)
        {
            Row row = sheet.createRow(rownum++);
            Object [] objArr = data.get(key);
            int cellnum = 0;
            for (Object obj : objArr)
            {
               Cell cell = row.createCell(cellnum++);
               if(obj instanceof String)
                    cell.setCellValue((String)obj);
                else if(obj instanceof Integer)
                    cell.setCellValue((Integer)obj);
            }
        }
        try
        {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(System.getProperty("user.dir") + "\\src\\test\\java\\Hexaware\\data\\user_accounts\\" + username + "_account_created.xlsx");
            workbook.write(out);
            out.close();
            System.out.println("Written successfully on disk.");
            return true;
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
            return false;
        }
	}
}
