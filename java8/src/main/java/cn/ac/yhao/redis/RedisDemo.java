package cn.ac.yhao.redis;

import redis.clients.jedis.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class RedisDemo {

    private Jedis jedis; //非切片客户端连接
    private JedisPool jedisPool; //非分片连接池
    private ShardedJedis shardedJedis; //切片客户端连接
    private ShardedJedisPool shardedJedisPool; //分片连接池



    public static void main(String[] args) {
        new RedisDemo().show();
    }

    public RedisDemo() {
        initPool();
        initShardedPool();
        shardedJedis = shardedJedisPool.getResource();
        jedis = jedisPool.getResource();

    }

    private void initPool() {
        //池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(5);
        config.setMaxWaitMillis(10000);
        config.setTestOnBorrow(false);

        jedisPool = new JedisPool(config, "127.0.0.1", 7000,5000,"admin1234");
    }


    private void initShardedPool() {
        //池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(5);
        config.setMaxWaitMillis(10001);
        config.setTestOnBorrow(false);

        //slave连接
        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
        JedisShardInfo info = new JedisShardInfo("127.0.0.1", 7000, "master");
        info.setPassword("admin1234");
        shards.add(info);

        //构造池
        shardedJedisPool = new ShardedJedisPool(config, shards);

    }

    public void show() {
        KeyOperate();
        //StringOperate();
        //ListOperate();
        //SetOperate();
//        sortedSetOperate();
        //HashOperate();
    }

    private void KeyOperate() {
        System.out.println("--key--");
        //清空数据
        System.out.println("清空所有数据：" + jedis.flushDB());
        //判断key是否存在
        System.out.println("判断key999是否存在：" + shardedJedis.exists("key999"));
        shardedJedis.set("key000", "value000");
        System.out.println("判断key000是否存在：" + shardedJedis.exists("key000"));

        System.out.println("新增key001：" + shardedJedis.set("key001", "value001"));

        System.out.println("设置key001的过期时间为5秒：" + shardedJedis.expire("key001", 5));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //查看某个key的剩余生存时间，单位【秒】， 永久生存或者不存在的都返回-1
        System.out.println("查看key001的剩余生存时间：" + shardedJedis.ttl("key001"));
        //移除某个key的生存时间
        System.out.println("移除key001的生存时间：" + shardedJedis.persist("key001"));

        System.out.println("查看key001的剩余生存时间：" + shardedJedis.ttl("key001"));
        //查看key所存储的值的类型
        System.out.println("查看key所存储的值的类型：" + shardedJedis.type("key001"));
        //删除key
        System.out.println("移除key001：" + shardedJedis.del("key001"));

    }


    private void StringOperate() {
        System.out.println("---String 1---");
        //清空数据
        System.out.println("清空库中所有数据：" + jedis.flushDB());

        System.out.println("--增--");
        jedis.set("key001", "value001");
        jedis.set("key002", "value002");
        jedis.set("key003", "value003");
        System.out.println("已新增的3个键值对");
        System.out.println(jedis.get("key001"));
        System.out.println(jedis.get("key002"));
        System.out.println(jedis.get("key003"));

        System.out.println("删除key003键值对：" + jedis.del("key003"));
        System.out.println("获取key003键值对：" + jedis.get("key003"));

        System.out.println("直接覆盖key001原来的数据：" + jedis.set("key001", "value-update"));
        System.out.println("获取key001的新值：" + jedis.get("key001"));

        System.out.println("在key002后面追加：" + jedis.append("key002", "+appending"));
        System.out.println("获取key002的值：" + jedis.get("key002"));

        /**
         * mset,mget同时新增、修改、删除多个键值对
         */
        System.out.println("一次新增key201，key202:" + jedis.mset("key201", "value201", "key202", "value202"));
        System.out.println("一次取key201,key202:" + jedis.mget("key201", "key202"));
        System.out.println("一次删除key201、key202:" + jedis.del("key201", "key202"));


        System.out.println("----String 2----");
        System.out.println("清空苦衷所有数据：" + jedis.flushDB());

        System.out.println("新增键值对是防止覆盖原先值");
        System.out.println("原先key301不存在时，新增key301：" + shardedJedis.setnx("key301", "value301"));
        System.out.println("原先key302不存在时，新增key302：" + shardedJedis.setnx("key302", "value302"));
        System.out.println("当key302存在时，尝试新增key302：" + shardedJedis.setnx("key302", "value302_new"));

        System.out.println("获取key301对应的值：" + shardedJedis.get("key301"));
        System.out.println("获取key302对应的值：" + shardedJedis.get("key302"));

        System.out.println("=============超过有效期键值对被删除=============");
        // 设置key的有效期，并存储数据
        System.out.println("新增key303，并指定过期时间为2秒" + shardedJedis.setex("key303", 2, "key303-2second"));
        System.out.println("获取key303对应的值：" + shardedJedis.get("key303"));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        System.out.println("3秒之后，获取key303对应的值：" + shardedJedis.get("key303"));

        System.out.println("=============获取原值，更新为新值一步完成=============");
        System.out.println("key302原值：" + shardedJedis.getSet("key302", "value302-after-getset"));
        System.out.println("key302新值：" + shardedJedis.get("key302"));

        System.out.println("=============获取子串=============");
        System.out.println("获取key302对应值中的子串：" + shardedJedis.getrange("key302", 5, 7));

    }


    private void ListOperate() {
        System.out.println("---list---");
        //清空数据
        System.out.println("清空库中所有数据：" + jedis.flushDB());

        System.out.println("---增---");
        shardedJedis.lpush("stringlists", "vector");
        shardedJedis.lpush("stringlists", "arrayList");
        shardedJedis.lpush("stringlists", "linkedlist");
        shardedJedis.lpush("stringlists", "vector");
        shardedJedis.lpush("stringlists", "vector");
        shardedJedis.lpush("stringlists", "maplist");
        shardedJedis.lpush("stringlists", "serialList");
        shardedJedis.lpush("stringlists", "hashlist");
        shardedJedis.lpush("numberlists", "2");
        shardedJedis.lpush("numberlists", "3");
        shardedJedis.lpush("numberlists", "5");
        shardedJedis.lpush("numberlists", "6");
        shardedJedis.lpush("numberlists", "7");

        System.out.println("所有元素-stringlists：" + shardedJedis.lrange("stringlists", 0, -1));
        System.out.println("所有元素-numberlists：" + shardedJedis.lrange("numberlists", 0, -1));

        System.out.println("=============删=============");
        // 删除列表指定的值 ，第二个参数为删除的个数（有重复时），后add进去的值先被删，类似于出栈
        System.out.println("成功删除指定元素个数-stringlists：" + shardedJedis.lrem("stringlists", 1, "vector"));
        System.out.println("删除指定元素之后-stringlists：" + shardedJedis.lrange("stringlists", 0, -1));

        System.out.println("=============查=============");
        // 数组长度
        System.out.println("长度-stringlists：" + shardedJedis.llen("stringlists"));
        System.out.println("长度-numberlists：" + shardedJedis.llen("numberlists"));

        // 排序
        /*
         * list中存字符串时必须指定参数为alpha，如果不使用SortingParams，而是直接使用sort("list")，
         * 会出现"ERR One or more scores can't be converted into double"
         */
        SortingParams sortingParameters = new SortingParams();
        sortingParameters.alpha();
        //sortingParameters.limit(0, 4);
        sortingParameters.desc();
        System.out.println("返回排序后的结果-stringlists：" + shardedJedis.sort("stringlists", sortingParameters));
        System.out.println("返回排序后的结果-numberlists：" + shardedJedis.sort("numberlists"));
        // 子串：  start为元素下标，end也为元素下标；-1代表倒数一个元素，-2代表倒数第二个元素
        System.out.println("子串-第二个开始到结束：" + shardedJedis.lrange("stringlists", 1, -1));
        // 获取列表指定下标的值
        System.out.println("获取下标为2的元素：" + shardedJedis.lindex("stringlists", 2));

    }

    private void SetOperate() {
        System.out.println("----set----");
        //清空数据
        System.out.println("清空库中所有数据：" + jedis.flushDB());

        System.out.println("---增---");
        System.out.println("向sets集合中加入元素element01:" + jedis.sadd("sets", "element01"));
        System.out.println("向sets集合中加入元素element01:" + jedis.sadd("sets", "element02"));
        System.out.println("向sets集合中加入元素element01:" + jedis.sadd("sets", "element03"));
        System.out.println("向sets集合中加入元素element01:" + jedis.sadd("sets", "element04"));
        System.out.println("查看sets集合中的所有元素：" + jedis.smembers("sets"));

        System.out.println("----删----");
        System.out.println("集合sets中删除元素element03" + jedis.srem("sets", "element03"));
        System.out.println("查看sets集合中的所有元素：" + jedis.smembers("sets"));

        System.out.println("-----查-----");
        System.out.println("判断element01是否在集合sets中：" + jedis.sismember("sets", "element01"));
        System.out.println("循环取sets中的每一个元素：");

        Set<String> sets = jedis.smembers("sets");
        Iterator<String> it = sets.iterator();
        while (it.hasNext()) {
            String next = it.next();
            System.out.println(next);
        }

        System.out.println("=============集合运算=============");
        System.out.println("sets1中添加元素element001：" + jedis.sadd("sets1", "element001"));
        System.out.println("sets1中添加元素element002：" + jedis.sadd("sets1", "element002"));
        System.out.println("sets1中添加元素element003：" + jedis.sadd("sets1", "element003"));
        System.out.println("sets1中添加元素element002：" + jedis.sadd("sets2", "element002"));
        System.out.println("sets1中添加元素element003：" + jedis.sadd("sets2", "element003"));
        System.out.println("sets1中添加元素element004：" + jedis.sadd("sets2", "element004"));
        System.out.println("查看sets1集合中的所有元素:" + jedis.smembers("sets1"));
        System.out.println("查看sets2集合中的所有元素:" + jedis.smembers("sets2"));
        System.out.println("sets1和sets2交集：" + jedis.sinter("sets1", "sets2"));
        System.out.println("sets1和sets2并集：" + jedis.sunion("sets1", "sets2"));
        System.out.println("sets1和sets2差集：" + jedis.sdiff("sets1", "sets2"));//差集：set1中有，set2中没有的元素

    }

    private void sortedSetOperate() {
        System.out.println("------zset------");
        //清除数据
        System.out.println("清除所有数据：" + jedis.flushDB());

        System.out.println("=============增=============");
        System.out.println("zset中添加元素element001：" + shardedJedis.zadd("zset", 7.0, "element001"));
        System.out.println("zset中添加元素element002：" + shardedJedis.zadd("zset", 8.0, "element002"));
        System.out.println("zset中添加元素element003：" + shardedJedis.zadd("zset", 2.0, "element003"));
        System.out.println("zset中添加元素element004：" + shardedJedis.zadd("zset", 3.0, "element004"));

        System.out.println("zset集合中的所有元素：" + shardedJedis.zrange("zset", 0, -1));//按照权重值升序排序

        System.out.println("=============删=============");
        System.out.println("zset中删除元素element002：" + shardedJedis.zrem("zset", "element002"));
        System.out.println("zset集合中的所有元素：" + shardedJedis.zrange("zset", 0, -1));


        System.out.println("=============查=============");
        System.out.println("统计zset集合中的元素中个数：" + shardedJedis.zcard("zset"));
        System.out.println("统计zset集合中权重某个范围内（1.0——5.0），元素的个数：" + shardedJedis.zcount("zset", 1.0, 5.0));
        System.out.println("查看zset集合中权重某个范围内（1.0——4.0），元素：" + shardedJedis.zrangeByScore("zset", 1.0, 4.0));
        System.out.println("查看zset集合中权重某个范围内（1.0——4.0），元素：" + shardedJedis.zrangeWithScores("zset", 1, 4));
        System.out.println("查看zset集合中element004的权重：" + shardedJedis.zscore("zset", "element004"));
        System.out.println("查看下标1到2范围内的元素值：" + shardedJedis.zrange("zset", 1, 2));


        Set<Tuple> zset = shardedJedis.zrangeWithScores("zset", 1, 4);
        Iterator<Tuple> it = zset.iterator();
        while (it.hasNext()) {
            Tuple next = it.next();
            System.out.println(next.getScore());
            System.out.println(next.getElement());

        }
    }

    private void HashOperate() {
        System.out.println("======================hash==========================");
        //清空数据
        System.out.println(jedis.flushDB());

        System.out.println("=============增=============");
        System.out.println("hashs中添加key001和value001键值对：" + shardedJedis.hset("hashs", "key001", "value001"));
        System.out.println("hashs中添加key002和value002键值对：" + shardedJedis.hset("hashs", "key002", "value002"));
        System.out.println("hashs中添加key003和value003键值对：" + shardedJedis.hset("hashs", "key003", "value003"));
        System.out.println("新增key004和4的整型键值对：" + shardedJedis.hincrBy("hashs", "key004", 4l));
        System.out.println("hashs中的所有值：" + shardedJedis.hvals("hashs"));
        System.out.println();

        System.out.println("=============删=============");
        System.out.println("hashs中删除key002键值对：" + shardedJedis.hdel("hashs", "key002"));
        System.out.println("hashs中的所有值：" + shardedJedis.hvals("hashs"));
        System.out.println();

        System.out.println("=============改=============");
        System.out.println("key004整型键值的值增加100：" + shardedJedis.hincrBy("hashs", "key004", 100l));
        System.out.println("hashs中的所有值：" + shardedJedis.hvals("hashs"));
        System.out.println();

        System.out.println("=============查=============");
        System.out.println("判断key003是否存在：" + shardedJedis.hexists("hashs", "key003"));
        System.out.println("获取key004对应的值：" + shardedJedis.hget("hashs", "key004"));
        System.out.println("批量获取key001和key003对应的值：" + shardedJedis.hmget("hashs", "key001", "key003"));
        System.out.println("获取hashs中所有的key：" + shardedJedis.hkeys("hashs"));
        System.out.println("获取hashs中所有的value：" + shardedJedis.hvals("hashs"));
        System.out.println();

    }

}
