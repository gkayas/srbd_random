package uirunner;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javafx.beans.property.StringProperty;


public class ReportUtils {

//	private static String TEMPLATE_REPORT_FILE_PATH =  "res/report_temp/report.xls";
//	private static String REPORT_DATE_TIME_FORMAT = "yyyy_MM_dd_HH_mm_ss";
//	private static String CURRENT_REPORT_FILE_PATH;
//
//
//	public static void initialize()
//	{
//		createReport_updated();
//	}
//
//	private static String getReportTime()
//	{
//		DateFormat dateFormat = new SimpleDateFormat(REPORT_DATE_TIME_FORMAT);
//		Calendar cal = Calendar.getInstance();
//		return dateFormat.format(cal.getTime());
//	}
//
//	private static void createReport_updated(){
//		FileInputStream templateFile;
//		HSSFWorkbook workbook = null;
//		try{
//			CURRENT_REPORT_FILE_PATH = "reports/report.xls";
//
//			File currentReportFile = new File(CURRENT_REPORT_FILE_PATH);
//			if(currentReportFile.exists())
//			{
//				templateFile = new FileInputStream(CURRENT_REPORT_FILE_PATH);
//			}
//			else
//			{
//				templateFile = new FileInputStream(TEMPLATE_REPORT_FILE_PATH);
//			}
//			workbook = new HSSFWorkbook(templateFile);
//			templateFile.close();
//			FileOutputStream outFile =new FileOutputStream(currentReportFile);
//			System.out.println("[Report] File path: " + currentReportFile.getAbsolutePath());
//			workbook.write(outFile);
//			outFile.close();
//		}
//		catch(Exception e){
//			System.out.println(e.getMessage());
//		}
//	}
//
//	private static Cell findRow(HSSFSheet sheet, String cellContent) {
//		for (Row row : sheet) {
//			for (Cell cell : row) {
//				if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
//					if (cell.getRichStringCellValue().getString().trim().equals(cellContent)) {
//						return cell;
//					}
//				}
//			}
//		}
//		return null;
//	}
//
//	static Hashtable<String , String> getTestCaseResultMap(String moduleName) {
//		Hashtable<String, String> map = null;
//		FileInputStream templateFile;
//		HSSFWorkbook workbook = null;
//		try{
//			map = new Hashtable<String, String>();
//			File file = new File(CURRENT_REPORT_FILE_PATH);
//			if(file.exists())
//			{
//				templateFile = new FileInputStream(CURRENT_REPORT_FILE_PATH);
//			}
//			else
//			{
//				templateFile = new FileInputStream(TEMPLATE_REPORT_FILE_PATH);
//			}
//			workbook = new HSSFWorkbook(templateFile);
//			HSSFSheet sheet = workbook.getSheet(moduleName);
//			for (Row row : sheet) {
//				Cell tcName = row.getCell(0);
//				Cell tcResult = row.getCell(1);
//				map.put(tcName.getStringCellValue(), tcResult.getStringCellValue());
//			}
//			templateFile.close();
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return map;
//	}
//
//	public static void resetAllTcResutl(String moduleName) {
//
//		FileInputStream templateFile;
//		HSSFWorkbook workbook = null;
//		try{
//			File file = new File(CURRENT_REPORT_FILE_PATH);
//			if(file.exists())
//			{
//				templateFile = new FileInputStream(CURRENT_REPORT_FILE_PATH);
//			}
//			else
//			{
//				templateFile = new FileInputStream(TEMPLATE_REPORT_FILE_PATH);
//			}
//			workbook = new HSSFWorkbook(templateFile);
//			HSSFSheet sheet = workbook.getSheet(moduleName);
//
//			for (Row row : sheet) {
//				Cell tcName = row.getCell(0);
//				Cell tcResult = row.getCell(1);
//				tcResult.setCellValue(TCResult.NR.toString());
//			}
//
//			templateFile.close();
//
//			FileOutputStream outFile = new FileOutputStream(file);
//			workbook.write(outFile);
//			outFile.close();
//
//		}catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//
//	}
//
//	static void updateExcelResult(String module, String tcid,String result){
//		try{
//
//			FileInputStream file = new FileInputStream(CURRENT_REPORT_FILE_PATH);
//			HSSFWorkbook workbook = new HSSFWorkbook(file);
//			HSSFSheet sheet = workbook.getSheet(module);
//
//			Cell cell = findRow(sheet,tcid);
//			Cell resultCell = null;
//			HSSFRow sheetrow = sheet.getRow(cell.getRowIndex());
//			resultCell = sheetrow.getCell(cell.getColumnIndex() + 1);
//			resultCell.setCellValue(result);
//			file.close();
//			//System.out.println("[Report] updated, Module : " + module + ", TC : " + tcid + ", Result : "+result);
//
//			FileOutputStream outFile =new FileOutputStream(new File(CURRENT_REPORT_FILE_PATH));
//			workbook.write(outFile);
//			outFile.close();
//
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}

	public static void exportStoreappReport(String reportFilePath, List<StoreAppInfo> apps)
	{
		try{
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet worksheet = workbook.createSheet("Report");

			String[] headers = new String[] { "App Name", "Java File Name", "Result", "Install", "Launch", "Exploratory","Close","Uninstall","Crash",
					"Install Time(ms)", "Launch Time(ms)", "Exploratory Time(ms)", "Close Time(ms)", "Uninstall Time(ms)", "Crash Time(ms)", "Total Elapsed Time(ms)" };

			int rowNumber = 0;
			HSSFRow nRow = worksheet.createRow(rowNumber++);

			for (int headerIndex=0; headerIndex<headers.length; headerIndex++) {
				nRow.createCell(headerIndex).setCellValue(headers[headerIndex]);
			}

			for (StoreAppInfo appInfo : apps) {
				int columnNumber = 0;
				nRow = worksheet.createRow(rowNumber++);

				ArrayList<String> propertyList = new ArrayList<String>();
				propertyList.add(appInfo.getName().getValue().toString());
				propertyList.add(appInfo.getJavaFileName() + ".java");

				ArrayList<StringProperty> resultList = new ArrayList<StringProperty>();
				resultList.add(appInfo.getResult());
				resultList.add(appInfo.getInstall());
				resultList.add(appInfo.getLaunch());
				resultList.add(appInfo.getExploratory());
				resultList.add(appInfo.getClose());
				resultList.add(appInfo.getUninstall());
				resultList.add(appInfo.getDetectCrash());

				ArrayList<String> timeList = new ArrayList<String>();
				timeList.add(appInfo.getTimeInstall() + "");
				timeList.add(appInfo.getTimeLaunch() + "");
				timeList.add(appInfo.getTimeExporatory() + "");
				timeList.add(appInfo.getTimeClose() + "");
				timeList.add(appInfo.getTimeUninstall() + "");
				timeList.add(appInfo.getTimeCrash() + "");
				timeList.add(appInfo.getTimeTotal() + "");

				for (String stringProperty : propertyList) {
					HSSFCell cell = nRow.createCell(columnNumber++);
					cell.setCellValue(stringProperty);
				}

				for (StringProperty stringProperty : resultList) {
					HSSFCell cell = nRow.createCell(columnNumber++);
					cell.setCellValue(stringProperty.getValue().toString());
				}

				for (String time : timeList) {
					HSSFCell cell = nRow.createCell(columnNumber++);
					cell.setCellValue(time);
				}

			}

			File reportFile = new File(reportFilePath);
			if(!reportFile.getParentFile().exists())
				reportFile.getParentFile().mkdir();
			FileOutputStream fileOutputStream = new FileOutputStream(reportFile);
			workbook.write(fileOutputStream);
			fileOutputStream.close();

		}
		catch(Exception e){
			System.out.println("[MESSAGE] Failed to save report, " + e.getMessage());
		}
	}

	public static void exportSampleappReport(String reportFilePath, List<SampleAppInfo> tableRows) {
		try{
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet worksheet = workbook.createSheet("Report");

			String[] headers = new String[] {"App Type", "Profile", "Required Version", "App Name", "Java File Name",
					"Result", "Build", "Package", "Install", "Launch", "Exploratory","Close","Uninstall","Crash",
					"Build Time(ms)", "Package Time(ms)", "Install Time(ms)", "Launch Time(ms)",
					"Exploratory Time(ms)", "Close Time(ms)", "Uninstall Time(ms)", "Crash Time(ms)", "Total Elapsed Time(ms)" };

			int rowNumber = 0;
			HSSFRow nRow = worksheet.createRow(rowNumber++);

			for (int headerIndex=0; headerIndex<headers.length; headerIndex++) {
				nRow.createCell(headerIndex).setCellValue(headers[headerIndex]);
			}

			for (SampleAppInfo appInfo : tableRows) {
				int columnNumber = 0;
				nRow = worksheet.createRow(rowNumber++);

				ArrayList<String> propertyList = new ArrayList<String>();
				propertyList.add(appInfo.getAppType().getValue().toString());
				propertyList.add(appInfo.getProfile().getValue().toString());
				propertyList.add(appInfo.getRequiredVersion().getValue().toString());
				propertyList.add(appInfo.getName().getValue().toString());
				propertyList.add(appInfo.getJavaFileName() + ".java");

				ArrayList<StringProperty> resultList = new ArrayList<StringProperty>();
				resultList.add(appInfo.getResult());
				resultList.add(appInfo.getBuild());
				resultList.add(appInfo.getPackageing());
				resultList.add(appInfo.getInstall());
				resultList.add(appInfo.getLaunch());
				resultList.add(appInfo.getExploratory());
				resultList.add(appInfo.getClose());
				resultList.add(appInfo.getUninstall());
				resultList.add(appInfo.getDetectCrash());

				ArrayList<String> timetList = new ArrayList<String>();
				timetList.add(appInfo.getTimeBuild()+"");
				timetList.add(appInfo.getTimePackage()+"");
				timetList.add(appInfo.getTimeInstall()+"");
				timetList.add(appInfo.getTimeLaunch()+"");
				timetList.add(appInfo.getTimeExporatory()+"");
				timetList.add(appInfo.getTimeClose()+"");
				timetList.add(appInfo.getTimeUninstall()+"");
				timetList.add(appInfo.getTimeCrash()+"");
				timetList.add(appInfo.getTimeTotal()+"");


				for (String stringProperty : propertyList) {
					HSSFCell cell = nRow.createCell(columnNumber++);
					cell.setCellValue(stringProperty);
				}

				for (StringProperty stringProperty : resultList) {
					HSSFCell cell = nRow.createCell(columnNumber++);
					cell.setCellValue(stringProperty.getValue().toString());
				}

				for (String time : timetList) {
					HSSFCell cell = nRow.createCell(columnNumber++);
					cell.setCellValue(time);
				}

			}

			File reportFile = new File(reportFilePath);
			if(!reportFile.getParentFile().exists())
				reportFile.getParentFile().mkdir();
			FileOutputStream fileOutputStream = new FileOutputStream(reportFile);
			workbook.write(fileOutputStream);
			fileOutputStream.close();

		}
		catch(Exception e){
			System.out.println("[MESSAGE] Failed to save report, " + e.getMessage());
		}

	}

	public static void exportCommonUseReport(String reportFilePath, String tcResult, long elapseTime){
		try{
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet worksheet = workbook.createSheet("Report");

			String[] headers = new String[] {"TC Name", "Result", "Total Elapsed Time(ms)" };

			int rowNumber = 0;
			HSSFRow nRow = worksheet.createRow(rowNumber++);

			for (int headerIndex=0; headerIndex<headers.length; headerIndex++) {
				nRow.createCell(headerIndex).setCellValue(headers[headerIndex]);
			}
			String tcName = new File(reportFilePath).getName().split(".xls")[0];

			nRow = worksheet.createRow(1);
			nRow.createCell(0).setCellValue(tcName);
			nRow.createCell(1).setCellValue(tcResult);
			nRow.createCell(2).setCellValue(elapseTime);

			File reportFile = new File(reportFilePath);
			if(!reportFile.getParentFile().exists())
				reportFile.getParentFile().mkdir();
			FileOutputStream fileOutputStream = new FileOutputStream(reportFile);
			workbook.write(fileOutputStream);
			fileOutputStream.close();

		}
		catch(Exception e){
			System.out.println("[MESSAGE] Failed to save report, " + e.getMessage());
		}
	}

}
