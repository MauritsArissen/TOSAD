package define.domain;

import java.util.ArrayList;

public class BusinessRuleType {
    private String code;
    private String name;
    private String description;
    private Operator selectedOperator;
    private ArrayList<Operator> operators = new ArrayList<Operator>();

    public BusinessRuleType(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }
    
    public ArrayList<Operator> getAllOperators() {
    	return operators;
    }

    public BusinessRuleType() {}

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSelectedOperator(String name) {
        selectedOperator = new Operator(name);
    }

    public Operator getSelectedOperator() { return selectedOperator; }

    public void setOperator(Operator operator) {
        operators.add(operator);
    }
}
