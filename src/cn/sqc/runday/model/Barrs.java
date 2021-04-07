package cn.sqc.runday.model;

import java.awt.*;

public class Barrs {
    public Image image;
    public Image[] images;
    public int WIDTH;
    public int HEIGHT;
    public int x,y;
    public int speed;
    public void step() {
        this.x -= this.speed;
    }
    public void drawBarr(Graphics g) {
        g.drawImage(this.image, this.x, this.y, this.WIDTH, this.HEIGHT, null);
    }

    public boolean outOfBounds() {
        return this.x <= -WIDTH;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image[] getImages() {
        return images;
    }

    public void setImages(Image[] images) {
        this.images = images;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public void setWIDTH(int WIDTH) {
        this.WIDTH = WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public void setHEIGHT(int HEIGHT) {
        this.HEIGHT = HEIGHT;
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
