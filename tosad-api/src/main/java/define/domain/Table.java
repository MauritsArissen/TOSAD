package define.domain;

import java.util.ArrayList;

public class Table {
    private String name;
    private TableAttribute selectedTableAttribute;
    private ArrayList<TableAttribute> attributes;

    public Table(String name, TableAttribute selectedTableAttribute,
                 ArrayList<TableAttribute> attributes) {
        this.name = name;
        this.selectedTableAttribute = selectedTableAttribute;
        this.attributes = attributes;
    }
}
