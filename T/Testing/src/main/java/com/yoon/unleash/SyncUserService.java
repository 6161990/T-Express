package com.yoon.unleash;

import io.getunleash.Unleash;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SyncUserService {

    private static final String SYNC_TOGGLE_KEY = "sync.user.toggle.enabled";

    private final Unleash unleash;
    private final OutsideClient_A synckerClientA;
    private final OutsideClient_B synckerClientB;

    public void sync(String userId) {
        if (unleash.isEnabled(SYNC_TOGGLE_KEY)) {
            log.info("unleash state isEnabled");
            synckerClientA.sync(userId);
        }else{
            log.info("unleash state unable");
            synckerClientB.sync(userId);
        }
    }
}
