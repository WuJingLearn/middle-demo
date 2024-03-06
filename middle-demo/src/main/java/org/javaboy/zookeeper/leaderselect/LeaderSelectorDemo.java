package org.javaboy.zookeeper.leaderselect;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;

/**
 * @author majin.wj
 * @date 2023/5/28 11:06 AM
 * @desc
 *
 *
 * LeaderLatch和LeaderSelector的原理都是差不多。
 *
 *
 *
 *
 */
public class LeaderSelectorDemo {

    public static void main(String[] args) {
        LeaderSelector leaderSelector = new LeaderSelector(null, "/leaderselector", new LeaderSelectorListener() {
            // 这个回调方法执行完成之后，就会释放锁。其他节点就会成为新的leader节点 line-> 477
            @Override
            public void takeLeadership(CuratorFramework client) throws Exception {

            }

            @Override
            public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {

            }
        });

        // 当takeLeaderShip方法完成之后，再次提交一个抢占分布式锁的任务。
        leaderSelector.autoRequeue();


        // internalRequeue 异步线程，执行分布式抢锁过程
        leaderSelector.start();;


    }

}
