package cn.sqc.runday.model;

import cn.sqc.runday.view.GameFrame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

public class Person {
    private Image cur_image;//当前玩家的显示图片
    private Image[] images;//所有玩家图片
    public static final int WIDTH = 120;//玩家宽高
    public static final int HEIGHT = 120;


    private int x, y;//起始位置
    int index;//切换图片使用
    private int score;//得分
    private int distance;//距离

    public Person() {
        init();//给图片赋值
        cur_image = images[0];
        x = 90;
        y = 580;
        index = 0;
        score = 0;
        distance = 0;
    }

    public void drop() {//玩家的下落
        y += 5;
        if(y >= 580) {
            y = 580;
        }
    }

    public void step() {
        cur_image = images[index ++ / 3 % images.length];//图片的切换
        //坐标的更改
        distance += 2;
    }

    public void paintPerson(Graphics g) {
        g.drawImage(cur_image, x, y, WIDTH, HEIGHT, null);
    }

    public boolean outOfBounds() {//判断是否越界
        return this.x >= GameFrame.WIDTH || this.x <= -WIDTH;
    }

    public void init() {
        images = new Image[9];
        for(int i = 0; i < images.length; i++) {
            try {
                String path = getClass().getClassLoader().getResource("resources/images" + (i + 1) + ".png").getPath();
                images[i] = ImageIO.read(new File(path));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Image getCur_image() {
        return cur_image;
    }

    public void setCur_image(Image cur_image) {
        this.cur_image = cur_image;
    }

    public Image[] getImages() {
        return images;
    }

    public void setImages(Image[] images) {
        this.images = images;
    }

    public static int getWIDTH() {
        return WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getTotalScore() {
        return (int)(score * 10 + distance * 0.6);
    }
}
