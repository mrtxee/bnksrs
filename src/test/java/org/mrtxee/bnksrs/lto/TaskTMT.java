package org.mrtxee.bnksrs.lto;

import java.util.concurrent.TimeUnit;

public class TaskTMT implements Runnable {
    private final String name;

    public TaskTMT(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        try {
            Long duration = (long) (Math.random() * 10);
            System.out.println("Doing a task during : " + name);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
