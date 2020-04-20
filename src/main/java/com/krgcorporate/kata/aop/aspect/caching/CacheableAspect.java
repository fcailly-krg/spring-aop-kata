package com.krgcorporate.kata.aop.aspect.caching;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheableAspect {

    public void cacheAfterReturning(Object result) {
        log.info("Simulating cache insertion {}", result);
    }

    public void evictAfterReturning(Object result) {
        log.info("Simulating cache eviction {}", result);
    }
}
