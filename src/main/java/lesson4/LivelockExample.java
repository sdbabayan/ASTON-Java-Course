package lesson4;

import java.util.concurrent.locks.ReentrantLock;

public class LivelockExample implements Runnable {
    private ReentrantLock lock1;
    private ReentrantLock lock2;

    LivelockExample(ReentrantLock lock1, ReentrantLock lock2) {
        this.lock1 = lock1;
        this.lock2 = lock2;
    }

    @Override
    public void run() {
        while (true) {
            if (lock1.tryLock()) {
                System.out.println(Thread.currentThread().getName() + " захватил первый замок.");
                try {
                    Thread.sleep(50);
                    if (lock2.tryLock()) {
                        System.out.println(Thread.currentThread().getName() + " захватил второй замок и завершил работу!");
                        lock2.unlock();
                        return;
                    } else {
                        System.out.println(Thread.currentThread().getName() + " не смог захватить второй замок и отпустил первый замок.");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock1.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();

        Thread thread1 = new Thread(new LivelockExample(lock1, lock2), "Thread 1");
        Thread thread2 = new Thread(new LivelockExample(lock2, lock1), "Thread 2");

        thread1.start();
        thread2.start();
    }
}


