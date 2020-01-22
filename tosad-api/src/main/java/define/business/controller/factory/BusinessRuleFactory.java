package define.business.controller.factory;

import java.util.ArrayList;

import define.business.domain.LiteralValue;
import define.business.domain.Table;
import define.business.domain.TableAttribute;
import define.business.domain.Trigger;
import define.business.domain.businessrules.BusinessRule;

public interface BusinessRuleFactory {
    BusinessRule createRule(ArrayList<LiteralValue> values, TableAttribute tableattribute, Trigger trigger, Table table);
}
