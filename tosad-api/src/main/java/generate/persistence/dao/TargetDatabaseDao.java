package generate.persistence.dao;

import java.sql.Connection;

public class TargetDatabaseDao extends OracleBaseDao implements TargetDao {

    @Override
    public Connection getConnection() {
        return null;
    }

    @Override
    public void closeConnection() {

    }
}
