package com.krgcorporate.kata.aop.aspect.activation;

public class DisabledAspect {

    public void beforeDisabledMethod(Disabled disabled) throws DisabledException {
        throw new DisabledException();
    }
}
