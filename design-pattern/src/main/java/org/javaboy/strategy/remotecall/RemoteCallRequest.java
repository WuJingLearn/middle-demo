package org.javaboy.strategy.remotecall;

import lombok.Data;

@Data
public class RemoteCallRequest {

    private RemoteCallType type;


    private Object[] args;


}
