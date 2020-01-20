package generate.business.domain;

import java.util.ArrayList;

public class BusinessRule {
    private String name;
    private Trigger trigger;
    private ArrayList<Parameter> parameters;
    private BusinessRuleType businessruletype;
    private Table table;

    public BusinessRule() {}
}
