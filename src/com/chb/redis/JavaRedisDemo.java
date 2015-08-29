package com.chb.redis;

/** 
 * 在不同的线程中使用相同的Jedis实例会发生奇怪的错误。但是创建太多的实现也不好因为这意味着会建立很多sokcet连接， 
 * 也会导致奇怪的错误发生。单一Jedis实例不是线程安全的。为了避免这些问题，可以使用JedisPool, 
 * JedisPool是一个线程安全的网络连接池。可以用JedisPool创建一些可靠Jedis实例，可以从池中拿到Jedis的实例。 
 * 这种方式可以解决那些问题并且会实现高效的性能 
 */  

public class JavaRedisDemo {
	public static void main(String[] args) {  
	   
		// ...when closing your application:  
//        RedisUtil.getPool().destroy();  
  
    } 
}

 
  
// 	    public static void Hello() {  
// 	        Jedis jedis = RedisUtil.getJedis();  
// 	        try {  
// 	            // 向key-->name中放入了value-->minxr  
// 	            jedis.set("name", "minxr");  
// 	            String ss = jedis.get("name");  
//	            System.out.println(ss);  
	  
//	            // 很直观，类似map 将jintao append到已经有的value之后  
//	            jedis.append("name", "jintao");  
//	            ss = jedis.get("name");  
//	            System.out.println(ss);  
//	  
//	            // 2、直接覆盖原来的数据  
//	            jedis.set("name", "jintao");  
//	            System.out.println(jedis.get("jintao"));  
//	  
//	            // 删除key对应的记录  
//	            jedis.del("name");  
//54.	            System.out.println(jedis.get("name"));// 执行结果：null  
//55.	  
//56.	            /** 
//57.	             * mset相当于 jedis.set("name","minxr"); jedis.set("jarorwar","aaa"); 
//58.	             */  
//59.	            jedis.mset("name", "minxr", "jarorwar", "aaa");  
//60.	            System.out.println(jedis.mget("name", "jarorwar"));  
//61.	        } catch (Exception e) {  
//62.	            e.printStackTrace();  
//63.	        } finally {  
//64.	            RedisUtil.getPool().returnResource(jedis);  
//65.	        }  
//66.	  
//67.	    }  
//68.	  
//69.	    private void testKey() {  
//70.	        Jedis jedis = RedisUtil.getJedis();  
//71.	        System.out.println("=============key==========================");  
//72.	        // 清空数据  
//73.	        System.out.println(jedis.flushDB());  
//74.	        System.out.println(jedis.echo("foo"));  
//75.	        // 判断key否存在  
//76.	        System.out.println(jedis.exists("foo"));  
//77.	        jedis.set("key", "values");  
//78.	        System.out.println(jedis.exists("key"));  
//79.	    }  
//80.	  
//81.	    public static void testString() {  
//82.	        System.out.println("==String==");  
//83.	        Jedis jedis = RedisUtil.getJedis();  
//84.	        try {  
//85.	            // String  
//86.	            jedis.set("key", "Hello World!");  
//87.	            String value = jedis.get("key");  
//88.	            System.out.println(value);  
//89.	        } catch (Exception e) {  
//90.	            e.printStackTrace();  
//91.	        } finally {  
//92.	            RedisUtil.getPool().returnResource(jedis);  
//93.	        }  
//94.	  
//95.	        System.out.println("=============String==========================");  
//96.	        // 清空数据  
//97.	        System.out.println(jedis.flushDB());  
//98.	        // 存储数据  
//99.	        jedis.set("foo", "bar");  
//100.	        System.out.println(jedis.get("foo"));  
//101.	        // 若key不存在，则存储  
//102.	        jedis.setnx("foo", "foo not exits");  
//103.	        System.out.println(jedis.get("foo"));  
//104.	        // 覆盖数据  
//105.	        jedis.set("foo", "foo update");  
//106.	        System.out.println(jedis.get("foo"));  
//107.	        // 追加数据  
//108.	        jedis.append("foo", " hello, world");  
//109.	        System.out.println(jedis.get("foo"));  
//110.	        // 设置key的有效期，并存储数据  
//111.	        jedis.setex("foo", 2, "foo not exits");  
//112.	        System.out.println(jedis.get("foo"));  
//113.	        try {  
//114.	            Thread.sleep(3000);  
//115.	        } catch (InterruptedException e) {  
//116.	        }  
//117.	        System.out.println(jedis.get("foo"));  
//118.	        // 获取并更改数据  
//119.	        jedis.set("foo", "foo update");  
//120.	        System.out.println(jedis.getSet("foo", "foo modify"));  
//121.	        // 截取value的值  
//122.	        System.out.println(jedis.getrange("foo", 1, 3));  
//123.	        System.out.println(jedis.mset("mset1", "mvalue1", "mset2", "mvalue2",  
//124.	                "mset3", "mvalue3", "mset4", "mvalue4"));  
//125.	        System.out.println(jedis.mget("mset1", "mset2", "mset3", "mset4"));  
//126.	        System.out.println(jedis.del(new String[] { "foo", "foo1", "foo3" }));  
//127.	    }  
//128.	  
//129.	    public static void testList() {  
//130.	        System.out.println("==List==");  
//131.	        Jedis jedis = RedisUtil.getJedis();  
//132.	        try {  
//133.	            // 开始前，先移除所有的内容  
//134.	            jedis.del("messages");  
//135.	            jedis.rpush("messages", "Hello how are you?");  
//136.	            jedis.rpush("messages", "Fine thanks. I'm having fun with redis.");  
//137.	            jedis.rpush("messages", "I should look into this NOSQL thing ASAP");  
//138.	  
//139.	            // 再取出所有数据jedis.lrange是按范围取出，  
//140.	            // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有  
//141.	            List<String> values = jedis.lrange("messages", 0, -1);  
//142.	            System.out.println(values);  
//143.	  
//144.	        } catch (Exception e) {  
//145.	            e.printStackTrace();  
//146.	        } finally {  
//147.	            RedisUtil.getPool().returnResource(jedis);  
//148.	        }  
//149.	  
//150.	        // 清空数据  
//151.	        System.out.println(jedis.flushDB());  
//152.	        // 添加数据  
//153.	        jedis.lpush("lists", "vector");  
//154.	        jedis.lpush("lists", "ArrayList");  
//155.	        jedis.lpush("lists", "LinkedList");  
//156.	        // 数组长度  
//157.	        System.out.println(jedis.llen("lists"));  
//158.	        // 排序  
//159.	        System.out.println(jedis.sort("lists"));  
//160.	        // 字串  
//161.	        System.out.println(jedis.lrange("lists", 0, 3));  
//162.	        // 修改列表中单个值  
//163.	        jedis.lset("lists", 0, "hello list!");  
//164.	        // 获取列表指定下标的值  
//165.	        System.out.println(jedis.lindex("lists", 1));  
//166.	        // 删除列表指定下标的值  
//167.	        System.out.println(jedis.lrem("lists", 1, "vector"));  
//168.	        // 删除区间以外的数据  
//169.	        System.out.println(jedis.ltrim("lists", 0, 1));  
//170.	        // 列表出栈  
//171.	        System.out.println(jedis.lpop("lists"));  
//172.	        // 整个列表值  
//173.	        System.out.println(jedis.lrange("lists", 0, -1));  
//174.	    }  
//175.	  
//176.	    public static void testSet() {  
//177.	        System.out.println("==Set==");  
//178.	        Jedis jedis = RedisUtil.getJedis();  
//179.	        try {  
//180.	            jedis.sadd("myset", "1");  
//181.	            jedis.sadd("myset", "2");  
//182.	            jedis.sadd("myset", "3");  
//183.	            jedis.sadd("myset", "4");  
//184.	            Set<String> setValues = jedis.smembers("myset");  
//185.	            System.out.println(setValues);  
//186.	  
//187.	            // 移除noname  
//188.	            jedis.srem("myset", "4");  
//189.	            System.out.println(jedis.smembers("myset"));// 获取所有加入的value  
//190.	            System.out.println(jedis.sismember("myset", "4"));// 判断 minxr  
//191.	                                                                // 是否是sname集合的元素  
//192.	            System.out.println(jedis.scard("sname"));// 返回集合的元素个数  
//193.	        } catch (Exception e) {  
//194.	            e.printStackTrace();  
//195.	        } finally {  
//196.	            RedisUtil.getPool().returnResource(jedis);  
//197.	        }  
//198.	  
//199.	        // 清空数据  
//200.	        System.out.println(jedis.flushDB());  
//201.	        // 添加数据  
//202.	        jedis.sadd("sets", "HashSet");  
//203.	        jedis.sadd("sets", "SortedSet");  
//204.	        jedis.sadd("sets", "TreeSet");  
//205.	        // 判断value是否在列表中  
//206.	        System.out.println(jedis.sismember("sets", "TreeSet"));  
//207.	        ;  
//208.	        // 整个列表值  
//209.	        System.out.println(jedis.smembers("sets"));  
//210.	        // 删除指定元素  
//211.	        System.out.println(jedis.srem("sets", "SortedSet"));  
//212.	        // 出栈  
//213.	        System.out.println(jedis.spop("sets"));  
//214.	        System.out.println(jedis.smembers("sets"));  
//215.	        //  
//216.	        jedis.sadd("sets1", "HashSet1");  
//217.	        jedis.sadd("sets1", "SortedSet1");  
//218.	        jedis.sadd("sets1", "TreeSet");  
//219.	        jedis.sadd("sets2", "HashSet2");  
//220.	        jedis.sadd("sets2", "SortedSet1");  
//221.	        jedis.sadd("sets2", "TreeSet1");  
//222.	        // 交集  
//223.	        System.out.println(jedis.sinter("sets1", "sets2"));  
//224.	        // 并集  
//225.	        System.out.println(jedis.sunion("sets1", "sets2"));  
//226.	        // 差集  
//227.	        System.out.println(jedis.sdiff("sets1", "sets2"));  
//228.	    }  
//229.	  
//230.	    public static void sortedSet() {  
//231.	        System.out.println("==SoretedSet==");  
//232.	        Jedis jedis = RedisUtil.getJedis();  
//233.	        try {  
//234.	            jedis.zadd("hackers", 1940, "Alan Kay");  
//235.	            jedis.zadd("hackers", 1953, "Richard Stallman");  
//236.	            jedis.zadd("hackers", 1965, "Yukihiro Matsumoto");  
//237.	            jedis.zadd("hackers", 1916, "Claude Shannon");  
//238.	            jedis.zadd("hackers", 1969, "Linus Torvalds");  
//239.	            jedis.zadd("hackers", 1912, "Alan Turing");  
//240.	            Set<String> setValues = jedis.zrange("hackers", 0, -1);  
//241.	            System.out.println(setValues);  
//242.	            Set<String> setValues2 = jedis.zrevrange("hackers", 0, -1);  
//243.	            System.out.println(setValues2);  
//244.	        } catch (Exception e) {  
//245.	            e.printStackTrace();  
//246.	        } finally {  
//247.	            RedisUtil.getPool().returnResource(jedis);  
//248.	        }  
//249.	  
//250.	        // 清空数据  
//251.	        System.out.println(jedis.flushDB());  
//252.	        // 添加数据  
//253.	        jedis.zadd("zset", 10.1, "hello");  
//254.	        jedis.zadd("zset", 10.0, ":");  
//255.	        jedis.zadd("zset", 9.0, "zset");  
//256.	        jedis.zadd("zset", 11.0, "zset!");  
//257.	        // 元素个数  
//258.	        System.out.println(jedis.zcard("zset"));  
//259.	        // 元素下标  
//260.	        System.out.println(jedis.zscore("zset", "zset"));  
//261.	        // 集合子集  
//262.	        System.out.println(jedis.zrange("zset", 0, -1));  
//263.	        // 删除元素  
//264.	        System.out.println(jedis.zrem("zset", "zset!"));  
//265.	        System.out.println(jedis.zcount("zset", 9.5, 10.5));  
//266.	        // 整个集合值  
//267.	        System.out.println(jedis.zrange("zset", 0, -1));  
//268.	    }  
//269.	  
//270.	    public static void testHsh() {  
//271.	        System.out.println("==Hash==");  
//272.	        Jedis jedis = RedisUtil.getJedis();  
//273.	        try {  
//274.	            Map<String, String> pairs = new HashMap<String, String>();  
//275.	            pairs.put("name", "Akshi");  
//276.	            pairs.put("age", "2");  
//277.	            pairs.put("sex", "Female");  
//278.	            jedis.hmset("kid", pairs);  
//279.	            List<String> name = jedis.hmget("kid", "name");// 结果是个泛型的LIST  
//280.	            // jedis.hdel("kid","age"); //删除map中的某个键值  
//281.	            System.out.println(jedis.hmget("kid", "pwd")); // 因为删除了，所以返回的是null  
//282.	            System.out.println(jedis.hlen("kid")); // 返回key为user的键中存放的值的个数  
//283.	            System.out.println(jedis.exists("kid"));// 是否存在key为user的记录  
//284.	            System.out.println(jedis.hkeys("kid"));// 返回map对象中的所有key  
//285.	            System.out.println(jedis.hvals("kid"));// 返回map对象中的所有value  
//286.	  
//287.	            Iterator<String> iter = jedis.hkeys("kid").iterator();  
//288.	            while (iter.hasNext()) {  
//289.	                String key = iter.next();  
//290.	                System.out.println(key + ":" + jedis.hmget("kid", key));  
//291.	            }  
//292.	  
//293.	            List<String> values = jedis.lrange("messages", 0, -1);  
//294.	            values = jedis.hmget("kid", new String[] { "name", "age", "sex" });  
//295.	            System.out.println(values);  
//296.	            Set<String> setValues = jedis.zrange("hackers", 0, -1);  
//297.	            setValues = jedis.hkeys("kid");  
//298.	            System.out.println(setValues);  
//299.	            values = jedis.hvals("kid");  
//300.	            System.out.println(values);  
//301.	            pairs = jedis.hgetAll("kid");  
//302.	            System.out.println(pairs);  
//303.	        } catch (Exception e) {  
//304.	            e.printStackTrace();  
//305.	        } finally {  
//306.	            RedisUtil.getPool().returnResource(jedis);  
//307.	        }  
//308.	  
//309.	        // 清空数据  
//310.	        System.out.println(jedis.flushDB());  
//311.	        // 添加数据  
//312.	        jedis.hset("hashs", "entryKey", "entryValue");  
//313.	        jedis.hset("hashs", "entryKey1", "entryValue1");  
//314.	        jedis.hset("hashs", "entryKey2", "entryValue2");  
//315.	        // 判断某个值是否存在  
//316.	        System.out.println(jedis.hexists("hashs", "entryKey"));  
//317.	        // 获取指定的值  
//318.	        System.out.println(jedis.hget("hashs", "entryKey")); // 批量获取指定的值  
//319.	        System.out.println(jedis.hmget("hashs", "entryKey", "entryKey1"));  
//320.	        // 删除指定的值  
//321.	        System.out.println(jedis.hdel("hashs", "entryKey"));  
//322.	        // 为key中的域 field 的值加上增量 increment  
//323.	        System.out.println(jedis.hincrBy("hashs", "entryKey", 123l));  
//324.	        // 获取所有的keys  
//325.	        System.out.println(jedis.hkeys("hashs"));  
//326.	        // 获取所有的values  
//327.	        System.out.println(jedis.hvals("hashs"));  
//328.	    }  
//329.	  
//330.	    public static void testOther() throws InterruptedException {  
//331.	        Jedis jedis = RedisUtil.getJedis();  
//332.	  
//333.	        try {  
//334.	            // keys中传入的可以用通配符  
//335.	            System.out.println(jedis.keys("*")); // 返回当前库中所有的key [sose, sanme,  
//336.	                                                    // name, jarorwar, foo,  
//337.	                                                    // sname, java framework,  
//338.	                                                    // user, braand]  
//339.	            System.out.println(jedis.keys("*name"));// 返回的sname [sname, name]  
//340.	            System.out.println(jedis.del("sanmdde"));// 删除key为sanmdde的对象 删除成功返回1  
//341.	                                                        // 删除失败（或者不存在）返回 0  
//342.	            System.out.println(jedis.ttl("sname"));// 返回给定key的有效时间，如果是-1则表示永远有效  
//343.	            jedis.setex("timekey", 10, "min");// 通过此方法，可以指定key的存活（有效时间） 时间为秒  
//344.	            Thread.sleep(5000);// 睡眠5秒后，剩余时间将为<=5  
//345.	            System.out.println(jedis.ttl("timekey")); // 输出结果为5  
//346.	            jedis.setex("timekey", 1, "min"); // 设为1后，下面再看剩余时间就是1了  
//347.	            System.out.println(jedis.ttl("timekey")); // 输出结果为1  
//348.	            System.out.println(jedis.exists("key"));// 检查key是否存在  
//349.	            System.out.println(jedis.rename("timekey", "time"));  
//350.	            System.out.println(jedis.get("timekey"));// 因为移除，返回为null  
//351.	            System.out.println(jedis.get("time")); // 因为将timekey 重命名为time  
//352.	                                                    // 所以可以取得值 min  
//353.	            // jedis 排序  
//354.	            // 注意，此处的rpush和lpush是List的操作。是一个双向链表（但从表现来看的）  
//355.	            jedis.del("a");// 先清除数据，再加入数据进行测试  
//356.	            jedis.rpush("a", "1");  
//357.	            jedis.lpush("a", "6");  
//358.	            jedis.lpush("a", "3");  
//359.	            jedis.lpush("a", "9");  
//360.	            System.out.println(jedis.lrange("a", 0, -1));// [9, 3, 6, 1]  
//361.	            System.out.println(jedis.sort("a")); // [1, 3, 6, 9] //输入排序后结果  
//362.	            System.out.println(jedis.lrange("a", 0, -1));  
//363.	        } catch (Exception e) {  
//364.	            e.printStackTrace();  
//365.	        } finally {  
//366.	            RedisUtil.getPool().returnResource(jedis);  
//367.	        }  
//368.	  
//369.	    }  
//370.	  
//371.	    @org.junit.Test  
//372.	    public void testUnUsePipeline() {  
//373.	        long start = new Date().getTime();  
//374.	  
//375.	        Jedis jedis = RedisUtil.getJedis();  
//376.	        for (int i = 0; i < 10000; i++) {  
//377.	            jedis.set("age1" + i, i + "");  
//378.	            jedis.get("age1" + i);// 每个操作都发送请求给redis-server  
//379.	        }  
//380.	        long end = new Date().getTime();  
//381.	  
//382.	        System.out.println("unuse pipeline cost:" + (end - start) + "ms");  
//383.	  
//384.	        RedisUtil.getPool().returnResource(jedis);  
//385.	    }  
//386.	  
//387.	    @org.junit.Test  
//388.	    /** 
//389.	     * 参考:http://blog.csdn.net/freebird_lb/article/details/7778919 
//390.	     */  
//391.	    public void testUsePipeline() {  
//392.	        long start = new Date().getTime();  
//393.	  
//394.	        Jedis jedis = RedisUtil.getJedis();  
//395.	        jedis.flushDB();  
//396.	        Pipeline p = jedis.pipelined();  
//397.	        for (int i = 0; i < 10000; i++) {  
//398.	            p.set("age2" + i, i + "");  
//399.	            System.out.println(p.get("age2" + i));  
//400.	        }  
//401.	        p.sync();// 这段代码获取所有的response  
//402.	  
//403.	        long end = new Date().getTime();  
//404.	  
//405.	        System.out.println("use pipeline cost:" + (end - start) + "ms");  
//406.	  
//407.	        RedisUtil.getPool().returnResource(jedis);  
//408.	    }  
//409.	  
//410.	  
//411.	    @org.junit.Test  
//412.	    /** 
//413.	     * 时间复杂度： 
//414.	          O(N+M*log(M))， N 为要排序的列表或集合内的元素数量， M 为要返回的元素数量。 
//415.	            如果只是使用 SORT 命令的 GET 选项获取数据而没有进行排序，时间复杂度 O(N)。 
//416.	     */  
//417.	    public void testSort1() {  
//418.	        // 排序默认以数字作为对象，值被解释为双精度浮点数，然后进行比较  
//419.	        Jedis redis = RedisUtil.getJedis();  
//420.	        // 一般SORT用法 最简单的SORT使用方法是SORT key。  
//421.	        redis.lpush("mylist", "1");  
//422.	        redis.lpush("mylist", "4");  
//423.	        redis.lpush("mylist", "6");  
//424.	        redis.lpush("mylist", "3");  
//425.	        redis.lpush("mylist", "0");  
//426.	        // List<String> list = redis.sort("sort");// 默认是升序  
//427.	        SortingParams sortingParameters = new SortingParams();  
//428.	        sortingParameters.desc();  
//429.	        // sortingParameters.alpha();//当数据集中保存的是字符串值时，你可以用 ALPHA  
//430.	        // 修饰符(modifier)进行排序。  
//431.	        sortingParameters.limit(0, 2);// 可用于分页查询  
//432.	        List<String> list = redis.sort("mylist", sortingParameters);// 默认是升序  
//433.	        for (int i = 0; i < list.size(); i++) {  
//434.	            System.out.println(list.get(i));  
//435.	        }  
//436.	        redis.flushDB();  
//437.	        RedisUtil.closeJedis(redis);  
//438.	    }  
//439.	  
//440.	    @org.junit.Test  
//441.	    /** 
//442.	     * sort list 
//443.	     * LIST结合hash的排序 
//444.	     */  
//445.	    public void testSort2() {  
//446.	        Jedis jedis = RedisUtil.getJedis();  
//447.	        jedis.del("user:66", "user:55", "user:33", "user:22", "user:11",  
//448.	                "userlist");  
//449.	        jedis.lpush("userlist", "33");  
//450.	        jedis.lpush("userlist", "22");  
//451.	        jedis.lpush("userlist", "55");  
//452.	        jedis.lpush("userlist", "11");  
//453.	  
//454.	        jedis.hset("user:66", "name", "66");  
//455.	        jedis.hset("user:55", "name", "55");  
//456.	        jedis.hset("user:33", "name", "33");  
//457.	        jedis.hset("user:22", "name", "79");  
//458.	        jedis.hset("user:11", "name", "24");  
//459.	        jedis.hset("user:11", "add", "beijing");  
//460.	        jedis.hset("user:22", "add", "shanghai");  
//461.	        jedis.hset("user:33", "add", "guangzhou");  
//462.	        jedis.hset("user:55", "add", "chongqing");  
//463.	        jedis.hset("user:66", "add", "xi'an");  
//464.	  
//465.	        SortingParams sortingParameters = new SortingParams();  
//466.	        // 符号 "->" 用于分割哈希表的键名(key name)和索引域(hash field)，格式为 "key->field" 。  
//467.	        sortingParameters.get("user:*->name");  
//468.	        sortingParameters.get("user:*->add");  
//469.	//      sortingParameters.by("user:*->name");  
//470.	//      sortingParameters.get("#");  
//471.	        List<String> result = jedis.sort("userlist", sortingParameters);  
//472.	        for (String item : result) {  
//473.	            System.out.println("item...." + item);  
//474.	        }  
//475.	        /** 
//476.	         * 对应的redis客户端命令是：sort ml get user*->name sort ml get user:*->name get 
//477.	         * user:*->add 
//478.	         */  
//479.	    }  
//480.	  
//481.	    @org.junit.Test  
//482.	    /** 
//483.	     * sort set 
//484.	     * SET结合String的排序 
//485.	     */  
//486.	    public void testSort3() {  
//487.	        Jedis jedis = RedisUtil.getJedis();  
//488.	        jedis.del("tom:friend:list", "score:uid:123", "score:uid:456",  
//489.	                "score:uid:789", "score:uid:101", "uid:123", "uid:456",  
//490.	                "uid:789", "uid:101");  
//491.	  
//492.	        jedis.sadd("tom:friend:list", "123"); // tom的好友列表  
//493.	        jedis.sadd("tom:friend:list", "456");  
//494.	        jedis.sadd("tom:friend:list", "789");  
//495.	        jedis.sadd("tom:friend:list", "101");  
//496.	  
//497.	        jedis.set("score:uid:123", "1000"); // 好友对应的成绩  
//498.	        jedis.set("score:uid:456", "6000");  
//499.	        jedis.set("score:uid:789", "100");  
//500.	        jedis.set("score:uid:101", "5999");  
//501.	  
//502.	        jedis.set("uid:123", "{'uid':123,'name':'lucy'}"); // 好友的详细信息  
//503.	        jedis.set("uid:456", "{'uid':456,'name':'jack'}");  
//504.	        jedis.set("uid:789", "{'uid':789,'name':'jay'}");  
//505.	        jedis.set("uid:101", "{'uid':101,'name':'jolin'}");  
//506.	  
//507.	        SortingParams sortingParameters = new SortingParams();  
//508.	  
//509.	        sortingParameters.desc();  
//510.	        // sortingParameters.limit(0, 2);  
//511.	        // 注意GET操作是有序的，GET user_name_* GET user_password_*  
//512.	        // 和 GET user_password_* GET user_name_*返回的结果位置不同  
//513.	        sortingParameters.get("#");// GET 还有一个特殊的规则—— "GET #"  
//514.	                                    // ，用于获取被排序对象(我们这里的例子是 user_id )的当前元素。  
//515.	        sortingParameters.get("uid:*");  
//516.	        sortingParameters.get("score:uid:*");  
//517.	        sortingParameters.by("score:uid:*");  
//518.	        // 对应的redis 命令是./redis-cli sort tom:friend:list by score:uid:* get # get  
//519.	        // uid:* get score:uid:*  
//520.	        List<String> result = jedis.sort("tom:friend:list", sortingParameters);  
//521.	        for (String item : result) {  
//522.	            System.out.println("item..." + item);  
//523.	        }  
//524.	  
//525.	    }  
//526.	  
//527.	    /** 
//528.	     *  
//529.	     * 只获取对象而不排序 BY 修饰符可以将一个不存在的 key 当作权重，让 SORT 跳过排序操作。 
//530.	     * 该方法用于你希望获取外部对象而又不希望引起排序开销时使用。 # 确保fake_key不存在 redis> EXISTS fake_key 
//531.	     * (integer) 0 # 以fake_key作BY参数，不排序，只GET name 和 GET password redis> SORT 
//532.	     * user_id BY fake_key GET # GET user_name_* GET user_password_* 1) "222" # 
//533.	     * id 2) "hacker" # user_name 3) "hey,im in" # password 4) "59230" 5) "jack" 
//534.	     * 6) "jack201022" 7) "2" 8) "huangz" 9) "nobodyknows" 10) "1" 11) "admin" 
//535.	     * 12) "a_long_long_password" 
//536.	     */  
//537.	    public void testSort4() {  
//538.	  
//539.	    }  
//540.	  
//541.	    /** 
//542.	     *  
//543.	     保存排序结果 默认情况下， SORT 操作只是简单地返回排序结果，如果你希望保存排序结果，可以给 STORE 选项指定一个 key 
//544.	     * 作为参数，排序结果将以列表的形式被保存到这个 key 上。(若指定 key 已存在，则覆盖。) redis> EXISTS 
//545.	     * user_info_sorted_by_level # 确保指定key不存在 (integer) 0 redis> SORT user_id BY 
//546.	     * user_level_* GET # GET user_name_* GET user_password_* STORE 
//547.	     * user_info_sorted_by_level # 排序 (integer) 12 # 显示有12条结果被保存了 redis> LRANGE 
//548.	     * user_info_sorted_by_level 0 11 # 查看排序结果 1) "59230" 2) "jack" 3) 
//549.	     * "jack201022" 4) "2" 5) "huangz" 6) "nobodyknows" 7) "222" 8) "hacker" 9) 
//550.	     * "hey,im in" 10) "1" 11) "admin" 12) "a_long_long_password" 一个有趣的用法是将 SORT 
//551.	     * 结果保存，用 EXPIRE 为结果集设置生存时间，这样结果集就成了 SORT 操作的一个缓存。 这样就不必频繁地调用 SORT 
//552.	     * 操作了，只有当结果集过期时，才需要再调用一次 SORT 操作。 
//553.	     * 有时候为了正确实现这一用法，你可能需要加锁以避免多个客户端同时进行缓存重建(也就是多个客户端，同一时间进行 SORT 
//554.	     * 操作，并保存为结果集)，具体参见 SETNX 命令。 
//555.	     */  
//556.	    @Test  
//557.	    public void testSort5() {  
//558.	        // 排序默认以数字作为对象，值被解释为双精度浮点数，然后进行比较  
//559.	        Jedis jedis = RedisUtil.getJedis();  
//560.	        // 一般SORT用法 最简单的SORT使用方法是SORT key。  
//561.	        jedis.lpush("mylist", "1");  
//562.	        jedis.lpush("mylist", "4");  
//563.	        jedis.lpush("mylist", "6");  
//564.	        jedis.lpush("mylist", "3");  
//565.	        jedis.lpush("mylist", "0");  
//566.	        // List<String> list = redis.sort("sort");// 默认是升序  
//567.	        SortingParams sortingParameters = new SortingParams();  
//568.	        sortingParameters.desc();  
//569.	        // sortingParameters.alpha();//当数据集中保存的是字符串值时，你可以用 ALPHA  
//570.	        // 修饰符(modifier)进行排序。  
//571.	        // sortingParameters.limit(0, 2);//可用于分页查询  
//572.	  
//573.	        // 没有使用 STORE 参数，返回列表形式的排序结果. 使用 STORE 参数，返回排序结果的元素数量。  
//574.	  
//575.	        jedis.sort("mylist", sortingParameters, "mylist");// 排序后指定排序结果到一个KEY中，这里讲结果覆盖原来的KEY  
//576.	  
//577.	        List<String> list = jedis.lrange("mylist", 0, -1);  
//578.	        for (int i = 0; i < list.size(); i++) {  
//579.	            System.out.println(list.get(i));  
//580.	        }  
//581.	  
//582.	        jedis.sadd("tom:friend:list", "123"); // tom的好友列表  
//583.	        jedis.sadd("tom:friend:list", "456");  
//584.	        jedis.sadd("tom:friend:list", "789");  
//585.	        jedis.sadd("tom:friend:list", "101");  
//586.	  
//587.	        jedis.set("score:uid:123", "1000"); // 好友对应的成绩  
//588.	        jedis.set("score:uid:456", "6000");  
//589.	        jedis.set("score:uid:789", "100");  
//590.	        jedis.set("score:uid:101", "5999");  
//591.	  
//592.	        jedis.set("uid:123", "{'uid':123,'name':'lucy'}"); // 好友的详细信息  
//593.	        jedis.set("uid:456", "{'uid':456,'name':'jack'}");  
//594.	        jedis.set("uid:789", "{'uid':789,'name':'jay'}");  
//595.	        jedis.set("uid:101", "{'uid':101,'name':'jolin'}");  
//596.	  
//597.	        sortingParameters = new SortingParams();  
//598.	        // sortingParameters.desc();  
//599.	        sortingParameters.get("#");// GET 还有一个特殊的规则—— "GET #"  
//600.	                                    // ，用于获取被排序对象(我们这里的例子是 user_id )的当前元素。  
//601.	        sortingParameters.by("score:uid:*");  
//602.	        jedis.sort("tom:friend:list", sortingParameters, "tom:friend:list");  
//603.	        List<String> result = jedis.lrange("tom:friend:list", 0, -1);  
//604.	        for (String item : result) {  
//605.	            System.out.println("item..." + item);  
//606.	        }  
//607.	  
//608.	        jedis.flushDB();  
//609.	        RedisUtil.closeJedis(jedis);  
//610.	    }  
//611.	      
//612.	      
//613.	    public void testMore(){  
//614.	        //ZRANGE取出最新的10个项目。  
//615.	        //使用LPUSH + LTRIM，确保只取出最新的1000条项目。  
//616.	        //HINCRBY key field increment,为哈希表 key 中的域 field 的值加上增量 increment  
//617.	        //INCRBY,HINCRBY等等，redis有了原子递增（atomic increment），你可以放心的加上各种计数，用GETSET重置，或者是让它们过期。  
//618.	        // LREM greet 2 morning     # 移除从表头到表尾，最先发现的两个 morning,这个可以用来删除特定评论  
//619.	        // zrevrank test a 查看a在sorted set中倒排序时排在第几名，查询结果按照INDEX，所以INDEX是3表示排在第四名  
//620.	        // zrank test a 相反，表示正排序时候的名次  
//621.	        // zscore test one表示one这个元素在sorted set中的score为多少  
//622.	        // zrevrange test 0 -1 表示sorted set倒排序,zrange test 0 -1表示正排序  
//623.	        //将一个或多个 member 元素及其 score 值加入到有序集 key 当中。如果某个 member 已经是有序集的成员，那么更新这个 member 的 score 值，并通过重新插入这个 member 元素，来保证该 member 在正确的位置上。  
//624.	        //zrem test one删除sorted set中某个元素  
//625.	    }  
//626.	      
//627.	    public List<String> get_latest_comments(int start, int num_items){  
//628.	        //获取最新评论  
//629.	        //LPUSH latest.comments <ID>   
//630.	        //-我们将列表裁剪为指定长度，因此Redis只需要保存最新的5000条评论：  
//631.	        //LTRIM latest.comments 0 5000   
//632.	        //们做了限制不能超过5000个ID，因此我们的获取ID函数会一直询问Redis。只有在start/count参数超出了这个范围的时候，才需要去访问数据库。  
//633.	        Jedis jedis = RedisUtil.getJedis();  
//634.	        List<String> id_list = jedis.lrange("latest.comments",start,start+num_items-1) ;  
//635.	          
//636.	        if(id_list.size()<num_items){  
//637.	            //id_list = SQL.EXECUTE("SELECT ... ORDER BY time LIMIT ...");  
//638.	        }  
//639.	        return id_list;  
//640.	    }  
//641.	             
//642.	      
//643.	  
//644.	    @Test  
//645.	    public void testDB() {  
//646.	        Jedis jedis = RedisUtil.getJedis();  
//647.	        System.out.println(jedis.select(0));// select db-index  
//648.	                                            // 通过索引选择数据库，默认连接的数据库所有是0,默认数据库数是16个。返回1表示成功，0失败  
//649.	        System.out.println(jedis.dbSize());// dbsize 返回当前数据库的key数量  
//650.	        System.out.println(jedis.keys("*")); // 返回匹配指定模式的所有key  
//651.	        System.out.println(jedis.randomKey());  
//652.	        jedis.flushDB();// 删除当前数据库中所有key,此方法不会失败。慎用  
//653.	        jedis.flushAll();// 删除所有数据库中的所有key，此方法不会失败。更加慎用  
//654.	  
//655.	    }  
//656.	  
//657.	    @Test  
//658.	    public void testMget() {  
//659.	  
//660.	        Jedis jedis = RedisUtil.getJedis();  
//661.	        jedis.flushDB();// 删除当前数据库中所有key,此方法不会失败。慎用  
//662.	  
//663.	        jedis.rpush("ids", "aa");  
//664.	        jedis.rpush("ids", "bb");  
//665.	        jedis.rpush("ids", "cc");  
//666.	  
//667.	        List<String> ids = jedis.lrange("ids", 0, -1);  
//668.	  
//669.	        jedis.set("aa", "{'name':'zhoujie','age':20}");  
//670.	        jedis.set("bb", "{'name':'yilin','age':28}");  
//671.	        jedis.set("cc", "{'name':'lucy','age':21}");  
//672.	        List<String> list = jedis.mget(ids.toArray(new String[ids.size()]));  
//673.	        System.out.println(list);  
//674.	    }  
//675.	  
//676.	    /** 
//677.	     * 可以利用lrange对list进行分页操作 
//678.	     */  
//679.	    @Test  
//680.	    public void queryPageBy() {  
//681.	        int pageNo = 6;  
//682.	        int pageSize = 6;  
//683.	        Jedis jedis = RedisUtil.getJedis();  
//684.	        jedis.del("a");  
//685.	        for (int i = 1; i <= 30; i++) {  
//686.	            jedis.rpush("a", i + "");  
//687.	        }  
//688.	  
//689.	        int start = pageSize * (pageNo - 1);// 因为redis中list元素位置基数是0  
//690.	        int end = start + pageSize - 1;  
//691.	  
//692.	        List<String> results = jedis.lrange("a", start, end);// 从start算起，start算一个元素，到结束那个元素  
//693.	        for (String str : results) {  
//694.	            System.out.println(str);  
//695.	        }  
//696.	  
//697.	    }  
//698.	  
//699.	    @Test  
//700.	    /** 
//701.	     * [向Redis list压入ID而不是实际的数据] 
//702.	        在上面的例子里 ，我们将“对象”（此例中是简单消息）直接压入Redis list，但通常不应这么做， 
//703.	        由于对象可能被多次引用：例如在一个list中维护其时间顺序，在一个集合中保存它的类别，只要有必要，它还会出现在其他list中，等等。 
//704.	        让我们回到reddit.com的例子，将用户提交的链接（新闻）添加到list中，有更可靠的方法如下所示： 
//705.	        $ redis-cli incr next.news.id 
//706.	        (integer) 1 
//707.	        $ redis-cli set news:1:title "Redis is simple" 
//708.	        OK 
//709.	        $ redis-cli set news:1:url "http://code.google.com/p/redis" 
//710.	        OK 
//711.	        $ redis-cli lpush submitted.news 1 
//712.	        OK 
//713.	        我们自增一个key，很容易得到一个独一无二的自增ID，然后通过此ID创建对象–为对象的每个字段设置一个key。最后将新对象的ID压入submitted.news list。 
//714.	        这只是牛刀小试。在命令参考文档中可以读到所有和list有关的命令。你可以删除元素，旋转list，根据索引获取和设置元素，当然也可以用LLEN得到list的长度。 
//715.	     */  
//716.	    public void testListStrUsage() {  
//717.	        String title = "太阳能是绿色能源4";  
//718.	        String url = "http://javacreazyer.iteye.com";  
//719.	        Jedis jedis = RedisUtil.getJedis();  
//720.	  
//721.	        long adInfoId = jedis.incr("ad:adinfo:next.id");  
//722.	        jedis.set("ad:adinfo:" + adInfoId + ":title", title);  
//723.	        jedis.set("ad:adinfo:" + adInfoId + ":url", url);  
//724.	        jedis.lpush("ad:adinfo", String.valueOf(adInfoId));  
//725.	  
//726.	        String resultTitle = jedis.get("ad:adinfo:" + adInfoId + ":title");  
//727.	        String resultUrl = jedis.get("ad:adinfo:" + adInfoId + ":url");  
//728.	        List<String> ids = jedis.lrange("ad:adinfo", 0, -1);  
//729.	        System.out.println(resultTitle);  
//730.	        System.out.println(resultUrl);  
//731.	        System.out.println(ids);  
//732.	  
//733.	        /** 
//734.	         * dbsize返回的是所有key的数目，包括已经过期的， 而redis-cli keys "*"查询得到的是有效的key数目 
//735.	         */  
//736.	        System.out.println(jedis.dbSize());  
//737.	  
//738.	        jedis.flushAll();  
//739.	    }  
//740.	  
//741.	    /** 
//742.	     * 下面是一个简单的方案：对每个想加标签的对象，用一个标签ID集合与之关联，并且对每个已有的标签，一组对象ID与之关联。 例如假设我们的新闻ID 
//743.	     * 1000被加了三个标签tag 1,2,5和77，就可以设置下面两个集合： $ redis-cli sadd news:1000:tags 1 
//744.	     * (integer) 1 $ redis-cli sadd news:1000:tags 2 (integer) 1 $ redis-cli 
//745.	     * sadd news:1000:tags 5 (integer) 1 $ redis-cli sadd news:1000:tags 77 
//746.	     * (integer) 1 $ redis-cli sadd tag:1:objects 1000 (integer) 1 $ redis-cli 
//747.	     * sadd tag:2:objects 1000 (integer) 1 $ redis-cli sadd tag:5:objects 1000 
//748.	     * (integer) 1 $ redis-cli sadd tag:77:objects 1000 (integer) 1 
//749.	     * 要获取一个对象的所有标签，如此简单： $ redis-cli smembers news:1000:tags 1. 5 2. 1 3. 77 4. 
//750.	     * 2 而有些看上去并不简单的操作仍然能使用相应的Redis命令轻松实现。例如我们也许想获得一份同时拥有标签1, 2, 
//751.	     * 10和27的对象列表。这可以用SINTER命令来做，他可以在不同集合之间取出交集。因此为达目的我们只需： $ redis-cli sinter 
//752.	     * tag:1:objects tag:2:objects tag:10:objects tag:27:objects ... no result 
//753.	     * in our dataset composed of just one object ... 
//754.	     * 在命令参考文档中可以找到和集合相关的其他命令，令人感兴趣的一抓一大把。一定要留意SORT命令，Redis集合和list都是可排序的。 
//755.	     */  
//756.	    @Test  
//757.	    public void testSetUsage() {  
//758.	        Jedis jedis = RedisUtil.getJedis();  
//759.	        jedis.sadd("zhongsou:news:1000:tags", "1");  
//760.	        jedis.sadd("zhongsou:news:1000:tags", "2");  
//761.	        jedis.sadd("zhongsou:news:1000:tags", "5");  
//762.	        jedis.sadd("zhongsou:news:1000:tags", "77");  
//763.	        jedis.sadd("zhongsou:news:2000:tags", "1");  
//764.	        jedis.sadd("zhongsou:news:2000:tags", "2");  
//765.	        jedis.sadd("zhongsou:news:2000:tags", "5");  
//766.	        jedis.sadd("zhongsou:news:2000:tags", "77");  
//767.	        jedis.sadd("zhongsou:news:3000:tags", "2");  
//768.	        jedis.sadd("zhongsou:news:4000:tags", "77");  
//769.	        jedis.sadd("zhongsou:news:5000:tags", "1");  
//770.	        jedis.sadd("zhongsou:news:6000:tags", "5");  
//771.	  
//772.	        jedis.sadd("zhongsou:tag:1:objects", 1000 + "");  
//773.	        jedis.sadd("zhongsou:tag:2:objects", 1000 + "");  
//774.	        jedis.sadd("zhongsou:tag:5:objects", 1000 + "");  
//775.	        jedis.sadd("zhongsou:tag:77:objects", 1000 + "");  
//776.	  
//777.	        jedis.sadd("zhongsou:tag:1:objects", 2000 + "");  
//778.	        jedis.sadd("zhongsou:tag:2:objects", 2000 + "");  
//779.	        jedis.sadd("zhongsou:tag:5:objects", 2000 + "");  
//780.	        jedis.sadd("zhongsou:tag:77:objects", 2000 + "");  
//781.	  
//782.	        Set<String> sets = jedis.sinter("zhongsou:tag:1:objects",  
//783.	                "zhongsou:tag:2:objects", "zhongsou:tag:5:objects",  
//784.	                "zhongsou:tag:77:objects");  
//785.	        System.out.println(sets);  
//786.	        jedis.flushAll();  
//787.	    }  
//788.	  
//789.	    @Test  
//790.	    public void testSortedSetUsage() {  
//791.	        Jedis jedis = RedisUtil.getJedis();  
//792.	        jedis.zadd("zhongsou:hackers", 1940, "Alan Kay");  
//793.	        jedis.zadd("zhongsou:hackers", 1953, "Richard Stallman");  
//794.	        jedis.zadd("zhongsou:hackers", 1943, "Jay");  
//795.	        jedis.zadd("zhongsou:hackers", 1920, "Jellon");  
//796.	        jedis.zadd("zhongsou:hackers", 1965, "Yukihiro Matsumoto");  
//797.	        jedis.zadd("zhongsou:hackers", 1916, "Claude Shannon");  
//798.	        jedis.zadd("zhongsou:hackers", 1969, "Linus Torvalds");  
//799.	        jedis.zadd("zhongsou:hackers", 1912, "Alan Turing");  
//800.	  
//801.	        Set<String> hackers = jedis.zrange("zhongsou:hackers", 0, -1);  
//802.	        System.out.println(hackers);  
//803.	  
//804.	        Set<String> hackers2 = jedis.zrevrange("zhongsou:hackers", 0, -1);  
//805.	        System.out.println(hackers2);  
//806.	  
//807.	        // 区间操作,我们请求Redis返回score介于负无穷到1920年之间的元素（两个极值也包含了）。  
//808.	        Set<String> hackers3 = jedis.zrangeByScore("zhongsou:hackers", "-inf",  
//809.	                "1920");  
//810.	        System.out.println(hackers3);  
//811.	  
//812.	        // ZREMRANGEBYSCORE 这个名字虽然不算好，但他却非常有用，还会返回已删除的元素数量。  
//813.	        long num = jedis.zremrangeByScore("zhongsou:hackers", "-inf", "1920");  
//814.	        System.out.println(num);  
//815.	  
//816.	        jedis.flushAll();  
//817.	    }  
//818.	  
//819.	}  
// 
// 
//   
//Java代码   
//1.	/** 
//2.	 * 获取连接池. 
//3.	 * @return 连接池实例 
//4.	 */  
//5.	private static JedisPool getPool(String ip,int port) {  
//6.	    JedisPoolConfig config = new JedisPoolConfig();  
//7.	    config.setMaxActive(RedisConfig.getMaxactive());  
//8.	    config.setMaxIdle(RedisConfig.getMaxidle());  
//9.	    config.setMaxWait(RedisConfig.getMaxwait());  
//10.	    config.setTestOnBorrow(true);  
//11.	    config.setTestOnReturn(true);  
//12.	    try{    
//13.	        /** 
//14.	         *如果你遇到 java.net.SocketTimeoutException: Read timed out exception的异常信息 
//15.	         *请尝试在构造JedisPool的时候设置自己的超时值. JedisPool默认的超时时间是2秒(单位毫秒) 
//16.	         */  
//17.	        pool = new JedisPool(config, ip, port,RedisConfig.getTimeout());  
//18.	    } catch(Exception e) {  
//19.	        e.printStackTrace();  
//20.	    }  
//21.	    return pool;  
//22.	}  



