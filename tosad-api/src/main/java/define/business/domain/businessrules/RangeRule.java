package define.business.domain.businessrules;

import define.business.domain.*;

import java.util.ArrayList;

public class RangeRule implements BusinessRule {
    private final String name;
    private final Operator operator;
    private final Trigger trigger;
    private final ArrayList<Parameter> parameters;
    private final Table table;

    public RangeRule(String name,
                     Operator operator, Trigger trigger,
                     ArrayList<Parameter> parameters, Table table) {
        this.name = name;
        this.operator = operator;
        this.trigger = trigger;
        this.parameters = parameters;
        this.table = table;
    }

}
