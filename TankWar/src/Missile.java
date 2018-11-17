import java.awt.*;
import java.util.ArrayList;

public class Missile {
    public static final int SPEED = 12 ;
    public static final int MISSILE_WIDTH = 10;
    public static final int MISSILE_HEIGHT = 10;
    public int x ;
    public int y ;
    public boolean live = true ;                      //true子弹活，false子弹死
    public int groupId ;
    public TankInterface tankInterface ;
    Direction dir ;

    Missile(int x, int y, Direction dir,int groupId,TankInterface tankInterface) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.groupId = groupId ;
        this.tankInterface = tankInterface ;
    }

    public void draw(Graphics g){
        if (live == true) {
            Color c = g.getColor();
            if(groupId == 1)  g.setColor(Color.RED);
            else g.setColor(Color.BLUE);
            g.fillOval(x,y,MISSILE_WIDTH,MISSILE_HEIGHT);
            g.setColor(c);
            move();
        }
        else {
            tankInterface.missiles.remove(this);
        }
    }

    /**
     * 子弹移动
     */
    public void move(){
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

        //如果子弹飞出了游戏的边界则将子弹置为0，即死亡状态
        if(x<0 || y<0 || x>TankInterface.GAME_WIDTH || y>TankInterface.GAME_HEIGHT){
            live = false ;
        }
    }


    public void hitTank(Tank tank){
        if(this.getRectangle().intersects(tank.getRectangle()) && this.groupId != tank.groupId){
            this.live = false ;
            tank.live =false ;
            tankInterface.explodes.add(new Explode(x,y,tankInterface));
        }
    }

    public  void hitTanks(ArrayList<EnemyTank> enemyTanks){
        for(int i=0 ; i<enemyTanks.size() ; i++){
            hitTank(enemyTanks.get(i));
        }
    }

    public void hitTank(EnemyTank tank){
        if(this.getRectangle().intersects(tank.getRectangle()) && this.groupId != tank.groupId){
            this.live = false ;
            tank.live =false ;
            tankInterface.enemyTanks.remove(tank);                      //坦克被打到移除容器中的该坦克
            tankInterface.explodes.add(new Explode(x,y,tankInterface)); //添加爆炸
        }
    }

    public Rectangle getRectangle(){
        return new Rectangle(x,y,MISSILE_WIDTH,MISSILE_HEIGHT);
    }
}
