package Sport_manager;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import javax.swing.table.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class manage extends JFrame{

    public static Athelete pane1=new Athelete();
    public static Group pane3=new Group();
    public static Sports pane5=new Sports();
    public static Comschedule pane7=new Comschedule();
    public static Comrelt pane9=new Comrelt();
    //public static refer pane11=new refer();
    public static JPanel contPanel1=new JPanel();
    public static JPanel contPanel2=new JPanel();
    public static JPanel contPanel3=new JPanel();
    public static JPanel contPanel4=new JPanel();
    public static JPanel contPanel5=new JPanel();
    public static JPanel contPanel6=new JPanel();
    private JTabbedPane tabp=new JTabbedPane();
    public manage(){
        setTitle("田径运动会管理系统");
        contPanel1.add(pane1);
        tabp.addTab("运动员",null,contPanel1);
        contPanel2.add(pane3);
        tabp.addTab("参赛团体",null,contPanel2);
        contPanel3.add(pane5);
        tabp.addTab("赛事信息",null,contPanel3);
        contPanel4.add(pane7);
        tabp.addTab("比赛日程",null,contPanel4);
        contPanel5.add(pane9);
        tabp.addTab("比赛成绩",null,contPanel5);
      //  contPanel5.add(pane11);
     //   tabp.addTab("姓名号码对照表",null,contPanel6);
        add(tabp);
    }
}