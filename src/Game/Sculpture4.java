package Game;

import java.awt.*;

public class Sculpture4 extends Sculpture{
    public Sculpture4(GameFrame gameFrame) {
        super(gameFrame);
        setImg("image/sculpture/面向左后.png");
        setX(1300);
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
        //g.drawOval(getX()-300,getY()-50,500,300);

    }
}
