package generate.business.domain.businessrules;

import generate.business.domain.LiteralValue;
import generate.business.domain.Operator;
import generate.business.domain.Table;
import generate.business.domain.Trigger;

import java.util.ArrayList;

public class AttributeListRule implements BusinessRule {
    private Operator operator;
    private ArrayList<LiteralValue> values = new ArrayList<>();
    private Table table;
    private String failuremessage;
    private String name;

    public AttributeListRule(Operator operator,
                             Table table, String failuremessage, String name) {
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
        String template =  "begin\n" +
                "l_passed := :new." + table.getSelectedTableAttribute() + " " + operator.getName() + " " +
                "(" + generateList() + ")" +
                "  if not l_passed\n" +
                "  then\n" +
                "    l_error_stack := '" + failuremessage + "';\n" +
                "    raise_application_error( -20800, l_error_stack );\n" +
                "  end if;\n";
        return template;
    }

    public String generateList() {
        String value = "";
        if (!values.isEmpty()) {
            for (LiteralValue v : values) {
                value += "'" + v.getValue() + "', ";
            }
        }
        return value;
    }

}