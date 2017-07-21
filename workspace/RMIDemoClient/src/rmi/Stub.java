package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Stub  extends Remote {
	public int tapOnMiddle(int x, int y) throws RemoteException;
	
}