package define.persistence;

import define.domain.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DefineOracleDao extends OracleBaseDao implements DefineDao {

    public BusinessRule getBusinessRule(int id) {
        BusinessRule businessRule = null;
        try(Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM businessrule WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                String name = rs.getString("name");
                String type = rs.getString("type");
                String triggerCode = rs.getString("trigger_code");
                String triggerEvent = rs.getString("trigger_event");
                String failureMessage = rs.getString("failure_message");
                int attributeId = rs.getInt("attributeid");

                BusinessRuleType businessRuleType = getBusinessruleType(type);
                Trigger trigger = new Trigger(triggerCode, triggerEvent, failureMessage);
                ArrayList<Parameter> parameters = getParameters(id);
                Table table = getTable(attributeId);

                businessRule = new BusinessRule(name, businessRuleType, businessRuleType.getSelectedOperator(), trigger, parameters, table);
            }
        } catch (SQLException sqle) { sqle.printStackTrace(); }

        return businessRule;
    }

    public BusinessRuleType getBusinessruleType(String code) {
        BusinessRuleType businessRuleType = null;

        return businessRuleType;
    }

    public ArrayList<Parameter> getParameters(int brId) {


        return null;
    }

    public Table getTable(int id) {
        Table table = null;
        try(Connection con = super.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM targettableattribute WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                String name = rs.getString("name");
            }
        } catch (SQLException sqle) { sqle.printStackTrace(); }

        return table;
    }

}
