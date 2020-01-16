package define.controller.builder;

import define.domain.*;

import java.util.ArrayList;

public class BusinessRuleBuilder implements Builder {
    private String name;
    private BusinessRuleType businessRuleType;
    private Operator operator;
    private Trigger trigger;
    private ArrayList<Parameter> parameters;
    private Table table;

    public BusinessRuleBuilder() {
    }

    @Override
    public void reset() {
        this.reset();
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setType(BusinessRuleType businessRuleType) {
        this.businessRuleType = businessRuleType;
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
        BusinessRule br = new BusinessRule(name, businessRuleType, operator, trigger, parameters, table);
        this.reset();
        return br;
    }
}
