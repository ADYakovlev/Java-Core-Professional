package lesson05hw;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Task01 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch waitingCarsReady = new CountDownLatch(Constant.CARS_COUNT);
        CountDownLatch waitingCarsFinished = new CountDownLatch(Constant.CARS_COUNT);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(Constant.CARS_COUNT);
        Semaphore semaphore = new Semaphore(Constant.CARS_COUNT/2);
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(semaphore), new Road(40));
        Car[] cars = new Car[Constant.CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), cyclicBarrier, waitingCarsReady, waitingCarsFinished);
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        waitingCarsReady.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        waitingCarsFinished.await();
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}