package com.krgcorporate.kata.aop.aspect.caching;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class CacheableAspect {

    @Pointcut("bean(*Repository)")
    public void repositoryBean() {
    }

    @AfterReturning(value = "execution(public * *.findBy*(..)) && repositoryBean()", returning = "result")
    public void cacheAfterReturning(Object result) {
        log.info("Simulating cache insertion {}", result);
    }

    @AfterReturning(value = "execution(public * *.save(..)) && repositoryBean()", returning = "result")
    public void evictAfterReturning(Object result) {
        log.info("Simulating cache eviction {}", result);
    }
}
