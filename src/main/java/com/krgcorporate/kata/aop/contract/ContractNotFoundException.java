package com.krgcorporate.kata.aop.contract;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class ContractNotFoundException extends RuntimeException {

    @NonNull
    private final String contractRef;

}
