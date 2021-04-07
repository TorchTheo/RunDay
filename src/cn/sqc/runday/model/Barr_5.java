package cn.sqc.runday.model;

import cn.sqc.runday.view.GameFrame;

import java.util.Random;

public class Barr_5 extends Barrs {
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
    Random random = new Random();
    public Barr_5() {
        this.speed = 20;
        this.x = GameFrame.WIDTH + 10;
        this.y = random.nextInt(600);
        try {
            getClass().getClassLoader().getResource("resources/images/" + (random.nextInt(6) + 21) + ".png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
