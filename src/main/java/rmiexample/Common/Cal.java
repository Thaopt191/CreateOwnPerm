package rmiexample.Common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Cal extends Remote {
     int Add(int x,int y) throws RemoteException;
}
