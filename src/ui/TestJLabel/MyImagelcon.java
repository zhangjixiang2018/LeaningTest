package ui.TestJLabel;

import javax.swing.*;
import java.net.URL;

public class MyImagelcon extends JFrame {
    public MyImagelcon(){
        //JLabel jl = new JLabel("带图标的标签组件",JLabel.CENTER);
        JLabel jl = new JLabel();
        URL url = MyImagelcon.class.getResource("2.jpg");
        Icon icon = new ImageIcon(url);
        jl.setIcon(icon);
        jl.setHorizontalAlignment(SwingConstants.CENTER);
        jl.setSize( 100  ,80);
        jl.setOpaque(true);
        this.add(jl);
        this.setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(500,500 );
        //this.pack();
    }

    public static void main(String[] args) {
       new MyImagelcon();
    }
}
