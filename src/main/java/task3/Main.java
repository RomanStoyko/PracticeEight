package task3;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.lang.Thread.sleep;

public class Main {

    private final static int MAX = 10;
    private final static int SLEEP = 1;
    static Map<String, String> map = new HashMap<String, String>();
    static Map<String, String> mapConcurrent = new ConcurrentHashMap<String, String>();

    public static void main(String[] args) {

        Thread writer1 = new Thread((Runnable) () -> {

            for (int i = 0; i < MAX; i++) {
                synchronized (map) {
                    map.put("T1_" + i, "T1_" + i);
                    try {
                        sleep(SLEEP);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread writer2 = new Thread((Runnable) () -> {
            for (int i = 0; i < MAX; i++) {
                synchronized (map) {
                    map.put("T1_" + i, "T2_" + i);
                    try {
                        sleep(SLEEP);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread writerC1 = new Thread((Runnable) () -> {
            for (int i = 0; i < MAX; i++) {
                mapConcurrent.put("T1_" + i, "T1_" + i);
                try {
                    sleep(SLEEP);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread writerC2 = new Thread((Runnable) () -> {
            for (int i = 0; i < MAX; i++) {
                mapConcurrent.put("T1_" + i, "T2_" + i);
                try {
                    sleep(SLEEP);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread reader1 = new Thread((Runnable) () -> {

            for (int i = 0; i < MAX; i++) {
                synchronized (map) {
                    System.out.println(map.get("T1_" + i));
                    try {
                        sleep(SLEEP);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        });

        Thread reader2 = new Thread((Runnable) () -> {

            for (int i = 0; i < MAX; i++) {
                synchronized (map) {
                    System.out.println(map.get("T1_" + i));
                    try {
                        sleep(SLEEP);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread reader3 = new Thread((Runnable) () -> {
            for (int i = 0; i < MAX; i++) {
                System.out.println(mapConcurrent.get("T1_" + i));
                try {
                    sleep(SLEEP);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread reader4 = new Thread((Runnable) () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(mapConcurrent.get("T1_" + i));
                try {
                    sleep(SLEEP);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Date date = new Date();
        writer1.start();
        writer2.start();
        reader1.start();
        reader2.start();
        try {
            writer1.join();
            writer2.join();
            reader1.join();
            reader2.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(new Date().getTime() - date.getTime());

        date = new Date();
        writerC1.start();
        writerC2.start();
        reader3.start();
        reader4.start();
        try {
            writerC1.join();
            writerC2.join();
            reader3.join();
            reader4.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Date().getTime() - date.getTime());
    }
}
