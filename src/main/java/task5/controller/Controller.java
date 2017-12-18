package task5.controller;

import org.apache.log4j.Logger;
import task5.model.Model;
import task5.model.util.InputUtility;
import task5.view.View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Controller {
    private static Logger logger = Logger.getLogger(Controller.class);
    private final View VIEW;
    private final Model MODEL;

    public Controller(View VIEW, Model MODEL) {
        this.VIEW = VIEW;
        this.MODEL = MODEL;
    }

    public void doWork(){

        while (true){
            VIEW.printMessage("for exit input --");
            String dirPath = InputUtility.inputStringWithBR(VIEW, "Input correct path to directory");
            if(dirPath.equals("--")){
                logger.info("Exit");
                break;
            }

            File dir = new File(dirPath);

            if(!dir.isDirectory() || !dir.exists()){
                VIEW.printMessage(dirPath + " - bad input. Repeat!");
                logger.info(dirPath + " is a dir: " + dir.isDirectory());
                logger.info(dirPath + " is a exists: " + dir.exists());
                continue;
            }

            String firstLetter = InputUtility.inputStringValueWithScanner(
                    VIEW,
                    "input first letter to find",
                    "wrong input. repeat!",
                    "[a-zA-Z]"
            );

            File fileOut = MODEL.createFile("Test.txt");

            MODEL.handleFile(fileOut,dir, firstLetter);
            VIEW.printMessage("---------------- result -------------------");
            try ( BufferedReader br = new BufferedReader(new FileReader(fileOut))){

                String line = null;
                while ((line = br.readLine()) != null) {
                    VIEW.printMessage(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            VIEW.printMessage("---------------------------------------");
        }

    }
}
