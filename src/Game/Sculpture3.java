package Game;

import java.awt.*;

public class Sculpture3 extends Sculpture{
    public Sculpture3(GameFrame gameFrame) {
        super(gameFrame);
        setImg("image/sculpture/面向右后.png");
        setX(410);
        setY(800);
    }
    @Override
    public void paintSelf(Graphics g) {
        //添加生命值
        this.addHp(g,0,25,100,20,Color.red);
        attack(gameFrame.player);
        g.drawImage(getImg(),getX(),getY(),null);
        g.fillOval(getX(),getY(),10,10);
        //g.drawRect(getX(),getY(),100,180);
        //g.drawOval(getX()-100,getY()-50,350,300);

    }
}
