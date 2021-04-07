package cn.sqc.runday.model;

import java.awt.*;

public class Barr_2 extends Barrs{
    int index;
    public static final int WIDTH= 70;
    public static final int HEIGHT = 60;
    public Barr_2() {
        init();
        this.image = this.images[0];
        this.x = 300;
        this.y = 460;
    }

    public void drop() {
        this.y ++;
        if(this.y >= 460) {
            this.y = 460;
        }
    }

    public void step() {
        this.image = this.images[index++ / 2 % images.length];
    }

    public void init() {
        this.images = new Image[6];
        for(int i = 0; i < 6; i++) {
            try {
                getClass().getClassLoader().getResource("resources/images/d" + (i + 1) + ".png");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
