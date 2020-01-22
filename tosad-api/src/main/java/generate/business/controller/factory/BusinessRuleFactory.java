package generate.business.controller.factory;

import generate.business.domain.*;
import generate.business.domain.businessrules.BusinessRule;
import generate.business.domain.businessrules.RangeRule;

import java.util.ArrayList;

public interface BusinessRuleFactory {
    String createRule(String name, Operator operator, Trigger trigger, Table table);
}
