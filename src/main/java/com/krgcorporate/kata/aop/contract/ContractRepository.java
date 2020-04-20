package com.krgcorporate.kata.aop.contract;

import java.util.List;
import java.util.Optional;

public interface ContractRepository {

    Contract save(Contract contract);

    Optional<Contract> findByReference(String reference);

    List<Contract> findAll();

}
