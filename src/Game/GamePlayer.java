package Game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

public class GamePlayer extends GameObject{
    //移动
    public boolean up,down,left,right,sitMove,vkn = false;
    public boolean W = false,A = false,S = false,D = false;
    //显示文本判定
    boolean show , xianzhi;
    int anInt = 0;
    //传送门判断
    boolean passing = false, blank;
    //闪现
    boolean flash,arrive;
    //移动图集
    static String[] images = new String[8];
    static String[] imagesDown = new String[8];
    static String[] imagesUp = new String[9];
    static String[] ImagesLeft = new String[6];
    static String[] imagesLD = new String[9];
    static String[] imagesRD = new String[8];
    static String[] imagesLU = new  String[9];
    static String[] imagesRU = new String[9];
    static String[] attackD = new String[9];
    static String[] sit = new String[9];
    static String[] pass = new String[10];
    static String[] imagesFlash1 = new String[12];
    static String[] imagesFlash2 = new String[12];
    //第几张图片
    int moveCount = 1;
    int moveCountL = 1;
    int moveCountU = 1;
    int passCount = 1;
    int flashCount = 1;
    //坐下
    int num = 1;
    int N;
    //随机位置
    int c ;
    int d ;
    //一秒
    int timeSum = 1;
    Rectangle p = getRec();
    static {
        for (int i = 1; i < 8; i++) {
            images[i] = "image/move/" + i + ".png";
            imagesDown[i] = "image/movedown/" + i + ".png";
            imagesRD[i] = "image/moveRD/" + i + ".png";
        }
    }
    static {
        for (int i = 1; i < 9; i++) {
            imagesUp[i] = "image/moveUp/" + i + ".png";
            imagesLD[i] = "image/moveLD/" + i + ".png";
            imagesLU[i] = "image/moveLU/" + i + ".png";
            imagesRU[i] = "image/moveRU/" + i + ".png";
            attackD[i] = "image/acctackD/" + i + ".png";
            sit[i] = "image/sit/" + i + ".png";

        }
    }
    static {
        for (int i = 1; i < 6; i++) {
            ImagesLeft[i] = "image/moveLeft/" + i + ".png";
        }
    }
    static {
        for (int i = 1; i < 10; i++) {
            pass[i] = "image/传送门/" + i + ".png";
        }
    }
    static {
        for (int i = 1; i < 10; i++) {
            imagesFlash1[i] = "image/闪现1/" + i + ".png";
            imagesFlash2[i] = "image/闪现2/" + i + ".png";
        }
    }


    public GamePlayer(GameFrame gameFrame) {
        super(gameFrame);

        setImg("image/八面站立/正面.png");
        setX(500);
        setY(500);
        setSpeed(10);
        setHp(5000);
        setCurrentHp(getCurrentHp());
    }

