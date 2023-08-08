package Game;

import javafx.scene.shape.Circle;

import java.awt.*;

public class Sculpture extends GameObject{

    public Sculpture(GameFrame gameFrame) {
        super(gameFrame);
        setImg("image/sculpture/1.png");
        setX(410);
        setY(430);
        setHp(5000);
        setCurrentHp(5000);
    }
    public Sculpture(GameFrame gameFrame,int x,int y){
        super(gameFrame,x,y);
        setImg("image/sculpture/1.png");
        setHp(6000);
        setCurrentHp(getHp());
        setAttackTime(1000);
        setDis(300);
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(getX(),getY(),100,180);
    }

    @Override
    public void paintSelf(Graphics g) {
        //添加生命值
        this.addHp(g,0,25,100,20,Color.red);
        attack(gameFrame.player);
        g.drawImage(getImg(),getX(),getY(),null);
        g.fillOval(getX()-100,getY()-50,10,10);
        //g.drawRect(getX(),getY(),100,180);
        //g.drawOval(410,580,55,55);

    }
}
