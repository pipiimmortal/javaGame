package Game;

import java.awt.*;

public class Sculpture5 extends Sculpture{
    public Rectangle s5 = getRec();
    public Sculpture5(GameFrame gameFrame) {
        super(gameFrame);
        setImg("image/sculpture/剑雕塑.png");
        setX(805);
        setY(615);
    }
    @Override
    public void paintSelf(Graphics g) {
        //添加生命值
        //this.addHp(g,0,30,100,20,Color.GREEN);
        attack(gameFrame.player);
        g.drawImage(getImg(),getX(),getY(),null);
        //g.fillOval(700 ,420,10,10);
        g.drawRect(760,570,200,200);
        //g.drawOval(700,300,200,200);
        //s5 = new Rectangle(getX(),getY()+20 ,100,80);

    }
}
