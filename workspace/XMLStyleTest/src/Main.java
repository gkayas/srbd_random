import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.stream.events.ProcessingInstruction;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Main {
	public static void main(String [] args) throws IOException {
		ArrayList<Testcase> testCaseList = new ArrayList<Testcase>();
		readExitingResultFile(testCaseList);
		System.out.println(testCaseList.size());
		dumpListToResultFile(testCaseList);
	}
	
	private static void readExitingResultFile(ArrayList<Testcase> testCaseList) throws IOException {
		Scanner sc = new Scanner(new File("report.csv"));
		
		sc.nextLine();
		
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			
			String parts[] = line.split(",");
			if(parts.length < 6) {
				System.out.println(line);				
				throw new IOException("report.csv" + " - Wrong formated report file...");
			}
			String tcmID = parts[0];
			String name = parts[1];
			String module = parts[2];
			String platform = parts[3];
			String profile = parts[4];
			String status = parts[5];
			String msg = "";
			if(parts.length == 7 ) {
				msg = parts[6];
			}
			
			
			Testcase tc = new Testcase();
			tc.setTcmID(tcmID);
			tc.setName(name);
			tc.setModule(module);
			tc.setPlatform(platform);
			tc.setProfile(profile);
			tc.setStatus(status);
			tc.setMsg(msg);
			testCaseList.add(tc);
		}		
		sc.close();
	}
	
	private static void dumpListToResultFile(ArrayList<Testcase> list) {
	
			ArrayList<XMLReportModule> reportModuleList = new ArrayList<XMLReportModule>();
			XMLReportModule.totalTc = 0;
			XMLReportModule.totalPassTc = 0;
			XMLReportModule.totalFailTc = 0;
			XMLReportModule.totalNATc = 0;
			XMLReportModule.totalNETc = 0;
			
			
			for (Testcase testcase : list) {
				
				XMLReportModule reportModule = new XMLReportModule(testcase.getModule());
				if(reportModuleList.contains(reportModule)) {
					reportModule = reportModuleList.get(reportModuleList.indexOf(reportModule));
				} else {
					reportModuleList.add(reportModule);
				}
				reportModule.increaseTcCount();
				XMLReportModule.totalTc++; 
				if(testcase.getStatus().equals("Pass")) {
					reportModule.increasePassTcCount();
					XMLReportModule.totalPassTc++;
				} else if(testcase.getStatus().equals("NA")) {
					reportModule.increaseNATcCount();
					XMLReportModule.totalNATc++;
				} else if(testcase.getStatus().equals("Fail")) {
					reportModule.increaseFailTcCount();
					XMLReportModule.totalFailTc++;
				} else {
					reportModule.increaseNETcCount();
					XMLReportModule.totalNETc++;
				}
			}
			System.out.println(reportModuleList.size());
			writeResultXML(reportModuleList);
	}
	
	public static void writeResultXML(ArrayList<XMLReportModule> moduleListList) {
		try {
			
			org.w3c.dom.Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
			
			
			org.w3c.dom.Element root = doc.createElement("report");
			doc.appendChild(root);
			org.w3c.dom.ProcessingInstruction pi = doc.createProcessingInstruction("xml-stylesheet", " href=\"style1.css\"");
			doc.insertBefore(pi, root);
			//creating summary table
			org.w3c.dom.Element summary = doc.createElement("summary");
			root.appendChild(summary);
			
			org.w3c.dom.Element summaryTitle = doc.createElement("data-caption");
			summaryTitle.setTextContent("Summary");
			summary.appendChild(summaryTitle);
			
			org.w3c.dom.Element titleRow = doc.createElement("summary-row");
			summary.appendChild(titleRow);
			
			org.w3c.dom.Element h1 = doc.createElement("data-header");
			h1.setTextContent("Total TCs");
			titleRow.appendChild(h1);
			org.w3c.dom.Element h2 = doc.createElement("data-header");
			h2.setTextContent("Pass");
			titleRow.appendChild(h2);
			org.w3c.dom.Element h3 = doc.createElement("data-header");
			h3.setTextContent("Fail");
			titleRow.appendChild(h3);
			org.w3c.dom.Element h4 = doc.createElement("data-header");
			h4.setTextContent("NA");
			titleRow.appendChild(h4);
			org.w3c.dom.Element h5 = doc.createElement("data-header");
			h5.setTextContent("NE");
			titleRow.appendChild(h5);
			org.w3c.dom.Element h6 = doc.createElement("data-header");
			h6.setTextContent("Pass Rate");
			titleRow.appendChild(h6);
			
			org.w3c.dom.Element dataRow = doc.createElement("data-row");
			summary.appendChild(dataRow);
			
			org.w3c.dom.Element v1 = doc.createElement("data-cell");
			v1.setTextContent(""+XMLReportModule.totalTc);
			dataRow.appendChild(v1);
			org.w3c.dom.Element v2 = doc.createElement("data-cell");
			v2.setTextContent(""+XMLReportModule.totalPassTc);
			dataRow.appendChild(v2);
			org.w3c.dom.Element v3 = doc.createElement("data-cell");
			v3.setTextContent(""+XMLReportModule.totalFailTc);
			dataRow.appendChild(v3);
			org.w3c.dom.Element v4 = doc.createElement("data-cell");
			v4.setTextContent(""+XMLReportModule.totalNATc);
			dataRow.appendChild(v4);
			org.w3c.dom.Element v5 = doc.createElement("data-cell");
			v5.setTextContent(""+XMLReportModule.totalNETc);
			dataRow.appendChild(v5);
			org.w3c.dom.Element v6 = doc.createElement("data-cell");
			v6.setTextContent(""+XMLReportModule.getGolbalPassRate());
			dataRow.appendChild(v6);
			
			// end of creating summary table
			
			//creating content table
			org.w3c.dom.Element content = doc.createElement("data");
			root.appendChild(content);
			
			org.w3c.dom.Element contentTitle = doc.createElement("data-caption");
			contentTitle.setTextContent("Result Details");
			content.appendChild(contentTitle);
			
			org.w3c.dom.Element contentTitleRow = doc.createElement("data-row");
			content.appendChild(contentTitleRow);
			org.w3c.dom.Element head1 = doc.createElement("data-header");
			head1.setTextContent("Module");
			contentTitleRow.appendChild(head1);
			org.w3c.dom.Element head2 = doc.createElement("data-header");
			head2.setTextContent("TCs");
			contentTitleRow.appendChild(head2);
			org.w3c.dom.Element head3 = doc.createElement("data-header");
			head3.setTextContent("Pass");
			contentTitleRow.appendChild(head3);
			org.w3c.dom.Element head4 = doc.createElement("data-header");
			head4.setTextContent("Fail");
			contentTitleRow.appendChild(head4);
			org.w3c.dom.Element head5 = doc.createElement("data-header");
			head5.setTextContent("NA");
			contentTitleRow.appendChild(head5);
			org.w3c.dom.Element head6 = doc.createElement("data-header");
			head6.setTextContent("NE");
			contentTitleRow.appendChild(head6);
			org.w3c.dom.Element head7 = doc.createElement("data-header");
			head7.setTextContent("Pass Rate");
			contentTitleRow.appendChild(head7);
			
			for (XMLReportModule module : moduleListList) {
				org.w3c.dom.Element row = doc.createElement("data-row");
				content.appendChild(row);
				
				org.w3c.dom.Element val1 = doc.createElement("data-cell");
				val1.setTextContent(module.getName());
				row.appendChild(val1);
				org.w3c.dom.Element val2 = doc.createElement("data-cell");
				val2.setTextContent(module.getTcCount()+"");
				row.appendChild(val2);
				org.w3c.dom.Element val3 = doc.createElement("data-cell");
				val3.setTextContent(""+module.getPassTc());
				row.appendChild(val3);
				org.w3c.dom.Element val4 = doc.createElement("data-cell");
				val4.setTextContent(""+module.getFailTc());
				row.appendChild(val4);
				org.w3c.dom.Element val5 = doc.createElement("data-cell");
				val5.setTextContent(""+module.getNaTc());
				row.appendChild(val5);
				org.w3c.dom.Element val6 = doc.createElement("data-cell");
				val6.setTextContent(""+module.getNeTc());
				row.appendChild(val6);
				org.w3c.dom.Element val7 = doc.createElement("data-cell");
				val7.setTextContent(""+module.getPassRate());
				row.appendChild(val7);
			}
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
		    StringWriter writer = new StringWriter();
		    StreamResult result = new StreamResult(writer);
		    transformer.transform(new DOMSource(doc), result);

		    PrintWriter pw = new PrintWriter(new File( "report.xml"));
//		    pw.write("<?xml-stylesheet href=\"style1.css\"?>\n");
		    pw.write(writer.toString());
		    pw.flush();
		    pw.close();
		    
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
