package com.krgcorporate.kata.aop.aspect.profiling;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
@AllArgsConstructor
public class ProfiledAspect {

    private final Profiler profiler;

    @Pointcut("@annotation(Profiled)")
    public void profiledMethods() {

    }

    @Around("profiledMethods()")
    public Object aroundProfiledMethods(ProceedingJoinPoint pjp) throws Throwable {
        return profiler.profile(pjp.getSignature().getName(), pjp::proceed);
    }
}
