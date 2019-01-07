package ui;

import javax.swing.*;
import java.awt.*;

class MyComboBox extends AbstractListModel implements ComboBoxModel{

    String selectedItem = null ;
    String[] test = {"身份证","军人证","学生证","工作证"};

    /**
     * 设置下拉列表框中的选项
     */
    public void setSelectedItem(Object item) {
        selectedItem = (String)item;
    }

    /**
     * 返回下拉列表中选择的项
     */
    public Object getSelectedItem() {
        return selectedItem;
    }

    /**
     * 返回列表长度
     */
    public int getSize() {
        return test.length;
    }

    /**
     * 返回指定索引处的值
     */
    public Object getElementAt(int index) {
        return test[index];
    }
}
public class TestJComboBox extends JFrame {
    JComboBox jc = new JComboBox(new MyComboBox());
    JLabel jl = new JLabel("请选择证件");

    TestJComboBox(){
    }

    public void launch(){
        this.setBounds(500,500,160,120);
        this.setLayout(new FlowLayout());
        this.setTitle("在窗口中设置下拉列表框");
        this.add(jl);
        this.add(jc);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {

        new TestJComboBox().launch();
    }
}
