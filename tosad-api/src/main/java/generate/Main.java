package generate;

import generate.business.controller.GenerateController;

import java.util.ArrayList;

public class Main {
    public static void main (String[] args) {
        System.out.println("test");
        GenerateController controller = new GenerateController();
        ArrayList response = controller.returnTriggers();
        System.out.println(response);
    }
}
