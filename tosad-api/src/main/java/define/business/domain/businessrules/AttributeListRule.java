package define.business.domain.businessrules;

import define.business.domain.*;

import java.util.ArrayList;

public class AttributeListRule implements BusinessRule {
    private Operator operator;
    private Trigger trigger;
    private ArrayList<LiteralValue> values;
    private Table table;

    public AttributeListRule(Operator operator, Trigger trigger,
                             ArrayList<LiteralValue> values, Table table) {
        this.operator = operator;
        this.trigger = trigger;
        this.values = values;
        this.table = table;
    }

    public String generateList() {
        String value = "";
        for (int i = 0; i <= values.size(); i++) {
            value += "'" + values.get(i).getValue() + "', ";
        }
        return value;
    }

}