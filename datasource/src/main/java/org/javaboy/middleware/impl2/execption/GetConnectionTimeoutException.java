package org.javaboy.middleware.impl2.execption;

import java.sql.SQLException;

public class GetConnectionTimeoutException extends SQLException {


    public GetConnectionTimeoutException(String reason) {
        super(reason);
    }

    public GetConnectionTimeoutException(String reason, Throwable throwable) {
        super(reason, throwable);
    }

}
