import define.persistence.DefineOracleDao;

public class Main {

    public static void main(String[] args) {
        System.out.println("Main class works");

        DefineOracleDao dao = new DefineOracleDao();

        System.out.println(dao.getDefineInfo());
    	
    
        }
    
    }

