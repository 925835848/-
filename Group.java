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

public class Group extends JPanel{
    private JLabel shc=new JLabel("查询项目");
    private JButton sch=new JButton("查询"),
            adds=new JButton("录入");
    private JTextField cdtn=new JTextField(10);
    private String[] heads={
            "团体编号","名称","运动员总分","运动队排名"
    };
    private String[] descripton={"","团体编号","团体名称"};
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
                    query="SELECT game_total_score,team_number,team_name,team_rank FROM 运动队总分表" ;
                if(condition==1)
                    query="SELECT game_total_score,team_number,team_name,team_rank FROM 运动队总分表 WHERE team_number='"+cdtn.getText()+"'" ;
                if(condition==2)
                    query="SELECT game_total_score,team_number,team_name,team_rank FROM 运动队总分表 WHERE team_name='"+cdtn.getText()+"'" ;

                Connect sel=new Connect();
                ResultSet relt=sel.ExecuteQuery(query);
                while(relt.next())
                {
                    model.addRow(new Object[]{relt.getString("team_number"),relt.getString("team_name"),relt.getString("game_total_score") ,relt.getString("team_rank")        });
                }

            } catch(Exception f) {
                f.printStackTrace();
            }

        }
    };
    private ActionListener b2=new ActionListener(){
        public void actionPerformed(ActionEvent e){
            Manager.contPanel2.remove(Manager.pane3);
            Manager.contPanel2.add(Manager.pane4);
            Manager.contPanel2.revalidate();
            Manager.contPanel2.repaint();
        }
    };
    public  Group(){
        setPreferredSize(new Dimension(800,300));
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
class Groupinf extends JPanel{
    private JLabel grpnum=new JLabel("团体编号"),
            grpname=new JLabel("团体名称"),
            athnum=new JLabel("运动总分"),
            dist=new JLabel("排名");
    private JTextField gnum=new JTextField(20),
            gname=new JTextField(20),
            anum=new JTextField(20),
            district=new JTextField(20);
    private JButton sav=new JButton("提交"),
            ext=new JButton("返回");
    private ActionListener b1=new ActionListener(){
        public void actionPerformed(ActionEvent e){
            Manager.contPanel2.remove(Manager.pane4);
            Manager.contPanel2.add(Manager.pane3);
            Manager.contPanel2.revalidate();
            Manager.contPanel2.repaint();
        }
    };
    private ActionListener b3=new ActionListener(){
        public void actionPerformed(ActionEvent e){
            String sanum=gnum.getText();
            String saname=gname.getText();
            String sbegnum=anum.getText();
            String saage=district.getText();
            String insert="INSERT INTO 运动队总分表 values('"+sbegnum+"','"+sanum+"','"+saname+"','"+saage+"')";
            Connect sql=new Connect();
            sql.ExecuteUpdate(insert);

            anum.setText(null);
            gnum.setText(null);
            gname.setText(null);
            district.setText(null);
            Manager.contPanel2.remove(Manager.pane4);
            Manager.contPanel2.add(Manager.pane3);
            Manager.contPanel2.revalidate();
            Manager.contPanel2.repaint();
        }
    };
    public Groupinf (){
        setLayout(new GridLayout(8,2));
        sav.addActionListener(b3);
        ext.addActionListener(b1);
        add(grpnum);
        add(gnum);
        add(grpname);
        add(gname);
        add(athnum);
        add(anum);
        add(dist);
        add(district);
        add(sav);
        add(ext);

    }

}