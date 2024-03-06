package org.javaboy.template.result.test;

import org.javaboy.template.result.ResultTemplate;
import org.javaboy.template.result.RpcResult;

public class HelloService {


    public RpcResult<String> get(String msg) {
        RpcResult<String> result = new RpcResult<>();
        result.setOk(true);
        result.setResult("hello");
        throw new RuntimeException("执行失败");
//        return result;
    }


   public RpcResult<Integer> test(Integer age) {
        RpcResult<Integer> result = new RpcResult<>();
        result.setOk(false);
        result.setErrorCode("404");
        result.setErrorMsg("找不到");
        return result;
    }

    public static void main(String[] args) {
        HelloService helloService = new HelloService();

//        String zs = ResultTemplate.invoke(helloService::get, "zs");
//        System.out.println(zs);


        Integer invoke = ResultTemplate.invoke(helloService::test, 11);
        System.out.println(invoke);



    }

}
