package org.javaboy.rocket.asyner;

import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;

/**
 * @author chengxl
 * @date 2020/9/8
 */
public class AsyncOperation {

    private String signature;
    private SerializingConverter serializingConverter;
    private DeserializingConverter deserializingConverter;
    private AsyncSupplier asyncSupplier;
    private ResultHandler resultHandler;
    private ErrorHandler errorHandler;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public SerializingConverter getSerializingConverter() {
        return serializingConverter;
    }

    public void setSerializingConverter(SerializingConverter serializingConverter) {
        this.serializingConverter = serializingConverter;
    }

    public DeserializingConverter getDeserializingConverter() {
        return deserializingConverter;
    }

    public void setDeserializingConverter(DeserializingConverter deserializingConverter) {
        this.deserializingConverter = deserializingConverter;
    }

    public AsyncSupplier getAsyncSupplier() {
        return asyncSupplier;
    }

    public void setAsyncSupplier(AsyncSupplier asyncSupplier) {
        this.asyncSupplier = asyncSupplier;
    }

    public ResultHandler getResultHandler() {
        return resultHandler;
    }

    public void setResultHandler(ResultHandler resultHandler) {
        this.resultHandler = resultHandler;
    }

    public ErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public void setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }
}
