package Game;

import java.awt.*;

public abstract class Enemy extends GameObject{
    public Enemy(GameFrame gameFrame) {
        super(gameFrame);
    }

    //添加移动方法
    public abstract void move();

    @Override
    public Rectangle getRec() {
        return new Rectangle(getX()-16,getY()-16,45,45);
    }

    @Override
    public void paintSelf(Graphics g) {
        if (this instanceof Enemy2) {
            //添加生命值
            this.addHp(g,17,28,45,10,Color.GREEN);
        }else {
            this.addHp(g,17,28,45,10,Color.red);
        }

        g.drawImage(getImg(),getX()-16,getY()-16,null);
        g.setColor(Color.red);
        g.fillOval(getX(),getY(),10,10);
        g.drawRect(getX()-16,getY()-16,45,45);
        move();
    }
}
