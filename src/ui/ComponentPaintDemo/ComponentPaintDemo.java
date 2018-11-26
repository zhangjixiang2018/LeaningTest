package ui.ComponentPaintDemo;

import javax.swing.*;
import java.awt.*;

public class ComponentPaintDemo extends JFrame {
    public static void main(String[] args) {
        new ComponentPaintDemo().launch();
    }

    public void launch(){
        this.setBounds(500,500,500,500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void paint(Graphics g){
        super.paint(g);
        String str = "石头与小草，会不会吵架?";
        Graphics2D g2d = (Graphics2D)g;
        Font font = new Font("隶书",Font.BOLD | Font.ITALIC,36);  //设置字体、样式、大小
        g2d.setFont(font);

        for(int i=0 ; i<str.length() ; i++ ){
            g2d.setColor(Color.GREEN);
            g2d.drawString(str.charAt(i)+"",20+i*font.getSize(),90+i*font.getSize());
        }

        for(int i=0 ; i<str.length() ; i++ ){
            g2d.setColor(Color.MAGENTA);
            g2d.drawString(str.charAt(i)+"",12+i*font.getSize(),83+i*font.getSize());
        }
    }
}
