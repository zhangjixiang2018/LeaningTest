package test;

import org.junit.Test;

public class TestEnum {

    enum Direction {L,LU,U,RU,R,RD,D,LD,STOPO};

    @Test
    /**
     * values()方法可以将枚举类转变为一个枚举类型的数组，因为枚举中没有下标，
     * 我们没有办法通过下标来快速找到需要的枚举类，这时候，转变为数组之后，
     * 我们就可以通过数组的下标，来找到我们需要的枚举类
     */
    public void testValues() {
        Direction[] ds = Direction.values();

        for(int i=0 ; i<ds.length ; i++) {
           System.out.println(ds[i]);
        }
    }
}
