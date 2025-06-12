package com.it.frame;

import com.it.pojo.User;
import com.it.pojo.Workload;
import com.it.util.DataUtil;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;

public class WorkloadFrame extends JDialog {

    private User currentUser;// 当前登录用户
    private JTextField dateField;//  工作日期
    private JTextField hoursField;//  工作小时
    private JTextField teacherField;//  教师名称
    private JTextArea descArea;//  工作描述
    private MainFrame parent;//  父窗口,方便调用父窗口刷新数据
    private Workload workload;//  保存当前正在编辑的

    public WorkloadFrame(JFrame parent, User currentUser) {
        super(parent,"添加工作量", true);
        setSize(500, 350);
        setLocationRelativeTo(null);

        //设置一个最外围的面板
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //设置第一个部分：表单面板（4行2列）
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        //添加第一行
        formPanel.add(new JLabel("教师名称:"));
        //用来保存教师姓名的组件
        teacherField = new JTextField();
        //教师这一列采用下拉列表选择教师姓名
        JComboBox<String> teacherComboBox = new JComboBox<>();
        for (User user : DataUtil.getAllTeachers()) {
            teacherComboBox.addItem(user.getUsername());
        }
        //当选择了教师姓名，将教师姓名保存到teacherField中
        teacherField.setText(currentUser.getUsername());
        teacherComboBox.addActionListener(e -> {
            String selectedTeacher = (String) teacherComboBox.getSelectedItem();
            teacherField.setText(selectedTeacher);
        });
        formPanel.add(teacherComboBox);
        //第二行数据设置
        formPanel.add(new JLabel("工作日期 (yyyy-MM-dd):"));
        dateField = new JTextField();
        //默认设置当前日期（将当前日期格式化）
        dateField.setText(new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()));
        formPanel.add(dateField);
        //第三行数据设置
        formPanel.add(new JLabel("工作小时:"));
        hoursField = new JTextField(1);
        formPanel.add(hoursField);
        //第四行数据设置
        formPanel.add(new JLabel("工作描述:"));
        descArea = new JTextArea(3, 20);
        formPanel.add(new JScrollPane(descArea));

        //将表单面板添加到最外围面板
        panel.add(formPanel, BorderLayout.CENTER);

        // 按钮面板
        JPanel buttonPanel = new JPanel();
        JButton saveBtn = new JButton("保存");
        saveBtn.addActionListener(e -> saveWorkload());
        JButton cancelBtn = new JButton("取消");
        cancelBtn.addActionListener(e -> dispose());
        buttonPanel.add(saveBtn);
        buttonPanel.add(cancelBtn);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    // 保存工作量
    private void saveWorkload() {
        System.out.println("保存工作");
    }
}
