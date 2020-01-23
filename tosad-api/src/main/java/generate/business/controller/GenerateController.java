package generate.business.controller;

import generate.business.domain.businessrules.BusinessRule;
import generate.business.controller.factory.BusinessRuleFactory;
import generate.business.controller.factory.TypeBasedBusinessRuleFactory;
import generate.business.domain.Operator;
import generate.business.domain.Table;
import generate.business.domain.TableAttribute;
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
        ArrayList<String> triggerData = new DefineOracleDao(generateconnectionadapter).getRulesByTrigger(trigger.getTriggercode());

        return triggerData;
    }

    public ArrayList generateTrigger(String data) {
        //JSONObject jsondata = new JSONObject(data);
        //Trigger trigger = new Trigger(jsondata.get("name").toString());

        Trigger trigger = new Trigger(data);
        BaseDao generateconnectionadapter = new DaoAdapter().serialize("Oracle", "jdbc:oracle:thin:@//ondora04.hu.nl:1521/EDUC11", "cursist", "cursist8101");
        ArrayList<HashMap<String, String>> result = new DefineOracleDao(generateconnectionadapter).getAllDataFromTrigger(trigger.getTriggercode());

        for (HashMap<String, String> list : result) {
            BusinessRuleFactory factory = new TypeBasedBusinessRuleFactory(list.get("businessruletypename"));
            Operator operator = new Operator(list.get("operatorname"));
            Table table = new Table(list.get("targettablename"), new TableAttribute(list.get("targettableattributename")));
            BusinessRule rule = factory.createRule(operator, table, list.get("failure_message"), list.get("businessrulename"));
            trigger.addBusinessRule(rule);
        }

        ArrayList<BusinessRule> ruleList = trigger.getBusinessRules();

        for (BusinessRule bRule : ruleList) {
            System.out.println(bRule.getName());
        }

        return null;
    }

}
