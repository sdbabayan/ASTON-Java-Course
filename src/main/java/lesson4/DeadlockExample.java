package lesson4;

public class DeadlockExample {
    Integer a;
    Integer b;

    DeadlockExample(Integer a, Integer b) {
        this.a = a;
        this.b = b;
    }

    public void operation1() {
        System.out.println("Метод operation1 стартовал.");
        synchronized (a) {
            System.out.println("Монитор поля а захвачен.");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (b) {
                System.out.println("Монитор поля b захвачен.");
            }
        }
        System.out.println("Метод operation1 закончил работу.");
    }

    public void operation2() {
        System.out.println("Метод operation2 стартовал.");
        synchronized (b) {
            System.out.println("Монитор поля b захвачен.");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (a) {
                System.out.println("Монитор поля а захвачен.");
            }
        }
        System.out.println("Метод operation2 закончил работу.");
    }

    public static void main(String[] args) {
        DeadlockExample deadlockExample = new DeadlockExample(1, 2);
        Thread t1 = new Thread(() -> {
            deadlockExample.operation1();
        }, "Thread 1");
        Thread t2 = new Thread(() -> {
            deadlockExample.operation2();
        }, "Thread 2");
        t1.start();
        t2.start();
    }
}
