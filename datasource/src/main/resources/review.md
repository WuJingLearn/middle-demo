

## 创建连接
在创建连接时，根据是否设置了过滤器
如果没有设置filter,那么创建的就是普通过Connection对象
如果设置了filter,那么创建的就是ConnectionProxy对象。ConnectionProxy对象。
创建的PreparedStatement对象也是proxy对象。会对sql的执行进行监控,记录等


## 获取链接

获取连接是一个同步操作。当连接池没有连接时 poolCount = 0；
先调用 empty.signal(); 通知生产者，需要创建了。 此时生产者线程会从同步队列，移动到阻塞队列。
然后调用notEmpty.await消费者等待； 理解一下这里的empty 和notEmpty的命名。生产者线程需要有空位才会创建线程。
所以在empty上等待，等待有空位。消费者需要获取连接，只有非空才可以获取，所以在notEmpty上进行等待



## 对于连接的封装
DruidPooledConnection 是最外层的一层壳子。

DruidConnectionHolder 



## druid提供比较有用的东西
1.LogFilter : 日志的记录
2.StatFilter: 执行状态，慢sql。
duird中提供的监控：
1.创建连接的次数，创建连接的最大时长，StatFilter的connection_connect方法统计
2.连接关闭的次数 。连接的存活时长，StatFilter的connection_close方法统计


## 
poolCount 空闲连接数
activeCount 活跃连接数
poolCount + activeCount: 当前连接池创建的连接数

waitThreadNums: 等待获取连接的线程
当waitThreadNums数超过了maxWaitThreadCount,直接获取连接失败
maxWait: 等待连接超时时间。一般设置为1s即可
在druid连接池中，可以监控到获取连接的时间。如果获取连接的时候，比较长，此时我们是否考虑,适当增加连接数；
默认设置1s就超时了。

activeCount和MaxActive数量相同时 考
1..SQL比较慢，导致单SQL占用连接时间过长，一般建议优化SQL
2.并发量比较大，连接确实不够用，这种情况可以找DBA


druid数据源监控,还可以监控到获取连接的超时数量，获取连接时长，记录sql耗时,打印慢sql。
1.将慢sql,执行时长打印到一个专门的日志文件中
2.由日志系统接入如sls，将慢sql解析。




连接池信息:
队列任务数