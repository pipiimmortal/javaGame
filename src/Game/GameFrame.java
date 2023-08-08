package Game;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileInputStream;


public class GameFrame extends JFrame{
    //关卡设置
    public int level = 1;
    //窗口尺寸
    private int windowWidth = 1800;
    private int windowHeight = 1200;
    //双缓存图片
    private Image offScreenImage = null;
    //游戏背景
    Background background = new Background(this);
    //玩家
    GamePlayer player = new GamePlayer(this);
    //野怪小兵
    Enemy2 e2 = new Enemy2(this);
    Enemy3 e3 = new Enemy3(this);

    //雕像
    Sculpture sculpture = new Sculpture(this);
    Sculpture2 sculpture2 = new Sculpture2(this);
    Sculpture3 sculpture3 = new Sculpture3(this);
    Sculpture4 sculpture4 = new Sculpture4(this);
    Sculpture5 sculpture5 = new Sculpture5(this);
    SculptureTwo1 sculptureTwo1 = new SculptureTwo1(this);
    SculptureTwo2 sculptureTwo2 = new SculptureTwo2(this);
    //子弹
    Bullet bullet = new Bullet(this,e2,player);

    //实现文本框
    private String[] text = {"封剑碑：剑冢入口","恭喜你，冒险者，机遇就在眼前！", "按F键进入！"};
    int index;
    private Color textColor = Color.YELLOW;
    private Color borderColor = Color.BLACK;

    //按钮事件
    private boolean buttonClicked = false;
    // 绘制按钮边框
    int buttonWidth = 100; // 按钮宽度
    int buttonHeight = 30; // 按钮高度
    int buttonX = 1000; // 按钮的横坐标
    int buttonY = 700; // 按钮的纵坐标
    //鼠标
    private int clickCount = 0; // 点击计数器
    // 剩余时间
    public int timeRemaining = 3;


