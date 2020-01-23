package generate.business.domain.businessrules;

import generate.business.domain.*;


import java.util.ArrayList;

public class AttributeRangeRule implements BusinessRule {
    private Operator operator;
    private Trigger trigger;
    private ArrayList<LiteralValue> values;
    private Table table;

    public AttributeRangeRule(Operator operator, Trigger trigger,
                              ArrayList<LiteralValue> values, Table table) {
        this.operator = operator;
        this.trigger = trigger;
        this.values = values;
        this.table = table;
    }

    public String generateDynamicPart() {
        String template =  "begin\n" +
                "l_passed := :new." + table.getSelectedTableAttribute() + " " + operator.getName() + " " +
                values.get(0).getValue() + " and " + values.get(1).getValue() + ";\n" +
                "  if not l_passed\n" +
                "  then\n" +
                "    l_error_stack := '" + trigger.getFailuremessage() + "';\n" +
                "    raise_application_error( -20800, l_error_stack );\n" +
                "  end if;\n";
        return template;
    }

}
