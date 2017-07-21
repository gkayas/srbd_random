package clirunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ReportUtils {


	public static String getReportFileName(String project){
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss");
		return format.format(date) + "_report_" + project + ".xls";
	}

	private static String getTempReportPath(){
		return Paths.get(System.getProperty("user.dir"), "temp", Constant.LAST_REPORT_FILE_NAME).toString();
	}

	private static ArrayList<String> getHeader(String project)
	{
		ArrayList<String> header = new ArrayList<String>();
		if(project.toLowerCase().equals(EnumProject.STOREAPP.toString())){
			header.add(Constant.REPORT_COLUMN_APP_NAME);
			header.add(Constant.JAVA_FILE_NAME);
			header.add(Constant.REPORT_COLUMN_RESULT);
		}else{
			header.add(Constant.REPORT_COLUMN_APP_TYPE);
			header.add(Constant.REPORT_COLUMN_PROFILE);
			header.add(Constant.REPORT_COLUMN_REQUIRED_VERSION);
			header.add(Constant.REPORT_COLUMN_APP_NAME);
			header.add(Constant.JAVA_FILE_NAME);
			header.add(Constant.REPORT_COLUMN_RESULT);
			header.add(Constant.REPORT_COLUMN_BUILD);
			header.add(Constant.REPORT_COLUMN_PACKAGE);
		}

		header.add(Constant.REPORT_COLUMN_INSTALL);
		header.add(Constant.REPORT_COLUMN_LAUNCH);
		header.add(Constant.REPORT_COLUMN_EXPOLATORY);
		header.add(Constant.REPORT_COLUMN_CLOSE);
		header.add(Constant.REPORT_COLUMN_UNINSTALL);
		header.add(Constant.REPORT_COLUMN_CRASH);

		if(project.toLowerCase().equals(EnumProject.SAMPLEAPP.toString())){
			header.add("Build Time(ms)");
			header.add("Package Time(ms)");
		}
		header.add("Install Time(ms)");
		header.add("Launch Time(ms)");
		header.add("Exploratory Time(ms)");
		header.add("Close Time(ms)");
		header.add("Uninstall Time(ms)");
		header.add("Crash Time(ms)");
		header.add("Total Elapsed Time(ms)");

		return header;
	}

	private static ArrayList<String> getTCNameList(String project){
		ArrayList<String> tcNameList = new ArrayList<String>();
		if(project.toLowerCase().equals(EnumProject.SAMPLEAPP.toString())){
			tcNameList.add(Constant.TC_NAME_BUILD);
			tcNameList.add(Constant.TC_NAME_PACKAGE);
		}
		tcNameList.add(Constant.TC_NAME_INSTALL);
		tcNameList.add(Constant.TC_NAME_LAUNCH);
		tcNameList.add(Constant.TC_NAME_EXPOLATRY);
		tcNameList.add(Constant.TC_NAME_CLOSE);
		tcNameList.add(Constant.TC_NAME_UNINSTALL);
		tcNameList.add(Constant.TC_NAME_CRASH);
		return tcNameList;
	}

	public static void checkConfig(String project, String sdkPath, String reportPath) {

		File reportDir = new File(reportPath).getParentFile();

		if(reportDir == null){
			System.out.println("[MESSAGE] No preceding report directory found with " + reportPath);
			System.exit(0);
		}else if(!reportDir.exists()){
			System.out.println("[MESSAGE] Report directory " + reportDir.getAbsolutePath() + " not exists");
			System.out.println("[MESSAGE] Please configure \"Save Report File\" in Settings window of UIAutomator or input correct path");
			System.exit(0);
		}

		if(!reportPath.endsWith(".xls")){
			System.out.println("[MESSAGE] Report file extension should be .xls");
			System.exit(0);
		}

		if(project.toLowerCase().equals("sampleapp")){
			File sdkDir = new File(sdkPath + "/tools/ide/bin/tizen");
			if(!sdkDir.exists()){
				System.out.println("[MESSAGE] SDK path " + sdkPath + " not valid");
				System.out.println("[MESSAGE] Please configure \"SDK Path\" in Settings window of UIAutomator");
				System.exit(0);
			}
		}

	}

	private static int findRow(HSSFSheet sheet, AppInfo appInfo,String project) {
		for (Row row : sheet) {
			for (Cell cell : row) {
				if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					if (cell.getRichStringCellValue().getString().trim().equals(appInfo.getAppName())) {
						if(project.toLowerCase().equals(EnumProject.STOREAPP.toString()))
							return cell.getRowIndex();
						else{
							if(isRwoHasCotent(row,appInfo.getProfile()) && isRwoHasCotent(row,appInfo.getRequiredVersion()) && isRwoHasCotent(row,appInfo.getAppType()))
								return cell.getRowIndex();
						}
					}
				}
			}
		}
		return 0;
	}

	private static boolean isRwoHasCotent(Row row, String value) {
		for (Cell cell : row)
			if (cell.getCellType() == Cell.CELL_TYPE_STRING)
				if (cell.getRichStringCellValue().getString().trim().equals(value))
					return true;
		return false;
	}

	private static void updateReport(String reportpath, ArrayList<AppInfo> appInfos, String project) {
		try{
			File reportFile = new File(reportpath);
			File lastReportFile = new File(getTempReportPath());
			FileInputStream file = new FileInputStream(lastReportFile);
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet sheet = workbook.getSheet(Constant.REPORT_SHETT_NAME);

			for (AppInfo appInfo : appInfos) {
				int rowNumber = findRow(sheet,appInfo,project);
				saveAppResult(sheet, rowNumber, appInfo, project);
			}

			file.close();

			FileOutputStream outFile =new FileOutputStream(reportFile);
			workbook.write(outFile);
			outFile.close();
			copyFile(reportFile,lastReportFile);
			System.out.println("[MESSAGE] " + reportpath + " saved successfully.");

		} catch (IOException e) {
			System.out.println("[MESSAGE] Failed to save report, " + e.getMessage());
		}
	}

	public static void saveTestcaseReport(String reportFilePath,String testCasePath,String tcResult, long elapseTime){
		try{
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet worksheet = workbook.createSheet("Report");

			String[] headers = new String[] {"TC Name", "Result", "Total Elapsed Time(ms)" };

			int rowNumber = 0;
			HSSFRow nRow = worksheet.createRow(rowNumber++);

			for (int headerIndex=0; headerIndex<headers.length; headerIndex++) {
				nRow.createCell(headerIndex).setCellValue(headers[headerIndex]);
			}
			String tcName = new File(testCasePath).getName().split(".tc")[0];

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
			System.out.println("[MESSAGE] " + reportFilePath + " saved successfully.");
		}
		catch(Exception e){
			System.out.println("[MESSAGE] Failed to save report, " + e.getMessage());
		}
	}

	public static void saveProjectReport(String reportpath, ArrayList<AppInfo> appInfos, String project, String mode) {
		if (mode == null || mode.equals("run"))
			saveReport(reportpath, appInfos, project);
		else
			updateReport(reportpath, appInfos, project);
	}

	public static void saveReport(String reportpath, List<AppInfo> appInfos, String project)
	{
		if (appInfos == null)
			return;
		try{
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet worksheet = workbook.createSheet(Constant.REPORT_SHETT_NAME);


			int rowNumber = 0;
			HSSFRow nRow = worksheet.createRow(rowNumber++);

			ArrayList<String> headers = getHeader(project);

			for (int headerIndex=0; headerIndex<headers.size(); headerIndex++) {
				nRow.createCell(headerIndex).setCellValue(headers.get(headerIndex));
			}

			for (AppInfo appInfo : appInfos) {
				saveAppResult(worksheet, rowNumber++, appInfo, project);
			}

			File reportFile = new File(reportpath);
			if(!reportFile.getParentFile().exists())
				reportFile.getParentFile().mkdirs();
			FileOutputStream fileOutputStream = new FileOutputStream(reportFile);
			workbook.write(fileOutputStream);
			fileOutputStream.close();
			copyFile(reportFile,new File(getTempReportPath()));
			System.out.println("[MESSAGE] " + reportpath + " saved successfully.");
		}
		catch(Exception e){
			System.out.println("[MESSAGE] Report " + reportpath + " saving failed.");
		}
	}

	private static void copyFile(File source, File target) throws IOException {
	    try (
	            InputStream in = new FileInputStream(source);
	            OutputStream out = new FileOutputStream(target)
	    ) {
	        byte[] buf = new byte[1024];
	        int length;
	        while ((length = in.read(buf)) > 0) {
	            out.write(buf, 0, length);
	        }
	    }
	}

	private static int saveAppResult(HSSFSheet worksheet, int rowNumber, AppInfo appInfo, String project) {
		HSSFRow nRow;
		HSSFCell cell;

		//ArrayList<String> headers = getHeader(project);
		ArrayList<String> tcNameList = getTCNameList(project);

		nRow = worksheet.createRow(rowNumber);
		int column = 0;
		if(project.toLowerCase().equals(EnumProject.STOREAPP.toString())){
			nRow.createCell(column++).setCellValue(appInfo.getAppName());
		}else{
			nRow.createCell(column++).setCellValue(appInfo.getAppType());
			nRow.createCell(column++).setCellValue(appInfo.getProfile());
			nRow.createCell(column++).setCellValue(appInfo.getRequiredVersion());
			nRow.createCell(column++).setCellValue(appInfo.getAppName());
		}

		nRow.createCell(column++).setCellValue(appInfo.getJavaFileName() + ".java");
		nRow.createCell(column++).setCellValue(appInfo.getAppResult()+"");

		for (String tcName : tcNameList) {
			TestInfo testInfo = appInfo.getTestInfo(tcName);
			cell = nRow.createCell(column++);

			if(testInfo != null)
				cell.setCellValue(testInfo.getResult()+"");
			else
				cell.setCellValue(TCResult.NA+"");
		}

		for (String tcName : tcNameList) {
			TestInfo testInfo = appInfo.getTestInfo(tcName);
			cell = nRow.createCell(column++);

			if(testInfo != null)
				cell.setCellValue(testInfo.getElapseTime());
			else
				cell.setCellValue(0);
		}
		cell = nRow.createCell(column++);
		cell.setCellValue(appInfo.getAppElapseTime());

		return rowNumber;
	}

//	private static int getColumnNumber(ArrayList<String> headers, String tcName) {
//		for (int i = 0; i < headers.size(); i++) {
//			if(tcName.toLowerCase().contains(headers.get(i).toLowerCase()))
//				return i;
//		}
//		return 0;
//	}

}
