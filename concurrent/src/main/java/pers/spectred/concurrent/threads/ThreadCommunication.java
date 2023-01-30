package pers.spectred.concurrent.threads;

public class ThreadCommunication {

    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        ThreadC threadC = new ThreadC();
        threadC.start();
        threadC.join();

        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();

        threadA.start();
        Thread.sleep(2_000);
        threadB.start();
        /*
            A先执行，遇到wait等待，2秒后B执行，遇到notify唤醒A,B和A结束
            Thread A start
            Thread B start
            Thread B end
            Thread A end
         */
    }

    static class ThreadA extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("Thread A start");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Thread A end");
            }
        }
    }

    static class ThreadB extends Thread {
        @Override
        public void run() {
            synchronized (lock) {
                System.out.println("Thread B start");
                lock.notifyAll();
                System.out.println("Thread B end");
            }
        }
    }

    static class ThreadC extends Thread {
        @Override
        public void run() {
            System.out.println("Thread C start,等待3秒");
            try {
                Thread.sleep(3_000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread C end");
        }
    }

}
