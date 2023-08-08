package Game;

import java.awt.*;

public class Background extends GameObject{
    String[] back = {
            "image/Free-RPG-Battleground-Asset-Pack3.jpg",
            "image/第二关.jpg"
    };

    public Background(GameFrame gameFrame){
        super(gameFrame);
    }

    @Override
    public Rectangle getRec() {
        return null;
    }
    public Image bg ;


    public void paintSelf(Graphics g){
        bg = Toolkit.getDefaultToolkit().getImage(back[0]);
        g.drawImage(bg,0,0,null);
    }
}

