package ui.Border;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BevelBorderDome extends JFrame {
    JProgressBar progressBar ;
    JProgressBar progressBar2 ;
    public static void main(String[] args) {
        new BevelBorderDome().launch();
    }

    public void launch(){
        this.setBounds(500,300,500,500);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setLayout(null);

        progressBar = new JProgressBar();
        progressBar2 = new JProgressBar();

        progressBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
        progressBar2.setBorder(new BevelBorder(BevelBorder.RAISED));

        progressBar.setBounds(20,20,300,50);
        progressBar2.setBounds(20,100,300,50);

        progressBar.setBackground(Color.RED);
        progressBar2.setBackground(Color.RED);

        this.add(progressBar);
        this.add(progressBar2);

        this.setVisible(true);
    }
}
