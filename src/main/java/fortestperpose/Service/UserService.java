package fortestperpose.Service;

import fortestperpose.Entity.User;

import javax.swing.*;

public class UserService {
    private JFrame frame;
    private User user;
    public UserService (JFrame root){
        this.frame= root;
    }
    public int register(){
        return JOptionPane.showConfirmDialog(frame,"Have account?","Choose:",JOptionPane.YES_NO_OPTION);
    }

    public void newUser(){
        user = new User();
        String un;
        do{
            un = JOptionPane.showInputDialog(frame,"Enter user name: ");
            if(!(un ==null || un.trim().equalsIgnoreCase(""))){
                break;
            }
        }while (true);

        user.setUsername(un.trim());

    }

    public void enterPass(){
        String pw,cfpw;
        do{
            pw = JOptionPane.showInputDialog(frame,"Enter password: ");
            if (pw ==null || pw.trim().equals("") || pw.trim().length()< 4 ){
                continue;
            }else {
                cfpw = JOptionPane.showInputDialog(frame,"Enter confirm password: ");
                if (cfpw ==null || cfpw.trim().equals("") || cfpw.trim().length()< 4 ){
                    continue;
                }else {
                    break;
                }
            }
        }while (true);
        user.setPassword(pw);
    }

    public User getUser() {
        return user;
    }
}
