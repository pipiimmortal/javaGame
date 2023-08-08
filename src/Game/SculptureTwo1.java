package Game;

import java.awt.*;

public class SculptureTwo1 extends Sculpture {
    public SculptureTwo1(GameFrame gameFrame) {

        super(gameFrame);
        setImg("image/第二关素材/左.png");
        setX(150);
        setY(280);
    }

    @Override
    public void paintSelf(Graphics g) {
        //添加生命值
        //this.addHp(g,0,30,100,20,Color.GREEN);
        g.drawImage(getImg(), getX(), getY(), null);
    }
}