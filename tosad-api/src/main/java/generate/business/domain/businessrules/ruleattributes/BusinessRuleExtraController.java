package generate.business.domain.businessrules.ruleattributes;

import generate.business.domain.businessrules.BusinessRule;

public class BusinessRuleExtraController {
    public Operator createOperator(String operatorName) {
        return new Operator(operatorName);
    }

    public Table createTable(String tableName, String attributeName) {
        TableAttribute attribute = new TableAttribute(attributeName);
        return new Table(tableName, attribute);
    }

    public LiteralValue createValues(BusinessRule rule) {

        return null;
    }
}
