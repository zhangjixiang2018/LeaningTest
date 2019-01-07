package ui.GetGraphics2D;

import javax.swing.*;
import java.awt.*;

public class GetGraphics2DDemo extends JFrame {

    public static void main(String[] args) {
        System.out.println("hello word");
        new GetGraphics2DDemo().launch();
    }

    public void launch(){
        this.setBounds(500,500,400,400);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        Graphics2D graphics2D = (Graphics2D)this.getGraphics();
        System.out.println(graphics2D.getColor());
        System.out.println(graphics2D.getBackground());
        System.out.println(graphics2D.getFont());
    }
}
