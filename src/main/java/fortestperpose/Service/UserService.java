package fortestperpose.Service;

import javax.swing.*;

public class UserService {
    private JFrame frame;
    public UserService (JFrame root){
        this.frame= root;
    }
    public int register(){
        return JOptionPane.showConfirmDialog(frame,"Have account?","Choose:",JOptionPane.YES_NO_OPTION);
    }

    public String newUser(){
        String un;
        do{
            un = JOptionPane.showInputDialog(frame,"Enter user name");
            if(!(un ==null || un.equalsIgnoreCase(""))){
                break;
            }
        }while (true);
        return un;
    }
}
