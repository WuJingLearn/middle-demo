package org.javaboy.threadpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Runnable task = ()-> System.out.println("hello");
        FutureTask<String> stringFutureTask = new FutureTask<String>(task,"zs");
        stringFutureTask.cancel(false);
        stringFutureTask.run();
        String s = stringFutureTask.get();
        System.out.println(s);

    }

}
