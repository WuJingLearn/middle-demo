package org.javaboy.threadlocal.context;

/**
 * @author:majin.wj
 */
public class Client {

    /**
     *
     */
    public void call() {

        RpcContextUtil.setAttribute("name","zs");

        Server server = new Server();
        //调用服务端方法
        server.invoke();


    }

    public static void main(String[] args) {
        Client client = new Client();
        client.call();
    }

}
