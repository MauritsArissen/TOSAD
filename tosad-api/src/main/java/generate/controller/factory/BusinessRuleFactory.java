package generate.controller.factory;

import generate.domain.*;

public interface BusinessRuleFactory {
    BusinessRule createRule(String name, BusinessRuleType ruletype, Operator operator, Trigger trigger, Parameter parameters, Table table);
}
