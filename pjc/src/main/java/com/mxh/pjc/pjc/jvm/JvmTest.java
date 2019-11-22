package com.mxh.pjc.pjc.jvm;

import com.carrotsearch.sizeof.RamUsageEstimator;
import com.mxh.pjc.pjc.domain.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class JvmTest {

    private  static  List<byte[]> staticList = new ArrayList<>();
    public void testHeap(){//堆溢出
        List<byte[]> list = new ArrayList<>();
        int i=0;
        while (true){
            list.add(new byte[1*1024*1024]);
            System.out.println("count is: "+(++i));
        }
    }
    int num = 1;
    public void testStack(){ //栈溢出
        num++;
        System.out.println(num);
        this.testStack();
    }

    public void testGCRoot(){
        int i=0;
        while (true){
            staticList.add(new byte[1*1024*1024]);
            System.out.println("count is: "+(++i));
        }
    }

    //测试java对象的占用大小
    public void testObjectSize(){
        User user = new User();
        user.setUserName("马小红");

        System.out.println(RamUsageEstimator.sizeOf(user));
    }
    public static void main(String[] agrs){
        JvmTest t = new JvmTest();
//        t.testHeap();
        t.testStack();
//        t.testObjectSize();

    }
}
