package define.controller.builder;

import define.domain.*;

import java.util.ArrayList;

public class BusinessRuleBuilder implements Builder {
    private String name;
    private BusinessruleType businessruletype;
    private Operator operator;
    private Trigger trigger;
    private ArrayList<Parameter> parameters;
    private Table table;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setType(BusinessruleType businessruleType) {
        this.businessruletype = businessruleType;
    }

    @Override
    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    @Override
    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    @Override
    public void setParameters(ArrayList<Parameter> parameters) {
        this.parameters = parameters;
    }

    @Override
    public void setTarget(Table table) {
        this.table = table;
    }

    public BusinessRule build() {
        return new BusinessRule(name, businessruletype, operator,
                trigger, parameters, table);
    }
}
