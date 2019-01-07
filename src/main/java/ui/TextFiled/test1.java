/**
 * 从键盘上输入文字到文本框，然后将文本框中的内容输出到控制台
 */
package ui.TextFiled;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class test1  {

    public static void main(String[] args) {
        new TFJFrame();
    }
}

class TFJFrame extends JFrame
{
    JTextField jtf = new JTextField();

    public TFJFrame()
    {
        this.setSize(300,200);
        //
        this.setLocationRelativeTo(null);
        //
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //
        this.setLayout(new FlowLayout(FlowLayout.LEFT,10,20));
        //
        this.add(jtf);
        //
        jtf.addActionListener(new JTFActionListener());
        //this.pack();
        //
        this.setVisible(true);
    }

    //内部类
    private class JTFActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String s = jtf.getText();
            System.out.println(s);
            //输出后将文本框清空
            jtf.setText("");
        }
    }
}
