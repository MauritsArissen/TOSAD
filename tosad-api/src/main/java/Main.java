import java.util.ArrayList;

import define.domain.BusinessRuleType;
import define.domain.Category;
import define.domain.Operator;
import define.persistence.DefineOracleDao;

public class Main {

    public static void main(String[] args) {
        System.out.println("Main class works");

        DefineOracleDao dao = new DefineOracleDao();

        ArrayList<Category> categories = dao.getDefineInfo();
        for(Category c : categories) {
        	System.out.println(c.getName());
        	for(BusinessRuleType brt: c.getRuleTypes()) {
        		System.out.println(brt.getCode());
        		for(Operator o : brt.getAllOperators()) {
        			System.out.println(o.getName());
        		}
        	}
        }

        }
    
    }

