package com.it.frame;

import com.it.util.DataUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class deleteWorkload extends JDialog  {
    private MainFrame parent;// 父窗口,方便调用父窗口刷新数据
    private DefaultTableModel  tableModel;
    private int selectedRow;
    // 删除工作量
    public  deleteWorkload(MainFrame  parent,DefaultTableModel tableModel,int selectedRow) {
        this.parent = parent;
        this.tableModel = tableModel;
        this.selectedRow = selectedRow;
        // 判断是否已选择数据
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "请先选择要删除的记录");
            return;
        }
        // 弹出对话框提问是否删除
        int confirm = JOptionPane.showConfirmDialog(this, "确定要删除这条记录吗?", "确认删除",
                JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION){
            return;
        }
        //  删除数据
        String workloadId = (String) tableModel.getValueAt(selectedRow, 0);
        DataUtil.deleteWorkload(workloadId);
        parent.refreshTable();
        JOptionPane.showMessageDialog(this, "删除成功");
    }

}
