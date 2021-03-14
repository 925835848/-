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

public class Sports extends JPanel{
    private JLabel shc=new JLabel("查询项目");
    private JButton sch=new JButton("查询"),
            adds=new JButton("录入");
    private JTextField cdtn=new JTextField(10);
    private String[] heads={
            "赛事编号","赛事名称","本赛事历史记录","破纪录者编号"
    };
    private String[] descripton={"","赛事编号","赛事名称"};
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
                    query="SELECT race_number,game_record,race_name,record_breaker_number FROM 项目记录表" ;
                if(condition==1)
                    query="SELECT race_number,game_record,race_name,record_breaker_number FROM 项目记录表 WHERE race_number='"+cdtn.getText()+"'" ;
                if(condition==2)
                    query="SELECT race_number,game_record,race_name,record_breaker_number FROM 项目记录表 WHERE race_name='"+cdtn.getText()+"'" ;
                Connect sel=new Connect();
                ResultSet relt=sel.ExecuteQuery(query);
                while(relt.next())
                {
                    model.addRow(new Object[]{relt.getString("race_number"),relt.getString("race_name"),relt.getString("game_record") ,relt.getString("record_breaker_number")       });
                }

            } catch(Exception f) {
                f.printStackTrace();
            }

        }
    };
    private ActionListener b2=new ActionListener(){
        public void actionPerformed(ActionEvent e){
            Manager.contPanel3.remove(Manager.pane5);
            Manager.contPanel3.add(Manager.pane6);
            Manager.contPanel3.revalidate();
            Manager.contPanel3.repaint();
        }
    };
    public  Sports(){
        setPreferredSize(new Dimension(500,400));
        setLayout(new FlowLayout());
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
class Sportinf extends JPanel{
    private JLabel spnum=new JLabel("赛事编号"),
            spname=new JLabel("赛事名称"),
            wldrcd=new JLabel("破纪录者编号"),
            wldcd= new JLabel("破纪录者成绩"),
            lhrcd=new JLabel("本赛事历史记录");

    private JTextField snum=new JTextField(20),
            sname=new JTextField(20),
            wrecord=new JTextField(20),
            lhrecord=new JTextField(20),
            wldcord=new JTextField(20);

    private JButton sav=new JButton("提交"),
            ext=new JButton("返回");
    private ActionListener b1=new ActionListener(){
        public void actionPerformed(ActionEvent e){
            Manager.contPanel3.remove(Manager.pane6);
            Manager.contPanel3.add(Manager.pane5);
            Manager.contPanel3.revalidate();
            Manager.contPanel3.repaint();
        }
    };
    private ActionListener b3=new ActionListener(){
        public void actionPerformed(ActionEvent e){
            String sanum=snum.getText();
            String saname=sname.getText();
            String sawld=wldcord.getText();
            String sbegnum=wrecord.getText();
            String saage=lhrecord.getText();
            String insert="INSERT INTO 项目记录表 values('"+sanum+"','"+saage+"','"+sbegnum+"','"+sawld+"'"+saname+")";
            Connect sql=new Connect();
            sql.ExecuteUpdate(insert);
        }
    };
    public Sportinf (){
        setLayout(new GridLayout(6,2));
        sav.addActionListener(b3);
        sav.addActionListener(b1);
        ext.addActionListener(b1);
        add(spnum);
        add(snum);
        add(spname);
        add(sname);
        add(wldrcd);
        add(wrecord);
        add(wldcd);
        add(wldcord);
        add(lhrcd);
        add(lhrecord);
        add(sav);
        add(ext);
    }
}