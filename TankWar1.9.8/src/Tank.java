import java.awt.*;
import java.awt.event.KeyEvent;

public class Tank {
    public static final int TANK_WIDTH = 30 ;
    public static final int TANK_HEIGHT = 30 ;
    public static final int SPEED = 10 ;
    public byte bL = 0 ,                                    //按键标志，键按下后置1
                bU = 0 ,
                bR = 0 ,
                bD = 0 ;
    public TankInterface tankInterface;
    public int x ;
    public int y ;
    public int groupId = 1 ;                                 //给坦克分组，同一组的坦克不能相互打，id=0表示电脑
    public int id ;                                          //坦克ID，唯一标识
    public boolean live = true ;                             //表示坦克生死，1表示生，0表示死
    Direction dir = Direction.U ;                            //坦克方向
    Direction ptDir = Direction.U;                           //炮筒方向

    Tank(int x ,int y){
        this.x = x ;
        this.y = y ;
    }

    public Tank(int x, int y, TankInterface tankInterface) {
        this.x = x;
        this.y = y;
        this.tankInterface = tankInterface ;
    }

    public void draw(Graphics g){
        if(live == false) return;
        move();
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x,y,TANK_WIDTH,TANK_HEIGHT);
        g.drawString("id:"+id ,x-25,y-5);
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
        //move();
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

        if(dir != Direction.STOP){
            ptDir = dir ;
        }

        locateDirection();
    }
    /**
     * 按键处理
     */
    public void keyPressd(KeyEvent e){
        int key = e.getKeyCode();
        switch (key){
            case KeyEvent.VK_DOWN :
                bD = 1;
                break;
            case KeyEvent.VK_UP :
                bU = 1;
                break;
            case KeyEvent.VK_LEFT :
                bL = 1;
                break;
            case KeyEvent.VK_RIGHT :
                bR = 1;
                break;
        }

    }

    /**
     * 释放键处理
     */
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key){
            case KeyEvent.VK_DOWN :
                bD = 0;
                break;
            case KeyEvent.VK_UP :
                bU = 0;
                break;
            case KeyEvent.VK_LEFT :
                bL = 0;
                break;
            case KeyEvent.VK_RIGHT :
                bR = 0;
                break;
            case KeyEvent.VK_A :
                fire();
                break;
        }
    }

    /**
     * 设置坦克方向
     */
    public void locateDirection(){
        int n = bL*8 + bU*4 + bR*2 + bD ;
        Direction oldDir = dir ;
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

        //如果坦克的方向发生改变则向其他的客户端发送移动的消息
        if(Direction.STOP != dir){
            //TankMoveMsg msg = new TankMoveMsg(this) ;
            TankMoveMsg msg = new TankMoveMsg(this,this.id,this.dir);
            tankInterface.nc.send(msg);
        }

//        if(dir != Direction.STOP){
//            ptDir = dir ;
//        }
    }

    public void fire() {

        int x = this.x+TANK_WIDTH/2-Missile.MISSILE_WIDTH/2 ;
        int y = this.y+TANK_HEIGHT/2-Missile.MISSILE_HEIGHT/2;
        Missile m = new Missile(x,y,this.ptDir,this.groupId,this.tankInterface,this.id);
        tankInterface.missiles.add(m);
        MissileNewMsg msg = new MissileNewMsg(m);
        tankInterface.nc.send(msg);
    }

    public Rectangle getRectangle(){
        return new Rectangle(x,y,TANK_WIDTH,TANK_HEIGHT);
    }
}
