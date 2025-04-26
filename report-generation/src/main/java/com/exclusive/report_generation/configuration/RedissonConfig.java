package com.exclusive.report_generation.configuration;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    @Value("${spring.redis.password}")
    private String redisPassword;

    @Value("${spring.redis.ssl.enabled}")
    private String sslEnabled;

    @Value("${spring.redis.database}")
    private int redisDatabase;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();

        String redisUrl = (sslEnabled.equals("true") ? "rediss://" : "redis://") + redisHost + ":" + redisPort;

        SingleServerConfig singleServerConfig = config.useSingleServer()
                .setAddress(redisUrl)
                .setDatabase(redisDatabase)
                .setConnectionMinimumIdleSize(10)
                .setConnectionPoolSize(64)
                .setTimeout(3000);


        singleServerConfig.setPassword(redisPassword);

        return Redisson.create(config);
    }
}
