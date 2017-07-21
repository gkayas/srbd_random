package rmi;

import java.awt.Point;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Stub extends Remote {
	public int mousePressEmulation(int x, int y) throws RemoteException;
	public int tapOnMiddle(int x, int y) throws RemoteException;
	public Point getDeviceRegion() throws RemoteException;
}
