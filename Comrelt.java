package Sport_manager;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
public	class Comrelt extends JPanel{
    private JLabel shc=new JLabel("查询项目");
    private JButton sch=new JButton("查询"),
            adds=new JButton("录入"),
            athinf=new JButton("运动员信息");
    private JTextField cdtn=new JTextField(10);
    private String[] heads={
            "运动员编号","运动员姓名","比赛编号1","赛事名称1","初赛成绩1","初赛名次1","决赛成绩1","决赛名次1","比赛编号2","赛事名称2","初赛成绩2","初赛名次2","决赛成绩2","决赛名次2"
    };
    private String[] descripton={"","比赛编号","运动员姓名","赛事名称","运动员编号"};
    private JComboBox schcdn=new JComboBox(descripton);
    private DefaultTableModel model = new DefaultTableModel(null,heads);
    private JTable table = new JTable(model);
    private ActionListener b3=new ActionListener(){
        public void actionPerformed(ActionEvent e){
            String query = null;
            while(model.getRowCount()!=0)
                model.removeRow(0);
            try{
                int condition=schcdn.getSelectedIndex();
                if(condition==0)
                    query="SELECT athlete_number,athlete_name,first_race_number,first_race_name,first_preliminaries,first_preliminary_ranking,first_final_result,first_final_ranking,second_race_number,second_race_name,second_preliminaries,second_preliminary_ranking,second_final_result,second_final_ranking FROM 项目成绩表" ;
                if(condition==1)
                    query="SELECT athlete_number,athlete_name,first_race_number,first_race_name,first_preliminaries,first_preliminary_ranking,first_final_result,first_final_ranking,second_race_number,second_race_name,second_preliminaries,second_preliminary_ranking,second_final_result,second_final_ranking FROM 项目成绩表 WHERE first_race_number='"+cdtn.getText()+"' or second_race_number='" +cdtn.getText() + "'" ;
                if(condition==2)
                    query="SELECT athlete_number,athlete_name,first_race_number,first_race_name,first_preliminaries,first_preliminary_ranking,first_final_result,first_final_ranking,second_race_number,second_race_name,second_preliminaries,second_preliminary_ranking,second_final_result,second_final_ranking FROM 项目成绩表 WHERE athlete_name='"+cdtn.getText()+"'" ;
                if(condition==3)
                    query="SELECT athlete_number,athlete_name,first_race_number,first_race_name,first_preliminaries,first_preliminary_ranking,first_final_result,first_final_ranking,second_race_number,second_race_name,second_preliminaries,second_preliminary_ranking,second_final_result,second_final_ranking FROM 项目成绩表 WHERE first_race_name='"+cdtn.getText()+"' or second_race_name='" + cdtn.getText() + "'";
                if(condition==4)
                    query="SELECT athlete_number,athlete_name,first_race_number,first_race_name,first_preliminaries,first_preliminary_ranking,first_final_result,first_final_ranking,second_race_number,second_race_name,second_preliminaries,second_preliminary_ranking,second_final_result,second_final_ranking FROM 项目成绩表 WHERE athlete_number='"+cdtn.getText()+"'" ;

                Connect sel=new Connect();
                ResultSet relt=sel.ExecuteQuery(query);
                while(relt.next())
                {
                    model.addRow(new Object[]{relt.getString("athlete_number"),relt.getString("athlete_name"),relt.getString("first_race_number") ,relt.getString("first_race_name"),relt.getString("first_preliminaries"),relt.getString("first_preliminary_ranking"),relt.getString("first_preliminary_ranking")
                            ,relt.getString("first_final_result"),relt.getString("second_race_number"),relt.getString("second_race_name"),relt.getString("second_preliminaries"),relt.getString("second_preliminary_ranking")
                            ,relt.getString("second_final_result"),relt.getString("second_final_ranking")});
                }

            } catch(Exception f) {
                f.printStackTrace();
            }

        }
    };
    JFrame showframe=new JFrame();
    JScrollPane scrollpane =new JScrollPane(table);
    private ActionListener b2=new ActionListener(){
        public void actionPerformed(ActionEvent e){
            Manager.contPanel5.remove(Manager.pane9);
            Manager.contPanel5.add(Manager.pane10);
            Manager.contPanel5.revalidate();
            Manager.contPanel5.repaint();
        }
    };
    private ActionListener b4=new ActionListener(){
        public void actionPerformed(ActionEvent e){
            try{
                int selectRow=table.getSelectedRow();
                if(selectRow==-1){
                    JOptionPane.showMessageDialog(null, "没有选择记录","错误",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String value=(String)table.getValueAt(selectRow,2);

                String sqlquery="SELECT student_id,student_name,student_department,student_sex,student_gname_1,student_gname_2 FROM 运动员 WHERE anum='"+value+"'";
                Connect que=new Connect();
                ResultSet rs=que.ExecuteQuery(sqlquery);
                String[] ainf={"","","","","","","",""};
                while(rs.next()){
                    ainf[0]=rs.getString("student_id");
                    ainf[1]=rs.getString("student_name");
                    ainf[2]=rs.getString("student_department") ;
                    ainf[3]=rs.getString("student_sex");
                    ainf[4]=rs.getString("student_gname_1");
                    ainf[5]=rs.getString("student_gname_2");
                }
                if(ainf[1].equals("")){
                    JOptionPane.showMessageDialog(null, "没有相应记录","提示",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                showframe.setTitle("运动员信息");
                showframe.setSize(400,200);
                showframe.setVisible(true);
            }catch(Exception f) {
                f.printStackTrace();


            }
        }
    };

    public  Comrelt(){
        setPreferredSize(new Dimension(1000,300));
        setLayout(null);
        adds.addActionListener(b2);
        table.getTableHeader().setReorderingAllowed(false);
        shc.setBounds(30,20,120,20);
        schcdn.setBounds(150,20,180,20);
        cdtn.setBounds(330,20,120,20);
        sch.setBounds(450,20,120,20);
        adds.setBounds(840,20,120,20);
        scrollpane.setBounds(30,50,900,300);
        sch.addActionListener(b3);
        athinf.addActionListener(b4);
        add(shc);
        add(schcdn);
        add(cdtn);
        add(sch);
        add(adds);
        add(athinf);
        add(scrollpane);
    }
}
class Comreltinf extends JPanel{
    //"运动员编号","运动员姓名","比赛编号1","赛事名称1","初赛成绩1","初赛名次1","决赛成绩1","决赛名次1",
    // "比赛编号2","赛事名称2","初赛成绩2","初赛名次2","决赛成绩2","决赛名次2"
    private JLabel csnum1=new JLabel("比赛编号1"),
            cname1=new JLabel("赛事名称1"),
            csnum2=new JLabel("比赛编号2"),
            cname2=new JLabel("赛事名称2"),
            athnum=new JLabel("运动员编号"),
            athname=new JLabel("运动员姓名"),
            ppos1=new JLabel("初赛名次1"),
            fpos1=new JLabel("决赛名次1"),
            ppos2=new JLabel("初赛名次2"),
            fpos2=new JLabel("决赛名次2"),
            pachmnt1=new JLabel("初赛成绩1"),
            fachmnt1=new JLabel("决赛成绩1"),
            pachmnt2=new JLabel("初赛成绩2"),
            fachmnt2=new JLabel("决赛成绩2");

    private JTextField comsnum1=new JTextField(20),
            comsnum2=new JTextField(20),
            comname1=new JTextField(20),
            comname2=new JTextField(20),
            athenum=new JTextField(20),
            athename=new JTextField(20),
            ppostion1=new JTextField(20),
            ppostion2=new JTextField(20),
            fpostion1=new JTextField(20),
            fpostion2=new JTextField(20),
            pachment1=new JTextField(20),
            fachment1=new JTextField(20),
            pachment2=new JTextField(20),
            fachment2=new JTextField(20);

    private JButton sav=new JButton("提交"),
            ext=new JButton("返回");
    private ActionListener b3=new ActionListener(){
        public void actionPerformed(ActionEvent e){
            String sanum1=comsnum1.getText();
            String saname1=comname1.getText();
            String sanum2=comsnum2.getText();
            String saname2=comname2.getText();
            String sbegnum=athenum.getText();
            String saage=athename.getText();
            String psathsex1=ppostion1.getText();
            String pssprts1=pachment1.getText();
            String fsathsex1=fpostion1.getText();
            String fssprts1=fachment1.getText();
            String psathsex2=ppostion2.getText();
            String pssprts2=pachment2.getText();
            String fsathsex2=fpostion2.getText();
            String fssprts2=fachment2.getText();
            String bnum = null;
            String groupnum=null;
            int gdnum = 0,slnum=0,cpnum=0;
            String insert="INSERT INTO 项目成绩表 values('"+sbegnum+"','"+sanum1+"','"+saname1+"','"+pssprts1+"','"+psathsex1+"','"+fssprts1+"','"+fsathsex1+"','"+pssprts2+"','"+psathsex2+"','"+fssprts2+"','"+fsathsex2+"','"+sanum2+"','"+saname2+"','"+saage+"')";
            Connect sql=new Connect();
            sql.ExecuteUpdate(insert);
        }
    };
    private ActionListener b1=new ActionListener(){
        public void actionPerformed(ActionEvent e){
            Manager.contPanel5.remove(Manager.pane9);
            Manager.contPanel5.add(Manager.pane10);
            Manager.contPanel5.revalidate();
            Manager.contPanel5.repaint();
        }

    };
    public Comreltinf (){
        setLayout(new GridLayout(13,2));
        sav.addActionListener(b3);
        ext.addActionListener(b1);
//csnum1=new JLabel("比赛编号1"),
//            cname1=new JLabel("赛事名称1"),
//            csnum2=new JLabel("比赛编号2"),
//            cname2=new JLabel("赛事名称2"),
//            athnum=new JLabel("运动员编号"),
//            athname=new JLabel("运动员姓名"),
//            pos1=new JLabel("初赛名次1"),
//            fpos1=new JLabel("决赛名次1"),
//            ppos2=new JLabel("初赛名次2"),
//            fpos2=new JLabel("决赛名次2"),
//            pachmnt1=new JLabel("初赛成绩1"),
//            fachmnt1=new JLabel("决赛成绩1"),
//            pachmnt2=new JLabel("初赛成绩2"),
//            fachmnt2=new JLabel("决赛成绩2");

        //comsnum1=new JTextField(20),
        //            comsnum2=new JTextField(20),
        //            comname1=new JTextField(20),
        //            comname2=new JTextField(20),
        //            athenum=new JTextField(20),
        //            athename=new JTextField(20),
        //            ppostion1=new JTextField(20),
        //            ppostion2=new JTextField(20),
        //            fpostion1=new JTextField(20),
        //            fpostion2=new JTextField(20),
        //            pachment1=new JTextField(20),
        //            fachment1=new JTextField(20),
        //            pachment2=new JTextField(20),
        //            fachment2=new JTextField(20);
        add(csnum1);
        add(comsnum1);
        add(cname1);
        add(comname1);
        add(athnum);
        add(athenum);
        add(athname);
        add(athename);
        add(ppos1);
        add(ppostion1);
        add(fpos1);
        add(fpostion1);
        add(pachmnt1);
        add(pachment1);
        add(fachmnt1);
        add(fachment1);
        add(csnum2);
        add(comsnum2);
        add(cname2);
        add(comname2);
        add(ppos2);
        add(ppostion2);
        add(fpos2);
        add(fpostion2);
        add(pachmnt2);
        add(pachment2);
        add(fachmnt2);
        add(fachment2);
        add(sav);
        add(ext);
    }
}
class Athinf extends JPanel{
    private JLabel anum=new JLabel("运动员编号"),
            athnum=new JLabel(""),
            aname=new JLabel("运动员姓名"),
            athname=new JLabel(""),
            bgnum=new JLabel("所属团体"),
            abgnum=new JLabel(""),
            sex=new JLabel("性别"),
            athsex=new JLabel(""),
            sport=new JLabel("参加项目1"),
            athsport=new JLabel(""),
            bgp=new JLabel("参加项目2"),
            athbgp=new JLabel("");
    public Athinf(){
        setPreferredSize(new Dimension(200,300));
        setLayout(new GridLayout(4,4));
        add(anum);
        add(athnum);
        add(aname);
        add(athname);
        add(bgnum);
        add(abgnum);
        add(sex);
        add(athsex);
        add(sport);
        add(athsport);
        add(bgp);
        add(athbgp);
    }
    public  void Showinf(String[] inf){
        athnum.setText(inf[0]);
        athname.setText(inf[1]);
        abgnum.setText(inf[2]);
        athsex.setText(inf[3]);
        athsport.setText(inf[4]);
        athbgp.setText(inf[5]);
        repaint();
    }
}
