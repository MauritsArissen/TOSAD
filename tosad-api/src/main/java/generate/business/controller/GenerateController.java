package generate.business.controller;

import generate.business.domain.businessrules.BusinessRule;
import generate.business.domain.businessrules.BusinessRuleController;
import generate.business.domain.businessrules.ruleattributes.*;
import generate.business.domain.factory.BusinessRuleFactory;
import generate.business.domain.factory.TypeBasedBusinessRuleFactory;
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
        BaseDao generateconnectionadapter = new DaoAdapter().serialize("Oracle", "jdbc:oracle:thin:@//ondora04.hu.nl:1521/EDUC11", "cursist", "cursist8101");
        DefineOracleDao defineOracleDao = new DefineOracleDao(generateconnectionadapter);

        Trigger trigger = new Trigger(jsondata.get("name").toString());
        trigger = new BusinessRuleController().fillTriggerWithRules(defineOracleDao.getAllDataFromTrigger(trigger.getTriggercode()), trigger);

        ArrayList<BusinessRule> ruleList = trigger.getBusinessRules();
        String tablename = "";
        String bRuleString = "";
        String bRuleDeclare = "";

        for (BusinessRule bRule : ruleList) {
            tablename = bRule.getTable().getName();
            ArrayList<String> values = defineOracleDao.getValuesFromRule(bRule.getName());
            for (String value : values) {
                LiteralValue litValue = new LiteralValue(value);
                bRule.addValue(litValue);
            }
            if(bRule.getClass().getName().equals("ModifyRule"))
            bRuleString += bRule.generateDynamicPart();
            bRuleDeclare += bRule.generateDeclare();
        }

        String triggerString = "create or replace trigger " + trigger.getTriggercode() + "\n" +
                trigger.getTriggerevent() + "\n" +
                "  on " + tablename + "\n" +
                "  for each row\n" +
                "declare\n" +
                "  l_passed boolean := true;\n" +
                "  l_error_stack varchar2(4000);\n" +
                bRuleDeclare +
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
