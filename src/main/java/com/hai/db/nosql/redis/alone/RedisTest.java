package com.hai.db.nosql.redis.alone;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;

/**
 * Created by Administrator on 2018/3/5.
 */
public class RedisTest {

    private static final int CONNECTION_TIMEOUT = 10000;
    private static final int SO_TIMEOUT = 5000;
    private static final int MAX_ATTEMPTS = 5;
    private static final String PASSWORD = "123456";

    @Test
    public void cluster() {
        //初始化集合，用于装下面的多个主机和端口
        HashSet<HostAndPort> nodes = new HashSet<HostAndPort>();

        //创建多个主机和端口实例
        HostAndPort hostAndPort = new HostAndPort("192.168.153.129", 7000);
        HostAndPort hostAndPort1 = new HostAndPort("192.168.153.129", 7001);
        HostAndPort hostAndPort2 = new HostAndPort("192.168.153.129", 7002);
        HostAndPort hostAndPort3 = new HostAndPort("192.168.153.129", 7003);
        HostAndPort hostAndPort4 = new HostAndPort("192.168.153.129", 7004);
        HostAndPort hostAndPort5 = new HostAndPort("192.168.153.129", 7005);

        //添加多个主机和端口到集合中
        nodes.add(hostAndPort);
        nodes.add(hostAndPort1);
        nodes.add(hostAndPort2);
        nodes.add(hostAndPort3);
        nodes.add(hostAndPort4);
        nodes.add(hostAndPort5);

        //创建config
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(10);
        config.setTestOnBorrow(false);
        config.setTestOnReturn(true);
        config.setTestWhileIdle(true);
        config.setTimeBetweenEvictionRunsMillis(30000);
        config.setNumTestsPerEvictionRun(10);
        config.setMinEvictableIdleTimeMillis(60000);

        //通过config创建集群实例
        JedisCluster jedisCluster = new JedisCluster(nodes, CONNECTION_TIMEOUT, SO_TIMEOUT, MAX_ATTEMPTS, PASSWORD, config);

        //获取集群中的key为name键的值
        String str = jedisCluster.get("name");
        System.out.println(str);
    }
}
