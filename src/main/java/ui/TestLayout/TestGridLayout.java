package ui.TestLayout;

import javax.swing.*;
import java.awt.*;

public class TestGridLayout extends JFrame {
    JPanel jp1;
    JPanel jp2;
    JPanel jp3;
    JPanel jp4;
    private JTextField jTextField1 ;
    private JTextField jTextField2 ;
    private JTextField jTextField3 ;
    private JTextField jTextField4 ;
    TestGridLayout(){

    }

    public void launch(){
        this.setBounds(500,500,500,300);
        this.setTitle("文本框演示程序");
        this.setLayout(new GridLayout(4,1,2,2));

        jp1 = new JPanel();
        jp1.setLayout(new FlowLayout(FlowLayout.LEFT));
        jTextField1 = new JTextField(10);
        jp1.add(new JLabel("姓名："));
        jp1.add(jTextField1);
        this.add(jp1);

        jp2 = new JPanel();
        jp2.setLayout(new FlowLayout(FlowLayout.LEFT));
        jTextField2 = new JTextField(2);
        jp2.add(new JLabel("性别："));
        jp2.add(jTextField2);
        this.add(jp2);

        jp3 = new JPanel();
        jp3.setLayout(new FlowLayout(FlowLayout.LEFT));
        jTextField3 = new JTextField(3);
        jp3.add(new JLabel("年龄："));
        jp3.add(jTextField3);
        this.add(jp3);

        jp4 = new JPanel();
        jp4.setLayout(new FlowLayout(FlowLayout.LEFT));
        jTextField4 = new JTextField(15);
        jp4.add(new JLabel("姓名："));
        jp4.add(jTextField4);
        this.add(jp4);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {

        new TestGridLayout().launch();
    }
}
