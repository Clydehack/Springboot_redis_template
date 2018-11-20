package com.template.ie.redis.configuration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
public class RedisConfig {

	/************************** 使用方法 - 第一种 **************************/
	/* 通过一个连接池的配置创建了RedisConnectionFactory,很早的方法了 */
	private RedisConnectionFactory connectionFactory = null;
	
	@Bean(name = "RedisConnectionFactory")
	public RedisConnectionFactory initRedisConnectionFactory() {
		if(this.connectionFactory != null) {
			return this.connectionFactory;
		}
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(30);			// 最大空闲数
		poolConfig.setMaxTotal(50); 		// 最大连接数
		poolConfig.setMaxWaitMillis(2000); 	// 最大等待毫秒数
		// 创建Jedis连接工厂
		JedisConnectionFactory connectionFactory = new JedisConnectionFactory(poolConfig);
		RedisStandaloneConfiguration rsCfg = connectionFactory.getStandaloneConfiguration();
		connectionFactory.setHostName("192.168.11.131");
		connectionFactory.setPort(6379);
		connectionFactory.setPassword("123456");
		this.connectionFactory = connectionFactory;
		return connectionFactory;
	}
	/************************** 使用方法 - 第一种 **************************/
	
	
	/************************** 使用方法 - 第二种 **************************/
	/* redisTemplate,应该是类似于mongodbTemplate一样的,使用最多的类 */
	@Bean(name = "redisTemplate")
	public RedisTemplate<Object, Object> initRedisTemplate(){
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
		//redisTemplate.setConnectionFactory(initConnectionFactory());
		return redisTemplate;
	}
	/************************** 使用方法 - 第二种 **************************/
	
	
	/************************** 使用方法 - 第三种 **************************/
	/* 缓存注解 */
	/************************** 使用方法 - 第三种 **************************/
}