    public void KeyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_D){
            right = true;
        }
        if(key == KeyEvent.VK_A){
            left = true;
        }
        if(key == KeyEvent.VK_W){
            up = true;
        }
        if(key == KeyEvent.VK_S){
            down = true;
        }
        if (key == KeyEvent.VK_J) {
            setAttack(true);
        }
        if (key == KeyEvent.VK_O) {
            setSit(true);
            sitMove = true;
        }


    }

    public void KeyReleased(KeyEvent e){
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_D){
            right = false;
        }
        if(key == KeyEvent.VK_A){
            left = false;
        }
        if(key == KeyEvent.VK_W){
            up = false;
        }
        if(key == KeyEvent.VK_S){
            down = false;
        }
        if (key == KeyEvent.VK_J) {
            setAttack(false);
        }
        if (key == KeyEvent.VK_O) {
            setSit(true);
            sitMove = false;
        }
        if (key == KeyEvent.VK_P) {
            setSit(false);
            vkn = false;
        }
        if (key == KeyEvent.VK_N){
            N++;
            if (N >= 9){
                N = 1;
            }
            setSit(true);
            vkn = true;
        }
        if(key == KeyEvent.VK_F){
            if (xianzhi) {
                setPassing(true);
                blank = true;
                gameFrame.launchTime();
                gameFrame.setTimeRemaining(3);
            }
        }
        if (key == KeyEvent.VK_L) {
            flash = true;
            c =((int)(Math.random()*1700));
            d = ((int)(Math.random()*870));
            setTimeSum(1);



        }

    }

    public void sit(){

        if (isSit()) {
            setImg(sit[num]);
            num++;
            if ((num >= 9)){
                num = 1;
            }
        }
    }
    public void sitting(boolean b){
        if (b){
            setImg(sit[N]);
        }
        else
            sit();
    }

    //传送门
    public void setPass(){
        if(passing){
            setImg(pass[passCount]);
            passCount++;
            if ((passCount == 10)){
                passCount = 1;
            }
        }else if(blank)
            setImg(pass[1]);
    }

    //闪现
    public void setFlash1(){

        if(flash){
            setImg(imagesFlash1[flashCount]);
            flashCount++;
            if ((flashCount == 12)){
                flashCount = 1;
            }
        }
    }
    public void setFlash2(){
        if(arrive){
            setY(d);
            setX(c);
            setImg(imagesFlash2[flashCount]);
            flashCount++;
            if ((flashCount == 12)){
                anInt++;
                flashCount = 1;
            }
        }
        if ((anInt == 3)) {
            setImg("image/八面站立/正面.png");
            arrive = false;
            anInt =0;
        }

    }

    //计时器
    public void setTimeSum(int n){
        Timer timer = new Timer(1000 , new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent a){
                timeSum--;
                System.out.println(timeSum);
                if (timeSum == 0){
                    ((Timer) a.getSource()).stop();
                    timeSum = 1;
                    if(n==1){
                        AfterTimeSumZero();
                    } else if(n == 2){
                        AfterTimeSumZero2();
                    }
                }
            }
        });
        timer.start();
    }



    public void AfterTimeSumZero() {
        // 在 timeSum 减为 0 时执行其他操作
            setFlash(false);
            setArrive(true);
    }
    public void AfterTimeSumZero2() {
        setArrive(false);
    }


    //八方向重置
    public void ff(){
        W = false;
        A = false;
        S = false;
        D = false;
        flash = false;
        //setPassing(false);
        setArrive(false);
    }

    //障碍物实现
    public void barrier(int n){

        Rectangle sculpture = new Rectangle(430,580,80,80);
        Rectangle sculpture2 = new Rectangle(1300,580,80,70);
        Rectangle sculpture3 = new Rectangle(410,940,80,70);
        Rectangle sculpture4 = new Rectangle(1300,940,80,70);
        Rectangle sculpture5 = new Rectangle(805,690,100,65);


        boolean canMove = revIntersectsRec(p,sculpture);
        boolean canMove2 =  revIntersectsRec(p,sculpture2);
        boolean canMove3 =  revIntersectsRec(p,sculpture3);
        boolean canMove4 =  revIntersectsRec(p,sculpture4);
        boolean canMove5 =  revIntersectsRec(p,sculpture5);

        if (canMove5){
            show = true;
        }

        if(gameFrame.getLevel() == 1) {

            if (canMove || canMove2 || canMove3 || canMove4 || canMove5) {
                gameFrame.getGraphics().drawRect(420, 580, 80, 80);
                gameFrame.getGraphics().drawRect(1300, 580, 80, 70);
                gameFrame.getGraphics().drawRect(410, 950, 80, 70);
                gameFrame.getGraphics().drawRect(1300, 950, 80, 70);
                gameFrame.getGraphics().drawRect(800, 670, 100, 80);
                setY(getY() + getSpeed());
                setX(getX() + getSpeed());
                switch (n) {
                    case 1:
                        setY(getY() + getSpeed());
                        break;
                    case 2:
                        setY(getY() - getSpeed());
                        break;
                    case 3:
                        setX(getX() + getSpeed());
                        break;
                    case 4:
                        setX(getX() - getSpeed());
                        break;
                    case 5:
                        setY(getY() + getSpeed());
                        setX(getX() + getSpeed());
                        break;
                    case 6:
                        setY(getY() + getSpeed());
                        setX(getX() - getSpeed());
                        break;
                    case 7:
                        setY(getY() - getSpeed());
                        setX(getX() + getSpeed());
                        break;
                    case 8:
                        setY(getY() - getSpeed());
                        setX(getX() - getSpeed());
                        break;
                }


            } else {
               move8(n);
            }
        }else
            move8(n);
    }
    //八方向移动方法
    public void move8(int n){
        switch (n) {
            case 1:
                setY(getY() - getSpeed());
                break;
            case 2:
                setY(getY() + getSpeed());
                break;
            case 3:
                setX(getX() - getSpeed());
                break;
            case 4:
                setX(getX() + getSpeed());
                break;
            case 5:
                setY(getY() - getSpeed());
                setX(getX() - getSpeed());
                break;
            case 6:
                setY(getY() - getSpeed());
                setX(getX() + getSpeed());
                break;
            case 7:
                setY(getY() + getSpeed());
                setX(getX() - getSpeed());
                break;
            case 8:
                setY(getY() + getSpeed());
                setX(getX() + getSpeed());
                break;
        }
    }

    //边界
    public void border(){
        if(getX() >= 1720){
            setX(getX() - getSpeed());
        } else if (getX() <= -25) {
            setX(getX() + getSpeed());
        }
        if(getY() >=1050){
            setY(-50);
        } else if (getY() <= -80) {
            setY(1000);
        }
    }

    //移动方法
    public void move(){
        if (up && !down && !right && !left){
            ff();
            W = true;
            barrier(1);
            setImg(imagesUp[moveCountU]);
            moveCountU++;
            if ((moveCountU == 9)){
                moveCountU = 1;
            }
        }
        else if (!up && down && !right && !left){
            ff();
            S = true;
            barrier(2);
            if (!isAttack()){
                setImg(imagesDown[moveCount]);
                moveCount++;
                if (moveCount == 8){
                    moveCount = 1;
                }
            }else{
                setImg(attackD[moveCountU]);
                moveCountU++;
                if ((moveCountU == 9)){
                    moveCountU = 1;
                }
            }

        }
        else if (!up && !down && !right && left){
            ff();
            A = true;
            barrier(3);
            setImg(ImagesLeft[moveCountL]);
            moveCountL++;
            if (moveCountL == 6){
                moveCountL = 1;
            }
        }
        else if (!up && !down && right && !left){
            ff();
            D = true;
            barrier(4);
            setImg(images[moveCount]);
            moveCount++;
            if (moveCount == 8){
                moveCount = 1;
            }
        }
        else if (up && !down && !right && left) {
            ff();
            W = true;
            A = true;
            barrier(5);
            setImg(imagesLU[moveCountU]);
            moveCountU++;
            if ((moveCountU == 9)){
                moveCountU = 1;
            }
        }
        else if (up && !down && right && !left) {
            ff();
            W = true;
            D = true;
            barrier(6);
            setImg(imagesRU[moveCountU]);
            moveCountU++;
            if ((moveCountU == 9)){
                moveCountU = 1;
            }
        }
        else if (!up && down && !right && left) {
            ff();
            S = true;
            A = true;
            barrier(7);
            setImg(imagesLD[moveCountU]);
            moveCountU++;
            if ((moveCountU == 9)){
                moveCountU = 1;
            }
        }
        else if (!up && down && right && !left) {
            S = true;
            D = true;
            barrier(8);
            setImg(imagesRD[moveCount]);
            moveCount++;
            if (moveCount == 8){
                moveCount = 1;
            }
        }
        else {
            if(W && !A && !S && !D ){
                setImg("image/八面站立/W.png");
            } else if ( !W && !A && S && !D ) {
                setImg("image/八面站立/正面.png");
            }else if (!W && A && !S && !D ) {
                setImg("image/八面站立/A.png");
            }else if (!W && !A && !S && D ) {
                setImg("image/八面站立/D.png");
            }else if (A && W && !S && !D) {
                setImg("image/八面站立/WA.png");
            }else if (A &&S && !W && !D) {
                setImg("image/八面站立/AS.png");
            }else if (D && W && !A && !S ) {
                setImg("image/八面站立/WD.png");
            }else if (D && S && !A && !W) {
                setImg("image/八面站立/SD.png");
            }
        }

    }

    public void setFlash(boolean flash) {
        this.flash = flash;
    }
    public void setPassing(boolean passing) {
        this.passing = passing;
    }
    public void setBlank(boolean blank) {
        this.blank = blank;
    }
    public void setArrive(boolean arrive) {
        this.arrive = arrive;
    }
    public boolean isArrive() {
        return arrive;
    }


    @Override
    public Rectangle getRec() {
        return new Rectangle(getX(),getY(),0,0);
    }

    @Override
    public void paintSelf(Graphics g) {
        //添加生命值
        if(!passing && !flash && !arrive && !show)
            addHp(g,-10,0,80,20,Color.GREEN);
        //绘制图片
        g.drawImage(getImg(),getX() ,getY(),null);
        //改变画笔颜色
        g.setColor(Color.GREEN);
        //绘制中心原点
        //g.fillOval(getX()+50,getY()+80,10,10);
        //绘制矩形边框
        //g.drawRect(getX()+20,getY()+35+40,60,65);

        p = new Rectangle(getX() +20,getY()+35 +20,60,65);

        //边界
        border();
        if(sitMove){
            move();
            sit();
        }
        else{
            if (isSit())
                sitting(vkn);
            else{
                if (passing&&xianzhi)
                    setPass();
                else
                    move();

            }
        }

        setPass();
        if(960>=getX() && getX()>=760 && getY()>=570 && getY()<=770){
            xianzhi = true;
        }
        else
            xianzhi = false;
        setFlash1();
        setFlash2();
    }
}
