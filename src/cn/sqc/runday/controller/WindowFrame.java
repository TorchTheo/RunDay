package cn.sqc.runday.controller;

import cn.sqc.runday.view.GameFrame;

import javax.swing.*;
import java.awt.*;

/**
 * @author THEO
 * @date 2021-3-25
 */

public class WindowFrame extends JFrame implements Runnable {

    JLabel background;
    JProgressBar jdt;//进度条

    //创建线程并启动
    public void Start() {
        WindowFrame frame = new WindowFrame();
        Thread t = new Thread(frame);
        t.start();
        dispose();
    }

    public WindowFrame() {
        String path;
        path = getClass().getClassLoader().getResource("resources/images/hbg.jpg").getPath();
        background = new JLabel(new ImageIcon(path));
        this.add(BorderLayout.NORTH, background);//放在窗口上

        jdt = new JProgressBar();
        jdt.setStringPainted(true);//加载以字符串形式显示
        jdt.setBackground(Color.ORANGE);
        this.add(BorderLayout.SOUTH, jdt);

        this.setSize(568, 340);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setUndecorated(true);
        path = getClass().getClassLoader().getResource("resources/images/115.png").getPath();
        this.setIconImage(new ImageIcon(path).getImage());
        this.setVisible(true);
        this.setAlwaysOnTop(true);
    }

    @Override
    public void run() {
        int[] values = {0, 1, 3, 10, 23, 32, 40, 47, 55, 66, 76, 86, 89, 95, 99, 99, 99, 100};
        for (int value : values) {
            jdt.setValue(value);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        new GameFrame();
    }

    public static void main(String[] args) {
        new WindowFrame().Start();
    }
}
