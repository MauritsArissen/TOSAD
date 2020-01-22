package define.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public class DefineOracleDao implements DefineDao {
	private BaseDao dbconnection;
	
	public DefineOracleDao(BaseDao dbconnection) {
		this.dbconnection = dbconnection;
	}

	@Override
	public HashMap<String, HashMap<String, HashMap<String, HashMap<String, ArrayList<String>>>>> getAvailableInput() {
        String query = "select operator.name as \"operator\", businessruletype.code as \"businessrulecode\", businessruletype.name as \"businessrulename\", businessruletype.description as \"businessruledescription\", category.name as \"categoryname\" from operator, businessruletype, category, operatorrule where operator.id = operatorrule.id and operatorrule.code = businessruletype.code and businessruletype.categoryid = category.id";
        HashMap<String, HashMap<String, HashMap<String, HashMap<String, ArrayList<String>>>>> data = new HashMap<>();        
        
        try (Connection conn = dbconnection.getConnection()) {

            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultset = statement.executeQuery();
            
            data.put("categories", new HashMap<String, HashMap<String, HashMap<String, ArrayList<String>>>>());
            while(resultset.next()) {
            	if(data.get("categories").containsKey(resultset.getString("categoryname"))) {
            		
            	} else {
            		data.get("categories").put(resultset.getString("categoryname"), new HashMap<String, HashMap<String, ArrayList<String>>>());
            	}
            	if(data.get("categories").get(resultset.getString("categoryname")).containsKey(resultset.getString("businessrulename"))) {
            		
            	} else {
            		data.get("categories").get(resultset.getString("categoryname")).put(resultset.getString("businessrulename"), new HashMap<String, ArrayList<String>>());
            		data.get("categories").get(resultset.getString("categoryname")).get(resultset.getString("businessrulename")).put("operators", new ArrayList<String>());
            	}
            	data.get("categories").get(resultset.getString("categoryname")).get(resultset.getString("businessrulename")).get("operators").add(resultset.getString("operator"));
            }
            resultset.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dbconnection.closeConnection();
        return data;
    }
    
    

}
