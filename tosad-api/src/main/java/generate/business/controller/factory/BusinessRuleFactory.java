package generate.business.controller.factory;

import generate.business.domain.*;
import generate.business.domain.businessrules.BusinessRule;
import generate.business.domain.businessrules.RangeRule;

public interface BusinessRuleFactory {
    String createRule(String name, Operator operator, Trigger trigger, Parameter parameters, Table table);
}
