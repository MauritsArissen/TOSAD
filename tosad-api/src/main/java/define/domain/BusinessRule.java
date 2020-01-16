package define.domain;

import java.util.ArrayList;

public class BusinessRule {
    private final String name;
    private final BusinessruleType businessruletype;
    private final Operator operator;
    private final Trigger trigger;
    private final ArrayList<Parameter> parameters;
    private final Table table;


    public BusinessRule(String name, BusinessruleType businessruleType,
                        Operator operator, Trigger trigger,
                        ArrayList<Parameter> parameters, Table table) {
        this.name = name;
        this.businessruletype = businessruleType;
        this.operator = operator;
        this.trigger = trigger;
        this.parameters = parameters;
        this.table = table;
    }
}
