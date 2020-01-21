package generate.business.domain.businessrules;

import generate.business.domain.*;


import java.util.ArrayList;

public class RangeRule implements BusinessRule {
    private Operator operator;
    private Trigger trigger;
    private ArrayList<LiteralValue> values;
    private Table table;
    private ArrayList<BusinessRule> businessRules;

    public RangeRule(Operator operator, Trigger trigger,
                     ArrayList<LiteralValue> values, Table table,
                     ArrayList<BusinessRule> businessRules) {
        this.operator = operator;
        this.trigger = trigger;
        this.values = values;
        this.table = table;
        this.businessRules = businessRules;
    }

    public String generate() {
        String template = "create or replace trigger " + trigger.getTriggercode() + "\n" +
                "  before " + trigger.getTriggerevent() + "\n" +
                "  on " + table.getName() + "\n" +
                "  for each row\n" +
                "declare\n" +
                "  l_passed boolean := true;\n" +
                "  l_error_stack varchar2(4000);\n" +
                "begin\n";
        businessRules = trigger.getBusinessRules();

        for (BusinessRule b : businessRules) {
            template += b.generateDynamicPart() + "\n";
        }

        template += "end " + trigger.getTriggercode() + ";";

        return template;
    }

    public String generateDynamicPart() {
        return "l_passed := :new." + table.getSelectedTableAttribute() + " " + operator.getName() + " " +
                values.get(0).getValue() + " and " + values.get(1).getValue() + ";\n" +
                "  if not l_passed\n" +
                "  then\n" +
                "    l_error_stack := '" + trigger.getFailuremessage() + "';\n" +
                "    raise_application_error( -20800, l_error_stack );\n" +
                "  end if;\n";
    }

}
