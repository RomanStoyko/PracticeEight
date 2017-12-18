package task5.model.util;

import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class FindWords extends RecursiveTask<Boolean> {
    private static Logger logger = Logger.getLogger(FindWords.class);
    private File fileOut;
    private File file;
    private String firstLetter;

    public FindWords(File fileOut, File file, String firstLetter) {
        this.fileOut = fileOut;
        this.file = file;
        this.firstLetter = firstLetter;
    }

    @Override
    public Boolean compute() {
        if (file.isDirectory()) {
            File[] dirAndFile = file.listFiles();
            if(dirAndFile == null){
                return true;
            }

            logger.info(file.getPath());
            for (int i = 0; i < dirAndFile.length; i++) {
                if (dirAndFile[i].isDirectory()) {
                    FindWords fw = new FindWords(fileOut, dirAndFile[i], firstLetter);
                    fw.fork();
                    fw.compute();
                }else {
                    if (dirAndFile[i].getName().toLowerCase().endsWith(".txt")) {
                        findAndWrite(dirAndFile[i]);
                    }
                }
            }
        } else {
            if (file.getName().toLowerCase().endsWith(".txt")) {
                findAndWrite(file);
            }
        }
        return true;
    }

    private void findAndWrite(File file){
        String fileString = "";
        try {
            byte[] encoded = Files.readAllBytes(file.toPath());
            fileString = new String(encoded);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(fileString.isEmpty()){return;}

        String[] strings = fileString.split("\\W+");
        long count = Arrays.stream(strings).filter(x -> x.toLowerCase().startsWith(firstLetter.toLowerCase())).count();

        if(count > 0){
            synchronized (fileOut){
                try(BufferedWriter out = new BufferedWriter( new FileWriter(fileOut,true))) {
                    String wString = file.getPath() + " " + count + "\n";
                    out.write(wString);
                    out.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

        }

    }


}
