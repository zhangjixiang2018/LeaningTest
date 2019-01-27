package DerbyTest;

import java.io.File;
import java.sql.*;

public class BiSheDome {
    //数据库驱动
    private static final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static String url = "jdbc:derby:db_EICS";//数据库URL
    private static Connection conn = null ; //数据库连接

    public static void main(String[] args) {
        BiSheDome biSheDome = new BiSheDome();
        biSheDome.selectTb_location();
        if(biSheDome.tableExists("TB_USERS"))//检查表tb_users是否存在,Derby数据库要用大写，mysql可以用小写
            System.out.println("表tb_users存在");
    }

    public BiSheDome(){
        try {
            Class.forName(driver);
            if(!dbExists()){//如果数据库不存在
                conn = DriverManager.getConnection(url + ";create=true");
                createTable();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void selectTb_location() {
        String sql = "select * from tb_location";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();

            while(resultSet.next()){
                System.out.println("x: " + resultSet.getInt(1));
                System.out.println("y: " + resultSet.getInt(2));
                System.out.println("w: " + resultSet.getInt(3));
                System.out.println("h: " + resultSet.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void createTable() {
        String createUserSql = "create table tb_users ("
                + "ip varchar(16) primary key,"
                + "host varchar(30),"
                + "name varchar(20),"
                + "tooltip varchar(50),"
                + "icon varchar(50))";
        String createLocationSql = "create table tb_location ("
                + "xLocation int ," + "yLocation int ,"
                + "width int," + "height int )";

        try {
            Statement stmt = conn.createStatement();
            stmt.execute(createUserSql);
            stmt.execute(createLocationSql);
            addDefLocation();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addDefLocation() {
        String sql = "insert into tb_location values(?,?,?,?)";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1,100);
            pst.setInt(2,0);
            pst.setInt(3,240);
            pst.setInt(4,500);
            pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean dbExists() {//测试数据库是否存在，存在返回true
        Boolean bExists = false;
        File dbFileDir = new File("db:EICS");
        if(dbFileDir.exists()){
            bExists = true;
        }
        return bExists;
    }

    public boolean tableExists(String tableName){
        boolean bExists = false;
        try {
            ResultSet resultSet = conn.getMetaData().getTables(
                    null,null,tableName,null);
           /* while(resultSet.next()){//查出数据库中存在的所有表
                System.out.println(resultSet.getString("TABLE_NAME"));
            }*/
           bExists = resultSet.next();
            /*if(resultSet.next()){
                bExists = true;
            }else{
                bExists = false;
            }*/
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bExists;
    }
}
