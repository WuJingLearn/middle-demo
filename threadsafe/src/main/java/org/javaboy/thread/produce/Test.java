package org.javaboy.thread.produce;

import java.util.LinkedList;

/**
 * @author majin.wj
 * @date 2023/6/27 3:19 PM
 * @desc
 */
public class Test {

    private LinkedList<String> list = new LinkedList<>();
    private int capacity;

    public Test(int size) {
        this.capacity = size;
    }


    public void produce(String item) {

        synchronized (list) {
            while (list.size() > this.capacity) {
                try {
                    list.wait();
                } catch (Exception e) {
                }
            }
            list.add(item);
            list.notifyAll();
        }
    }


    public String consume() {
        synchronized (list) {
            while (list.size() == 0) {
                try {
                    list.wait();
                } catch (Exception e) {
                }
            }
            String s = list.removeFirst();
            list.notifyAll();
            return s;
        }
    }

    public static void main(String[] args) {
        Test test = new Test(10);

        new Thread(() -> {
            int i =0;
            while (i++<10) {
                test.produce("zs");
            }
        }).start();

        new Thread(() -> {
            int i =0;
            while (i++<100) {
                String item = test.consume();
                System.out.println(item);
            }
        }).start();
    }

}
