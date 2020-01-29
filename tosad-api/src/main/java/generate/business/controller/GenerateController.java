package generate.business.controller;

import generate.persistence.targetdaofactory.TargetDaoFactory;
import generate.persistence.targetdaofactory.TypeBasedTargetDaoFactory;
import generate.persistence.dao.*;
import generate.business.domain.businessrules.BusinessRule;
import generate.business.domain.businessrules.BusinessRuleController;
import generate.business.domain.businessrules.ruleattributes.*;
import org.json.JSONObject;

import java.util.ArrayList;

public class GenerateController {
    private DefineDao definedao;
    private TargetDao targetDao;

    public GenerateController() {
        BaseDao defineconnection = new OracleBaseDao("jdbc:oracle:thin:@//ondora04.hu.nl:1521/EDUC11", "cursist", "cursist8101");
        this.definedao = new DefineOracleDao(defineconnection);
    }

    public void setTargetDao(JSONObject data) {
        TargetDaoFactory targetdaofactory = new TypeBasedTargetDaoFactory(data.get("type").toString(), data.get("url").toString(), data.get("username").toString(), data.get("password").toString());
        this.targetDao = targetdaofactory.getTargetDao();
    }


    public ArrayList<String> returnTriggers(String data) {
        JSONObject jsondata = new JSONObject(data);
        setTargetDao(jsondata);
        ArrayList<String> triggerData = definedao.getTriggerInfo(jsondata.getString("name"));

        return triggerData;
    }

    public ArrayList<String> returnRulesByTrigger(String data) {
        JSONObject jsondata = new JSONObject(data);
        Trigger trigger = new Trigger(jsondata.get("name").toString());
        ArrayList<String> triggerData = definedao.getRulesByTrigger(trigger.getTriggercode());

        return triggerData;
    }

    public ArrayList<String> generateTriggerCode(String data) {
        JSONObject jsondata = new JSONObject(data);
        Trigger trigger = new Trigger(jsondata.get("name").toString());
        trigger = new BusinessRuleController(definedao).fillTriggerWithRules(definedao.getAllDataFromTrigger(trigger.getTriggercode()), trigger);

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
        JSONObject jsondata = new JSONObject(data);
        JSONObject credentials = new JSONObject(jsondata.get("credentials"));
        setTargetDao(credentials);
        ArrayList<String> triggercode = generateTriggerCode(data);

        ArrayList<String> triggerData = targetDao.executeCode(triggercode.get(0));

        return triggerData;
    }

}
