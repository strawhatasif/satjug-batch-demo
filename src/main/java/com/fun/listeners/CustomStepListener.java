package com.fun.listeners;

import javax.batch.api.listener.StepListener;
import javax.batch.runtime.context.StepContext;
import javax.inject.Inject;

public class CustomStepListener implements StepListener {

    @Inject
    private StepContext stepContext;

    @Override
    public void beforeStep() throws Exception {
        System.out.println("listening before step: " + stepContext.getStepName());
    }

    @Override
    public void afterStep() throws Exception {
        System.out.println("listening after step: " + stepContext.getStepName());
    }
}
