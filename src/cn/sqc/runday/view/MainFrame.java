package cn.sqc.runday.view;

import cn.sqc.runday.controller.WindowFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

/**
 * @author THEO
 * date 2021-3-24
 */

public class MainFrame extends JFrame implements MouseListener {
    //创建开始 帮助 离开按钮
    JLabel start, help, exit;
    JPanel MainPanel;
     public MainFrame() {
         String path;
         path = getClass().getClassLoader().getResource("resources/images/hh1.png").getPath();
         start = new JLabel(new ImageIcon(path));
         start.setBounds(350, 320, 150, 40);
         start.setEnabled(false);
         start.addMouseListener(this);
         this.add(start);

         path = getClass().getClassLoader().getResource("resources/images/hh2.png").getPath();
         help = new JLabel(new ImageIcon(path));
         help.setBounds(350, 420, 150, 40);
         help.setEnabled(false);
         help.addMouseListener(this);
         this.add(help);

         path = getClass().getClassLoader().getResource("resources/images/hh3.png").getPath();
         exit = new JLabel(new ImageIcon(path));
         exit.setBounds(350, 520, 150, 40);
         exit.setEnabled(false);
         exit.addMouseListener(this);
         this.add(exit);

         //实现背景图片及窗体属性
         MainPanel panel = new MainPanel();
         this.add(panel);
//         panel.add(start);
//         panel.add(exit);
//         panel.add(help);

         //设置窗体基本属性大小 居中 边框隐藏 默认关闭按钮 logo图标
         this.setSize(1200, 730);
         this.setLocationRelativeTo(null);//居中
         this.setUndecorated(true);//隐藏边框
         this.setDefaultCloseOperation(EXIT_ON_CLOSE);
         path = getClass().getClassLoader().getResource("resources/images/115.png").getPath();
         this.setIconImage(new ImageIcon(path).getImage());//logo
         this.setVisible(true);
         this.setTitle("天天酷跑-THEO制作");
     }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource().equals(start)) {
            //跳转到下一界面
            new WindowFrame().Start();
            //new WindowFrame().start();
        }else if(e.getSource().equals(exit)) {
            dispose();
        }else if(e.getSource().equals(help)) {
            JOptionPane.showMessageDialog(null, "有问题请联系开发者");
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(e.getSource().equals(start)) {
            start.setEnabled(true);
        }else if(e.getSource().equals(help)) {
            help.setEnabled(true);
        }else if(e.getSource().equals(exit)) {
            exit.setEnabled(true);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource().equals(start)) {
            start.setEnabled(false);
        }else if(e.getSource().equals(help)) {
            help.setEnabled(false);
        }else if(e.getSource().equals(exit)) {
            exit.setEnabled(false);
        }
    }

    class MainPanel extends JPanel {
         Image background;
         public MainPanel() {
             try {
                 String path = getClass().getClassLoader().getResource("resources/images/main.png").getPath();
                 background = ImageIO.read(new File(path));
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }

         public void paint(Graphics g) {
             g.drawImage(background, 0, 0, 1200, 730, null);
         }
     }

    public static void main(String[] args) {
        new MainFrame();
    }
}
