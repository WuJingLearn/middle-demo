package org.javaboy.threadlocal;

import org.omg.PortableServer.ThreadPolicyOperations;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author:majin.wj
 */
public class InThreadLocaltest {

    static ThreadPoolExecutor executor = ((ThreadPoolExecutor) Executors.newFixedThreadPool(1));

    public static void main(String[] args) throws InterruptedException {
        InheritableThreadLocal<String> lT = new InheritableThreadLocal<>();
        lT.set("zs");

        new Thread(()->{
            System.out.println(lT.get());
        }).start();


        executor.submit(()->{
            System.out.println("线程池:"+lT.get());
        });
        Thread.sleep(1000);
        lT.set("ls");
        executor.submit(()->{
            System.out.println("线程池:"+lT.get());
        });
        System.out.println(lT.get());
    }


}
