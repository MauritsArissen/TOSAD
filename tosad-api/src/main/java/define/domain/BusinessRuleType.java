package define.domain;

import java.util.ArrayList;

public class BusinessRuleType {
    private String code;
    private String name;
    private String description;
    private Operator selectedOperator;
    private ArrayList<Operator> operators;

    public BusinessRuleType() {}

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSelectedOperator(String name) {
        selectedOperator = new Operator(name);
    }

    public Operator getSelectedOperator() { return selectedOperator; }

    public void setOperators(ArrayList<String> names) {
        for(String name : names) {
            operators.add(new Operator(name));
        }
    }
}
