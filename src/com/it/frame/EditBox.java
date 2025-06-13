package com.it.frame;

import com.it.pojo.User;
import com.it.pojo.Workload;
import com.it.util.DataUtil;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditBox extends JDialog {
    private User user;
    private JTextField teacherField=new JTextField(20);
    private JTextField dateField=new JTextField(20);
    private JTextField hoursField=new JTextField(20);
    private JTextArea descArea=new JTextArea(3,20);
    private MainFrame  mainFrame;
    private Workload workload=new Workload();

//    找到当前选择框内的本地信息并获取到变量

//    private String teacher0=workload.getTeacher();
//    private String workDate0=workload.getWorkDate();
//    private float  hours0=workload.getHours();
//    private String desc0=workload.getDescription();

    public EditBox(JFrame Jframe,User username)  {
        super(Jframe,"编辑工作量"+username.getUsername(),true);
        this.user=username;
//        System.out.println(teacher0);
//        System.out.println(workDate0);
//        System.out.println(hours0);
//        System.out.println(desc0);
        this.mainFrame=(MainFrame)Jframe;
        setSize(450, 300);
        setLocationRelativeTo(null);
//        设置最外围的面板
        JPanel panel = new JPanel(new BorderLayout(5,5));
        panel.setBorder(BorderFactory.createEmptyBorder(5,20,5,20));

//        设置第一部分，表单面板
        JPanel formPanel = new JPanel(new GridLayout(4, 1, 3, 3));
//        创建第一行的面板，左右 布局
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel teacher=new JLabel("教师名称:");
        JComboBox<String> teacherBox=new JComboBox<>();

        for(User users: DataUtil.getAllTeachers())
        {
            teacherBox.addItem(users.getUsername());
        }
//       设置当前选择的用户为登录的用户
        teacherBox.setSelectedItem(user.getUsername());
        teacherField.setText(user.getUsername());

        teacherBox.addActionListener(e->{
            String user0=(String) teacherBox.getSelectedItem();
            teacherField.setText(user0);
        });

        row1.add(teacher);
        row1.add(teacherBox);
        formPanel.add(row1);

//        第二行工作日期设置
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel date=new JLabel("工作日期:");
//        设置初始化日期为当前选择的信息框的日期
        if (workload != null && workload.getWorkDate() != null) {
            dateField.setText(workload.getWorkDate());
        } else {
            dateField.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        }
        formPanel.add(dateField);

        row2.add(date);
        row2.add(dateField);
        formPanel.add(row2);

//        第三行工作小时设置
        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel hours=new JLabel("工作小时:");
//      获取当前对话框的工作小时
        if (workload != null && workload.getHours() != 0) {
            hoursField.setText(String.valueOf(workload.getHours()));
        }
        row3.add(hours);
        row3.add(hoursField);
        formPanel.add(row3);

//        第四行工作描述设置
        JPanel row4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel desc=new JLabel("工作描述:");
//        descArea.setText(desc0);

        row4.add(desc);
        row4.add(descArea);
        formPanel.add(row4);

//      将表单面板添加到最外围面板
        panel.add(formPanel, BorderLayout.CENTER);

//        在显示页面最底下添加保存和取消按钮
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton saveBtn = new JButton("保存");
        saveBtn.addActionListener(e -> { saveWorkload();});
        JButton cancelBtn = new JButton("取消");
        cancelBtn.addActionListener(e -> {dispose();});

        btnPanel.add(saveBtn);
        btnPanel.add(cancelBtn);

        panel.add(btnPanel,  BorderLayout.SOUTH);

        add(panel);

    }
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

        // 修改当前Workload对象的四个字段的信息


//        workload.setTeacher(teacherName);
//        workload.setHours(workHours);
//        workload.setWorkDate(workDate);
//        workload.setDescription(description);

        // 保存数据
        DataUtil.saveWorkload(workload);

        // 刷新父窗口表格
        if (mainFrame != null) {
            mainFrame.refreshTable();
        }

        // 关闭当前窗口
        dispose();
    }
}
