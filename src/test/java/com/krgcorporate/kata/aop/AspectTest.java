package com.krgcorporate.kata.aop;

import com.krgcorporate.kata.aop.aspect.activation.DisabledException;
import com.krgcorporate.kata.aop.aspect.activation.DisabledAspect;
import com.krgcorporate.kata.aop.aspect.caching.CacheableAspect;
import com.krgcorporate.kata.aop.aspect.logging.LoggingAspect;
import com.krgcorporate.kata.aop.aspect.profiling.ProfiledAspect;
import com.krgcorporate.kata.aop.contract.Contract;
import com.krgcorporate.kata.aop.contract.ContractRepository;
import com.krgcorporate.kata.aop.contract.ContractService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class AspectTest {

    @SpyBean
    private DisabledAspect enabledAspect;

    @SpyBean
    private LoggingAspect loggingAspect;

    @SpyBean
    private ProfiledAspect profilingAspect;

    @SpyBean
    private CacheableAspect cacheableAspect;

    @Autowired
    private ContractService contractService;

    @Autowired
    private ContractRepository contractRepository;

    /**
     * Configure the com.krgcorporate.kata.aop.aspect.activation.DisabledAspect
     *  in order to catch any methods that is annotated by @Disabled before the execution.
     * @throws DisabledException a disabled exception.
     */
    @Test(expected = DisabledException.class)
    public void disabledMethodThrowsDisabledException() throws DisabledException {
        contractService.disabledMethod();

        verify(enabledAspect, times(1)).beforeDisabledMethod(any());
        verify(contractService, never()).disabledMethod();
    }

    /**
     * Configure the com.krgcorporate.kata.aop.aspect.profiling.ProfiledAspect
     *  in order to catch any methods that is annotated by @Profiled around the execution.
     * @throws Throwable any exception that occurred in method execution.
     */
    @Test
    public void createShouldBeProfiled() throws Throwable {
        final String contractRef = "A00001";
        contractService.create(contractRef);

        verify(profilingAspect, times(1)).aroundProfiledMethods(any());
    }

    /**
     * Configure the com.krgcorporate.kata.aop.aspect.logging.LoggingAspect
     *  in order to log a message after any methods that is in classes suffixed with 'Service'.
     */
    @Test
    public void serviceMethodShouldBeLogged() {
        final String contractRef = "A00001";
        contractService.create(contractRef);
        contractService.get(contractRef);
        contractService.loggable();

        verify(loggingAspect, times(3)).logAfter(any());
    }

    /**
     * Configure the com.krgcorporate.kata.aop.aspect.logging.LoggingAspect
     *  in order to log a message after any methods that result in throwing an exception in classes suffixed with 'Service'.
     * @throws IllegalStateException the expected exception.
     */
    @Test(expected = IllegalStateException.class)
    public void erroneousMethodShouldBeLogged() throws IllegalStateException {
        try {
            contractService.erroneousMethod();
        } finally {
            verify(loggingAspect, times(1)).logAfterThrowing(any());
        }
    }

    /**
     * Configure the com.krgcorporate.kata.aop.aspect.caching.CacheableAspect
     *  in order to evict the result of find method in classes suffixed by 'Repository'.
     */
    @Test
    public void repositorySaveMethodShouldEvictTheCache() {
        contractRepository.save(new Contract("ANY_REF"));
        verify(cacheableAspect, times(1)).evictAfterReturning(any());
    }

    /**
     * Configure the com.krgcorporate.kata.aop.aspect.caching.CacheableAspect
     *  in order to cache the result of any findBy methods in classes suffixed by 'Repository'.
     */
    @Test
    public void repositoryFindMethodShouldBeCached() {
        contractRepository.findByReference("ANY_REF");
        verify(cacheableAspect, times(1)).cacheAfterReturning(any());
    }

    @Test
    public void repositoryFindAllMethodShouldNotBeCached() {
        contractRepository.findAll();
        verify(cacheableAspect, never()).cacheAfterReturning(any());
    }
}
