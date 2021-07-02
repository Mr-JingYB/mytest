package com.ruoyi.wxgzh.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisUtil
 * @Author Jing Yaobin
 * @Date 2021/3/11 17:30
 * @Description redis 工具类
 **/
@Component
public class RedisUtil {

    @Autowired
    public RedisTemplate redisTemplate;

    /**
     * @MethodName set
     * @Author Jing Yaobin
     * @Param [key, value]键，值
     * @Return boolean
     * @Date 2021/3/9 15:52
     * @Description
     *      //TODO 写入缓存
     **/
    public boolean set(final String key,Object value){
        boolean result = false;
        try {
            ValueOperations<Serializable,Object> operations = redisTemplate.opsForValue();
            operations.set(key,value);
            result = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @MethodName set
     * @Author Jing Yaobin
     * @Param [key, value, exprireTime, timeUnit]键，值，时间，时间颗粒（如：TimeUnit.MILLISECONDS：毫秒）
     * @Return boolean
     * @Date 2021/3/9 15:57
     * @Description
     *      //TODO 写入缓存设置失效时间
     **/
    public boolean set(final String key, Object value, Long exprireTime, TimeUnit timeUnit){
        boolean result = false;
        try {
            ValueOperations<Serializable,Object> operations = redisTemplate.opsForValue();
            operations.set(key,value);
            redisTemplate.expire(key,exprireTime,timeUnit);
            result = true;
        } catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @MethodName remove
     * @Author Jing Yaobin
     * @Param [keys]
     * @Return void
     * @Date 2021/3/9 16:03
     * @Description
     *      //TODO 批量删除对应的 value
     **/
    public void remove(final String... keys){
        for (String key : keys){
            remove(key);
        }
    }

    /**
     * @MethodName removePatter
     * @Author Jing Yaobin
     * @Param [pattern]
     * @Return void
     * @Date 2021/3/9 16:05
     * @Description
     *      //TODO 批量删除 key
     **/
    public void removePatter(final String pattern){
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0){
            redisTemplate.delete(keys);
        }
    }

    /**
     * @MethodName remove
     * @Author Jing Yaobin
     * @Param [key]
     * @Return void
     * @Date 2021/3/9 16:07
     * @Description
     *      //TODO 删除对应的 value
     **/
    public void remove(final String key){
        if (exists(key)){
            redisTemplate.delete(key);
        }
    }

    /**
     * @MethodName exists
     * @Author Jing Yaobin
     * @Param [key]
     * @Return boolean
     * @Date 2021/3/9 16:07
     * @Description
     *      //TODO 判断缓存中是否有对应的 value
     **/
    public boolean exists(final String key){
        return redisTemplate.hasKey(key);
    }

    /**
     * @MethodName get
     * @Author Jing Yaobin
     * @Param [key]
     * @Return java.lang.Object
     * @Date 2021/3/9 16:09
     * @Description
     *      //TODO 读取缓存
     **/
    public Object get(final String key){
        Object result = null;
        ValueOperations<Serializable,Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * @MethodName hmSet
     * @Author Jing Yaobin
     * @Param [key, hashKey, value]
     * @Return void
     * @Date 2021/3/9 16:11
     * @Description
     *      //TODO 哈希 添加
     **/
    public void hmSet(String key,Object hashKey,Object value){
        HashOperations<String,Object,Object> hash = redisTemplate.opsForHash();
        hash.put(key,hashKey,value);
    }

    /**
     * @MethodName hmGet
     * @Author Jing Yaobin
     * @Param [key, hashKey]
     * @Return java.lang.Object
     * @Date 2021/3/9 16:13
     * @Description
     *      //TODO 哈希获取
     **/
    public Object hmGet(String key,Object hashKey){
        HashOperations<String,Object,Object> hash = redisTemplate.opsForHash();
        return hash.get(key,hashKey);
    }

    /**
     * @MethodName lPush
     * @Author Jing Yaobin
     * @Param [k, v]
     * @Return void
     * @Date 2021/3/9 16:14
     * @Description
     *      //TODO 添加列表
     **/
    public void lPush(String k,Object v){
        ListOperations<String,Object> list = redisTemplate.opsForList();
        list.rightPush(k,v);
    }

    /**
     * @MethodName LRange
     * @Author Jing Yaobin
     * @Param [k, l, l1]
     * @Return java.util.List<java.lang.Object>
     * @Date 2021/3/9 16:16
     * @Description
     *      //TODO 获取列表
     **/
    public List<Object> LRange(String k, long l, long l1){
        ListOperations<String,Object> list = redisTemplate.opsForList();
        return list.range(k,l,l1);
    }

    /**
     * @MethodName add
     * @Author Jing Yaobin
     * @Param [key, value]
     * @Return void
     * @Date 2021/3/9 16:18
     * @Description
     *      //TODO 集合添加
     **/
    public void add(String key,Object value){
        SetOperations<String,Object> set = redisTemplate.opsForSet();
        set.add(key,value);
    }

    /**
     * @MethodName setMembers
     * @Author Jing Yaobin
     * @Param [key]
     * @Return java.util.Set<java.lang.Object>
     * @Date 2021/3/9 16:19
     * @Description
     *      //TODO 集合获取
     **/
    public Set<Object> setMembers(String key){
        SetOperations<String,Object> set = redisTemplate.opsForSet();
        return set.members(key);
    }

    /**
     * @MethodName zAdd
     * @Author Jing Yaobin
     * @Param [key, value, scoure]
     * @Return void
     * @Date 2021/3/9 16:20
     * @Description
     *      //TODO 有序集合添加
     **/
    public void zAdd(String key,Object value,double scoure){
        ZSetOperations<String,Object> zset = redisTemplate.opsForZSet();
        zset.add(key, value, scoure);
    }

    /**
     * @MethodName rangeByScore
     * @Author Jing Yaobin
     * @Param [key, scoure, scoure1]
     * @Return java.util.Set<java.lang.Object>
     * @Date 2021/3/9 16:22
     * @Description
     *      //TODO 有序集合获取
     **/
    public Set<Object> rangeByScore(String key, double scoure, double scoure1){
        ZSetOperations<String,Object> zset = redisTemplate.opsForZSet();
        return zset.rangeByScore(key, scoure, scoure1);
    }

}
