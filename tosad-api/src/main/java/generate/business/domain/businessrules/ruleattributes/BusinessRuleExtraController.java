package generate.business.domain.businessrules.ruleattributes;

import generate.business.domain.businessrules.BusinessRule;
import generate.persistence.dao.BaseDao;
import generate.persistence.dao.DefineOracleDao;

import java.util.ArrayList;

public class BusinessRuleExtraController {
    private BaseDao generateconnectionadapter;

    public BusinessRuleExtraController(BaseDao generateconnectionadapter) {
        this.generateconnectionadapter = generateconnectionadapter;
    }

    public Operator createOperator(String operatorName) {
        return new Operator(operatorName);
    }

    public Table createTable(String tableName, String attributeName) {
        TableAttribute attribute = new TableAttribute(attributeName);
        return new Table(tableName, attribute);
    }

    public BusinessRule addValuesToRule(BusinessRule rule) {
        ArrayList<String> values = new DefineOracleDao(generateconnectionadapter).getValuesFromRule(rule.getName());
        for (String value : values) {
            LiteralValue litValue = new LiteralValue(value);
            rule.addValue(litValue);
        }
        return rule;
    }
}
