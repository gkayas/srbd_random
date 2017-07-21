package refapp;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;


public class ReportUtils {

	private static String TEMPLATE_REPORT_FILE_PATH =  "res/report_temp/ReportTemplate.xls";
	private static String REPORT_DATE_TIME_FORMAT = "yyyy_MM_dd_HH_mm_ss";
	private static String CURRENT_REPORT_FILE_PATH; 
	
	
	static void initialize()
	{
		createReport();
		cleanErrorPath();
	}
	
	private static void deleteFolder(File folder) {
	    File[] files = folder.listFiles();
	    if(files!=null) {
	        for(File f: files) {
	            if(f.isDirectory()) {
	                deleteFolder(f);
	            } else {
	                f.delete();
	            }
	        }
	    }
	    folder.delete();
	}
	
	private static void cleanErrorPath()
	{
		File errorScreenDir = new File("error");
		deleteFolder(errorScreenDir);
		errorScreenDir.mkdir();
	}
	
	private static String getReportTime()
	{
		DateFormat dateFormat = new SimpleDateFormat(REPORT_DATE_TIME_FORMAT);
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
	}
	
	private static void createReport(){
		try{
			FileInputStream file = new FileInputStream(TEMPLATE_REPORT_FILE_PATH);
	        HSSFWorkbook workbook = new HSSFWorkbook(file);
	        file.close();
	        
	        CURRENT_REPORT_FILE_PATH = "reports/ReffApp_Verification_Report_" + getReportTime() +".xls";
	        
	        File reportFile = new File(CURRENT_REPORT_FILE_PATH);
	        FileOutputStream outFile =new FileOutputStream(reportFile);
	        System.out.println("[Report] File path: " + reportFile.getAbsolutePath());
	        
	        workbook.write(outFile);
	        outFile.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	private static Cell findRow(HSSFSheet sheet, String cellContent) {
	    for (Row row : sheet) {
	        for (Cell cell : row) {
	            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
	                if (cell.getRichStringCellValue().getString().trim().equals(cellContent)) {
	                    return cell;
	                }
	            }
	        }
	    }               
	    return null;
	}
	
	static void updateExcelResult(String module, String tcid,String result){
       try{
            FileInputStream file = new FileInputStream(CURRENT_REPORT_FILE_PATH);
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheet(module);
            
            Cell cell = findRow(sheet,tcid);
            Cell resultCell = null;
            HSSFRow sheetrow = sheet.getRow(cell.getRowIndex());
            resultCell = sheetrow.getCell(cell.getColumnIndex() + 1);
            resultCell.setCellValue(result);
            file.close();
            System.out.println("[Report] updated, Module : " + module + ", TC : " + tcid + ", Result : "+result);
            
            FileOutputStream outFile =new FileOutputStream(new File(CURRENT_REPORT_FILE_PATH));
            workbook.write(outFile);
            outFile.close();
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
     }
	 
	 public static void main(String[] args) {
		 createReport();
		 updateExcelResult("Calendar","Calendar_003","NT");
	}
	
}
