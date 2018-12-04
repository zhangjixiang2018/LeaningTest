package ui.Event;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrameEventDemo2 extends JFrame {
    public static void main(String[] args) {
        new FrameEventDemo2().launch();
    }

    public void launch(){
        this.setBounds(500,500,500,500);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("你单击了窗体的关闭按钮");
                System.out.println("在这个事件处理方法中可以再干点别的");
                System.out.println("例如，关闭数据库连接等。");
                System.out.println("好了，不说了，Bye");
                System.exit(0);
            }
        });
        this.setVisible(true);
    }
}
