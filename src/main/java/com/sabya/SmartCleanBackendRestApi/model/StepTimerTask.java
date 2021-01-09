package com.sabya.SmartCleanBackendRestApi.model;

import java.util.Date;
import java.util.TimerTask;

public class StepTimerTask extends TimerTask {

    private final StepTimer stepTimer;

    public StepTimerTask(StepTimer st) {
        this.stepTimer = st;
    }

    @Override
    public void run() {
        int x= stepTimer.getCounter();
        stepTimer.setCounter(++x);
        stepTimer.setUpdationTime(new Date());
    }
}
