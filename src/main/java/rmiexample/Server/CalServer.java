package rmiexample.Server;

import hellop.Hello;
import rmiexample.Common.Cal;

import java.rmi.Naming;

public class CalServer {
    public static void main(String[] args) {
        try {
            Cal obj = new CalImplement();
            Naming.rebind("Sum12", obj);
            System.out.println("HelloObject is registried");
        }catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
}
