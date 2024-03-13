//package org.javaboy.utils.lock.rdb;
//
//import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
//import redis.clients.jedis.JedisPoolConfig;
//
///**
// * Created by xianliang on 13/3/2020.
// *
// * @author xianliang
// * @date 2020/03/13
// */
//public class RdbLockProperties {
//
//    private static final int PORT = 6379;
//    private static final int DEFAULT_TIMEOUT = 2000;
//    private static final int DEFAULT_RETRY_TIMES = 5;
//
//    private String host;
//    private String password;
//
//    private int port = PORT;
//    private int connectionTimeout = DEFAULT_TIMEOUT;
//    private int soTimeout = DEFAULT_TIMEOUT;
//    private int maxAttempts = DEFAULT_RETRY_TIMES;
//    private boolean ssl = false;
//    private String clientName = "distributedLockClient";
//
//    private GenericObjectPoolConfig pool = new JedisPoolConfig();
//
//    public String getHost() {
//        return host;
//    }
//
//    public void setHost(String host) {
//        this.host = host;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public int getPort() {
//        return port;
//    }
//
//    public void setPort(int port) {
//        this.port = port;
//    }
//
//    public int getConnectionTimeout() {
//        return connectionTimeout;
//    }
//
//    public void setConnectionTimeout(int connectionTimeout) {
//        this.connectionTimeout = connectionTimeout;
//    }
//
//    public int getSoTimeout() {
//        return soTimeout;
//    }
//
//    public void setSoTimeout(int soTimeout) {
//        this.soTimeout = soTimeout;
//    }
//
//    public int getMaxAttempts() {
//        return maxAttempts;
//    }
//
//    public void setMaxAttempts(int maxAttempts) {
//        this.maxAttempts = maxAttempts;
//    }
//
//    public boolean isSsl() {
//        return ssl;
//    }
//
//    public void setSsl(boolean ssl) {
//        this.ssl = ssl;
//    }
//
//    public String getClientName() {
//        return clientName;
//    }
//
//    public void setClientName(String clientName) {
//        this.clientName = clientName;
//    }
//
//    public GenericObjectPoolConfig getPool() {
//        return pool;
//    }
//
//    public void setPool(GenericObjectPoolConfig pool) {
//        this.pool = pool;
//    }
//}
