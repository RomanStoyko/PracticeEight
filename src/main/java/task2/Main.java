package task2;


import org.apache.log4j.Logger;

public class Main {

    static Logger logger = Logger.getLogger(Main.class);
    public static final Object obj = new Object();
    private static boolean doPrint = false;
    private static int i = 0;
    private static final int MAX = 100;

    public static void main(String[] args) {

        Thread counter = new Thread((Runnable) () -> {
            while (i < MAX) {
                synchronized (obj) {
                    while (!doPrint) {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    i++;
                    doPrint = false;

                    obj.notify();
                }
            }
        }, "counter");

        Thread printer = new Thread((Runnable) () -> {
            while (i < MAX) {
                synchronized (obj) {
                    while (doPrint) {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println(i);
                    doPrint = true;
                    obj.notify();
                }
            }
        }, "printer");

        counter.start();
        printer.start();
    }

}