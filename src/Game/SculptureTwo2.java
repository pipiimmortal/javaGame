package Game;

import java.awt.*;

public class SculptureTwo2 extends Sculpture {
    public SculptureTwo2(GameFrame gameFrame) {
        super(gameFrame);
        setImg("image/第二关素材/右.png");
        setX(1400);
        setY(280);
    }

    @Override
    public void paintSelf(Graphics g) {
        //添加生命值
        //this.addHp(g,0,30,100,20,Color.GREEN);

        g.drawImage(getImg(), getX(), getY(), null);
    }
}