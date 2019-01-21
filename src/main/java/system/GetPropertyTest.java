package system;

public class GetPropertyTest {
    public static void main(String[] args) {

        System.out.println(System.getProperty("user.dir"));//输出用户的主目录
        System.out.println(System.getProperty("java.version"));//输出
        System.out.println(System.getProperty("java.home"));//输出用户的主目录
        System.out.println(System.getProperty("os.name"));//输出操作系统的名称
        System.out.println(System.getProperty("os.arch"));//输出操作系统的架构
        System.out.println(System.getProperty("os.version"));//输出操作系统的版本
    }
}
