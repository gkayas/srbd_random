import java.util.Scanner;

public class Main {
	
	public static int N,R;
	public static int [] data; 
	public static boolean [] used; 
	public static void main(String [] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		R = sc.nextInt();
		used = new boolean[N];
		data = new int [R];
		solve(0);
		sc.close();
	}
	
	public static void solve(int i) {
		
		if(i == R) {
			for (int n : data) {
				System.out.print(n);
			}
			System.out.println();
			return;
		}
		
		for(int j = 0; j<R ; j++) {
			if(!used[j]) {
				used[j] = true;
				data[i] = j;
				solve(i+1);
				used[j] = false;
			}
		}
	}
}
