package rmi;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiServerIntf extends Remote {
    public String getMessage() throws RemoteException;
    public int sum(int a, int b) throws RemoteException;
    public String getMessage(String message) throws RemoteException;
}