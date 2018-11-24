package thread.SwingSingleThreadDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class SwingSingleThreadDemo extends JFrame {
    private JList jList ;                       //列表组件
    private Vector listData = new Vector();     //列表数据
    private ButtonGroup buttonGroup = new ButtonGroup();  //按键组

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
           @Override
           public void run() {
               new SwingSingleThreadDemo().launch();
           }
       });
    }

    public void launch(){
        this.setBounds(500,500,350,350);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JScrollPane jScrollPane = new JScrollPane();            //滚动版面
        this.add(jScrollPane,BorderLayout.CENTER);              //添加滚动版面到窗体
        jList = new JList();                                    //创建滚动列表
        jScrollPane.setViewportView(jList);                     //滚动版面控制列表组件

        JPanel buttonJpanel = new JPanel();                     //创建按键面板
        this.add(buttonJpanel,BorderLayout.SOUTH);
        JButton jButton = new JButton("添加列表选项");       //创建按钮
        buttonJpanel.add(jButton);
        jButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton_actionPerformed(e);
            }
        });

        JPanel jRadioButtonJPanel = new JPanel();               //单选按键面板
        jRadioButtonJPanel.setLayout(new GridLayout(4,1));//使用网格布局
        this.add(jRadioButtonJPanel,BorderLayout.EAST);         //
        JRadioButton jRadioButton1 = new JRadioButton("单选1");
        jRadioButton1.setSelected(true);                        //设为被选中
        jRadioButtonJPanel.add(jRadioButton1);
        JRadioButton jRadioButton2 = new JRadioButton("单选2");
        jRadioButtonJPanel.add(jRadioButton2);
        JRadioButton jRadioButton3 = new JRadioButton("单选3");
        jRadioButtonJPanel.add(jRadioButton3);
        JRadioButton jRadioButton4 = new JRadioButton("单选4");
        jRadioButtonJPanel.add(jRadioButton4);
        //将单选按键添加到单选按键组
        buttonGroup.add(jRadioButton1);
        buttonGroup.add(jRadioButton2);
        buttonGroup.add(jRadioButton3);
        buttonGroup.add(jRadioButton4);
        this.setVisible(true);
    }

    private void jButton_actionPerformed(ActionEvent e) {
        try {
            for(int i=0 ; i<10 ; i++){
                listData.add("选项" + listData.size());
                jList.setListData(listData);
                Thread.sleep(1000);                     //阻塞UI线程1秒
            }
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}
