package generate.persistence;

import java.sql.Connection;

public interface BaseDao {
    public Connection getConnection();
    public void closeConnection();
}