    public void launch(){
        //设置尺寸
        setSize(windowWidth,windowHeight);
        //窗口居中
        setLocationRelativeTo(null);
        //关闭事件
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //用户不能调整窗口大小
        setResizable(false);
        //标题
        setTitle("零点工作室");
        //窗口可见
        setVisible(true);
        //添加键盘监视器
        this.addKeyListener(new keyMonitor());
        // 添加按钮点击事件监听器
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clickCount++;
                handleButtonClick();
                playMusic();

            }
        });

        // 创建计时器，每隔25毫秒触发一次绘制
        Timer timer = new Timer(25, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });

        // 启动计时器
        timer.start();
    }

    @Override  // 使用 @Override 注解
    public void paint(Graphics g){
        //查找坐标
        //System.out.println(player.getX()+ "  "+player.getY());
        //碰撞检测验证
        /*   S =  new Rectangle(100, 100, 100, 100);
            g.setColor(Color.GREEN);
            g.drawRect(100,100,100,100);
            System.out.println(player.p);
            System.out.println(player.revIntersectsRec(player.p,S));*/
        //创建双缓存图片
        if (offScreenImage == null) {
            offScreenImage = this.createImage(1800,1200);
        }
        Graphics gImage = offScreenImage.getGraphics();
        gImage.clearRect(0, 0, windowWidth, windowHeight);
        switch (level) {
            case 1:
                background.paintSelf(gImage);
                player.paintSelf(gImage);
                //e2.paintSelf(gImage);
                //e3.paintSelf(gImage);
                sculpture.paintSelf(gImage);
                sculpture2.paintSelf(gImage);
                sculpture3.paintSelf(gImage);
                sculpture4.paintSelf(gImage);
                sculpture5.paintSelf(gImage);
                //文本转换
                if (player.show) {
                int width ;
                if (text[index].equals("按F键进入！")) {
                    width = 115;
                } else if (text[index].equals("封剑碑：剑冢入口")) {
                    width = 180;
                } else
                    width = 315;
                box(880, 600, width, 50, 1, gImage);
            }
                if (clickCount == 3) {
                player.show = false;
                clickCount = 0;
            }
                //倒计时
                if (player.passing) {
                gImage.setFont(new Font("黑体", Font.BOLD, 50));
                gImage.setColor(Color.BLACK);
                gImage.drawString("倒计时:" + timeRemaining, 750, 300);

            }
                break;
            case 2:
                background.bg = Toolkit.getDefaultToolkit().getImage("image/第二关素材/第二关.jpg");
                gImage.drawImage(background.bg,0,0,null);
                player.paintSelf(gImage);
                sculptureTwo1.paintSelf(gImage);
                sculptureTwo2.paintSelf(gImage);
                break;


        }



        g.drawImage(offScreenImage,0,0,this);



    }


    public void box(int x,int y,int textWidth,int textHeight,int decorSize,Graphics gImage){
        setBox(x,y,textWidth,textHeight,decorSize,gImage);
        // 绘制文字内容

        gImage.setFont(new Font("宋体", Font.BOLD, 20));
        gImage.setColor(textColor);
        gImage.drawString(text[index], 880+10, 600+35);

        gImage.setColor(Color.BLACK);
        gImage.drawRect(buttonX, buttonY, buttonWidth, buttonHeight);
        // 绘制按钮文字
        String buttonText = "切换文字";
        if (text[index].equals("按F键进入！")){
            buttonText = "关闭";
        }
        gImage.drawString(buttonText, buttonX + 10, buttonY + buttonHeight / 2);
    }

    public void launchTime() {


        //JLabel label = new JLabel();
        Timer timer = new Timer(1000,  new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeRemaining--; // 倒计时递减
                //label.setText("倒计时: " + timeRemaining); // 更新倒计时文本
                if (timeRemaining == 0) { // 倒计时结束
                    ((Timer) e.getSource()).stop(); // 停止计时器
                    player.setPassing(false);
                    JOptionPane.showMessageDialog(null, "传送完成！点击确认"); // 弹出消息框
                    player.setBlank(false);
                    setLevel(2);
                    player.setImg("image/sit/1.png");

                }
            }
        });

        timer.start(); // 启动计时器
    }

    //边框制作
    public void setBox(int x,int y,int textWidth,int textHeight,int decorSize,Graphics gImage){

        // 绘制修饰边框
        gImage.setColor(Color.black);

        // 绘制外层修饰边框
        gImage.drawRect(x - decorSize, y - decorSize, textWidth + 2 * decorSize, textHeight + 2 * decorSize);
        // 绘制内层修饰边框
        gImage.drawRect(x + decorSize, y + decorSize, textWidth - 2 * decorSize, textHeight - 2 * decorSize);
        // 绘制边框缝隙填充
        gImage.setColor(Color.BLUE);
        gImage.fillRect(x - decorSize, y + decorSize, 2*decorSize, textHeight -   2*decorSize); // 左边缝隙填充
        gImage.fillRect(x + textWidth -decorSize, y + decorSize, 2*decorSize, textHeight - 2 * decorSize); // 右边缝隙填充
        gImage.fillRect(x - decorSize, y+ textHeight -decorSize, textWidth + 2 * decorSize,2*decorSize); // 下边缝隙填充
        gImage.fillRect(x-decorSize,y-decorSize,textWidth+2*decorSize,2*decorSize); // 上边缝隙填充

    }


    public static void main(String[] args) {
        GameFrame gameFrame = new GameFrame();

        gameFrame.launch();
    }

    //键盘监视
    class keyMonitor extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();
            //System.out.println(key);
            player.KeyPressed(e);

        }
        public void keyReleased(KeyEvent e){
            player.KeyReleased(e);
        }
    }


    //鼠标监视
    private void handleButtonClick() {
        //buttonClicked = !buttonClicked; // 切换按钮的状态
        index++;
        if (index == 3)
            index = 0;
      /*  // 切换显示的文字
        if (buttonClicked) {
            text = "点击按钮了";
        } else {
           text ="我来了";
        }
        repaint();
*/
    }
    public void setTimeRemaining(int timeRemaining) {
        this.timeRemaining = timeRemaining;
    }
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    // 播放音效方法

    public void playMusic() {
        try {
            FileInputStream fileAu = new FileInputStream("C:\\Users\\86182\\Desktop\\14312.WAV");
            AudioStream as = new AudioStream(fileAu);
            AudioPlayer.player.start(as);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
