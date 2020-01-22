package generate.business.domain;

import java.util.ArrayList;

public class Table {
    private String name;
    private TableAttribute selectedTableAttributes;

    public Table(String name, TableAttribute selectedTableAttributes) {
        this.name = name;
        this.selectedTableAttributes = selectedTableAttributes;
    }

    public String getName() {
        return name;
    }

    public TableAttribute getSelectedTableAttributes() {
        return selectedTableAttributes;
    }

}
