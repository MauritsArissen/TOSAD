package generate.business.controller.factory;

import generate.business.domain.*;

public interface BusinessRuleFactory {
    BusinessRule createRule(String name, BusinessRuleType ruletype, Operator operator, Trigger trigger, Parameter parameters, Table table);
}
