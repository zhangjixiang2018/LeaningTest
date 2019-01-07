package io;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TestImageIconBytes {

    public static void main(String[] args) throws Exception {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FileInputStream fis = new FileInputStream("d:/2.jpg");
        BufferedImage bimg = ImageIO.read(fis);

        //得到图片字节数组baos
        ImageIO.write(bimg,"jpg",baos);
        //将图片另存为3.jpg
        //ImageIO.write(bimg,"jpg",new File("d:/3.jpg"));

        new MyFrame(baos.toByteArray());
    }

}

class MyFrame extends JFrame
{
    private JLabel lab = null;
    private ImageIcon img = null;
    public MyFrame(byte[] imgBytes)
    {
        this.setSize(1000,800);
        //
        this.setLocationRelativeTo(null);
        //
        lab = new JLabel();
        //
        img = new ImageIcon(imgBytes);
        //
        lab.setIcon(img);
        //
        this.add(lab);

        //设置关闭方式
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
