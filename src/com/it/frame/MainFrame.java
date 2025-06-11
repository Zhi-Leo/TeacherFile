package com.it.frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * 主界面
 */

public class MainFrame extends JFrame {
    public MainFrame(String user) {

        setTitle("工作量管理系统 - " + user);
//        设置大小
        setSize(750, 500);
//        设置最小化关闭
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        空间定位
        setLocationRelativeTo(null);
//        打开页面
        setVisible(true);
//        设置三行五列
        JPanel panel0 = new JPanel(new GridLayout(3, 1));

//        新建菜单装操作里面有添加工作量和退出选项，并且放到左上角
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("操作");
        JMenuItem addWorkload = new JMenuItem("添加工作量");
        JMenuItem exit = new JMenuItem("退出");
        menu.add(addWorkload);
        menu.add(exit);
        menuBar.add(menu);
        setJMenuBar(menuBar);
//        关闭当前页面，返回登录页面
        exit.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
        add(panel0);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


////        帮助菜单栏含有关于
//        JMenuBar helpMenuBar = new JMenuBar();
//        JMenu helpMenu = new JMenu("帮助");
//        JMenuItem about = new JMenuItem("关于");
//        helpMenu.add(about);
//        helpMenuBar.add(helpMenu);
//        setJMenuBar(helpMenuBar);
//        about.addActionListener(e->{
//            JOptionPane.showMessageDialog(this, "作者：张三\n" +
//                    "学号：20202110010011001\n" +
//                    "联系方式：12345678901\n" +
//                    "邮箱：<EMAIL>");
//        });
    }
}
