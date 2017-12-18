package task4;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {

    private static int size = 1000000;
    private static int[] data = new int[size];
    public static void main(String[] args) {

        System.out.println("fill");
        Random random = new Random();
        for (int i = 0; i < data.length; i++) {
            data[i] = random.nextInt(101);
        }
        System.out.println("start");
        ForkJoinPool pool = new ForkJoinPool(4);
        Long sum = pool.invoke(new SumOfN(data,0,data.length));
        System.out.println(sum);

    }
}
