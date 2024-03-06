package org.javaboy.template.result;

import java.util.function.Function;

public class ResultTemplate {


    /**
     * protected <T extends InvokeContextDto,S> S invoke(Function<T, ResultSupport<S>> func, T param){
     * param.setAccessKey(accessToken);
     * param.setUniqueKey(UUID.randomUUID().toString());
     * <p>
     * ResultSupport<S> resultSupport;
     * try {
     * log.warn("AbstractDataService invoke param {}", JsonUtil.logJson(param));
     * resultSupport= func.apply(param);
     * }catch (Exception e){
     * log.error("{} call exception {}",MASTERDATA_W_CONTRACT,e.getMessage());
     * throw new ContractThirdException(String.format(MASTERDATA_ERROR_PREFIX,"call exception"),e);
     * }
     * <p>
     * if(Objects.isNull(resultSupport)){
     * log.error("{} call exception",MASTERDATA_W_CONTRACT);
     * throw new ContractThirdException(String.format(MASTERDATA_ERROR_PREFIX, "resultSupport null"));
     * }
     * if(!resultSupport.isSuccess()){
     * log.error("{} call exception {}",MASTERDATA_W_CONTRACT,resultSupport.getMessage());
     * throw new ContractThirdException(String.format(MASTERDATA_ERROR_PREFIX, resultSupport.getMessage()));
     * }
     * return resultSupport.getResult();
     * }
     *
     * @param function
     * @param param
     * @param <T>
     */
    public static <T, R> R invoke(Function<T, RpcResult<R>> function, T param) {
        RpcResult<R> result;
        try {
            result = function.apply(param);
        } catch (Exception e) {
            throw new RuntimeException("rpc call exception");
        }

        if (result == null) {
            throw new RuntimeException("result is null");
        }

        if (!result.isOk()) {
            throw new RuntimeException(String.format("errorCode:%s,errorMsg:%s", result.getErrorCode(), result.getErrorMsg()));
        }
        return result.getResult();
    }


}
