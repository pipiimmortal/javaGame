package Game;

public class Enemy3 extends Enemy{
    public Enemy3(GameFrame gameFrame) {
        super(gameFrame);
        setImg("image/正面.png");
        setX(300);
        setY(300);
        setSpeed(10);
        setHp(2000);
    }

    @Override
    public void move(){
        int playX = gameFrame.player.getX();
        int playY = gameFrame.player.getX();
        if(getX() < playX && getY() > playY){
            setX(getX() + getSpeed());
            setY(getY() - getSpeed());
        }else if ( getX() < playX && getY() < playY){
            setX(getX() + getSpeed());
            setY(getY() + getSpeed());
        } else if (getX() > playX && getY() > playY) {
            setX(getX() - getSpeed());
            setY(getY() - getSpeed());
        } else if (getX() >playX && getY() < playY) {
            setX(getX() - getSpeed());
            setY(getY() + getSpeed());
        }else if (getX() > playX && getY() == playY) {
            setX(getX() - getSpeed());
        }else if (getX() < playX && getY() == playY) {
            setX(getX() + getSpeed());
        } else if (getX() == playX && getY() < playY) {
            setY(getY() + getSpeed());
        }else if (getX() == playX && getY() > playY) {
            setY(getY() - getSpeed());
        }
    }
}
