package generate.persistence.dao;

import generate.business.domain.*;
import generate.business.domain.businessrules.AttributeRangeRule;
import generate.business.domain.businessrules.BusinessRule;
import generate.persistence.dao.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TargetOracleDao implements TargetDao {
    private BaseDao dbconnection;

    public TargetOracleDao(BaseDao dbconnection) {
        this.dbconnection = dbconnection;
    }

//    public String getGenerateInfo(String triggerName) {
//        ArrayList<BusinessRule> businessRules = null;
//        Trigger trigger = null;
//        String tableName = null;
//        TableAttribute selectedTableAttribute = null;
//        try(Connection con = dbconnection.getConnection()) {
//            PreparedStatement pstmt = con.prepareStatement("SELECT gt.event trigger_event, br.failure_message failure_message, \n" +
//                    "o.name operator, br.id br_id, tt.name table_name, br.type br_type, tta.name attribute_name\n" +
//                    "FROM businessrule br, generatedtrigger gt, operator o, \n" +
//                    "targettableattribute tta, targettable tt\n" +
//                    "WHERE br.attributeid = tta.id\n" +
//                    "AND tta.tableid = tt.id\n" +
//                    "AND br.operatorid = o.id\n" +
//                    "AND br.triggerid = gt.id\n" +
//                    "AND gt.name = ?;");
//            pstmt.setString(1, triggerName);
//            ResultSet rs = pstmt.executeQuery();
//            while(rs.next()) {
//                String triggerEvent = rs.getString("trigger_event");
//                String failureMessage = rs.getString("failure_message");
//                String operatorName = rs.getString("operator");
//                int brId = rs.getInt("br_id");
//                tableName = rs.getString("table_name");
//                String type = rs.getString("br_type");
//
//                selectedTableAttribute = new TableAttribute(rs.getString("attribute_name"));
//
//                trigger = new Trigger(triggerName);
//                Operator operator = new Operator(operatorName);
//
//                if (type == "ARNG") {
//                    ArrayList<LiteralValue> values = null;
//                    PreparedStatement pstmt2 = con.prepareStatement("SELECT p.value value FROM parameter p, parameterrule pr\n" +
//                            "WHERE pr.parameterid = p.id\n" +
//                            "AND pr.businessruleid = ?;");
//                    pstmt2.setInt(1, brId);
//                    ResultSet rs2 = pstmt2.executeQuery();
//                    while (rs2.next()) {
//                        values.add(new LiteralValue(rs2.getString("value")));
//                    }
//
//                    Table table = new Table(tableName, selectedTableAttribute);
//
//                    businessRules.add(new AttributeRangeRule(operator, values, table, "fail"));
//                }
//
//            }
//        } catch (SQLException sqle) {sqle.printStackTrace();}
//
//        String template = generateStaticPart(trigger, tableName);
//
//
//
//        for (BusinessRule b : businessRules) {
//            template += b.generateDynamicPart();
//        }
//
//        template += "end " + trigger.getTriggercode() + ";";
//
//        return template;
//    }

    public String generateStaticPart(Trigger trigger, String tableName) {
        String template = "create or replace trigger " + trigger.getTriggercode() + "\n" +
                trigger.getTriggerevent() + "\n" +
                "  on " + tableName + "\n" +
                "  for each row\n" +
                "declare\n" +
                "  l_passed boolean := true;\n" +
                "  l_error_stack varchar2(4000);\n";


        return template;
    }
}
