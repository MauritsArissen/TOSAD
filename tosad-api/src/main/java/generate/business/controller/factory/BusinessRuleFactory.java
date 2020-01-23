package generate.business.controller.factory;

import define.business.domain.businessrules.BusinessRule;
import generate.business.domain.*;
import org.json.JSONObject;

public interface BusinessRuleFactory {
    BusinessRule createRule(JSONObject jsondata);
}
