package ui.Menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MenuDemo extends JFrame {
    public static void main(String[] args) {
        new MenuDemo().launch();
    }

    public void launch(){
        this.setBounds(500,500,500,500);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        JMenuBar menuBar = new JMenuBar();//创建菜单栏
        JMenu menu_file = new JMenu("文件");//创建“文件”菜单
        JMenu menu_edit = new JMenu("编辑");//创建“编辑”菜单
        JMenuItem mi_new = new JMenuItem("新建");//创建“新建”菜单项
        JMenuItem mi_open = new JMenuItem("打开");//创建“打开”菜单项
        JMenuItem mi_exit = new JMenuItem("退出");//创建”退出“菜单项
        JMenuItem mi_save = new JMenuItem("保存");//创建”保存“菜单项
        JMenuItem mi_copy = new JMenuItem("复制");//创建”复制“菜单项
        JMenuItem mi_paste = new JMenuItem("粘贴");//创建”粘贴“菜单项

        //为退出菜单项添加动作事件监听器
        mi_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        menu_file.add(mi_new);//把“新建”菜单项添加到“文件”菜单上
        menu_file.add(mi_open);//把“打开”菜单项添加到“文件”菜单上
        menu_file.add(mi_exit);//把“退出”菜单项添加到“文件”菜单上
        menu_file.add(mi_save);//把“保存”菜单项添加到“文件”菜单上
        menu_edit.add(mi_copy);//把“复制”菜单项添加到“编辑”菜单上
        menu_edit.add(mi_paste);//把“粘贴”菜单项添加到“编辑”菜单上

        menuBar.add(menu_edit);//把“文件”菜单添加到菜单栏上
        menuBar.add(menu_file);//把“编辑”菜单添加到菜单栏上
        this.setJMenuBar(menuBar);//把菜单栏添加到窗体上
        this.setVisible(true);
    }
}
