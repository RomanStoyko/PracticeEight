package task5.model.util;


import org.apache.log4j.Logger;
import task5.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputUtility {
    private static Logger logger = Logger.getLogger(InputUtility.class);

    private static Scanner scanner = new Scanner(System.in);
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static int inputIntValueWithScanner(View view, String msg, String errorMsg, Pattern pattern) {
        view.printMessage(msg);
        while (!scanner.hasNextInt()) {
            view.printMessage(errorMsg +
                    msg);
            scanner.next();
        }
        return scanner.nextInt();
    }

    public static String inputStringValueWithScanner(View view, String msg, String errorMsg, String sPattern) {
        view.printMessage(msg);
        scanner.useDelimiter("\n");

        while (!scanner.hasNext(sPattern)) {
            view.printMessage(errorMsg +
                    msg);
            scanner.next();
        }
        return scanner.next(sPattern);
    }

    public static String inputStringWithBR(View view, String msg) {
        String result = "";

        view.printMessage(msg);
        try {
            result = bufferedReader.readLine();
        } catch (IOException e) {
            logger.info("Wrong input: " + e.getMessage());
        }
        return result;
    }


}
