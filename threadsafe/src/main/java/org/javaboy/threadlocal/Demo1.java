package org.javaboy.threadlocal;

public class Demo1 {
    public static void main(String[] args) {

        ThreadLocal<String> local = new ThreadLocal(){
            @Override
            protected String initialValue() {
                return "hello";
            }
        };


        /**
         *
         * 我们可能会在业务代码中执行了 ThreadLocal instance = null 操作，想清理掉这个 ThreadLocal 实例，
         * 但是假设我们在 ThreadLocalMap 的 Entry 中强引用了 ThreadLocal 实例，那么，虽然在业务代码中把 ThreadLocal
         * 实例置为了 null，但是在 Thread 类中依然有这个引用链的存在。
         *GC 在垃圾回收的时候会进行可达性分析，它会发现这个 ThreadLocal 对象依然是可达的，
         * 所以对于这个 ThreadLocal 对象不会进行垃圾回收，这样的话就造成了内存泄漏的情况。
         * JDK 开发者考虑到了这一点，所以 ThreadLocalMap 中的 Entry 继承了 WeakReference 弱引用
         *
         */
        local = null;


        local.remove();

    }
}