// 
//附加一个工作中常用到的Jedis工具类，如下：
 
//1.	package com.zhongsou.vertportal.util;  
//2.	  
//3.	import java.util.HashMap;  
//4.	import java.util.Map;  
//5.	  
//6.	import org.slf4j.Logger;  
//7.	import org.slf4j.LoggerFactory;  
//8.	  
//9.	import redis.clients.jedis.Jedis;  
//10.	import redis.clients.jedis.JedisPool;  
//11.	import redis.clients.jedis.JedisPoolConfig;  
//12.	  
//13.	import com.zhongsou.vertportal.conf.BaseConfig;  
//14.	import com.zhongsou.vertportal.conf.RedisConfig;  
//15.	  
//16.	/** 
//17.	 * Redis工具类,用于获取RedisPool. 
//18.	 * 参考官网说明如下： 
//19.	 * You shouldn't use the same instance from different threads because you'll have strange errors. 
//20.	 * And sometimes creating lots of Jedis instances is not good enough because it means lots of sockets and connections, 
//21.	 * which leads to strange errors as well. A single Jedis instance is not threadsafe! 
//22.	 * To avoid these problems, you should use JedisPool, which is a threadsafe pool of network connections. 
//23.	 * This way you can overcome those strange errors and achieve great performance. 
//24.	 * To use it, init a pool: 
//25.	 *  JedisPool pool = new JedisPool(new JedisPoolConfig(), "localhost"); 
//26.	 *  You can store the pool somewhere statically, it is thread-safe. 
//27.	 *  JedisPoolConfig includes a number of helpful Redis-specific connection pooling defaults. 
//28.	 *  For example, Jedis with JedisPoolConfig will close a connection after 300 seconds if it has not been returned. 
//29.	 * @author wujintao 
//30.	 */ 
//31.	public class JedisUtil  {  
//32.	    protected Logger log = LoggerFactory.getLogger(getClass());  
//33.	      
//34.	    /** 
//35.	     * 私有构造器. 
//36.	     */  
//37.	    private JedisUtil() {  
//38.	          
//39.	    }  
//40.	    private static Map<String,JedisPool> maps  = new HashMap<String,JedisPool>();  
//41.	      
//42.	      
//43.	    /** 
//44.	     * 获取连接池. 
//45.	     * @return 连接池实例 
//46.	     */  
//47.	    private static JedisPool getPool(String ip,int port) {  
//48.	        String key = ip+":" +port;  
//49.	        JedisPool pool = null;  
//50.	        if(!maps.containsKey(key)) {  
//51.	            JedisPoolConfig config = new JedisPoolConfig();  
//52.	            config.setMaxActive(RedisConfig.getMaxactive());  
//53.	            config.setMaxIdle(RedisConfig.getMaxidle());  
//54.	            config.setMaxWait(RedisConfig.getMaxwait());  
//55.	            config.setTestOnBorrow(true);  
//56.	            config.setTestOnReturn(true);  
//57.	            try{    
//58.	                /** 
//59.	                 *如果你遇到 java.net.SocketTimeoutException: Read timed out exception的异常信息 
//60.	                 *请尝试在构造JedisPool的时候设置自己的超时值. JedisPool默认的超时时间是2秒(单位毫秒) 
//61.	                 */  
//62.	                pool = new JedisPool(config, ip, port,RedisConfig.getTimeout());  
//63.	                maps.put(key, pool);  
//64.	            } catch(Exception e) {  
//65.	                e.printStackTrace();  
//66.	            }  
//67.	        }else{  
//68.	            pool = maps.get(key);  
//69.	        }  
//70.	        return pool;  
//71.	    }  
//72.	  
//73.	    /** 
//74.	     *类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例 
//75.	     *没有绑定关系，而且只有被调用到时才会装载，从而实现了延迟加载。 
//76.	     */  
//77.	    private static class RedisUtilHolder{  
//78.	        /** 
//79.	         * 静态初始化器，由JVM来保证线程安全 
//80.	         */  
//81.	        private static JedisUtil instance = new JedisUtil();  
//82.	    }  
//83.	  
//84.	    /** 
//85.	     *当getInstance方法第一次被调用的时候，它第一次读取 
//86.	     *RedisUtilHolder.instance，导致RedisUtilHolder类得到初始化；而这个类在装载并被初始化的时候，会初始化它的静 
//87.	     *态域，从而创建RedisUtil的实例，由于是静态的域，因此只会在虚拟机装载类的时候初始化一次，并由虚拟机来保证它的线程安全性。 
//88.	     *这个模式的优势在于，getInstance方法并没有被同步，并且只是执行一个域的访问，因此延迟初始化并没有增加任何访问成本。 
//89.	     */  
//90.	    public static JedisUtil getInstance() {  
//91.	        return RedisUtilHolder.instance;  
//92.	    }  
//93.	      
//94.	    /** 
//95.	     * 获取Redis实例. 
//96.	     * @return Redis工具类实例 
//97.	     */  
//98.	    public Jedis getJedis(String ip,int port) {  
//99.	        Jedis jedis  = null;  
//100.	        int count =0;  
//101.	        do{  
//102.	            try{   
//103.	                jedis = getPool(ip,port).getResource();  
//104.	                //log.info("get redis master1!");  
//105.	            } catch (Exception e) {  
//106.	                log.error("get redis master1 failed!", e);  
//107.	                 // 销毁对象    
//108.	                getPool(ip,port).returnBrokenResource(jedis);    
//109.	            }  
//110.	            count++;  
//111.	        }while(jedis==null&&count<BaseConfig.getRetryNum());  
//112.	        return jedis;  
//113.	    }  
//114.	  
//115.	    /** 
//116.	     * 释放redis实例到连接池. 
//117.	     * @param jedis redis实例 
//118.	     */  
//119.	    public void closeJedis(Jedis jedis,String ip,int port) {  
//120.	        if(jedis != null) {  
//121.	            getPool(ip,port).returnResource(jedis);  
//122.	        }  
//123.	    }  
//124.	}  

