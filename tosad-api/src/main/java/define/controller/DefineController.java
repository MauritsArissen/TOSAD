package define.controller;

public class DefineController {
    private static DefineController myInstance;


    private DefineController() {}

    public static DefineController getInstance(){
        if (myInstance == null) {
            myInstance = new DefineController();
        }
        return myInstance;
    }
}
