package cn.ac.yhao.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.LinkedHashSet;
import java.util.Set;

public class RedisClusterDemo {

    private JedisCluster cluster;

    public void init() {
        //池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        //最大连接数
        config.setMaxTotal(300);
        //最大闲置数
        config.setMaxIdle(150);
        config.setMaxWaitMillis(10000);
        config.setTestOnBorrow(true);
        Set<HostAndPort> nodes = new LinkedHashSet<HostAndPort>();
        nodes.add(new HostAndPort("127.0.0.1", 7000));
        nodes.add(new HostAndPort("127.0.0.1", 7001));
        nodes.add(new HostAndPort("127.0.0.1", 7002));
        nodes.add(new HostAndPort("127.0.0.1", 7003));
        nodes.add(new HostAndPort("127.0.0.1", 7004));
        nodes.add(new HostAndPort("127.0.0.1", 7005));

        cluster = new JedisCluster(nodes, 200, 2000, 2000,"admin1234", config);
    }

    public static void main(String[] args) {
        RedisClusterDemo redisClusterDemo = new RedisClusterDemo();
        redisClusterDemo.init();
        redisClusterDemo.StringOperate();
    }

    private void StringOperate() {
        System.out.println("--增--");
        System.out.println(cluster.get("t1"));
        /*cluster.set("key001", "value001");
        cluster.set("key002", "value002");
        cluster.set("key003", "value003");
        System.out.println("已新增的3个键值对");
        System.out.println(cluster.get("key001"));
        System.out.println(cluster.get("key002"));
        System.out.println(cluster.get("key003"));

        System.out.println("删除key003键值对：" + cluster.del("key003"));
        System.out.println("获取key003键值对：" + cluster.get("key003"));

        System.out.println("直接覆盖key001原来的数据：" + cluster.set("key001", "value-update"));
        System.out.println("获取key001的新值：" + cluster.get("key001"));

        System.out.println("在key002后面追加：" + cluster.append("key002", "+appending"));
        System.out.println("获取key002的值：" + cluster.get("key002"));

        *
         * mset,mget同时新增、修改、删除多个键值对

        System.out.println("一次新增key201，key202:" + cluster.mset("key201", "value201", "key202", "value202"));
        System.out.println("一次取key201,key202:" + cluster.mget("key201", "key202"));
        System.out.println("一次删除key201、key202:" + cluster.del("key201", "key202"));*/

    }
}
