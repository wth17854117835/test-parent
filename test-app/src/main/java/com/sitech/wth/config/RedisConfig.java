package com.sitech.wth.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: wangth_oup
 * @date: 2021-07-05 14:55
 * @description:
 **/
@Configuration
@EnableCaching //启用缓存
public class RedisConfig {

    /**
     *  注入Redis配置（自定义RedisTemplate，替换默认的）
     * @param redisConnectionFactory redis
     * @return RedisTemplate
     */
    @Bean
    public RedisTemplate<String, Object> initRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // 创建RedisTemplate<String, Object>对象
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        //如果不配置Serializer，那么存储的时候缺省使用String，如果用User类型存储，那么会提示错误User can't cast to String！
        // redis key 序列化方式使用stringSerial
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // redis hash key 序列化方式使用stringSerial
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        // redis value 序列化方式使用jackson
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // redis hash value 序列化方式使用jackson
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 开启事务
        redisTemplate.setEnableTransactionSupport(true);
        // 配置连接工厂
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    @Bean
    public CacheManager cacheManager(RedisTemplate<String, Object> template) {
        RedisCacheConfiguration defaultCacheConfiguration =
                RedisCacheConfiguration
                        .defaultCacheConfig()
                        // 设置key为String
                        .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(template.getStringSerializer()))
                        // 设置value 为自动转Json的Object
                        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(template.getValueSerializer()))
                        // 不缓存null
                        .disableCachingNullValues()
                        // 缓存数据保存1小时
//                        .entryTtl(Duration.ofHours(1));
                        .entryTtl(Duration.ofMinutes(2));
        RedisCacheManager redisCacheManager =
                RedisCacheManager.RedisCacheManagerBuilder
                        // Redis 连接工厂
                        .fromConnectionFactory(template.getConnectionFactory())
                        // 缓存配置
                        .cacheDefaults(defaultCacheConfiguration)
                        // 配置同步修改或删除 put/evict
                        .transactionAware()
                        .build();
        return redisCacheManager;
    }

}
