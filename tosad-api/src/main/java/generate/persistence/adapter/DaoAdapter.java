package generate.persistence.adapter;

import generate.persistence.dao.BaseDao;
import generate.persistence.dao.OracleBaseDao;

public class DaoAdapter  implements DaoSerializer{
    public BaseDao serialize(String type, String url, String user, String pass) {
        // for each different databasetype, add an if-statement
        if(type.equals("Oracle")) {
            return new OracleBaseDao(url, user, pass);
        }
        return null;
    }
}
