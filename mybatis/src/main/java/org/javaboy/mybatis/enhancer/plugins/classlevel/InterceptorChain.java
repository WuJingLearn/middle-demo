package org.javaboy.mybatis.enhancer.plugins.classlevel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author majin.wj
 * @date 2023/6/9 2:38 PM
 * @desc
 */
public class InterceptorChain {

    private final List<Interceptor> chain;

    public InterceptorChain(){
        this.chain = new ArrayList<>();
    }

    public Object plugin(Object target){
        for (Interceptor interceptor : chain) {
            // 如果可以增强，则返回一个代理对象，否则返回本身
            // 将上个interceptor生成的代理，做为目标对象，再次代理。
            target = interceptor.plugin(target);
        }
        return target;
    }

    public void addInterceptor(Interceptor interceptor){
        chain.add(interceptor);
    }


}
