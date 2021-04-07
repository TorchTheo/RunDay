package cn.sqc.runday.controller;

import cn.sqc.runday.model.*;
import cn.sqc.runday.view.EndFrame;
import cn.sqc.runday.view.GameFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Arrays;

public class GamePanel extends JPanel implements KeyListener {
    //背景图片
    Image background;
    Image score;
    Image pause;
    Image proceed;

    Person person;//玩家
    Barr_2 barr_2;//宠物
    Barr_4 barr_4;//鱼钩
    Barr_5 barr_5;//金币

    Barr_1[]barr1s = {};//存储螃蟹数组（没有元素，可以扩容）
    Barr_3[]barr3s = {};//导弹
    Barr_4[]barr4s = {};//鱼钩
    Barr_5[]barr5s = {};

    public GamePanel() {
        person = new Person();
        barr_2 = new Barr_2();
        try {
            String path = getClass().getClassLoader().getResource("resources/images/cc.png").getPath();
            background = ImageIO.read(new File(path));
            path = getClass().getClassLoader().getResource("resources/images/a12.png").getPath();
            score = ImageIO.read(new File(path));
            path = getClass().getClassLoader().getResource("resources/images/b2.png").getPath();
            pause = ImageIO.read(new File(path));
            path = getClass().getClassLoader().getResource("resources/images/b1.png").getPath();
            proceed = ImageIO.read(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    int x = 0;
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if(flag) {
            x -= 20;
        }

        g.drawImage(background, x, 0, GameFrame.WIDTH, GameFrame.HEIGHT, null);
        g.drawImage(background, x+GameFrame.WIDTH, 0, GameFrame.WIDTH, GameFrame.HEIGHT,null);
        if(x<=-GameFrame.WIDTH){//实现两张图片之间的切换
            x = 0;
        }
        person.paintPerson(g);

        for (Barr_1 barr1 : barr1s) {
            barr1.drawBarr(g);
        }

        barr_2.drawBarr(g);

        for (Barr_3 barr3 : barr3s) {
            barr3.drawBarr(g);
        }

        for (Barr_4 barr4 : barr4s) {
            barr4.drawBarr(g);
        }

        for (Barr_5 barr5 : barr5s) {
            barr5.drawBarr(g);
        }

        g.drawImage(score, 120, 50, null);
        g.setColor(Color.ORANGE);
        g.setFont(new Font("宋体",Font.BOLD,30 ));
        g.drawString("玩家得分："+person.getScore()+"分", 133, 95);

        if(flag) {
            g.drawImage(proceed, 200, 800, 90, 90, null);
        } else {
            g.drawImage(pause, 200, 800, 90, 90, null);
        }
    }

    int index = 0;

    public void enteredAction() {//源源不断地产生障碍物
        index++;
        if(index%100==0){
            //生成一个螃蟹
            Barr_1 b1 = new Barr_1();
            Barr_3 b3 = new Barr_3();
            Barr_4 b4 = new Barr_4();

            barr1s = Arrays.copyOf(barr1s,barr1s.length+1);//数组扩容
            barr1s[barr1s.length-1]= b1;//放到数组最后一个元素的位置
            barr3s =Arrays.copyOf(barr3s,barr3s.length+1);
            barr3s[barr3s.length-1]= b3;
            barr4s =Arrays.copyOf(barr4s,barr4s.length+1);
            barr4s[barr4s.length-1]= b4;
        }
        if(index%15==0){
            Barr_5 b5 = new Barr_5();
            barr5s = Arrays.copyOf(barr5s, barr5s.length +1);
            barr5s[barr5s.length-1] = b5;
        }
    }

    public void stepAction() {
        person.step();
        person.drop();
        barr_2.drop();
        for(int i = 0; i < barr3s.length; i++) {
            barr3s[i].step();
            if(barr3s[i].outOfBounds()) {
                barr3s[i] = barr3s[barr3s.length - 1];
                barr3s = Arrays.copyOf(barr3s, barr3s.length - 1);
            }
        }

        for(int i = 0; i < barr4s.length; i++) {
            barr4s[i].step();
            if(barr4s[i].outOfBounds()) {
                barr4s[i] = barr4s[barr4s.length - 1];
                barr4s = Arrays.copyOf(barr4s, barr4s.length - 1);
            }
        }

        for(int i = 0; i < barr5s.length; i++) {
            barr5s[i].step();
            if(barr5s[i].outOfBounds()) {
                barr5s[i] = barr5s[barr5s.length - 1];
                barr5s = Arrays.copyOf(barr5s, barr5s.length - 1);
            }
        }
    }

    public void pengAction() {
        for (Barr_1 barr1 : barr1s) {//上下左右都写了，下是用不到的
            if (person.getX() + Person.WIDTH >= barr1.getX() &&
                    person.getX() <= barr1.getX() + Barr_1.WIDTH &&
                    person.getY() + Person.getHEIGHT() >= barr1.getY() &&
                    person.getY() <= barr1.getY() + Barr_1.HEIGHT) {
                //碰撞后的处理（遮挡类障碍物）
                if (person.getX() + Person.WIDTH <= barr1.getX() + Barr_1.WIDTH) {//防止人在右边，碰撞后可以穿过障碍物
                    //左碰撞
                    person.setX(barr1.getX() - Barr_1.WIDTH);
                } else {
                    //右碰撞
                    person.setX(barr1.getX() + Barr_1.WIDTH);
                }
            }
        }

        for (Barr_3 barr3 : barr3s) {
            if (person.getX() + Person.WIDTH >= barr3.getX() &&
                    person.getX() <= barr3.getX() + Barr_3.WIDTH &&
                    person.getY() + Person.getHEIGHT() >= barr3.getY() &&
                    person.getY() <= barr3.getY() + Barr_3.HEIGHT) {
                if (person.getX() + Person.WIDTH <= barr3.getX() + Barr_3.WIDTH) {//玩家的宽度（120px）是比障碍物小的
                    //左碰撞
                    person.setX(barr3.getX() - Barr_3.WIDTH);
                } else {
                    //右碰撞
                    person.setX(barr3.getX() + Barr_3.WIDTH);
                }
            }
        }

        for(int i = 0;i<=barr4s.length -1;i++){//小心数组越界！
            if(person.getX() + Person.WIDTH >= barr4s[i].getX() &&
                    person.getX() <= barr4s[i].getX() + Barr_4.WIDTH &&
                    person.getY() + Person.HEIGHT >= barr4s[i].getY() &&
                    person.getY() <= barr4s[i].getY() + Barr_4.HEIGHT	){
                if(person.getX() + Person.WIDTH <= barr4s[i].getX() + Barr_4.WIDTH	){
                    //左碰撞
                    person.setX(barr4s[i].getX() - Barr_4.WIDTH);
                }else{
                    //右碰撞
                    person.setX(barr4s[i].getX()+ Barr_4.WIDTH	);
                }
            }
        }

        for(int i = 0;i<barr5s.length;i++){
            if(person.getX() + Person.WIDTH >= barr5s[i].getX() &&
                    person.getX() <= barr5s[i].getX()	 + Barr_5.WIDTH  &&
                    person .getY() +Person.getHEIGHT() >= barr5s[i].getY() &&
                    person.getY()	<= barr5s[i].getY () + Barr_5.HEIGHT){//判断玩家与金币的碰撞
                if(person.getX() + Person.WIDTH <= barr5s[i].getX() + Barr_5.WIDTH){
                    //删除当前金币
                    barr5s[i]	= barr5s[barr5s.length - 1];
                    barr5s = Arrays.copyOf(barr5s, barr5s.length - 1);

                    //玩家加分
                    int score = person.getScore();
                    person.setScore(score + 10);
                }
            }
        }
    }

    public void gameOverAction() {
        if(person.outOfBounds()) {
            isGameOver = true;
        }

        new EndFrame(person);

        person = new Person();
        barr1s = new Barr_1[]{};
        barr3s = new Barr_3[]{};
    }

    public static boolean isGameOver = false;
    boolean flag = true;

    public void action() {
        new Thread() {
            @Override
            public void run() {
                while(!isGameOver) {
                    if(flag) {
                        enteredAction();
                        stepAction();
                        pengAction();
                        gameOverAction();
                    }
                    repaint();
                    try {
                        Thread.sleep(60);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int x = person.getX();
        int y = person.getY();
        int x1 = barr_2.getX();
        int y1 = barr_2.getY();

        if(e.getKeyCode() == KeyEvent.VK_UP && y > 10 && y1 > 10) {
            person.setY(y - 25);
            barr_2.setY(y1 - 25);
        }

        if(e.getKeyCode() == KeyEvent.VK_DOWN && y <= 560 && y1 < 560) {
            person.setY(y + 30);
            barr_2.setY(y1 - 25);
        }

        if(e.getKeyCode() == KeyEvent.VK_LEFT && x >= 0) {
            person.setY(x - 30);
            barr_2.setY(x1 - 30);
        }

        if(e.getKeyCode() == KeyEvent.VK_RIGHT && x >= 0) {
            person.setY(x + 22);
            barr_2.setY(x1 + 22);
            if(x >= GameFrame.WIDTH - Person.WIDTH) {
                person.setX(GameFrame.WIDTH - Person.WIDTH);
            }

            if(x1 >= GameFrame.WIDTH - Person.WIDTH) {
                barr_2.setX(GameFrame.WIDTH - Person.WIDTH);
            }
        }

        if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            flag = !flag;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
