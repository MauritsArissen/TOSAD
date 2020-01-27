package generate.business.controller.factory;

import generate.business.domain.businessrules.BusinessRule;
import generate.business.domain.*;

public interface BusinessRuleFactory {
    BusinessRule createRule(Operator operator, Table table, String failuremessage, String name);
}
