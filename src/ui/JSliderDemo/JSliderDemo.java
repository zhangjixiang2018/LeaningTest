package ui.JSliderDemo;

import javafx.scene.control.Slider;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JSliderDemo extends JFrame {
    private JSlider slider ;                       //创建滑块
    public static void main(String[] args) {
        new JSliderDemo().launch();
    }

    public void launch(){
        this.setBounds(500,500,500,500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //创建一个滑块，并设定其最小值、最大值、初始值分别为：0、100、10
        slider = new JSlider(0,100,10);
        //设置主刻度间隔
        slider.setMajorTickSpacing(5);
        //设置次刻度间隔
        slider.setMinorTickSpacing(1);
        //绘制刻度和标签
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        //添加改变刻度的监听器
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println("当前值： " + slider.getValue());
            }
        });
        this.add(slider);
        this.setVisible(true);
    }
}
