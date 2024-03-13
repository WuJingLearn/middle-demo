//package org.javaboy.utils.lock.rdb;
//
//import com.alibaba.boot.distributed.lock.BeanTypeNotPresentCondition;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Conditional;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import redis.clients.jedis.HostAndPort;
//import redis.clients.jedis.JedisCluster;
//
//import java.util.Objects;
//
///**
// * Created by xianliang on 2/4/2020.
// *
// * @author xianliang
// * @date 2020/04/02
// */
//@Configuration
//public class RedisServiceConfiguration {
//
//    @Autowired
//    private Environment environment;
//    @Autowired
//    private ConfigurableApplicationContext applicationContext;
//
//    @Value("${spring.rdb.clusterBeanName:distributedLockJedisCluster}")
//    private String clusterBeanName;
//
//    private JedisCluster jedisCluster;
//
//    /**
//     * 锁的最长时间，默认2小时 2 * 60 * 60 = 3600 * 2 = 7200
//     */
//    @Value("${distributed.lock.max-timeout:7200}")
//    private long lockMaxTimeout;
//
//    @Conditional(BeanTypeNotPresentCondition.class)
//    @Bean(destroyMethod = "close")
//    public JedisCluster distributedLockJedisCluster() {
//
//        RdbLockProperties rdbLockProperties = applicationContext.getBean(RdbLockProperties.class);
//
//        Objects.requireNonNull(rdbLockProperties, "RdbLockProperties bean not found");
//        Objects.requireNonNull(rdbLockProperties.getHost());
//        Objects.requireNonNull(rdbLockProperties.getPassword());
//
//        HostAndPort hostAndPort = new HostAndPort(rdbLockProperties.getHost(), rdbLockProperties.getPort());
//
//        JedisCluster jedisCluster = new JedisCluster(hostAndPort, rdbLockProperties.getConnectionTimeout(), rdbLockProperties.getSoTimeout(),
//            rdbLockProperties.getMaxAttempts(), rdbLockProperties.getPassword(), rdbLockProperties.getPool());
//
//        return jedisCluster;
//    }
//
//    @Conditional(BeanTypeNotPresentCondition.class)
//    @Bean
//    public RedisService distributedLockRedisService() {
//
//        RdbLockProperties rdbLockProperties = applicationContext.getBean(RdbLockProperties.class);
//        if(rdbLockProperties.getHost() != null) {
//            JedisCluster jedisCluster = getJedisCluster();
//            if(jedisCluster != null) {
//                return new DefaultRedisService(jedisCluster);
//            }
//        }
//        //@ConditionalOnBean(name = {"redisTemplate", "stringRedisTemplate"})
//        if(applicationContext.containsBean("redisTemplate")) {
//            return new SpringRedisService();
//        }
//
//        throw new RuntimeException("RedisService not found");
//    }
//
//    private JedisCluster getJedisCluster() {
//        if(jedisCluster == null) {
//            jedisCluster = applicationContext.getBean(clusterBeanName, JedisCluster.class);
//        }
//        return jedisCluster;
//    }
//
//    public void setJedisCluster(JedisCluster jedisCluster) {
//        this.jedisCluster = jedisCluster;
//    }
//}
