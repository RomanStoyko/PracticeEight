package task5;

import task5.controller.Controller;
import task5.model.Model;
import task5.view.View;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller(new View(), new Model());
        controller.doWork();
    }
}
