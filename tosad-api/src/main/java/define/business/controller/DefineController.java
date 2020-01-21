package define.business.controller;

import java.util.ArrayList;

import define.persistence.dao.DefineOracleDao;

public class DefineController {
    private static DefineController myInstance;


    private DefineController() {}

    // Singelton pattern
    public static DefineController getInstance(){
        if (myInstance == null) {
            myInstance = new DefineController();
        }
        return myInstance;
    }

    public ArrayList<String> getBusinessRuleData() {
        try {
        	// returns all available user from the database as a string
            return new DefineOracleDao().getAvailableInput();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}
