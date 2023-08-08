package Game;

import java.awt.*;

public class Bullet extends GameObject{
    //发射子弹的游戏元素
    GameObject attacker;
    //目标
    GameObject target;
    public Bullet(GameFrame gameFrame,GameObject attacker,GameObject target) {

        super(gameFrame,attacker.getX(),attacker.getY());
        this.attacker = attacker;
        this.target = target;
        setImg("image/正面.png");
    }



    public void move(){
        int dis = (int) getDis(getX(),getY(),target.getX(),target.getY());
        int xSpeed = (int)(getSpeed() * (target.getX() - getX()) / dis);
        int ySpeed = (int)(getSpeed() * (target.getY() - getY()) / dis);
        setX(getX() + xSpeed);
        setY(getY() + ySpeed);

    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(getX(),getY(), 10,10);
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(getImg(),getX(),getY(),null) ;
        g.setColor(Color.BLUE);
        g.fillOval(getX(),getY(),10,10);
        g.drawRect(getX(),getY(), 10,10);
        move();

    }
}
