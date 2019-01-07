package ui.Event.MouseEventDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseEventDemo1 extends JFrame {
    private JTextField textField ;
    public static void main(String[] args) {
        new MouseEventDemo1().launch();
    }

    public void launch(){
        this.setBounds(500,500,500,500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        textField = new JTextField("MouseEventDemo");//创建文本框
        textField.setBackground(Color.cyan);
        this.add(textField,BorderLayout.SOUTH);

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                textField.setText("用户连续点击了鼠标" + e.getClickCount() + "次");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                textField.setText("鼠标在窗体按下了按键" + e.getButton());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                textField.setText("鼠标在窗体释放了按键" + e.getButton());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                textField.setText("鼠标进入了窗体区域");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                textField.setText("鼠标离开了窗体区域");
            }
        });

        this.setVisible(true);
    }
}
