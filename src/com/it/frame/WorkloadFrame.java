package com.it.frame;

import com.it.pojo.User;
import com.it.pojo.Workload;
import com.it.util.DataUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class WorkloadFrame extends JDialog {

    private User user; // 当前登录用户
    private JTextField teacherField = new JTextField(20); // 教师名称
    private JTextField dateField = new JTextField(20); // 工作日期
    private JTextField hoursField = new JTextField(20); // 工作小时
    private JTextArea descArea = new JTextArea(3, 30); // 工作描述
    private MainFrame parent; // 父窗口,方便调用父窗口刷新数据
    private Workload workload; // 保存当前正在编辑的

    public WorkloadFrame(JFrame parent, Workload  workload0, User user) {
        super(parent, workload0==null?"添加工作量":"编辑工作量", true);
        this.workload = workload==null?new Workload():workload;
        this.user = user;
        this.parent = (MainFrame) parent; // 正确初始化父窗口引用
        setSize(450, 300);
        setLocationRelativeTo(null);//居中显示

        if(workload0 != null) {this.workload = workload0;}
        else this.workload = new Workload();


        // 设置一个最外围的面板
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));

        // 设置第一个部分：表单面板（4行1列）
        JPanel formPanel = new JPanel(new GridLayout(4, 1, 3, 3));

        /// 创建第一行的面板，左右 布局
        JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel teacher = new JLabel("教师名称:");
        JComboBox<String> teacherCBox = new JComboBox<>();

        if(("admin").equals(user.getRole())){
            for (User user0 : DataUtil.getAllTeachers()) {
                teacherCBox.addItem(user0.getUsername());
            }
        }else{teacherCBox.addItem(user.getUsername());}

//设置教师名字
        if(workload0 != null) {
            // 编辑老师 - 应该是设置选中项而不是获取
            teacherCBox.setSelectedItem(workload0.getTeacher());
            teacherField.setText(workload0.getTeacher());
        } else {
            // 新建 - 使用当前用户信息
            teacherField.setText(user.getUsername());
            teacherCBox.setSelectedItem(user.getUsername());
        }

        teacherField.setText(user.getUsername());
        teacherCBox.addActionListener(e -> {
            String selectedTeacher = (String) teacherCBox.getSelectedItem();
            teacherField.setText(selectedTeacher);
        });

        row1.add(teacher);
        row1.add(teacherCBox);
        formPanel.add(row1);

        /// 第二行数据设置
        JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel date = new JLabel("工作日期:");

//设置工作日期
        if(workload0 != null) {
            // 编辑工作日期 - 应该设置文本而不是获取
            dateField.setText(workload0.getWorkDate()); // 假设属性名为getWorkDate()
        } else {
            // 新建模式下可能需要设置默认日期
            dateField.setText(LocalDate.now().toString()); // 使用当前日期作为默认值
        }

        dateField.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        row2.add(date);
        row2.add(dateField);
        formPanel.add(row2);



        /// 第三行数据设置
        JPanel row3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel how = new JLabel("工作小时:");

//设置工作小时
//        工作时长hoursField输入框只能输入数字,限定一下
        hoursField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c)) {
                    e.consume();
                }
                }
        });

        if(workload!=null){
            //编辑工作小时
            hoursField.setText(workload.getHours()+"");
        }

        row3.add(how);
        row3.add(hoursField);
        formPanel.add(row3);

        /// 第四行数据设置
        JPanel row4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel miao = new JLabel("工作描述:");
//设置工作描述
        if(workload!=null&&workload.getDescription()!=null){
            //编辑工作描述
            descArea.setText(workload.getDescription());
            descArea.setCaretPosition(0);

        }else{
            teacherField.setText(user.getUsername());
        }
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
        String teacherName = teacherField.getText();
        float workHours=Float.parseFloat(hoursField.getText());
        String workDate = dateField.getText();
        String description = descArea.getText();

//        工作日期不能早于今天(但可以等于今天)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date inputDate = sdf.parse(workDate);
            Date yesterday = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
            if (inputDate.before(yesterday)) {
                JOptionPane.showMessageDialog(this, "工作日期不能早于今天！");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "日期格式错误！请输入yyyy-MM-dd格式的日期。");
            return;
        }

        // 创建Workload对象并设置属性
        workload.setTeacher(teacherName);
        workload.setHours(workHours);
        workload.setWorkDate(workDate);
        workload.setDescription(description);
        // 保存数据
        DataUtil.saveWorkload(workload);
        // 刷新父窗口表格
        parent.refreshTable();
        // 关闭当前窗口
        dispose();
    }
}