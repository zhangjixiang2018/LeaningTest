package thread;

import javafx.scene.control.ProgressBar;

import javax.swing.*;
import java.awt.*;

public class PriorityDemo extends JFrame {

    private JProgressBar pA ;
    private JProgressBar pB ;
    private JProgressBar pC ;
    private JProgressBar pD ;
    Thread threadA ;
    Thread threadB ;
    Thread threadC ;
    Thread threadD ;

    public static void main(String[] args) {
        new PriorityDemo().launch();
    }

    public void launch(){
        this.setBounds(500,500,300,200);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(4,1));

        //实例四个进度条
        pA = new JProgressBar();
        pA.setStringPainted(true);
        pB = new JProgressBar();
        pB.setStringPainted(true);
        pC = new JProgressBar();
        pC.setStringPainted(true);
        pD = new JProgressBar();
        pD.setStringPainted(true);

        //将进度条添加到窗体
        this.add(pA);
        this.add(pB);
        this.add(pC);
        this.add(pD);

        //实例4个线程，每个线程控制一个进度条
        threadA = new Thread(new MyThread(pA));
        threadB = new Thread(new MyThread(pB));
        threadC = new Thread(new MyThread(pC));
        threadD = new Thread(new MyThread(pD));

        //对线程初始化，设置线程的名字、其优先级并启动线程
        initThread("ThreaA",10,threadA);
        initThread("ThreaB",1,threadB);
        initThread("ThreaC",1,threadC);
        initThread("ThreaD",10,threadD);

        this.setVisible(true);
    }

    //对线程初始化，设置线程的名字、其优先级并启动线程
    public void initThread(String name , int priority ,Thread t){
        t.setName(name);
        t.setPriority(priority);
        t.start();
    }

    class MyThread implements Runnable{
        int progress = 0 ;
        private JProgressBar bar ;


        public MyThread(JProgressBar bar) {
            this.bar = bar;
        }

        public void run() {
           while (progress < 101){

               bar.setValue(progress);
               bar.setString(Thread.currentThread().getName()+ " " + progress + "%");
               progress += 5 ;
               try {
                   Thread.sleep(2000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

           }
        }

    }
}


