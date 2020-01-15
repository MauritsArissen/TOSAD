package define.domain;

import java.util.ArrayList;

public class BusinessRule {
    private String name;
    private Trigger trigger;
    private ArrayList<Parameter> parameters;
    private BusinessruleType businessruletype;
    private Table table;

    public BusinessRule() {}
}
