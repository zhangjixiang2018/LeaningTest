package ui.ImageZoomDemo;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.net.URL;

public class ImageZoomDemo extends JFrame {
    private JSlider slider ;
    Image image;
    MyJPanel imagePanel ;
    public static void main(String[] args) {
        new ImageZoomDemo().launch();
    }

    public void launch(){
        this.setBounds(500, 500, 800, 800);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JPanel sliderPanel = new JPanel(new GridLayout(1,1));//创建容纳滑块的面板
        this.add(sliderPanel,BorderLayout.SOUTH);
        slider = new JSlider(100,800,200);//创建最小值、最大值、初始值分别为100,800,200的滑块
        //设置主刻度间隔
        slider.setMajorTickSpacing(50);
        //设置次刻度间隔
        slider.setMinorTickSpacing(10);
        //绘制刻度和标签
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        //添加改变刻度的监听器,当刻度发生改变时，调用imagePanel.repaint()重新画图片
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                imagePanel.repaint();
            }
        });
        sliderPanel.add(slider);

        imagePanel = new MyJPanel();//创建图片面板
        URL imgURL = this.getClass().getResource("2.jpg");//获取图片资源的路径
        image = Toolkit.getDefaultToolkit().getImage(imgURL);//获取图片资源
        this.add(imagePanel,BorderLayout.CENTER);
        this.setVisible(true);
    }

    class MyJPanel extends JPanel{

        public void paint(Graphics g) {
            super.paint(g);
            Graphics2D graphics2D = (Graphics2D)g;
            //g.drawImage(image,0,0,this);
            //获取滑块的值作为图片的大小
            graphics2D.drawImage(image,20,0,
                    slider.getValue()-15,slider.getValue()+40,this);
        }
    }
}
