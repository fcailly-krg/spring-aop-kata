package com.krgcorporate.kata.aop.contract.repository;

import com.krgcorporate.kata.aop.contract.Contract;
import com.krgcorporate.kata.aop.contract.ContractRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Repository
public class InMemoryContractRepository implements ContractRepository {

    private static final Map<String, Contract> MEMORY = new HashMap<>();

    @Override
    public Contract save(Contract contract) {
        return MEMORY.put(contract.getReference(), contract);
    }

    @Override
    public Optional<Contract> findByReference(String reference) {
        return Optional.ofNullable(MEMORY.get(reference));
    }

    @Override
    public List<Contract> findAll() {
        return Collections.unmodifiableList(new ArrayList<>(MEMORY.values()));
    }
}
