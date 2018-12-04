package ui.Event.KeyEventDemo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class KeyEventDemo extends JFrame {
    JTextField result ;
    private int n1 = 10 ; //第一个数字
    private int n2 = 10 ; //第二个数字
    JLabel numLabel1 ;//第一个数字标签
    JLabel numLabel2 ;//第二个数字标签
    private JLabel message ; //显示运算结果信息的标签

    public static void main(String[] args) {
        new KeyEventDemo().launch();
    }

    public void launch(){
        this.setBounds(500,500,350,200);
        this.setDefaultCloseOperation(3);
        this.setLayout(null);

        numLabel1 = new JLabel("10");//第一个数字标签
        numLabel1.setText(n1+"");
        numLabel1.setBounds(70,80,30,30);
        numLabel1.setFont(new Font("Dialod",1,20));//设置字体大小
        this.add(numLabel1);

        JLabel Label1 = new JLabel("+");//加号标签
        Label1.setBounds(100,80,30,30);
        Label1.setFont(new Font("Dialod",1,20));
        this.add(Label1);

        numLabel2 = new JLabel("10");//第二个数字标签
        numLabel2.setText(n2+"");
        numLabel2.setBounds(120,80,30,30);
        numLabel2.setFont(new Font("Dialod",1,20));
        this.add(numLabel2);

        JLabel Label2 = new JLabel("=");//等于号标签
        Label2.setBounds(150,80,30,30);
        Label2.setFont(new Font("Dialod",1,20));
        this.add(Label2);

        result = new JTextField();//创建计算结果的输入文本框
        result.setBounds(170,80,60,30);
        result.setFont(new Font("Dialod",1,20));
        result.addKeyListener(new KeyAdapter() {//为文本框添加按键监听适配器
            @Override
            public void keyPressed(KeyEvent e) {//重写KeyPressed()方法
                String resultStr = result.getText();
                int resultNum = 0;
                if(e.getKeyCode() == KeyEvent.VK_ENTER){//如果按下的是Entry键
                    try {
                        resultNum = Integer.parseInt(resultStr);//转化数字字符串为整数
                    } catch (NumberFormatException e1) {//如果输入非整数
                        message.setText("结果只能是整数，请重新输入");
                        result.selectAll();//全选文本框
                        return;
                    }
                    if(resultNum == n1 + n2){
                        message.setText("计算正确，请计算下一个计算题");
                        randomNumber();//生成两个随机数
                        result.selectAll();//全选文本框
                    }else {
                        message.setText("计算错误，请重新计算");
                        result.selectAll();
                    }
                }

            }
        });
        this.add(result);

        message = new JLabel("请计算");//创建显示运算结果的标签
        message.setBounds(30,30,300,30);
        message.setFont(new Font("Dialod",1,20));
        message.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(message);


        this.setVisible(true);
    }

    private void randomNumber(){
        Random random = new Random(); //随机数产生器
        n1 = random.nextInt(100);
        n2 = random.nextInt(100);
        numLabel1.setText(n1 + "");
        numLabel2.setText(n2 + "");
    }

}
