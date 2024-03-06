package org.javaboy.threadlocal;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlRunnable;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author:majin.wj
 */
public class TTLTest {

    static ThreadPoolExecutor executor = ((ThreadPoolExecutor) Executors.newFixedThreadPool(1));

    public static void main(String[] args) throws InterruptedException {
        TransmittableThreadLocal<String> lT = new TransmittableThreadLocal<>();
        lT.set("zs");


        executor.submit(()->{
            System.out.println("线程池:"+lT.get());
        });
        lT.set("ls");
        Runnable ttlRunnable = TtlRunnable.get(()->{
            System.out.println("线程池:"+lT.get());
        });
        executor.submit(ttlRunnable);
        System.out.println(lT.get());

    }
}
