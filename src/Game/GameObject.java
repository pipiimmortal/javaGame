package Game;

import java.awt.*;
import java.util.ArrayList;

public abstract class GameObject {
    //坐标
    private int x;
    private int y;
    //图片
    Image img;

    //游戏界面

    GameFrame gameFrame;

    //玩家速度
    private int speed;

    //攻击判断
    boolean isAttack = false;

    boolean sit = false;

    //初始生命值
    private int hp;
    //当前生命值
    private int currentHp;

    //攻击目标
    private GameObject target;

    //是否有目标
    private boolean hasTarget = false;
    //攻击距离
    private int dis;
    //攻击时间间隔
    private int attackTime;
    //攻击是否在冷却状态
    private boolean attackCool = true;




    public GameObject(GameFrame gameFrame){
        this.gameFrame = gameFrame;
    }
    public GameObject(GameFrame gameFrame, int x , int y){
        this.gameFrame = gameFrame;
        this.x = x;
        this.y = y;
    }

    //返回矩形
    public abstract Rectangle getRec();

    //绘制元素
    public abstract  void paintSelf(Graphics g);

    //添加生命值
    public void addHp(Graphics g,int difX,int difY , int width,int height,Color color){
        //外部轮廓
        g.setColor(Color.black);
        g.drawRect(getX() - difX,getY() - difY,width,height);
        //生命条
        g.setColor(color);
        g.fillRect(getX() - difX,getY() - difY,(int)(width * (getCurrentHp()/getHp())),height);
    }

    //获取两点之间距离
    public double getDis(int x1,int y1,int x2,int y2){
        return Math.sqrt(Math.pow(x1 - x2,2) + Math.pow(y1 - y2 , 2));
    }

    //矩形与矩形碰撞检测
    public boolean revIntersectsRec(Rectangle r1,Rectangle r2){
        return r1.intersects(r2);
    }
    //矩形与圆的碰撞检测
    public boolean revIntersectsCir(Rectangle r,int x,int y,int dis){
        if (getDis(x,y,r.x,r.y) < dis
                || getDis(x,y,r.x + r.width,r.y) < dis
                ||getDis(x,y,r.x,r.y + r.height) < dis
                ||getDis(x,y,r.x + r.width,r.y + r.height) < dis)
            return true;
        return false;
    }

    //攻击方法
   // public void attack(ArrayList<GameObject> gameObjectArrayList){
    public void attack(GameObject gameObjectArrayList){
        if (hasTarget){
            if (isAttackCool()){
                System.out.println("11");
                //线程开始
                new AttackCD().start();
            }
        }else {
            //遍历列表
            //for (GameObject obj:gameObjectArrayList){
                //判断是否在攻击范围内（矩形与圆）
                if(revIntersectsCir(gameObjectArrayList.getRec(),getX(),getY(),getDis())){
                    //找到目标
                    target = gameObjectArrayList;
                    hasTarget = true;
                    //break;
                }
            //}
            if (!hasTarget && gameObjectArrayList == gameFrame.e2){
                if(revIntersectsCir(gameFrame.player.getRec(),getX(),getY(),getDis())){
                    //找到目标
                    target = gameFrame.player;
                    hasTarget = true;

                }
            }
        }
    }
    class AttackCD extends Thread{
        public void run(){
            //将攻击功能设置为冷却状态
            setAttackCool(false);
            //线程休眠
            try {
                Thread.sleep(attackTime);
            }catch (Exception e){
                e.printStackTrace();
            }
            //将攻击功能设置为攻击状态
            setAttackCool(true);
            //线程终止
            this.stop();
        }
    }



    public int getY(){
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public void setX(int x){
        this.x = x;
    }
    public Image getImg(){
        return img;
    }
    public void setImg(String img){
        this.img = Toolkit.getDefaultToolkit().getImage(img);
    }
    public GameFrame getGameFrame(){
        return gameFrame;
    }
    public void setGameFrame(GameFrame gameFrame){
        this.gameFrame = gameFrame;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public int getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(int currentHp) {
        this.currentHp = currentHp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }



    public boolean isSit() {
        return sit;
    }



    public void setSit(boolean sit) {
        this.sit = sit;
        if (!isSit()) {
            // 当起立时重置坐下图片序号
            setImg("image/正面.png");
        }
    }

    public GameObject getTarget() {
        return target;
    }

    public void setTarget(GameObject target) {
        this.target = target;
    }

    public int getDis() {
        return dis;
    }

    public void setDis(int dis) {
        this.dis = dis;
    }

    public boolean isHasTarget() {
        return hasTarget;
    }

    public boolean isAttackCool() {
        return attackCool;
    }

    public void setHasTarget(boolean hasTarget) {
        this.hasTarget = hasTarget;
    }
    public void setAttackCool(boolean attackCool) {
        this.attackCool = attackCool;
    }
    public int getAttackTime() {
        return attackTime;
    }

    public void setAttackTime(int attackTime) {
        this.attackTime = attackTime;
    }
    public boolean isAttack() {
        return isAttack;
    }

    public void setAttack(boolean attack) {
        isAttack = attack;
    }


}
