package org.javaboy.utils.asynccall;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

/**
 * Created by xianliang on 2019-07-30.
 *
 * @author xianliang
 * @date 2019/07/30
 */
public class AsyncMessageListener implements MessageListenerConcurrently, BeanFactoryAware, BeanClassLoaderAware {

    private static final Logger logger = LoggerFactory.getLogger(AsyncMessageListener.class);

    private BeanFactory beanFactory;
    private ClassLoader classLoader;

    @Autowired
    private AsyncOperationSource asyncOperationSource;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {

        MessageExt messageExt = msgs.get(0);

        try {
            AsyncallContext ctx = new AsyncallContext();
            AsyncUtils.setContext(ctx);

            logger.info("AsyncMessageListener:messageId=" + messageExt.getMsgId());

            //恢复现场
            Class<?> targetClass = getTargetClass(messageExt);
            Method targetMethod = getTargetMethod(messageExt, targetClass);
            AsyncOperation asyncOperation = asyncOperationSource.getAsyncOperation(targetMethod, targetClass);

            Object target = getTargetBean(targetClass, messageExt.getUserProperty(AsyncProperties.BEAN_NAME));
            Object[] args = (Object[]) asyncOperation.getDeserializingConverter().convert(messageExt.getBody());

            ctx.setTargetClass(target.getClass());
            ctx.setTargetMethod(getTargetMethod(messageExt, target.getClass()));
            ctx.setTarget(target);
            ctx.setArgs(args);
            ctx.setAsyncOperation(asyncOperation);
            ctx.setRetryCount(messageExt.getReconsumeTimes());
            ctx.invoke();

        } catch (Throwable ex) {
            Throwable e = ex;
            if(ex instanceof InvocationTargetException) {
                e = Optional.ofNullable(((InvocationTargetException)ex).getTargetException()).orElse(ex);
            }
            logger.error("AsyncMessageListener error, message=" + e.getMessage(), e);
            AsyncallContext ctx = AsyncUtils.getContext();
            ctx.getErrorHandler().process(e, ctx);
        } finally {
            AsyncUtils.remove();
        }

        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    private Object getTargetBean(Class<?> targetClass, String beanName) {
        try {
            return beanFactory.getBean(targetClass);
        } catch (NoSuchBeanDefinitionException e) {
            //尝试通过beanName查找
            return beanName != null ? beanFactory.getBean(beanName) : beanFactory.getBean(StringUtils.uncapitalize(targetClass.getSimpleName()));
        }
    }

    public Class<?> getTargetClass(MessageExt messageExt) throws ClassNotFoundException {
        return classLoader.loadClass(messageExt.getUserProperty(AsyncProperties.CLASS_NAME));
    }

    public Method getTargetMethod(MessageExt messageExt, Class<?> targetClass) {
        Method[] candidates = targetClass.getMethods();//MethodUtils.getMethodsWithAnnotation(targetClass, Asynchronized.class);
        if(candidates == null || candidates.length < 1) {
            return null;
        }

        String signature = messageExt.getUserProperty(AsyncProperties.METHOD_SIGNATURE);

        for(Method candidate : candidates) {
            if(signature.equals(AsyncUtils.getSignature(candidate))) {
                return candidate;
            }
        }

        return null;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }
}