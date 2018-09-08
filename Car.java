package lesson05hw;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/*
 *@author Yakovlev Alexandr
 */
public class Car implements Runnable {
    CyclicBarrier cyclicBarrier;
    CountDownLatch countDownLatch;
    CountDownLatch countDownLatchf;
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed, CyclicBarrier cyclicBarrier, CountDownLatch countDownLatch, CountDownLatch countDownLatchf) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
        this.cyclicBarrier = cyclicBarrier;
        this.countDownLatch = countDownLatch;
        this.countDownLatchf = countDownLatchf;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            countDownLatch.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < race.getStages().size(); i++) {
            try {
                race.getStages().get(i).go(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        countDownLatchf.countDown();
    }
}