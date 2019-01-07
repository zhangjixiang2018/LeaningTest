package ui.Event.MouseEventDemo;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseEventDemo2 extends JFrame {
    private JTextField textField ;
    public static void main(String[] args) {
        new MouseEventDemo2().launch();
    }

    public void launch(){
        this.setBounds(500,500,500,500);
        this.setDefaultCloseOperation(3);
        this.setLayout(null);
        textField = new JTextField();//创建文本框
        textField.setBounds(200,200,200,20);
        this.add(textField);
        this.addMouseMotionListener(new MouseMotionListener() {//添加鼠标监听器
            @Override
            public void mouseDragged(MouseEvent e) {//处理鼠标拖动，当鼠标拖动时文本框也跟着移动
                textField.setLocation(e.getPoint());
            }

            @Override
            public void mouseMoved(MouseEvent e) {//处理鼠标移动，当鼠标移动时文本框显示鼠标的坐标
                textField.setText("Mouse Location is :"+ e.getPoint().toString());
            }
        });
        this.setVisible(true);
    }
}
