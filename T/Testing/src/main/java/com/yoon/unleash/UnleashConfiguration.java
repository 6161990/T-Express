package com.yoon.unleash;

import io.getunleash.DefaultUnleash;
import io.getunleash.Unleash;
import io.getunleash.util.UnleashConfig;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.yml")
@EnableConfigurationProperties(UnleashProperty.class)
public class UnleashConfiguration {

    @Bean
    public Unleash unleash(UnleashProperty unleashProperty) {
        UnleashConfig config = UnleashConfig.builder()
                .appName(unleashProperty.appName)
                .instanceId(unleashProperty.instanceId)
                .unleashAPI("http://localhost:4242/api/")
                .apiKey(unleashProperty.sdkKey)
                .synchronousFetchOnInitialisation(true)
                .build();

        return new DefaultUnleash(config);
    }

}
