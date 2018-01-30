package rmiexample.Client;



import hellop.HelloItf;
import rmiexample.Common.Cal;

import java.rmi.Naming;

public class CalClient {
    public static void main(String[] args) {
        String helloURL = "Sum12";
        Cal object = null;
        try {
            object = (Cal) Naming.lookup( helloURL);
            int total = object.Add(1,2);
            System.out.println(total);
        }catch (Exception e) {
            System.out.println("Client Error :" + e);
        }
    }
}
