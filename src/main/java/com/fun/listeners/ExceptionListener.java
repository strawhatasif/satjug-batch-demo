package com.fun.listeners;

import javax.batch.api.chunk.listener.ItemProcessListener;
import javax.batch.api.listener.StepListener;

public class ExceptionListener implements ItemProcessListener, StepListener {

    @Override
    public void beforeProcess(Object item) throws Exception {

    }

    @Override
    public void afterProcess(Object item, Object result) throws Exception {

    }

    @Override
    public void onProcessError(Object item, Exception ex) throws Exception {
        System.out.println("we can do whatever we want here...like write out to a report");
    }

    @Override
    public void beforeStep() throws Exception {

    }

    @Override
    public void afterStep() throws Exception {

    }
}
