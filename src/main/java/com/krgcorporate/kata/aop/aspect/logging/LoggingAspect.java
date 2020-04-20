package com.krgcorporate.kata.aop.aspect.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;

@Slf4j
public class LoggingAspect {

    public void logAfter(JoinPoint jp) {
        log.info("After calling {}", jp.getSignature());
    }

    public void logAfterThrowing(Throwable throwable) {
        log.error("Unhandled exception", throwable);
    }
}
