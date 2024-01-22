package org.mrtxee.bnksrs.lto;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class CallableTaskMoney implements Callable<Boolean>
{
    private String name;

    public CallableTaskMoney(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public Boolean call() throws Exception {
        try
        {
            long duration = (long) (Math.random() * 10);
            System.out.printf("Doing a money-task : %s with %s%n", name, Thread.currentThread().getId());
            TimeUnit.SECONDS.sleep(duration);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        return true;
    }
}
