package com.yoon.unleash;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OutsideClient_A {
    public void sync(String userId) {
        log.info("OutsideClient_A" + userId);
    }
}
