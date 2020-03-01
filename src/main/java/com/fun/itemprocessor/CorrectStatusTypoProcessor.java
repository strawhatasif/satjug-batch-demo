package com.fun.itemprocessor;

import com.fun.model.SettledAccount;

import javax.batch.api.chunk.ItemProcessor;

public class CorrectStatusTypoProcessor implements ItemProcessor {
    private static final String CORRECTED_STATUS = "SETTLED";

    @Override
    public Object processItem(Object item) throws Exception {
        SettledAccount settledAccount = (SettledAccount) item;
        System.out.println("ACCOUNT WITH A TYPO IN STATUS? "
                + settledAccount.getName() + " " + settledAccount.getStatus());
        SettledAccount correctedStatusAccount = new SettledAccount();

        return correctedStatusAccount
                .toBuilder()
                .id(settledAccount.getId())
                .name(settledAccount.getName())
                .product(settledAccount.getProduct())
                .notes(settledAccount.getNotes())
                .status(CORRECTED_STATUS)
                .build();
    }
}
