package compatibility_report_parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Scanner;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class Main {

	
	
	final static  String REMOVE_SYMBOLS_TAG = "<a name='Binary_Removed'></a><a name='Binary_Withdrawn'></a>";
	final static  String TYPE_MEDIUM_TAG = "<a name='Type_Binary_Problems_Medium'></a>";
	final static  String TYPE_HIGH_TAG = "<a name='Type_Binary_Problems_High'></a>";
	final static  String TYPE_LOW_TAG = "<a name='Type_Binary_Problems_Low'></a>";
	final static  String INTERFACE_MEDIUM_TAG = "<a name='Interface_Binary_Problems_Medium'></a>";
	final static  String INTERFACE_LOW_TAG = "<a name='Interface_Binary_Problems_Low'></a>";
	final static  String INTERFACE_HIGH_TAG = "<a name='Interface_Binary_Problems_High'></a>";
	final static  String CHANGED_CONSTANTS = "<a name='Constant_Binary_Problems_Low'></a>";
	final static  String END_TAG = "<a style='font-size:11px;' href='#Top'>to the top</a>";
	
	final static  String gitTopackageMapFile = "pkg2git-map.list";
	final static int SYMBOLS_COUNT = 8;
	
	//final static  String END_TAG = "<a name=\"Headers\"></a>";
	
	static ArrayList<String> urlList =new ArrayList<String>();
	static ArrayList<Api> apiList =new ArrayList<Api>();
	static boolean [] TAGS_TO_FIND = new boolean[SYMBOLS_COUNT];
	static String tags[] = {"removed",
							"type_problems_high", "interface_problems_high",
							"type_problems_medium", "interface_problems_medium",
							"type_problems_low", "interface_problems_low",
							"changed_constants"};
	
	static String htmlTags[] = {REMOVE_SYMBOLS_TAG, 
								TYPE_HIGH_TAG,
								INTERFACE_HIGH_TAG,
								TYPE_MEDIUM_TAG,
								INTERFACE_MEDIUM_TAG,
								TYPE_LOW_TAG,
								INTERFACE_LOW_TAG,
								CHANGED_CONSTANTS
								};
	static String segments[] = new String[SYMBOLS_COUNT];
	static Hashtable<String, String> hashTable = new Hashtable<String, String>();
	static Hashtable<String, String> githash = new Hashtable<String, String>();
	static ArrayList<String> publicApi = new ArrayList<String>();
	static ArrayList<String> inhouseApi = new ArrayList<String>();
	static WritableWorkbook workbook;
	static  WritableSheet sheet0;
	static  WritableSheet sheet1;
	static String packageName = "";
	static String baseUrl = "";
	static int packageNumber = 2;

	
	public static void main(String args[]) throws FileNotFoundException{
		
		if(args.length < 1){
			System.out.println("Not enough arguments!");
			return;
		}
		//baseUrl = "/home/kayas/Desktop/compat_reports";
		baseUrl = args[0];
		String index = baseUrl+"/index.html";
		try {
		    workbook = Workbook.createWorkbook(new File(baseUrl+"/report_summary.xls"));
		       
		    initSheet0();
		    initSheet1();
			readTemplateExcel("template_report_summary.xls");
		    //readTemplateExcel("template.xls");
			readGitInfo();
			getUrls(index);
			
			for (String string : urlList) {
				parseUrl(string);
			}
			listPrint(apiList);
			
			workbook.write();
			workbook.close();
		    
		} catch (IOException e) {
			//System.out.println("Output file can not be created");
			return;
		}catch(WriteException e){
			//System.out.println("Output file can not be written");
			return;
		}

	}

	

	/**
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	private static void initSheet0(){
		
		try{
			sheet0 = workbook.createSheet("Summary", 0 );
			WritableFont wfobj=new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD);
			WritableCellFormat cfobj=new WritableCellFormat(wfobj);
			cfobj.setBackground(Colour.ICE_BLUE);
			cfobj.setWrap(true);
			
			Label h1,h2,h3;
			int row = 1;
			h1 = new Label(1, row,"Git",cfobj);
			h2 = new Label(2, row,"Package",cfobj);
			h3 = new Label(3, row,"Verdict",cfobj);
			
			sheet0.addCell(h1);
			sheet0.addCell(h2);
			sheet0.addCell(h3);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static void initSheet1(){
	   sheet1 = workbook.createSheet("Incompatitable Apis", 1 );
	}
	
	static void getUrls(String filepath){
		try {
			BufferedReader buf = new BufferedReader(new FileReader(filepath));
			StringBuffer sbuf = new StringBuffer();
			String inputLine = "";
			while ((inputLine = buf.readLine()) != null) {
				sbuf.append(inputLine + "\n");
			}
			
			Document doc = Jsoup.parse(sbuf.toString());
			Elements elems = doc.select("a[href]");
			for (Element element : elems) {
				String url = element.attr("href");
				urlList.add(baseUrl+"/"+url);
			}
			 buf.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	static void parseUrl(String filename){
		try{
			TAGS_TO_FIND = new boolean[8]; 
			BufferedReader buf = new BufferedReader(new FileReader(filename));
			StringBuffer sbuf = new StringBuffer();
		    String inputLine;
		    String firstLine;
		    firstLine = buf.readLine();
		    getKeyValues(firstLine );
		    
		    
		    for(int i = 0; i< tags.length ; i++){
		    	int val =Integer.parseInt(hashTable.get(tags[i]));
		    	
		    	if(val > 0){
		    		TAGS_TO_FIND[i] = true;
		    	}
		    }
		    
		    	
			while ((inputLine = buf.readLine()) != null) {
				sbuf.append(inputLine + "\n");
			}
			String htmlDoc = sbuf.toString();
			
			Document doc = Jsoup.parse(htmlDoc);
			Element elem = doc.select("span[style]").first();
			String style = elem.attr("style");
			if(style.equals("color:Blue;")){
				packageName = elem.text().trim();
			}
			System.out.println("***********"+packageName+"***********"+filename);
		 	Label c1 = new Label(1, packageNumber, githash.get(packageName));
		 	Label c2 = new Label(2, packageNumber, packageName.replace("so", "").replaceAll("\\.", ""));
		 	Label c3 = new Label(3, packageNumber++, hashTable.get("verdict"));
		 	
		 	sheet0.addCell(c1);
			sheet0.addCell(c2);
			sheet0.addCell(c3);
			
			buf.close();
		    if(hashTable.get("verdict").equals("compatible")){
		    	System.out.println("Page in compatible!!!!");
		    	return;
		    }else{
		    	System.out.println("Page in incompatible!!!!");
		    }
			for(int i = 0; i<tags.length; i++){
				if(TAGS_TO_FIND[i]){
					
					segments[i] = getContentForTags(htmlDoc, i);
					parseSegment( segments[i]);
				}
			}
			
			
		}catch(FileNotFoundException e){
			System.out.println("*&ERROR&* File not found :"+ e.getMessage());
		}catch(IOException e){
			e.printStackTrace();
		}catch(RowsExceededException e){
			System.out.println("*&ERROR&* Row number exceeded :"+ e.getMessage());
		}catch(WriteException e){
			System.out.println("*&ERROR&* File can not be written:"+ e.getMessage());
		}
	}
	
	static void getKeyValues(String doc){
		String values[] = doc.split(";");
		for (String string : values) {
			String cut [] = string.split(":");
			String key = cut[0].trim();
			String valString = cut[1].trim();
			//System.out.println(key + ": "+valString);
			hashTable.put(key, valString);
		}
	}
	
	static String getContentForTags(String htmlDocs,int  tagIndex){
		
		
			String [] segment = htmlDocs.split(htmlTags[tagIndex]);
			
			if(segment.length > 1){
				int nextTag = -1;
				int cursor = tagIndex + 1 ;
				while(cursor < TAGS_TO_FIND.length){
					if(TAGS_TO_FIND[cursor]){
						nextTag = cursor;
						break;
					}
					cursor++;
				}
				if(nextTag < 0){
					segment = segment[1].split(END_TAG);
					System.out.println(htmlTags[tagIndex] +" "+ END_TAG);
				}else{
					segment = segment[1].split(htmlTags[nextTag]);
					System.out.println(htmlTags[tagIndex] +" "+ htmlTags[nextTag]);
				}
				
			}else{
				
				System.out.println("WOW! Invalide Header!");
			}

			return segment[0].split("<h2>Problem Summary</h2>")[0]; //this split is added to remove the source compatibility HTML source
		
	}
	
	public static void parseSegment(String segment){
		
		Document doc = Jsoup.parse(segment);

		Elements elements = doc.select("span.iname");
		for (Element element : elements) {
		    Api api = new Api(element.text().trim());
			String shortName = element.text().split("\\(")[0].trim();
			api.setup("function", shortName, "Removed", "");
			api.setPackageName(packageName);
			if(!(apiList.contains(api))){
    			apiList.add(api);
    		}

		}
	    elements = doc.select("span.section");
	    Elements tables = doc.select("div table.ptable");
	    for (int i = 0 ; i<elements.size(); i++) {

	    	Elements type = elements.get(i).select("span.ttype");
	    	if(tables.size() > i){
	    		Api api = null;
	    		String fullName = getFuncFullNameFromSection(elements.get(i).text());
	    		
	    		String typeName ;
	    		String shortName ;
	    		if((type.size()>0)){
	    			typeName = type.get(0).text();
	    			shortName = fullName.split(typeName)[1].trim();//removes the type name datatype symbols
	    		}else{
	    			if(fullName.contains(":")){
	    				typeName = "function";
	    				shortName = fullName.split("\\(")[0].trim();//removes the parameters from function symbols
	    			}
	    			else{
	    				typeName = "const";
	    				shortName = fullName.trim();
	    			}
	    		}
	    		// = getFuncShortNameFromFullName(typeName, fullName);
		        
	    		api = new Api(fullName.trim());
	    		api.setShort_name(shortName);
	    		api.setType(typeName);
	    		 
				for (Element row : tables.get(i).select("tr")) {
		            Elements tds = row.select("td");
		            if (tds.size() > 1) {
		            	Api temp = new Api("");
		            	temp.copy(api);
		            	temp.setChange(tds.get(0).text());
		            	temp.setEffect(tds.get(1).text());
		            	temp.setPackageName(packageName);

		            	if(!(apiList.contains(temp))){
			    			apiList.add(temp);
			    		}
		            }
		        }
	    	}else{ //special code for several packages(e.g.: key-manager)
	    		Api temp = new Api(elements.get(i).text().trim());
	    		//temp.
	    		temp.setShort_name(temp.getFull_name().split("\\(")[0].trim());
	    		temp.setPackageName(packageName);
	    		temp.setType("function");
	    		temp.setChange("Removed");
	    		if(!(apiList.contains(temp))){
	    			apiList.add(temp);
	    		}
	    	}
		}
	 // System.out.println(api);
	}
	
	public static String  getFuncFullNameFromSection(String row){
		String [] pieces = row.split("\\([\\d]+\\)");
		String fullName;
		if(pieces[0].contains("[+]")){
			fullName  = pieces[0].split("\\[\\+\\]")[1].trim();
		}else{
			fullName = pieces[0];
		}
		
		return fullName;
		
		//getFuncShortNameFromFullName(type, fullName);
		
	/*	Api api = new Api(fullName.trim());
		api.setShort_name(shortName);
		api.setType(type);
		//System.out.println(api);
	
		return api;
	*/
	}


