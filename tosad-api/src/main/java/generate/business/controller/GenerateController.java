package generate.business.controller;

import generate.business.domain.businessrules.BusinessRule;
import generate.business.domain.businessrules.BusinessRuleController;
import generate.business.domain.businessrules.ruleattributes.*;
import generate.persistence.adapter.DaoAdapter;
import generate.persistence.dao.BaseDao;
import generate.persistence.dao.DefineOracleDao;
import generate.persistence.dao.TargetOracleDao;
import org.json.JSONObject;

import java.util.ArrayList;

public class GenerateController {
    private BaseDao generateconnectionadapter;
    private BaseDao targetconnectionadapter;
    public GenerateController() {
        this.generateconnectionadapter = new DaoAdapter().serialize("Oracle", "jdbc:oracle:thin:@//ondora04.hu.nl:1521/EDUC11", "cursist", "cursist8101");
        this.targetconnectionadapter = new DaoAdapter().serialize("Oracle", "jdbc:oracle:thin:@//ondora04.hu.nl:1521/EDUC11", "maurits", "maurits");
    }

    public ArrayList returnTriggers() {
        ArrayList<String> triggerData = new DefineOracleDao(generateconnectionadapter).getTriggerInfo();

        return triggerData;
    }

    public ArrayList<String> returnRulesByTrigger(String data) {
        JSONObject jsondata = new JSONObject(data);
        Trigger trigger = new Trigger(jsondata.get("name").toString());
        ArrayList<String> triggerData = new DefineOracleDao(generateconnectionadapter).getRulesByTrigger(trigger.getTriggercode());

        return triggerData;
    }

    public ArrayList<String> generateTriggerCode(String data) {
        JSONObject jsondata = new JSONObject(data);
        DefineOracleDao defineOracleDao = new DefineOracleDao(generateconnectionadapter);

        Trigger trigger = new Trigger(jsondata.get("name").toString());
        trigger = new BusinessRuleController(generateconnectionadapter).fillTriggerWithRules(defineOracleDao.getAllDataFromTrigger(trigger.getTriggercode()), trigger);

        ArrayList<BusinessRule> ruleList = trigger.getBusinessRules();
        String tablename = "";
        String bRuleString = "";
        String bRuleDeclare = "";

        for (BusinessRule bRule : ruleList) {
            tablename = bRule.getTable().getName();
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

        ArrayList<String> triggerData = new TargetOracleDao(targetconnectionadapter).executeCode(triggercode.get(0));

        return triggerData;
    }

}
