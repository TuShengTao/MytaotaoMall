package com.taotao.rest.dao.impl;

import com.taotao.rest.dao.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created on 2019/11/5
 *
 * @author Tu ShengTao
 * Description: Redis 单机版实现类
 */
//不加注解 用spring配置方法

public class JedisClientSingle implements JedisClient {
    @Autowired
    private JedisPool jedisPool;

    @Override
    public String get(String key) {
        Jedis jedis=jedisPool.getResource();
        String string=jedis.get(key);
        jedis.close();
        return string;
    }

    @Override
    public String set(String key, String value) {
        Jedis jedis=jedisPool.getResource();
        String string=jedis.set(key,value);
        jedis.close();
        return string;
    }

    @Override
    public String hget(String hkey, String key) {
        Jedis jedis=jedisPool.getResource();
        String string=jedis.hget(hkey,key);
        jedis.close();
        return string;
    }

    @Override
    public long hset(String hkey, String key, String value) {
        Jedis jedis=jedisPool.getResource();
        long result=jedis.hset(hkey,key,value);
        jedis.close();
        return result;
    }

    @Override
    public long incr(String key) {
        Jedis jedis=jedisPool.getResource();
        long result=jedis.incr(key);
        return result;
    }

    @Override
    public long expire(String key, int second) {
        Jedis jedis=jedisPool.getResource();
        long result=jedis.expire(key,second);
        return result;
    }

    @Override
    public long ttl(String key) {
        Jedis jedis=jedisPool.getResource();
        long result=jedis.ttl(key);
        return result;
    }

    @Override
    public long del(String key) {
        Jedis jedis=jedisPool.getResource();
        long result=jedis.del(key);
        return result;
    }

    @Override
    public long hdel(String hkey, String key) {
        Jedis jedis=jedisPool.getResource();
        long result=jedis.hdel(hkey,key);
        return result;
    }


}
