package org.javaboy.elasticjob;

import org.apache.shardingsphere.elasticjob.api.JobConfiguration;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.ScheduleJobBootstrap;
import org.apache.shardingsphere.elasticjob.reg.base.CoordinatorRegistryCenter;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperConfiguration;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;

/**
 * @author majin.wj
 * @date 2023/6/25 9:43 AM
 * @desc
 */
public class MyJob implements SimpleJob {
    @Override
    public void execute(ShardingContext context) {
        int shard = context.getShardingItem();
        System.out.println(shard);
        System.out.println("execute shard " + shard + "task thread: " + Thread.currentThread().getName());
        int i = 1/0;
    }


    public static void main(String[] args) {
        // 任务配置
        JobConfiguration config = JobConfiguration.newBuilder("myJob", 2).overwrite(true)
                .failover(true)
                .monitorExecution(true) // 分片项正在运行的状态
                .misfire(true) // 错过任务重新执行
                .cron("0/10 * * * * ?").build();

        ScheduleJobBootstrap bootstrap = new ScheduleJobBootstrap(createRegistryCenter(), new MyJob(), config);
        // 启动
        bootstrap.schedule();

    }

    private static CoordinatorRegistryCenter createRegistryCenter() {
        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(new ZookeeperConfiguration("localhost:2181", "my-job"));
        regCenter.init();
        return regCenter;
    }
}
