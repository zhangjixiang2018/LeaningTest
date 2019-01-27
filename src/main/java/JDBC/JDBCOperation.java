package JDBC;

import java.sql.*;

public class JDBCOperation {
    public static Connection conn = null;//创建Connection对象
    public static void main(String[] args) {
        Student student1 = new Student("s7","张强",22,"男","计算机");
        //JDBCOperation.insert(student1);//插入操作，想student表中添加一行记录
        //JDBCOperation.update();//更新操作，将计算机系学生年龄加1
        //JDBCOperation.delete();
        JDBCOperation.selectAll();
        JDBCOperation.tableExists("student");//判断student表是否存在
    }

   private static Connection getConnection(){
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

    //插入操作,向student表中添加一行新记录
    private static int insert(Student student){
        System.out.println("=====插入操作连接数据库中。。。。。=====");
        Connection connection = getConnection();//连接数据库
        System.out.println("=====插入操作连接数据库成功=====");
        int i = 0;
        String sql = "insert student(sNo,sName,sAge,sSex,sDept) values(?,?,?,?,?)";//定义预编译语句
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);//创建预处理对象
            //设置预编译语句参数
            preparedStatement.setString(1,student.getsNo());
            preparedStatement.setString(2,student.getsName());
            preparedStatement.setInt(3,student.getsAge());
            preparedStatement.setString(4,student.getsSex());
            preparedStatement.setString(5,student.getsDept());
            i=preparedStatement.executeUpdate();//执行SQL语句
            preparedStatement.close();//关闭预处理
            connection.close();//关闭与数据库的连接
            System.out.println("=====插入操作完成，已关闭与数据库的连接=====");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    //删除操作，将student表学号为s7的学生删除
    private static int delete(){
        int i =0 ;
        System.out.println("=====删除操作连接数据库中。。。。。=====");
        Connection connection = getConnection();//连接数据库
        System.out.println("=====删除操作连接数据库成功=====");
        String sql = "delete from student where Sno = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"s7");
            i=preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            System.out.println("=====删除操作完成，已关闭与数据库的连接=====");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    //更新操作，将计算机系学生年龄加1
    private static int update(){
        int i = 0 ;
        System.out.println("=====更新操作连接数据库中。。。。。=====");
        Connection connection = getConnection();//连接数据库
        System.out.println("=====更新操作连接数据库成功=====");
        String sql = "update student set Sage = Sage+1 where Sdept='计算机'";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);//创建预处理语句
            i=preparedStatement.executeUpdate();
            preparedStatement.close();//关闭预处理
            connection.close();//关闭与数据库的连接
            System.out.println("=====更新操作完成，已关闭与数据库的连接=====");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }

    //查询操作，查询学号为s1,s2,s3学生的所有信息
    private static void selectAll() {
        System.out.println("=====查询操作连接数据库中。。。。。=====");
        Connection connection = getConnection();//连接数据库
        System.out.println("=====查询操作连接数据库成功=====");
        String sql = "select * from student where Sno in(?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);//创建预处理语句
            //设置预编译语句参数
            preparedStatement.setString(1,"s1");
            preparedStatement.setString(2,"s3");
            preparedStatement.setString(3,"s5");
            //执行预编译语句
            ResultSet result = preparedStatement.executeQuery();

            //循环遍历查询结果集
            while (result.next()){
                System.out.println("学生的学号为： " + result.getString("Sno"));
                System.out.println("学生的姓名为： " + result.getString("Sname"));
                System.out.println("学生的年龄为： " + result.getInt("Sage"));
                System.out.println("学生的性别为： " + result.getString("Ssex"));
                System.out.println("学生的系别为： " + result.getString("Sdept"));
            }

            preparedStatement.close();//关闭预处理
            connection.close();//关闭与数据库的连接
            System.out.println("=====查询操作完成，已关闭与数据库的连接=====");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean tableExists(String tableName){
        Connection conn = getConnection();
        boolean bExists = false;
        String type [] = {"TABLE"};
        try {
            ResultSet resultSet = conn.getMetaData().getTables(
                    null,null,tableName,type);
            if(resultSet.next()){
                System.out.println("表存在");
                resultSet.getString(1);
                bExists = true;
            }else{
                System.out.println("表不存在");
                bExists = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bExists;
    }
}
