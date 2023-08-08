package Game;

public class Enemy2 extends Enemy{

    public Enemy2(GameFrame gameFrame) {
        super(gameFrame);
        setImg("image/5.png");
        setX(800);
        setY(800);
        setSpeed(10);
        setHp(1000);


    }
    @Override
    public void move(){


    }
}
