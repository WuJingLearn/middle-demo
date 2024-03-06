package org.javaboy.arraylist;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author majin.wj
 * @date 2023/6/26 10:04 PM
 * @desc
 */
public class ListTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("zs");


        Vector<String> vector = new Vector<>();
        vector.add("zs");
        /**
         * 为什么get方法也加了synchronized锁的原因：
         * 如果一个线程正在读取Vector的数据，而另一个线程在此时修改了Vector的大小，就有可能出现数组越界异常。
         */
        vector.get(1);

        /**
         * 这里get方法没有加锁，因为写数据的时候，会先将原来的数组拷贝一份，然后在新的数组上操作。
         * 这样读和(写的过程操作)就是两个不同的数组，所以不会有线程安全问题。
         * 当写完数据之后，将最新的数组赋值给里面的array引用。这个array引用是加volatile修饰的。
         * 后面的读请求就可以读取最新的数据。
         */
        CopyOnWriteArrayList<String> cpList = new CopyOnWriteArrayList<>();
        cpList.add("zs");
        cpList.get(1);

        cpList.iterator();
    }
}
