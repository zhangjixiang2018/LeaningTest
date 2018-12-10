package ui.SplitPane;

import javax.swing.*;

public class SplitPaneDemo extends JFrame {
    public static void main(String[] args) {
        new SplitPaneDemo().launch();
    }

    public void launch(){
        this.setBounds(500,500,500,500);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        final JSplitPane hSplitPane = new JSplitPane();//创建在水平方向上的分割板面
        JLabel labelLeft = new JLabel("左侧");//
        final JSplitPane vSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);//创建一个在竖直方向上的分割板面
        JLabel labelTop = new JLabel("上面");//
        JLabel labelBottom = new JLabel("下面");
        vSplitPane.setTopComponent(labelTop);//在竖直分割板面的上面放置标签
        vSplitPane.setBottomComponent(labelBottom);//在竖直分割板面的下面放置标签
        vSplitPane.setDividerLocation(320);//设置竖直分割板面的位置，分隔条距上边为50像素
        hSplitPane.setLeftComponent(vSplitPane);//在水平分割板面的左边放置标签
        hSplitPane.setRightComponent(labelLeft);//在水平分割板面的右边放置竖直分割板面
        hSplitPane.setDividerLocation(350);//设置水平分割板面的位置，分隔条距左边为50像素
        hSplitPane.setOneTouchExpandable(true);//设置水平分割面板分割条显示UI小部件
        this.add(hSplitPane);
        this.setVisible(true);
    }
}
