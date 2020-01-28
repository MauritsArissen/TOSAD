package generate.business.domain.businessrules;

import generate.business.domain.businessrules.ruleattributes.*;
import generate.business.domain.factory.BusinessRuleFactory;
import generate.business.domain.factory.TypeBasedBusinessRuleFactory;
import generate.persistence.dao.BaseDao;

import java.util.ArrayList;
import java.util.HashMap;

public class BusinessRuleController {
    private BusinessRuleExtraController extraController;

    public BusinessRuleController(BaseDao generateconnectionadapter) {
        extraController = new BusinessRuleExtraController(generateconnectionadapter);
    }

    public Trigger fillTriggerWithRules (ArrayList<HashMap<String, String>> ruleData, Trigger trigger) {
        Trigger updatedTrigger = trigger;
        for (HashMap<String, String> list : ruleData) {
            Operator operator = extraController.createOperator(list.get("operatorname"));
            Table table = extraController.createTable(list.get("targettablename"), list.get("targettableattribute"));
            BusinessRuleFactory factory = new TypeBasedBusinessRuleFactory(list.get("businessruletypename"));
            BusinessRule rule = factory.createRule(operator, table, list.get("failure_message"), list.get("businessrulename"));
            rule = extraController.addValuesToRule(rule);
            updatedTrigger.addBusinessRule(rule);

        }
        return updatedTrigger;
    }
}
