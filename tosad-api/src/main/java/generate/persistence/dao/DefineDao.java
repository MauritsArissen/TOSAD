package generate.persistence.dao;

import java.util.ArrayList;

public interface DefineDao {
    ArrayList<String> getTriggerInfo();
    ArrayList<String> getRulesByTrigger(String triggername);
}
