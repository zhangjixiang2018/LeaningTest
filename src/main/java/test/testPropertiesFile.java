package test;

import org.junit.Test;

import java.io.IOException;
import java.util.Properties;


public class testPropertiesFile {

    @Test
    public void test1() {
        int i = 1 ;
        Properties props = new Properties();
        try {
            props.load(this.getClass().getClassLoader().getResourceAsStream("test/test.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(i);
        i = Integer.parseInt(props.getProperty("zjx"));
        System.out.println(i);
    }
}
