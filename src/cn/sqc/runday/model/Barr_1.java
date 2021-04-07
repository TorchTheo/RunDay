package cn.sqc.runday.model;

import cn.sqc.runday.view.GameFrame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Barr_1 extends Barrs{

    int index;
    public static final int WIDTH=100;
    public static final int HEIGHT=110;
    public Barr_1() {
        this.images = new Image[2];
        try {
            String path = getClass().getClassLoader().getResource("resources/images/a2.png").getPath();
            this.images[0] = ImageIO.read(new File(path));
            path = getClass().getClassLoader().getResource("resources/images/a4.png").getPath();
            this.images[1] = ImageIO.read(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.image = images[0];
        this.x= GameFrame.WIDTH+100;
        this.y=580;
        this.speed =30;
        index = 0;
    }

    public void step() {//切换图片
        image =images[index++/5%images.length];
        this.x -= this.speed;//切换图片实现螃蟹爪子张合的动态效果的同时，使其向左移动
    }

}
