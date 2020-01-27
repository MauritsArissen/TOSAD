package generate.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class TargetOracleDao implements TargetDao {
    private BaseDao dbconnection;

    public TargetOracleDao(BaseDao dbconnection) {
        this.dbconnection = dbconnection;
    }

    public ArrayList<String> executeCode (String triggerCode) {
        System.out.println(triggerCode);
        String query = triggerCode;
        ArrayList<String> result = new ArrayList();
        try (Connection conn = dbconnection.getConnection()) {

            PreparedStatement statement = conn.prepareStatement(query);
            boolean compiled = statement.executeUpdate() > 0;

            if(compiled) {
                result.add("Trigger compiled");
            } else {
                result.add("Trigger Failed");
            }

            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dbconnection.closeConnection();
        return result;
    }
}
