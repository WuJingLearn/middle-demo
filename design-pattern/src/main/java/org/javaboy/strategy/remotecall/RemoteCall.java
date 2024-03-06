package org.javaboy.strategy.remotecall;

public interface RemoteCall {

    boolean support(RemoteCallType type);


    RemoteCallResult call(RemoteCallRequest request);


}
