package generate.persistence.dao;

import generate.persistence.dao.BaseDao;
import generate.business.domain.*;
import generate.business.domain.businessrules.AttributeRangeRule;
import generate.business.domain.businessrules.BusinessRule;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DefineOracleDao implements DefineDao {
   private BaseDao dbconnection;

    public DefineOracleDao(BaseDao dbconnection) {
        this.dbconnection = dbconnection;
    }

   public ArrayList<String> getTriggerInfo() {
        //hardcoded application
        String query = "select name from generatedtrigger WHERE REGEXP_LIKE(name, '^BRG_BRGEN')";
        ArrayList<String> result = new ArrayList();
       try (Connection conn = dbconnection.getConnection()) {

           PreparedStatement statement = conn.prepareStatement(query);
           ResultSet resultset = statement.executeQuery();

           while(resultset.next()) {
                result.add(resultset.getString("name"));
           }
           resultset.close();
           statement.close();
       } catch (Exception e) {
           e.printStackTrace();
       }
       dbconnection.closeConnection();
       return result;
   }

   public ArrayList getRulesByTrigger(String triggername) {
       String query = "select businessrule.name from businessrule, generatedtrigger where generatedtrigger.name = '" + triggername + "' AND generatedtrigger.id = businessrule.triggerid";
       ArrayList<String> result = new ArrayList();
       try (Connection conn = dbconnection.getConnection()) {

           PreparedStatement statement = conn.prepareStatement(query);
           ResultSet resultset = statement.executeQuery();

           while(resultset.next()) {
               result.add(resultset.getString("name"));
           }
           resultset.close();
           statement.close();
       } catch (Exception e) {
           e.printStackTrace();
       }
       dbconnection.closeConnection();
       return result;
   }

   public ArrayList getAllDataFromTrigger(String triggername) {
        return null;
   }

 
}
