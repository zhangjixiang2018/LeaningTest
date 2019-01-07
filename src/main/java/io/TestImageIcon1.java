package io;

import javax.swing.*;
import java.awt.*;

public class TestImageIcon1 {

    public static void main(String[] args) {

        new frame();
        System.out.println("hello world");
    }
}

class frame extends JFrame
{
    private JLabel jl ;
    private ImageIcon image ;
    //private JLabel jl1 ;
    //private ImageIcon image1 ;

    public frame()
    {
        //将窗口设为最大
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //
        this.setLocationRelativeTo(null);
        //
        this.setLayout(new BorderLayout());
        //
        jl = new JLabel();
        //通过指定路径获取图标
        image = new ImageIcon("d:/2.jpg");
        //
        jl.setIcon(image);
        //

        this.add(jl,BorderLayout.WEST);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

}
