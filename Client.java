package Sport_manager;
import javax.swing.JFrame;
public class Client{
    public static Login logn=new Login();
    public static void main(String[] args)
    {
        logn.setTitle("欢迎使用田径运动会管理系统");
        logn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        logn.setSize(300,200);
        logn.setVisible(true);
    }
}