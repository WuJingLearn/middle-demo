//package org.javaboy.utils.lock.rdb;
//
//import com.alibaba.boot.distributed.lock.IdempotentLockAttribute;
//import com.alibaba.boot.distributed.lock.IdempotentLockSupport;
//import com.alibaba.boot.distributed.lock.NullType;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * https://javadoc.io/doc/com.aliyun.tair/alibabacloud-tairjedis-sdk/latest/com/aliyun/tairjedis/tairstring/TairStringCluster.html
// *
// * @author xianliang
// * @date 2019/08/24
// */
//public class RdbIdempotentMethodInterceptor extends IdempotentLockSupport {
//
//    private static final NullType NULL_VALUE = new NullType();
//
//    @Autowired
//    private RedisService redisService;
//
//    @Override
//    protected Object getIdempotentValue(String idempotentKey, IdempotentLockAttribute lockAttribute) {
//        return redisService.get(idempotentKey, lockAttribute.getConverter());
//    }
//
//    @Override
//    protected void setIdempotentValue(String idempotentKey, Object value, IdempotentLockAttribute lockAttribute) {
//        int lifecycle = lockAttribute.getLifecycle();
//        checkArgument(idempotentKey != null);
//        if(value == null) {
//            value = NULL_VALUE;
//        }
//        boolean result = redisService.setIfAbsent(idempotentKey, value, lockAttribute.getConverter(), lifecycle, TimeUnit.SECONDS);
//        checkResult(result);
//    }
//
//}
