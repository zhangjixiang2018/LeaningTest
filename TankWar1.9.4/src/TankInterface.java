import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankInterface extends JFrame {

    public static final int GAME_WIDTH = 1200 ;
    public static final int GAME_HEIGHT = 800 ;
    public Point p = new Point(50,50);
    Image offScreenImage = null ;
    public Tank myTank = new Tank(500,300,this);
    public ArrayList<Missile> missiles = new ArrayList<Missile>();                 //子弹容器
    public List<Explode> explodes = new ArrayList<Explode>();                //爆炸容器
    public List<Tank> tanks = new ArrayList<Tank>();          //敌军坦克
    public NetClient nc = new NetClient(this);
    TankInterface(){

    }

    public void launch(){
        this.setSize(GAME_WIDTH,GAME_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(-1);
            }
        });
        this.setLayout(null);
        this.setTitle("TankWar");

        MyJPanel panel = new MyJPanel();
        panel.setSize(GAME_WIDTH,GAME_HEIGHT);
        panel.setBackground(Color.DARK_GRAY);
        this.add(panel);
        addKeyListener(new MyKeyListener());
        this.setVisible(true);
        //for(int i=0 ; i<10 ; i++){
         //   tanks.add(new EnemyTank(100+i*80,200,this));
        //}
        new RepaintThread().start();
        nc.connect("127.0.0.1",TankServer.TCP_PORT);
    }

    class MyJPanel extends JPanel{
        public void paint(Graphics g) {
            if(offScreenImage == null){
                offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
            }

            Graphics gOffImage = offScreenImage.getGraphics();         //获取图片的画笔
            //背景重擦
            Color c1 = gOffImage.getColor();
            gOffImage.setColor(Color.DARK_GRAY);
            gOffImage.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
            gOffImage.setColor(c1);

            super.paint(gOffImage);

            //在图片上画各种组件
            gOffImage.setColor(Color.RED);
            gOffImage.drawString("missile nunbers : " + missiles.size(),10,20);
            gOffImage.drawString("explode nunbers : " + explodes.size(),10,40);
            myTank.draw(gOffImage);                                     //画坦克

            for(int i = 0; i< tanks.size() ; i++){
                Tank e = tanks.get(i);
                e.draw(gOffImage);
            }
            for(int i=0 ; i<missiles.size() ; i++){                     //画子弹
                Missile m = missiles.get(i);
                m.hitTanks((ArrayList<Tank>) tanks);
                m.hitTank(myTank);
                m.draw(gOffImage);
            }
            for(int i=0 ; i<explodes.size() ; i++){                     //画爆炸
               Explode e = explodes.get(i);
               e.draw(gOffImage);
            }


            gOffImage.setColor(c1);                                     //恢复画笔原来的颜色

            //在界面上画出缓冲图片
            g.drawImage(offScreenImage,0,0,null);
        }
    }

    class MyKeyListener extends KeyAdapter{
        public void keyPressed(KeyEvent e) {
            myTank.keyPressd(e);
        }

        public void keyReleased(KeyEvent e) {
            myTank.keyReleased(e);
        }
    }

    class RepaintThread extends Thread{
        public void run() {
            while (true) {
                repaint();
                try {
                    sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
