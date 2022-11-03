package com.yoon.TExpress.modernJavaInAction.seventeen;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Flow;

class TempSubscriberTest {

    @Test
    void step1() {
        getTemperatures(" NEW YORK ").subscribe(new TempSubscriber());
    }

    private static Flow.Publisher<TempInfo> getTemperatures(String town){
        return subscriber -> subscriber.onSubscribe(new TempSubscription(subscriber, town));
    }
}