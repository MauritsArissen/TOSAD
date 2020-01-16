package define.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class OracleBaseDao implements BaseDao {

    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private String user = "REDOUANOV";
    private String pass = "Utrecht030";
    protected Connection myConn;
    protected Statement myStmt;

    public Connection getConnection() {
        try {
            myConn = DriverManager.getConnection(url, user, pass);
            myStmt = myConn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return myConn;
    }

    public void closeConnection() {
        try {
            myConn.close();
            myStmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Connection to database closed successfully.");
    }
}
