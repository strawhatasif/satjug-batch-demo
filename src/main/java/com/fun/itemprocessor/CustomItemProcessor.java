package com.fun.itemprocessor;

import com.fun.misc.InvalidAccountException;
import com.fun.model.Account;
import com.fun.model.SettledAccount;

import javax.batch.api.chunk.ItemProcessor;

public class CustomItemProcessor implements ItemProcessor {
    private static final String VIP_PRODUCT = "VIP";
    private static final String SETTLED_STATUS = "SETTLED";

    @Override
    public Object processItem(Object item) throws Exception {
        Account account = (Account) item;
        SettledAccount settledAccount = new SettledAccount();

        if (account.getProduct().startsWith(VIP_PRODUCT)) {
            throw new InvalidAccountException();
        }

        return settledAccount.toBuilder()
                .id(account.getId())
                .name(account.getName())
                .product(account.getProduct())
                .notes(account.getNotes())
                .status(SETTLED_STATUS)
                .build();
    }
}
