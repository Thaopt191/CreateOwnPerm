package rmiexample.Server;

import rmiexample.Common.Cal;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalImplement extends UnicastRemoteObject implements Cal {
    protected CalImplement() throws RemoteException {
    }

    public int Add(int x, int y) throws RemoteException {
        return x+y;
    }
}
