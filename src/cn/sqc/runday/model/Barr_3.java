package cn.sqc.runday.model;

import cn.sqc.runday.view.GameFrame;

import javax.imageio.ImageIO;
import java.io.File;

public class Barr_3 extends Barrs {
    public static final int WIDTH = 150;
    public static final int HEIGHT=70;
    public Barr_3() {
        this.x = GameFrame.WIDTH + 1000;
        this.y = 450;
        this.speed = 25;
        try {
            String path = getClass().getClassLoader().getResource("resources/images/daodan.png").getPath();
            this.image = ImageIO.read(new File(path));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
