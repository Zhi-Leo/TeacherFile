package com.it.frame;

import com.it.pojo.User;
import com.it.util.DataUtil;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    //  用户名输入框
    private JTextField usernameField;
    // 密码输入框
    private JPasswordField passwordField;

    public LoginFrame() {
        //  设置标题和图标
        setTitle("教师工作量管理系统 - 登录");
        //  设置大小
        setSize(350, 250);
        // 设置关闭按钮
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //传入 null 时，表示相对于屏幕中央位置进行定位。
        setLocationRelativeTo(null);

        // 创建面板4行1列
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));

        //  设置边框
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 用户名
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        userPanel.add(new JLabel("用户名:"));
        usernameField = new JTextField(30);
        userPanel.add(usernameField);
        panel.add(userPanel);

        // 密码
        JPanel passPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passPanel.add(new JLabel("密  码:"));
        passwordField = new JPasswordField(30);//用密文，即不显示密码passwordField
        passPanel.add(passwordField);
        panel.add(passPanel);

        //  登录按钮
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton loginBtn = new JButton("登录");
        // 添加点击事件
        loginBtn.addActionListener(e -> login());

        JButton registerBtn = new JButton("注册");
        // 添加点击事件
        registerBtn.addActionListener(e -> register());

        //  添加按钮
        btnPanel.add(loginBtn);
        btnPanel.add(registerBtn);
        panel.add(btnPanel);
        //  添加面板
        add(panel);
    }


    /**
     * 注册
     */
    private void register() {
        // 1. 获取用户名和密码
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        // 2.  校验用户名和密码
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "用户名和密码不能为空");
            return;
        }
        // 3. 校验用户是否存在
        User user = DataUtil.getUserByUsername(username);
        // 4. 用户已存在
        if (user != null) {
            JOptionPane.showMessageDialog(this, "用户已存在");
            return;
        }
        // 5.将用户信息持久化到本地存储中
        user = new User();
        user.setId(String.valueOf(System.currentTimeMillis()));
        user.setUsername(username);
        user.setPassword(password);
        user.setRole("teacher");
        DataUtil.saveUser(user); //  保存用户信息
        // 6. 提示用户注册成功
        JOptionPane.showMessageDialog(this, "注册成功,请点击登录");
//    点击注册按钮后打开另外一个窗口，展示注册登记表，并绑定原窗口不可用
//        JFrame boundFrame=new JFrame("注册表单");
//        boundFrame.setSize(500,500);
//        boundFrame.setLocationRelativeTo(orig);


    }


/**
*登录
 */
    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        // 检查用户名和密码是否为空
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "用户名和密码不能为空");
            return;
        }
        //  查询用户
        User  user = DataUtil.getUserByUsername(username);
        if (user == null) {
            JOptionPane.showMessageDialog(this, "用户不存在");
            return;
        }
        if (!password.equals(user.getPassword())) {
            JOptionPane.showMessageDialog(this, "密码错误");
            return;
        }
        // 关闭登录窗口
        dispose();
        // 登录成功

        if (password.equals(user.getPassword())&&user.getRole().equals("teacher")) {
            JOptionPane.showMessageDialog(this, "登陆成功");
            // 关闭登录窗口
        }
        new MainFrame(user.getUsername()).setVisible(true);
    }

}
