package task5.model;

import task5.model.util.FindWords;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;

public class Model {

    public File createFile(String s){
        File fileOut = new File(s);
        if(fileOut.exists()){
            try {
                new FileWriter(fileOut).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return fileOut;
    }

    public void handleFile(File fileOut, File dir, String firstLetter){
        ForkJoinPool pool = new ForkJoinPool(4);
        boolean done = false;
        done = pool.invoke(new FindWords(fileOut,dir, firstLetter));

        while (pool.getActiveThreadCount() != 0){}
    }

}
