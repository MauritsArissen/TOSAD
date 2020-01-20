package define.business.controller;

import define.persistence.dao.DefineOracleDao;

public class DefineController {
    private static DefineController myInstance;


    private DefineController() {}

    public static DefineController getInstance(){
        if (myInstance == null) {
            myInstance = new DefineController();
        }
        return myInstance;
    }

    public String getDBData() {
        try {
            return new DefineOracleDao().getDefineInfo();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

}
