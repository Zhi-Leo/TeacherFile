package com.it.frame;

import com.it.pojo.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
第五列渲染器和编辑器
 */
class ButtonRenderer implements TableCellRenderer {
    private JPanel panel;// 按钮面板
    private JButton editBtn;// 修改按钮
    private JButton deleteBtn;//  删除按钮

    /** 构造方法 */
    public ButtonRenderer() {
        //  1. 创建面板
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        // 2. 创建编辑和删除按钮
        editBtn = new JButton("修改");
        deleteBtn = new JButton("删除");
        // 3. 设置按钮样式
        editBtn.setMargin(new Insets(0, 5, 0, 5));
        deleteBtn.setMargin(new Insets(0, 5, 0, 5));
        // 4. 将按钮添加到面板中
        panel.add(editBtn);
        panel.add(deleteBtn);
    }

    /**
     * 返回一个包含两个按钮的面板
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        return panel;
    }
}

// 自定义按钮面板编辑器
class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private User  user;
    private JPanel panel;// 按钮面板
    private JButton editBtn;//  修改按钮
    private JButton deleteBtn;//  删除按钮
    private int editedRow;// 被修改的行
    
    public ButtonEditor(JTable table, MainFrame frame, User user, DefaultTableModel model) {
        this.user = user;
        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        editBtn = new JButton("修改");
        deleteBtn = new JButton("删除");
        // 设置按钮样式
        editBtn.setMargin(new Insets(0, 5, 0, 5));
        deleteBtn.setMargin(new Insets(0, 5, 0, 5));
        // 修改按钮事件
        editBtn.addActionListener(e -> {
            fireEditingStopped();//  通知表格编辑结束
            frame.etditWordload(editedRow);
        });
        // 删除按钮事件
        deleteBtn.addActionListener(e -> {
            fireEditingStopped();//  通知表格编辑结束
            //弹出对话框，提示是删除
            int confirm = JOptionPane.showConfirmDialog(table, "确定删除此行吗?", "确认删除", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) { // 如果用户确认删除，则调用方法执行删除（传入被删除的行）
                frame.deleteWorkload(frame, model, editedRow);
            }
        });
        
        panel.add(editBtn);//  将按钮添加到面板中
        panel.add(deleteBtn);//  将按钮添加到面板中
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        this.editedRow = row;//  记录被修改的行
        return panel;
    }
    
    @Override
    public Object getCellEditorValue() {
        return ""; // 返回值不重要，因为我们直接处理按钮事件
    }
}
