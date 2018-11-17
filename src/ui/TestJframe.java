package ui;

import org.junit.Test;

import javax.swing.*;

public class TestJframe extends JFrame {

    @Test
    public static void main(String[] args)
    {
        TestJframe frame = new TestJframe();
    }
    public TestJframe()
    {
        init();
    }

    private void init() {

        //
        this.setSize(800,600);
        //设置窗口位于居中的位置
        this.setLocationRelativeTo(null);
        //设置x（退出）的方式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setVisible(true);
    }
}
