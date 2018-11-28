package ui.DrawImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class DrawImage extends JFrame {
    Image image ;
    BufferedImage bi ;
    public static void main(String[] args) throws IOException {
        new DrawImage().launch();
    }

    public void launch() throws IOException {
        //this.setBounds(500,500,500,500);
        this.setLocation(500,500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //两种获取图片资源路径的方法,即获取字节码文件对象的两种方法
        //URL url = DrawImage.class.getResource("image/2.jpg");           //获取图片资源的路径
        URL url = this.getClass().getResource("image/2.jpg");       //获取图片资源的路径

        //两种获取图片的方法
        //1.通过ToolKit工具获取
        image = Toolkit.getDefaultToolkit().getImage(url);                //获取图片资源
        //2.通过IO获取
        bi = ImageIO.read(url);                               //将图片缓存到内存中
        int width = bi.getWidth();
        int height = bi.getHeight(this);
        this.setSize(width,height);                                           //设置窗体的大小

        this.add(new MyPanel());
        this.setVisible(true);
    }

    class MyPanel extends JPanel {
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D graphics2D = (Graphics2D)g;
            //上述两种获取图片资源的方法获取的图片，都可以用drawImage()方法画出。
            //graphics2D.drawImage(image,0,0,this);
            graphics2D.drawImage(bi,0,0,this);
        }
    }
}
