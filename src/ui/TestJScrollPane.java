package ui;

import javax.swing.*;

public class TestJScrollPane {

    public static void main(String[] args) {
        new MyJScrollPane();
    }
}

class MyJScrollPane extends JFrame
{
    private JTextArea text;

    //JScrollPane:一种带滚动条的容器
    private JScrollPane sp ;

    public MyJScrollPane()
    {
        this.setTitle("TestJScrollPane");
        this.setSize(500,300);
        //窗口居中
        this.setLocationRelativeTo(null);
        this.setLayout(null);

        text = new JTextArea();
        text.setBounds(0,0,400,200);

        sp = new JScrollPane(text);
        sp.setBounds(0,0,400,200);

        this.add(sp);

        //设置退出
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}