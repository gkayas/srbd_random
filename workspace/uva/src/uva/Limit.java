package uva;

class A {
	int x = 3;
	A(int a) {
		x = a;
	}
	
	void inc() {
		x++;
	}
	
	void inc(int a) {
		x = x+ a;
	}
	
	void print () {
		System.out.println(x);
	}
}

public class Limit {
//	Integer x = new Integer(3);
	int [] ar = {1,2,3};
	A a = new A(3);
	public static void inc(Limit l) {
		l.a.inc();
//		= new Integer(l.x.intValue() + 1);
	}
	
	void inc(Limit l, int x) {
		l.a.inc(x);
	}
	
	public static void incx(A x/*, Limit l*/) {
//		System.out.println(x.hashCode());
		x.inc();
//		System.out.println("$$$");
//		
//		print(l);
	}
	

	
	public static void print(Limit l){
		l.a.print();
	}
	
	public static void car(int []ar){
		ar[0] = 4;
	} 
	
	public static void main(String []args) {
		
		Limit l1 = new Limit();
		inc(l1);
//		print(l1);
		incx(l1.a);
		car(l1.ar);
		
//		print(l1);
		
		System.out.println(l1.ar[0]);
	}
}