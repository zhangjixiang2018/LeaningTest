package serializable;

import java.io.*;

public class ObjectSer {

    public static void main(String[] args) {
        Student stu = new Student(1,"tom",12);
        serializeObject(stu);
        Student stu1 = (Student) unSerialize();
        System.out.println(stu1.age);
        System.out.println(stu1.id);
        System.out.println(stu1.name);
    }

    /**
     * 串行化对象
     */
    public static void serializeObject(Serializable src){
        try {
            FileOutputStream fos = new FileOutputStream("D:/java/idea/data.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(src);
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 反串行
     */
    public static Serializable unSerialize(){
        try {
            FileInputStream fis = new FileInputStream("D:/java/idea/data.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Serializable ser = (Serializable) ois.readObject();
            return ser ;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
