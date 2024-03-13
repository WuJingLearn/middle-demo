//package org.javaboy.utils.lock.rdb;
//
//import com.alibaba.boot.distributed.lock.IdempotentMethondPointcutAdvisor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * 开启tair的自动配置
// *
// * @author xianliang
// * @date 2017/03/11
// */
//@Configuration
//public class RdbIdempotentLockAutoConfiguration {
//
//    @Bean
//    public RdbIdempotentMethodInterceptor idempotentMethodInterceptor() {
//        return new RdbIdempotentMethodInterceptor();
//    }
//
//    @Bean
//    public IdempotentMethondPointcutAdvisor idempotentMethondPointcutAdvisor(RdbIdempotentMethodInterceptor interceptor) {
//        return new IdempotentMethondPointcutAdvisor(interceptor);
//    }
//}
