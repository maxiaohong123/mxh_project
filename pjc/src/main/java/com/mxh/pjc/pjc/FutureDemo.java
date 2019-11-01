package com.mxh.pjc.pjc;

import java.util.Random;
import java.util.concurrent.*;

/**
 * hystrix原理：
 *  内部采用future，采用超时机制。
 */

public class FutureDemo {

    public static void main(String[] args) {
        final Random random = new Random();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> future =   executorService.submit(()->{
            int value = random.nextInt(200);
            System.out.println("helloworld() costs"+value+"ms.");
            Thread.sleep(value);
            return  "helloworld";
            }
        );

        try {
            future.get(100,TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            System.out.println("超时保护");
        }

        executorService.shutdown();
    }
}
