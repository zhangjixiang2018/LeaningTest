package ui.Graphics2DDrawDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Graphics2DDrawDemo extends JFrame {
    public static void main(String[] args) {
        new Graphics2DDrawDemo().launch();
    }

    public void launch(){
        this.setBounds(500,500,500,500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        Shape[] shapes = new Shape[4];
        shapes[0] = new Ellipse2D.Double(100,100,100,100);
        shapes[1] = new Rectangle2D.Double(100,100,80,80);
        shapes[2] = new Ellipse2D.Double(200,200,80,80);
        shapes[3] = new Rectangle2D.Double(200,200,100,100);

        for(Shape s : shapes){
           Rectangle2D rectangle2D = s.getBounds2D();
           if(rectangle2D.getHeight() < 90){
               g2d.fill(s);
           }else g2d.draw(s);
        }
    }
}
