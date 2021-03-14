package Sport_manager;
import java.sql.*;
import javax.swing.*;
public class Connect {
    private String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private String dbURL="jdbc:sqlserver://localhost:1433;databaseName=Sports_Management";
    private String userName="sa";
    private String userPwd="wyl102600";
    public Connect(){
        try
        {
            Class.forName(driverName);
            System.out.println("加载驱动成功！");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("加载驱动失败！");
        }
        try{
            Connection dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
            System.out.println("连接数据库成功！");
        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.print("SQL Server连接失败！");
        }
    }
    public void ExecuteUpdate(String sql){
        try {
            // 加载驱动程序
            Class.forName(driverName);
            // 连续数据库
            Connection conn = DriverManager.getConnection(dbURL, userName, userPwd);
            if(conn.isClosed())
                JOptionPane.showMessageDialog(null, "数据库连接错误","错误",JOptionPane.ERROR_MESSAGE);
            System.out.println("Succeeded connecting to the Database!");
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "保存成功","提示",JOptionPane.INFORMATION_MESSAGE);
        } catch(ClassNotFoundException e) {
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } catch(com.microsoft.sqlserver.jdbc.SQLServerException e){
            JOptionPane.showMessageDialog(null, "记录重复，请重新输入","提示",JOptionPane.ERROR_MESSAGE);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    public ResultSet ExecuteQuery(String sql){
        String[][] str;
        try {
            // 加载驱动程序
            Class.forName(driverName);
            // 连续数据库
            Connection conn = DriverManager.getConnection(dbURL,userName,userPwd);
            if(!conn.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            return rs;
        } catch(ClassNotFoundException e) {
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } catch(SQLException e) {
            e.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
