package com.krgcorporate.kata.aop.aspect.profiling;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;

@Slf4j
@AllArgsConstructor
public class ProfiledAspect {

    private final Profiler profiler;

    public Object aroundProfiledMethods(ProceedingJoinPoint pjp) throws Throwable {
        return profiler.profile(pjp.getSignature().getName(), pjp::proceed);
    }
}
