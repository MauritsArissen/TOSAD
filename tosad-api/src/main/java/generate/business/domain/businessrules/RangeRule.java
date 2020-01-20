package generate.business.domain.businessrules;

import generate.business.domain.*;


import java.util.ArrayList;
import java.util.Collections;

public class RangeRule implements BusinessRule {
    private final String name;
    private final Operator operator;
    private final Trigger trigger;
    private final ArrayList<Parameter> parameters;
    private final Table table;
    private int minimumValue = 0;
    private int maximumValue = 0;

    public RangeRule(String name,
                     Operator operator, Trigger trigger,
                     ArrayList<Parameter> parameters, Table table) {
        this.name = name;
        this.operator = operator;
        this.trigger = trigger;
        this.parameters = parameters;
        this.table = table;
    }

    public String generateRangeRule() {
        trigger = "create or replace TRIGGER " + this.name + "\n" +
                "    BEFORE INSERT ON " + this.table + "\n" +
                "    FOR EACH ROW\n" +
                "DECLARE\n" +
                "    l_passed boolean;\n" +
                "BEGIN\n" +
                "    l_passed := " + attribute + " " + this.operator + " " + value1 + " and " + value2 + ";\n" +
                "    IF not l_passed \n" +
                "        THEN    \n" +
                "            raise_application_error(-20000, '" + failureMessage + "');\n" +
                "    END IF;\n" +
                "END generated_name;";
    }

    public int getMinimumValue() {
        return minimumValue;
    }

    public int getMaximumValue() {
        return maximumValue;
    }

    public int defineMinMaxValues() {
        maximumValue = Collections.max(parameters);
        minimumValue = Collections.min(parameters);
    }

}
