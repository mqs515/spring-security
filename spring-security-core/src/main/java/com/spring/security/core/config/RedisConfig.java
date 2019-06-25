package com.spring.security.core.config;

import com.spring.security.core.utils.JedisUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * @author ：miaoqs
 * @date ：2019-06-25 14:04
 * @description：Redis的配置中心
 */

@Configuration
public class RedisConfig {
    /**********增加redis的配置 为了实现 JedisUtil 可以将此段代码进行整改  start**********/
    @Value("${spring.redis.pool.min-idle}")
    Integer maxIdle;
    @Value("${spring.redis.pool.max-active}")
    Integer maxTotal;
    @Value("${spring.redis.host}")
    String host;
    @Value("${spring.redis.port}")
    Integer port;
    @Value("${spring.redis.timeout}")
    Integer timeout;
    @Value("${spring.redis.password}")
    String password;
    @Value("${spring.redis.prefix}")
    String prefix;

    @Bean
    JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setTestOnBorrow(true);
        return jedisPoolConfig;
    }

    @Bean
    JedisPool jedisPool() {
        return new JedisPool(jedisPoolConfig(), host, port, timeout, password);
    }

    @Bean
    JedisUtil jedisUtil() {
        return new JedisUtil(jedisPool(), prefix);
    }

    /**********增加redis的配置 为了实现 JedisUtil 可以将此段代码进行整改  end**********/

//    @Bean
//    public StringRedisTemplate sessionRedisTemplate() {
//        StringRedisTemplate template = new StringRedisTemplate();
//
//        template.setConnectionFactory(sessionJedisConnectionFactory());
//
//        return template;
//    }
//
//    @Bean(name = {"cacheRedisTemplate", "redisTemplate"})
//    @Primary
//    public RedisTemplate cacheRedisTemplate() {
//        RedisTemplate template = new RedisTemplate<>();
//
//        template.setConnectionFactory(cacheJedisConnectionFactory());
//
//        return template;
//    }


//    @Bean
//    @ConfigurationProperties(prefix = "spring.redis")
//    RedisProperties sessionRedisProperties() {
//        return new RedisProperties();
//    }
//
//    @Bean
//    @ConfigurationProperties(prefix = "redis.youfan")
//    RedisProperties cacheRedisProperties() {
//        return new RedisProperties();
//    }
//
//    @Bean
//    JedisConnectionFactory sessionJedisConnectionFactory() {
//        return createJedisConnectionFactory(sessionRedisProperties(), "youfan-session");
//    }
//
//    @Bean
//    JedisConnectionFactory cacheJedisConnectionFactory() {
//        return createJedisConnectionFactory(cacheRedisProperties(), "youfan-cache");
//    }


    private JedisConnectionFactory createJedisConnectionFactory(RedisProperties props, String clientName) {
        JedisClientConfiguration clientConfiguration = getJedisClientConfiguration(props, clientName);
        return new JedisConnectionFactory(getStandaloneConfig(props), clientConfiguration);
    }

    private JedisClientConfiguration getJedisClientConfiguration(RedisProperties props, String clientName) {

        JedisClientConfiguration.JedisClientConfigurationBuilder builder =
                applyProperties(props, JedisClientConfiguration.builder());
        builder.clientName(clientName);
        RedisProperties.Pool pool = props.getJedis().getPool();
        if (pool != null) {
            applyPooling(pool, builder);
        }
        return builder.build();
    }

    private JedisClientConfiguration.JedisClientConfigurationBuilder applyProperties(
            RedisProperties props,
            JedisClientConfiguration.JedisClientConfigurationBuilder builder) {
        if (props.isSsl()) {
            builder.useSsl();
        }
        if (props.getTimeout() != null) {
            Duration timeout = props.getTimeout();
            builder.readTimeout(timeout).connectTimeout(timeout);
        }
        return builder;
    }

    private void applyPooling(RedisProperties.Pool pool,
                              JedisClientConfiguration.JedisClientConfigurationBuilder builder) {
        builder.usePooling().poolConfig(jedisPoolConfig(pool));
    }

    private JedisPoolConfig jedisPoolConfig(RedisProperties.Pool pool) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(pool.getMaxActive());
        config.setMaxIdle(pool.getMaxIdle());
        config.setMinIdle(pool.getMinIdle());
        if (pool.getMaxWait() != null) {
            config.setMaxWaitMillis(pool.getMaxWait().toMillis());
        }
        return config;
    }


    protected final RedisStandaloneConfiguration getStandaloneConfig(RedisProperties props) {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();

        config.setHostName(props.getHost());
        config.setPort(props.getPort());
        config.setPassword(RedisPassword.of(props.getPassword()));
        config.setDatabase(props.getDatabase());
        return config;
    }
}
