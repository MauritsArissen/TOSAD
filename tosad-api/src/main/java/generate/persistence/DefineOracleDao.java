package generate.persistence;

import java.sql.Connection;

public class DefineOracleDao extends OracleBaseDao implements DefineDao {
    @Override
    public Connection getConnection() {
        return null;
    }

    @Override
    public void closeConnection() {

    }
}
