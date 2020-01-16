package define.persistence;

import define.domain.*;

import java.sql.*;
import java.util.ArrayList;

public class DefineOracleDao extends OracleBaseDao implements DefineDao {

//    public BusinessRule getBusinessRule(int id) {
//        BusinessRule businessRule = null;
//        try(Connection con = super.getConnection()) {
//            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM businessrule WHERE id = ?");
//            pstmt.setInt(1, id);
//            ResultSet rs = pstmt.executeQuery();
//            while(rs.next()) {
//                String name = rs.getString("name");
//                String type = rs.getString("type");
//                String triggerCode = rs.getString("trigger_code");
//                String triggerEvent = rs.getString("trigger_event");
//                String failureMessage = rs.getString("failure_message");
//                int attributeId = rs.getInt("attributeid");
//
//                BusinessRuleType businessRuleType = getBusinessruleType(type);
//                Trigger trigger = new Trigger(triggerCode, triggerEvent, failureMessage);
//                ArrayList<Parameter> parameters = getParameters(id);
//                Table table = getTable(attributeId);
//
//                businessRule = new BusinessRule(name, businessRuleType, businessRuleType.getSelectedOperator(), trigger, parameters, table);
//            }
//        } catch (SQLException sqle) { sqle.printStackTrace(); }
//
//        return businessRule;
//    }
//
//    public BusinessRuleType getBusinessruleType(String code) {
//        BusinessRuleType businessRuleType = null;
//
//        return businessRuleType;
//    }
//
//    public ArrayList<Parameter> getParameters(int brId) {
//
//
//        return null;
//    }

//    public Table getTable(int id) {
//        Table table = null;
//        try(Connection con = super.getConnection()) {
//            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM targettableattribute WHERE id = ?");
//            pstmt.setInt(1, id);
//            ResultSet rs = pstmt.executeQuery();
//            while(rs.next()) {
//                String name = rs.getString("name");
//            }
//        } catch (SQLException sqle) { sqle.printStackTrace(); }
//
//        return table;
//    }

//    public ArrayList<Category> getDefineInfo() {
//        String query = "select category.name as categoryname, businessruletype.code as ruletypecode, businessruletype.name as ruletypename, businessruletype.description as ruletypedescription\n" +
//                        "from category, businessruletype\n" +
//                         "where category.id = businessruletype.categoryid";
//        ArrayList<Category> categories = new ArrayList<>();
//
//        try (Connection conn = super.getConnection()) {
//
//            PreparedStatement pstmt = conn.prepareStatement(query);
//            ResultSet myRs = pstmt.executeQuery();
//
//            while(myRs.next()) {
//                // Category parameters
//                String categoryname = myRs.getString("categoryname");
//                ArrayList<BusinessRuleType> businessruletypes = new ArrayList<>();
//
//                // BusinessRuleType parameters
//                String ruletypecode = myRs.getString("ruletypecode");
//                String ruletypename = myRs.getString("ruletypename");
//                String ruletypedescription = myRs.getString("ruletypedescription");
//                BusinessRuleType businessruletype = new BusinessRuleType(ruletypecode, ruletypename, ruletypedescription);
//
//                // Operator parameters
////                Operator operator = new Operator();
//
//                businessruletypes.add(businessruletype);
//                Category category = new Category(categoryname, businessruletypes);
//
//                categories.add(category);
//                System.out.println(categoryname + " " + ruletypename);
//            }
//            myRs.close();
//            pstmt.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return categories;
//    }

    public ArrayList<Category> getDefineInfo() {
        String categoryquery = "select * from category";
        String ruletypequery = "select code, name, description, (SELECT name FROM category WHERE businessruletype.categoryid = category.id) AS categoryname from businessruletype";
        String operatorquery = "select operator.name, businessruletype.code from operator, operatorrule, businessruletype where operator.id = operatorrule.id and businessruletype.code = operatorrule.code";
        ArrayList<Category> categories = new ArrayList<>();

        try (Connection conn = super.getConnection()) {

            // Category
            PreparedStatement categorypstmt = conn.prepareStatement(categoryquery);
            ResultSet categorymyRs = categorypstmt.executeQuery();

            while(categorymyRs.next()) {
                String categoryname = categorymyRs.getString("name");

                Category category = new Category(categoryname);
                categories.add(category);
            }
            categorymyRs.close();
            categorypstmt.close();

            // Businessruletypes
            PreparedStatement ruletypepstmt = conn.prepareStatement(ruletypequery);
            ResultSet ruletypemyRs = ruletypepstmt.executeQuery();

            while (ruletypemyRs.next()) {
                String ruletypecode = ruletypemyRs.getString("code");
                String ruletypename = ruletypemyRs.getString("name");
                String ruletypedescription = ruletypemyRs.getString("description");
                String ruletypecategoryname = ruletypemyRs.getString("categoryname");

                BusinessRuleType ruletype = new BusinessRuleType(ruletypecode, ruletypename, ruletypedescription);
                for (Category c : categories) {
                    if(c.getName().equals(ruletypecategoryname)) {
                        c.addRuleType(ruletype);
                    }
                }
            }

            ruletypemyRs.close();
            ruletypepstmt.close();


            // Operators
            PreparedStatement operatorpstmt = conn.prepareStatement(operatorquery);
            ResultSet operatormyRs = operatorpstmt.executeQuery();

            while (operatormyRs.next()) {
                String operatorname = operatormyRs.getString("name");
                String ruleTypeCode = operatormyRs.getString("code");

                Operator operator = new Operator(operatorname);

                for (Category c : categories) {
                    for(BusinessRuleType brt : c.getRuleTypes()) {
                        if(brt.getCode().equals(ruleTypeCode)) {
                            brt.setOperator(operator);
                        }
                    }
                }
            }

            operatormyRs.close();
            operatorpstmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }



}
