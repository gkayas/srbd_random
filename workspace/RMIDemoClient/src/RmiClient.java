import java.rmi.Naming;

import rmi.RmiServerIntf;
import rmi.Stub;

public class RmiClient { 
    public static void main(String args[]) throws Exception {
        RmiServerIntf obj = (RmiServerIntf)Naming.lookup("//localhost/RmiServer");
        System.out.println(obj.getMessage()); 
        System.out.println(obj.sum(2, 3));
        System.out.println(obj.getMessage(" lol"));
        
//    	 Stub obj = (Stub)Naming.lookup("//localhost/RmiServer");
//    	 System.out.println(obj.tapOnMiddle(450, 650));
    	 
    }
}