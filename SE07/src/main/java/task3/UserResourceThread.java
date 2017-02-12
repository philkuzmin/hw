package task3;

/**
 * Created by Air on 12/02/2017.
 */

import java.util.Random;

public class UserResourceThread {

    public static void main(String[] args) throws InterruptedException {
        SharedResource res = new SharedResource();
        IntegerSetterGetter t1 = new IntegerSetterGetter("1", res);
        IntegerSetterGetter t2 = new IntegerSetterGetter("2", res);
        IntegerSetterGetter t3 = new IntegerSetterGetter("3", res);
        IntegerSetterGetter t4 = new IntegerSetterGetter("4", res);
        IntegerSetterGetter t5 = new IntegerSetterGetter("5", res);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        Thread.sleep(100);

        t1.stopThread();
        t2.stopThread();
        t3.stopThread();
        t4.stopThread();
        t5.stopThread();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();

        System.out.println("main");
    }
}

class IntegerSetterGetter extends Thread {
    private SharedResource resource;
    private boolean run;

    // вводим дополнительную статическую переменную, которая будет содержать количество активных в данный момент потоков.
    // перед тем как заснуть, каждый поток будет проверять, не остался ли он последним активным потоком, если да - то
    // выходим из цикла и пробуем записать число.
    private static volatile int numberOfThreads = 0;

    private Random rand = new Random();

    public IntegerSetterGetter(String name, SharedResource resource) {
        super(name);
        this.resource = resource;
        run = true;
    }

    public void stopThread() {
        run = false;
    }

    public void run() {
        numberOfThreads++;
        System.out.println(numberOfThreads);
        int action;

        try {
            while (run) {
                action = rand.nextInt(1000);
                if (action % 2 == 0) {
                    getIntegersFromResource();
                } else {
                    setIntegersIntoResource();
                }
            }
            System.out.println("Поток " + getName() + " завершил работу.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            numberOfThreads--;
        }
    }

    private void getIntegersFromResource() throws InterruptedException {
        Integer number;

        synchronized (resource) {
            System.out.println("Поток " + getName() + " хочет извлечь число.");
            number = resource.getELement();
            while (number == null) {
                if (numberOfThreads > 1) {
                    System.out.println("Поток " + getName() + " ждет пока очередь заполнится.");
                    numberOfThreads--;
                    resource.wait();
                    System.out.println("Поток " + getName() + " возобновил работу.");
                    number = resource.getELement();
                    numberOfThreads++;
                } else {
                    System.out.println("Поток " + getName() + " уходит на второй круг, чтобы избежать дедлока");
                    return;
                }
            }
            System.out.println("Поток " + getName() + " извлек число " + number);
        }
    }

    private void setIntegersIntoResource() throws InterruptedException {
        Integer number = rand.nextInt(500);
        synchronized (resource) {
            resource.setElement(number);
            System.out.println("Поток " + getName() + " записал число " + number);
            resource.notify();
        }
    }
}

