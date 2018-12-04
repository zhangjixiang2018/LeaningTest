package ui.Event;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class FrameEventDemo extends JFrame {
    public static void main(String[] args) {
        new FrameEventDemo().launch();
    }

    public void launch(){
        this.setBounds(500,500,500,500);
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("打开状态");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("正在关闭");
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("已关闭");
            }

            @Override
            public void windowIconified(WindowEvent e) {
                System.out.println("最小化");
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                System.out.println("脱离最小化");
            }

            @Override
            public void windowActivated(WindowEvent e) {
                System.out.println("活动状态");
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                System.out.println("非活动状态");
            }
        });
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
