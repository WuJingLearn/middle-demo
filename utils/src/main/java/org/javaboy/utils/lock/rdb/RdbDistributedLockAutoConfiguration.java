//package org.javaboy.utils.lock.rdb;
//
//import com.alibaba.boot.distributed.lock.*;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//
//import java.util.Objects;
//
///**
// * 开启tair的自动配置
// *
// * @author xianliang
// * @date 2017/03/11
// */
//@Import({RedisServiceConfiguration.class, RdbIdempotentLockAutoConfiguration.class})
//@Configuration
//public class RdbDistributedLockAutoConfiguration {
//
//    @Value("${distributed.lock.order:-2147483648}")
//    private int order;
//
//    @Bean
//    public LockAttributeSource lockAttributeSource() {
//        return new AnnotationLockAttributeSource();
//    }
//
//    @Bean
//    public LockAspectSupport lockInterceptor() {
//        LockAspectSupport lockInterceptor = new LockAspectSupport();
//        return lockInterceptor;
//    }
//
//    @Bean
//    public LockService lockService(RedisService redisService) {
//        return new RdbLockService(redisService);
//    }
//
//    @Bean
//    public DistributedLockMethodPointcutAdvisor distributedLockMethodPointcutAdvisor(@Qualifier("lockInterceptor") LockAspectSupport lockInterceptor) {
//        Objects.requireNonNull(lockInterceptor);
//        DistributedLockMethodPointcutAdvisor advisor = new DistributedLockMethodPointcutAdvisor(lockInterceptor);
//        advisor.setOrder(order);
//        return advisor;
//    }
//}
