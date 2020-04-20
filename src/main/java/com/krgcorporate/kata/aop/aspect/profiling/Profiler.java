package com.krgcorporate.kata.aop.aspect.profiling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Component
public class Profiler {

    public <T> T profile(String name, ProfiledProcess<T> process) throws Throwable {
        StopWatch sw = new StopWatch(getClass().getSimpleName());
        try {
            sw.start(name);
            return process.proceed();
        } finally {
            sw.stop();
            log.info(sw.prettyPrint());
        }
    }

    @FunctionalInterface
    public interface ProfiledProcess<T> {
        T proceed() throws Throwable;
    }
}
