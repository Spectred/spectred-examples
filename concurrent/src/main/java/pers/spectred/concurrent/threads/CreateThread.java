package pers.spectred.concurrent.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class CreateThread {

    public static void main(String[] args) throws Exception {
        // 1. 继承Thread
        new MyThread().start(); // From Thread:Thread-0
        // 2. 实现Runnable
        new Thread(new MyRunnable()).start(); // From Runnable:Thread-1
        // 3. 实现Callable,使用FutureTask ,有返回值
        FutureTask<String> futureTask = new FutureTask<>(new MyCallable());
        new Thread(futureTask).start();
        System.out.println(futureTask.get()); // From Callable:Thread-2
        // 4. 使用线程池ThreadPoolExecutor
        final ExecutorService threadPool = Executors.newSingleThreadExecutor();
        threadPool.execute(() -> System.out.println("From ThreadPool:" + Thread.currentThread().getName())); // From ThreadPool:pool-1-thread-1
        threadPool.shutdown();
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("From Thread:" + Thread.currentThread().getName());
        }
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("From Runnable:" + Thread.currentThread().getName());
        }
    }

    static class MyCallable implements Callable<String> {
        @Override
        public String call() throws Exception {
            return "From Callable:" + Thread.currentThread().getName();
        }
    }
}
