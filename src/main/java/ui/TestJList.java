package ui;

import javax.swing.*;

public class TestJList {
    public static void main(String[] args) {

        String[] s = new String[]{
          "中国","美国","日本"
        };
        new MyJFrame(s);
    }
}

class MyJFrame extends JFrame
{
    private JList<String> list;

    public MyJFrame(String[] s )
    {
        this.setBounds(100,100,500,300);
        this.setLayout(null);


        list = new JList<String>(s);
        list.setBounds(10,10,300,100);
        this.add(list);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}