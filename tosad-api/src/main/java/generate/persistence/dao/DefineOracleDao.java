package generate.persistence.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DefineOracleDao implements DefineDao {
   private BaseDao dbconnection;

    public DefineOracleDao(BaseDao dbconnection) {
        this.dbconnection = dbconnection;
    }

   public ArrayList<String> getTriggerInfo(String name) {
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

   public ArrayList<HashMap<String, String>> getAllDataFromTrigger(String triggername) {
       String query = "SELECT generatedtrigger.name as triggername,(SELECT name from targettableattribute WHERE targettableattribute.id = businessrule.attributeid) AS targettableattribute, \n" +
               "(SELECT targettable.name from targettable ,targettableattribute WHERE targettableattribute.id = businessrule.attributeid AND targettable.id = targettableattribute.tableid) as targettablename,\n" +
               "businessrule.failure_message, businessrule.name as businessrulename, businessruletype.name as businessruletypename, operator.name as operatorname \n" +
               "FROM generatedtrigger, businessrule, businessruletype, operator\n" +
               "WHERE generatedtrigger.id = businessrule.triggerid AND operator.id = businessrule.operatorid AND businessrule.type = businessruletype.code AND generatedtrigger.name = ?";
       ArrayList result = new ArrayList();
       int arrayIndex = 0;
       try (Connection conn = dbconnection.getConnection()) {

           PreparedStatement statement = conn.prepareStatement(query);
           statement.setString(1, triggername);
           ResultSet resultset = statement.executeQuery();
           while(resultset.next()) {
               HashMap rowResult = new HashMap();
               rowResult.put("triggername", resultset.getString("triggername"));
               rowResult.put("targettableattribute", resultset.getString("targettableattribute"));
               rowResult.put("targettablename", resultset.getString("targettablename"));
               rowResult.put("failure_message", resultset.getString("failure_message"));
               rowResult.put("businessrulename", resultset.getString("businessrulename"));
               rowResult.put("businessruletypename", resultset.getString("businessruletypename"));
               rowResult.put("operatorname", resultset.getString("operatorname"));

               result.add(rowResult);
           }
           resultset.close();
           statement.close();
       } catch (Exception e) {
           e.printStackTrace();
       }
       dbconnection.closeConnection();
       return result;
   }

   public ArrayList<String> getValuesFromRule(String ruleName) {
       String query = "SELECT parameter.value, \n" +
               "(SELECT targettableattribute.name FROM targettableattribute WHERE targettableattribute.id = parameterrule.attributeid) as attributename, \n" +
               "(SELECT targettable.name FROM targettable, targettableattribute WHERE targettableattribute.id = parameterrule.attributeid AND targettableattribute.tableid = targettable.id) as tablename\n" +
               "FROM parameterrule, parameter, businessrule\n" +
               "WHERE businessrule.name = ? AND parameterrule.businessruleid = businessrule.id AND parameterrule.parameterid = parameter.id";
       ArrayList<String> result = new ArrayList<>();
       int arrayIndex = 0;
       try (Connection conn = dbconnection.getConnection()) {

           PreparedStatement statement = conn.prepareStatement(query);
           statement.setString(1, ruleName);
           ResultSet resultset = statement.executeQuery();
           while(resultset.next()) {
               String value = resultset.getString("value");
               if(value == null) {
                   result.add(resultset.getString("tablename"));
                   result.add(resultset.getString("attributename"));
               } else {
                   result.add(value);
               }

           }
           resultset.close();
           statement.close();
       } catch (Exception e) {
           e.printStackTrace();
       }
       dbconnection.closeConnection();
       return result;

   }

 
}
