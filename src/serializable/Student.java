package serializable;

import java.io.*;

public class Student implements Serializable {

    public int id ;
    public String name ;
    public int age ;

    public Student(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

}
