package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class BoundInterfaceDemo {
    public static void main(String[] args) {
        // 创建主界面
        JFrame mainFrame = new JFrame("主界面");
        mainFrame.setSize(400, 300);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);

        // 主界面内容面板
        JPanel mainPanel = new JPanel();
        JButton openBoundButton = new JButton("打开绑定界面");

        // 打开绑定界面的按钮事件
        openBoundButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 打开绑定界面并禁用主界面
                openBoundInterface(mainFrame);
            }
        });

        mainPanel.add(openBoundButton);
        mainFrame.add(mainPanel);
        mainFrame.setVisible(true);
    }

    /**
     * 打开绑定界面并禁用原界面
     */
    private static void openBoundInterface(JFrame originalFrame) {
        // 禁用原界面
        originalFrame.setEnabled(false);

        // 创建绑定界面
        JFrame boundFrame = new JFrame("绑定界面");
        boundFrame.setSize(300, 200);
        boundFrame.setLocationRelativeTo(originalFrame);
        boundFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // 添加绑定界面内容
        JPanel boundPanel = new JPanel();
        JLabel messageLabel = new JLabel("这是绑定界面，原界面已被禁用");
        JButton closeButton = new JButton("关闭绑定界面");

        // 关闭绑定界面的按钮事件
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 关闭绑定界面并恢复原界面
                boundFrame.dispose();
                originalFrame.setEnabled(true);
            }
        });

        boundPanel.add(messageLabel);
        boundPanel.add(closeButton);
        boundFrame.add(boundPanel);

        // 添加窗口关闭事件处理
        boundFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // 关闭绑定界面并恢复原界面
                boundFrame.dispose();
                originalFrame.setEnabled(true);
            }
        });

        boundFrame.setVisible(true);
    }
}