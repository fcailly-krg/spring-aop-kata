package com.krgcorporate.kata.aop.contract;

import com.krgcorporate.kata.aop.aspect.activation.Disabled;
import com.krgcorporate.kata.aop.aspect.profiling.Profiled;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ContractService {

    @NonNull
    private final ContractRepository contractRepository;

    public Contract get(String contractReference) {
        return this.contractRepository.findByReference(contractReference)
                .orElseThrow(() -> new ContractNotFoundException(contractReference));
    }

    @Profiled
    public Contract create(String reference) {
        final Contract contract = new Contract(reference);

        return this.contractRepository.save(contract);
    }

    @Disabled
    public void disabledMethod() {
        // nothing to see here
    }

    public void erroneousMethod() {
        throw new IllegalStateException();
    }

    public void loggable() {
    }
}
