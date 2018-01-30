package hellop;

import java.rmi. server.UnicastRemoteObject;
import java.rmi.RemoteException;
public class Hello extends UnicastRemoteObject implements HelloItf {
    public Hello() throws RemoteException {
        super();
    }
    public String sayHello() {
        return "Hello World !";
    }
}