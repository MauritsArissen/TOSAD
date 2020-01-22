package generate.business.domain.businessrules;

import generate.business.domain.LiteralValue;
import generate.business.domain.Operator;
import generate.business.domain.Table;
import generate.business.domain.Trigger;

import java.util.ArrayList;

public class AttributeListRule implements BusinessRule {
    private Operator operator;
    private Trigger trigger;
    private ArrayList<LiteralValue> values;
    private Table table;

    public AttributeListRule(Operator operator, Trigger trigger,
                                ArrayList<LiteralValue> values, Table table) {
        this.operator = operator;
        this.trigger = trigger;
        this.values = values;
        this.table = table;
    }

    public String generateDynamicPart() {
        return "l_passed := :new." + table.getSelectedTableAttribute() + " " + operator.getName() + " " +
                "(" + generateList() + ")" +
                "  if not l_passed\n" +
                "  then\n" +
                "    l_error_stack := '" + trigger.getFailuremessage() + "';\n" +
                "    raise_application_error( -20800, l_error_stack );\n" +
                "  end if;\n";
    }

    public String generateList() {
        String value = "";
        for (int i = 0; i <= values.size(); i++) {
            value += "'" + values.get(i).getValue() + "', ";
        }
        return value;
    }

}