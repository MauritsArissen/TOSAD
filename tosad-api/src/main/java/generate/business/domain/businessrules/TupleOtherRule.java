package generate.business.domain.businessrules;

import generate.business.domain.businessrules.ruleattributes.LiteralValue;
import generate.business.domain.businessrules.ruleattributes.Operator;
import generate.business.domain.businessrules.ruleattributes.Table;

import java.util.ArrayList;

public class TupleOtherRule implements BusinessRule {
    private Operator operator;
    private ArrayList<LiteralValue> values;
    private Table table;
    private String failuremessage;
    private String name;

    public TupleOtherRule(Operator operator, Table table, String failuremessage, String name) {
        this.operator = operator;
        this.values = values;
        this.table = table;
        this.failuremessage = failuremessage;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Table getTable () {
        return table;
    }

    public void addValue(LiteralValue value) {
        values.add(value);
    }

    public String generateDynamicPart() {
        String template =  "--" + name + "\n" +
                "l_passed := " + values.get(0).getValue() + ";\n" +
                "  if not l_passed\n" +
                "  then\n" +
                "    l_error_stack := '" + failuremessage + "';\n" +
                "    raise_application_error( -20800, l_error_stack );\n" +
                "  end if;\n";
        return template;
    }

    public String generateDeclare() {
        return "";
    }

}