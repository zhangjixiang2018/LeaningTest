package thread.JoinDemo;

import javax.swing.*;
import java.awt.*;

public class JoinDemo extends JFrame {
    private Thread threadA;                                    //线程A
    private Thread threadB;                                    //线程B
    private JProgressBar progressBarA = new JProgressBar();    //进度条A
    private JProgressBar progressBarB = new JProgressBar();    //进度条B

    public static void main(String[] args) {
        new JoinDemo().launch();
    }

    public void launch(){
        this.setBounds(500,500,500,500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(progressBarA,BorderLayout.NORTH);
        this.add(progressBarB,BorderLayout.SOUTH);

        progressBarA.setStringPainted(true);
        progressBarB.setStringPainted(true);

        progressBarA.setString("线程A");
        progressBarB.setString("线程B");

        //创建线程A
        threadA = new Thread(new Runnable() {
            public void run() {
                for(int i=0 ; i<=100 ; i++){
                    try {
                        progressBarA.setValue(i);//控制进度条的移动
                        progressBarA.setString("线程A  " + i + "%");
                        if(i>=50){
                            threadB.join();
                        }
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //创建线程B
        threadB = new Thread(new Runnable() {
            public void run() {
                for(int i=0 ; i<=100 ; i++){
                    try {
                        progressBarB.setValue(i);
                        progressBarB.setString("线程B  " + i + "%");
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        this.setVisible(true);
        threadA.start();
        threadB.start();
    }
}
