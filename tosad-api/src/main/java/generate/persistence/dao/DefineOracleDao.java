package generate.persistence.dao;

import generate.business.domain.businessrules.BusinessRule;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DefineOracleDao extends OracleBaseDao implements DefineDao {
   public void getGenerateInfo() {
       ArrayList<BusinessRule> businessRules = null;
       try(Connection con = super.getConnection()) {
           PreparedStatement pstmt = con.prepareStatement("");
           ResultSet rs = pstmt.executeQuery();
           while(rs.next()) {
               String type = "";
               /*
               if (type == "ARNG") {
                   businessRules.add(new RangeRule(operator, trigger, values, table, ));
               }

                */
           }
       } catch (SQLException sqle) {sqle.printStackTrace();}
   }
/*
   public String generateStaticPart() {
       String template = "create or replace trigger " + trigger.getTriggercode() + "\n" +
               "  before " + trigger.getTriggerevent() + "\n" +
               "  on " + table.getName() + "\n" +
               "  for each row\n" +
               "declare\n" +
               "  l_passed boolean := true;\n" +
               "  l_error_stack varchar2(4000);\n" +
               "begin\n";
       businessRules = trigger.getBusinessRules();

       for (BusinessRule b : businessRules) {
           template += b.generateDynamicPart() + "\n";
       }

       template += "end " + trigger.getTriggercode() + ";";

       return template;
   }

 */
}
