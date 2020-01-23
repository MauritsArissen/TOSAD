package generate.persistence.adapter;

import generate.persistence.dao.BaseDao;

public interface DaoSerializer {
    public BaseDao serialize(String type, String url, String user, String pass);
}
