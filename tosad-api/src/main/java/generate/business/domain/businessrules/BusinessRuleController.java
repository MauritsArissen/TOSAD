package generate.business.domain.businessrules;

import generate.business.domain.businessrules.ruleattributes.*;
import generate.business.domain.factory.BusinessRuleFactory;
import generate.business.domain.factory.TypeBasedBusinessRuleFactory;

import java.util.ArrayList;
import java.util.HashMap;

public class BusinessRuleController {
    BusinessRuleExtraController extraController = new BusinessRuleExtraController();

    public Trigger fillTriggerWithRules (ArrayList<HashMap<String, String>> ruleData, Trigger trigger) {
        Trigger updatedTrigger = trigger;
        for (HashMap<String, String> list : ruleData) {
            Operator operator = extraController.createOperator(list.get("operatorname"));
            Table table = extraController.createTable(list.get("targettablename"), list.get("targettableattribute"));
            BusinessRuleFactory factory = new TypeBasedBusinessRuleFactory(list.get("businessruletypename"));
            BusinessRule rule = factory.createRule(operator, table, list.get("failure_message"), list.get("businessrulename"));
            updatedTrigger.addBusinessRule(rule);
        }
        return updatedTrigger;
    }
}
