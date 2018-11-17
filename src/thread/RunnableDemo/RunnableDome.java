package thread.RunnableDemo;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class RunnableDome extends JFrame implements Runnable {

    private String string = "现在大家已经对计算机非常的熟悉，如今的计算机操作" +
            "系统可以同时执行多个任务，在听歌的同时可以打字、下载文件，在聊天窗口打" +
            "字的时候，对方还可以通过视频看到你、听到你的声音。这一切都是使用多任务实现" +
            "的。Java语言使用多线程实现一个程序中的多个任务同时执行。程序员可以在程序" +
            "中执行多个线程，每一个线程实现一个功能，并与其它线程并发执行，这种机制称为多线程。" ;

    private JLabel jLabel ;
    private JTextArea jTextArea ;
    private JScrollPane scrollPane ;
    private JPanel jPanel;

    public static void main(String[] args) {
        RunnableDome runnableDome = new RunnableDome();
        runnableDome.launch();
        Thread thread = new Thread(runnableDome);
        thread.start();
    }

    public void launch(){
        this.setBounds(500,500,150,200);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        jLabel = new JLabel("用实现Runnable接口的方法创建线程");
        this.add(jLabel,BorderLayout.NORTH);

        jTextArea = new JTextArea();
        jTextArea.setBorder(new BevelBorder(BevelBorder.LOWERED));  //设置边框
        jTextArea.setLineWrap(true);                                //设置自动换行
        scrollPane = new JScrollPane(jTextArea);
        this.add(scrollPane,BorderLayout.CENTER);
        this.setVisible(true);
    }
    public void run() {
        String[] strings = string.split("");
        for(String ch : strings){
            jTextArea.append(ch);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
