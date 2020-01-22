package define.persistence.adapter;

import define.persistence.dao.BaseDao;

public interface DaoSerializer {

    public BaseDao serialize(String type, String url, String user, String pass);
}
