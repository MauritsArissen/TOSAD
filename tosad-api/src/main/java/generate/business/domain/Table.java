package generate.business.domain;

import java.util.ArrayList;

public class Table {
    private String name;
    private ArrayList<TableAttribute> selectedTableAttributes;

    public Table(String name, ArrayList<TableAttribute> selectedTableAttributes) {
        this.name = name;
        this.selectedTableAttributes = selectedTableAttributes;
    }

    public String getName() {
        return name;
    }

    public ArrayList<TableAttribute> getSelectedTableAttributes() {
        return selectedTableAttributes;
    }

}
