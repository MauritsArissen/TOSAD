package generate.business.domain.businessrules;

import generate.business.domain.LiteralValue;
import generate.business.domain.Operator;
import generate.business.domain.Table;
import generate.business.domain.Trigger;

import java.util.ArrayList;

public class AttributeOtherRule implements BusinessRule {
    private Operator operator;
    private ArrayList<LiteralValue> values;
    private Table table;
    private String failuremessage;
    private String name;

    public AttributeOtherRule(Operator operator,
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

    public String generateDynamicPart() {
        String template =  "begin\n" +
                "l_passed := " + values.get(0).getValue() + ";\n" +
                "  if not l_passed\n" +
                "  then\n" +
                "    l_error_stack := '" + failuremessage + "';\n" +
                "    raise_application_error( -20800, l_error_stack );\n" +
                "  end if;\n";
        return template;
    }

}