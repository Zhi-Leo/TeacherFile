package com.it.frame;

import com.it.pojo.User;
import com.it.pojo.Workload;
import com.it.util.DataUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * 主界面
 */

public class MainFrame extends JFrame {
    DefaultTableModel model;
    User currentUser;
    User user;
    JTable jtable;

    public MainFrame(User user) {
        this.user = user;
        setTitle("工作量管理系统 - " + user.getUsername());
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
        JMenuItem WorkloadFrame = new JMenuItem("添加工作量");
        JMenuItem exit = new JMenuItem("退出");
        menu.add(WorkloadFrame);
        menu.add(exit);

//        关闭当前页面，返回登录页面
        exit.addActionListener(e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
//        添加工作量
        WorkloadFrame.addActionListener(e -> {
            new WorkloadFrame(this, user).setVisible(true);
        });

        menuBar.add(menu);
        this.setJMenuBar(menuBar);

//        add(panel0);
//        setVisible(true);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//        setVisible(true);
//        setResizable(false);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setJMenuBar(menuBar);
//
//        帮助菜单栏含有关于
//        JMenuBar helpMenuBar = new JMenuBar();
//        JMenu helpMenu = new JMenu("帮助");
//        JMenuItem about = new JMenuItem("关于");
//        helpMenu.add(about);
//        helpMenuBar.add(helpMenu);
////        this.setJMenuBar(helpMenuBar);
//
//        about.addActionListener(e->{
//            JOptionPane.showMessageDialog(this, "作者：张三\n" +
//                    "学号：20202110010011001\n" +
//                    "联系方式：12345678901\n" +
//                    "邮箱：<EMAIL>");
//        });


        Object[] bt = new Object[]{"ID", "教师名称", "日期", "工作小时", "工作内容描述", "操作"};
        model = new DefaultTableModel(bt, 0);
//        根据表格模型创建表格组件
        jtable = new JTable(model);
//        设置表格行高为40
        jtable.setRowHeight(20);
//        添加菜单按钮编辑和删除
        TableColumn column = jtable.getColumnModel().getColumn(5);
        column.setCellRenderer(new ButtonRenderer());
        column.setCellEditor(new ButtonEditor(jtable, this));

//        创建一个滑动框讲表格包裹起来
        JScrollPane jsp = new JScrollPane(jtable);
        add(jsp);
//        设置表头居中，如果账户角色是admin那么显示所有教师工作量，如果是teacher，呢么显示我的工作量
//        三元表达式
        String title = "admin".equals(user.getUsername()) ? "所有教师工作量" : "我的工作量";
        JLabel titleTable = new JLabel(title);
//            设置字体为微软雅黑，加粗，16号
        titleTable.setFont(new Font("微软雅黑", Font.BOLD, 16));
//            居中显示
        titleTable.setHorizontalAlignment(JLabel.CENTER);
//            添加到顶部
        add(titleTable, BorderLayout.NORTH);
//        加载数据
        refreshTable();
    }

    void refreshTable() {
//        清空数据
        model.setRowCount(0);
        model.getColumnName(0);
//            获取所有工作量需要设置数据到模型
//        如果是admin那么显示所有教师工作，如果是teacher那么显示我的工作
        if ("admin".equals(user.getUsername())) {
            for (Workload workload : DataUtil.getAllWorkloads()) {
                model.addRow(new Object[]{workload.getId(),
                        workload.getTeacher(),
                        workload.getWorkDate(),
                        workload.getHours(),
                        workload.getDescription(),
                });
            }
        } else {
            ArrayList<Workload> workloads = DataUtil.getWorkloadsByTeacherName(user.getUsername());
            for (Workload workload : workloads) {
                model.addRow(new Object[]{workload.getId(),
                        workload.getTeacher(),
                        workload.getWorkDate(),
                        workload.getHours(),
                        workload.getDescription(),
                });
            }
        }
    }
}