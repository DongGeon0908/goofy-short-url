package com.goofy.shorturl.config.redis

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CachingConfigurerSupport
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

@EnableCaching
@Configuration
@EnableConfigurationProperties(RedisProperties::class)
class RedisConfig(
    private val properties: RedisProperties
) : CachingConfigurerSupport() {
    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        return LettuceConnectionFactory(properties.host, properties.port)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> {
        val redisTemplate: RedisTemplate<String, Any> = RedisTemplate()

        redisTemplate.setConnectionFactory(redisConnectionFactory())
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer = GenericJackson2JsonRedisSerializer()
        redisTemplate.hashKeySerializer = StringRedisSerializer()
        redisTemplate.hashValueSerializer = GenericJackson2JsonRedisSerializer()

        return redisTemplate
    }

    @Bean
    override fun cacheManager(): CacheManager? {
        val builder = RedisCacheManager.RedisCacheManagerBuilder
            .fromConnectionFactory(redisConnectionFactory())

        val defaultConfig = RedisCacheConfiguration
            .defaultCacheConfig()
            .serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(GenericJackson2JsonRedisSerializer())
            )
            .entryTtl(cachingSecond(30L))

        builder.cacheDefaults(defaultConfig)

        return builder.build()
    }

    @Bean
    fun goofyCacheManager(): CacheManager? {
        val builder = RedisCacheManager
            .RedisCacheManagerBuilder
            .fromConnectionFactory(redisConnectionFactory())

        val objectMapper = ObjectMapper()
            .activateDefaultTyping(
                LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY
            )
            .registerModule(KotlinModule())
            .registerModule(JavaTimeModule())

        val serializer = GenericJackson2JsonRedisSerializer(objectMapper)

        val fromSerializer = RedisSerializationContext.SerializationPair.fromSerializer(serializer)

        val defaultConfig = RedisCacheConfiguration
            .defaultCacheConfig()
            .serializeValuesWith(fromSerializer)
            .entryTtl(cachingDay(1L))

        builder.cacheDefaults(defaultConfig)

        return builder.build()
    }

    private fun cachingSecond(second: Long): Duration {
        return Duration.ofSeconds(second)
    }

    private fun cachingMin(min: Long): Duration {
        return Duration.ofMinutes(min)
    }

    private fun cachingHour(hour: Long): Duration {
        return Duration.ofHours(hour)
    }

    private fun cachingDay(day: Long): Duration {
        return Duration.ofDays(day)
    }
}
