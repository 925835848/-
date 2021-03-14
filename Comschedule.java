package Sport_manager;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public	class Comschedule extends JPanel{
    private JLabel shc=new JLabel("查询项目");
    private JButton sch=new JButton("查询"),
            adds=new JButton("录入");
    private JTextField cdtn=new JTextField(10);
    private String[] heads={
            "比赛编号","比赛名称","比赛类型","初赛时间","决赛时间"
    };
    private String[] descripton={"","比赛名称","比赛类型"};
    private JComboBox schcdn=new JComboBox(descripton);
    private DefaultTableModel model = new DefaultTableModel(null,heads);
    private JTable table = new JTable(model);

    JScrollPane scrollpane =new JScrollPane(table);
    private ActionListener b3=new ActionListener(){
        public void actionPerformed(ActionEvent e){
            String query = null;
            while(model.getRowCount()!=0)
                model.removeRow(0);
            try{
                int condition=schcdn.getSelectedIndex();
                if(condition==0)
                    query="SELECT race_number,race_name,race_type,race_preliminary_time,race_final_time FROM 比赛项目表" ;
                if(condition==1)
                    query="SELECT race_number,race_name,race_type,race_preliminary_time,race_final_time FROM 比赛项目表 WHERE race_name='" +cdtn.getText()+"')";
                if(condition==2)
                    query="SELECT race_number,race_name,race_type,race_preliminary_time,race_final_time FROM 比赛项目表 WHERE race_type='" +cdtn.getText()+"')";

                Connect sel=new Connect();
                ResultSet relt=sel.ExecuteQuery(query);
                while(relt.next())
                {
                    model.addRow(new Object[]{relt.getString("race_number"),relt.getString("race_name"),relt.getString("race_type") ,relt.getString("race_preliminary_time"),relt.getString("race_final_time")     });
                }

            } catch(Exception f) {
                f.printStackTrace();
            }

        }
    };
    private ActionListener b2=new ActionListener(){
        public void actionPerformed(ActionEvent e){
            Manager.contPanel4.remove(Manager.pane7);
            Manager.contPanel4.add(Manager.pane8);
            Manager.contPanel4.revalidate();
            Manager.contPanel4.repaint();
        }
    };
    public  Comschedule(){
        setPreferredSize(new Dimension(600,300));
        setLayout(null);
        shc.setBounds(30,20,60,20);
        schcdn.setBounds(90,20,60,20);
        cdtn.setBounds(170,20,60,20);
        sch.setBounds(250,20,60,20);
        adds.setBounds(440,20,60,20);
        scrollpane.setBounds(30,50,500,300);
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
class Comscheduleinf extends JPanel{
    private JLabel csnum=new JLabel("比赛编号"),
            cstype=new JLabel("比赛类型"),
            csptime=new JLabel("初赛时间"),
            spname=new JLabel("赛事名称"),
            csftime=new JLabel("决赛时间");

    private JTextField comsnum=new JTextField(20),
            comstype=new JTextField(20),
            comsptime=new JTextField(20),
            comsftime=new JTextField(20),
            sportname=new JTextField(20);
    private JButton sav=new JButton("提交"),
            ext=new JButton("返回");

    private ActionListener b3=new ActionListener(){
        public void actionPerformed(ActionEvent e){
            String sanum=comsnum.getText();
            String saname=comstype.getText();
            String spbegnum=comsptime.getText();
            String saage=sportname.getText();
            String sfbegnum=comsftime.getText();
            String insert="INSERT INTO 比赛项目表 values('"+sanum+"','"+saage+"','"+saname+"','"+saage+"','"+spbegnum+"','"+sfbegnum+"')";
            Connect sql=new Connect();
            sql.ExecuteUpdate(insert);
            Manager.contPanel4.remove(Manager.pane8);
            Manager.contPanel4.add(Manager.pane7);
            Manager.contPanel4.revalidate();
            Manager.contPanel4.repaint();
        }
    };
    private ActionListener b1=new ActionListener(){
        public void actionPerformed(ActionEvent e){
            Manager.contPanel4.remove(Manager.pane8);
            Manager.contPanel4.add(Manager.pane7);
            Manager.contPanel4.revalidate();
            Manager.contPanel4.repaint();
        }
    };
    public Comscheduleinf (){
        setLayout(new GridLayout(10,2));
        sav.addActionListener(b3);
        ext.addActionListener(b1);
        add(csnum);
        add(comsnum);
        add(spname);
        add(sportname);
        add(cstype);
        add(comstype);
        add(csptime);
        add(comsptime);
        add(csftime);
        add(comsftime);
        add(sav);
        add(ext);

    }
}
