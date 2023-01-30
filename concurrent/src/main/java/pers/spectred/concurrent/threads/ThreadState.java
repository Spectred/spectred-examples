package pers.spectred.concurrent.threads;

public class ThreadState {

    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(() -> {
            try {
                System.out.println("a is running");
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread b = new Thread(() -> {
            try {
                System.out.println("b is running");
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        a.start();
        a.join();
        b.start();

        System.out.println(a.getName() + ":" + a.getState());
        System.out.println(b.getName() + ":" + b.getState());
    }
}
