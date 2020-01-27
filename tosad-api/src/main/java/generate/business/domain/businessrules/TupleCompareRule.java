package generate.business.domain.businessrules;

import generate.business.domain.LiteralValue;
import generate.business.domain.Operator;
import generate.business.domain.Table;

import java.util.ArrayList;

public class TupleCompareRule implements BusinessRule {
    private Operator operator;
    private ArrayList<LiteralValue> values;
    private Table table;
    private String failuremessage;
    private String name;

    public TupleCompareRule(Operator operator,
                            ArrayList<LiteralValue> values, Table table, String failuremessage, String name) {
        this.operator = operator;
        this.values = values;
        this.table = table;
        this.failuremessage = failuremessage;
        this.name = name;
    }

    public void addValue(LiteralValue value) {
        values.add(value);
    }

    public String getName() {
        return name;
    }

    public Table getTable () {
        return table;
    }

    public String generateDynamicPart() {
        //Checks of het vergelijkbare data typen zijn, front-end check?

        //Er is nog geen onderscheid in de getSelectedTableAttribute(). Het heeft nog geen weet van de twee verschillende attributen
        String template =  "begin\n" +
                "l_passed := :new." + table.getSelectedTableAttribute() + " " + operator.getName() + " :new." +
                values.get(0).getValue() + ";\n" +
                "  if not l_passed\n" +
                "  then\n" +
                "    l_error_stack := '" + failuremessage + "';\n" +
                "    raise_application_error( -20800, l_error_stack );\n" +
                "  end if;\n";
        return template;
    }
}