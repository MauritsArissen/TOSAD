package generate.business.domain.businessrules;

import generate.business.domain.LiteralValue;
import generate.business.domain.Operator;
import generate.business.domain.Table;
import generate.business.domain.Trigger;

import java.util.ArrayList;

public class AttributeListRule implements BusinessRule {
    private Operator operator;
    private ArrayList<LiteralValue> values;
    private Table table;
    private String failuremessage;

    public AttributeListRule(Operator operator,
                             ArrayList<LiteralValue> values, Table table, String failuremessage) {
        this.operator = operator;
        this.values = values;
        this.table = table;
        this.failuremessage = failuremessage;
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
        for (int i = 0; i <= values.size(); i++) {
            value += "'" + values.get(i).getValue() + "', ";
        }
        return value;
    }

}