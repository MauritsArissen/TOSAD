package define.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class DefineOracleDao extends OracleBaseDao implements DefineDao {

    public HashMap<String, String> getAvailableInput() {
        String query = "select operator.name as \"operator\", businessruletype.code as \"businessrulecode\", businessruletype.name as \"businessrulename\", businessruletype.description as \"businessruledescription\", category.name as \"categoryname\"from operator, businessruletype, category, operatorrulewhere operator.id = operatorrule.idand operatorrule.code = businessruletype.codeand businessruletype.categoryid = category.id;";
        HashMap<String, HashMap<String, HashMap<String, ArrayList<String>>>> data = new HashMap<>();        
        
        try (Connection conn = super.getConnection()) {

            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultset = statement.executeQuery();
            
            while(resultset.next()) {
            	if(data.containsKey(resultset.getString("categoryname"))) {
            		
            	} else {
            		data.put(resultset.getString("categoryname"), new HashMap<String, HashMap<String, ArrayList<String>>>());
            	}
            	if(data.get(resultset.getString("categoryname")).containsKey(resultset.getString("businessrulename"))) {
            		
            	} else {
            		data.get(resultset.getString("categoryname")).put(resultset.getString("businessrulename"), new HashMap<String , ArrayList<String>>());
            		data.get(resultset.getString("categoryname")).get(resultset.getString("businessrulename")).put("operators", new ArrayList<String>());
            	}
            	data.get(resultset.getString("categoryname")).get(resultset.getString("businessrulename").)
            		
            		
            	data.put("categories", 
            			new HashMap<String, String>().put(resultset.getString("categoryname"), 
            					new HashMap<String, String>().put(resultset.getString("businessrulename"), 
            							new HashMap<String, String>().put("operators", 
            									new ArrayList<String>().add(resultset.getString("operator"))))));
                
            }
            resultset.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.closeConnection();
        return data;
       
    }

}
