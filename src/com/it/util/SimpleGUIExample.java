package com.it.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SimpleGUIExample {
    public static void main(String[] args) {
        // 创建JFrame实例
        JFrame frame = new JFrame("GUI入门");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置关闭按钮
        frame.setSize(400, 300);//设置窗口大小

        // 设置主布局为BorderLayout
        frame.setLayout(new BorderLayout());//设置布局 北、东、南、西、中

        // 创建顶部面板，使用FlowLayout
        JPanel topPanel = new JPanel(new FlowLayout());//设置布局 从左到右排列
        JLabel label = new JLabel("姓名:");
        JTextField textField = new JTextField(15); // 文本框
        topPanel.add(label);
        topPanel.add(textField);

        // 添加topPanel到顶部
        frame.add(topPanel, BorderLayout.NORTH);//添加到顶部（北)

        // 创建中间面板，使用GridLayout(行数，列数)
        JPanel centerPanel = new JPanel(new GridLayout(0, 1));

        JTextArea textArea = new JTextArea(5, 20);
        textArea.setToolTipText("这里可以写很多文字！");
        JScrollPane scrollPane = new JScrollPane(textArea); // 增加滚动条支持
        centerPanel.add(scrollPane);

        String[] options = {"雷电", "暴风", "真火"};
        JComboBox<String> comboBox = new JComboBox<>(options);
        centerPanel.add(comboBox);

        // 添加centerPanel到中部
        frame.add(centerPanel, BorderLayout.CENTER);

        // 创建底部面板，使用FlowLayout
        JPanel bottomPanel = new JPanel(new FlowLayout());
        JButton button = new JButton("提交");
        button.addActionListener((ActionEvent e) -> {
            String name = textField.getText();
            String des = textArea.getText();
            String selected = (String) comboBox.getSelectedItem();
            //弹出Dialog 将上面三个信息展示出来
            JOptionPane.showMessageDialog(frame, "姓名：" + name + "\n描述：" + des + "\n类别：" + selected);
        });
        bottomPanel.add(button);

        // 添加bottomPanel到底部（南）
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // 显示窗口
        frame.setVisible(true);
    }
}

class gui{
    public static void main(String[] args) {
        JFrame jf=new JFrame("GUI");
        jf.setSize(500,400);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


//        设置内部组件布局
        jf.setLayout(new BorderLayout());


//        新建面板(装姓名和输入框)
        JPanel jp1=new JPanel();
        JLabel jl=new JLabel("姓名");
        JTextField  jtf=new JTextField(20);
        jp1.add(jl);
        jp1.add(jtf);

//        添加到顶部
        jf.add(jp1,BorderLayout.NORTH);

//        再次新建面板，装文本域和下拉框
        JPanel jp2=new JPanel(new GridLayout(2,1));
        JTextArea jta=new JTextArea("这是一个文本框，显示信息",10,20);
        JScrollPane sr=new JScrollPane(jta);
        JComboBox jcb=new JComboBox();
        jcb.addItem("选项1");
        jcb.addItem("选项2");
        jcb.addItem("选项3");
        jp2.add(sr);
        jp2.add(jcb);
        jf.add(jp2,BorderLayout.CENTER);


//        添加一个按钮
        JPanel jp3=new JPanel();
        JButton jb=new JButton("提交");
        jb.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("姓名："+jtf.getText());
                System.out.println("内容："+jta.getText());
                System.out.println("选项："+jcb.getSelectedItem());
            }
        });
        jp3.add(jb);
        jf.add(jp3,BorderLayout.SOUTH);
        jb.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("姓名："+jtf.getText());
                System.out.println("内容："+jta.getText());
                System.out.println("选项："+jcb.getSelectedItem());
            }
        });
        JOptionPane.showMessageDialog(jf,"提交成功！");

        jf.setVisible(true);


    }
}

