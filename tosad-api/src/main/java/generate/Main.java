package generate;

import generate.business.controller.GenerateController;

import java.util.ArrayList;

public class Main {
    public static void main (String[] args) {
        System.out.println("test");
        GenerateController controller = new GenerateController();
        String triggername = "BRG_BRGEN_GES_trigger";
        ArrayList response = controller.generateTrigger(triggername);
    }
}
