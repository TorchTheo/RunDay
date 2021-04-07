package cn.sqc.runday.view;

import cn.sqc.runday.controller.WindowFrame;
import cn.sqc.runday.model.Person;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

public class EndFrame extends JFrame implements MouseListener {
    JLabel again, exit, back;
    public EndFrame(Person person) {
        String path = getClass().getClassLoader().getResource("resources/images/hh5.png").getPath();
        again = new JLabel(new ImageIcon(path));
        again.setBounds(520, 622, 60, 25);
        again.addMouseListener(this);
        this.add(again);
        path = getClass().getClassLoader().getResource("resources/images/hh6.png").getPath();
        back = new JLabel(new ImageIcon(path));
        back.setBounds(520, 722, 60, 25);
        back.addMouseListener(this);
        this.add(back);
        path = getClass().getClassLoader().getResource("resources/images/hh3.png").getPath();
        exit = new JLabel(new ImageIcon(path));
        exit.setBounds(520, 822, 60, 25);
        exit.addMouseListener(this);
        this.add(exit);

        EndPanel end = new EndPanel(person);
        this.add(end);

        this.setSize(1500, 900);
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon("Image/115.png").getImage());
        this.setVisible(true);
    }

    class EndPanel extends JPanel {
        Image background;
        Person p;
        public EndPanel(Person person) {
            this.p = person;
            try {
                String path = getClass().getClassLoader().getResource("resources/images/chou.png").getPath();
                background = ImageIO.read(new File(path));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.drawImage(background, 0, 0,1500,900 ,null);
            g.setColor(Color.CYAN);
            g.setFont(new Font("宋体",Font.BOLD,30));
            g.drawString(p.getScore()+"",1110,705);// + ” “ 属实妙
            g.drawString(p.getDistance() + " ", 1110, 622);

            g.setFont(new Font("宋体",Font.BOLD,50));
            g.setColor(Color.ORANGE);
            g.drawString(p.getTotalScore() + "", 1075, 500);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource().equals(again)){
            //跳转到下一界面
            new WindowFrame().Start();
            //关闭当前界面
            dispose();
        }	else if(e.getSource().equals(back)){
            new MainFrame();
            dispose();
        }else if(e.getSource().equals(exit)){
            System.exit(0);
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

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
