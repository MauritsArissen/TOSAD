package define;

import define.persistence.dao.DefineOracleDao;

public class Main {

    public static void main(String[] args) {
        System.out.println("define.Main class works");

        DefineOracleDao dao = new DefineOracleDao();

        System.out.println(dao.getDefineInfo());
    	
    
        }
    
    }

