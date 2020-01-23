package generate.business.controller;

import java.util.ArrayList;

import org.json.JSONObject;

import generate.business.domain.Trigger;
import generate.persistence.adapter.DaoAdapter;
import generate.persistence.dao.BaseDao;
import generate.persistence.dao.DefineOracleDao;

public class GenerateController {

    public ArrayList returnTriggers() {
        BaseDao generateconnectionadapter = new DaoAdapter().serialize("Oracle", "jdbc:oracle:thin:@//ondora04.hu.nl:1521/EDUC11", "cursist", "cursist8101");
        ArrayList<String> triggerData = new DefineOracleDao(generateconnectionadapter).getTriggerInfo();

        return triggerData;
    }

    public ArrayList returnRulesByTrigger(String data) {
        JSONObject jsondata = new JSONObject(data);
        Trigger trigger = new Trigger(jsondata.get("name").toString());
        BaseDao generateconnectionadapter = new DaoAdapter().serialize("Oracle", "jdbc:oracle:thin:@//ondora04.hu.nl:1521/EDUC11", "cursist", "cursist8101");
        ArrayList<String> triggerData = new DefineOracleDao(generateconnectionadapter).getRulesByTrigger(trigger);

        return triggerData;
    }

    public String generateTrigger(String data) {
        JSONObject jsondata = new JSONObject(data);
        Trigger trigger = new Trigger(jsondata.get("name").toString());

        return null;
    }

}
