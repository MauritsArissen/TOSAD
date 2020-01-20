package define.business.domain;

import java.util.ArrayList;

public class Category {
    private String name;
    private ArrayList<BusinessRuleType> businessruletypes = new ArrayList<BusinessRuleType>();

    public Category(String name, ArrayList<BusinessRuleType> businessruletypes) {
        this.name = name;
        this.businessruletypes = businessruletypes;
    }

    public Category(String name) {
        this.name = name;
    }

    public void addRuleType(BusinessRuleType rule) {
        businessruletypes.add(rule);
    }

    public ArrayList<BusinessRuleType> getRuleTypes() {
        return this.businessruletypes;
    }

    public String getName() {
        return name;
    }
}
