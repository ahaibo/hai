package com.hai.db.nosql.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.Session;
import org.junit.Before;

import java.net.InetSocketAddress;

/**
 * Created by Administrator on 2018/5/7.
 */
public class BaseCassandraTest {

    protected static final String HOST_NAME = "localhost";
    protected static final int HOST_PORT = 9042;

    protected Cluster cluster;
    protected Session session;
    protected boolean initWithPool = false;

    @Before
    public void init() {
        if (initWithPool) {
            connect();
        } else {
            connectWithPool();
        }
    }

    public void connect() {
        connect(null);
    }

    public void connect(String keyspace) {
        cluster = Cluster.builder()
                .addContactPointsWithPorts(new InetSocketAddress(HOST_NAME, HOST_PORT))
                //鉴权
                //.withCredentials("cassandra", "cassandra")
                .build();
        if (null == keyspace || "".equals(keyspace.trim())) {
            session = cluster.connect();
        } else {
            session = cluster.connect(keyspace);
        }
        System.out.println("cluster: " + cluster);
        System.out.println("session: " + session);
    }

    public void connectWithPool() {
        connectWithPool(null);
    }

    public void connectWithPool(String keyspace) {
        PoolingOptions options = new PoolingOptions();
        //每个连接的最大请求数
        options.setMaxRequestsPerConnection(HostDistance.LOCAL, 32);

        // 表示和集群里的机器至少有2个连接 最多有4个连接
        options.setCoreConnectionsPerHost(HostDistance.LOCAL, 2)
                .setMaxConnectionsPerHost(HostDistance.LOCAL, 4)
                .setCoreConnectionsPerHost(HostDistance.REMOTE, 2)
                .setMaxConnectionsPerHost(HostDistance.REMOTE, 4);

        cluster = Cluster.builder()
                .addContactPointsWithPorts(new InetSocketAddress(HOST_NAME, HOST_PORT))
                .withCredentials("cassandra", "cassandra")
                .build();

        if (null == keyspace || "".equals(keyspace.trim())) {
            session = cluster.connect();
        } else {
            session = cluster.connect(keyspace);
        }
    }

}
