package define.persistence.adapter;

import define.persistence.dao.BaseDao;
import define.persistence.dao.OracleBaseDao;

public class DaoAdapter implements DaoSerializer {

	@Override
	public BaseDao serialize(String type, String url, String user, String pass) {
		// for each different databasetype, add an if-statement
		if(type.equals("Oracle")) {
			return new OracleBaseDao(url, user, pass);
		}
		return null;
	}


}
