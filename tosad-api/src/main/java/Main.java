import define.persistence.OracleBaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        System.out.println("Main class works");

        String query = "select * from businessruletype where code = 'ACMP'";
        String result = "";

        Connection conn = new OracleBaseDao().getConnection();

        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet myRs = pstmt.executeQuery();

        while (myRs.next()) {
            result += myRs.getString("code");
        }

        myRs.close();
        pstmt.close();

        }
    }

