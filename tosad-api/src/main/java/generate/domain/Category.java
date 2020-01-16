package generate.domain;

import java.util.ArrayList;

public class Category {
    private String name;
    private ArrayList<BusinessRuleType> businessruletypes;

    public Category() {}

    public Category(String name) {
        this.name = name;
    }
}
