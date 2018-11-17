/**
 * 从键盘上输入firstName，Last Name到对应文本框
 */
package ui.TextFiled;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class test2{

    public static void main(String[] args) {
        new TFJFrame2();
    }
}

class TFJFrame2 extends JFrame
{
    public TFJFrame2()
    {
        this.setSize(300,200);
        //
        this.setLocationRelativeTo(null);
        //
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //
        this.setLayout(new FlowLayout(FlowLayout.LEFT,10,20));
        //
        this.add(new JLabel("First Name"));
        //
        this.add(new JTextField(8));
        //
        this.add(new JLabel("MI"));
        //
        this.add(new JTextField(1));
        //
        this.add(new JLabel("Last Name"));
        //
        this.add(new JTextField(8));
        //
        this.setVisible(true);
    }

}
