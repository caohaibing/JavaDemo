package com.chb.redis;

import redis.clients.jedis.Jedis;

public class TestRedis {
	public static void main(String[] args){
		Jedis jedis = new Jedis("localhost");
//		Jedis jedis = new Jedis("127.0.0.1", 6379);
		
		jedis.set("hw", "Hello World!");	
		String name = jedis.get("hw");
		
		jedis.set("key1", "I am value 1");  
        String ss = jedis.get("key1");
		
		System.out.println(name);
		System.out.println(ss);
	}
}
