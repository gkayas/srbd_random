import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Per {
	static int N;
	static int P;
	static boolean visited[];
	static String [] input; 
	static String [] out;
	static int comIndex = -1;
	public static void main(String [] ar) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("input.txt"));//System.in);
		int T = sc.nextInt();
		int tc = 1;
		while(tc <= T) {
			comIndex = -1;
			System.out.println("Testcase# "+tc);
			N = sc.nextInt();
			P = sc.nextInt();
			input = new String [N];
			visited = new boolean [N];
			out = new String[P];
			for(int i = 0; i< N ; i++) {
				input[i] = sc.next();
			}
			
//			solveCom(0);
			solvePer(1);
			
			tc++;
		}
		
	}
	
	private static void solvePer(int i) {
		if( i == P) {
			print();

			return;
		}
		
		for(int n = 0; n < N ; n++) {
			if(!visited[n]) {
				out[i] = input[n];
				visited[n] = true;
				solvePer(i+1);
				visited[n] = false;
				
			}
		}
	}

	private static void solveCom(int i) {
		if( i == P) {
			print();
			return;
		}
		
		for(int n = comIndex+1; n < N ; n++) {
				out[i] = input[n];
				comIndex = n;
				solveCom(i+1);
				comIndex = i;
		}
	}
	
	private static void print() {
		for (int i = 0; i < P; i++) {
			System.out.print(out[i]);
		}
		System.out.println();
	}
	
}
