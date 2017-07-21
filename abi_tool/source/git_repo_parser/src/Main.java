/*
 * This tool will extract the git package path
 * from a .package file named "old-pkg.list" and
 * give "modules_git_repo.txt" file as output containing
 * all the  git package path from .package file
 * 
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
	
	
	
	public static void main(String[] args){
		ArrayList<String> list = new ArrayList<String>();
		try {
			PrintWriter pw = new PrintWriter(new File("modules_git_repo.txt"));
			Scanner sc = new Scanner(new File("old-pkg.list"));
			while(sc.hasNext()){
				String s = sc.nextLine();
				String [] ar = s.split(" ");
				if(!list.contains(ar[ar.length-1])){
					list.add(ar[ar.length-1]);
					int index = ar[ar.length-1].indexOf('#');
					if(index >= 0){
						ar[ar.length-1] = ar[ar.length-1].substring(0, index);//removing the part after "#" from git package path
						pw.append(ar[ar.length-1]+"\n");
					}
				}
			}
			sc.close();
			pw.flush();
			pw.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}

}
