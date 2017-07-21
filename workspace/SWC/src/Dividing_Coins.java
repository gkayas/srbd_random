
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Dividing_Coins {
	private static int[] val;
	static int [][] mem ;
	public static void main(String []args) throws FileNotFoundException {
		Scanner sc = new Scanner(new File("coin_in.txt"));//System.in);
		int T = sc.nextInt();
		int tc = 1;
		while(tc <= T) {
			mem = new int [101] [50601];
			for(int i = 0; i < 101; i++) {
				for(int j = 0; j < 50001; j++) {
					mem[i][j] = -1;
				}
			}
			
			int coinNum = sc.nextInt();
			val = new int[coinNum];
			int sum = 0;
			for(int i = 0; i<coinNum; i++) {
				val[i] = sc.nextInt();
				sum = sum + val[i];
			}
			int half = sum/2;
			half = divide(coinNum-1, half);
			System.out.println((sum-2*half));
			tc++;
		}
		
	}
	
	static int divide(int coinNum, int limit) {
		if( coinNum == -1 || limit == 0) {
			return 0;
		} 
		if(mem[coinNum][limit] == -1) {
			if(val[coinNum] > limit) {
				return divide(coinNum-1, limit);	
			} else {
				int x = val[coinNum] + divide(coinNum-1, limit-val[coinNum]);
				int y =  divide(coinNum-1, limit);
				mem[coinNum][limit] = (x > y)?x:y;
			}
		}
		return mem[coinNum][limit];
	}
}
