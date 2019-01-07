package screenbroadcast;

import javax.swing.*;

public class  StudentUI extends JFrame {

    private JLabel jlb = null;
    private ImageIcon img = null;

    public StudentUI()
    {
        this.setSize( 900 ,700);
        //居中
        this.setLocationRelativeTo(null);

        jlb = new JLabel() ;
        img = new ImageIcon();

        jlb.setIcon(img);

        this.add(jlb);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    //更新图标
    public void updateIcon(byte[] bytes)
    {
        img = new ImageIcon(bytes);
        jlb.setIcon(img);
    }
}
