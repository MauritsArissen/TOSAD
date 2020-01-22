package generate.business.controller.factory;

import generate.business.domain.*;

public interface BusinessRuleFactory {
    String createRule(String name, Operator operator, Trigger trigger, Table table);
}
