package ui.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PopupMenuDome extends JFrame {
    public static void main(String[] args) {
       new PopupMenuDome().launch();
    }

    public void launch(){
        this.setBounds(500,500,500,500);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        final JPopupMenu popupMenu = new JPopupMenu();//创建弹出菜单
        JMenuItem mi_new = new JMenuItem("新建");//创建“新建”菜单项
        JMenuItem mi_open = new JMenuItem("打开");//创建“打开”菜单项
        JMenuItem mi_exit = new JMenuItem("退出");//创建”退出“菜单项
        //将菜单项添加到弹出菜单
        popupMenu.add(mi_new);
        popupMenu.add(mi_open);
        popupMenu.addSeparator();//添加分隔符
        popupMenu.add(mi_exit);

        final JLabel label = new JLabel();
        label.addMouseListener(new MouseAdapter() {//为标签添加鼠标事件监听器
            public void mouseReleased(MouseEvent e) {//执行标签的鼠标事件
                //如果弹出菜单的当前组件标签将鼠标事件视为弹出菜单的触发器
                if(e.isPopupTrigger()){
                    popupMenu.show(e.getComponent(),e.getX(),e.getY());
                }
            }
        });
        label.setOpaque(true);
        label.setBackground(Color.RED);
        this.add(label);
        this.setVisible(true);
    }
}
