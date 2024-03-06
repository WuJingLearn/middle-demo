package org.javaboy.strategy.remotecall;

import java.util.ArrayList;
import java.util.List;


/**
 * 封装了一个远程调用接口，
 * 支持Dubbo,Http,Grpc方式的调用。
 * 这是典型的策略设计模式呀。
 */
public class RemoteCaller {

    List<RemoteCall> remoteCallers = new ArrayList<>();


    /**
     * 这里类似于一个策略模式，通过请求类型，找到具体的调用器；
     * @param request
     * @return
     */
    public RemoteCallResult call(RemoteCallRequest request) {
        RemoteCall remoteCall = getRemoteCall(request.getType());
        try {
            return remoteCall.call(request);

        } catch (Exception e) {
            throw new RuntimeException("Failed to remote call, callType: " + request.getType()
                    + "args: " + request.getType());
        }
    }

    public RemoteCall getRemoteCall(RemoteCallType type) {
        for (RemoteCall remoteCaller : remoteCallers) {
            if (remoteCaller.support(type)) {
                return remoteCaller;
            }
        }
        throw new RuntimeException("cant support call type " + type);
    }


}
