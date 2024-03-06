package org.javaboy.template.result;

import lombok.Data;

//@Data
public class RpcResult <R>{

    private boolean ok;

    private String errorCode;
    private String errorMsg;

    private R result;

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public R getResult() {
        return result;
    }

    public void setResult(R result) {
        this.result = result;
    }

    public static void main(String[] args) {
        RpcResult<String> result = new RpcResult<>();

        result.setOk(true);
    }
}
