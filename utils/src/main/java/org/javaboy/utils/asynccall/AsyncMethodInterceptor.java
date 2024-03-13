package org.javaboy.utils.asynccall;

import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.ProxyMethodInvocation;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.CacheInterceptor;
import org.springframework.core.serializer.support.SerializingConverter;

import java.lang.reflect.Method;

/**
 * Created by xianliang on 2019-08-07.
 *
 * @author xianliang
 * @date 2019/08/07
 * @see CacheInterceptor
 */
public class AsyncMethodInterceptor extends AsyncAspectSupport implements MethodInterceptor {

    @Autowired
    private AsyncOperationSource asyncOperationSource;

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        if (AsyncUtils.isAsyncCall()) {
            return invocation.proceed();
        }

        if(!initialized) {
            logger.warn("AsyncMethodInterceptor:initialized = false");
            return invocation.proceed();
        }

        Method method = invocation.getMethod();
        Object target = invocation.getThis();

        Class<?> targetClass = getTargetClass(target);
        AsyncOperation asyncOperation = asyncOperationSource.getAsyncOperation(method, targetClass);
        if (asyncOperation == null) {
            return invocation.proceed();
        }

        SerializingConverter converter = asyncOperation.getSerializingConverter();

        Message message = new Message(properties.getTopic(), properties.getMessageType(), converter.convert(invocation.getArguments()));
        message.putUserProperty(AsyncProperties.CLASS_NAME, targetClass.getName());
        message.putUserProperty(AsyncProperties.METHOD_NAME, method.getName());
        message.putUserProperty(AsyncProperties.METHOD_SIGNATURE, asyncOperation.getSignature());

        if(invocation instanceof ProxyMethodInvocation) {
            ProxyMethodInvocation proxyMethodInvocation = (ProxyMethodInvocation)invocation;
            ListableBeanFactory beanFactory = (ListableBeanFactory) AsyncUtils.getBeanFactory();
            String[] beanNames = beanFactory.getBeanNamesForType(proxyMethodInvocation.getProxy().getClass());
            if(beanNames.length > 0) {
                message.putUserProperty(AsyncProperties.BEAN_NAME, beanNames[0]);
            }
        }

        SendResult sendResult = metaProducer.send(message);

        logger.info("AsyncMethodInterceptor:messageId=" + sendResult.getMsgId() + ",sendResult:" + sendResult + ",signature=" + asyncOperation.getSignature());

//        Preconditions.checkState(sendResult.getSendStatus() == SendStatus.SEND_OK);

        return asyncOperation.getAsyncSupplier().get(invocation.getArguments());
    }


}
