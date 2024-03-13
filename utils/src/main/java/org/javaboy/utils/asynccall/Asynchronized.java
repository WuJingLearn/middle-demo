package org.javaboy.utils.asynccall;

import org.springframework.core.serializer.DefaultDeserializer;
import org.springframework.core.serializer.DefaultSerializer;
import org.springframework.core.serializer.Deserializer;
import org.springframework.core.serializer.Serializer;

import java.lang.annotation.*;

/**
 * Created by xianliang on 2019-10-09.
 *
 * @author 
 * @date 2019/10/09
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Asynchronized {

    String EMPTY = "";

    /**
     * 默认返回值处理
     * @return
     */
    Class<? extends AsyncSupplier> defaultResult() default DefaultSupplier.class;

    /**
     * 序列化
     * @return
     */
    Class<? extends Serializer> serializer() default DefaultSerializer.class;

    /**
     * 反序列化
     * @return
     */
    Class<? extends Deserializer> deserializer() default DefaultDeserializer.class;

    /**
     * 结果回调处理
     * @return
     */
    Class<? extends ResultHandler> callback() default NoOPResultHandler.class;

    /**
     * 异常处理
     * @return
     */
    Class<? extends ErrorHandler> errorHandler() default DefaultErrorHandler.class;

}
