import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class EnemyTank {
    public static final int TANK_WIDTH = 30 ;
    public static final int TANK_HEIGHT = 30 ;
    public static final int SPEED = 10 ;
    public byte bL = 0 ,                        //按键标志，键按下后置1
                bU = 0 ,
                bR = 0 ,
                bD = 0 ;
    public int x ;
    public int y ;
    public int groupId = 0 ;                                 //给坦克分组，同一组的坦克不能相互打，id=0表示电脑
    public boolean live = true ;                             //表示坦克生死，1表示生，0表示死
    Direction dir = Direction.D ;                            //坦克方向
    Direction ptDir = Direction.D;                           //炮筒方向
    Direction[] ds = Direction.values();                     //将枚举类型转化为数组，便于访问
    private Random random = new Random();                   //随机数生成器
    private int step = random.nextInt(12) + 3 ;      //敌方坦克移动多少步之后改变方向
    ArrayList<Missile> missiles = new ArrayList<Missile>();
    TankInterface tankInterface ;

    EnemyTank(int x , int y){
        this.x = x ;
        this.y = y ;
    }

    public EnemyTank(int x, int y,  TankInterface tankInterface) {
        this.x = x;
        this.y = y;
        this.tankInterface = tankInterface;
    }

    public void draw(Graphics g){
        if(live == false) return;
        Color c = g.getColor();
        g.setColor(Color.BLUE);
        g.fillOval(x,y,TANK_WIDTH,TANK_HEIGHT);
        g.setColor(Color.BLACK);
        switch (ptDir){
            case L:
                g.drawLine(x+TANK_WIDTH/2,y+TANK_HEIGHT/2,x,y+TANK_HEIGHT/2);
                break;
            case LU:
                g.drawLine(x+TANK_WIDTH/2,y+TANK_HEIGHT/2,x,y);
                break;
            case U:
                g.drawLine(x+TANK_WIDTH/2,y+TANK_HEIGHT/2,x+TANK_WIDTH/2,y);
                break;
            case RU:
                g.drawLine(x+TANK_WIDTH/2,y+TANK_HEIGHT/2,x+TANK_WIDTH,y);
                break;
            case R:
                g.drawLine(x+TANK_WIDTH/2,y+TANK_HEIGHT/2,x+TANK_WIDTH,y+TANK_HEIGHT/2);
                break;
            case RD:
                g.drawLine(x+TANK_WIDTH/2,y+TANK_HEIGHT/2,x+TANK_WIDTH,y+TANK_HEIGHT);
                break;
            case D:
                g.drawLine(x+TANK_WIDTH/2,y+TANK_HEIGHT/2,x+TANK_WIDTH/2,y+TANK_HEIGHT);
                break;
            case LD:
                g.drawLine(x+TANK_WIDTH/2,y+TANK_HEIGHT/2,x,y+TANK_WIDTH);
                break;
        }

        g.setColor(c);
        move();
    }

    /**
     * 坦克移动
     */
    public void move(){
        //locateDirection();

        //记录坦克的原始位置
        int oldX = x ;
        int oldY = y ;

        switch (dir){
            case L:
                x -= SPEED;
                break;
            case LU:
                x -= SPEED;
                y -= SPEED;
                break;
            case U:
                y -= SPEED;
                break;
            case RU:
                x += SPEED;
                y -= SPEED;
                break;
            case R:
                x += SPEED;
                break;
            case RD:
                x += SPEED;
                y += SPEED;
                break;
            case D:
                y += SPEED;
                break;
            case LD:
                x -= SPEED;
                y += SPEED;
                break;
        }

        //不让坦克出游戏的边界
        if(x<0 || x>TankInterface.GAME_WIDTH-30)   x = oldX ;
        if(y<0 || y>TankInterface.GAME_HEIGHT-56)  y = oldY ;

        if(step == 0){
            int rm = random.nextInt(8);
            dir = ds[rm];
            step = random.nextInt(12) + 8 ;
        }
        step--;

        //控制敌方坦克发射子弹的频率，
        //产生0~40的随机数，只有当随机数大于35时敌方坦克才打出子弹
        //if(random.nextInt(40) > 38)
            //fire();
    }

   /* *//**
     * 设置坦克方向
     *//*
    public void locateDirection(){
        int n = bL*8 + bU*4 + bR*2 + bD ;
        switch (n){
            case 0 :
                dir = Direction.STOP ;
                break;
            case 1 :
                dir = Direction.D ;
                break;
            case 2 :
                dir = Direction.R ;
                break;
            case 3 :
                dir = Direction.RD ;
                break;
            case 4 :
                dir = Direction.U ;
                break;
            case 6 :
                dir = Direction.RU ;
                break;
            case 8 :
                dir = Direction.L ;
                break;
            case 9 :
                dir = Direction.LD ;
                break;
            case 12 :
                dir = Direction.LU ;
                break;
        }

        if(dir != Direction.STOP){
            ptDir = dir ;
        }
    }*/

   /* public void fire() {
        tankInterface.missiles.add(new Missile(x+TANK_WIDTH/2-Missile.MISSILE_WIDTH/2,
                y+TANK_HEIGHT/2-Missile.MISSILE_HEIGHT/2,ptDir,groupId,this.tankInterface));
    }*/

    public Rectangle getRectangle(){
        return new Rectangle(x,y,TANK_WIDTH,TANK_HEIGHT);
    }
}
