package com.lifeix.d20131130;

import com.lifeix.TestUtil;
import com.lifeix.Util;
import com.lifeix.d20131023.IntDecoder;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lifeix
 * Date: 11/30/13
 * Time: 11:58 AM
 * To change this template use File | Settings | File Templates.
 */
public class TopicTest {
    public void test() {
        TestUtil.searchAndTest("com.lifeix.d20131130", TrainTest.class, "testImplements", this);
    }

    /**
     * 测试实现
     *
     * @param invoker
     */
    public void testImplements(TrainTest invoker) {

        List<String> list = new ArrayList<String>();
        list.add("5 2");
        list.add("1 2 3 4 5");
        list.add("5 5");
        list.add("3 1 2 4 5");

        System.out.println("classname===================:" + invoker.getClass().getName());
        for (int i=0;i<list.size();i+=2) {
            String nm= list.get(i);
            String trains = list.get(i+1);
            long t1 = System.currentTimeMillis();

            boolean result = invoker.check(nm, trains);

            long t2 = System.currentTimeMillis();
            System.out.println("nm = " + nm + " trains=" + trains);
            System.out.println("result = " + result + " time:" + (t2 - t1));
        }
    }

    public static void main(String[] args) {
        TopicTest t = new TopicTest();
        t.test();
    }
}
