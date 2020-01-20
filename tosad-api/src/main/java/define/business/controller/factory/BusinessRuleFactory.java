package define.business.controller.factory;

import define.business.domain.*;
import define.business.domain.businessrules.BusinessRule;
import define.business.domain.businessrules.RangeRule;

public interface BusinessRuleFactory {
    BusinessRule createRule(String name, Operator operator, Trigger trigger, Parameter parameters, Table table);
}
