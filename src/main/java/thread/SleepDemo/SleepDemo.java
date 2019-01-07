package thread.SleepDemo;

import javax.swing.*;

public class SleepDemo extends JFrame implements Runnable {

    private JLabel jLabel;
    public static void main(String[] args) {
        SleepDemo sleepDemo = new SleepDemo();
        sleepDemo.launch();
        Thread thread = new Thread(sleepDemo);
        thread.start();
    }

    public void launch(){
        this.setBounds(500,500,800,500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jLabel = new JLabel();
        //加载图片
        ImageIcon icon = new ImageIcon(getClass().getResource("tankL.gif"));
        //为标签组件设置图片
        jLabel.setIcon(icon);
        //设置标签组件位置和大小
        jLabel.setBounds(0,0,icon.getIconWidth(),icon.getIconHeight());
        this.add(jLabel);
        this.setVisible(true);
    }

    public void run() {
        int width = this.getWidth();
        while (true){
            for(int i=0 ; i<width ; i+=20){
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                jLabel.setLocation(i,jLabel.getY());
            }
        }
    }
}