package reflection;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

class User implements Serializable{
    private int userId ;
    public String userName ;
    public int age ;

    User(){}

    private User(int userId) {
        this.userId = userId;
    }

    public User(int userId, String userName){
        this.userId = userId ;
        this.userName = userName ;
    }

    public User(int userId, String userName, int age) {
        this.userId = userId;
        this.userName = userName;
        this.age = age;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private void print(int a ,String s){
        System.out.println(a + s);
    }
}


public class test1 {
    public static void main(String[] args) throws Exception{

        //第一种方法直接通过类名获取Class（一般不会这么用，因为实例都获取到了没必要多此一举）
        User user = new User();
        Class class1 = user.getClass();
        System.out.println("我是方法1反射处理的类=" + class1);
        System.out.println("我是方法1反射处理的父类=" + class1.getSuperclass());
        System.out.println("----------------------------------------");

        ///第二种方法通过类名的方法获取class需要增加对应的类引用
        Class class2 = User.class;
        System.out.println("我是方法2反射处理的类=" + class2);
        System.out.println("我是方法2反射处理的父类=" + class2.getSuperclass());
        System.out.println("----------------------------------------");

        //第三种方法通过全类名获取，用得比较多，推荐使用这种方法
        Class class3 = Class.forName("reflection.User");
        System.out.println("我是方法3反射处理的类=" + class3);
        System.out.println("我是方法3反射处理的父类=" + class3.getSuperclass());
        System.out.println("----------------------------------------");

        //获取单个构造方法
        Constructor constructor =  class3.getDeclaredConstructor(int.class,String.class,int.class);
        System.out.println("获取单个构造方法：" + constructor);
        System.out.println("----------------------------------------");

        //反射获取User对象的所有公共构造器
        System.out.println("----反射获取User对象的所有公共构造器---");
        Constructor<User>[] constructors = class3.getConstructors();
        int i = 0;
        for(Constructor c : constructors){
            System.out.println("我是User的第" + ++i + "个构造方法：" + c);
            System.out.print("我的参数有：");
            //得到构造函数参数类型的类类型数组
            Class[] paramTypes = c.getParameterTypes();
            for(Class p : paramTypes){
                String paramName = p.getName();//得到该参数的类型名
                System.out.print(paramName + "   ");
            }
            System.out.println();
        }
        System.out.println("----------------------------------------");

        //反射获取User对象的所有构造器
        System.out.println("----反射获取User对象的所有公共构造器---");
        Constructor<User>[] constructors1 = class3.getDeclaredConstructors();
        i = 0;
        for(Constructor c : constructors1){
            System.out.println("我是User的第" + ++i + "个构造方法：" + c);
            System.out.print("我的参数有：");
            //得到构造函数参数类型的类类型数组
            Class[] paramTypes = c.getParameterTypes();
            for(Class p : paramTypes){
                String paramName = p.getName();//得到该参数的类型名
                System.out.print(paramName + "   ");
            }
            System.out.println();
        }
        System.out.println("----------------------------------------");

        //通过getMethod方法获获取某个具体的public方法
        Method method = class3.getMethod("setUserName",String.class);
        System.out.println("通过getMethod方法获取类的某个具体方法: " + method);
        //通过反射机制使用这个方法
        method.invoke(user,"zjx");
        System.out.println(user.getUserName());
        System.out.println("----------------------------------------");

        //通过getDeclaredMethod()方法获获取任意一个具体的方法，可以是private的
        Method method1 = class3.getDeclaredMethod("print", int.class, String.class);
        System.out.println("通过getDeclaredMethod()方法获获取任意一个具体的方法: " + method1);
        //通过反射机制使用这个方法
        //method1.invoke(user,1,"zjx");//获取到的private方法好像并不能使用
        System.out.println("----------------------------------------");

        String sname = class3.getSimpleName();
        System.out.println("我是类名不包含包路径 = "+sname);
        System.out.println("----------------------------------------------------");

        String name = class3.getName();
        System.out.println("我是类名包含包路径 = "+name);
        System.out.println("----------------------------------------------------");

        Package packA=class3.getPackage();
        System.out.println("我是类的包路径 = "+packA.getName());
        System.out.println("----------------------------------------------------");

        Class<?>[] interfaces=class3.getInterfaces();
        for(i=0;i<interfaces.length;i++){
            System.out.println("我是实现的接口"+(i+1)+" = "+interfaces[i]);
        }
        System.out.println("----------------------------------------------------");

        // 返回此类或接口以整数编码的 Java 语言修饰符。修饰符由 Java 虚拟机的 public、 protected、 private、 final、 static、 abstract 和 interface 对应的常量组成；
        // 它们应当使用 Modifier 类的方法来解码
        Integer modefiers = class3.getModifiers();
        System.out.println("我是类的modefiers = "+modefiers);

    }
}
