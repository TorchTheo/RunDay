package cn.sqc.runday.view;

import cn.sqc.runday.controller.GamePanel;

import javax.swing.*;
import java.awt.*;

/**
 * @author THEO
 * @date 2021-3-26
 * 游戏主界面：显示窗体，承载游戏的主面板类
 */
public class GameFrame extends JFrame {
    //设置窗体宽高
    public static final int WIDTH = 1500;
    public static final int HEIGHT = 900;

    public GameFrame() {
        GamePanel panel = new GamePanel();
        panel.action();
        this.addKeyListener(panel);
        this.add(panel);

        //设置窗体基本属性
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setUndecorated(true);
        String path = getClass().getClassLoader().getResource("resources/images/115.png").getPath();
        this.setIconImage(new ImageIcon(path).getImage());
    }
}
