package lesson4;

class CommonData {
    int num = 1;
}

class PrintOne implements Runnable {
    CommonData commonData;

    PrintOne(CommonData commonData) {
        this.commonData = commonData;
    }

    public void printOne() {
        synchronized (commonData) {
            if (commonData.num != 1) {
                try {
                    commonData.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println(commonData.num);
                commonData.num++;
                commonData.notify();
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            printOne();
        }
    }
}

class PrintTwo implements Runnable {
    CommonData commonData;

    PrintTwo(CommonData commonData) {
        this.commonData = commonData;
    }

    public synchronized void printTwo() {
        synchronized (commonData) {
            if (commonData.num != 2) {
                try {
                    commonData.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println(commonData.num);
                commonData.num--;
                commonData.notify();
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            printTwo();
        }
    }
}

public class PrintNumbers {
    public static void main(String[] args) {
        CommonData commonData = new CommonData();
        Thread printNumOne = new Thread(new PrintOne(commonData));
        Thread printNumTwo = new Thread(new PrintTwo(commonData));
        printNumOne.start();
        printNumTwo.start();
    }
}
