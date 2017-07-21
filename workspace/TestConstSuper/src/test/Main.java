package test;

import java.lang.reflect.InvocationTargetException;

public class Main {
	public static void main(String []args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		Class<?> c = Class.forName("test." + "CustomChild");
		Custom t = (Custom) c.getConstructor(String.class, String.class).newInstance("we", "me");
		System.out.println(t);
	}
}
