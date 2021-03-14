package Sport_manager;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import static Sport_manager.SwingConsole.*;
public class Login extends JFrame {
    public Login() {
        JLabel user = new JLabel("用户名");
        final JTextField usernm = new JTextField(10);
        JLabel pwd = new JLabel("密码");
        final JPasswordField pswd = new JPasswordField(10);
        JButton login = new JButton("登录");
        setVisible(true);
        Container mk = getContentPane();
        mk.add(user);
        mk.add(usernm);
        mk.add(pwd);
        mk.add(pswd);
        mk.add(login);
        setBounds(300, 300, 300, 290);
        mk.setLayout(null);
        user.setBounds(10, 40, 50, 18);
        pwd.setBounds(10, 80, 50, 18);
        usernm.setBounds(60, 40, 200, 18);
        pswd.setBounds(60, 80, 200, 18);
        login.setBounds(110, 180, 60, 30);
        login.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usernm.equals("") || pswd.equals("")) {
                    JOptionPane.showMessageDialog(null, "请输入用户名和密码！");
                }
                else {
                    new Connect();
                    String Password = new String(pswd.getPassword());
                    String sql = "select user_account,user_password,permissions from 用户 where user_account = '" + usernm.getText() + "" +
                            "' and user_password = '" + Password + "';";
                    Connect sel = new Connect();
                    ResultSet res = sel.ExecuteQuery(sql);
                    try {
                        if (res.next()) {
                            System.out.println(res.getString("permissions"));
                            System.out.println(res.getString("permissions").equals("1"));
                            String s = res.getString("permissions");
                            int b = Integer.valueOf(s.charAt(0));
                            System.out.println(b);

                            if (b == 49)
                                run(new Manager(), 1500, 400, "田径运动会管理系统");
                            else
                                run(new manage(), 1500, 400, "田径运动会管理系统");
                        }
                        else
                            JOptionPane.showMessageDialog(null, "请输入正确的用户名或密码！");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }
}
