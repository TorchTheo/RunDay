package cn.sqc.runday.model;

import cn.sqc.runday.view.GameFrame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.Random;

public class Barr_4 extends Barrs{
    public static final int WIDTH =150;
    public static final int HEIGHT =350;
    public Barr_4() {
        Random random = new Random();
        this.images = new Image[4];
        try {
            String path = getClass().getClassLoader().getResource("resources/images/11.png").getPath();
            this.images[0] = ImageIO.read(new File(path));
            path = getClass().getClassLoader().getResource("resources/images/12.png").getPath();
            this.images[1] = ImageIO.read(new File(path));
            path = getClass().getClassLoader().getResource("resources/images/13.png").getPath();
            this.images[2] = ImageIO.read(new File(path));
            path = getClass().getClassLoader().getResource("resources/images/14.png").getPath();
            this.images[3] = ImageIO.read(new File(path));
        }catch (Exception e) {
            e.printStackTrace();
        }
        this.image = this.images[random.nextInt(4)];
        this.x = GameFrame.WIDTH + 1500;
        this.y = 0;
        this.speed = 20;
    }
}
