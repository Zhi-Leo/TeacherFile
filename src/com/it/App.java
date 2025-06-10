package com.it;

import com.it.frame.LoginFrame;

import javax.swing.*;

/**
 * 启动类
 */
public class App {

    public static void main(String[] args) {
        // 确保在事件调度线程中运行Swing组件
        SwingUtilities.invokeLater(() -> {
            try {
                // 设置系统外观
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                // 创建并显示登录窗口
                LoginFrame loginFrame = new LoginFrame();
                loginFrame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "启动失败: " + e.getMessage(),
                        "错误", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

}
