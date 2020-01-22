package define.business.controller;

import java.util.ArrayList;
import java.util.HashMap;

import define.persistence.adapter.DaoAdapter;
import define.persistence.dao.BaseDao;
import define.persistence.dao.DefineOracleDao;
import define.persistence.dao.TargetOracleDao;

public class DefineController {

    public DefineController() {}

	public HashMap<String, HashMap> getDefineData() {
        try {
        	BaseDao defineconnectionadapter = new DaoAdapter().serialize("Oracle", "jdbc:oracle:thin:@//ondora04.hu.nl:1521/EDUC11", "cursist", "cursist8101");
        	HashMap<String, HashMap<String, HashMap<String, HashMap<String, ArrayList<String>>>>> ruledata = new HashMap<>();
        	ruledata = new DefineOracleDao(defineconnectionadapter).getAvailableInput();
        
        	BaseDao targetconnectionadapter = new DaoAdapter().serialize("Oracle", "jdbc:oracle:thin:@//ondora04.hu.nl:1521/EDUC11", "maurits", "maurits");
        	HashMap<String, HashMap<String, HashMap<String, HashMap<String, String>>>> targetdata = new HashMap<>();
        	targetdata = new TargetOracleDao(targetconnectionadapter).loadTargetDatabase();
        	
			HashMap<String, HashMap> totaldata= new HashMap();
        	totaldata.put("categories", ruledata.get("categories"));
        	totaldata.put("datatable", targetdata.get("datatable"));
        	
        	return totaldata;
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Failed to execute method.");
        return null;
    }
	
	

}
