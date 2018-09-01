/*
 *@author Yakovlev Alexandr
 * 1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз.
 *    Порядок должен быть именно таким: ABСABСABС. Используйте wait/notify/notifyAll.
 */
public class Task01 {
    public static void main(String[] args) {
        ThreadC threadC = new ThreadC();
        ThreadB threadB = new ThreadB();
        ThreadA threadA = new ThreadA();

        threadC.start();
        threadA.start();
        threadB.start();

        synchronized (threadC) {
            for (int i = 0; i < 5; i++) {
                synchronized (threadC) {
                    threadA.print();
                }
                synchronized (threadA) {
                    threadB.print();
                }
                synchronized (threadB) {
                    threadC.print();
                }
            }
        }

    }

    static class ThreadA extends Thread {
        @Override
        public void run() {

        }

        public void print() {
            synchronized (this) {
                    System.out.println('A');
                notify();
            }
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run() {

        }

        public void print() {
            synchronized (this) {
                System.out.println('B');
                notify();
            }
        }
    }

    static class ThreadC extends Thread {
        @Override
        public void run() {
        }

        public void print() {
            synchronized (this) {
                System.out.println('C');
                notify();
            }
        }
    }
}
