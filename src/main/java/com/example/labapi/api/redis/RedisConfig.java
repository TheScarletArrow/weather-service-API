package com.example.labapi.api.redis;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisClusterNode;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfig {


    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
        RedisClusterNode m_a = new RedisClusterNode("m_a", 6379);
        RedisClusterNode m_b = new RedisClusterNode("m_b", 6380);
        RedisClusterNode m_c = new RedisClusterNode("m_c", 6381);
        RedisClusterNode r_a = new RedisClusterNode("r_a", 6382);
        RedisClusterNode r_b = new RedisClusterNode("r_b", 6383);
        RedisClusterNode r_c = new RedisClusterNode("r_c", 6384);

        redisClusterConfiguration.addClusterNode(m_a);
        redisClusterConfiguration.addClusterNode(m_b);
        redisClusterConfiguration.addClusterNode(m_c);
        redisClusterConfiguration.addClusterNode(r_a);
        redisClusterConfiguration.addClusterNode(r_b);
        redisClusterConfiguration.addClusterNode(r_c);

        return new JedisConnectionFactory(redisClusterConfiguration);
    }


    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
        redisTemplate.setEnableTransactionSupport(true);

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}
