package define.business.controller.factory;

import org.json.JSONObject;

import define.business.domain.businessrules.BusinessRule;

public interface BusinessRuleFactory {
    BusinessRule createRule(JSONObject jsondata);
}
