    常用命令
    
    1）连接操作命令
    quit：关闭连接（connection）
    auth：简单密码认证
    help cmd： 查看cmd帮助，例如：help quit
    
    2）持久化
    save：将数据同步保存到磁盘
    bgsave：将数据异步保存到磁盘
    lastsave：返回上次成功将数据保存到磁盘的Unix时戳
    shundown：将数据同步保存到磁盘，然后关闭服务
    
    3）远程服务控制
    info：提供服务器的信息和统计
    monitor：实时转储收到的请求
    slaveof：改变复制策略设置
    config：在运行时配置Redis服务器
    
    4）对value操作的命令
    exists(key)：确认一个key是否存在
    del(key)：删除一个key
    type(key)：返回值的类型
    keys(pattern)：返回满足给定pattern的所有key
    randomkey：随机返回key空间的一个
    keyrename(oldname, newname)：重命名key
    dbsize：返回当前数据库中key的数目
    expire：设定一个key的活动时间（s）
    ttl：获得一个key的活动时间
    select(index)：按索引查询
    move(key, dbindex)：移动当前数据库中的key到dbindex数据库
    flushdb：删除当前选择数据库中的所有key
    flushall：删除所有数据库中的所有key
    
    5）String
    set(key, value)：给数据库中名称为key的string赋予值value
    get(key)：返回数据库中名称为key的string的value
    getset(key, value)：给名称为key的string赋予上一次的value
    mget(key1, key2,…, key N)：返回库中多个string的value
    setnx(key, value)：添加string，名称为key，值为value
    setex(key, time, value)：向库中添加string，设定过期时间time
    mset(key N, value N)：批量设置多个string的值
    msetnx(key N, value N)：如果所有名称为key i的string都不存在
    incr(key)：名称为key的string增1操作
    incrby(key, integer)：名称为key的string增加integer
    decr(key)：名称为key的string减1操作
    decrby(key, integer)：名称为key的string减少integer
    append(key, value)：名称为key的string的值附加value
    substr(key, start, end)：返回名称为key的string的value的子串
    
    6）List 
    rpush(key, value)：在名称为key的list尾添加一个值为value的元素
    lpush(key, value)：在名称为key的list头添加一个值为value的 元素
    llen(key)：返回名称为key的list的长度
    lrange(key, start, end)：返回名称为key的list中start至end之间的元素
    ltrim(key, start, end)：截取名称为key的list
    lindex(key, index)：返回名称为key的list中index位置的元素
    lset(key, index, value)：给名称为key的list中index位置的元素赋值
    lrem(key, count, value)：删除count个key的list中值为value的元素
    lpop(key)：返回并删除名称为key的list中的首元素
    rpop(key)：返回并删除名称为key的list中的尾元素
    blpop(key1, key2,… key N, timeout)：lpop命令的block版本。
    brpop(key1, key2,… key N, timeout)：rpop的block版本。
    rpoplpush(srckey, dstkey)：返回并删除名称为srckey的list的尾元素，并将该元素添加到名称为dstkey的list的头部
    
    7）Set
    sadd(key, member)：向名称为key的set中添加元素member
    srem(key, member) ：删除名称为key的set中的元素member
    spop(key) ：随机返回并删除名称为key的set中一个元素
    smove(srckey, dstkey, member) ：移到集合元素
    scard(key) ：返回名称为key的set的基数
    sismember(key, member) ：member是否是名称为key的set的元素
    sinter(key1, key2,…key N) ：求交集
    sinterstore(dstkey, (keys)) ：求交集并将交集保存到dstkey的集合
    sunion(key1, (keys)) ：求并集
    sunionstore(dstkey, (keys)) ：求并集并将并集保存到dstkey的集合
    sdiff(key1, (keys)) ：求差集
    sdiffstore(dstkey, (keys)) ：求差集并将差集保存到dstkey的集合
    smembers(key) ：返回名称为key的set的所有元素
    srandmember(key) ：随机返回名称为key的set的一个元素
    
    8)ZSet
    zadd(key, score, member)：向名称为key的zset中添加元素member，score用于排序。如果该元素已经存在，则根据score更新该元素的顺序。
    zrem(key, member) ：删除名称为key的zset中的元素member
    zincrby(key, increment, member) ：如果在名称为key的zset中已经存在元素member，则该元素的score增加increment；否则向集合中添加该元素，其score的值为increment
    zrank(key, member) ：返回名称为key的zset（元素已按score从小到大排序）中member元素的rank（即index，从0开始），若没有member元素，返回“nil”
    zrevrank(key, member) ：返回名称为key的zset（元素已按score从大到小排序）中member元素的rank（即index，从0开始），若没有member元素，返回“nil”
    zrange(key, start, end)：返回名称为key的zset（元素已按score从小到大排序）中的index从start到end的所有元素
    zrevrange(key, start, end)：返回名称为key的zset（元素已按score从大到小排序）中的index从start到end的所有元素
    zrangebyscore(key, min, max)：返回名称为key的zset中score >= min且score <= max的所有元素
    zcard(key)：返回名称为key的zset的基数
    zscore(key, element)：返回名称为key的zset中元素element的score
    zremrangebyrank(key, min, max)：删除名称为key的zset中rank >= min且rank <= max的所有元素
    zremrangebyscore(key, min, max) ：删除名称为key的zset中score >= min且score <= max的所有元素
    zunionstore / zinterstore(dstkeyN, key1,…,keyN, WEIGHTS w1,…wN, AGGREGATE SUM|MIN|MAX)：对N个zset求并集和交集，并将最后的集合保存在dstkeyN中。对于集合中每一个元素的score，在进行AGGREGATE运算前，都要乘以对于的WEIGHT参数。如果没有提供WEIGHT，默认为1。默认的AGGREGATE是SUM，即结果集合中元素的score是所有集合对应元素进行 SUM运算的值，而MIN和MAX是指，结果集合中元素的score是所有集合对应元素中最小值和最大值。
    e
    9）Hash
    hset(key, field, value)：向名称为key的hash中添加元素field
    hget(key, field)：返回名称为key的hash中field对应的value
    hmget(key, (fields))：返回名称为key的hash中field i对应的value
    hmset(key, (fields))：向名称为key的hash中添加元素field 
    hincrby(key, field, integer)：将名称为key的hash中field的value增加integer
    hexists(key, field)：名称为key的hash中是否存在键为field的域
    hdel(key, field)：删除名称为key的hash中键为field的域
    hlen(key)：返回名称为key的hash中元素个数
    hkeys(key)：返回名称为key的hash中所有键
    hvals(key)：返回名称为key的hash中所有键对应的value
    hgetall(key)：返回名称为key的hash中所有的键（field）及其对应的value


    1、为什么使用redis
        性能和并发

    2、使用redis有什么缺点
        (一)缓存和数据库双写一致性问题
        (二)缓存雪崩问题
        (三)缓存击穿问题
        (四)缓存的并发竞争问题

    3、单线程的redis为什么这么快
        (一)纯内存操作
        (二)单线程操作，避免了频繁的上下文切换
        (三)采用了非阻塞I/O多路复用机制
            redis还提供了select、epoll、evport、kqueue等多路复用函数库

    4、redis的数据类型，以及每种数据类型的使用场景
        (一)String
            一般做一些复杂的计数功能的缓存
        (二)hash
            单点登录
        (三)list
            简单的消息队列, 利用lrange命令，做基于redis的分页功能，LIST可以很好的完成排队，先进先出的原则
        (四)set
            全局去重, 利用交集、并集、差集等操作，可以计算共同喜好，全部的喜好，自己独有的喜好等功能
        (五)sorted set
            排行榜, TOP N
    5、redis的过期策略以及内存淘汰机制
        redis采用的是定期删除+惰性删除策略
        为什么不用定时删除策略?
        定时删除,用一个定时器来负责监视key,过期则自动删除。虽然内存及时释放，但是十分消耗CPU资源。
        在大并发请求下，CPU要将时间应用在处理请求，而不是删除key,因此没有采用这一策略.

        定期删除+惰性删除是如何工作的呢?
            定期删除，redis默认每个100ms检查，是否有过期的key,有过期key则删除。需要说明的是，redis不是每个100ms将所有的key检查一次，
            而是随机抽取进行检查(如果每隔100ms,全部key进行检查，redis岂不是卡死)。因此，如果只采用定期删除策略，会导致很多key到时间没有删除。
            于是，惰性删除派上用场。也就是说在你获取某个key的时候，redis会检查一下，这个key如果设置了过期时间那么是否过期了？如果过期了此时就会删除。
        采用定期删除+惰性删除就没其他问题了么?
        不是的，如果定期删除没删除key。然后你也没即时去请求key，也就是说惰性删除也没生效。这样，redis的内存会越来越高。那么就应该采用内存淘汰机制。

        在redis.conf中有一行配置
        # maxmemory-policy allkeys-lru
        该配置就是配内存淘汰策略的
        1）noeviction：当内存不足以容纳新写入数据时，新写入操作会报错。应该没人用吧。
        2）allkeys-lru：当内存不足以容纳新写入数据时，在键空间中，移除最近最少使用的key。推荐使用，目前项目在用这种。
        3）allkeys-random：当内存不足以容纳新写入数据时，在键空间中，随机移除某个key。应该也没人用吧，你不删最少使用Key,去随机删。
        4）volatile-lru：当内存不足以容纳新写入数据时，在设置了过期时间的键空间中，移除最近最少使用的key。这种情况一般是把redis既当缓存，
            又做持久化存储的时候才用。不推荐
        5）volatile-random：当内存不足以容纳新写入数据时，在设置了过期时间的键空间中，随机移除某个key。依然不推荐
        6）volatile-ttl：当内存不足以容纳新写入数据时，在设置了过期时间的键空间中，有更早过期时间的key优先移除。不推荐

    6、redis和数据库双写一致性问题
        分析:一致性问题是分布式常见问题，还可以再分为最终一致性和强一致性。数据库和缓存双写，就必然会存在不一致的问题。
        答这个问题，先明白一个前提。就是如果对数据有强一致性要求，不能放缓存。我们所做的一切，只能保证最终一致性。
        另外，我们所做的方案其实从根本上来说，只能说降低不一致发生的概率，无法完全避免。因此，有强一致性要求的数据，不能放缓存。
        首先，采取正确更新策略，先更新数据库，再删缓存。其次，因为可能存在删除缓存失败的问题，提供一个补偿措施即可，例如利用消息队列。

    7、如何应对缓存穿透和缓存雪崩问题
        缓存穿透，即黑客故意去请求缓存中不存在的数据，导致所有的请求都怼到数据库上，从而数据库连接异常。
        解决方案:
        (一)利用互斥锁，缓存失效的时候，先去获得锁，得到锁了，再去请求数据库。没得到锁，则休眠一段时间重试
        (二)采用异步更新策略，无论key是否取到值，都直接返回。value值中维护一个缓存失效时间，缓存如果过期，异步起一个线程去读数据库，更新缓存。
            需要做缓存预热(项目启动前，先加载缓存)操作。
        (三)提供一个能迅速判断请求是否有效的拦截机制，比如，利用布隆过滤器，内部维护一系列合法有效的key。迅速判断出，请求所携带的Key是否合法有效。
            如果不合法，则直接返回。

        缓存雪崩，即缓存同一时间大面积的失效，这个时候又来了一波请求，结果请求都怼到数据库上，从而导致数据库连接异常。
        解决方案:
        (一)给缓存的失效时间，加上一个随机值，避免集体失效。
        (二)使用互斥锁，但是该方案吞吐量明显下降了。
        (三)双缓存。我们有两个缓存，缓存A和缓存B。缓存A的失效时间为20分钟，缓存B不设失效时间。自己做缓存预热操作。然后细分以下几个小点
        I 从缓存A读数据库，有则直接返回
        II A没有数据，直接从B读数据，直接返回，并且异步启动一个更新线程。
        III 更新线程同时更新缓存A和缓存B。

    8、如何解决redis的并发竞争问题
        (1)如果对这个key操作，不要求顺序
            这种情况下，准备一个分布式锁，大家去抢锁，抢到锁就做set操作即可，比较简单。
        (2)如果对这个key操作，要求顺序
        假设有一个key1,系统A需要将key1设置为valueA,系统B需要将key1设置为valueB,系统C需要将key1设置为valueC.
        期望按照key1的value值按照 valueA–>valueB–>valueC的顺序变化。这种时候我们在数据写入数据库的时候，需要保存一个时间戳。假设时间戳如下
        系统A key 1 {valueA  3:00}
        系统B key 1 {valueB  3:05}
        系统C key 1 {valueC  3:10}
        那么，假设这会系统B先抢到锁，将key1设置为{valueB 3:05}。接下来系统A抢到锁，发现自己的valueA的时间戳早于缓存中的时间戳，那就不做set操作了。以此类推。
        其他方法，比如利用队列，将set方法变成串行访问也可以。总之，灵活变通。




    Redis的7个应用场景

    一：缓存——热数据

    热点数据（经常会被查询，但是不经常被修改或者删除的数据），首选是使用redis缓存，毕竟强大到冒泡的QPS和极强的稳定性不是所有类似工具都有的，而且相比于memcached还提供了丰富的数据类型可以使用，另外，内存中的数据也提供了AOF和RDB等持久化机制可以选择，要冷、热的还是忽冷忽热的都可选。

    结合具体应用需要注意一下：很多人用spring的AOP来构建redis缓存的自动生产和清除，过程可能如下：

    Select 数据库前查询redis，有的话使用redis数据，放弃select 数据库，没有的话，select 数据库，然后将数据插入redis

    update或者delete数据库钱，查询redis是否存在该数据，存在的话先删除redis中数据，然后再update或者delete数据库中的数据

    上面这种操作，如果并发量很小的情况下基本没问题，但是高并发的情况请注意下面场景：

    为了update先删掉了redis中的该数据，这时候另一个线程执行查询，发现redis中没有，瞬间执行了查询SQL，并且插入到redis中一条数据，回到刚才那个update语句，这个悲催的线程压根不知道刚才那个该死的select线程犯了一个弥天大错！于是这个redis中的错误数据就永远的存在了下去，直到下一个update或者delete。

    二：计数器

    诸如统计点击数等应用。由于单线程，可以避免并发问题，保证不会出错，而且100%毫秒级性能！爽。

    命令：INCRBY

    当然爽完了，别忘记持久化，毕竟是redis只是存了内存！

    三：队列

    相当于消息系统，ActiveMQ，RocketMQ等工具类似，但是个人觉得简单用一下还行，如果对于数据一致性要求高的话还是用RocketMQ等专业系统。

    由于redis把数据添加到队列是返回添加元素在队列的第几位，所以可以做判断用户是第几个访问这种业务

    队列不仅可以把并发请求变成串行，并且还可以做队列或者栈使用

    四：位操作（大数据处理）

    用于数据量上亿的场景下，例如几亿用户系统的签到，去重登录次数统计，某用户是否在线状态等等。

    想想一下腾讯10亿用户，要几个毫秒内查询到某个用户是否在线，你能怎么做？千万别说给每个用户建立一个key，然后挨个记（你可以算一下需要的内存会很恐怖，而且这种类似的需求很多，腾讯光这个得多花多少钱。。）好吧。这里要用到位操作——使用setbit、getbit、bitcount命令。

    原理是：

    redis内构建一个足够长的数组，每个数组元素只能是0和1两个值，然后这个数组的下标index用来表示我们上面例子里面的用户id（必须是数字哈），那么很显然，这个几亿长的大数组就能通过下标和元素值（0和1）来构建一个记忆系统，上面我说的几个场景也就能够实现。用到的命令是：setbit、getbit、bitcount

    五：分布式锁与单线程机制

    验证前端的重复请求（可以自由扩展类似情况），可以通过redis进行过滤：每次请求将request Ip、参数、接口等hash作为key存储redis（幂等性请求），设置多长时间有效期，然后下次请求过来的时候先在redis中检索有没有这个key，进而验证是不是一定时间内过来的重复提交

    秒杀系统，基于redis是单线程特征，防止出现数据库“爆破”

    全局增量ID生成，类似“秒杀”

    六：最新列表

    例如新闻列表页面最新的新闻列表，如果总数量很大的情况下，尽量不要使用select a from A limit 10这种low货，尝试redis的 LPUSH命令构建List，一个个顺序都塞进去就可以啦。不过万一内存清掉了咋办？也简单，查询不到存储key的话，用mysql查询并且初始化一个List到redis中就好了。

    七：排行榜

    谁得分高谁排名往上。命令：ZADD（有续集，sorted set）