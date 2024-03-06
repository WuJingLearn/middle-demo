package org.javaboy.middleware.impl1;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author majin.wj
 * @date 2023/5/9 3:21 PM
 * @desc
 */
public class FastDataSource extends JdbcProperties {

    private int maxActive;
    private int minIdle;
    private int initSize;

    private Object[] connections;

    private int poolCount;
    private int activeCount;


    private Lock lock = new ReentrantLock();
    private Condition noFull = lock.newCondition();

    public FastDataSource(int minIdle, int maxActive, int initSize, String username,String password,String url) {
        super(url,username,password);
        this.minIdle = minIdle;
        this.maxActive = maxActive;
        this.initSize = initSize;
        this.init();
    }

    private void init() {
        // check 参数
        if (maxActive <= 0) {
            throw new IllegalArgumentException("illegal maxActive " + maxActive);
        }
        if (maxActive < minIdle) {
            throw new IllegalArgumentException("illegal maxActive " + maxActive);
        }
        if (initSize > maxActive) {
            throw new IllegalArgumentException("illegal initialSize " + this.initSize + ", maxActive " + maxActive);
        }
        createAndStartDestroyThread();
        // init 连接池
        this.connections = new Object[maxActive];
        try {
            for (int i = 0; i < this.initSize; i++) {
                Connection connection = createConn();
                connections[poolCount++] = connection;
            }
        } catch (SQLException e) {
            throw new RuntimeException("init create conn error", e);
        }
    }


    public Connection getConnection() throws SQLException {
        lock.lock();
        try {
            for (; ; ) {
                // 连接池没有连接，并且活跃的连接 小于最大连接，此时创建连接
                if (poolCount == 0 && activeCount < maxActive) {
                    Connection conn = createConn();
                    connections[poolCount++] = conn;
                    activeCount++;
                    return conn;
                } else if (poolCount > 0) {
                    // 连接池有空闲连接,直接取出。
                    Connection conn = (Connection) connections[--poolCount];
                    connections[poolCount] = null;
                    activeCount++;
                    return conn;
                } else {
                    // 活跃的连接 == 最大的连接池，此时需要等待连接释放
                    try {
                        noFull.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } finally {
            lock.unlock();
        }
    }

    private Connection createConn() throws SQLException{
        Connection connection = DriverManager.getConnection(getUrl(), getUsername(), getPassword());
        return (Connection) Proxy.newProxyInstance(getClass().getClassLoader(),new Class[]{Connection.class},new ConnectionProxy(connection,this));
    }

    protected void recycleConnection(Connection connection) {
        lock.lock();
        try {
            activeCount--;
            connections[poolCount++] = connection;
            noFull.signal();
        } finally {
            lock.unlock();
        }
    }


    public int getActiveCount() {
        return activeCount;
    }

    public int getPoolCount() {
        return poolCount;
    }


    private void createAndStartDestroyThread() {
        new Thread(()->{



        }).start();
    }

}
