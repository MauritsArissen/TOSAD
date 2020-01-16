package define.domain;

import java.util.ArrayList;

public class BusinessruleType {
    private String code;
    private String name;
    private String description;
    private Operator selectedOperator;
    private ArrayList<Operator> operators;

    public BusinessruleType() {}

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

    public void setOperators(ArrayList<String> names) {
        for(String name : names) {
            operators.add(new Operator(name));
        }
    }
}
