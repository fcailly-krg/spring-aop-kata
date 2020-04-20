package com.krgcorporate.kata.aop.aspect.activation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Slf4j
@Aspect
public class DisabledAspect {

    @Before("@annotation(disabled)")
    public void beforeDisabledMethod(Disabled disabled) throws DisabledException {
        throw new DisabledException();
    }
}
