package Sport_manager;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

class Athelete extends JPanel{
    //  "运动员姓名","所属运动队","运动员编号","运动队编号","性别","参赛项目1","参赛项目2"
    //输出运动员成绩要  运动员编号，运动员姓名（运动员姓名号码对照表，运动员编号）
    //                项目名称，初赛成绩，初赛排名，决赛成绩，决赛排名，运动员编号（项目成绩表，运动员编号+比赛编号）
    //                项目编号（运动员表，运动员编号）

    //输出运动队总分要 运动队编号，运动队名称，运动队成绩，运动队排名（运动队总分表，运动队编号）
    //输出比赛项目要  项目编号，项目名称，项目类型，初赛时间，决赛时间（比赛项目表，项目编号）
    private JLabel shc=new JLabel("查询项目");
    private JButton sch=new JButton("查询"),
            adds=new JButton("录入");
    private JTextField cdtn=new JTextField(10);
    private String[] heads={
            "运动员编号","姓名","所属运动队","性别","参赛项目1","参赛项目2"
    };
    private String[] descripton={"","运动员编号","运动员姓名","所属运动队"};
    private JComboBox schcdn=new JComboBox(descripton);
    private DefaultTableModel model = new DefaultTableModel(null,heads);

    private JTable table = new JTable(model){
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    JScrollPane scrollpane =new JScrollPane(table);

    private ActionListener b2=new ActionListener(){
        public void actionPerformed(ActionEvent e){
            Manager.contPanel1.remove(Manager.pane1);
            Manager.contPanel1.add(Manager.pane2);
            Manager.contPanel1.revalidate();
            Manager.contPanel1.repaint();
        }
    };
    private ActionListener b3=new ActionListener(){
        public void actionPerformed(ActionEvent e){
            String query = null;
            while(model.getRowCount()!=0)
                model.removeRow(0);
            try{
                int condition=schcdn.getSelectedIndex();//                                                                              运动员编号","运动员姓名","所属运动队
                if(condition==0)
                    query="SELECT student_id,student_name,student_department,student_sex,student_gname_1,student_gname_2 FROM 运动员 WHERE student_id like'%"+cdtn.getText()+"%'or  student_name like '%"+cdtn.getText()+"%' or student_department like '%"+cdtn.getText()+"%'" ;
                if(condition==1)
                    query="SELECT student_id,student_name,student_department,student_sex,student_gname_1,student_gname_2 FROM 运动员 WHERE student_id like'%"+cdtn.getText()+"%'" ;
                if(condition==2)
                    query="SELECT student_id,student_name,student_department,student_sex,student_gname_1,student_gname_2 FROM 运动员 WHERE student_name like '%"+cdtn.getText()+"%'" ;
                if(condition==3)
                    query="SELECT student_id,student_name,student_department,student_sex,student_gname_1,student_gname_2 FROM 运动员 WHERE student_department like '%"+cdtn.getText()+"%'" ;

                Connect sel=new Connect();
                ResultSet relt=sel.ExecuteQuery(query);
                while(relt.next())
                {
                    model.addRow(new Object[]{relt.getString("student_id"),relt.getString("student_name"),relt.getString("student_department") ,relt.getString("student_sex"),relt.getString("student_gname_1"),relt.getString("student_gname_2")       });
                }

            } catch(Exception f) {
                f.printStackTrace();
            }
            if(model.getRowCount()==0)
                JOptionPane.showMessageDialog(null, "没有相应记录","提示",JOptionPane.WARNING_MESSAGE);

        }
    };
    public  Athelete(){
        setPreferredSize(new Dimension(1000,300));
        setLayout(null);
        table.getTableHeader().setReorderingAllowed(false);
        shc.setBounds(30,20,120,20);
        schcdn.setBounds(150,20,180,20);
        cdtn.setBounds(330,20,120,20);
        sch.setBounds(450,20,120,20);
        adds.setBounds(840,20,120,20);
        scrollpane.setBounds(30,50,900,300);
        sch.addActionListener(b3);
        adds.addActionListener(b2);
        add(shc);
        add(schcdn);
        add(cdtn);
        add(sch);
        add(adds);
        add(scrollpane);
    }
}
class Atheleteinf extends JPanel{
    private JLabel athnum = new JLabel("运动员编号"),
            athname = new JLabel("运动员姓名"),
            bgname = new JLabel("所属运动队"),
            begnum = new JLabel("所属运动队编号"),
            athsex = new JLabel("性别"),
            sprtsn1 = new JLabel("参赛项目1"),
            sprtsn2 = new JLabel("参赛项目2"),
            sprts1 = new JLabel("参赛项目1编号"),
            sprts2 = new JLabel("参赛项目2表闹");
    private JTextField anum=new JTextField(20),
            aname=new JTextField(20),
            begname=new JTextField(20),
            bgnum=new JTextField(20),
            //aage=new JTextField(20),
            asex=new JTextField(20),
            sportn1=new JTextField(20),
            sportn2 = new JTextField(20),
            sport1=new JTextField(20),
            sport2 = new JTextField(20),
            bgp=new JTextField(20);
    private JButton sav=new JButton("提交"),
            ext=new JButton("返回");
    private ActionListener b3=new ActionListener(){
        public void actionPerformed(ActionEvent e){
            String sanum=anum.getText();
            String saname=aname.getText();
            String sbename=begname.getText();
            String sbegnum=bgnum.getText();
            String sathsex=asex.getText();
            String ssprt1=sport1.getText();
            String ssprt2=sport2.getText();
            String ssprtsn1=sportn1.getText();
            String ssprtsn2=sportn2.getText();
            String sbgp=bgp.getText();
            if(sanum.equals("")||saname.equals(""))
            {
                JOptionPane.showMessageDialog(null, "运动员编号不能为空","提示",JOptionPane.ERROR_MESSAGE);
                return;
            }
            String insert="INSERT INTO 运动员 values('"+saname+"','"+sbename+"','"+sanum+"','"+sbegnum+"','"+sathsex+"','"+ssprtsn1+"','"+ssprtsn2+"','"+ssprt1+"','"+ssprt2+"')";
            Connect sql=new Connect();
            sql.ExecuteUpdate(insert);

            anum.setText(null);
            aname.setText(null);
            bgnum.setText(null);
            //aage.setText(null);
            asex.setText(null);
            sportn1.setText(null);
            sportn2.setText(null);
            //bgp.setText(null);
            Manager.contPanel1.remove(Manager.pane1);
            Manager.contPanel1.add(Manager.pane2);
            Manager.contPanel1.revalidate();
            Manager.contPanel1.repaint();
        }
    };
    private ActionListener b1=new ActionListener(){
        public void actionPerformed(ActionEvent e){
            Manager.contPanel1.remove(Manager.pane2);
            Manager.contPanel1.add(Manager.pane1);
            Manager.contPanel1.revalidate();
            Manager.contPanel1.repaint();
        }
    };
    public Atheleteinf (){
        setLayout(new GridLayout(3,2));
        sav.addActionListener(b3);
        ext.addActionListener(b1);
        add(athnum);
        add(anum);
        add(athname);
        add(aname);
        add(begnum);
        add(bgnum);
        add(athsex);
        add(asex);
        add(sprtsn1);
        add(sportn1);
        add(sprtsn2);
        add(sportn2);
        add(sprts1);
        add(sport1);
        add(sprts2);
        add(sport2);
        add(sav);
        add(ext);

    }
}

