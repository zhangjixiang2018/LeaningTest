import java.awt.*;

public class Explode {

    public int x,y ;
    private int[] diameter = {10,10,15,15,23,23,33,33,45,45,55,55,55,38,20,10};          //圆的直径
    private boolean live=true ;                             //爆炸的生死，true为生，生就画出来
    public int step = 0 ;
    TankInterface tankInterface ;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Explode(int x, int y, TankInterface tankInterface) {
        this.x = x;
        this.y = y;
        this.tankInterface = tankInterface;
    }

    public void draw(Graphics g){

        if(!live) return;

        if(step == diameter.length){
            live = false ;
            tankInterface.explodes.remove(this);
            return;
        }

        Color c = g.getColor();
        g.setColor(Color.ORANGE);
        g.fillOval(x,y,diameter[step],diameter[step]);
        step++;
        g.setColor(c);
    }
}
