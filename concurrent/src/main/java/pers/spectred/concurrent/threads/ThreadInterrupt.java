package pers.spectred.concurrent.threads;

public class ThreadInterrupt {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("start");
                while (!isInterrupted()) {
                    System.out.println("线程没有被中断");
                }
                System.out.println("线程被中断了...");
            }
        };
        thread.start();

        Thread.sleep(1_000);

        thread.interrupt();

        System.out.println(thread.getName() + ":" + thread.isInterrupted() + ":" + thread.getState());
    }
}
