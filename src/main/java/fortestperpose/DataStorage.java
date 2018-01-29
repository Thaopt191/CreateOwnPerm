package fortestperpose;

import java.io.*;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.HashMap;
import java.util.Hashtable;

public class DataStorage {
    private File highScoreFile;
    HashMap<String,Hashtable<String,String>> db;
    public DataStorage(){
        SecurityManager sm = System.getSecurityManager();
        AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
                String path =
                        System.getProperty("user.home") +
                                File.separator +
                                ".datatemp";
                highScoreFile = new File(path);
                return null;
            }
        });
    }

    public void setData(final String name, final Hashtable<String,String> data) throws IOException {
        SecurityManager sm = System.getSecurityManager();
//        if (sm != null) {
//            sm.checkPermission(new HighScorePermission(gameName));
//        }

        // need a doPrivileged block to manipulate the file
        try {
            AccessController.doPrivileged(new PrivilegedExceptionAction() {
                public Object run() throws IOException {
                    HashMap<String,Hashtable<String,String>> scores = null;
                    // try to open the existing file. Should have a locking
                    // protocol (could use File.createNewFile).
                    try {
                        FileInputStream fis =
                                new FileInputStream(highScoreFile);
                        ObjectInputStream ois = new ObjectInputStream(fis);
                        scores = (HashMap<String,Hashtable<String,String>>) ois.readObject();
                    } catch (Exception e) {
                        // ignore, try and create new file
                    }

                    // if scores is null, create a new hashtable
                    if (scores == null)
                        scores = new HashMap<String, Hashtable<String,String>>();

                    // update the score and save out the new high score
                    scores.put(name, data);
                    FileOutputStream fos = new FileOutputStream(highScoreFile);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(scores);
                    oos.close();
                    return null;
                }
            });
        } catch (PrivilegedActionException pae) {
            throw (IOException) pae.getException();
        }
    }

    public Hashtable<String,String> getData(final String name)
            throws IOException, ClassNotFoundException
    {
        //check permission first
        SecurityManager sm = System.getSecurityManager();
//        if (sm != null) {
//            sm.checkPermission(new HighScorePermission(gameName));
//        }

        Hashtable<String,String> score = null;

        // need a doPrivileged block to manipulate the file
        try {
            score = (Hashtable<String,String>) AccessController.doPrivileged(
                    new PrivilegedExceptionAction() {
                        public Object run()
                                throws IOException, ClassNotFoundException
                        {
                            HashMap<String,Hashtable<String,String>> scores = null;
                            // try to open the existing file. Should have a locking
                            // protocol (could use File.createNewFile).
                            FileInputStream fis =
                                    new FileInputStream(highScoreFile);
                            ObjectInputStream ois = new ObjectInputStream(fis);
                            scores = (HashMap<String,Hashtable<String,String>>) ois.readObject();

                            // get the high score out
                            return scores.get(name);
                        }
                    });
        } catch (PrivilegedActionException pae) {
            Exception e = pae.getException();
            if (e instanceof IOException)
                throw (IOException) e;
            else
                throw (ClassNotFoundException) e;
        }
        if (score == null)
            return null;
        else
            return score;
    }

    public boolean checkIfUserExist(final String name)
            throws IOException, ClassNotFoundException {
        //check permission first
        SecurityManager sm = System.getSecurityManager();
//        if (sm != null) {
//            sm.checkPermission(new HighScorePermission(gameName));
//        }
        HashMap<String, Hashtable<String, String>> flag;


        try {
            flag = (HashMap) AccessController.doPrivileged(
                    new PrivilegedExceptionAction() {
                        public Object run()
                                throws IOException, ClassNotFoundException {
                            HashMap<String, Hashtable<String, String>> scores = null;
                            // try to open the existing file. Should have a locking
                            // protocol (could use File.createNewFile).
                            FileInputStream fis =
                                    new FileInputStream(highScoreFile);
                            ObjectInputStream ois = new ObjectInputStream(fis);
                            scores = (HashMap<String, Hashtable<String, String>>) ois.readObject();

                            // get the high score out
                            return scores;
                        }
                    });
            return flag.containsKey(name);
        } catch (PrivilegedActionException pae) {
            pae.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        DataStorage t = new DataStorage();

        t.setData("thaopt2",new Hashtable<String, String>());
    }
}
