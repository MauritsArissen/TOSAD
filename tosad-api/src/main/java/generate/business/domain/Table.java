package generate.business.domain;

import java.util.ArrayList;

public class Table {
    private String name;
    private TableAttribute selectedTableAttribute;
    private ArrayList<TableAttribute> attributes;

    public Table(String name, TableAttribute selectedTableAttribute, ArrayList<TableAttribute> attributes) {
        this.name = name;
        this.selectedTableAttribute = selectedTableAttribute;
        this.attributes = attributes;
    }

    public String getName() {
        return name;
    }

    public TableAttribute getSelectedTableAttribute() {
        return selectedTableAttribute;
    }

    public ArrayList<TableAttribute> getAttributes() {
        return attributes;
    }
}
