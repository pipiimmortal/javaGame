package Game;

import java.awt.*;

public class Sculpture2 extends Sculpture{
    public Sculpture2(GameFrame gameFrame) {
        super(gameFrame);
        setImg("image/sculpture/面向左前.png");
        setX(1300);
        setY(430);
    }
    @Override
    public void paintSelf(Graphics g) {
        //添加生命值
        this.addHp(g,0,25,100,20,Color.red);
        attack(gameFrame.player);
        g.drawImage(getImg(),getX(),getY(),null);
        g.fillOval(getX(),getY(),10,10);
        //g.drawRect(getX(),getY(),100,180);
        //圆
        //g.drawOval(getX()-300,getY(),500,300);

    }
}
