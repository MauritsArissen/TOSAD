package define.business.controller;

import java.util.ArrayList;
import java.util.HashMap;

import define.persistence.dao.DefineOracleDao;

public class DefineController {

    public DefineController() {}

    public HashMap<String, HashMap<String, HashMap<String, HashMap<String, ArrayList<String>>>>> getBusinessRuleData() {
        try {
        	// returns all available user from the database as a string
            return new DefineOracleDao().getAvailableInput();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}
