package define;

import define.business.controller.DefineController;

public class Main {

    public static void main(String[] args) {
        System.out.println("define.Main class works");

        DefineController dao = new DefineController();

        System.out.println(dao.getDefineData());
    	
    
        }
    }

