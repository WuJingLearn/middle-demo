package org.javaboy.threadlocal.context;

/**
 * @author:majin.wj
 */
public class Server {


    public void invoke() {

        System.out.println("执行服务端方法");
        Object name = RpcContextUtil.getAttribute("name");
        System.out.println("服务端收到：" + name);

    }

}
