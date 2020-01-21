package generate.business.domain.businessrules;

import generate.business.domain.*;


import java.util.ArrayList;
import java.util.Collections;

public class RangeRule implements BusinessRule {
    private String name;
    private Operator operator;
    private Trigger trigger;
    private ArrayList<Parameter> parameters;
    private Table table;
    private String triggerCode;
    private String template;
    private ArrayList<BusinessRule> businessRules;

    public RangeRule(String name,
                     Operator operator, Trigger trigger,
                     ArrayList<Parameter> parameters, Table table) {
        this.name = name;
        this.operator = operator;
        this.trigger = trigger;
        this.parameters = parameters;
        this.table = table;
    }

    public String generate() {
        template = "create or replace trigger " + trigger.getTriggercode() + "\n" +
                "  before " + trigger.getTriggerevent() + "\n" +
                "  on " + table.getName() + "\n" +
                "  for each row\n" +
                "declare\n" +
                "  l_passed boolean := true;\n" +
                "  l_error_stack varchar2(4000);\n" +
                "begin\n";
        businessRules = trigger.getBusinessRules();
        for (BusinessRule b : businessRules) {
            //b.
        }
        /*
        triggerCode = triggerCode.replace("[CONSTRAINT_STATEMENT]", );
        triggerCode = triggerCode.replace("[TRIGGER_CODE]", );
        triggerCode = triggerCode.replace("[FAILURE_MESSAGE]", )
        */

        return triggerCode;
    }

}
