package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConn {
    public Connection conn = null;//创建Connection对象
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");//加载数据库驱动
            String url = "jdbc:mysql://localhost:3306/test?serverTimezone=GMT%2B8";//指定连接数据库的URL
            String user="root";//指定数据库的用户名
            String password = "mysql";//指定数据库的密码
            conn = DriverManager.getConnection(url,user,password);//获取连接
            if(conn != null) //如果Connection实例不为空，输出提示信息
                System.out.println("数据库连接成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn ;
    }

    public static void main(String[] args) {
        GetConn getConn = new GetConn();
        getConn.getConnection();
    }
}
