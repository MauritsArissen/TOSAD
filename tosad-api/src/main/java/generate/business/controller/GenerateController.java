package generate.business.controller;

import generate.business.domain.*;
import generate.business.domain.businessrules.BusinessRule;
import generate.business.controller.factory.BusinessRuleFactory;
import generate.business.controller.factory.TypeBasedBusinessRuleFactory;
import generate.persistence.adapter.DaoAdapter;
import generate.persistence.dao.BaseDao;
import generate.persistence.dao.DefineOracleDao;
import generate.persistence.dao.TargetOracleDao;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class GenerateController {

    public ArrayList returnTriggers() {
        BaseDao generateconnectionadapter = new DaoAdapter().serialize("Oracle", "jdbc:oracle:thin:@//ondora04.hu.nl:1521/EDUC11", "cursist", "cursist8101");
        ArrayList<String> triggerData = new DefineOracleDao(generateconnectionadapter).getTriggerInfo();

        return triggerData;
    }

    public ArrayList<String> returnRulesByTrigger(String data) {
        JSONObject jsondata = new JSONObject(data);
        Trigger trigger = new Trigger(jsondata.get("name").toString());
        BaseDao generateconnectionadapter = new DaoAdapter().serialize("Oracle", "jdbc:oracle:thin:@//ondora04.hu.nl:1521/EDUC11", "cursist", "cursist8101");
        ArrayList<String> triggerData = new DefineOracleDao(generateconnectionadapter).getRulesByTrigger(trigger.getTriggercode());

        return triggerData;
    }

    public ArrayList<String> generateTriggerCode(String data) {
        JSONObject jsondata = new JSONObject(data);
        Trigger trigger = new Trigger(jsondata.get("name").toString());
        BaseDao generateconnectionadapter = new DaoAdapter().serialize("Oracle", "jdbc:oracle:thin:@//ondora04.hu.nl:1521/EDUC11", "cursist", "cursist8101");
        DefineOracleDao defineOracleDao = new DefineOracleDao(generateconnectionadapter);

        ArrayList<HashMap<String, String>> result = defineOracleDao.getAllDataFromTrigger(trigger.getTriggercode());

        for (HashMap<String, String> list : result) {
            BusinessRuleFactory factory = new TypeBasedBusinessRuleFactory(list.get("businessruletypename"));
            Operator operator = new Operator(list.get("operatorname"));
            Table table = new Table(list.get("targettablename"), new TableAttribute(list.get("targettableattribute")));
            BusinessRule rule = factory.createRule(operator, table, list.get("failure_message"), list.get("businessrulename"));
            trigger.addBusinessRule(rule);
        }

        ArrayList<BusinessRule> ruleList = trigger.getBusinessRules();
        String tablename = "";
        String bRuleString = "";

        for (BusinessRule bRule : ruleList) {
            tablename = bRule.getTable().getName();
            ArrayList<String> values = defineOracleDao.getValuesFromRule(bRule.getName());
            for (String value : values) {
                LiteralValue litValue = new LiteralValue(value);
                bRule.addValue(litValue);
            }
            bRuleString += bRule.generateDynamicPart();
        }

        String triggerString = "create or replace trigger " + trigger.getTriggercode() + "\n" +
                trigger.getTriggerevent() + "\n" +
                "  on " + tablename + "\n" +
                "  for each row\n" +
                "declare\n" +
                "  l_passed boolean := true;\n" +
                "  l_error_stack varchar2(4000);\n" +
                " begin\n";

        triggerString += bRuleString;

        triggerString += "end " + trigger.getTriggercode() + ";";
        ArrayList<String> returnList = new ArrayList<>();
        returnList.add(triggerString);
        return returnList;
    }

    public ArrayList<String> generateTrigger(String data) {
        ArrayList<String> triggercode = generateTriggerCode(data);

        BaseDao generateconnectionadapter = new DaoAdapter().serialize("Oracle", "jdbc:oracle:thin:@//ondora04.hu.nl:1521/EDUC11", "maurits", "maurits");
        ArrayList<String> triggerData = new TargetOracleDao(generateconnectionadapter).executeCode(triggercode.get(0));



        return triggerData;
    }

}
