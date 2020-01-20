package define.controller.builder;

import define.domain.*;

public interface BusinessRuleFactory {
    BusinessRule createRule(String name, BusinessRuleType ruletype, Operator operator, Trigger trigger, Parameter parameters, Table table);
}
