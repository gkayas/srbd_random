package utils;

import java.awt.List;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import models.*;

public class HTMLParser {
	
	public static ArrayList<StockInfo> getData() {
		System.out.println("get data!!");
		URL url;
		ArrayList<StockInfo> stockInfoList = new ArrayList<StockInfo>();
		try {
			url = new URL("http://www.dsebd.org/");
			Document doc = Jsoup.connect("http://www.dsebd.org/").get() ;
			
			Element marq = doc.getElementById("mq2");
//			System.out.println(marq.html());
			Elements infos = marq.select("td");
			
//			System.out.println(infos.size());
			
//			System.out.println(infos.get(0).text());
			Scanner sc ;
			for (Element element : infos) {
				if(element.attr("width").equals("100%")) {
					String target = element.text();
					target = target.replace("\u00a0"," ");
					sc = new Scanner(target);
					String company = sc.next();
					String price = sc.next();
					String taka = sc.next();
					String per = sc.next();
					StockInfo info = new StockInfo(company, price, taka, per);
//					System.out.println(company+"  "+price+"  "+taka+"  "+per);
					
					if(!stockInfoList.contains(info)) {
						stockInfoList.add(info);
					}
					
				}
				
			}
			
		} catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return stockInfoList;
	} 
	
	public static void test() {
		System.out.println("it is inside timer task");
	}
}
