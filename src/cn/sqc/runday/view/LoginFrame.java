package cn.sqc.runday.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

/**
 * @author THEO
 * date 2021-3-23
 * 登录界面
 */
public class LoginFrame extends JFrame {
    JLabel userLabel;//用户名变量（文本）
    JTextField userField;//用户名输入框（文本输入框）
    JLabel userLabel2;//密码变量（文本）
    JPasswordField userFiled2;//密码输入框（文本输入框）
    JButton Login, Cancel;

    public LoginFrame() {
        userLabel = new JLabel("用户名");
        userLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        userLabel2 = new JLabel("密 码");
        userLabel2.setFont(new Font("微软雅黑", Font.BOLD, 18));

        //绝对布局
        userLabel.setBounds(20, 220, 100, 30);
        this.add(userLabel);
        userLabel2.setBounds(20, 280, 100, 30);
        this.add(userLabel2);

        //用户名输入框
        userField = new JTextField();
        userField.setBounds(80, 220, 100, 30);
        //设置输入框凹陷效果
        userField.setBorder(BorderFactory.createLoweredBevelBorder());
        //设置输入框背景透明
        userField.setOpaque(false);
        this.add(userField);

        userFiled2 = new JPasswordField();
        userFiled2.setBounds(80, 280, 100, 30);
        userFiled2.setBorder(BorderFactory.createLoweredBevelBorder());
        userFiled2.setOpaque(false);
        this.add(userFiled2);

        //登录按钮
        Login = new JButton("登录");
        Login.setBounds(45, 350, 60, 36);
//        Login.setBackground(new Color(44, 22, 44));//背景色
//        Login.setForeground(Color.BLUE);//前景色
        //绑定登录按钮的事件监听
        Login.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取输入框内容
                String userName = userField.getText();
                String password = new String(userFiled2.getPassword());
                if("THEO".equals(userName) && "Csh-020415".equals(password)) {
                    //登陆成功
                    JOptionPane.showMessageDialog(null, "欢迎" + userName + "来到天天酷跑");
                    //跳转到下一个界面

                    //关闭当前界面
                    dispose();
                } else if("".equals(userName) || "".equals(password)) {
                    JOptionPane.showMessageDialog(null, "用户名 / 密码不能为空，请重新输入");
                } else {
                    JOptionPane.showMessageDialog(null, "用户名 / 密码错误");
                }
            }
        });
        this.add(Login);

        //取消按钮
        Cancel = new JButton("取消");
        Cancel.setBounds(135, 350, 60, 36);
        this.add(Cancel);
        //绑定取消按钮的事件监听
        Cancel.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //创建背景面板，并添加到窗体上去
        LoginPanel panel = new LoginPanel();
        this.add(panel);

        //设置登录界面的基本属性
        this.setSize(900, 530);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(false);
        this.setTitle("天天酷跑-THEO制作");

        //设置窗体的Logo图标
        String path = getClass().getClassLoader().getResource("resources/images/115.png").getPath();
        this.setIconImage(new ImageIcon(path).getImage());
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new LoginFrame();
    }

    static class LoginPanel extends JPanel {//面板
        //背景图片变量
        Image background;
        public LoginPanel() {
            try {
                String path = getClass().getClassLoader().getResource("resources/images/login.jpg").getPath();
                background = ImageIO.read(new File(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //重写paint方法
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            //绘制背景图片
            g.drawImage(background, 0, 0, 900, 530, null);//900, 530为宽高
        }
    }
}