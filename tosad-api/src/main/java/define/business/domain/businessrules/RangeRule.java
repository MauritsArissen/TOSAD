package define.business.domain.businessrules;

import define.business.domain.*;

import java.util.ArrayList;

public class RangeRule implements BusinessRule {
    private final String name;
    private final Operator operator;
    private final Trigger trigger;
    private final Table table;

    public RangeRule(String name, Operator operator, Trigger trigger, Table table) {
        this.name = name;
        this.operator = operator;
        this.trigger = trigger;
        this.table = table;
    }

}
