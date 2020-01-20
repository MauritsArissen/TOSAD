package generate.business.controller.factory;

import generate.business.domain.*;
import generate.business.domain.businessrules.RangeRule;

public interface BusinessRuleFactory {
    RangeRule createRule(String name, Operator operator, Trigger trigger, Parameter parameters, Table table);
}
