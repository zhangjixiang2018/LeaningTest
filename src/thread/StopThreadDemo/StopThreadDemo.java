package thread.StopThreadDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopThreadDemo extends JFrame implements Runnable {

    private JButton button ;
    private JPanel jPanel ;
    private JTextArea textArea ;
    private JScrollPane jScrollPane ;
    private boolean runing = true ;
    public static void main(String[] args) {
        StopThreadDemo stopThreadDemo = new StopThreadDemo();
        stopThreadDemo.launch();
        new Thread(stopThreadDemo).start();
    }

    public void launch(){
        this.setBounds(500,500,300,200);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jPanel = new JPanel();
        button = new JButton("停止");                           //创建停止按钮
        button.addActionListener(new ActionListener() {             //按钮事件处理
            @Override
            public void actionPerformed(ActionEvent e) {
                setRuning();
            }
        });
        this.add(jPanel,BorderLayout.SOUTH);
        jPanel.add(button);                                  //

        jScrollPane = new JScrollPane();                     //创建滚动面板
        textArea = new JTextArea();
        textArea.setLineWrap(true);                         //文本域自动换行
        textArea.setBackground(Color.cyan);
        this.add(jScrollPane,BorderLayout.CENTER);          //添加滚动面板到窗体
        jScrollPane.setViewportView(textArea);              //滚动面板控制文本域组件
        this.setVisible(true);
    }

    public void run() {
        int i = 0;
        while(true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(runing == false){            //runing为false时跳出循环
                break;
            }
            textArea.append(++i + " ");     //追加数字到文本域
        }
    }

    public void setRuning() {
        this.runing = false;
    }
}
