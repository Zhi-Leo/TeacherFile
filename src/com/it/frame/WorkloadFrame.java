package com.it.frame;

import com.it.pojo.User;
import com.it.pojo.Workload;
import com.it.util.DataUtil;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WorkloadFrame extends JDialog {

    private User user; // 当前登录用户
    private JTextField teacherField = new JTextField(); // 教师名称
    private JTextField dateField = new JTextField(); // 工作日期
    private JTextField hoursField = new JTextField(); // 工作小时
    private JTextArea descArea = new JTextArea(); // 工作描述
    private MainFrame parent; // 父窗口,方便调用父窗口刷新数据
    private Workload workload = new Workload(); // 保存当前正在编辑的

    public WorkloadFrame(JFrame parent, User currentUser) {
        super(parent, "添加工作量", true);
        this.user = currentUser;
        this.parent = (MainFrame) parent; // 正确初始化父窗口引用
        setSize(450, 300);
        setLocationRelativeTo(null);

        // 设置一个最外围的面板
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));

        // 设置第一个部分：表单面板（4行1列）
        JPanel formPanel = new JPanel(new GridLayout(4, 1, 3, 3));

        // 创建第一行的面板，左右 布局
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel teacher = new JLabel("教师名称:");
        JComboBox<String> teacherCBox = new JComboBox<>();

        for (User user : DataUtil.getAllTeachers()) {
            teacherCBox.addItem(user.getUsername());
        }

        teacherField.setText(currentUser.getUsername());
        teacherCBox.addActionListener(e -> {
            String selectedTeacher = (String) teacherCBox.getSelectedItem();
            teacherField.setText(selectedTeacher);
        });

        row1.add(teacher);
        row1.add(teacherCBox);
        formPanel.add(row1);

        // 第二行数据设置
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel riqi = new JLabel("工作日期:");
        dateField = new JTextField(20);
        dateField.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        row2.add(riqi);
        row2.add(dateField);
        formPanel.add(row2);

        // 第三行数据设置
        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel how = new JLabel("工作小时:");
        hoursField = new JTextField(20);
        row3.add(how);
        row3.add(hoursField);
        formPanel.add(row3);

        // 第四行数据设置
        JPanel row4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel miao = new JLabel("工作描述:");
        descArea = new JTextArea(3, 30);
        JScrollPane scrollPane = new JScrollPane(descArea);
        row4.add(miao);
        row4.add(scrollPane);
        formPanel.add(row4);

        // 将表单面板添加到最外围面板
        panel.add(formPanel, BorderLayout.CENTER);

        // 添加保存和取消按钮
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
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
        // 获取4个字段的信息
        String teacherName = teacherField.getText();
        float workHours;

        // 验证工作小时是否为有效数字
        try {
            workHours = Float.parseFloat(hoursField.getText());
            if (workHours <= 0) {
                JOptionPane.showMessageDialog(this, "工作小时必须大于0", "输入错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "请输入有效的工作小时数", "输入错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String workDate = dateField.getText();
        String description = descArea.getText();

        // 创建Workload对象并设置属性
        workload.setTeacher(teacherName);
        workload.setHours(workHours);
        workload.setWorkDate(workDate);
        workload.setDescription(description);

        // 保存数据
        DataUtil.saveWorkload(workload);

        // 刷新父窗口表格
        if (parent != null) {
            parent.refreshTable();
        }

        // 关闭当前窗口
        dispose();
    }
}
