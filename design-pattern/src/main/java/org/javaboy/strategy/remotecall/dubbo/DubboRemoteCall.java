package org.javaboy.strategy.remotecall.dubbo;

import org.javaboy.strategy.remotecall.RemoteCall;
import org.javaboy.strategy.remotecall.RemoteCallRequest;
import org.javaboy.strategy.remotecall.RemoteCallResult;
import org.javaboy.strategy.remotecall.RemoteCallType;

public class DubboRemoteCall implements RemoteCall {
    @Override
    public boolean support(RemoteCallType type) {
        return type.equals(RemoteCallType.DUBBO);
    }

    @Override
    public RemoteCallResult call(RemoteCallRequest request) {
        return null;
    }
}
