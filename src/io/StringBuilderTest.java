package io;

public class StringBuilderTest {
    public static void main(String[] args) {
       String str = "";
       long startTime = System.currentTimeMillis();//记录字符串执行操作的起始时间
       for(int i=0 ; i<10000; i++){
           str = str + i ;
       }
       long endTime = System.currentTimeMillis();//记录字符串执行操作的结束时间
       long time = endTime - startTime ;
       System.out.println("String消耗的时间为：" + time);

       StringBuilder stringBuilder = new StringBuilder("");//创建字符串产生器
        startTime = System.currentTimeMillis();//记录字符串执行操作的起始时间
        for(int i=0 ; i<10000; i++){
            stringBuilder.append(i);
        }

        endTime = System.currentTimeMillis();//记录字符串执行操作的结束时间
        time = endTime - startTime ;
        System.out.println("StringBuilder消耗的时间为：" + time);
        System.out.println(str);
        System.out.println(stringBuilder.toString());
        System.out.println(stringBuilder);
    }
}