//	private static String getFuncShortNameFromFullName(String type,
//			String fullName) {
//		String shortName;
//		if(!type.equals("function") && !type.equals("const")){
//			shortName = fullName.split(type)[1].trim();
//		}else{
//			shortName = fullName.split("\\(")[0].trim();
//		}
//		return shortName;
//	}
	
	public static <E> void listPrint(ArrayList<E> list) throws IOException{
		try{
			
			WritableFont wfobj=new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD);
		    WritableCellFormat cfobj=new WritableCellFormat(wfobj);
		    cfobj.setBackground(Colour.ICE_BLUE);
		    cfobj.setWrap(true);
		    
		    Label h1,h2,h3,h4,h5,h6,h7,h8;
		    int row = 1;
		   	h1 = new Label(1, row,"Package",cfobj);
			h2 = new Label(2, row,"Symbol",cfobj);
			h3 = new Label(3, row,"Type",cfobj);
			h4 = new Label(4, row,"Api Type",cfobj);
			h5 = new Label(5, row,"Change",cfobj);
			h6 = new Label(6, row,"Effect",cfobj);
			h7 = new Label(7, row,"Full Signature",cfobj);
			h8 = new Label(8, row,"Remark",cfobj);
			
			sheet1.addCell(h1);
			sheet1.addCell(h2);
			sheet1.addCell(h3);
			sheet1.addCell(h4);
			sheet1.addCell(h5);
			sheet1.addCell(h6);
			sheet1.addCell(h7);
			sheet1.addCell(h8);
			
			Iterator<E> ita = list.iterator();
			int index = 3;
			while(ita.hasNext()){
				row++;
				Api api = (Api)ita.next();
				if(publicApi.contains(api.getShort_name())){
					api.setApiType("Public");
				}else{
					if(inhouseApi.contains(api.getShort_name())){
						api.setApiType("Inhouse");
					}else{
						api.setApiType("Internal");
					}
				}
				
				Label c1 = new Label(1, row, api.getPackageName());
				//api.getPackageName().replaceAll(regex, replacement)
				Label c2 = new Label(2, row, api.getShort_name());
				Label c3 = new Label(3, row, api.getType());
				//Label c4 = new Label(4, row, api.getApiType());
				Formula f4 = new Formula(4, row, "IF(COUNTIF('Public'!C:C,C"+index+")>0,\"Public\",IF(COUNTIF('Inhouse'!C:C,C"+index+")>0,\"Inhouse\",\"Internal\"))");
				Label c5 = new Label(5, row, api.getChange());
				Label c6 = new Label(6, row, api.getEffect());
				Label c7 = new Label(7, row, api.getFull_name());
				Label c8 = new Label(8, row, "");
				
				sheet1.addCell(c1);
				sheet1.addCell(c2);
				sheet1.addCell(c3);
				sheet1.addCell(f4);
				sheet1.addCell(c5);
				sheet1.addCell(c6);
				sheet1.addCell(c7);
				sheet1.addCell(c8);
				index++;
			}
		}catch (WriteException e) {
			e.printStackTrace();
		}

	}
	
	public static void readGitInfo(){
		try {
			Scanner sc = new Scanner(new File(gitTopackageMapFile));
			while(sc.hasNext()){
				String line = sc.nextLine();
				String [] ar = line.split(",");
				ar[0].trim();
				ar[1] = ar[1].split("#")[0];
				githash.put(ar[0], ar[1].trim());
			}
			sc.close();
		} catch (FileNotFoundException e) {
			
			//System.out.println("Git list file not found");
		}
	}
	
	private static void readTemplateExcel(String filepath){
		 try {
			Workbook workbook = Workbook.getWorkbook(new File(filepath));
			Sheet publicSheet = workbook.getSheet("Public");
			Main.workbook.importSheet("Public", 2, publicSheet);
			
			Sheet inhouseSheet = workbook.getSheet("Inhouse");
			Main.workbook.importSheet("Inhouse", 3, inhouseSheet);
			int col = 2;
			for(int row = 3; row < publicSheet.getRows(); row++){
				Cell cell = publicSheet.getCell(col, row);
				publicApi.add(cell.getContents().trim());
				//System.out.println(row+" *&*"+cell.getContents());
			}
			
			for(int row = 3; row < inhouseSheet.getRows(); row++){
				Cell cell = inhouseSheet.getCell(col, row);
				inhouseApi.add(cell.getContents().trim());
				//System.out.println(row+" *&*"+cell.getContents());
			}
			
		} catch (BiffException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		
	}
}
