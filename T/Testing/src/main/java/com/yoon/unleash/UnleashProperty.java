package com.yoon.unleash;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("app.unleash")
public class UnleashProperty {
    String appName;
    String instanceId;
    String sdkKey;

}
