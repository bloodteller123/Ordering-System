package com.zihanc.takeout.config;

import com.zihanc.takeout.model.Meal;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@Configuration
class Config {

//    @Bean
//    LettuceConnectionFactory redisConnectionFactory() {
//        return new LettuceConnectionFactory();
//    }

    @Bean
    RedisTemplate<String, List<Meal>> redisTemplate(RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<String, List<Meal>> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }
}