package task1;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) {
        new Thread( (Runnable)() -> {
                int i = 10;
                while (i >= 0){
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(i != 0) {
                        System.out.println(i);
                    }else{
                        System.out.println("Bomb");
                    }
                    i--;
                } }
                ).start();

    }
}
