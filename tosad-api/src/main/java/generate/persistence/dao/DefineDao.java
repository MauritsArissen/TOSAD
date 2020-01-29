package generate.persistence.dao;

import java.util.ArrayList;
import java.util.HashMap;

public interface DefineDao {
    ArrayList<String> getTriggerInfo();
    ArrayList<String> getRulesByTrigger(String triggername);

    ArrayList<HashMap<String, String>> getAllDataFromTrigger(String triggercode);

    ArrayList<String> getValuesFromRule(String name);
}
