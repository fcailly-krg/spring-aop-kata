package com.krgcorporate.kata.aop.contract;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Contract {

    @NonNull
    private final String reference;
}
